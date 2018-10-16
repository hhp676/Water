package com.hongguaninfo.hgdf.wadp.service.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.wadp.core.Constants;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.core.base.BaseService;
import com.hongguaninfo.hgdf.wadp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysUserGroupDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysDepartment;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUserGroup;
import com.hongguaninfo.hgdf.core.utils.page.Page;

@Service("sysUserGroupService")
public class SysUserGroupService extends BaseService {

    @Autowired
    private SysUserGroupDao sysUserGroupDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;

    @Autowired
    private SysUgroupAuthJoinService sysUgroupAuthJoinService;

    @Autowired
    private SysRoleUgroupJoinService sysRoleUgroupJoinService;

    /**
     * @Title: getSysUserGroupList
     * @Description: 查询列表
     * @param @param
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @since 1.0.0
     */
    public List<SysUserGroup> getSysUserGroupList(
            BasePage<SysUserGroup> basePage, SysUserGroup vo,
            Map<String, Object> map) throws BizException {

        SysUserGroup userGroup = new SysUserGroup();
        userGroup.setIsDelete(0);
        List<SysUserGroup> list = sysUserGroupDao.getList(userGroup);

        if ((vo.getGroupName() != null && vo.getGroupName().length() > Constants.ZERO)
                || (vo.getGroupCode() != null && vo.getGroupCode().length() > Constants.ZERO)) {
            List<SysUserGroup> parentList = new ArrayList<SysUserGroup>();
            List<SysUserGroup> resultList = sysUserGroupDao
                    .getUserGroupByUserNameOrCode(vo);

            if (resultList.size() > Constants.ZERO) {
                List<SysUserGroup> receiveList = null;
                for (SysUserGroup ug : resultList) {
                    if (ug.getFid() != null) {
                        receiveList = getParentList(ug.getFid().intValue(),
                                list, parentList);
                    } else {
                        for (SysUserGroup bo : resultList) {
                            bo.set_parentId(bo.getFid());
                            bo.setIconCls("acc_icon_group");
                            bo.setIsFinalStr(sysDatadicItemBiz
                                    .getItemNameByValue("LOGIC_TAG",
                                            bo.getIsFinal() + ""));
                        }
                        map.put("rows", resultList);
                        return resultList;
                    }

                }
                for (SysUserGroup ug : receiveList) {
                    resultList.add(ug);
                }
                Map<Integer, SysUserGroup> mapTemp = new HashMap<Integer, SysUserGroup>();
                List<SysUserGroup> lastList = new ArrayList<SysUserGroup>();
                for (SysUserGroup ug : resultList) {
                    mapTemp.put(ug.getGroupId().intValue(), ug);
                }
                Set keys = mapTemp.keySet();
                if (keys != null) {
                    Iterator iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        Object key = iterator.next();
                        Object value = mapTemp.get(key);
                        lastList.add((SysUserGroup) value);
                    }

                }
                for (SysUserGroup bo : lastList) {
                    bo.set_parentId(bo.getFid());
                    bo.setIconCls("acc_icon_group");
                    bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue(
                            "LOGIC_TAG", bo.getIsFinal() + ""));
                }
                map.put("rows", lastList);
                return lastList;
            } else {
                map.put("rows", resultList);
                return resultList;
            }

        }

        for (SysUserGroup bo : list) {
            bo.set_parentId(bo.getFid());
            bo.setIconCls("acc_icon_group");
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
        }
        map.put("rows", list);
        return list;
    }

    /**
     * @description 根据传入的父id递归查询出与此条数据相关的所有上级数据
     * @author zhaj
     * @date 2014-6-10 10:56
     * */
    public List<SysUserGroup> getParentList(int userGroupFid,
            List<SysUserGroup> listAll, List<SysUserGroup> parentList) {
        for (SysUserGroup userGroup : listAll) {
            if (userGroup.getGroupId().intValue() == userGroupFid) {
                parentList.add(userGroup);
                if (userGroup.getFid() != null) {
                    getParentList(userGroup.getFid().intValue(), listAll,
                            parentList);
                }

            }
        }
        return parentList;
    }

    // 获取单条用户组数据
    public SysUserGroup getUserGroupById(int groupId) throws BizException {
        if (groupId == 0) {
            return new SysUserGroup();
        }
        SysUserGroup bo = sysUserGroupDao.getById(groupId);
        if (bo.getFid() != null && bo.getFid().intValue() != 0) {
            bo.setfName(getUserGroupById(bo.getFid().intValue()).getGroupName());
        }

        bo.setAuthIds(sysUgroupAuthJoinService.getAuthIdsByGroupId(groupId));
        bo.setRoleIds(sysRoleUgroupJoinService.getRoleIdsByGroupId(groupId));
        return bo;
    }

    // 新增用户组
    public void insertUserGroup(SysUserGroup sysUserGroup) throws BizException {
        // 校验-------------------------------------------------------------------
        // 检验用户组code-------------
        SysUserGroup checkVo = new SysUserGroup();
        checkVo.setGroupCode(sysUserGroup.getGroupCode());
        checkVo.setIsDelete(0);
        List<SysUserGroup> checkResultList = sysUserGroupDao.getList(checkVo);
        if (checkResultList != null && checkResultList.size() > 0) {
            throw new BizException(JSON.toJSONString(new CheckFieldResult(
                    "groupCode", "用户组code已经存在")));
        }

        // 校验通过-----------------------------------------------------------------
        sysUserGroup.setGroupId(new BigDecimal(dbIdGenerator.getNextId()));
        sysUserGroup.setIsFinal(0);
        sysUserGroup.setIsDelete(0);
        if (sysUserGroup.getFid().intValue() == 0) {
            sysUserGroup.setFid(null);
        }
        sysUserGroup.setCrtTime(new Date());
        sysUserGroup.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysUserGroup.setUpdTime(new Date());
        sysUserGroup.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysUserGroupDao.save(sysUserGroup);

        // 保存权限关联
        sysUgroupAuthJoinService.insertBatchSysUgroupAuthJoin(sysUserGroup
                .getCheckedAuthIds(), sysUserGroup.getGroupId().intValue());
        sysRoleUgroupJoinService.insertBatchSysUgroupRoleJoin(sysUserGroup
                .getCheckedRoleIds(), sysUserGroup.getGroupId().intValue());

    }

    public void updateUserGroup(SysUserGroup sysUserGroup) throws BizException {
        sysUserGroup.setUpdTime(new Date());
        sysUserGroup.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysUserGroupDao.update(sysUserGroup);
        // 重新保存权限关联
        if (!StringUtils.equals(sysUserGroup.getAuthIds(),
                sysUserGroup.getCheckedAuthIds())) {
            LOG.debug("--------------------------------------->重新保存权限关联<------------------------------------");
            sysUgroupAuthJoinService.deleteByGroupId(sysUserGroup.getGroupId()
                    .intValue());
            sysUgroupAuthJoinService.insertBatchSysUgroupAuthJoin(sysUserGroup
                    .getCheckedAuthIds(), sysUserGroup.getGroupId().intValue());
        }
        if (!StringUtils.equals(sysUserGroup.getRoleIds(),
                sysUserGroup.getCheckedRoleIds())) {
            LOG.debug("--------------------------------------->重新保存角色关联<------------------------------------");
            sysRoleUgroupJoinService.deleteByGroupId(sysUserGroup.getGroupId()
                    .intValue());
            sysRoleUgroupJoinService.insertBatchSysUgroupRoleJoin(sysUserGroup
                    .getCheckedRoleIds(), sysUserGroup.getGroupId().intValue());
        }
    }

    // 通过id逻辑删除
    public void deleteById(int id) throws BizException {
        SysUserGroup delVo = sysUserGroupDao.getById(id);
        delVo.setUpdTime(new Date());
        delVo.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        delVo.setIsDelete(1);
        delVo.setGroupCode(delVo.getGroupCode() + "_" + delVo.getGroupId());
        sysUserGroupDao.update(delVo);
        // 级联删除
        SysUserGroup queryVo = new SysUserGroup();
        queryVo.setIsDelete(0);
        queryVo.setIsFinal(0);
        queryVo.setFid(new BigDecimal(id));
        List<SysUserGroup> childList = sysUserGroupDao.getList(queryVo);
        for (SysUserGroup bo : childList) {
            deleteById(bo.getGroupId().intValue());
        }

    }

}