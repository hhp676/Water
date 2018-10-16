package com.hongguaninfo.hgdf.tss.core.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.hongguaninfo.hgdf.core.utils.PropertiesHelper;
import com.hongguaninfo.hgdf.core.utils.jdbc.dialect.Dialect;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * 2012-08-14 由于源代码不兼容mybatis3.1 故在此修改覆盖 henry
 * 
 * 为ibatis3提供基于方言(Dialect)的分页查询的插件
 * 
 * 将拦截Executor.query()方法实现分页方言的插入.
 * 
 * 配置文件内容:
 * 
 * <pre>
 * 	&lt;plugins>
 * 	&lt;plugin interceptor="cn.org.rapid_framework.ibatis3.plugin.OffsetLimitInterceptor">
 * 		&lt;property name="dialectClass" value="cn.org.rapid_framework.jdbc.dialect.MySQLDialect"/>
 * 	&lt;/plugin>
 * &lt;/plugins>
 * </pre>
 * 
 * @author badqiu
 * 
 */

@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class, Object.class, RowBounds.class,
        ResultHandler.class }) })
public class OffsetLimitInterceptor implements Interceptor {

    private Log log = LogFactory.getLog(getClass());

    private static final int MAPPED_STATEMENT_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;
    private static final int ROWBOUNDS_INDEX = 2;
    private static final int RESULT_HANDLER_INDEX = 3;

    private Dialect dialect;

    public Object intercept(Invocation invocation) throws Exception {
        processIntercept(invocation.getArgs());
        return invocation.proceed();
    }

    void processIntercept(final Object[] queryArgs) {
        /*queryArgs = query(MappedStatement ms, Object parameter, RowBounds
        rowBounds, ResultHandler resultHandler)*/
        MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
        int offset = rowBounds.getOffset();
        int limit = rowBounds.getLimit();

        if (dialect.supportsLimit()
                && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
            BoundSql boundSql = ms.getBoundSql(parameter);
            String sql = boundSql.getSql().trim();
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, offset, limit);
                offset = RowBounds.NO_ROW_OFFSET;
            } else {
                sql = dialect.getLimitString(sql, 0, limit);
            }
            limit = RowBounds.NO_ROW_LIMIT;

            queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);

            BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);

            MappedStatement newMs = copyFromMappedStatement(ms,
                    new BoundSqlSqlSource(newBoundSql));
            queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
        }
    }

    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
            String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql,
                boundSql.getParameterMappings(), boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop,
                        boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    // see: MapperBuilderAssistant
    private MappedStatement copyFromMappedStatement(MappedStatement ms,
            SqlSource newSqlSource) {
        Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        /*
         * 不兼容mybatis3.1 拿掉 builder.keyProperty(ms.getKeyProperty());
         */

        // setStatementTimeout()
        builder.timeout(ms.getTimeout());

        // setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        // setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        // setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        String dialectClass = new PropertiesHelper(properties)
                .getRequiredProperty("dialectClass");
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            log.error("cannot create dialect instance by dialectClass:"
                    + dialectClass, e);
        }
        log.debug(OffsetLimitInterceptor.class.getSimpleName() + ".dialect="
                + dialectClass);
    }

    public static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}
