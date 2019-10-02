package org.zcoreframework.pos.service.impl;

import mockit.Expectations;
import mockit.integration.junit4.JMockit;
import org.jpos.iso.ISOMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.pos.annotation.ISOMethod;
import org.zcoreframework.pos.annotation.ISOService;
import org.zcoreframework.pos.constant.ISOMethodReturnType;
import org.zcoreframework.pos.data.ISOMessage;
import org.zcoreframework.pos.data.MethodMetadata;
import org.zcoreframework.pos.exception.PosRuntimeException;
import org.zcoreframework.pos.service.ISOServiceDispatcher;
import org.zcoreframework.pos.service.ISOServiceRegistry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@RunWith(JMockit.class)
public class ISOServiceRegistryImplTest {

    @Test
    public void newISOServiceRegistryNormal() throws Exception {
        Map<String, Class<?>> map = new HashMap<>();
        map.put("ISOService1", ISOService1.class);

        new Expectations(ApplicationContextUtils.class) {{
            ApplicationContextUtils.getBeanNamesForAnnotation((Class<? extends Annotation>) any);
            result = map;
        }};

        ISOServiceRegistry registry = new ISOServiceRegistryImpl();
        MethodMetadata metadata1 = registry.getMethodMetadata("1101", "10000");
        MethodMetadata metadata2 = registry.getMethodMetadata("1101", "30000");
        MethodMetadata metadata3 = registry.getMethodMetadata("1101", "40000");

        assertEquals("execute1", metadata1.getMethod().getName());
        assertEquals(0, metadata1.getIdxInZMsg());
        assertEquals(-1, metadata1.getIdxInJMsg());
        assertEquals(ISOMethodReturnType.ZMSG, metadata1.getReturnType());

        assertEquals("execute2", metadata2.getMethod().getName());
        assertEquals(1, metadata2.getIdxInZMsg());
        assertEquals(-1, metadata2.getIdxInJMsg());
        assertEquals(ISOMethodReturnType.VOID, metadata2.getReturnType());

        assertEquals("execute3", metadata3.getMethod().getName());
        assertEquals(-1, metadata3.getIdxInZMsg());
        assertEquals(0, metadata3.getIdxInJMsg());
        assertEquals(ISOMethodReturnType.VOID, metadata3.getReturnType());
    }

    @Test
    public void newISOServiceRegistryMultiple() throws Exception {
        Map<String, Class<?>> map = new HashMap<>();
        map.put("ISOService1", ISOService1.class);
        map.put("ISOService2", ISOService2.class);

        new Expectations(ApplicationContextUtils.class) {{
            ApplicationContextUtils.getBeanNamesForAnnotation((Class<? extends Annotation>) any);
            result = map;
        }};

        ISOServiceRegistry registry = new ISOServiceRegistryImpl();
        Field f = ISOServiceRegistryImpl.class.getDeclaredField("methodRegistry");
        f.setAccessible(true);

        Map methodRegistry = (Map) f.get(registry);

        assertEquals(2, methodRegistry.size());
        assertEquals(4, ((Map) methodRegistry.get("1101")).size());
        assertEquals(3, ((Map) methodRegistry.get("1201")).size());
    }

    @Test(expected = PosRuntimeException.class)
    public void newISOServiceRegistryConflict() throws Exception {
        Map<String, Class<?>> map = new HashMap<>();
        map.put("ISOService2", ISOService2.class);
        map.put("ISOService3", ISOService3.class);

        new Expectations(ApplicationContextUtils.class) {{
            ApplicationContextUtils.getBeanNamesForAnnotation((Class<? extends Annotation>) any);
            result = map;
        }};

        new ISOServiceRegistryImpl();
    }

    @Test(expected = PosRuntimeException.class)
    public void newISOServiceRegistryInvalidSign() throws Exception {
        Map<String, Class<?>> map = new HashMap<>();
        map.put("ISOService4", ISOService4.class);

        new Expectations(ApplicationContextUtils.class) {{
            ApplicationContextUtils.getBeanNamesForAnnotation((Class<? extends Annotation>) any);
            result = map;
        }};

        new ISOServiceRegistryImpl();
    }

    @ISOService(mti = "1101")
    private static class ISOService1 {

        @ISOMethod(prCodes = {"10000"})
        public ISOMessage execute1(ISOMessage message) {
            return null;
        }

        @ISOMethod(prCodes = {"20000", "30000"})
        public void execute2(Integer i, ISOMessage message) {
        }

        @ISOMethod(prCodes = {"40000"})
        public void execute3(ISOMsg message) {
        }
    }

    @ISOService(mti = "1201")
    private static class ISOService2 {

        @ISOMethod(prCodes = {"10000"})
        public ISOMessage execute1(ISOMsg message) {
            return null;
        }

        @ISOMethod(prCodes = {"20000", "30000"})
        public void execute2(ISOMsg message) {
        }
    }

    @ISOService(mti = "1201")
    private class ISOService3 {

        @ISOMethod(prCodes = {"50000"})
        public ISOMessage execute1(ISOMessage message) {
            return null;
        }
    }

    @ISOService(mti = "1201")
    private class ISOService4 {

        @ISOMethod(prCodes = {"60000"})
        public Boolean execute1() {
            return null;
        }
    }
}