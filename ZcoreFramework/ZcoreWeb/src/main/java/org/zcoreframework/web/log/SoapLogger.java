package org.zcoreframework.web.log;

import org.apache.cxf.service.model.MessageInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.springframework.util.StopWatch;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.log.LogMock;
import org.zcoreframework.base.log.Logger;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.web.log.domain.SoapLogEntity;
import org.zcoreframework.web.service.soap.SoapContext;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class SoapLogger implements Logger, InitializeAware {

    @RepositoryInstance
    private DefaultRepository repository;

    protected String getParameters(SoapContext soapContext) {
        Map<String, Object> result = new HashMap<>();
        List<MessagePartInfo> messagePartInfos = ((MessageInfo) soapContext.getContext().get("org.apache.cxf.service.model.MessageInfo")).getMessageParts();
        for (MessagePartInfo messagePartInfo : messagePartInfos) {
            result.put(messagePartInfo.getName().getLocalPart(), soapContext.getParams().get(messagePartInfo.getIndex()));
        }
        return JsonUtils.encode(result);
    }

    protected void persist(SoapContext soapContext, StopWatch stopWatch, String status) {
        SoapLogEntity entity = new SoapLogEntity();
        HttpServletRequest request = soapContext.getRequest();
        entity.setId(UUID.randomUUID().toString());
        entity.setLocale(String.valueOf(request.getLocale()));
        entity.setMethod(request.getMethod());
        entity.setProtocol(request.getProtocol());
        entity.setUri(request.getRequestURI());
        entity.setUserAgent(request.getHeader("user-agent"));
        entity.setRemoteAddr(request.getRemoteAddr());
        entity.setRemotePort(request.getRemotePort());
        entity.setServerName(request.getServerName());
        entity.setServerPort(request.getServerPort());
        entity.setTotalTimeMillis(stopWatch.getTotalTimeMillis());
        entity.setEffectiveDate(new Date());
        entity.setStatus(status);
        entity.setParameter(getParameters(soapContext));
        repository.persist(entity);
    }

    @Override
    public void log(LogMock logMock) throws Exception {
        if (logMock.getStopWatch().isRunning()) {
            logMock.getStopWatch().stop();
        }
        persist((SoapContext) logMock.getObject(), logMock.getStopWatch(), logMock.getType().toString());
    }

}

