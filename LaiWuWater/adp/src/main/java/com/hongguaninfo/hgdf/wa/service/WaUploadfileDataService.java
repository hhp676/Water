/*
 * File Name:WaUploadfileDataService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.wa
 * Date:2018年10月12日 下午4:17:20
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.service;


import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.wa.dao.WaUploadfileDataDao;
import com.hongguaninfo.hgdf.wa.entity.WaUploadfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * : wa_uploadfile_data. <br />
 * service interface 层 <br />
 * Date: 2018年10月12日 下午4:17:20 <br />
 * @author  hhp
 * @since V1.0.0
 */
@Service("waUploadfileDataService")
public class WaUploadfileDataService {

	@Autowired
	private WaUploadfileDataDao waUploadfileDataDao;

	@Autowired
	private DbIdGenerator dbIdGenerator;
	
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<WaUploadfileData> getWaUploadfileDataList (BasePage<WaUploadfileData> basePage,
														   WaUploadfileData vo, Map<String, Object> map) throws BizException {
		vo.setIsDelte(0);
        basePage.setFilters(vo);
        Page<WaUploadfileData> page = waUploadfileDataDao.pageQuery(basePage);
        List<WaUploadfileData> list = page.getResult();
        /**for (WaUploadfileData bo : list) {
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
	public void addWaUploadfileData (WaUploadfileData waUploadfileData) throws BizException{
		 
		waUploadfileData.setIsDelte(0);
        waUploadfileData.setCrtTime(new Date());
        waUploadfileData.setUpdTime(new Date());
		waUploadfileDataDao.save(waUploadfileData);
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateWaUploadfileData (WaUploadfileData waUploadfileData) throws BizException{
 		waUploadfileData.setUpdTime(new Date());
		waUploadfileDataDao.update(waUploadfileData);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteWaUploadfileData(int id) throws BizException{
		waUploadfileDataDao.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteWaUploadfileDataLogic(int id) throws BizException{
        WaUploadfileData waUploadfileData = new WaUploadfileData();
        waUploadfileData.setFileid(id);
        waUploadfileData.setUpdTime(new Date());
        waUploadfileData.setIsDelte(1);
        waUploadfileDataDao.update(waUploadfileData);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public WaUploadfileData getWaUploadfileDataById(int id) throws BizException{
		if (id == 0) {
            return null;
        }
        return waUploadfileDataDao.getById(id);
	}

	public List<WaUploadfileData> getFileListByCompanyId(int id) throws BizException{
		if (id == 0) {
			return null;
		}
		WaUploadfileData waUploadfileData = new WaUploadfileData();
		waUploadfileData.setCompanyId(String.valueOf(id));
		waUploadfileData.setIsDelte(0);
		return waUploadfileDataDao.getList(waUploadfileData);
	}
}
