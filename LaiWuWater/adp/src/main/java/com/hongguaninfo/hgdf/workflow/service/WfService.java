package com.hongguaninfo.hgdf.workflow.service;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.workflow.cache.WfProcessDefinitionCache;
import com.hongguaninfo.hgdf.workflow.entity.WfProcess;
import com.hongguaninfo.hgdf.workflow.entity.WfTask;
import com.hongguaninfo.hgdf.workflow.utils.WfEntityCastUtils;

/**
 * Workflow service
 * 
 * @author henry
 */
@Service
public class WfService {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FormService formService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private SysUserDao sysUserDao;
    
    /**
     * 分页查询流程定义信息
     * 
     * @param basePage
     * @param map
     * @return
     * @throws BizException
     */
    /**
     * @param basePage
     * @param map
     * @return
     * @throws BizException
     */
    public List<WfProcess> getProcessDefinedList(BasePage<ProcessDefinition> basePage, WfProcess vo,
            Map<String, Object> map) throws BizException {
        Subject subject = SecurityUtils.getSubject();

        // 读取所有流程定义
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().active()
                .orderByProcessDefinitionVersion().desc();
        if (!StringUtil.isEmpty(vo.getKey())) {
            query = query.processDefinitionKeyLike(vo.getKey());
        }
        if (!StringUtil.isEmpty(vo.getName())) {
            query = query.processDefinitionNameLike(vo.getName());
        }
        if (vo.getVersion() > 0) {
            query = query.processDefinitionVersion(vo.getVersion());
        }
        List<ProcessDefinition> list = query.listPage(basePage.getPosStart(), basePage.getRows());
        List<WfProcess> retList = new ArrayList<WfProcess>();
        for (ProcessDefinition pd : list) {
            WfProcess wfp = new WfProcess(pd.getId(), pd.getName(), pd.getKey());
            wfp.setDescription(pd.getDescription());
            wfp.setVersion(pd.getVersion());
            wfp.setCategory(pd.getCategory());
            wfp.setResourceName(StringUtils.substring(pd.getResourceName(),
                    pd.getResourceName().lastIndexOf(File.separatorChar) + 1));
            wfp.setDeploymentId(pd.getDeploymentId());
            wfp.setDiagramResourceName(StringUtils.substring(pd.getDiagramResourceName(), pd.getDiagramResourceName()
                    .lastIndexOf(File.separatorChar) + 1));
            wfp.setStartFormKey(pd.hasStartFormKey());
            wfp.setSuspended(pd.isSuspended());
            wfp.setTenantId(pd.getTenantId());;

            // 判断是否是最后一个版本
            wfp.setLastVersion(false);
            if (pd.getVersion() == repositoryService.createProcessDefinitionQuery().processDefinitionKey(pd.getKey())
                    .latestVersion().singleResult().getVersion()) {
                wfp.setLastVersion(true);
            }

            //********************************权限方面的操作*************************
            if (pd.getKey().equals(Constants.PROCESS_KEY_DIARY)) {// 日报流程
                wfp.setHasPermit(subject.isPermitted(Auths.PROCESS_START_DIARY));
            }
            if (pd.getKey().equals(Constants.PROCESS_KEY_RECRUIT)) {// 招聘流程
                wfp.setHasPermit(subject.isPermitted(Auths.PROCESS_START_RRCRUIT));
            }
            //********************************权限方面的操作*************************
            
            retList.add(wfp);
        }
        map.put("rows", retList);
        map.put("total", query.count());
        return retList;
    }

