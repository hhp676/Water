package com.hongguaninfo.hgdf.workflow.entity;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;
import com.hongguaninfo.hgdf.core.utils.DateUtil;

/**
 * 通用process实体，解决activit的process相关entity json化失败
 * 
 * @author henry
 * 
 */
public class WfProcess extends BaseEntity implements ProcessDefinition, ProcessInstance, HistoricProcessInstance {

    protected String id;
    protected String category;
    protected String name;
    protected String key;
    protected String description;
    protected int version;
    protected String resourceName;
    protected String deploymentId;
    protected String diagramResourceName;
    protected boolean startFormKey;
    protected boolean suspended;
    protected String tenantId;
    
    protected boolean ended;
    protected String activityId;
    protected String processInstanceId;
    protected String parentId;
    protected String processDefinitionId;
    protected String businessKey;

    protected Date startTime;
    protected Date endTime;
    protected Long durationInMillis;
    protected String endActivityId;
    protected String startUserId;
    protected String startActivityId;
    protected String deleteReason;
    protected String superProcessInstanceId;
    
    protected String activitiName;
    protected boolean lastVersion;
    protected String startTimeStr;
    protected String endTimeStr;
    
    private String startStartTime;
    private String startEndTime;
    private String endStartTime;
    private String endEndTime;
    
    private boolean hasPermit;
    
    public WfProcess(){
        
    }
    
    public WfProcess(String id, String name, String key) {
        this.id = id;
        this.name = name;
        this.key = key;
    }

    public WfProcess(String id, String businessKey, String processInstanceId, String activityId) {
        this.id = id;
        this.businessKey = businessKey;
        this.processInstanceId = processInstanceId;
        this.activityId = activityId;
    }

    public WfProcess(String id, String businessKey, String startUserId, String startActivityId, String endActivityId) {
        this.startUserId = startUserId;
        this.businessKey = businessKey;
        this.startUserId = startUserId;
        this.startActivityId = startActivityId;
        this.endActivityId = endActivityId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getDiagramResourceName() {
        return diagramResourceName;
    }

    public void setDiagramResourceName(String diagramResourceName) {
        this.diagramResourceName = diagramResourceName;
    }

    public boolean hasStartFormKey() {
        return startFormKey;
    }

    public void setStartFormKey(boolean startFormKey) {
        this.startFormKey = startFormKey;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public boolean isStartFormKey() {
        return startFormKey;
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return null;
    }

    public boolean isLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(boolean lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getActivitiName() {
        return activitiName;
    }

    public void setActivitiName(String activitiName) {
        this.activitiName = activitiName;
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

    public String getEndActivityId() {
        return endActivityId;
    }

    public void setEndActivityId(String endActivityId) {
        this.endActivityId = endActivityId;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getStartActivityId() {
        return startActivityId;
    }

    public void setStartActivityId(String startActivityId) {
        this.startActivityId = startActivityId;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public String getSuperProcessInstanceId() {
        return superProcessInstanceId;
    }

    public void setSuperProcessInstanceId(String superProcessInstanceId) {
        this.superProcessInstanceId = superProcessInstanceId;
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

    public boolean isHasPermit() {
        return hasPermit;
    }

    public void setHasPermit(boolean hasPermit) {
        this.hasPermit = hasPermit;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
