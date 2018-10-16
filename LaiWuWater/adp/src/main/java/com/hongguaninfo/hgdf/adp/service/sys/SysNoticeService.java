package com.hongguaninfo.hgdf.adp.service.sys;

import java.math.BigDecimal;
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
import com.hongguaninfo.hgdf.adp.dao.sys.SysNoticeDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysNotice;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.EncodeUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 系统公告表:SYS_NOTICE biz 层
 * 
 * @author:
 */

@Service("sysNoticeService")
public class SysNoticeService {

    @Autowired
    private SysNoticeDao sysNoticeDao;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;

    // 查询列表数据
    public List<SysNotice> getSysNoticeList(BasePage<SysNotice> basePage,
    		SysNotice vo, Map<String, Object> map) throws BizException {
        vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<SysNotice> page = sysNoticeDao.pageQuery(basePage);
        List<SysNotice> list = page.getResult();
        for (SysNotice bo : list) {
        	bo.setStartTimeStr(DateUtil.convertDateToString(bo.getStartTime()));
        	bo.setEndTimeStr(DateUtil.convertDateToString(bo.getEndTime()));
        	bo.setStatusStr(sysDatadicItemBiz.getItemNameByValue("PUB_STATUS", bo.getStatus() + ""));
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
            bo.setAutoPubStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
            		bo.getAutoPub() + ""));
        }
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
    }

    // 新增系统公告
    public void insertNotice(SysNotice sysNotice) throws BizException {
    	convertProp(sysNotice);
    	sysNotice.setNotifyId(dbIdGenerator.getNextId().intValue());
    	sysNotice.setIsDelete(0);
    	sysNotice.setIsFinal(0);
    	sysNotice.setStatus(0);//未发布
    	sysNotice.setCrtTime(new Date());
    	sysNotice.setCrtUserid(SessionUtils.getUserId());
    	sysNotice.setUpdTime(new Date());
    	sysNotice.setUpdUserid(SessionUtils.getUserId());
    	sysNoticeDao.save(sysNotice);
    }

    // 修改系统公告
    public void updateNotice(SysNotice sysNotice) throws BizException {
    	convertProp(sysNotice);
    	sysNotice.setUpdTime(new Date());
    	sysNotice.setUpdUserid(SessionUtils.getUserId());
    	sysNoticeDao.update(sysNotice);

    }

    // 通过id删除
    public void deleteById(Integer id) throws BizException {
    	SysNotice sysNotice = new SysNotice();
    	sysNotice.setUpdTime(new Date());
    	sysNotice.setUpdUserid(SessionUtils.getUserId());
    	sysNotice.setIsDelete(1);
    	sysNotice.setNotifyId(id);
    	sysNoticeDao.update(sysNotice);

    }
    
    // 发布或取消
    public void doPublishOrNot(Integer id) throws BizException {
    	SysNotice bo = sysNoticeDao.getById(id);
    	if (bo.getStatus().intValue() == 0) {
    		bo.setStatus(1);
    	} else if (bo.getStatus().intValue() == 1) {
    		bo.setStatus(0);
    	}
    	bo.setUpdTime(new Date());
    	bo.setUpdUserid(SessionUtils.getUserId());
    	sysNoticeDao.update(bo);

    }

    // 获取单条系统公告
    public SysNotice getSysNoticeById(int id) throws BizException {
        if (id == 0) {
            return null;
        }
        SysNotice bo = sysNoticeDao.getById(id);
        bo.setStartTimeStr(DateUtil.convertDateToString(bo.getStartTime()));
        bo.setEndTimeStr(DateUtil.convertDateToString(bo.getEndTime()));
        return sysNoticeDao.getById(id);
    }
    
    
    //获取首页公告
    public List<SysNotice> getIndexNoticeList() throws BizException {
    	SysNotice queryVo= new SysNotice();
    	queryVo.setQueryDate(DateUtil.convertDateToString(new Date()));
    	queryVo.setIsDelete(0);
    	queryVo.setStatus(1);
    	List<SysNotice> list = sysNoticeDao.getList(queryVo);
    	return list;
    }
    
    private void convertProp(SysNotice vo) {
    	if (!StringUtils.isEmpty(vo.getStartTimeStr())) {
    		vo.setStartTime(DateUtil.convertStringToDate(vo.getStartTimeStr()));
    	}
    	if (!StringUtils.isEmpty(vo.getEndTimeStr())) {
    		vo.setEndTime(DateUtil.convertStringToDate(vo.getEndTimeStr()));
    	}
    	System.out.println(vo.getContent());
    	vo.setContent( EncodeUtil.unescapeHtml4(vo.getContent()));
    	System.out.println(vo.getContent());
    }

}