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
import com.hongguaninfo.hgdf.adp.dao.sys.SysNotifyDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysNotify;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 系统通知表:SYS_NOTIFY biz 层
 * 
 * @author:
 */

@Service("sysNotifyService")
public class SysNotifyService {

    @Autowired
    private SysNotifyDao sysNotifyDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;

    // 查询列表数据
    public List<SysNotify> getSysNotifyList(BasePage<SysNotify> basePage,
    		SysNotify vo, Map<String, Object> map) throws BizException {
        basePage.setFilters(vo);
        Page<SysNotify> page = sysNotifyDao.pageQuery(basePage);
        List<SysNotify> list = page.getResult();
        for (SysNotify bo : list) {       	
        	bo.setIsReadStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsRead() + ""));
        	bo.setReadTimeStr(DateUtil.convertDateTimeToString(bo.getReadTime()));
        }
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
    }

    // 新增通知
    public void insertNotify(SysNotify vo) throws BizException {
    	vo.setNotifyId(dbIdGenerator.getNextId().intValue());
    	vo.setCrtTime(new Date());
    	vo.setIsRead(0);
    	sysNotifyDao.save(vo);
    }

    // 修改通知模板
    public void updateNotify(SysNotify vo) throws BizException {
    	sysNotifyDao.update(vo);

    }

    public SysNotify getIndexUserNotify() {
    	SysNotify queryVo = new SysNotify(); 
    	queryVo.setUserId(SessionUtils.getUserId());
    	queryVo.setIsRead(0);
    	List<SysNotify> list = sysNotifyDao.getList(queryVo);
    	if (list.size() > 0) {
    		queryVo = list.get(0);
    		queryVo.setTitleSub(queryVo.getTitle());
    		if(queryVo.getTitle() != null && queryVo.getTitle().length()>15){
    			queryVo.setTitleSub(queryVo.getTitle().substring(0, 15)+"....");
    		}
    		queryVo.setContentSub(queryVo.getContent());
    		if(queryVo.getContent() != null && queryVo.getContent().length()>30){
    			queryVo.setContentSub(queryVo.getContent().substring(0, 30)+"....");
    		}
    		return queryVo;
    	} else {
    		return null;
    	}
    }
    
  

    // 获取单条通知
    public  SysNotify getSysNotifyById(int id) throws BizException {
        if (id == 0) {
            return null;
        }
        SysNotify bo = sysNotifyDao.getById(id);
        return bo;
    }
    
    /**
     * 统计我的通知
     * 
     * @throws BizException
     */
    public void doCountMyNotify(Map<String, Object> map) throws BizException {
        SysUser user = SessionUtils.getUser();
        SysNotify queryVo = new SysNotify(); 
        queryVo.setUserId(SessionUtils.getUserId());
        queryVo.setIsRead(0);
        int count = sysNotifyDao.getListCount(queryVo);
        map.put("count", count);
    } 

}