package com.hongguaninfo.hgdf.workflow.utils;

import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import com.hongguaninfo.hgdf.workflow.cache.WfProcessDefinitionCache;
import com.hongguaninfo.hgdf.workflow.entity.WfProcess;
import com.hongguaninfo.hgdf.workflow.entity.WfTask;

/**
 * @author henry
 */
public class WfEntityCastUtils {
    
    /**
     * 接口iftasklist转成tasklist
     * 
     * @param ifTastList
     * @param taskList
     */
    public static void castIfToTasks(List<Task> ifTastList, List<WfTask> taskList) {
        for (Task pt : ifTastList) {
            WfTask wfPt = new WfTask(pt.getId(), pt.getName());
            wfPt.setDescription(pt.getDescription());
            wfPt.setPriority(pt.getPriority());
            wfPt.setOwner(pt.getOwner());
            wfPt.setAssignee(pt.getAssignee());
            wfPt.setDelegationState(pt.getDelegationState());
            wfPt.setProcessInstanceId(pt.getProcessInstanceId());
            wfPt.setExecutionId(pt.getExecutionId());
            wfPt.setProcessDefinitionId(pt.getProcessDefinitionId());
            wfPt.setCreateTime(pt.getCreateTime());
            wfPt.setTaskDefinitionKey(pt.getTaskDefinitionKey());
            wfPt.setDueDate(pt.getDueDate());
            wfPt.setParentTaskId(pt.getParentTaskId());
            wfPt.setSuspended(pt.isSuspended());
            wfPt.setTenantId(pt.getTenantId());
            wfPt.setCategory(pt.getCategory());
            taskList.add(wfPt);
        }
    }

    /**
     * 历史接口ifHistasklist转成tasklist
     * 
     * @param ifTastList
     * @param taskList
     */
    public static void castIfHisToTasks(List<HistoricTaskInstance> ifHisTastList, List<WfTask> taskList) {
        for (HistoricTaskInstance hpt : ifHisTastList) {
            WfTask wfPt = new WfTask(hpt.getId(), hpt.getName());
            wfPt.setPriority(hpt.getPriority());
            wfPt.setOwner(hpt.getOwner());
            wfPt.setAssignee(hpt.getAssignee());
            wfPt.setProcessInstanceId(hpt.getProcessInstanceId());
            wfPt.setExecutionId(hpt.getExecutionId());
            wfPt.setProcessDefinitionId(hpt.getProcessDefinitionId());
            wfPt.setTaskDefinitionKey(hpt.getTaskDefinitionKey());
            wfPt.setDueDate(hpt.getDueDate());
            wfPt.setParentTaskId(hpt.getParentTaskId());

            wfPt.setDeleteReason(hpt.getDeleteReason());
            wfPt.setStartTime(hpt.getStartTime());
            wfPt.setEndTime(hpt.getEndTime());
            wfPt.setDurationInMillis(hpt.getDurationInMillis());
            wfPt.setWorkTimeInMillis(hpt.getWorkTimeInMillis());
            wfPt.setClaimTime(hpt.getClaimTime());
            wfPt.setFormKey(hpt.getFormKey());
            
            wfPt.setTenantId(hpt.getTenantId());
            wfPt.setCategory(hpt.getCategory());
            
            taskList.add(wfPt);
        }
    }

    /**
     * 历史接口ifHisProcesslist转成processlist
     * 
     * @param ifTastList
     * @param taskList
     */
    public static void cartIfHisToProcesses(List<HistoricProcessInstance> ifHisProcessList, List<WfProcess> ProcessList) {
        for (HistoricProcessInstance hpi : ifHisProcessList) {
            WfProcess wfp = new WfProcess(hpi.getId(), hpi.getBusinessKey(), hpi.getStartUserId(),
                    hpi.getStartActivityId(), hpi.getEndActivityId());
            wfp.setProcessDefinitionId(hpi.getProcessDefinitionId());
            wfp.setStartTime(hpi.getStartTime());
            wfp.setEndTime(hpi.getEndTime());
            wfp.setDurationInMillis(hpi.getDurationInMillis());
            wfp.setEndActivityId(hpi.getEndActivityId());
            wfp.setStartUserId(hpi.getStartUserId());
            wfp.setStartActivityId(hpi.getStartActivityId());
            wfp.setDeleteReason(hpi.getDeleteReason());
            wfp.setSuperProcessInstanceId(hpi.getSuperProcessInstanceId());
            wfp.setTenantId(hpi.getTenantId());
            
            wfp.setName(WfProcessDefinitionCache.get(hpi.getProcessDefinitionId()).getName());
            wfp.setActivitiName(WfProcessDefinitionCache.getActivityName(hpi.getProcessDefinitionId(),
                    hpi.getEndActivityId()));

            ProcessList.add(wfp);
        }
    }
}