    /**
     * 分页查询运行中流程实例信息
     * 
     * @param basePage
     * @param map
     * @return
     * @throws BizException
     */
    public List<WfProcess> getRunningProcessList(BasePage<ProcessDefinition> basePage, WfProcess vo,
            Map<String, Object> map) throws BizException {
        // 设置缓存需要的bean
        WfProcessDefinitionCache.setRepositoryService(repositoryService);

        // 读取所有运行中的流程
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().orderByProcessInstanceId().desc()
                .active();
        if (!StringUtil.isEmpty(vo.getProcessDefinitionId())) {
            query = query.processDefinitionId(vo.getProcessDefinitionId());
        }
        if (!StringUtil.isEmpty(vo.getProcessInstanceId())) {
            query = query.processInstanceId(vo.getProcessInstanceId());
        }
        if (!StringUtil.isEmpty(vo.getBusinessKey())) {
            query = query.processInstanceBusinessKey(vo.getBusinessKey());
        }
        List<ProcessInstance> list = query.listPage(basePage.getPosStart(), basePage.getRows());

        List<WfProcess> retList = new ArrayList<WfProcess>();
        for (ProcessInstance pi : list) {
            WfProcess wfp = new WfProcess(pi.getId(), pi.getBusinessKey(), pi.getProcessInstanceId(),
                    pi.getActivityId());
            wfp.setSuspended(pi.isSuspended());
            wfp.setEnded(pi.isEnded());
            wfp.setActivityId(pi.getActivityId());
            wfp.setProcessInstanceId(pi.getProcessInstanceId());
            wfp.setParentId(pi.getParentId());
            wfp.setProcessDefinitionId(pi.getProcessDefinitionId());
            wfp.setTenantId(pi.getTenantId());

            wfp.setName(WfProcessDefinitionCache.get(pi.getProcessDefinitionId()).getName());
            wfp.setActivitiName(WfProcessDefinitionCache.getActivityName(pi.getProcessDefinitionId(),
                    pi.getActivityId()));

            retList.add(wfp);
        }
        map.put("rows", retList);
        map.put("total", query.count());
        return retList;
    }

    /**
     * 分页查询完结的流程实例信息
     * 
     * @param basePage
     * @param map
     * @return
     * @throws BizException
     */
    public List<WfProcess> getFinishedProcessList(BasePage<ProcessDefinition> basePage, WfProcess vo,
            Map<String, Object> map) throws BizException {
        // 设置缓存需要的bean
        WfProcessDefinitionCache.setRepositoryService(repositoryService);

        SysUser user = SessionUtils.getUser();

        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery().finished()
                .startedBy(user.getLoginName()).orderByProcessInstanceEndTime().desc();
        query = formatProcessQuery(query, vo);
        List<HistoricProcessInstance> list = query.listPage(basePage.getPosStart(), basePage.getRows());

        List<WfProcess> retList = new ArrayList<WfProcess>();
        WfEntityCastUtils.cartIfHisToProcesses(list, retList);
        map.put("rows", retList);
        map.put("total", query.count());
        return retList;
    }

    /**
     * 分页查询参与的流程定义信息
     * 
     * @param basePage
     * @param map
     * @return
     * @throws BizException
     */
    public List<WfProcess> getJoinedProcessList(BasePage<ProcessDefinition> basePage, WfProcess vo,
            Map<String, Object> map) throws BizException {
        // 设置缓存需要的bean
        WfProcessDefinitionCache.setRepositoryService(repositoryService);

        SysUser user = SessionUtils.getUser();

        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
                .involvedUser(user.getLoginName()).orderByProcessInstanceStartTime().desc();
        query = formatProcessQuery(query, vo);
        List<HistoricProcessInstance> list = query.listPage(basePage.getPosStart(), basePage.getRows());

        List<WfProcess> retList = new ArrayList<WfProcess>();
        WfEntityCastUtils.cartIfHisToProcesses(list, retList);
        map.put("rows", retList);
        map.put("total", query.count());
        return retList;
    }

