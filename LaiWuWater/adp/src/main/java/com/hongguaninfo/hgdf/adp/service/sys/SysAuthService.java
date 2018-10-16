package com.hongguaninfo.hgdf.adp.service.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysAuthDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserGroup;

/**
 * 系统权限表:SYS_AUTH biz 层
 * 
 * @author:
 */

@Service("sysAuthBiz")
public class SysAuthService {

	@Autowired
	private SysAuthDao sysAuthDao;

	@Autowired
	private SysDatadicItemService sysDatadicItemBiz;

	@Autowired
	private DbIdGenerator dbIdGenerator;

	@Autowired
	private SysRoleAuthJoinService sysRoleAuthJoinService;
	@Autowired
	private SysUserAuthJoinService sysUserAuthJoinService;
	@Autowired
    private SysUgroupAuthJoinService sysUgroupAuthJoinService;


	public List<SysAuth> getSysAuthList(SysAuth vo, Map<String, Object> map)
			throws BizException {
		vo.setOrderStr("SA.AUTH_TYPE_");
		List<SysAuth> list = sysAuthDao.getList(vo);
		SysAuth EmptyVo = new SysAuth();
		List<SysAuth> listAll = sysAuthDao.getList(EmptyVo);
		Map<Integer, SysAuth> resultMap = new HashMap<Integer, SysAuth>();
		for (SysAuth bo : list) {
			resultMap.put(bo.getAuthId().intValue(), bo);
			if (bo.getFid() != null) {
				recurGetParentAuth(bo.getFid().intValue(), listAll, resultMap);
			}
		}
		List<SysAuth> resultList = new ArrayList<SysAuth>();
		for (Entry<Integer, SysAuth> entry : resultMap.entrySet()) {
			resultList.add(entry.getValue());
		}

		formatList(resultList);
		 //对list进行排序-升序
        Collections.sort(resultList, Collections.reverseOrder(Collections.reverseOrder()));
		map.put("rows", resultList);
		map.put("total", sysAuthDao.getListCount(vo));
		return resultList;
	}

