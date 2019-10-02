package org.zcoreframework.base.domain.descriptor;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.zcoreframework.base.type.Restorable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.apache.commons.lang.ClassUtils.getAllInterfaces;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 3/29/2017
 */

public final class GenericEnumConverter implements Converter {

    public static final String NAME = "genericEnumConverter";

    private final Map<Long, Restorable> map = new HashMap<>();

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return (objectValue != null) ? (objectValue instanceof Restorable ? ((Restorable) objectValue).getUniqueId() : objectValue) : null;
    }

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        if (dataValue == null) return null;
        final Restorable restorableEnumConstant = map.get(((BigDecimal) dataValue).longValue());
        if (restorableEnumConstant != null) return restorableEnumConstant;
        throw new IllegalArgumentException(format("Restorable enum constant not found for uniqueId %s", dataValue));
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        final Class attributeClass = mapping.getAttributeClassification();
        if (!isRestorableEnum(attributeClass)) {
            throw new IllegalArgumentException(mapping.getAttributeName() + " is not restorable enum and you can not use this converter for it!");
        }

        for (Restorable enumConstant : (Restorable[]) attributeClass.getEnumConstants()) {
            map.put(enumConstant.getUniqueId(), enumConstant);
        }
    }

    private boolean isRestorableEnum(Class attributeClass) {
        return attributeClass.isEnum() && getAllInterfaces(attributeClass).contains(Restorable.class);
    }
}
