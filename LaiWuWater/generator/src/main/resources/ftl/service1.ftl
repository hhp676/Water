/*
 * File Name:${meta.className}${ServiceSuffix}.java
 * Package Name:${pkgName}.${serviceFolder}
 * Date:${currentDate}
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package ${pkgName}.${serviceFolder};


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.core.utils.page.Page;

import ${pkgName}.${entityFolder}.${meta.className};
import ${pkgName}.${daoFolder}.${meta.className}${DaoSuffix};

/**
 * ${meta.tableDesc}: ${meta.tableName}. <br />
 * service interface 层 <br />
 * Date: ${currentDate} <br />
 * @author  ${author}
 * @since V1.0.0
 */
@Service("${meta.firstLowerClassName}${ServiceSuffix}")
public class ${meta.className}${ServiceSuffix} {

	@Autowired
	private ${meta.className}${DaoSuffix} ${meta.firstLowerClassName}${DaoSuffix};

	@Autowired
	private DbIdGenerator dbIdGenerator;
	
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<${meta.className}> get${meta.className}List (BasePage<${meta.className}> basePage,
            ${meta.className} vo, Map<String, Object> map) throws BizException {
		vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<${meta.className}> page = ${meta.firstLowerClassName}${DaoSuffix}.pageQuery(basePage);
        List<${meta.className}> list = page.getResult();
        /**for (${meta.className} bo : list) {
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
        }*/
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
	}
    
	/**
	 * REMARK
	 * 新增信息
	 * Through the id inquires the out a data
	 * Date need their conversion !
	 * UserName is the current user name !
	 */
	public void add${meta.className} (${meta.className} ${meta.firstLowerClassName}) throws BizException{
		 
		${meta.firstLowerClassName}.setIsDelete(0);
        ${meta.firstLowerClassName}.setIsFinal(0);
        ${meta.firstLowerClassName}.setCrtTime(new Date());
        ${meta.firstLowerClassName}.setCrtUserid(SessionUtils.getUserId());
        ${meta.firstLowerClassName}.setUpdTime(new Date());
        ${meta.firstLowerClassName}.setUpdUserid(SessionUtils.getUserId());
		${meta.firstLowerClassName}${DaoSuffix}.save(${meta.firstLowerClassName});
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void update${meta.className} (${meta.className} ${meta.firstLowerClassName}) throws BizException{
 		${meta.firstLowerClassName}.setUpdTime(new Date());
        ${meta.firstLowerClassName}.setUpdUserid(SessionUtils.getUserId());
		${meta.firstLowerClassName}${DaoSuffix}.update(${meta.firstLowerClassName});
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void delete${meta.className}(int id) throws BizException{
		${meta.firstLowerClassName}${DaoSuffix}.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void delete${meta.className}Logic(int id) throws BizException{
        ${meta.className} ${meta.firstLowerClassName} = new ${meta.className}();
        ${meta.firstLowerClassName}.setUpdTime(new Date());
        ${meta.firstLowerClassName}.setUpdUserid(SessionUtils.getUserId());
        ${meta.firstLowerClassName}.setIsDelete(1);
        ${meta.firstLowerClassName}${DaoSuffix}.update(${meta.firstLowerClassName});
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public ${meta.className} get${meta.className}ById(int id) throws BizException{
		if (id == 0) {
            return null;
        }
        return ${meta.firstLowerClassName}${DaoSuffix}.getById(id);
	}
}