	/**
	 * @description 页面初始化数据和高级查询数据的公共方法
	 * @author zhaj
	 * @throws BizException
	 * @date 2014-6-13 11:55
	 * */
	public void formatList(List<SysAuth> list) throws BizException {
		for (SysAuth bo : list) {
			if (bo.getAuthType() == 1) { // 模块权限
				bo.set_parentId(bo.getFid());
				bo.setIconCls("m-icon-tag-green");
			} else if (bo.getAuthType() == 2) { // 操作权限
				bo.set_parentId(bo.getFid());
				bo.setIconCls("m-icon-tag-purple");
			} else if (bo.getAuthType() == 0) { // 应用权限
				bo.setIconCls("m-icon-tag-red");
			}
			bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
					bo.getIsFinal() + ""));
			bo.setAuthTypeStr(sysDatadicItemBiz.getItemNameByValue("AUTH_TYPE",
					bo.getAuthType() + ""));
		}
	}

	/**
	 * @description 根据传入的父id递归查询出与此条数据相关的所有上级数据
	 * @author zhaj
	 * @date 2014-6-10 13:02
	 * */
	public List<SysAuth> getParentList(int authFid, List<SysAuth> listAll,
			List<SysAuth> parentList) {
		for (SysAuth auth : listAll) {
			if (auth.getAuthId().intValue() == authFid) {
				parentList.add(auth);
				if (auth.getFid().intValue() != 0) {
					getParentList(auth.getFid().intValue(), listAll, parentList);
				}
			}
		}
		return parentList;
	}

	/**
	 * @author yuyanlin
	 * @description
	 * */
	public void recurGetParentAuth(int authFid, List<SysAuth> listAll,
			Map<Integer, SysAuth> map) {
		for (SysAuth auth : listAll) {
			if (auth.getAuthId().intValue() == authFid) {
				map.put(auth.getAuthId().intValue(), auth);
				if (auth.getFid() != null) {
					recurGetParentAuth(auth.getFid().intValue(), listAll, map);
				}

			}
		}
	}

	// 通过id级联删除
	public void deleteByAuthId(int authId) throws BizException {
//		if (checkResultList != null && checkResultList.size() > 0) {
//			throw new BizException(JSON.toJSONString(new CheckFieldResult(
//					"authCode", "权限code已经存在")));
//		}
		SysAuth delVo = sysAuthDao.getById(authId);
		delVo.setIsDelete(1);
		delVo.setUpdTime(new Date());
		delVo.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
		delVo.setAuthCode(delVo.getAuthCode() + "_" + authId);
		sysAuthDao.update(delVo);

		// 级联删除
		SysAuth delChindVo = new SysAuth();
		delChindVo.setIsDelete(0);
		delChindVo.setIsFinal(0);
		delChindVo.setFid(new BigDecimal(authId));
	    List<SysAuth> childList = sysAuthDao.getList(delChindVo);
        for (SysAuth bo : childList) {
        	deleteByAuthId(bo.getAuthId().intValue());
        }

        //删除 用户组-权限关联信息、删除角色-权限关联信息，删除权限-用户关联信息
        sysUgroupAuthJoinService.deleteByAuthId(authId);
        sysRoleAuthJoinService.deleteByAuthId(authId);
        sysUserAuthJoinService.deleteByAuthId(authId);

	}

	// 获取单条数据
	public SysAuth getSysAuthById(int authId) throws BizException {
		if (authId == 0) {
			return new SysAuth();
		}
		return sysAuthDao.getById(authId);
	}

	/**
     * 判断树形层级数量，大于5层则抛出异常信息
     * */
    private void checkFid(int fid) throws BizException {
    	SysAuth sysAuth = new SysAuth();
        List<SysAuth> list = sysAuthDao.getList(sysAuth);
        List<SysAuth> parentList = new ArrayList<SysAuth>();
        parentList = getParentList(fid, list, parentList);
        if (parentList.size() > Constants.SIX) {
            throw new BizException(JSON.toJSONString("层级数量过多"));
        }
    }
	// 新增权限
	public void insertApp(SysAuth sysAuth) throws BizException {
		// 校验------------------------------------------------------------------
		// 校验code-------------------------------------
		SysAuth checkVo = new SysAuth();
		checkVo.setAuthCode(sysAuth.getAuthCode());
		checkVo.setIsDelete(0);
		List<SysAuth> checkResultList = sysAuthDao.getList(checkVo);
		if (checkResultList != null && checkResultList.size() > 0) {
			throw new BizException(JSON.toJSONString(new CheckFieldResult(
					"authCode", "权限code已经存在")));
		}

		// 校验通过------------------------------------------------------------------
		// 判断树形层级数量，大于5层则抛出异常信息
		if(sysAuth.getFid()!=null && sysAuth.getFid().intValue()!=0){
			checkFid(sysAuth.getFid().intValue());
		}
        
        
		sysAuth.setIsDelete(0);
		sysAuth.setIsFinal(0);
		sysAuth.setCrtTime(new Date());
		sysAuth.setUpdTime(new Date());
		sysAuth.setCrtUserid(new BigDecimal(SessionUtils.getUserId()));
		sysAuth.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
		sysAuth.setAuthId(new BigDecimal(dbIdGenerator.getNextId()));
		if (sysAuth.getAuthType() == 0) {
			sysAuth.setOrderId(0);
			sysAuth.setLevelId(0);
			sysAuth.setFid(new BigDecimal(0));
		} else {
			sysAuth.setFids(mrkFidsByFid(sysAuth.getFid().intValue()));
			sysAuth.setLevelId(getSysAuthById(sysAuth.getFid().intValue())
					.getLevelId() + 1);
		}

		sysAuthDao.save(sysAuth);
		// 将权限授权给超级管理员
		sysRoleAuthJoinService.insertSysRoleAuthJoin(Constants.ADMIN_ID,
				sysAuth.getAuthId().intValue());
	}

	public void updateApp(SysAuth sysAuth) throws BizException {
		sysAuth.setUpdTime(new Date());
		sysAuth.setUpdUserid(new BigDecimal(SessionUtils.getUserId()));
		sysAuthDao.update(sysAuth);
	}

	public String mrkFidsByFid(int fid) throws BizException {
		SysAuth fBo = sysAuthDao.getById(fid);
		return fBo.getFids() + "/" + fid;
	}

}