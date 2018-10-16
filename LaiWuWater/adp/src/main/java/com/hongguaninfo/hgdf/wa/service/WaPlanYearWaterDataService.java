/*
 * File Name:WaPlanYearWaterDataService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.service;


import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.wa.dao.WaCompanyInfoDao;
import com.hongguaninfo.hgdf.wa.dao.WaPlanYearWaterDataDao;
import com.hongguaninfo.hgdf.wa.entity.WaCompanyInfo;
import com.hongguaninfo.hgdf.wa.entity.WaPlanYearWaterData;
import com.hongguaninfo.hgdf.wa.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * : wa_plan_year_water_data. <br />
 * service interface 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 * @author  hhp
 * @since V1.0.0
 */
@Service("waPlanYearWaterDataService")
public class WaPlanYearWaterDataService {

	@Autowired
	private WaPlanYearWaterDataDao waPlanYearWaterDataDao;

	@Autowired
	private DbIdGenerator dbIdGenerator;

	@Autowired
	private WaCompanyInfoDao waCompanyInfoDao;
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<WaPlanYearWaterData> getWaPlanYearWaterDataList (BasePage<WaPlanYearWaterData> basePage,
																 WaPlanYearWaterData vo, Map<String, Object> map) throws BizException {
		vo.setIsDelte(0);
        basePage.setFilters(vo);
        Page<WaPlanYearWaterData> page = waPlanYearWaterDataDao.pageQuery(basePage);
        List<WaPlanYearWaterData> list = page.getResult();
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
	public void addWaPlanYearWaterData (WaPlanYearWaterData waPlanYearWaterData) throws BizException{


		waPlanYearWaterData.setIsDelte(0);
		WaPlanYearWaterData resultEntity = waPlanYearWaterDataDao.getByYearWaterData(waPlanYearWaterData);
		if(!StringUtil.isNull(resultEntity)){   //先删除后新增
			waPlanYearWaterDataDao.deleteByWaterData(resultEntity);
		}
		waPlanYearWaterData.setCrtTime(new Date());
		waPlanYearWaterData.setUpdTime(new Date());
		waPlanYearWaterDataDao.save(waPlanYearWaterData);
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateWaPlanYearWaterData (WaPlanYearWaterData waPlanYearWaterData) throws BizException{
 		waPlanYearWaterData.setUpdTime(new Date());
		waPlanYearWaterData.setIsDelte(0);
		waPlanYearWaterDataDao.update(waPlanYearWaterData);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteWaPlanYearWaterData(int id) throws BizException{
		waPlanYearWaterDataDao.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteWaPlanYearWaterDataLogic(int planWaterId) throws BizException{
        WaPlanYearWaterData waPlanYearWaterData = new WaPlanYearWaterData();
        waPlanYearWaterData.setPlanWaterId(planWaterId);
        waPlanYearWaterData.setUpdTime(new Date());
        waPlanYearWaterData.setIsDelte(1);
        waPlanYearWaterDataDao.update(waPlanYearWaterData);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public WaPlanYearWaterData getWaPlanYearWaterDataById(int id) throws BizException{
		if (id == 0) {
            return null;
        }
        return waPlanYearWaterDataDao.getById(id);
	}

	public List<WaPlanYearWaterData> getPlanWaterList(WaPlanYearWaterData waterData){
		return  waPlanYearWaterDataDao.getList(waterData);
	}

	/**
	 * 获取下一年的时间
	 * @return
	 */
	public static String getNextYear(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		date = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date checkDate = date != null ? date : new Date();
		return dateFormat.format(checkDate);
	}

	/**
	 * 去除待计算年的list
	 * @param yearList
	 * @return
	 */
	public static List<WaPlanYearWaterData> getYearWaterList(List<WaPlanYearWaterData> yearList){
		List<WaPlanYearWaterData> resultYearList = new ArrayList<>();
		String nextYear = getNextYear();
		for (WaPlanYearWaterData data: yearList) {
			if (!data.getPlanYear().equals(nextYear)){
				resultYearList.add(data);
			}
		}
		return resultYearList;
	}
	/**
	 * excel文件遍历
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public boolean doReadXls(InputStream is) throws Exception{
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(is);
			HSSFSheet sheet = workBook.getSheetAt(0);
			boolean result=true;//返回结果标识
			boolean cell_0=true,cell_1=true,cell_2=true,cell_3=true,cell_4=true;//单元格校验通过与否的标识
			boolean cell_5=true,cell_6=true,cell_7=true,cell_8=true,cell_9=true;
			List<WaPlanYearWaterData> planWaterList = new ArrayList<WaPlanYearWaterData>();//创建list，用做中间变量，暂时存放user

			for (int rowNum = 1;rowNum <= sheet.getLastRowNum();rowNum++) {
				HSSFRow row = sheet.getRow(rowNum);
				WaPlanYearWaterData waMonthWaterEntity = new WaPlanYearWaterData();
				if (row != null) {
					if(StringUtil.isEmpty(ExcelUtil.getCellValue(row.getCell(0)))){
						continue;
					}
					WaCompanyInfo com = new WaCompanyInfo();
					com.setCompanyCode(ExcelUtil.getCellValue(row.getCell(0)));
					WaCompanyInfo resultCom = waCompanyInfoDao.getEntityByCode(com);
					//根据code获取id后存入mysql
					waMonthWaterEntity.setCompanyId(String.valueOf(resultCom.getCompanyId()));
					waMonthWaterEntity.setPlanYear(ExcelUtil.getCellValue(row.getCell(2)));
					waMonthWaterEntity.setPlanYearAvgWater(ExcelUtil.getCellValue(row.getCell(3)));
					planWaterList.add(waMonthWaterEntity);
				}
			}
			if(result){
				for(WaPlanYearWaterData monData : planWaterList){
					WaPlanYearWaterData tmp = new WaPlanYearWaterData();
					tmp.setCompanyId(monData.getCompanyId());
					tmp.setPlanYear(monData.getPlanYear());
					tmp.setIsDelte(0);
					List<WaPlanYearWaterData> resultTmpList = new ArrayList<>();
					resultTmpList = waPlanYearWaterDataDao.getList(tmp);
					if(resultTmpList.size()>0){  //判断当前单位当年数据是否已存在,存在即先删除在录入
						tmp.setPlanWaterId(resultTmpList.get(0).getPlanWaterId());
						waPlanYearWaterDataDao.update(tmp);
					}else {
						addWaPlanYearWaterData(monData);
					}
				}
			}
			return result;
		}catch (Exception e){
			return false;
		}
	}

}
