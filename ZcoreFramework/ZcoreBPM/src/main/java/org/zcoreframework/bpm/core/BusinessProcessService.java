/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.bpm.core;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.zcoreframework.base.component.ResponseResult;

import java.util.List;
import java.util.Map;

public interface BusinessProcessService {

    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey);

    void complete(String taskId) throws Exception;

    void complete(String taskId, Map<String, Object> variables) throws Exception;

    ResponseResult getBpmn(String processDefinitionId) throws Exception;

    void delete(String processInstanceId, String msg);

    List<Object> getCustomTasks();

    Integer getTaskAssignee();

}