    /**
     * 分页查询待领任务
     * 
     * @param basePage
     * @param map
     * @return
     * @throws BizException
     */
    public List<WfTask> getToClaimTaskList(BasePage<WfTask> basePage, WfTask vo, Map<String, Object> map)
            throws BizException {
        SysUser user = SessionUtils.getUser();
        TaskQuery query = taskService.createTaskQuery().taskCandidateUser(user.getLoginName()).active().orderByTaskCreateTime()
                .desc();
        formatTaskQuery(query, vo);
        List<Task> list = query.listPage(basePage.getPosStart(), basePage.getRows());
        List<WfTask> retList = new ArrayList<WfTask>();
        WfEntityCastUtils.castIfToTasks(list, retList);
        
        for(WfTask wt : retList){//待认领人
            wt.setCandidateUsers(getTaskCandidateUsers(wt.getId()));
            
            //用户信息
            for(WfTask task : retList){
                try {
                    task.setInitiator(sysUserDao.getUserByLoginName(String.valueOf(taskService.getVariables(task.getId()).get("initiator"))).getUserName());
                } catch (Exception e) {
                    log.error("获取用户信息失败",e);
                }
            }
        }
        
        
        map.put("rows", retList);
        map.put("total", query.count());
        return retList;
    }

    /**
     * 分页查询待办任务
     * 
     * @param basePage
     * @param map
     * @return
     * @throws BizException
     */
    public List<WfTask> getToDoTaskList(BasePage<WfTask> basePage, WfTask vo, Map<String, Object> map)
            throws BizException {
        SysUser user = SessionUtils.getUser();
        TaskQuery query = taskService.createTaskQuery().taskAssignee(user.getLoginName()).active().orderByTaskCreateTime().desc();
        formatTaskQuery(query, vo);
        List<Task> list = query.listPage(basePage.getPosStart(), basePage.getRows());
        List<WfTask> retList = new ArrayList<WfTask>();
        WfEntityCastUtils.castIfToTasks(list, retList);
        
        //用户信息
        for(WfTask task : retList){
            try {
                task.setInitiator(sysUserDao.getUserByLoginName(String.valueOf(taskService.getVariables(task.getId()).get("initiator"))).getUserName());
                task.setAssignee(sysUserDao.getUserByLoginName(task.getAssignee()).getUserName());
            } catch (Exception e) {
                log.error("获取用户信息失败",e);
            }
        }
        
        map.put("rows", retList);
        map.put("total", query.count());
        return retList;
    }

    /**
     * 分页查询已办任务
     * 
     * @param basePage
     * @param map
     * @return
     * @throws BizException
     */
    public List<WfTask> getDoneTaskList(BasePage<WfTask> basePage, WfTask vo, Map<String, Object> map)
            throws BizException {
        SysUser user = SessionUtils.getUser();
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(user.getLoginName()).finished().orderByTaskDueDate().desc();
        List<HistoricTaskInstance> tasks = query.listPage(basePage.getPosStart(), basePage.getRows());
        List<WfTask> retList = new ArrayList<WfTask>();
        WfEntityCastUtils.castIfHisToTasks(tasks, retList);
        //用户信息
        for(WfTask task : retList){
            try {
                task.setAssignee(sysUserDao.getUserByLoginName(task.getAssignee()).getUserName());
            } catch (Exception e) {
                log.error("获取用户信息失败",e);
            }
        }
        map.put("rows", retList);
        map.put("total", query.count());
        return retList;
    }

    private HistoricProcessInstanceQuery formatProcessQuery(HistoricProcessInstanceQuery query, WfProcess vo) {
        if (!StringUtil.isEmpty(vo.getStartUserId())) {
            query = query.startedBy(vo.getStartUserId());
        }
        if (!StringUtil.isEmpty(vo.getProcessDefinitionId())) {
            query = query.processDefinitionId(vo.getProcessDefinitionId());
        }
        if (!StringUtil.isEmpty(vo.getStartStartTime())) {
            query = query.startedAfter(DateUtil.convertTimeStringToDate(vo.getStartStartTime()));
        }
        if (!StringUtil.isEmpty(vo.getStartEndTime())) {
            query = query.startedBefore(DateUtil.convertTimeStringToDate(vo.getStartEndTime()));
        }
        if (!StringUtil.isEmpty(vo.getEndStartTime())) {
            query = query.finishedAfter(DateUtil.convertTimeStringToDate(vo.getEndStartTime()));
        }
        if (!StringUtil.isEmpty(vo.getEndEndTime())) {
            query = query.finishedBefore(DateUtil.convertTimeStringToDate(vo.getEndEndTime()));
        }
        if (!StringUtil.isEmpty(vo.getBusinessKey())) {
            query = query.processInstanceBusinessKey(vo.getBusinessKey());
        }
        return query;
    }

