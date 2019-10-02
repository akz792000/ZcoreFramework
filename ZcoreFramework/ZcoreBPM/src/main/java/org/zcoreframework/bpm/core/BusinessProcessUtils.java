/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.bpm.core;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.PersianCalendar;
import com.ibm.icu.util.ULocale;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.impl.BpmnModelConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.calendar.icu.PersianDateFormat;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.bpm.config.ContextDefinitionParser;
import org.zcoreframework.security.core.SecurityHelper;

import java.util.*;

class BusinessProcessUtils {

    private static BusinessProcessRegistry camundaRegistry;

    static {
        camundaRegistry = (BusinessProcessRegistry) ApplicationContextUtils.getBean(ContextDefinitionParser.REGISTRY);
    }

    public static RepositoryService getRepositoryService() {
        return BusinessProcessUtils.camundaRegistry.getRepositoryService();
    }

    public static RuntimeService getRuntimeService() {
        return BusinessProcessUtils.camundaRegistry.getRuntimeService();
    }

    public static TaskService getTaskService() {
        return BusinessProcessUtils.camundaRegistry.getTaskService();
    }

    public static HistoryService getHistoryService() {
        return BusinessProcessUtils.camundaRegistry.getHistoryService();
    }

    public static ManagementService getManagementService() {
        return BusinessProcessUtils.camundaRegistry.getManagementService();
    }

    public static FormService getFormService() {
        return BusinessProcessUtils.camundaRegistry.getFormService();
    }

    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

		/*
         * add users' group
		 * 
		 * select distinct RES.REV_, RES.ID_, RES.NAME_, RES.PARENT_TASK_ID_,
		 * RES.DESCRIPTION_, RES.PRIORITY_, RES.CREATE_TIME_, RES.OWNER_,
		 * RES.ASSIGNEE_, RES.DELEGATION_, RES.EXECUTION_ID_, RES.PROC_INST_ID_,
		 * RES.PROC_DEF_ID_, RES.CASE_EXECUTION_ID_, RES.CASE_INST_ID_,
		 * RES.CASE_DEF_ID_, RES.TASK_DEF_KEY_, RES.DUE_DATE_,
		 * RES.FOLLOW_UP_DATE_, RES.SUSPENSION_STATE_, RES.TENANT_ID_ from
		 * ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ =
		 * RES.ID_ WHERE RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' and
		 * (I.GROUP_ID_ IN ('ROLE_ADMIN')) order by RES.ID_ asc
		 */
        List<String> candidateGroup = new ArrayList<String>();
        for (GrantedAuthority grantedAuthority : SecurityHelper.getAuthorities()) {
            for (String authority : grantedAuthority.getAuthority().split(",")) {
                candidateGroup.add(authority);
            }
        }
        tasks.addAll(getTaskService().createTaskQuery()
                        .processVariableValueEquals("organization", SecurityHelper.getUserInfo().getOrganization().getId())
                        .taskCandidateGroupIn(candidateGroup)
                        .active()
                        .list()
        );

		/*
         * add users' task
		 * 
		 * select distinct RES.REV_, RES.ID_, RES.NAME_, RES.PARENT_TASK_ID_,
		 * RES.DESCRIPTION_, RES.PRIORITY_, RES.CREATE_TIME_, RES.OWNER_,
		 * RES.ASSIGNEE_, RES.DELEGATION_, RES.EXECUTION_ID_, RES.PROC_INST_ID_,
		 * RES.PROC_DEF_ID_, RES.CASE_EXECUTION_ID_, RES.CASE_INST_ID_,
		 * RES.CASE_DEF_ID_, RES.TASK_DEF_KEY_, RES.DUE_DATE_,
		 * RES.FOLLOW_UP_DATE_, RES.SUSPENSION_STATE_, RES.TENANT_ID_ from
		 * ACT_RU_TASK RES WHERE RES.ASSIGNEE_ = ? order by RES.ID_ asc
		 */
		tasks.addAll(getTaskService().createTaskQuery()
                        .taskAssignee(SecurityHelper.getUserInfo().getUsername())
                        .active()
                        .list()
        );

        return tasks;
    }

    public static List<Object> getCustomTasks() {
        List<Object> result = new ArrayList<>();
        for (Task task : getTasks()) {
            List<Object> objects = new ArrayList<>();

			/*
             * process for show flow diagram and get process definition
			 */
            ProcessDefinition processDefinition = getRepositoryService().getProcessDefinition(task.getProcessDefinitionId());
            objects.add(processDefinition.getId());
            objects.add(processDefinition.getKey());
            objects.add(processDefinition.getName());
            objects.add(task.getProcessInstanceId());
            objects.add(getRuntimeService().createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getBusinessKey());

            // task
            objects.add(task.getTaskDefinitionKey());
            objects.add(task.getId());
            objects.add(task.getName());
            objects.add(task.getCreateTime());

            // for data
            TaskFormData taskFormData = getFormService().getTaskFormData(task.getId());
            List<Map<String, Object>> items = new LinkedList<>();
            Map<String, Object> key = new LinkedHashMap<>();
            for (FormField formField : taskFormData.getFormFields()) {
                Map<String, Object> item = new LinkedHashMap<>();
                if (formField.getId().toLowerCase().equals("key")) {

                    // add key
                    key.put("context", formField.getProperties().get("context"));
                    key.put("ui", formField.getProperties().get("ui"));
                    key.put("uparam", formField.getProperties().get("uparam"));
                    key.put("service", formField.getProperties().get("service"));
                    key.put("param", formField.getProperties().get("param"));

                } else {

                    // add label of component
                    if (!StringUtils.isEmpty(formField.getLabel())) {
                        item.put("type", "label");
                        item.put("value", formField.getLabel());
                        items.add(item);
                        item = new HashMap<String, Object>();
                    }

                    // add component
                    item.put("id", formField.getId());
                    for (Map.Entry<String, String> entry : formField.getProperties().entrySet()) {
                        item.put(entry.getKey(), entry.getValue());
                    }
                    items.add(item);

                }
            }
            objects.add(JsonUtils.encode(key)); // form key
            objects.add(JsonUtils.encode(items)); // form data

            // variables
            Map<String, Object> variables = new HashMap<>();
            for (Map.Entry<String, Object> entry : getTaskService().getVariables(task.getId()).entrySet()) {
                variables.put(entry.getKey(), entry.getValue());
            }
            objects.add(JsonUtils.encode(variables));

            result.add(objects.toArray());
        }
        return result;
    }

    public static List<Object> getTaskDetails(String taskId) {

        List<Object> list = new ArrayList<>();

        List<Object> objects = null;

        Calendar calendar = new PersianCalendar();

        for (HistoricTaskInstance historicTaskInstance : getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(taskId).finished().list()) {
            objects = new ArrayList<>();

            // task
            objects.add(historicTaskInstance.getName());
            calendar.setTime(historicTaskInstance.getEndTime());
            objects.add(new PersianDateFormat("yyyy/MM/dd HH:mm:ss", ULocale.getDefault()).format(calendar.getTime()));

            // variables
            BpmnModelInstance bpmnModelInstance = getRepositoryService().getBpmnModelInstance(historicTaskInstance.getProcessDefinitionId());
            org.camunda.bpm.model.bpmn.instance.Task task = bpmnModelInstance.getModelElementById(historicTaskInstance.getTaskDefinitionKey());
            objects.add(task.getAttributeValueNs(BpmnModelConstants.CAMUNDA_NS, "formKey"));

            list.add(objects.toArray());
        }

        return list;
    }

}
