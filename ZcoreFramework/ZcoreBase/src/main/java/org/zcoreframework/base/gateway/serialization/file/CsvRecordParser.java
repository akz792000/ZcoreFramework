package org.zcoreframework.base.gateway.serialization.file;


import org.zcoreframework.base.gateway.exception.GatewayFormatException;
import org.zcoreframework.base.gateway.exception.GatewayParseException;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class CsvRecordParser<M> {

    private final Class<? extends M> modelType;

    public CsvRecordParser(Class<? extends M> modelType) {
        this.modelType = modelType;
    }

    public M apply(Long row, Iterable<String> values) throws GatewayParseException {
        if (values == null) {
            return null;
        }

        M model;
        FileEntryOrder order = modelType.getAnnotation(FileEntryOrder.class);

        if (order == null) {
            throw new GatewayParseException("_TODO");
        }

        try {
            model = modelType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new GatewayParseException("_TODO", e);
        }

        try {
            int idx = 0;
            for (String value : values) {
                String prop = order.value()[idx];
                Field field = modelType.getDeclaredField(prop);
                field.setAccessible(true);

                field.set(model, convert(row, field.getType(), value.trim()));

                idx++;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GatewayParseException("_TODO", e);
        } catch (NoSuchFieldException e) {
            throw new GatewayParseException("_TODO", e);
        } catch (IllegalAccessException e) {
            throw new GatewayParseException("_TODO", e);
        }

        return model;
    }

    private Object convert(Long row, Class<?> type, String str) throws GatewayParseException {
        if (str.isEmpty()) {
            return null;
        }

        try {
            if (Long.class.equals(type)) {
                return Long.valueOf(str);
            } else if (Integer.class.equals(type)) {
                return Integer.valueOf(str);
            } else if (Date.class.equals(type)) {
                FileDateFormat dateFormat = type.getAnnotation(FileDateFormat.class);

                if (dateFormat == null) {
                    throw new GatewayFormatException("_TODO");
                }

                return new SimpleDateFormat(dateFormat.pattern()).parse(str);
            } else if (Calendar.class.equals(type)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime((Date) convert(row, Date.class, str));

                return calendar;
            }

        } catch (Exception e) {
            throw new GatewayParseException("_TODO", e, row, str, type);
        }


        return str;
    }
}