    private TaskQuery formatTaskQuery(TaskQuery query, WfTask vo) {
        if (!StringUtil.isEmpty(vo.getId())) {
            query = query.taskId(vo.getId());
        }
        if (!StringUtil.isEmpty(vo.getTaskDefinitionKey())) {
            query = query.taskDefinitionKeyLike(vo.getTaskDefinitionKey());
        }
        if (!StringUtil.isEmpty(vo.getName())) {
            query = query.taskName(vo.getName());
        }
        if (!StringUtil.isEmpty(vo.getStartStartTime())) {
            query = query.taskCreatedAfter(DateUtil.convertTimeStringToDate(vo.getStartStartTime()));
        }
        if (!StringUtil.isEmpty(vo.getStartEndTime())) {
            query = query.taskCreatedBefore(DateUtil.convertTimeStringToDate(vo.getStartEndTime()));
        }
        if (!StringUtil.isEmpty(vo.getEndStartTime())) {
            query = query.dueAfter(DateUtil.convertTimeStringToDate(vo.getEndStartTime()));
        }
        if (!StringUtil.isEmpty(vo.getEndEndTime())) {
            query = query.dueBefore(DateUtil.convertTimeStringToDate(vo.getEndEndTime()));
        }
        if (!StringUtil.isEmpty(vo.getProcessInstanceId())) {
            query = query.processInstanceId(vo.getProcessInstanceId());
        }
        return query;
    }

    private HistoricTaskInstanceQuery formatHisTaskQuery(HistoricTaskInstanceQuery query, WfTask vo) {
        if (!StringUtil.isEmpty(vo.getId())) {
            query = query.taskId(vo.getId());
        }
        if (!StringUtil.isEmpty(vo.getTaskDefinitionKey())) {
            query = query.taskDefinitionKeyLike(vo.getTaskDefinitionKey());
        }
        if (!StringUtil.isEmpty(vo.getName())) {
            query = query.taskName(vo.getName());
        }
        if (!StringUtil.isEmpty(vo.getStartStartTime())) {
            query = query.taskCreatedAfter(DateUtil.convertTimeStringToDate(vo.getStartStartTime()));
        }
        if (!StringUtil.isEmpty(vo.getStartEndTime())) {
            query = query.taskCreatedBefore(DateUtil.convertTimeStringToDate(vo.getStartEndTime()));
        }
        if (!StringUtil.isEmpty(vo.getEndStartTime())) {
            query = query.taskCompletedAfter(DateUtil.convertTimeStringToDate(vo.getEndStartTime()));
        }
        if (!StringUtil.isEmpty(vo.getEndEndTime())) {
            query = query.taskCompletedBefore(DateUtil.convertTimeStringToDate(vo.getEndEndTime()));
        }
        if (!StringUtil.isEmpty(vo.getProcessInstanceId())) {
            query = query.processInstanceId(vo.getProcessInstanceId());
        }
        return query;
    }

    /**
     * 认领任务
     * 
     * @param OaRecruit
     * @throws BizException
     */
    public void doClaimTask(WfTask wfTask) throws BizException {
        SysUser user = SessionUtils.getUser();
        taskService.claim(wfTask.getTaskId(), String.valueOf(user.getLoginName()));
    }

    /**
     * 批量认领任务
     * 
     * @param OaRecruit
     * @throws BizException
     */
    public void doClaimTaskMuti(WfTask wfTask) throws BizException {
        SysUser user = SessionUtils.getUser();
        if(!StringUtil.isEmpty(wfTask.getTaskIds())){
            List<String> list = Arrays.asList(StringUtils.split(wfTask.getTaskIds(), ","));
            for(String taskId : list){
                taskService.claim(taskId, String.valueOf(user.getLoginName()));
            }
        }
    }

