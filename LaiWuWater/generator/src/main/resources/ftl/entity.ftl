/*
 * File Name:${meta.className}.java
 * Package Name:${pkgName}.${entityFolder}
 * Date:${currentDate}
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package ${pkgName}.${entityFolder};

import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * ${meta.tableDesc}: ${meta.tableName}. <br />
 * entity 层 <br />
 * Date: ${currentDate} <br />
 *
 * @author  ${author}
 * @since V1.0.0
 */
 
public class ${meta.className} extends ${baseEntity} {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    <#list meta.cols as col>
    /**
     * ${col.colName} : ${col.colDesc}。
     */
    private ${col.javaType} ${col.fieldName};
    </#list>
 


    <#list meta.cols as col>
        
    public ${col.javaType} ${col.geOperName}() {
        return ${col.fieldName};
    }

    public void ${col.seOperName}(${col.javaType} ${col.fieldName}) {
        this.${col.fieldName} = ${col.fieldName};
    }

    </#list>
    

}
