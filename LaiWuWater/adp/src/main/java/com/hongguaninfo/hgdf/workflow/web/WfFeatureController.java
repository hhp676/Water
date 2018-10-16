package com.hongguaninfo.hgdf.workflow.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongguaninfo.hgdf.adp.core.aop.log.UserLog;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.templete.HttpTemplete;
import com.hongguaninfo.hgdf.adp.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.workflow.cmd.CounterSignCmd;
import com.hongguaninfo.hgdf.workflow.cmd.DelegateTaskCmd;
import com.hongguaninfo.hgdf.workflow.cmd.RollbackTaskCmd;
import com.hongguaninfo.hgdf.workflow.cmd.WithdrawTaskCmd;
import com.hongguaninfo.hgdf.workflow.entity.WfProcess;
import com.hongguaninfo.hgdf.workflow.entity.WfTask;
import com.hongguaninfo.hgdf.workflow.service.WfService;

/**
 * 工作流 国内特色流程  Crontroller
 * 
 * @author henry
 */
@Controller
@RequestMapping(value = "/workflow/feature")
public class WfFeatureController {

    private Log log = LogFactory.getLog(getClass());
    
    @Autowired
    private ProcessEngine processEngine;
    
    /**
     * 回退任务
     * 
     * @return
     */
    @RequestMapping("rollback")
    public String rollback(@RequestParam("taskId") String taskId) {
        Command<Integer> cmd = new RollbackTaskCmd(taskId);

        processEngine.getManagementService().executeCommand(cmd);

        return "redirect:/workflow/listPersonalTasks.do";
    }

    /**
     * 取回任务
     * 
     * @return
     */
    @RequestMapping("withdraw")
    public String withdraw(@RequestParam("taskId") String taskId) {
        Command<Integer> cmd = new WithdrawTaskCmd(taskId);

        processEngine.getManagementService().executeCommand(cmd);

        return "redirect:/workflow/listPersonalTasks.do";
    }

    /**
     * 准备加减签.
     */
    @RequestMapping("changeCounterSign")
    public String changeCounterSign() {
        return "workflow/changeCounterSign";
    }

    /**
     * 进行加减签.
     */
    @RequestMapping("saveCounterSign")
    public String saveCounterSign(@RequestParam("operationType") String operationType,
            @RequestParam("userId") String userId, @RequestParam("taskId") String taskId) {
        CounterSignCmd cmd = new CounterSignCmd(operationType, userId, taskId);

        processEngine.getManagementService().executeCommand(cmd);

        return "redirect:/workflow/listPersonalTasks.do";
    }

    /**
     * 转办.
     */
    @RequestMapping("doDelegate")
    public String doDelegate(@RequestParam("taskId") String taskId, @RequestParam("attorney") String attorney) {
        DelegateTaskCmd cmd = new DelegateTaskCmd(taskId, attorney);
        processEngine.getManagementService().executeCommand(cmd);

        return "redirect:/workflow/listPersonalTasks.do";
    }

    /**
     * 协办.
     */
    @RequestMapping("doDelegateHelp")
    public String doDelegateHelp(@RequestParam("taskId") String taskId, @RequestParam("attorney") String attorney) {
        TaskService taskService = processEngine.getTaskService();
        taskService.delegateTask(taskId, attorney);

        return "redirect:/workflow/listPersonalTasks.do";
    }

}
