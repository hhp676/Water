package com.hongguaninfo.hgdf.adp.service.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;

/**
 * @author yyl
 */

@Service("sysChartService")
public class SysChartService {

    @Autowired
    private SysUserDao sysUserDao;
    


    public String getUserBithMonthCntListStr(int sex) throws BizException {
    	SysUser queryVo = new SysUser();
    	queryVo.setSex(sex);
    	List<Integer> list = new ArrayList<Integer>();
    	List<SysUser> boList = sysUserDao.getCntListForChart(queryVo);
    	Map map = new HashMap();
    	for (SysUser bo : boList) {
			map.put(bo.getMonth(), bo.getCount());
		}
    	for (int i=1; i<=12;i++) {
    		Integer count = (Integer) map.get(i);
    		if (count == null) {
    			count = 0;
    		}
    		list.add(count);
    	}
//    	return JSON.toJSONString(list);
    	return StringUtils.join(list, ",");
    }
    
    
    
}