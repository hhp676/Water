package com.hongguaninfo.hgdf.workflow.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.hongguaninfo.hgdf.adp.shiro.conf.Auths;
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
 * 工作流核心 Crontroller
 * 
 * @author henry
 */
@Controller
@RequestMapping(value = "/workflow")
public class WfController {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private WfService workFlowService;

    /**
     * 流程列表
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(Auths.PROCESS_SHOWTOSTART)
    @RequestMapping(value = "/process/show")
    public String showProcess(HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "workflow/process_show";
            }
        };
        return templete.operateModel();
    }

    /**
     * 流程列表查询
     * 
     * @param pageRequest
     * @param response
     * @param request
     * @return
     */
    @RequiresPermissions(Auths.PROCESS_SHOWTOSTART)
    @RequestMapping("/process/list")
    @ResponseBody
    public Map getProcessList(final WfProcess vo, final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                workFlowService.getProcessDefinedList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    /**
     * 运行中的流程实例列表
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/process-instance/showTrace/{processDefinitionId}/{processInstanceId}")
    public String showTrace(@PathVariable final String processDefinitionId,
            @PathVariable final String processInstanceId, HttpServletRequest request, HttpServletResponse response,
            final Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                model.addAttribute("processInstanceId", processInstanceId);
                model.addAttribute("processDefinitionId", processDefinitionId);
                model.addAttribute("historicTasks", workFlowService.getHistoricTasks(processInstanceId));
                model.addAttribute("historicVariables", workFlowService.getHistoricVariables(processInstanceId));
                str = "workflow/process_showTrace";
            }
        };
        return templete.operateModel();
    }

    /**
     * 运行中的流程实例列表
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/process-instance/showTracePid/{processInstanceId}")
    public String showTracePid(@PathVariable final String processInstanceId, HttpServletRequest request, HttpServletResponse response,
            final Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                model.addAttribute("processInstanceId", processInstanceId);
                model.addAttribute("processDefinitionId", workFlowService.getWfDefIdByPid(processInstanceId));
                model.addAttribute("historicTasks", workFlowService.getHistoricTasks(processInstanceId));
                model.addAttribute("historicVariables", workFlowService.getHistoricVariables(processInstanceId));
                str = "workflow/process_showTrace";
            }
        };
        return templete.operateModel();
    }

    /**
     * 运行中的流程实例列表
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(Auths.PROCESS_SHOWRUNNINGS)
    @RequestMapping(value = "/process-instance/running/show")
    public String showRunningProcess(HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "workflow/process_running_show";
            }
        };
        return templete.operateModel();
    }

    /**
     * 运行中的流程实例列表查询
     * 
     * @param pageRequest
     * @param response
     * @param request
     * @return
     */
    @RequiresPermissions(Auths.PROCESS_SHOWRUNNINGS)
    @RequestMapping("/process-instance/running/list")
    @ResponseBody
    public Map getRunningProcessList(final WfProcess vo, final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                workFlowService.getRunningProcessList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    /**
     * 办结的流程实例列表
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(Auths.PROCESS_SHOWFINISHS)
    @RequestMapping(value = "/process-instance/finished/show")
    public String showFinishedProcess(HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "workflow/process_finished_show";
            }
        };
        return templete.operateModel();
    }

    /**
     * 办结的流程实例列表查询
     * 
     * @param pageRequest
     * @param response
     * @param request
     * @return
     */
    @RequiresPermissions(Auths.PROCESS_SHOWFINISHS)
    @RequestMapping("/process-instance/finished/list")
    @ResponseBody
    public Map getFinishedProcessList(final WfProcess vo, final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                workFlowService.getFinishedProcessList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    /**
     * 参与的流程实例列表
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(Auths.PROCESS_SHOWJOINS)
    @RequestMapping(value = "/process-instance/joined/show")
    public String showJoinedProcess(HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "workflow/process_joined_show";
            }
        };
        return templete.operateModel();
    }

    /**
     * 参与的流程实例列表查询
     * 
     * @param pageRequest
     * @param response
     * @param request
     * @return
     */
    @RequiresPermissions(Auths.PROCESS_SHOWJOINS)
    @RequestMapping("/process-instance/joined/list")
    @ResponseBody
    public Map getJoinedProcessList(final WfProcess vo, final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                workFlowService.getJoinedProcessList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    /**
     * 待领任务页面
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/task/toClaim/show")
    public String showToClaimTask(HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "workflow/task_toClaim_show";
            }
        };
        return templete.operateModel();
    }

    /**
     * 待领任务列表
     * 
     * @param pageRequest
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/task/toClaim/list")
    @ResponseBody
    public Map getToClaimTaskList(final WfTask vo, final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                workFlowService.getToClaimTaskList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    /**
     * 待办任务页面
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(Auths.TASK_SHOWTODOS)
    @RequestMapping(value = "/task/toDo/show")
    public String showToDoTask(HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "workflow/task_toDo_show";
            }
        };
        return templete.operateModel();
    }

    /**
     * 待领任务列表
     * 
     * @param pageRequest
     * @param response
     * @param request
     * @return
     */
    @RequiresPermissions(Auths.TASK_SHOWTODOS)
    @RequestMapping("/task/toDo/list")
    @ResponseBody
    public Map getToDoTaskList(final WfTask vo, final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                workFlowService.getToDoTaskList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    /**
     * 已办任务页面
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(Auths.TASK_SHOWDONES)
    @RequestMapping(value = "/task/done/show")
    public String showDoneTask(HttpServletRequest request, HttpServletResponse response, Model model) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BaseException {
                str = "workflow/task_done_show";
            }
        };
        return templete.operateModel();
    }

    /**
     * 已办任务列表
     * 
     * @param pageRequest
     * @param response
     * @param request
     * @return
     */
    @RequiresPermissions(Auths.TASK_SHOWDONES)
    @RequestMapping("/task/done/list")
    @ResponseBody
    public Map getDoneTaskList(final WfTask vo, final BasePage pageRequest, HttpServletResponse response,
            HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                workFlowService.getDoneTaskList(pageRequest, vo, map);
            }
        };
        return templete.operate();
    }

    /**
     * 认领任务
     */
    @RequiresPermissions(Auths.TASK_SHOWTOCLAIMS)
    @RequestMapping(value = "/task/doClaim")
    @ResponseBody
    @UserLog(code = "claimTask", name = "认领任务", remarkClass = WfTask.class)
    public Map claimTask(@Valid final WfTask vo, BindingResult result, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    workFlowService.doClaimTask(vo);
                }
            }
        };
        return templete.operate();
    }

    /**
     * 认领任务
     */
    @RequiresPermissions(Auths.TASK_SHOWTOCLAIMS)
    @RequestMapping(value = "/task/doClaimMuti")
    @ResponseBody
    @UserLog(code = "claimTask", name = "批量认领任务", remarkClass = WfTask.class)
    public Map claimTaskMuti(@Valid final WfTask vo, BindingResult result, HttpServletResponse response,
            final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request, result) {
            protected void doSomething() throws BizException {
                if (doValidate()) {
                    workFlowService.doClaimTaskMuti(vo);
                }
            }
        };
        return templete.operate();
    }

    /**
     * 任务统计
     */
    @RequestMapping(value = "/task/count")
    @ResponseBody
    public Map countTask(HttpServletResponse response, final HttpServletRequest request) {
        OperateTemplete templete = new HttpTemplete(request) {
            protected void doSomething() throws BizException {
                workFlowService.doCountTask(map);
            }
        };
        return templete.operate();
    }
}
