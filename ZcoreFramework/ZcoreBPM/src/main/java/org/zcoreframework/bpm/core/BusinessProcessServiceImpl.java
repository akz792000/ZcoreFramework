/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.bpm.core;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.context.ConfigurableApplicationContext;
import org.zcoreframework.base.annotation.Service;
import org.zcoreframework.base.annotation.ServiceAction;
import org.zcoreframework.base.annotation.ServiceActionParam;
import org.zcoreframework.base.calendar.icu.PersianDateFormat;
import org.zcoreframework.base.component.ResponseResult;
import org.zcoreframework.base.component.ResponseResult.ResponseType;
import org.zcoreframework.base.service.ServiceProperty;
import org.zcoreframework.base.service.ServicePropertyImpl;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.base.util.MessageSourceUtils;
import org.zcoreframework.base.util.ReflectionUtils;
import org.zcoreframework.security.core.SecurityHelper;
import org.zcoreframework.security.model.UserInfo;
import org.zcoreframework.web.service.controller.AbstractServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Task variables are only available within the task itself. Once the task is complete, 
 * they are gone (although present in history if configured to historyLevel FULL).
 */

@Service
public class BusinessProcessServiceImpl extends AbstractServiceImpl implements BusinessProcessService {

    @ServiceAction
    @Override
    public ProcessInstance startProcessInstanceByKey(@ServiceActionParam("processDefinitionKey") String processDefinitionKey, @ServiceActionParam("businessKey") String businessKey) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("organization", SecurityHelper.getUserInfo().getOrganization().getId());
        variables.put("businessKey", businessKey);
        return BusinessProcessUtils.getRuntimeService().startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
    }

    @ServiceAction
    @Override
    public void complete(@ServiceActionParam("taskId") String taskId) throws Exception {
        BusinessProcessUtils.getTaskService().complete(taskId);
    }

    @ServiceAction
    @Override
    public void complete(@ServiceActionParam("taskId") String taskId, @ServiceActionParam("variables") Map<String, Object> variables) throws Exception {
        BusinessProcessUtils.getTaskService().complete(taskId, variables);
    }

    @ServiceAction
    @Override
    public ResponseResult getBpmn(@ServiceActionParam("processDefinitionId") String processDefinitionId) throws Exception {
        // prepare response result
        String fileType = "xml";
        ResponseResult responseResult = new ResponseResult(ResponseType.DOWNLOAD, IOUtils.toByteArray(BusinessProcessUtils.getRepositoryService().getProcessModel(processDefinitionId)));
        responseResult.getParameter().put("FileType", fileType);
        responseResult.getParameter().put(
                "Content",
                "inline; filename=" + processDefinitionId + '_'
                        + new PersianDateFormat("yyyyMMdd-hhmmss", ULocale.getDefault()).format(Calendar.getInstance().getTime()) + "." + fileType);
        return responseResult;
    }

    @ServiceAction()
    @Override
    public void delete(@ServiceActionParam("processInstanceId") String processInstanceId, @ServiceActionParam("msg") String msg) {
        BusinessProcessUtils.getRuntimeService().deleteProcessInstance(processInstanceId, msg);
    }

    @ServiceAction
    @Override
    public List<Object> getCustomTasks() {
        return BusinessProcessUtils.getCustomTasks();
    }

    @ServiceAction
    public ServiceProperty fetchServices(@ServiceActionParam("system") String system) {
        // root node
        List<ServiceProperty> services = new ArrayList<>();
        String context = "core";
        for (String serviceName : ((ConfigurableApplicationContext) ApplicationContextUtils.getApplicationContext()).getBeanFactory()
                .getBeanNamesForAnnotation(Service.class)) {
            if (serviceName.startsWith(system + "_")) {
                Class<?> clazz = ((ConfigurableApplicationContext) ApplicationContextUtils.getApplicationContext()).getBeanFactory().getType(serviceName);
                List<ServiceProperty> actions = new ArrayList<>();
                for (String actionName : ReflectionUtils.getMethods(clazz, ServiceAction.class)) {
                    actions.add(new ServicePropertyImpl(actionName, MessageSourceUtils.getMessage(context + "." + serviceName + "." + actionName)));
                }
                services.add(new ServicePropertyImpl(serviceName, MessageSourceUtils.getMessage(context + "." + serviceName), actions));
            }
        }
        return new ServicePropertyImpl(services);
    }

    @ServiceAction
    public Integer getTaskAssignee() {
        return BusinessProcessUtils.getTasks().size();
    }

}
