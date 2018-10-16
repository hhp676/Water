/*
 * File Name:WaCompanyInfoService.java
 * Package Name:com.hongguaninfo.hgdf.bud.service.wa
 * Date:2018年09月10日 下午5:14:26
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
 */
package com.hongguaninfo.hgdf.wa.service;


import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.service.sys.SysDatadicItemService;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.wa.dao.WaCompanyInfoDao;
import com.hongguaninfo.hgdf.wa.entity.WaCompanyInfo;
import com.hongguaninfo.hgdf.wa.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;


/**
 * : wa_company_info. <br />
 * service interface 层 <br />
 * Date: 2018年09月10日 下午5:14:26 <br />
 * @author  hhp
 * @since V1.0.0
 */
@Service("waCompanyInfoService")
public class WaCompanyInfoService {

	@Autowired
	private WaCompanyInfoDao waCompanyInfoDao;
	@Autowired
	private SysDatadicItemService sysDatadicItemBiz;
	@Autowired
	private DbIdGenerator dbIdGenerator;

	/**
	 * REMARK
	 * 分页查询
	 * Show all content and can paging
	 * The following id to give priority to key !
	 */
	public List<WaCompanyInfo> getWaCompanyInfoList (BasePage<WaCompanyInfo> basePage,
													 WaCompanyInfo vo, Map<String, Object> map) throws BizException {
		vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<WaCompanyInfo> page = waCompanyInfoDao.pageQuery(basePage);
        List<WaCompanyInfo> list = page.getResult();
        for (WaCompanyInfo bo : list) {
			bo.setUserType(sysDatadicItemBiz.getItemNameByValue("USER_TYPE",
					bo.getUserType() + ""));  //用户类别
			bo.setWaterType(sysDatadicItemBiz.getItemNameByValue("WATER_TYPE",
					bo.getWaterType()+ "")); //用水性质
			bo.setIsImport(sysDatadicItemBiz.getItemNameByValue("IS_IMPORT",
					bo.getIsImport()+ "")); //重点用户
		}
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
	public void addWaCompanyInfo (WaCompanyInfo waCompanyInfo) throws BizException{
		//查证单位名称 节点信息是否存在
		WaCompanyInfo infoCode = new WaCompanyInfo();
		infoCode.setCompanyCode(waCompanyInfo.getCompanyCode());
		if (waCompanyInfoDao.getEntityByCode(infoCode) != null){
			throw new BizException(JSON.toJSONString(new CheckFieldResult(
					"companyCode", "节水代码已经存在")));
		}

		WaCompanyInfo infoName = new WaCompanyInfo();
		infoName.setCompanyCode(waCompanyInfo.getCompanyName().trim());
		if(waCompanyInfoDao.getEntityByCode(infoName) != null){
			throw new BizException(JSON.toJSONString(new CheckFieldResult(
					"companyName", "单位名称已经存在")));
		}

		waCompanyInfo.setIsDelete(0);
        waCompanyInfo.setCrtTime(new Date());
        waCompanyInfo.setUpdTime(new Date());
		waCompanyInfoDao.save(waCompanyInfo);
	}

	public void addWithExcel (WaCompanyInfo waCompanyInfo) throws BizException{
		//查证单位名称 节点信息是否存在
		WaCompanyInfo infoCode = new WaCompanyInfo();
		infoCode.setCompanyCode(waCompanyInfo.getCompanyCode());
		WaCompanyInfo checkEntity = waCompanyInfoDao.getEntityByCode(infoCode);
		if (null != checkEntity){
			/*throw new BizException(JSON.toJSONString(new CheckFieldResult(
					"companyCode", "节水代码已经存在")));*/
			infoCode.setCompanyId(checkEntity.getCompanyId());
			updateWaCompanyInfo(infoCode);
			return;
		}

		waCompanyInfo.setIsDelete(0);
		waCompanyInfo.setCrtTime(new Date());
		waCompanyInfo.setUpdTime(new Date());
		waCompanyInfoDao.save(waCompanyInfo);
	}

	/**
	 * REMARK
	 * 修改
	 * update data
	 * Date need their conversion !
	 */
	public void updateWaCompanyInfo (WaCompanyInfo waCompanyInfo) throws BizException{
 		waCompanyInfo.setUpdTime(new Date());
		waCompanyInfoDao.update(waCompanyInfo);
	}

	/**
	 * REMARK
	 * 删除
	 * Through the id delete a data
	 */
	public void deleteWaCompanyInfo(int companyId) throws BizException{
		waCompanyInfoDao.delete(companyId);
	}
	
	/**
	 * REMARK
	 * 物理删除
	 * Through the id delete a data logic
	 */
	public void deleteWaCompanyInfoLogic(int companyId) throws BizException{
        WaCompanyInfo waCompanyInfo = new WaCompanyInfo();
        waCompanyInfo.setUpdTime(new Date());
        waCompanyInfo.setCompanyId(companyId);
        waCompanyInfo.setIsDelete(1);
        waCompanyInfoDao.update(waCompanyInfo);
	}	
	

	/**
	 * REMARK
	 * 查询实体信息
	 * Inquires the individual data
	 * Date need their conversion !
	 */
	public WaCompanyInfo getWaCompanyInfoById(int companyId) throws BizException{
		if (companyId == 0) {
            return null;
        }
        return waCompanyInfoDao.getById(companyId);
	}

	public List<WaCompanyInfo> getCompanyList(WaCompanyInfo waCompanyInfo) throws BizException{
		waCompanyInfo.setIsDelete(0);
		return waCompanyInfoDao.getList(waCompanyInfo);
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
			List<WaCompanyInfo> companyList = new ArrayList<WaCompanyInfo>();//创建list，用做中间变量，暂时存放user

			for (int rowNum = 1;rowNum <= sheet.getLastRowNum();rowNum++) {
				HSSFRow row = sheet.getRow(rowNum);
				WaCompanyInfo companyEntity = new WaCompanyInfo();
				if (row != null) {
					if(StringUtil.isEmpty(ExcelUtil.getCellValue(row.getCell(0)))){
						continue;
					}
					//用水类型
					String waterType = ExcelUtil.getCellValue(row.getCell(12));
					String waterTypeVal = "";
					switch (waterType){
						case "居民生活用水": waterTypeVal = "0"; break;
						case "教育用水": waterTypeVal = "1"; break;
						case "非生活用水": waterTypeVal = "2"; break;
						case  "特种用水": waterTypeVal = "3"; break;
						default: waterTypeVal = "0";
					}

					//用户类型
					String userType = ExcelUtil.getCellValue(row.getCell(5));
					String userTypeVal = "";
					switch (userType){
						case "小区用户": userTypeVal = "0";break;
						case "企业用户": userTypeVal = "1";break;
						case "单位用户": userTypeVal = "2";break;
						case "特种行业用户": userTypeVal = "3";break;
						default: userTypeVal = "0";
					}

					//重点用户
					String isimport = ExcelUtil.getCellValue(row.getCell(13));
					String isimportVal = "";
					switch (isimport){
						case "否": isimportVal = "0"; break;
						case "是": isimportVal = "1"; break;
					}

					companyEntity.setCompanyCode(ExcelUtil.getCellValue(row.getCell(0)));
					companyEntity.setCompanyName(ExcelUtil.getCellValue(row.getCell(1)));
					companyEntity.setContactNum(ExcelUtil.getCellValue(row.getCell(2)));
					companyEntity.setContactMan(ExcelUtil.getCellValue(row.getCell(3)));
					companyEntity.setDepartment(ExcelUtil.getCellValue(row.getCell(4)));
					companyEntity.setUserType(userTypeVal);
					companyEntity.setEmail(ExcelUtil.getCellValue(row.getCell(6)));
					companyEntity.setTelphone(ExcelUtil.getCellValue(row.getCell(7)));
					companyEntity.setCityArea(ExcelUtil.getCellValue(row.getCell(8)));
					companyEntity.setPostcode(ExcelUtil.getCellValue(row.getCell(9)));
					companyEntity.setPeopleCount(ExcelUtil.getCellValue(row.getCell(10)));
					companyEntity.setAcreage(ExcelUtil.getCellValue(row.getCell(11)));
					companyEntity.setWaterType(waterTypeVal);
					companyEntity.setIsImport(isimportVal);
					companyEntity.setDescr(ExcelUtil.getCellValue(row.getCell(14)));

					companyList.add(companyEntity);
				}
			}
			if(result){
				for(int i=0;i<companyList.size();i++){
					addWithExcel(companyList.get(i));
				}
			}
			return result;
		}catch (Exception e){
			return false;
		}
	}

	public Map<String, String> getComPanyMap() throws BizException {
		WaCompanyInfo wacompanyInfo = new WaCompanyInfo();
		List<WaCompanyInfo> companyList = getCompanyList(wacompanyInfo);
		Map<String, String> companyMap = new HashMap<>();
		for (WaCompanyInfo wa: companyList){
			companyMap.put(wa.getCompanyId().toString(), wa.getCompanyName());
		}
		return companyMap;
	}

}
