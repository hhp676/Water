/*
 * File Name:JbzProductInfoService.java
 * Package Name:com.hongguaninfo.hgdf.adp.service.jbz
 * Date:2018年01月11日 下午9:09:20
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.jbz.service.jbz;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.jbz.dao.jbz.JbzProductInfoDao;
import com.hongguaninfo.hgdf.jbz.entity.jbz.JbzProductInfo;



/**
 * : jbz_product_info. <br />
 * service interface 层 <br />
 * Date: 2018年01月11日 下午9:09:20 <br />
 * @author  hhp
 * @since V1.0.0
 */
@Service("jbzProductInfoService")
public class JbzProductInfoService {

	@Autowired
	private JbzProductInfoDao jbzProductInfoDao;

	@Autowired
	private DbIdGenerator dbIdGenerator;
	
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<JbzProductInfo> getJbzProductInfoList (BasePage<JbzProductInfo> basePage,
            JbzProductInfo vo, Map<String, Object> map) throws BizException {
//		vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<JbzProductInfo> page = jbzProductInfoDao.pageQuery(basePage);
        List<JbzProductInfo> list = page.getResult();
        /**for (JbzProductInfo bo : list) {
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
        }*/
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
	}
    
	/**
	 * 查询信息
	 * @param basePage
	 * @param vo
	 * @param map
	 * @return
	 * @throws BizException
	 */
	public List<JbzProductInfo> getInfoList (JbzProductInfo jbzInfo) throws BizException {
		return jbzProductInfoDao.getList(jbzInfo);
	}
	/**
	 * REMARK
	 * 新增信息
	 * Through the id inquires the out a data
	 * Date need their conversion !
	 * UserName is the current user name !
	 */
	public void addJbzProductInfo (JbzProductInfo jbzProductInfo) throws BizException{
		 
//		jbzProductInfo.setIsDelete(0);
//        jbzProductInfo.setIsFinal(0);
//        jbzProductInfo.setCrtTime(new Date());
//        jbzProductInfo.setCrtUserid(SessionUtils.getUserId());
//        jbzProductInfo.setUpdTime(new Date());
//        jbzProductInfo.setUpdUserid(SessionUtils.getUserId());
		jbzProductInfoDao.save(jbzProductInfo);
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateJbzProductInfo (JbzProductInfo jbzProductInfo) throws BizException{
// 		jbzProductInfo.setUpdTime(new Date());
//        jbzProductInfo.setUpdUserid(SessionUtils.getUserId());
		jbzProductInfoDao.update(jbzProductInfo);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteJbzProductInfo(int id) throws BizException{
		jbzProductInfoDao.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteJbzProductInfoLogic(int id) throws BizException{
        JbzProductInfo jbzProductInfo = new JbzProductInfo();
//        jbzProductInfo.setUpdTime(new Date());
//        jbzProductInfo.setUpdUserid(SessionUtils.getUserId());
//        jbzProductInfo.setIsDelete(1);
        jbzProductInfoDao.update(jbzProductInfo);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public JbzProductInfo getJbzProductInfoById(int id) throws BizException{
		if (id == 0) {
            return null;
        }
        return jbzProductInfoDao.getById(id);
	}
}
