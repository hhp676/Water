package com.hongguaninfo.hgdf.adp.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysDepartmentDao;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserRoleJoinDao;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserUgroupJoinDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysDepartment;
import com.hongguaninfo.hgdf.adp.entity.sys.SysRole;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserRoleJoin;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserUgroupJoin;
import com.hongguaninfo.hgdf.core.utils.CollectionsUtil;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;

/**
 * 组织架构信息 biz 层
 * 
 * @author yuyanlin
 */

@Service("oaCompanyStructureInfoService")
public class OaCompanyStructureInfoService extends BaseService {

	@Autowired
	private SysUserRoleJoinDao sysUserRoleJoinDao;

	@Autowired
	private SysDepartmentDao sysDepartmentDao;

	@Autowired
	private SysUserUgroupJoinDao sysUserUgroupJoinDao;

	@Autowired
	private SysUserRoleJoinService sysUserRoleJoinService;
	
    
    @Autowired
    private SysUserUgroupJoinService sysUserUgroupJoinService;

    
    @Autowired
    private SysDatadicItemService sysDatadicItemService;
    
	 
	 
	 
	/**
	 * 通过部门、员工姓名获取员工列表；
	 * @param departId 部门Id或域Id
	 * @param userName 人员姓名
	 * @author qun
	 */
	public List<SysUser> getUserByDepartIdAndUserName(Integer departId,String userName, boolean isContainLeaves) {
		String nativeSql = " AND (SUG.DEPART_ID_ = " + departId  + " OR SD.FID_ = " + departId+" )";
		if(StringUtil.isNotEmpty(StringUtil.trim(userName))){
			nativeSql += " AND (SU.USER_NAME_ LIKE '%" + StringUtil.trim(userName) + "%')";
		}
		if (!isContainLeaves) {
			nativeSql += " AND (OUE.STATUS_ = 1)";
		}
		LOG.debug("--------------------------------->" + nativeSql);
		List<SysUser> list = sysUserRoleJoinDao.getSpecUserList(nativeSql);
		for(SysUser user : list){
			if(user != null && user.getUserId() != null){
				user.setMainDepartNamesStr(getMainDepartNamesStr(user.getUserId()));
				user.setSecondDepartNamesStr(getSecondDepartNamesStr(user.getUserId()));
			}
		}
		return list;
	}
	
	/**
	 * 通过部门获取所有员工列表包括子部门
	 * 
	 * @author yuyanlin
	 */
	public List<SysUser> getAllUserByDepartId(Integer departId) {
		String nativeSql = " AND (SUG.DEPART_ID_ = " + departId + " OR SD.FID_ = " + departId+" )";
		LOG.debug("--------------------------------->" + nativeSql);
		List<SysUser> list = sysUserRoleJoinDao.getSpecUserList(nativeSql);
		return list;
	}
	
	public List<SysUser> getAllUserByDepartIds(String departIds) {
		String nativeSql = " AND (SUG.DEPART_ID_ in (" + departIds + ") OR SD.FID_ in (" + departIds+") )";
		LOG.debug("--------------------------------->" + nativeSql);
		List<SysUser> list = sysUserRoleJoinDao.getSpecUserList(nativeSql);
		return list;
	}
	public List<SysUser> getAllNeedDiaryUserByDepartIds(String departIds) {
		String nativeSql = "AND OUE.NEED_DIARY_=1 AND (SUG.DEPART_ID_ in (" + departIds + ") OR SD.FID_ in (" + departIds+"))";
		Date date = DateUtils.addMonths(new Date(), -12);
		String dateStr = com.hongguaninfo.hgdf.core.utils.DateUtil.convertDateToString(date);
		nativeSql += " AND (OUE.LEAVE_DATE_ is null OR OUE.LEAVE_DATE_ > '"+dateStr+"')";
		LOG.debug("--------------------------------->" + nativeSql);
		List<SysUser> list = sysUserRoleJoinDao.getSpecUserList(nativeSql);
		return list;
	}
	 

