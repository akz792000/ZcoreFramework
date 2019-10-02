package org.zcoreframework.pos.utils;

import org.jpos.iso.ISOField;
import org.jpos.iso.ISOMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.zcoreframework.pos.data.ISOMessage;
import org.zcoreframework.pos.data.MethodMetadata;
import org.zcoreframework.pos.exception.PosStandardException;
import org.zcoreframework.pos.service.ISOPaymentService;
import org.zcoreframework.pos.service.ISORegisterService;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.*;

/**
 *
 */
@SuppressWarnings("rawtypes")
@RunWith(JUnit4.class)
public class InvokerUtilsTest {
    @Test(expected = PosStandardException.class)
    public void responseMergeInvokeNull() throws Exception {
        InvokerUtils.invoke(null, null, new ISOMsg());
    }

    @Test
    public void responseMergeInvoke1() throws Exception {
        Method method = ISOPaymentService.class.getMethod("invoke", ISOMessage.class);
        ISOMsg msg = new ISOMsg();
        msg.set(9, "101010");

        msg = InvokerUtils.invoke(new ISOPaymentService(), new MethodMetadata(method), msg);

        Map map = msg.getChildren();

        assertEquals(2, map.size());
        assertEquals("101010", ((ISOField) map.get(9)).getValue());
        assertEquals("000", ((ISOField) map.get(39)).getValue());
    }

    @Test
    public void responseMergeInvoke2() throws Exception {
        Method method = ISORegisterService.class.getMethod("invoke", ISOMsg.class);
        ISOMsg msg = new ISOMsg();
        msg.set(9, "101010");

        msg = InvokerUtils.invoke(new ISORegisterService(), new MethodMetadata(method), msg);

        Map map = msg.getChildren();

        assertEquals(2, map.size());
        assertEquals("101010", ((ISOField) map.get(9)).getValue());
        assertEquals("000", ((ISOField) map.get(39)).getValue());
    }
}