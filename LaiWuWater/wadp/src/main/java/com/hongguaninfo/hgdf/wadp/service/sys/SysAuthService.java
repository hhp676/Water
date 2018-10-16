package com.hongguaninfo.hgdf.wadp.service.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.wadp.core.Constants;
import com.hongguaninfo.hgdf.wadp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysAuthDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysRole;

/**
 * 系统权限表:SYS_AUTH biz 层
 * 
 * @author:
 */

@Service("sysAuthBiz")
public class SysAuthService {

    @Autowired
    private SysAuthDao sysAuthDao;

    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    @Autowired
    private SysRoleAuthJoinService sysRoleAuthJoinService;

    public List<SysAuth> getSysAuthList(SysAuth vo, Map<String, Object> map)
            throws BizException {

        vo.setIsDelete(0);
        vo.setOrderStr("SA.AUTH_TYPE_");
        List<SysAuth> list = sysAuthDao.getList(vo);

        if (vo.getAuthName() != null || vo.getAuthCode() != null) {
            List<SysAuth> parentList = new ArrayList<SysAuth>();
            List<SysAuth> resultList = sysAuthDao
                    .getAuthByAuthNameAndAuthCode(vo);

            if (resultList.size() > 0) {
                List<SysAuth> receiveList = null;
                for (SysAuth auth : resultList) {
                    receiveList = getParentList(auth.getFid().intValue(), list,
                            parentList);
                }
                for (SysAuth auth : receiveList) {
                    resultList.add(auth);
                }
                Map<Integer, SysAuth> mapTemp = new HashMap<Integer, SysAuth>();
                List<SysAuth> lastList = new ArrayList<SysAuth>();
                for (SysAuth auth : resultList) {
                    mapTemp.put(auth.getAuthId().intValue(), auth);
                }
                Set keys = mapTemp.keySet();
                if (keys != null) {
                    Iterator iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        Object key = iterator.next();
                        Object value = mapTemp.get(key);
                        lastList.add((SysAuth) value);
                    }

                }
                formatList(lastList);
                map.put("rows", lastList);
                return lastList;
            } else {
                map.put("rows", resultList);
                return resultList;
            }
        }
        formatList(list);
        map.put("rows", list);
        map.put("totalinfo", sysAuthDao.getListCount(vo));
        return list;
    }

    /**
     * @description 页面初始化数据和高级查询数据的公共方法
     * @author zhaj
     * @throws BizException
     * @date 2014-6-13 11:55
     * */
    public void formatList(List<SysAuth> list) throws BizException {
        for (SysAuth bo : list) {
            if (bo.getAuthType() == 1) { // 模块权限
                bo.set_parentId(bo.getFid());
                bo.setIconCls("m-icon-tag-green");
            } else if (bo.getAuthType() == 2) { // 操作权限
                bo.set_parentId(bo.getFid());
                bo.setIconCls("m-icon-tag-purple");
            } else if (bo.getAuthType() == 0) { // 应用权限
                bo.setIconCls("m-icon-tag-red");
            }
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
            bo.setAuthTypeStr(sysDatadicItemBiz.getItemNameByValue("AUTH_TYPE",
                    bo.getAuthType() + ""));
        }
    }

    /**
     * @description 根据传入的父id递归查询出与此条数据相关的所有上级数据
     * @author zhaj
     * @date 2014-6-10 13:02
     * */
    public List<SysAuth> getParentList(int authFid, List<SysAuth> listAll,
            List<SysAuth> parentList) {
        for (SysAuth auth : listAll) {
            if (auth.getAuthId().intValue() == authFid) {
                parentList.add(auth);
                if (auth.getFid().intValue() != 0) {
                    getParentList(auth.getFid().intValue(), listAll, parentList);
                }
            }
        }
        return parentList;
    }

    // 通过id级联删除
    public void deleteByAuthId(int authId) throws BizException {
        SysAuth delVo = sysAuthDao.getById(authId);
        delVo.setIsDelete(1);
        delVo.setUpdTime(new Date());
        delVo.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        delVo.setAuthCode(delVo.getAuthCode() + "_" + authId);
        sysAuthDao.update(delVo);

        // 级联删除
        SysAuth delChindVo = new SysAuth();
        delChindVo.setFid(new BigDecimal(authId));
        delChindVo.setIsDelete(1);
        delChindVo.setUpdTime(new Date());
        delChindVo.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        delChindVo.setAuthCode("authCode");
        sysAuthDao.updateByFid(delChindVo);

    }

    // 获取单条数据
    public SysAuth getSysAuthById(int authId) throws BizException {
        if (authId == 0) {
            return new SysAuth();
        }
        return sysAuthDao.getById(authId);
    }

    // 新增权限
    public void insertApp(SysAuth sysAuth) throws BizException {
        // 校验------------------------------------------------------------------
        // 校验code-------------------------------------
        SysAuth checkVo = new SysAuth();
        checkVo.setAuthCode(sysAuth.getAuthCode());
        checkVo.setIsDelete(0);
        List<SysAuth> checkResultList = sysAuthDao.getList(checkVo);
        if (checkResultList != null && checkResultList.size() > 0) {
            throw new BizException(JSON.toJSONString(new CheckFieldResult(
                    "authCode", "权限code已经存在")));
        }

        // 校验通过------------------------------------------------------------------

        sysAuth.setIsDelete(0);
        sysAuth.setIsFinal(0);
        sysAuth.setCrtTime(new Date());
        sysAuth.setUpdTime(new Date());
        sysAuth.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuth.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuth.setAuthId(new BigDecimal(dbIdGenerator.getNextId()));
        if (sysAuth.getAuthType() == 0) {
            sysAuth.setLevelId(0);
            sysAuth.setFid(new BigDecimal(0));
        } else {
            sysAuth.setFids(mrkFidsByFid(sysAuth.getFid().intValue()));
            sysAuth.setLevelId(getSysAuthById(sysAuth.getFid().intValue())
                    .getLevelId() + 1);
        }

        sysAuthDao.save(sysAuth);
        // 将权限授权给超级管理员
        sysRoleAuthJoinService.insertSysRoleAuthJoin(Constants.ADMIN_ID,
                sysAuth.getAuthId().intValue());
    }

    public void updateApp(SysAuth sysAuth) throws BizException {
        sysAuth.setUpdTime(new Date());
        sysAuth.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthDao.update(sysAuth);
    }

    public String mrkFidsByFid(int fid) throws BizException {
        SysAuth fBo = sysAuthDao.getById(fid);
        return fBo.getFids() + "/" + fid;
    }

}