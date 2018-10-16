package com.hongguaninfo.hgdf.adp.service.sys;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysAuthDao;
import com.hongguaninfo.hgdf.adp.dao.sys.SysAuthOperDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuthOper;
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
    private SysAuthDao sysAuthDao;
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
        map.put("total", page.getTotalCount());
        return list;
    }

    // 新增
    public void insertAuthOper(SysAuthOper sysAuthOper) throws BizException {
    	SysAuthOper bean = new SysAuthOper();
    	bean.setOperCode(sysAuthOper.getOperCode());
    	List list = sysAuthOperDao.getList(bean);
    	if(list !=null && list.size() >0){
    		throw new BizException(JSON.toJSONString(new CheckFieldResult(
                    "operCode", "操作code已经存在")));
    	}
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
    	SysAuthOper bean = new SysAuthOper();
    	bean.setOperCode(sysAuthOper.getOperCode());
    	List list = sysAuthOperDao.getList(bean);
    	if(list !=null && list.size() >0){
    		bean = (SysAuthOper) list.get(0);
    		if(bean.getOperId().intValue() != sysAuthOper.getOperId().intValue()){
    			throw new BizException(JSON.toJSONString(new CheckFieldResult(
    					"operCode", "操作code已经存在")));
    		}
    	}
        sysAuthOper.setUpdTime(new Date());
        sysAuthOper.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
        sysAuthOperDao.update(sysAuthOper);

    }

    // 通过id删除
    public void deleteById(int id) throws BizException {
    	SysAuth auth = new SysAuth();
    	auth.setOperId(BigDecimal.valueOf(id));
    	auth.setIsDelete(0);
    	List list = sysAuthDao.getList(auth);
    	if(list !=null && list.size() >0){
    		throw new BizException("操作类型正在使用,不能删除！");
    	}
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