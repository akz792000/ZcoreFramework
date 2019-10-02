/**
 *
 * @author Ali Karimizandi
 * @since 2009
 *
 */

package org.zcoreframework.web.service.soap;

import org.apache.cxf.jaxws.context.WrappedMessageContext;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;
import java.util.List;

public class SoapContext {

    private final WrappedMessageContext context;

    private final HttpServletRequest request;

    private List<Object> params;

    private Object result;

    public WrappedMessageContext getContext() {
        return context;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public SoapContext(WrappedMessageContext context, List<Object> params) {
        this.context = context;
        this.params = params;
        this.request = (HttpServletRequest) context.get(MessageContext.SERVLET_REQUEST);
    }
}
