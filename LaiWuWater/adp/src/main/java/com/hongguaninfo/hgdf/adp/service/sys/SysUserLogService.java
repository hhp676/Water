package com.hongguaninfo.hgdf.adp.service.sys;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserLogDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLoginLog;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.SmsUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;

@Service("sysUserLogService")
public class SysUserLogService extends BaseService {

    @Autowired
    private SysUserLogDao sysUserLogDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;
    
    @Autowired
    private SysUserService sysUserService;
    
    public List<SysUserLog> getSysUserLogList(BasePage<SysUserLog> basePage,
            SysUserLog vo, Map<String, Object> map) throws BizException {
        LOG.debug("--------------------------------->" + basePage.getRows());
        basePage.setFilters(vo);
        Page<SysUserLog> page = sysUserLogDao.pageQuery(basePage);
        List<SysUserLog> list = page.getResult();
        for (SysUserLog userLog : list) {
            if (userLog.getLogType() == Constants.ZERO) {
                userLog.setLogTypeStr(Constants.BUSINESS);
            } else {
                userLog.setLogTypeStr(Constants.OPERATION);
            }
            userLog.setCrtTimeStr(DateUtil.convertDateTimeToString(userLog
                    .getCrtTime()));
        }
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
    }

    public void saveSysUserLog(SysUserLog sysUserLog) {
        String idStr = dbIdGenerator.getNextId()
                + SmsUtil.getDyVfCode().substring(4);
        sysUserLog.setLogId(new BigDecimal(idStr));
        LOG.debug("---------------------------------logid:>"
                + sysUserLog.getLogId());
        sysUserLog.setUserId(SessionUtils.getUserId() == 0 ? null
                : SessionUtils.getUserId());
        sysUserLogDao.save(sysUserLog);
    }

    public SysUserLog getSysUserLogById(int id) {
        SysUserLog sysUserLog = sysUserLogDao.getById(id);
        if (sysUserLog.getLogType() == Constants.ZERO) {
            sysUserLog.setLogTypeStr(Constants.BUSINESS);
        } else {
            sysUserLog.setLogTypeStr(Constants.OPERATION);
        }
        sysUserLog.setCrtTimeStr(DateUtil.convertDateTimeToString(sysUserLog
                .getCrtTime()));
        return sysUserLog;
    }
    
}