	/**
	 * 获取所有一级部门列表
	 * 
	 * @author yuyanlin
	 */
//	public List<SysDepartment> getMainDepart() {
//		SysDepartment queryVo = new SysDepartment();
//		queryVo.setIsDelete(0);
//		queryVo.setFid(new BigDecimal(OaConstants.DEPARTID_HG));
//		List<SysDepartment> list = sysDepartmentDao.getList(queryVo);
//		return list;
//
//	}
	
	/**
	 * 根据参数返回一级部门集合列表
	 * @param departIds 部门id集合
	 * @return
	 */
//    public List<SysDepartment> getMainDepart(Integer... departIds) {
//      List<SysDepartment> list = new ArrayList<>();  
//      List<SysDepartment> listAll = getMainDepart();
//      for(SysDepartment sysDepartment:listAll){
//        if (com.hongguaninfo.hgdf.adp.core.utils.CollectionsUtil.contains(departIds,Integer.parseInt(sysDepartment.getDepartId().toString()))){
//          list.add(sysDepartment);
//        }
//      }
//      return list;
//    }
    
    /**
	 * 获取 用户所在部门列表
	 * 
	 * @author yuyanlin
	 */
	public List<SysDepartment> getDepartListByUserId(Integer userId) {
		String nativeSql = "  AND SU.USER_ID_ = " + userId;
		LOG.debug("--------------------------------->" + nativeSql);
		List<SysDepartment> list = sysUserUgroupJoinDao
				.getSpecDepartList(nativeSql);
		return list;
	}
	
	private void checkMainDepart(SysDepartment bo, List<SysDepartment> newList) {
		   if(bo.getFid()!=null){
			   SysDepartment fBo = sysDepartmentDao.getById(bo.getFid().intValue());
			   if (fBo != null) {
				   checkMainDepart(fBo, newList);
			   }
		   }
	}
	
    /**
	 * 获取 用户所在一级部门列表
	 * 
	 * @author yuyanlin
	 */
	public List<SysDepartment> getMainDepartByUserId(Integer userId) {
		List<SysDepartment> list = getDepartListByUserId(userId);
		List<SysDepartment> newList = new ArrayList();
		for (SysDepartment bo : list) {
			checkMainDepart(bo, newList);
		}
		return newList;
	}
	 

	/**
	 * 获取所有一级部门字符串
	 * 
	 * @author yuyanlin
	 */
//	public String getMainDepartStr() {
//		List<SysDepartment> list = getMainDepart();
//		return mrkDepartIdsStr(list);
//	}

	public String getMainDepartStr(int userId) {
		List<SysDepartment> list = getMainDepartByUserId(userId);
		return mrkDepartIdsStr(list);
	}

	public String getDepartIdsStr(int userId) {
		List<SysDepartment> list = getDepartListByUserId(userId);
		return mrkDepartIdsStr(list);
	}
	
	public String getDepartNamesStr(int userId) {
		List<SysDepartment> list = getDepartListByUserId(userId);
		return mrkDepartNamesStr(list);
	}
	
	/**
	 * 通过用户id获取二级部门字符串
	 */
	
	public String getSecondDepartIdsStr(int userId) {
		List<SysDepartment> list = getSecondaryDepartByUserId(userId);
		return mrkDepartIdsStr(list);
	}
	/**
	 * 获取 用户所在二级部门列表
	 * 
	 * @author yuyanlin
	 */
	public List<SysDepartment> getSecondaryDepartByUserId(Integer userId) {
		List<SysDepartment> list = getDepartListByUserId(userId);
		List<SysDepartment> newList = new ArrayList();
		boolean isMec= false;
		SysDepartment mecDepart = null;
		for (SysDepartment bo : list) {
			checkSecondaryDepart(bo, newList);
		}
		
		if (isMec) {
			newList.add(mecDepart);
		}
		return newList;
	}
	private void checkSecondaryDepart(SysDepartment bo, List<SysDepartment> newList) {
			int count = 0;
			for (SysDepartment depart : newList) {
				if (depart.getDepartId().intValue() == bo.getDepartId()
						.intValue()) {
					count++;
				}
			}
			if (count == 0) {
				newList.add(bo);
			}
	}
	/**
	 * 通过用户id获取一级部门名称字符串
	 */
	public String getMainDepartNamesStr(int userId) {
		List<SysDepartment> list = getMainDepartByUserId(userId);
		return mrkDepartNamesStr(list);
	}
	
