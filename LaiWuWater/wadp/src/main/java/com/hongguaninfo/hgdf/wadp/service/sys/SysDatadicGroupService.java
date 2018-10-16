package com.hongguaninfo.hgdf.wadp.service.sys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysDatadicGroupDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysDatadicGroup;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysRole;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 系统变量表:SYS_VARIABLE biz 层
 * 
 * @author:
 */

@Service("sysDatadicGroupBiz")
public class SysDatadicGroupService {

    @Autowired
    private SysDatadicGroupDao sysDatadicGroupDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;

    public List<SysDatadicGroup> getSysDatadicGroupList(
            BasePage<SysDatadicGroup> basePage, SysDatadicGroup vo,
            Map<String, Object> map) throws BizException {
        vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<SysDatadicGroup> page = sysDatadicGroupDao.pageQuery(basePage);
        List<SysDatadicGroup> list = page.getResult();
        for (SysDatadicGroup bo : list) {
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
        }
        map.put("rows", list);
        map.put("totalinfo", page.getTotalCount());
        return list;
    }

    public void insertDatadicGroup(SysDatadicGroup sysDatadicGroup)
            throws BizException {
        // 校验------------------------------------------------------------------
        // 校验code-------------------------------------
        SysDatadicGroup checkVo = new SysDatadicGroup();
        checkVo.setGroupCode(sysDatadicGroup.getGroupCode());
        checkVo.setIsDelete(0);
        List<SysDatadicGroup> checkResultList = sysDatadicGroupDao
                .getList(checkVo);
        if (checkResultList != null && checkResultList.size() > 0) {
            throw new BizException(JSON.toJSONString(new CheckFieldResult(
                    "groupCode", "字典组code已经存在")));
        }

        // 校验通过------------------------------------------------------------------
        sysDatadicGroup.setGroupId(dbIdGenerator.getNextId().intValue());
        sysDatadicGroup.setIsFinal(0);
        sysDatadicGroup.setIsDelete(0);
        sysDatadicGroup.setCrtTime(new Date());
        sysDatadicGroup.setUpdTime(new Date());
        sysDatadicGroup.setCrtUserid(SessionUtils.getUser().getUserId());
        sysDatadicGroup.setUpdUserid(SessionUtils.getUser().getUserId());
        sysDatadicGroupDao.save(sysDatadicGroup);

    }

    public void updateDatadicGroup(SysDatadicGroup sysDatadicGroup)
            throws BizException {
        sysDatadicGroup.setUpdTime(new Date());
        sysDatadicGroup.setUpdUserid(SessionUtils.getUser().getUserId());
        sysDatadicGroupDao.update(sysDatadicGroup);

    }

    public void deleteById(int id) throws BizException {
        SysDatadicGroup sysDatadicGroup = sysDatadicGroupDao.getById(id);
        sysDatadicGroup.setUpdTime(new Date());
        sysDatadicGroup.setUpdUserid(SessionUtils.getUser().getUserId());
        sysDatadicGroup.setIsDelete(new Integer(1));
        sysDatadicGroup.setGroupCode(sysDatadicGroup.getGroupCode() + "_" + id);
        sysDatadicGroupDao.update(sysDatadicGroup);

    }

    public SysDatadicGroup getDatadicGroupById(int id) {
        return sysDatadicGroupDao.getById(id);
    }

}