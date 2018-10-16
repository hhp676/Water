package com.hongguaninfo.hgdf.adp.service.sys;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysAuthDao;
import com.hongguaninfo.hgdf.adp.dao.sys.SysAuthModuleDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuthModule;

/**
 * 系统模块权限表:SYS_AUTH_MODULE biz 层
 * 
 * @author:yuyanlin
 */

@Service("sysAuthModuleBiz")
public class SysAuthModuleService extends BaseService {

    @Autowired
    private SysAuthModuleDao sysAuthModuleDao;

    @Autowired
    private SysAuthDao sysAuthDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    // 新增权限模块
    public void insertAuthModule(SysAuthModule sysAuthModule)
            throws BizException {
        // 新增权限表------------------------------------
        SysAuth sysAuth = new SysAuth();
        // *后期修改*
        sysAuth.setAuthType(1);
        sysAuth.setAuthCode(sysAuthModule.getModuleCode());
        // 模块名称
        sysAuth.setAuthName(sysAuthModule.getModuleName());
        // 模块英文名称
        sysAuth.setAuthEnname(sysAuthModule.getModuleEnname());
        // 模块uri
        sysAuth.setAuthUri(sysAuthModule.getModuleUri());
        // 模块图标
        sysAuth.setAuthIcon(sysAuthModule.getModuleIcon());
        // 排序id
        sysAuth.setOrderId(sysAuthModule.getOrderId());
        // 描述
        sysAuth.setDescr(sysAuthModule.getDescr());
        sysAuth.setIsDelete(0);
        sysAuth.setIsFinal(sysAuthModule.getIsFinal());
        sysAuth.setCrtTime(new Date());
        sysAuth.setUpdTime(new Date());
        sysAuth.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuth.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuth.setAuthId(new BigDecimal(dbIdGenerator.getNextId()));
        sysAuthDao.save(sysAuth);
        BigDecimal authId = sysAuth.getAuthId();
        LOG.debug("------------------------------>新增authId：" + authId);

        // 新增权限模块表-------------------------------------
        // 权限id
        sysAuthModule.setAuthId(authId);
        sysAuthModule.setIsDelete(0);
        sysAuthModule.setCrtTime(new Date());
        sysAuthModule.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthModule.setUpdTime(new Date());
        sysAuthModule.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthModule.setModuleId(new BigDecimal(dbIdGenerator.getNextId()));
        sysAuthModuleDao.save(sysAuthModule);

    }

    // 修改权限模块
    public void updateAuthModule(SysAuthModule sysAuthModule)
            throws BizException {
        // 修改权限表
        SysAuth sysAuth = new SysAuth();
        sysAuth.setAuthId(sysAuthModule.getAuthId());
        sysAuth.setAuthCode(sysAuthModule.getModuleCode());
        // 模块名称
        sysAuth.setAuthName(sysAuthModule.getModuleName());
        // 模块英文名称
        sysAuth.setAuthEnname(sysAuthModule.getModuleEnname());
        // 模块uri
        sysAuth.setAuthUri(sysAuthModule.getModuleUri());
        // 模块图标
        sysAuth.setAuthIcon(sysAuthModule.getModuleIcon());
        // 排序id
        sysAuth.setOrderId(sysAuthModule.getOrderId());
        sysAuth.setIsFinal(sysAuthModule.getIsFinal());
        // 描述
        sysAuth.setDescr(sysAuthModule.getDescr());
        sysAuth.setUpdTime(new Date());
        sysAuth.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthDao.update(sysAuth);

        // 修改权限模块表
        sysAuthModule.setUpdTime(new Date());
        sysAuthModule.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthModuleDao.update(sysAuthModule);

    }

    // 通过权限id删除
    public void deleteByAuthId(int authId) throws BizException {
        SysAuthModule vo = new SysAuthModule();
        vo.setIsDelete(1);
        vo.setAuthId(new BigDecimal(authId));
        vo.setUpdTime(new Date());
        vo.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthModuleDao.update(vo);

    }

    // 通过权限id获取单条
    public SysAuthModule getSysAuthModuleByAuthId(int authId)
            throws BizException {
        if (authId == 0) {
            return null;
        }
        return sysAuthModuleDao.getById(authId);
    }
}