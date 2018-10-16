package com.hongguaninfo.hgdf.workflow.entity;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;
import com.hongguaninfo.hgdf.core.utils.DateUtil;

/**
 * 通用process实体，解决activit的task相关entity json化失败
 * @author henry
 *
 */
public class WfTask extends BaseEntity implements Task, HistoricTaskInstance {

    protected String id;
    protected String name;
    protected String description;
    protected int priority = 50;
    protected String owner;
    protected String assignee;
    protected DelegationState delegationState;
    protected String processInstanceId;
    protected String executionId;
    protected String processDefinitionId;
    protected Date createTime;
    protected String taskDefinitionKey;
    protected Date dueDate;
    protected String parentTaskId;
    protected boolean suspended;
    protected String tenantId;
    protected String category;

    // Historic
    protected String deleteReason;
    protected Date startTime;
    protected Date endTime;
    protected Long durationInMillis;
    protected Long workTimeInMillis;
    protected Date claimTime;
    protected String formKey;

    protected String createTimeStr;
    protected String dueDateStr;
    protected String startTimeStr;
    protected String endTimeStr;
    protected String claimTimeStr;
    
    //业务使用
    private String taskId;
    private String taskKey;
    private String comment;

    private String startStartTime;
    private String startEndTime;
    private String endStartTime;
    private String endEndTime;
    
    private String CandidateUsers;
    
    private String taskIds;
    
    private String initiator;
    
    public WfTask(){
        
    }
    
    public WfTask(String id, String name){
        this.id=id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public DelegationState getDelegationState() {
        return delegationState;
    }

    public void setDelegationState(DelegationState delegationState) {
        this.delegationState = delegationState;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if (createTime != null) {
            this.createTimeStr = DateUtil.convertDateTimeToString(createTime);
        }
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        if (dueDate != null) {
            this.dueDateStr = DateUtil.convertDateTimeToString(dueDate);
        }        
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            this.startTimeStr = DateUtil.convertDateTimeToString(startTime);
        }  
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            this.endTimeStr = DateUtil.convertDateTimeToString(endTime);
        }  
    }

    public Long getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(Long durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    public Long getWorkTimeInMillis() {
        return workTimeInMillis;
    }

    public void setWorkTimeInMillis(Long workTimeInMillis) {
        this.workTimeInMillis = workTimeInMillis;
    }

    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
        if (claimTime != null) {
            this.claimTimeStr = DateUtil.convertDateTimeToString(claimTime);
        }          
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    @Override
    public void delegate(String userId) {
        // TODO 自动生成的方法存根

    }

    @Override
    public Map<String, Object> getTaskLocalVariables() {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        // TODO 自动生成的方法存根
        return null;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getDueDateStr() {
        return dueDateStr;
    }

    public void setDueDateStr(String dueDateStr) {
        this.dueDateStr = dueDateStr;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getClaimTimeStr() {
        return claimTimeStr;
    }

    public void setClaimTimeStr(String claimTimeStr) {
        this.claimTimeStr = claimTimeStr;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getStartStartTime() {
        return startStartTime;
    }

    public void setStartStartTime(String startStartTime) {
        this.startStartTime = startStartTime;
    }

    public String getStartEndTime() {
        return startEndTime;
    }

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }

    public String getEndStartTime() {
        return endStartTime;
    }

    public void setEndStartTime(String endStartTime) {
        this.endStartTime = endStartTime;
    }

    public String getEndEndTime() {
        return endEndTime;
    }

    public void setEndEndTime(String endEndTime) {
        this.endEndTime = endEndTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public Date getTime() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCandidateUsers() {
        return CandidateUsers;
    }

    public void setCandidateUsers(String candidateUsers) {
        CandidateUsers = candidateUsers;
    }

    public String getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(String taskIds) {
        this.taskIds = taskIds;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }
    
}