    /**
     * 统计任务（当前用户的待办、待领数量）
     * 
     * @param OaRecruit
     * @throws BizException
     */
    public void doCountTask(Map<String, Object> map) throws BizException {
        SysUser user = SessionUtils.getUser();
        Long toClaimCount = taskService.createTaskQuery().taskCandidateUser(user.getLoginName()).active().count();
        Long toDoCount = taskService.createTaskQuery().taskAssignee(user.getLoginName()).active().count();
        map.put("toClaimCount", toClaimCount);
        map.put("toDoCount", toDoCount);
    }

    /**
     * 获取历史任务信息
     * 
     * @param processInstanceId
     * @return
     */
    public List<WfTask> getHistoricTasks(String processInstanceId) {
        List<HistoricTaskInstance> historicTasks = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).orderByTaskId().desc().list();
        List<WfTask> taskList = new ArrayList<WfTask>();
        List<WfTask> retList = new ArrayList<WfTask>();
        WfEntityCastUtils.castIfHisToTasks(historicTasks, taskList);
        for (WfTask wt : taskList) {
            List<Comment> comments = taskService.getTaskComments(wt.getId());
            if (null != comments && comments.size() > 0) {
                wt.setComment(comments.get(0).getFullMessage());
            }
            
            if(wt.getAssignee() == null){ //获取待认领人
                wt.setOwner(getTaskCandidateUsers(wt.getId()));
            }else{                
                wt.setOwner(sysUserDao.getUserByLoginName(wt.getAssignee()).getUserName());
            }
            retList.add(wt);
        }
        return retList;
    }

    /**
     * 获取变量信息
     * 
     * @param processInstanceId
     * @return
     */
    public List<HistoricVariableInstance> getHistoricVariables(String processInstanceId) {
        List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(processInstanceId).list();
        return historicVariableInstances;
    }

    /**
     * 根据processInstanceId获取业务KEY
     * 
     * @param processInstanceId
     * @return
     */
    public String getBusinessKeyByPIID(String processInstanceId) {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().orderByProcessInstanceId().desc()
                .processInstanceId(processInstanceId);
        HistoricProcessInstanceQuery query2 = historyService.createHistoricProcessInstanceQuery()
                .orderByProcessInstanceId().desc().processInstanceId(processInstanceId);
        return (query.singleResult() != null) ? query.singleResult().getBusinessKey() : query2.singleResult()
                .getBusinessKey();
    }
    
    /**
     * 获取任务待认领人信息（字符串）
     * @param taskId
     * @return
     */
    public String getTaskCandidateUsers(String taskId){
        List<IdentityLink> ils = taskService.getIdentityLinksForTask(taskId);
        List<String> users = new ArrayList<String>();
        for(IdentityLink il : ils){
            users.add(sysUserDao.getUserByLoginName(il.getUserId()).getUserName());
        }
        return users.toString();
    }
    
    /**
     * 办理任务
     * @param taskId
     * @param userId
     * @param variables
     * @throws BizException 
     */
    public void completeTask(String taskId, String userId, Map<String, Object> variables) throws BizException{
        if(!chkTaskAssignee(taskId, userId)){
           throw new BizException("该任务不是你的！");
        }
        taskService.complete(taskId, variables);
    }
    
    /**
     * 检查任务是否是该用户的
     * @param taskId
     * @param userId
     * @return
     */
    private boolean chkTaskAssignee(String taskId, String userId){
        long count = taskService.createTaskQuery().taskAssignee(userId).taskId(taskId).active().count();
        if(count > 0){
            return true;
        }
        return false;
    }
    
    /**
     * 根据流程实例ID获取流程定义Id
     * @param processInstanceId
     * @return
     * @throws BizException 
     */
    public String getWfDefIdByPid(String processInstanceId) throws BizException{
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().orderByProcessInstanceId().desc()
                .processInstanceId(processInstanceId);
        ProcessInstance pi = query.singleResult();
        if(pi != null){
            return pi.getProcessDefinitionId();
        }
        HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(hpi != null){
            return hpi.getProcessDefinitionId();
        }
        throw new BizException("查询不到该流程实例！");
    }
}