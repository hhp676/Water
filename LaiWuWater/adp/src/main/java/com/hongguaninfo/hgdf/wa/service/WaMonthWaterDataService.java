/*
 * File Name:WaMonthWaterDataService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.service;


import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.wa.dao.WaCompanyInfoDao;
import com.hongguaninfo.hgdf.wa.dao.WaMonthWaterDataDao;
import com.hongguaninfo.hgdf.wa.entity.WaCompanyInfo;
import com.hongguaninfo.hgdf.wa.entity.WaMonthWaterData;
import com.hongguaninfo.hgdf.wa.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * : wa_month_water_data. <br />
 * service interface 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 * @author  hhp
 * @since V1.0.0
 */
@Service("waMonthWaterDataService")
public class WaMonthWaterDataService {

	@Autowired
	private WaMonthWaterDataDao waMonthWaterDataDao;

	@Autowired
	private SysDatadicItemService sysDatadicItemBiz;

	@Autowired
	private WaCompanyInfoDao waCompanyInfoDao;

	private static final Log LOG = LogFactory.getLog(WaMonthWaterDataService.class);
	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<WaMonthWaterData> getWaMonthWaterDataList (BasePage<WaMonthWaterData> basePage,
														   WaMonthWaterData vo, Map<String, Object> map) throws BizException {
		vo.setIsDelte(0);
		String monthnData = (StringUtil.isEmpty(vo.getWaYear())? "" : vo.getWaYear()) + "" + (StringUtil.isEmpty(vo.getWaMonth()) ? "" : "" + vo.getWaMonth());
        vo.setMonthDate(monthnData);

		basePage.setFilters(vo);
        Page<WaMonthWaterData> page = waMonthWaterDataDao.pageQuery(basePage);
		List<WaMonthWaterData> list = getDataList(page);
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
	}

	/**
	 * REMARK
	 * 月计划用水分页查询，去除月用水量为空数据
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<WaMonthWaterData> getWaMonthWaterPlanDataList (BasePage<WaMonthWaterData> basePage,
														   WaMonthWaterData vo, Map<String, Object> map) throws BizException {
		vo.setIsDelte(0);
		String monthnData = (StringUtil.isEmpty(vo.getWaYear())? "" : vo.getWaYear()) + "" + (StringUtil.isEmpty(vo.getWaMonth()) ? "" : "" + vo.getWaMonth());
		vo.setMonthDate(monthnData);

		basePage.setFilters(vo);
		Page<WaMonthWaterData> page = waMonthWaterDataDao.pagePlanDataQuery(basePage);
		List<WaMonthWaterData> list = getDataList(page);
		map.put("rows", list);
		map.put("total", page.getTotalCount());
		return list;
	}

	/**
	 * REMARK
	 * 月实际用水分页查询，去除月实际量为空数据
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<WaMonthWaterData> getWaMonthWaterActDataList (BasePage<WaMonthWaterData> basePage,
															   WaMonthWaterData vo, Map<String, Object> map) throws BizException {
		vo.setIsDelte(0);
		String monthnData = (StringUtil.isEmpty(vo.getWaYear())? "" : vo.getWaYear()) + "" + (StringUtil.isEmpty(vo.getWaMonth()) ? "" : "" + vo.getWaMonth());
		vo.setMonthDate(monthnData);

		basePage.setFilters(vo);
		Page<WaMonthWaterData> page = waMonthWaterDataDao.pageActDataQuery(basePage);
		List<WaMonthWaterData> list = getDataList(page);
		map.put("rows", list);
		map.put("total", page.getTotalCount());
		return list;
	}

	/**
	 * 公共使用方法获取list返回值
	 * @param page
	 * @return
	 * @throws BizException
	 */
	public List<WaMonthWaterData> getDataList(Page<WaMonthWaterData> page) throws BizException {
		List<WaMonthWaterData> list = page.getResult();
		for (WaMonthWaterData bo : list) {
			bo.setIsOverroof(sysDatadicItemBiz.getItemNameByValue("IS_OVERROOF",
					bo.getIsOverroof() + ""));
			bo.setIsImport(sysDatadicItemBiz.getItemNameByValue("IS_IMPORT",
					bo.getIsImport() + ""));
		}
		return list;
	}

    
	/**
	 * REMARK
	 * 新增信息
	 * Through the id inquires the out a data
	 * Date need their conversion !
	 * UserName is the current user name !
	 */
	public void addWaMonthWaterData (WaMonthWaterData waMonthWaterData, String doType) throws BizException{
		//先判断是否存在该月数据,存在即只更新计划用水操作
		WaMonthWaterData tmp = new WaMonthWaterData();
		tmp.setCompanyId(waMonthWaterData.getCompanyId());
		tmp.setMonthDate(waMonthWaterData.getMonthDate());
		tmp.setIsDelte(0);
		WaMonthWaterData resultTmp = new WaMonthWaterData();
		resultTmp = waMonthWaterDataDao.getWaListByEntity(tmp);
		if(null != resultTmp){  //判断当前单位当月数据是否已存在,存在即先删除在录入
			if ("plan".equals(doType)){  //计划用水操作，需要赋值新进的计划用水
				resultTmp.setPlanMonthWater(waMonthWaterData.getPlanMonthWater());
			}else if("act".equals(doType)){ //实际用水操作，需要赋值新进来的实际用水
				resultTmp.setActMonthWater(waMonthWaterData.getActMonthWater());
			}

			waMonthWaterDataDao.update(getEntityByFee(resultTmp));
			return;
		}

		///新增数据入库
		waMonthWaterData.setIsOverroof("0");
		waMonthWaterData.setIsDelte(0);
        waMonthWaterData.setCrtTime(new Date());
        waMonthWaterData.setUpdTime(new Date());
		waMonthWaterDataDao.save(getEntityByFee(waMonthWaterData));
	}