	/**
	 * 通过用户id获取二级部门名称字符串
	 */
	public String getSecondDepartNamesStr(int userId) {
		List<SysDepartment> list = getSecondaryDepartByUserId(userId);
		return mrkDepartNamesStr(list);
	}
	

	/**
	 * 获取部门Id获取用户id字符串
	 * 
	 * @author yuyanlin
	 */
//	public String getUserIdStrByDepartId(Integer departId,boolean isContainLeaves) {
//		List<SysUser> list = getUserByDepartId(departId, isContainLeaves);
//		return mrkUserIdsStr(list);
//
//	}
	
	/**
	 * 获取部门Id获取所有用户id字符串包括子部门
	 * 
	 * @author yuyanlin
	 */
	public String getAllUserIdStrByDepartId(Integer departId) {
		List<SysUser> list = getAllUserByDepartId(departId);
		return mrkUserIdsStr(list);

	}

	/**
	 * 获取部门Ids获取所有用户id字符串包括子部门
	 * 
	 */
	public String getAllUserIdStrByDepartIds(String departIds) {
		List<SysUser> list = getAllUserByDepartIds(departIds);
		return mrkUserIdsStr(list);

	}
	
	/**
	 * 获取部门Ids获取所有需填日报的用户id字符串包括子部门
	 * 
	 * @author yuyanlin
	 */
	public String getAllNeedDiaryUserIdsStrByDepartIds(String departIds) {
		List<SysUser> list = getAllNeedDiaryUserByDepartIds(departIds);
		return mrkUserIdsStr(list);
	}

	/**
	 * 通过部门id获取域列表
	 * 
	 * @author yuyanlin
	 */
	public List<SysDepartment> getAreaByDepartId(int departId) {
		SysDepartment queryVo = new SysDepartment();
		queryVo.setIsDelete(0);
		queryVo.setFid(new BigDecimal(departId));
		List<SysDepartment> list = sysDepartmentDao.getList(queryVo);
		return list;
	}
	
	 
	
	
	
	
	
	// ---------------------------------------------------------------------------------------------------
	 
 

	public String mrkDepartIdsStr(List<SysDepartment> list) {
		Integer[] depIdAry = new Integer[list.size()];
		for (int i = 0; i < list.size(); i++) {
			depIdAry[i] = list.get(i).getDepartId().intValue();
		}
		return StringUtils.join(depIdAry, ",");
	}
	

	
	
	/**
	 *  返回部门名称串
	* @param list
	* @return
	 */
	public String mrkDepartNamesStr(List<SysDepartment> list) {
		String[] depIdAry = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			depIdAry[i] = list.get(i).getDepartName();
		}
		return StringUtils.join(depIdAry, ",");
	}
	
	public String mrkUserIdsStr(List<SysUser> list) {
		Integer[] userIdAry = new Integer[list.size()];
		for (int i = 0; i < list.size(); i++) {
			userIdAry[i] = list.get(i).getUserId().intValue();
		}
		return StringUtils.join(userIdAry, ",");
	}
	
	 
	
	public List<SysUser> getUniqueUserList(List<SysUser> list1,List<SysUser> list2) {
		for (SysUser user : list2) {
			if (!containUser(list1, user.getUserId().intValue())) {
				list1.add(user);
			}
		}
		return list1;
	}
	
	public boolean containUser(List<SysUser> list, int userId) {
		for (SysUser user : list) {
			if (user.getUserId().intValue() == userId) {
				return true;
			}
		}
		return false;
	}
	
	public String getUserDepartName(int userId) throws BizException {
		List<SysUserUgroupJoin> list = sysUserUgroupJoinService.getListByUserId(userId);
		if (list.size() == 0) {
			return "";
		}
		return list.get(0).getDepartName();
	}
	
	  
    /**
     * 部门id通过员工ID，获取域信息
     */
	public Object getAreaByUserDepartId(int departId, Integer userId) {
		String nativeSql = "  AND SU.USER_ID_ = " + userId +" AND SD.FID_ = "+departId;
		LOG.debug("--------------------------------->" + nativeSql);
		List<SysDepartment> list = sysUserUgroupJoinDao
				.getSpecDepartList(nativeSql);
		return list;
	}
	
}