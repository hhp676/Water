package com.hongguaninfo.hgdf.workflow.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.workflow.service.WfTraceService;

/**
 * 流程管理控制器
 *
 * @author henry
 */
@Controller
@RequestMapping(value = "/activiti")
public class ActivitiController {

    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    WfTraceService wfTraceService;

    @Autowired
    ProcessEngineFactoryBean processEngine;

    /**
     * 读取资源，通过部署ID
     *
     * @param processDefinitionId
     *            流程定义
     * @param resourceType
     *            资源类型(xml|image)
     * @throws Exception
     */
    @RequestMapping(value = "/resource/read")
    public void loadByDeployment(@RequestParam("processDefinitionId") String processDefinitionId,
            @RequestParam("resourceType") String resourceType, HttpServletResponse response){
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();

        String resourceName = "";
        if (resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        try {
            while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            log.error("查询流程资源失败", e);
        }
    }

    /**
     * 读取资源，通过流程ID
     *
     * @param resourceType
     *            资源类型(xml|image)
     * @param processInstanceId
     *            流程实例ID
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/resource/process-instance")
    public void loadByProcessInstance(@RequestParam("type") String resourceType,
            @RequestParam("pid") String processInstanceId, HttpServletResponse response) {
        InputStream resourceAsStream = null;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();

        String resourceName = "";
        if (resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        try {
            while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            log.error("查询流程资源失败", e);
        }
    }

    /**
     * 输出跟踪流程信息
     *
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/process/trace")
    @ResponseBody
    public List<Map<String, Object>> traceProcess(@RequestParam("pid") String processInstanceId){
        List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();
        try {
            activityInfos = wfTraceService.traceProcess(processInstanceId);
        } catch (Exception e) {
            log.error("追踪流程失败", e);
        }
        return activityInfos;
    }

    /**
     * trace process
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/process/showTraceView/{processInstanceId}/{processDefinitionId}")
    public String showProcessTraceView(@PathVariable final String processInstanceId, @PathVariable final String processDefinitionId, HttpServletRequest request,
            HttpServletResponse response, final Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                model.addAttribute("processInstanceId", processInstanceId);
                model.addAttribute("processDefinitionId", processDefinitionId);
                str = "workflow/process_showTraceView";
            }
        };
        return templete.operateModel();
    }
}