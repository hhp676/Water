/*
 * File Name:WaSavewaterTypeService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.service;


import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.wa.dao.WaSavewaterTypeDao;
import com.hongguaninfo.hgdf.wa.entity.WaSavewaterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * : wa_savewater_type. <br />
 * service interface 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 * @author  hhp
 * @since V1.0.0
 */
@Service("waSavewaterTypeService")
public class WaSavewaterTypeService {

	@Autowired
	private WaSavewaterTypeDao waSavewaterTypeDao;

	@Autowired
	private DbIdGenerator dbIdGenerator;
	
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<WaSavewaterType> getWaSavewaterTypeList (BasePage<WaSavewaterType> basePage,
														 WaSavewaterType vo, Map<String, Object> map) throws BizException {
		vo.setIsDelte(0);
        basePage.setFilters(vo);
        Page<WaSavewaterType> page = waSavewaterTypeDao.pageQuery(basePage);
        List<WaSavewaterType> list = page.getResult();
        /**for (WaSavewaterType bo : list) {
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
	public void addWaSavewaterType (WaSavewaterType waSavewaterType) throws BizException{
		 
		waSavewaterType.setIsDelte(0);
        waSavewaterType.setCrtTime(new Date());
        waSavewaterType.setUpdTime(new Date());
		waSavewaterTypeDao.save(waSavewaterType);
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateWaSavewaterType (WaSavewaterType waSavewaterType) throws BizException{
 		waSavewaterType.setUpdTime(new Date());
		waSavewaterTypeDao.update(waSavewaterType);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteWaSavewaterType(int id) throws BizException{
		waSavewaterTypeDao.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteWaSavewaterTypeLogic(int id) throws BizException{
        WaSavewaterType waSavewaterType = new WaSavewaterType();
        waSavewaterType.setUpdTime(new Date());
        waSavewaterType.setIsDelte(1);
        waSavewaterTypeDao.update(waSavewaterType);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public WaSavewaterType getWaSavewaterTypeById(int id) throws BizException{
		if (id == 0) {
            return null;
        }
        return waSavewaterTypeDao.getById(id);
	}
}
