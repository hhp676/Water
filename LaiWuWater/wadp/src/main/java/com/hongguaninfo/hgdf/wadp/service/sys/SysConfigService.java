package com.hongguaninfo.hgdf.wadp.service.sys;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.hongguaninfo.hgdf.wadp.core.Constants;
import com.hongguaninfo.hgdf.wadp.core.utils.generator.IdBlock;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysConfigDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysConfig;

/**
 * 系统设置表:SYS_CONFIG biz 层
 * 
 * @author:
 */

@Service("sysConfigBiz")
public class SysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;

    public List<SysConfig> getSysCfgList(SysConfig vo) {
        vo.setIsVisible(1);
        List<SysConfig> list = sysConfigDao.getList(vo);
        for (SysConfig config : list) {
            config.setConfigKey(executeString(config.getConfigKey()));
        }
        return list;
    }

    public String executeString(String str) {
        str = "(" + str + ")";
        return str;
    }

    public void resetSysCfgValue() {
        sysConfigDao.resetSysConfig();
    }

    public void doBatchUpdateSysCfg(String jsonListStr) {
        List<SysConfig> list = JSONArray.parseArray(jsonListStr,
                SysConfig.class);
        for (SysConfig vo : list) {
            String configKey = vo.getConfigKey().substring(Constants.FIVE,
                    vo.getConfigKey().length() - Constants.FIVE);
            vo.setConfigKey(configKey);
            vo.setUpdTime(new Date());
            sysConfigDao.update(vo);

        }
    }

    /**
     * @Title: execute
     * @Description: 获取数据库中的增长ID端
     * @param @param commandContext
     * @since 1.0.0
     */
    public IdBlock doGetDbIds(int idBlockSize) {
        SysConfig sysConfig = sysConfigDao
                .getSysConfigByKey(Constants.NEXT_DB_ID);
        long oldValue = Long.parseLong(sysConfig.getConfigValue());
        long newValue = oldValue + idBlockSize;

        sysConfig.setConfigValue(Long.toString(newValue));
        sysConfig.setUpdTime(new Date());
        sysConfig.setUpdUserid(new BigDecimal(Constants.ADMIN_ID));
        sysConfigDao.update(sysConfig);
        return new IdBlock(oldValue, newValue - 1);
    }
    
    /**
     * 根据key获取SysConfig
     * @param configKey
     * @return
     */
    public SysConfig getSysConfigByKey(String configKey){
        if(StringUtils.isEmpty(configKey)){
            return null;
        }
        return sysConfigDao.getSysConfigByKey(configKey);
    }

}