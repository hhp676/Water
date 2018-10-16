package com.hongguaninfo.hgdf.adp.service.sys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysTokenDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysToken;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * : sys_token biz 层
 * 
 * @author henry
 */

@Service("sysTokenService")
public class SysTokenService {

    @Autowired
    private SysTokenDao sysTokenDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    /**
     * REMARK
     *
     * Show all content and can paging The following id to give priority to key
     * !
     */
    public List<SysToken> getSysTokenList(BasePage<SysToken> basePage, SysToken vo, Map<String, Object> map)
            throws BizException {
        basePage.setFilters(vo);
        Page<SysToken> page = sysTokenDao.pageQuery(basePage);
        List<SysToken> list = page.getResult();
        /**
         * for (SysToken bo : list) {
         * bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
         * bo.getIsFinal() + "")); }
         */
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
    }

    /**
     * REMARK
     *
     * Through the id inquires the out a data Date need their conversion !
     * UserName is the current user name !
     */
    public void addSysToken(SysToken sysToken) throws BizException {
        sysToken.setTokenId(dbIdGenerator.getNextId().intValue());
        sysToken.setCrtTime(new Date());
        sysTokenDao.save(sysToken);
    }

    /**
     * REMARK
     *
     * update data Date need their conversion !
     */
    public void updateSysToken(SysToken sysToken) throws BizException {
        sysTokenDao.update(sysToken);
    }

    /**
     * REMARK
     *
     * Through the id delete a data
     */
    public void deleteSysToken(int id) throws BizException {
        sysTokenDao.delete(id);
    }

    /**
     * REMARK
     *
     * Through the id delete a data logic
     */
    public void deleteSysTokenlogic(int id) throws BizException {
        SysToken sysToken = new SysToken();
        sysToken.setTokenId(id);
        sysTokenDao.update(sysToken);
    }

    /**
     * REMARK
     *
     * Inquires the individual data Date need their conversion !
     */
    public SysToken getSysTokenbyId(int id) throws BizException {
        if (id == 0) {
            return null;
        }
        return sysTokenDao.getById(id);
    }
    
}