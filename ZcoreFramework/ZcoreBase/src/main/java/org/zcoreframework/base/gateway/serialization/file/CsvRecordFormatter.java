package org.zcoreframework.base.gateway.serialization.file;

import org.zcoreframework.base.gateway.exception.GatewayFormatException;
import org.zcoreframework.base.util.ArrayList;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class CsvRecordFormatter {

    private final Class modelType;

    public CsvRecordFormatter(Class<?> modelType) {
        this.modelType = modelType;
    }

    public Iterable<String> apply(Object model) throws GatewayFormatException {
        if (model == null) {
            return null;
        }

        FileEntryOrder order = model.getClass().getAnnotation(FileEntryOrder.class);

        if (order == null) {
            throw new GatewayFormatException("_TODO");
        }

        List<String> list = new ArrayList<>();

        try {
            for (String prop : order.value()) {
                Field field = model.getClass().getDeclaredField(prop.trim());
                field.setAccessible(true);
                list.add(convert(field.getType(), field.get(model)));
            }
        } catch (NoSuchFieldException e) {
            throw new GatewayFormatException("_TODO", e);
        } catch (IllegalAccessException e) {
            throw new GatewayFormatException("_TODO", e);
        }

        return list;
    }

    private String convert(Class<?> type, Object value) throws GatewayFormatException {
        if (value == null) {
            return "";
        } else if (value instanceof Date) {
            FileDateFormat dateFormat = type.getAnnotation(FileDateFormat.class);

            if (dateFormat == null) {
                throw new GatewayFormatException("_TODO");
            }


            return new SimpleDateFormat(dateFormat.pattern()).format((Date) value);
        } else if (value instanceof Calendar) {
            return convert(type, ((Calendar) value).getTime());
        }

        return value.toString();
    }
}
