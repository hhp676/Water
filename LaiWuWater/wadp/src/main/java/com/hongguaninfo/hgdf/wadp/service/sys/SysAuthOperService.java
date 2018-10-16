package com.hongguaninfo.hgdf.wadp.service.sys;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.core.base.BaseService;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysAuthOperDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysAuthOper;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 系统操作权限表:SYS_AUTH_OPER biz 层
 * 
 * @author:yuyanlin
 */

@Service("sysAuthOperBiz")
public class SysAuthOperService extends BaseService {

    @Autowired
    private SysAuthOperDao sysAuthOperDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;

    public List<SysAuthOper> getSysAuthOperList(BasePage<SysAuthOper> basePage,
            SysAuthOper vo, Map<String, Object> map) throws BizException {
        vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<SysAuthOper> page = sysAuthOperDao.pageQuery(basePage);
        List<SysAuthOper> list = page.getResult();
        for (SysAuthOper bo : list) {
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
        }
        map.put("rows", list);
        map.put("totalinfo", page.getTotalCount());
        return list;
    }

    // 新增
    public void insertAuthOper(SysAuthOper sysAuthOper) throws BizException {
        sysAuthOper.setOperId(new BigDecimal(dbIdGenerator.getNextId()));
        sysAuthOper.setIsDelete(0);
        sysAuthOper.setIsFinal(0);
        sysAuthOper.setCrtTime(new Date());
        sysAuthOper.setUpdTime(new Date());
        sysAuthOper.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthOper.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthOperDao.save(sysAuthOper);
    }

    // 修改权限操作
    public void updateAuthOper(SysAuthOper sysAuthOper) throws BizException {
        sysAuthOper.setUpdTime(new Date());
        sysAuthOper.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthOperDao.update(sysAuthOper);

    }

    // 通过id删除
    public void deleteById(int id) throws BizException {
        SysAuthOper vo = new SysAuthOper();
        vo.setIsDelete(1);
        vo.setOperId(new BigDecimal(id));
        vo.setUpdTime(new Date());
        vo.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthOperDao.update(vo);

    }

    // 获取单条
    public SysAuthOper getSysAuthOperById(int id) throws BizException {
        if (id == 0) {
            return null;
        }
        return sysAuthOperDao.getById(id);
    }

}