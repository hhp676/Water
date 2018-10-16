package com.hongguaninfo.hgdf.adp.service.sys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysNotifyTemplateDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysNotifyTemplate;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 系统通知模板表:SYS_NOTIFY_TEMPLATE biz 层
 * 
 * @author:
 */

@Service("sysNotifyTemplateService")
public class SysNotifyTemplateService {

    @Autowired
    private SysNotifyTemplateDao sysNotifyTemplateDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;

    // 查询列表数据
    public List<SysNotifyTemplate> getSysNotifyTemplateList(BasePage<SysNotifyTemplate> basePage,
    		SysNotifyTemplate vo, Map<String, Object> map) throws BizException {
        vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<SysNotifyTemplate> page = sysNotifyTemplateDao.pageQuery(basePage);
        List<SysNotifyTemplate> list = page.getResult();
        for (SysNotifyTemplate bo : list) {       	
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
        }
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
    }

    // 新增通知模板
    public void insertNotifyTemplate(SysNotifyTemplate vo) throws BizException {
    	vo.setTempId(dbIdGenerator.getNextId().intValue());
    	vo.setIsDelete(0);
    	vo.setIsFinal(0);
    	vo.setCrtTime(new Date());
    	vo.setCrtUserid(SessionUtils.getUserId());
    	vo.setUpdTime(new Date());
    	vo.setUpdUserid(SessionUtils.getUserId());
    	sysNotifyTemplateDao.save(vo);
    }

    // 修改通知模板
    public void updateNotifyTemplate(SysNotifyTemplate vo) throws BizException {
    	vo.setUpdTime(new Date());
    	vo.setUpdUserid(SessionUtils.getUserId());
    	sysNotifyTemplateDao.update(vo);

    }

    // 通过id删除
    public void deleteById(Integer id) throws BizException {
    	SysNotifyTemplate vo = new SysNotifyTemplate();
    	vo.setUpdTime(new Date());
    	vo.setUpdUserid(SessionUtils.getUserId());
    	vo.setIsDelete(1);
    	vo.setTempId(id);
    	sysNotifyTemplateDao.update(vo);

    }
    
  

    // 获取单条通知模板
    public  SysNotifyTemplate getSysNotifyTemplateById(int id) throws BizException {
        if (id == 0) {
            return null;
        }
        SysNotifyTemplate bo = sysNotifyTemplateDao.getById(id);
        return bo;
    }
    
 

}