	/**
	 * 获取数据库里面的数据内容
	 * @param waMonthWaterData
	 * @return
	 */
	public WaMonthWaterData getEntityByFee(WaMonthWaterData waMonthWaterData){
		int planWaterAmount = Integer.valueOf(StringUtil.isEmpty(waMonthWaterData.getPlanMonthWater())? "0": waMonthWaterData.getPlanMonthWater());
		int actWaterAmount = Integer.valueOf(StringUtil.isEmpty(waMonthWaterData.getActMonthWater())? "0": waMonthWaterData.getActMonthWater());
		int beyondAmount = actWaterAmount - planWaterAmount;
		String beyondResult = beyondAmount<0? "0" : String.valueOf(beyondAmount);
		waMonthWaterData.setBeyondAmount(beyondResult);
		if (beyondAmount > 0){  //计划<实际则超标
			waMonthWaterData.setIsOverroof("1");
		}
		waMonthWaterData.setFeeStandard(getBeyondFee(actWaterAmount, planWaterAmount));  //获取收费标准
		return waMonthWaterData;
	}

	public String getBeyondFee(int actWaterAmount, int planWaterAmount){
		DecimalFormat df=new DecimalFormat("0.00");
		String result = "";
		int beyondAmount = actWaterAmount - planWaterAmount;
		float beyondRate = (float) beyondAmount/planWaterAmount;
		if (actWaterAmount == 0 || StringUtil.isNull(actWaterAmount)){  //实际用水未生成情况下
			return "";
		}

		//情况1：未超计划用水
		if (beyondAmount<=0){
			result = "按照正常收费标准收费";//"正常收费用水量：" + actWaterAmount + "; ";
		}
		//超计划用水 水量在0-0.1之间
		if (beyondRate>0 && beyondRate <=0.1){
			result = "超计划10%内（含）部分，按照当地水资源标准1倍加收";//" 正常收费用水量："+ planWaterAmount +";\n 按当地水资费标准1倍加收量：" + beyondAmount;
		}
		else if (beyondRate>0.1 && beyondRate<=0.3){
			result =  "超计划10%至30%内（含）部分，按照当地水资源标准2倍加收";  //  " 正常收费用水量："+ planWaterAmount +";\n 按当地水资费标准1倍加收量：" + df.format(0.1*planWaterAmount) +";\n 按当地水资源资费标准2倍加收量: "+df.format((beyondRate-0.1)*planWaterAmount);
		}
		else if(beyondRate>0.3){
			result = "超计划30%以上部分，按照当地水资源标准3倍加收";  // " 正常收费用水量："+ planWaterAmount +";\n 按当地水资费标准1倍加收量：" + df.format(0.1*planWaterAmount) +";\n 按当地水资源资费标准2倍加收量: "+ df.format(0.2*planWaterAmount) +";\n 按当地水资源资费标准3倍加收量:" + df.format((beyondRate-0.3)*planWaterAmount);
		}
		return result;
	}
	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateWaMonthWaterData (WaMonthWaterData waMonthWaterData, String doType) throws BizException{
		WaMonthWaterData tmp = new WaMonthWaterData();
//		tmp.setCompanyId(waMonthWaterData.getCompanyId());
		tmp.setMonthWaterId(waMonthWaterData.getMonthWaterId());
		tmp.setIsDelte(0);
		WaMonthWaterData resultTmp = new WaMonthWaterData();
		resultTmp = waMonthWaterDataDao.getWaListByEntity(tmp);

		if ("plan".equals(doType)){
			waMonthWaterData.setActMonthWater(resultTmp.getActMonthWater());
		}else if("act".equals(doType)){
			waMonthWaterData.setPlanMonthWater(resultTmp.getPlanMonthWater());
		}

 		waMonthWaterData.setUpdTime(new Date());
		waMonthWaterData.setIsOverroof("0");
		int planWaterAmount = Integer.valueOf(StringUtil.isEmpty(waMonthWaterData.getPlanMonthWater())? "0": waMonthWaterData.getPlanMonthWater());
		int actWaterAmount = Integer.valueOf(StringUtil.isEmpty(waMonthWaterData.getActMonthWater())? "0": waMonthWaterData.getActMonthWater());
		int beyondAmount = actWaterAmount - planWaterAmount;
		String beyondResult = beyondAmount<0 ? "0" : String.valueOf(beyondAmount);
		waMonthWaterData.setBeyondAmount(beyondResult);
		if (beyondAmount > 0){  //计划<实际则超标
			waMonthWaterData.setIsOverroof("1");
		}
		waMonthWaterData.setFeeStandard(getBeyondFee(actWaterAmount, planWaterAmount));  //获取收费标准

		waMonthWaterDataDao.update(waMonthWaterData);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteWaMonthWaterData(int id) throws BizException{
		waMonthWaterDataDao.delete(id);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteWaMonthWaterDataLogic(int monthWaterId, String doType) throws BizException{
        WaMonthWaterData waMonthWaterData = new WaMonthWaterData();
        waMonthWaterData.setUpdTime(new Date());

        if ("plan".equals(doType)){  //计划用水 需要将planwater清空
        	waMonthWaterData.setPlanMonthWater("");
		}else if("act".equals(doType)){  //实际用水，需要将actwater清空
        	waMonthWaterData.setActMonthWater("");
		}else {
			waMonthWaterData.setIsDelte(1);
		}
        waMonthWaterData.setMonthWaterId(monthWaterId);
        waMonthWaterDataDao.update(waMonthWaterData);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public WaMonthWaterData getWaMonthWaterDataById(WaMonthWaterData entity) throws BizException{
        return waMonthWaterDataDao.getWaListByEntity(entity);
	}

	/**
	 * 读取excel文件
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
			List<WaMonthWaterData> monthWaterList = new ArrayList<WaMonthWaterData>();//创建list，用做中间变量，暂时存放user

			for (int rowNum = 1;rowNum <= sheet.getLastRowNum();rowNum++) {
				HSSFRow row = sheet.getRow(rowNum);
				WaMonthWaterData waMonthWaterEntity = new WaMonthWaterData();
				if (row != null) {
					if(StringUtil.isEmpty(ExcelUtil.getCellValue(row.getCell(0)))){
						continue;
					}
					WaCompanyInfo com = new WaCompanyInfo();
					com.setCompanyCode(ExcelUtil.getCellValue(row.getCell(0)));
					WaCompanyInfo resultCom = waCompanyInfoDao.getEntityByCode(com);
					//根据code获取id后存入mysql
					waMonthWaterEntity.setCompanyId(String.valueOf(resultCom.getCompanyId()));
					waMonthWaterEntity.setMonthDate(ExcelUtil.getCellValue(row.getCell(2)));
					waMonthWaterEntity.setActMonthWater(ExcelUtil.getCellValue(row.getCell(3)));
					monthWaterList.add(waMonthWaterEntity);
				}
			}
			if(result){
				for(WaMonthWaterData monData : monthWaterList){
					WaMonthWaterData tmp = new WaMonthWaterData();
					tmp.setCompanyId(monData.getCompanyId());
					tmp.setMonthDate(monData.getMonthDate());
					tmp.setIsDelte(0);
					WaMonthWaterData resultTmp = new WaMonthWaterData();
					resultTmp = waMonthWaterDataDao.getWaListByEntity(tmp);
					if(null != resultTmp){  //判断当前单位当月数据是否已存在,存在即先删除在录入
						waMonthWaterDataDao.delete(resultTmp);
					}
					addWaMonthWaterData(monData, "");
				}
			}
			return result;
		}catch (Exception e){
			return false;
		}
	}

	public WaMonthWaterData getTagFile(MultipartFile file){
		try {
			WaMonthWaterData resultEntity = getXlsContnet(file.getInputStream());
			return resultEntity;
		}catch (Exception e){
			LOG.error("获取数据出错", e);
		}
		return null;
	}

	/**
	 * 获取实体类
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public WaMonthWaterData getXlsContnet(InputStream is) throws Exception{
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(is);
			HSSFSheet sheet = workBook.getSheetAt(0);
			WaMonthWaterData waMonthWaterEntity = new WaMonthWaterData();
			for (int rowNum = 1;rowNum <= sheet.getLastRowNum();rowNum++) {
				HSSFRow row = sheet.getRow(rowNum);
				if (row != null) {
					if(StringUtil.isEmpty(ExcelUtil.getCellValue(row.getCell(0)))){
						continue;
					}
					WaCompanyInfo com = new WaCompanyInfo();
					com.setCompanyCode(ExcelUtil.getCellValue(row.getCell(0)));
					WaCompanyInfo resultCom = waCompanyInfoDao.getEntityByCode(com);
					//根据code获取id后存入mysql
					waMonthWaterEntity.setCompanyId(String.valueOf(resultCom.getCompanyId()));
					waMonthWaterEntity.setMonthDate(ExcelUtil.getCellValue(row.getCell(2)));
					waMonthWaterEntity.setPlanMonthWater(ExcelUtil.getCellValue(row.getCell(3)));
					waMonthWaterEntity.setIsDelte(0);
				}
			}
			return waMonthWaterEntity;
		}catch (Exception e){
			return null;
		}
	}

}
