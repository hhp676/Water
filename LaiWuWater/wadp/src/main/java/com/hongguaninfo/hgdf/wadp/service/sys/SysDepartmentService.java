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
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysDatadicItemDao;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysDepartmentDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysDatadicItem;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysDepartment;

/**
 * @author 查健
 * @Description Department Service层
 * @Date 2014-5-22
 */
@Service("sysDepartmentService")
public class SysDepartmentService {

    @Autowired
    private SysDepartmentDao sysDepartmentDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    @Autowired
    private SysDatadicItemDao sysDatadicItemDao;

    public List<SysDepartment> getDeptList(BasePage<SysDepartment> basePage,
            SysDepartment vo, Map<String, Object> map) throws BizException {

        SysDepartment sysDept = new SysDepartment();
        List<SysDepartment> list = sysDepartmentDao.getList(sysDept);

        if ((vo.getDepartName() != null && vo.getDepartName().length() > Constants.ZERO)
                || (vo.getDepartCode() != null && vo.getDepartCode().length() > Constants.ZERO)) {
            List<SysDepartment> parentList = new ArrayList<SysDepartment>();
            List<SysDepartment> resultList = sysDepartmentDao
                    .getDepartmentByDepartmentName(vo);

            if (resultList.size() > Constants.ZERO) {
                List<SysDepartment> receiveList = null;
                for (SysDepartment dept : resultList) {
                    receiveList = getParentList(dept.getFid().intValue(), list,
                            parentList);
                }
                for (SysDepartment dept : receiveList) {
                    if (dept.getDepartId().intValue() != Constants.ZERO) {
                        resultList.add(dept);
                    }
                }
                Map<Integer, SysDepartment> mapTemp = new HashMap<Integer, SysDepartment>();
                List<SysDepartment> lastList = new ArrayList<SysDepartment>();
                for (SysDepartment dept : resultList) {
                    mapTemp.put(dept.getDepartId().intValue(), dept);
                }
                Set keys = mapTemp.keySet();
                if (keys != null) {
                    Iterator iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        Object key = iterator.next();
                        Object value = mapTemp.get(key);
                        lastList.add((SysDepartment) value);
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
        return list;
    }

    /**
     * @description 页面初始化数据和高级查询数据的公共方法
     * @author zhaj
     * @throws BizException
     * @date 2014-6-13 9:56
     * */
    public void formatList(List<SysDepartment> list) throws BizException {
        for (SysDepartment dept : list) {
            if (dept.getDepartId().intValue() == Constants.ZERO) {
                list.remove(dept);
                break;
            }
            if (dept.getIsFinal() != null) {
                dept.setIsFinalStr(getItemNameByValue("LOGIC_TAG",
                        dept.getIsFinal() + ""));
            }
            if (dept.getFid() != null) {
                dept.set_parentId(dept.getFid());
                dept.setIconCls("m-icon-tag-dept");
            } else {
                dept.setIconCls("m-icon-tag-parentDept");
            }
        }
    }

    /**
     * @description 根据传入的父id递归查询出与此条数据相关的所有上级数据
     * @author zhaj
     * @date 2014-6-10 10:56
     * */
    public List<SysDepartment> getParentList(int deptFid,
            List<SysDepartment> listAll, List<SysDepartment> parentList) {
        for (SysDepartment d : listAll) {
            if (d.getDepartId().intValue() == deptFid) {
                parentList.add(d);
                if (d.getFid() != null) {
                    getParentList(d.getFid().intValue(), listAll, parentList);
                }

            }
        }
        return parentList;
    }

    /**
     * 插入之前判断code是否已经存在
     * */
    private void existCode(SysDepartment sysDepartment) throws BizException {
        SysDepartment dept = new SysDepartment();
        if (sysDepartment.getDepartCode().length() > Constants.ZERO) {
            dept.setDepartCode(sysDepartment.getDepartCode());
            List<SysDepartment> checkResultList = sysDepartmentDao
                    .getList(dept);
            if (checkResultList != null && checkResultList.size() > 0) {
                throw new BizException(JSON.toJSONString(new CheckFieldResult(
                        "departCode", "部门code已经存在")));
            }
        } else {
            throw new BizException(JSON.toJSONString(new CheckFieldResult(
                    "departCode", "不允许插入空值")));
        }

    }

    /**
     * 判断树形层级数量，大于5层则抛出异常信息
     * */
    private void checkFid(int fid) throws BizException {
        SysDepartment sysDept = new SysDepartment();
        List<SysDepartment> list = sysDepartmentDao.getList(sysDept);
        List<SysDepartment> parentList = new ArrayList<SysDepartment>();
        parentList = getParentList(fid, list, parentList);
        if (parentList.size() > Constants.SIX) {
            throw new BizException(JSON.toJSONString("层级数量过多"));
        }
    }

    public void insertDept(SysDepartment sysDepartment, int userId)
            throws BizException {
        // 判断code是否已经存在
        existCode(sysDepartment);

        // 判断树形层级数量，大于5层则抛出异常信息
        checkFid(sysDepartment.getFid().intValue());

        sysDepartment.setDepartId(new BigDecimal(dbIdGenerator.getNextId()));
        sysDepartment.setIsFinal(0);
        sysDepartment.setIsDelete(0);
        sysDepartment.setCrtTime(new Date());
        sysDepartment.setCrtUserid(new BigDecimal(userId));
        sysDepartment.setUpdTime(new Date());
        sysDepartment.setUpdUserid(new BigDecimal(userId));
        sysDepartmentDao.save(sysDepartment);
    }

    public void updateDept(SysDepartment sysDepartment, int userId)
            throws BizException {
        // 判断code是否已经存在
        existCode(sysDepartment);

        sysDepartment.setUpdTime(new Date());
        sysDepartment.setUpdUserid(new BigDecimal(userId));
        sysDepartmentDao.update(sysDepartment);

    }

    public void deleteDeptById(int id, int userId) throws BizException {
        SysDepartment sysDepartment = new SysDepartment();
        sysDepartment.setUpdTime(new Date());
        sysDepartment.setUpdUserid(new BigDecimal(userId));
        sysDepartment.setIsDelete(Constants.ONE);
        sysDepartment.setDepartId(new BigDecimal(id));
        sysDepartmentDao.update(sysDepartment);

    }

    public SysDepartment getSysDepartmentById(int id) {
        return sysDepartmentDao.getById(id);
    }

    public String getFnodeNameByFid(BigDecimal fid) {
        return sysDepartmentDao.getFnodeNameByFid(fid);
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

    // 通过字典项值获取字典项名称
    public String getItemNameByValue(String groupCode, String value)
            throws BizException {
        List<SysDatadicItem> list = getListByGroupCode(groupCode);
        for (SysDatadicItem bo : list) {
            if (value.equals(bo.getItemValue())) {
                return bo.getItemName();
            }
        }
        return "";
    }

}
