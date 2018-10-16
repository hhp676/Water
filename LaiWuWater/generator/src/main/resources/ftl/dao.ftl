/*
 * File Name:${meta.className}${DaoSuffix}.java
 * Package Name:${pkgName}.${daoFolder}
 * Date:${currentDate}
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package ${pkgName}.${daoFolder};

import org.springframework.stereotype.Repository;

import com.hongguaninfo.hgdf.adp.core.base.BaseDao;
import ${pkgName}.${entityFolder}.${meta.className};
import ${pkgName}.${mapperFolder}.${meta.className}${MapperSuffix};

/**
 * ${meta.tableDesc}: ${meta.tableName}. <br />
 * dao 层 <br />
 * Date: ${currentDate} <br />
 * @author ${author}
 * @since V1.0.0
 */
@SuppressWarnings("unchecked")
@Repository("${meta.firstLowerClassName}${DaoSuffix}")
public class ${meta.className}${DaoSuffix} extends BaseDao<${meta.className}, ${meta.className}${MapperSuffix}, Integer> implements
		${meta.className}${MapperSuffix}{

    @Override
    public String getMapperNamesapce() {
        return ${meta.className}${MapperSuffix}.class.getName().toString;
    }


}
