package org.zcoreframework.pos.utils;

import org.jpos.iso.ISOField;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.XMLPackager;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 *
 */
@SuppressWarnings("rawtypes")
@RunWith(JUnit4.class)
public class ISOMsgUtilsTest {

    @Test
    public void responseMergeConvertNull() {
        assertNull(ISOMsgUtils.convert(null));
    }

    @Test
    public void responseMergeConvert() {
        ISOMsg msg = new ISOMsg();
        msg.set(0, "1200");
        msg.set(20, "kkkk");

        ISOMessage message = ISOMsgUtils.convert(msg);

        assertEquals(2, message.size());
        assertEquals("1200", message.get(0));
        assertEquals("kkkk", message.get(20));
    }

    @Test
    public void responseMergeNull1() {
        assertNotNull(ISOMsgUtils.merge(new ISOMsg(), null));
    }

    @Test
    public void responseMergeNull2() {
        assertNull(ISOMsgUtils.merge(null, new ISOMessage()));
    }

    @Test
    public void responseMerge1() {
        ISOMsg msg = new ISOMsg();
        msg.set(0, "1200");
        msg.set(10, "5555");
        msg.set(20, "kkkk");

        ISOMessage message = new ISOMessage();
        message.put(15, "zzzz");
        message.put(25, "1212");

        msg = ISOMsgUtils.merge(msg, message);

        Map map = msg.getChildren();

        assertEquals(5, map.size());
        assertEquals("1200", ((ISOField) map.get(0)).getValue());
        assertEquals("5555", ((ISOField) map.get(10)).getValue());
        assertEquals("kkkk", ((ISOField) map.get(20)).getValue());
        assertEquals("zzzz", ((ISOField) map.get(15)).getValue());
        assertEquals("1212", ((ISOField) map.get(25)).getValue());
    }

    @Test
    public void responseMerge2() {
        ISOMsg msg = new ISOMsg();
        msg.set(0, "1200");
        msg.set(20, "kkkk");

        ISOMessage message = new ISOMessage();
        message.put(20, "zzzz");

        ISOMsgUtils.merge(msg, message);

        Map map = msg.getChildren();

        assertEquals(2, map.size());
        assertEquals("1200", ((ISOField) map.get(0)).getValue());
        assertEquals("kkkk", ((ISOField) map.get(20)).getValue());
    }

    @Test
    public void response1() throws Exception {
        assertEquals("1210", ISOMsgUtils.response(new ISOMsg("1200")).getMTI());
        assertEquals("1610", ISOMsgUtils.response(new ISOMsg("1600")).getMTI());
    }

    @Test
    public void response2() throws Exception {
        assertEquals("1210", ISOMsgUtils.response(new ISOMsg("1210")).getMTI());
    }

    @Test
    public void parse() throws Exception {
        ISOMsg msg = new ISOMsg("1200");
        msg.set(11, "105623");
        msg.set(12, "20170505112350");
        msg.setPackager(new XMLPackager());


        String expected = "<isomsg>\r\n" +
                "  <!-- org.jpos.iso.packager.XMLPackager -->\r\n" +
                "  <field id=\"0\" value=\"1200\"/>\r\n" +
                "  <field id=\"11\" value=\"105623\"/>\r\n" +
                "  <field id=\"12\" value=\"20170505112350\"/>\r\n" +
                "</isomsg>\r\n";

        assertEquals(expected, ISOMsgUtils.parse(msg));
    }

}