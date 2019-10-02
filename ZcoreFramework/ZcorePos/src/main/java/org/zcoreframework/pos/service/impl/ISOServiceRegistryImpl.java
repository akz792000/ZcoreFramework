package org.zcoreframework.pos.service.impl;

import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.pos.annotation.ISOMethod;
import org.zcoreframework.pos.annotation.ISOService;
import org.zcoreframework.pos.data.MethodMetadata;
import org.zcoreframework.pos.exception.PosRuntimeException;
import org.zcoreframework.pos.service.ISOServiceRegistry;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ISOServiceRegistryImpl implements ISOServiceRegistry {

    private final Map<String, String> beanRegistry;

    private final Map<String, Map<String, MethodMetadata>> methodRegistry;

    public ISOServiceRegistryImpl() {
        beanRegistry = new HashMap<>();
        methodRegistry = new HashMap<>();

        try {
            Map<String, Class<?>> map = ApplicationContextUtils.getBeanNamesForAnnotation(ISOService.class);
            map.forEach((s, clazz) -> add(s, clazz));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String mti) {
        if (mti == null || !beanRegistry.containsKey(mti)) {
            return null;
        }

        return ApplicationContextUtils.getBean(beanRegistry.get(mti));
    }

    public MethodMetadata getMethodMetadata(String mti, String processCode) {
        if (mti == null || processCode == null) {
            return null;
        }

        Map<String, MethodMetadata> map = methodRegistry.get(mti);

        if (map == null) {
            return null;
        }

        return map.get(processCode);
    }

    private void add(String beanName, Class<?> beanClass) {
        ISOService endpoint = beanClass.getAnnotation(ISOService.class);
        String mti = endpoint == null ? null : (endpoint.mti() == null ? null : endpoint.mti().trim());

        if (mti == null || mti.isEmpty()) {
            throw new PosRuntimeException("MTI code must be specified: " + beanClass.getName());
        } else if (beanRegistry.containsKey(mti)) {
            throw new PosRuntimeException("Duplicate MTI code: " + mti);
        }

        Map<String, MethodMetadata> methods = new HashMap<>();

        for (Method method : beanClass.getDeclaredMethods()) {
            ISOMethod isoMethod = method.getAnnotation(ISOMethod.class);
            MethodMetadata metadata = new MethodMetadata(method);

            if (isoMethod == null) {
                continue;
            } else if (isoMethod.prCodes() == null || isoMethod.prCodes().length == 0) {
                throw new PosRuntimeException("PR code must be specified: " + beanClass.getName());
            } else if (metadata.getIdxInZMsg() < 0 && metadata.getIdxInJMsg() < 0) {
                throw new PosRuntimeException("At least one message input must be declared: " + beanClass.getName());
            }

            for (String code : isoMethod.prCodes()) {
                if (methods.containsKey(code.trim())) {
                    throw new PosRuntimeException("Duplicate PR code: " + code);
                }

                methods.put(code.trim(), metadata);
            }
        }

        beanRegistry.put(mti, beanName);
        methodRegistry.put(mti, methods);
    }

}
