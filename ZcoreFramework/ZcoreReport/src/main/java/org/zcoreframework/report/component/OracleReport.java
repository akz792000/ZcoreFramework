/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.report.component;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;
import org.zcoreframework.base.calendar.icu.PersianDateFormat;
import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.component.AjaxClientable;
import org.zcoreframework.base.component.Component;
import org.zcoreframework.base.component.ResponseResult;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue;
import org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportService;
import org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportServiceServiceLocator;
import org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest;
import org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse;
import org.zcoreframework.report.util.ReportUtils;
import org.zcoreframework.base.component.ResponseResult.ResponseType;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;

public class OracleReport extends AbstractComponentImpl implements AjaxClientable {

	private static final Log log = LogFactory.getLog(OracleReport.class);
	
	private String path;

	private String name;

	private String fileType;
	
	private String template;

	private Map<String, String[]> params = new HashMap<>();
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getTemplate() {
		return template;
	}
	
	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, String[]> getParams() {
		return params;
	}

	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}	
	
	public String[] addParam(String key, String[] value) {
		return params.put(key, value);
	}	
	
	public OracleReport setParam(String param1, String param2) {
        if (!param2.equals("null") && !param2.equals("")) {
            params.put(param1, new String[]{param2});
        }
        return this;
    }	

	public String getRealFileType() {
		if (fileType.toLowerCase().equals("excel"))
			return "xls";
		else
			return fileType.toLowerCase();
	}

	@Override
	public Object data() {
		return null;
	}

	@Override
	public Object value() {
		return null;
	}

	@Override
	public Component getClientModel() {
		return null;
	}

	@Override
	public ResponseResult partial() throws BaseException {

		// public report service locator
		PublicReportServiceServiceLocator publicReportServiceServiceLocator = new PublicReportServiceServiceLocator(ReportUtils.getUrl());
		PublicReportService publicReportService = null;
		try {
			publicReportService = publicReportServiceServiceLocator.getPublicReportService();
		} catch (ServiceException e) {
			ReflectionUtils.handleReflectionException(e);
		}

		// create report request
		ReportRequest repReq = new ReportRequest();

		// set absolute path and file type
		String absoluteCompletePath = ReportUtils.getAbsolutePath() + path + "/%REPORT_NAME%/%REPORT_NAME%.xdo".replace("%REPORT_NAME%", name);
		repReq.setReportAbsolutePath(absoluteCompletePath);
		repReq.setAttributeFormat(fileType);
		repReq.setAttributeTemplate(template);
		repReq.setSizeOfDataChunkDownload(-1);

		// set params value
		if (params.size() > 0) {
			ParamNameValue[] paramNameValue = new ParamNameValue[params.size()];
			int index = 0;
			for (Entry<String, String[]> entrySet : params.entrySet()) {
				paramNameValue[index] = new ParamNameValue();
				paramNameValue[index].setName(entrySet.getKey());
				paramNameValue[index].setValues(entrySet.getValue());
				index++;
			}
			repReq.setParameterNameValues(paramNameValue);
		}

		// log
		if (log.isDebugEnabled()) {
				log.debug("Report parameters: [name=" + name + "],[fileType=" + fileType + "],[parametes=" + JsonUtils.encode(params) + "]");
		}

		// set response
		ReportResponse report = new ReportResponse();
		try {
			report = publicReportService.runReport(repReq, ReportUtils.getUsername(), ReportUtils.getPassword());
		} catch (RemoteException e) {
			throw new BaseException(e.getMessage());
		}
		
		// return result		
		ResponseResult responseResult = new ResponseResult(ResponseType.DOWNLOAD, report.getReportBytes());
		responseResult.getParameter().put("FileType", fileType);
		responseResult.getParameter()
			.put("Content", "inline; filename=" + name + '_' +  new PersianDateFormat("yyyyMMdd-hhmmss", ULocale.getDefault()).format(Calendar.getInstance().getTime()) + "." + fileType);	
		return responseResult; 
	}

}
