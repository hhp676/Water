<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- sqlmapper 层 -->
<mapper namespace="${pkgName}.${mapperFolder}.${meta.className}${MapperSuffix}">

    <#if (hasFk)>
    <!-- result map -->
    <resultMap type="${meta.className}" id="${meta.firstLowerClassName}Map">
        <#list meta.cols as col>
        <<#if (col.pkFlag)>id<#else>result</#if> property="${col.fieldName}" column="${col.fieldName}" />
        </#list>
    </resultMap>
    </#if>

    <!-- sqlColumn -->
    <sql id="SQL_${meta.tableNameUC}_COLUMN">
        <#list meta.cols as col>
        ${meta.tableAlias}.${col.colName} ${col.fieldName}<#if col_index + 1 < meta.cols?size>,</#if>
        </#list>
    </sql>

    <!-- sqlWhere -->
    <sql id="SQL_${meta.tableNameUC}_WHERE">
        <#list meta.cols as col>
            <#if col.colType == "String">
        		<if test="${col.fieldName} != null and ${col.fieldName}.length() != 0">
        		AND ${meta.tableAlias}.${col.colName} like '%${r'${' + col.fieldName + '}'}%'
            <#else>
        	<if test="${col.fieldName} != null">
	            AND ${meta.tableAlias}.${col.colName} = ${r'#{' + col.fieldName + '}'}
            </#if>
        </if>
        </#list>
        <if test="filterStr !=null and filterStr.length()!=0">
            ${r'${filterStr}'}
        </if>
    </sql>

    <#--getById-->
    <!-- According to the id check record  -->
    <select id="getById" parameterType="long" resultType="${meta.className}">
        select <include  refid="SQL_${meta.tableNameUC}_COLUMN" />
        from ${meta.tableNameUC} ${meta.tableAlias}
        <where>
            <#list meta.pkCols as col>
            ${meta.tableAlias}.${col.colName} = ${r'#{idValue}'}
            </#list>
        </where>
    </select>

    <#--getList-->
    <!-- Query All  -->
    <select id="getList" parameterType="${meta.className}" resultType="${meta.className}">
        select <include  refid="SQL_${meta.tableNameUC}_COLUMN"/>
        from ${meta.tableNameUC} ${meta.tableAlias}
        <where>
            <include  refid="SQL_${meta.tableNameUC}_WHERE"/>
        </where>
        <if test="orderStr != null and orderStr.length() != 0">
            ORDER BY ${r'${orderStr}'}
        </if>
        <if test="orderStr == null or orderStr.length() == 0">
            ORDER BY
            <#list meta.pkCols as col>
                ${meta.tableAlias}.${col.colName} desc<#if col_index + 1 < meta.pkCols?size>,</#if>
            </#list>
        </if>
    </select>

    <#--getListCount-->
    <!-- Statistical line   -->
    <select id="getListCount" parameterType="${meta.className}" resultType="int">
        select count(
            ${meta.tableAlias}.${meta.pkCols[0].colName}
        ) as counts
        from ${meta.tableNameUC} ${meta.tableAlias}
        <where>
            <include  refid="SQL_${meta.tableNameUC}_WHERE"/>
        </where>
    </select>
    
	<!-- add 默认使用全局ID -->
	<insert id="insert" parameterType="${meta.className}" >
		insert into ${meta.tableNameUC} 
		<trim prefix="(" suffix=")" suffixOverrides=",">
		  <#list meta.cols as col>
		  	<if test="${col.fieldName} != null">
        		${col.colName},
      		</if>
		  </#list>
		</trim> 
		<trim prefix="values (" suffix=")" suffixOverrides=",">
		    <#list meta.cols as col>
		        <#if col.colType == "String">
			  	<if test="${col.fieldName} != null">
			  	  	   ${r'#{' + col.fieldName + ',jdbcType=VARCHAR}'},
			  	</if>  
			  	</#if>
			  	<#if col.colType == "Integer">
			  	<if test="${col.fieldName} != null">
			  	  	   ${r'#{' + col.fieldName + ',jdbcType=NUMERIC}'},
			  	</if>  
			  	</#if>
			  	<#if col.colType == "Long">
			  	<if test="${col.fieldName} != null">
			  	  	   ${r'#{' + col.fieldName + ',jdbcType=NUMERIC}'},
			  	</if>  
			  	</#if>
			  	<#if col.colType == "TIMESTAMP">
			  	<if test="${col.fieldName} != null">
			  	  	   ${r'#{' + col.fieldName + ',jdbcType=TIMESTAMP}'},
			  	</if>  
			  	</#if>
			  	<#if col.colType == "java.math.BigDecimal">
			  	<if test="${col.fieldName} != null">
			  	  	   ${r'#{' + col.fieldName + ',jdbcType=NUMERIC}'},
			  	</if>  
			  	</#if>
		    </#list>
		</trim>
    </insert>

	<!-- update  -->
	<update id="update" parameterType="${meta.className}">
		UPDATE ${meta.tableNameUC}
		<set>
		 <#list meta.cols as col>
		  	<if test="${col.fieldName} != null">
        		${col.colName} = ${r'#{' + col.fieldName + '}'} <#if col_index + 1 < meta.cols?size>,</#if>
      		</if>
		  </#list>
		 
		</set>
		WHERE
		  <#list meta.pkCols as col>
            ${meta.tableAlias}.${col.colName} = ${r'#{idValue}'}
          </#list>
	</update>

	<!-- delete  -->
	<delete id="delete" parameterType="${meta.className}">
		DELETE FROM ${meta.tableNameUC}
		WHERE
		 <#list meta.pkCols as col>
            ${meta.tableAlias}.${col.colName} = ${r'#{idValue}'}
         </#list>
	</delete>
 
</mapper>
