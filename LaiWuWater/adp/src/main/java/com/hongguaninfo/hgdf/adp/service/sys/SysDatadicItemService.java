package com.hongguaninfo.hgdf.adp.service.sys;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysDatadicItemDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysDatadicItem;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 系统变量表:SYS_VARIABLE biz 层
 * 
 * @author:
 */

@Service("sysDatadicItemBiz")
public class SysDatadicItemService {

    @Autowired
    private SysDatadicItemDao sysDatadicItemDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    public List<SysDatadicItem> getSysDatadicList(
            BasePage<SysDatadicItem> basePage, SysDatadicItem vo,
            Map<String, Object> map) throws BizException {
        vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<SysDatadicItem> page = sysDatadicItemDao.pageQuery(basePage);
        List<SysDatadicItem> list = page.getResult();
        for (SysDatadicItem bo : list) {
            bo.setIsFinalStr(getItemNameByValue("LOGIC_TAG", bo.getIsFinal()
                    + ""));
        }
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
    }

    public void insertDatadicItem(SysDatadicItem sysDatadicItem)
            throws BizException {
    	List list = sysDatadicItemDao.getListByGroupIdItemCode(sysDatadicItem);
    	if(list !=null && list.size() >0){
    		throw new BizException(JSON.toJSONString(new CheckFieldResult(
                    "itemCode", "字典项code已经存在")));
    	}
        sysDatadicItem.setItemId(dbIdGenerator.getNextId().intValue());
        sysDatadicItem.setIsFinal(0);
        sysDatadicItem.setIsDelete(0);
        sysDatadicItem.setCrtTime(new Date());
        sysDatadicItem.setCrtUserid(SessionUtils.getUserId());
        sysDatadicItem.setUpdTime(new Date());
        sysDatadicItem.setUpdUserid(SessionUtils.getUserId());
        sysDatadicItemDao.save(sysDatadicItem);

    }

    public void updateDatadicItem(SysDatadicItem sysDatadicItem)
            throws BizException {
    	SysDatadicItem vo = new SysDatadicItem();
    	vo.setGroupId(sysDatadicItem.getGroupId());
    	vo.setItemCode(sysDatadicItem.getItemCode());
    	List list = sysDatadicItemDao.getListByGroupIdItemCode(vo);
    	if(list !=null && list.size() >0){
    		SysDatadicItem item =(SysDatadicItem) list.get(0);
    		if(!sysDatadicItem.getItemId().equals(item.getItemId())){
    			throw new BizException(JSON.toJSONString(new CheckFieldResult(
    					"itemCode", "字典项code已经存在")));
    		}
    	}
        sysDatadicItem.setUpdTime(new Date());
        sysDatadicItem.setUpdUserid(SessionUtils.getUser().getUserId());
        sysDatadicItemDao.update(sysDatadicItem);

    }

    public void deleteById(int id) throws BizException {
        SysDatadicItem sysDatadicItem = new SysDatadicItem();
        sysDatadicItem.setUpdTime(new Date());
        sysDatadicItem.setUpdUserid(SessionUtils.getUser().getUserId());
        sysDatadicItem.setIsDelete(1);
        sysDatadicItem.setItemId(id);
        sysDatadicItemDao.update(sysDatadicItem);

    }

    public SysDatadicItem getDatadicItemById(int id) {
        return sysDatadicItemDao.getById(id);
    }

    // 通过字典组code获取字典项列表
    public List<SysDatadicItem> getListByGroupCode(String groupCode)
            throws BizException {
        SysDatadicItem queryVo = new SysDatadicItem();
        queryVo.setIsDelete(0);
        queryVo.setGroupCode(groupCode);
        queryVo.setOrderStr("SDI.ORDER_ID_");
        return sysDatadicItemDao.getList(queryVo);
    }

    // 通过字典组code获取字典项列表
    public Map<String, String> getMapByGroupCode(String groupCode)
            throws BizException {
        List<SysDatadicItem> list = getListByGroupCode(groupCode);
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        for (SysDatadicItem bo : list) {
            map.put(bo.getItemName(), bo.getItemValue());
        }
        return map;
    }

    // 通过字典项值获取字典项名称
    public String getItemNameByValue(String groupCode, String value)
            throws BizException {
    	
    	if (StringUtils.isEmpty(value)) {
    		return "";
    	}
        List<SysDatadicItem> list = getListByGroupCode(groupCode);
        for (SysDatadicItem bo : list) {
            if (value.equals(bo.getItemValue())) {
                return bo.getItemName();
            }
        }
        return "";
    }

}