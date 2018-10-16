/*
 * File Name:WaDormitoryWaterDataService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.wa
 * Date:2018年09月14日 上午9:23:39
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.service.totalinfo;


import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.wa.dao.totalinfo.WaDormitoryWaterDataDao;
import com.hongguaninfo.hgdf.wa.entity.totalinfo.WaDormitoryWaterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * : wa_dormitory_water_data. <br />
 * service interface 层 <br />
 * Date: 2018年09月14日 上午9:23:39 <br />
 * @author  hhp
 * @since V1.0.0
 */
@Service("waDormitoryWaterDataService")
public class WaDormitoryWaterDataService {

	@Autowired
	private WaDormitoryWaterDataDao waDormitoryWaterDataDao;

	@Autowired
	private DbIdGenerator dbIdGenerator;
	
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<WaDormitoryWaterData> getWaDormitoryWaterDataList (BasePage<WaDormitoryWaterData> basePage,
																   WaDormitoryWaterData vo, Map<String, Object> map) throws BizException {
		vo.setIsDelte(0);
        basePage.setFilters(vo);
        Page<WaDormitoryWaterData> page = waDormitoryWaterDataDao.pageQuery(basePage);
        List<WaDormitoryWaterData> list = page.getResult();
        /**for (WaDormitoryWaterData bo : list) {
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
	public void addWaDormitoryWaterData (WaDormitoryWaterData waDormitoryWaterData) throws BizException{
		 
		waDormitoryWaterData.setIsDelte(0);
        waDormitoryWaterData.setCrtTime(new Date());
        waDormitoryWaterData.setUpdTime(new Date());
		waDormitoryWaterDataDao.save(waDormitoryWaterData);
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateWaDormitoryWaterData (WaDormitoryWaterData waDormitoryWaterData) throws BizException{
 		waDormitoryWaterData.setUpdTime(new Date());
		waDormitoryWaterDataDao.update(waDormitoryWaterData);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteWaDormitoryWaterData(int id) throws BizException{
		waDormitoryWaterDataDao.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteWaDormitoryWaterDataLogic(int id) throws BizException{
        WaDormitoryWaterData waDormitoryWaterData = new WaDormitoryWaterData();
        waDormitoryWaterData.setUpdTime(new Date());
        waDormitoryWaterData.setIsDelte(1);
        waDormitoryWaterDataDao.update(waDormitoryWaterData);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public WaDormitoryWaterData getWaDormitoryWaterDataById(int id) throws BizException{
		if (id == 0) {
            return null;
        }
        return waDormitoryWaterDataDao.getById(id);
	}

	public WaDormitoryWaterData getDorDataByCompanyId(int companyId) throws BizException{
		if (companyId == 0){
			return null;
		}
		return waDormitoryWaterDataDao.getDorDataByCompanyId(companyId);
	}
}
