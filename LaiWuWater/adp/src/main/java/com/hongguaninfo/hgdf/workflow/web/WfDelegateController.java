package com.hongguaninfo.hgdf.workflow.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
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
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
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
 * 工作流 代理相关  Crontroller
 * 
 * @author henry
 */
@Controller
@RequestMapping(value = "/workflow/delegate")
public class WfDelegateController {

    private Log log = LogFactory.getLog(getClass());
    
    @Autowired
    private ProcessEngine processEngine;

    // ***************************************************************
    /**
     * 代理中的任务（代理人还未完成该任务）
     * 
     * @return
     */
    @RequestMapping("/tasks/list")
    public String listDelegatedTasks(Model model) {
        TaskService taskService = processEngine.getTaskService();
        String userId = String.valueOf(SessionUtils.getUserId());
        List<Task> tasks = taskService.createTaskQuery().taskOwner(userId).taskDelegationState(DelegationState.PENDING)
                .list();

        model.addAttribute("tasks", tasks);

        return "bpm/workspace-listDelegatedTasks";
    }

    /**
     * 任务代理页面
     * 
     * @return
     */
    @RequestMapping("/task/prepare")
    public String prepareDelegateTask() {
        return "bpm/workspace-prepareDelegateTask";
    }

    /**
     * 任务代理
     * 
     * @return
     */
    @RequestMapping("/task/")
    public String delegateTask(@RequestParam("taskId") String taskId, @RequestParam("userId") String userId) {
        TaskService taskService = processEngine.getTaskService();
        taskService.delegateTask(taskId, userId);

        return "redirect:/bpm/workspace-listPersonalTasks.do";
    }
}
