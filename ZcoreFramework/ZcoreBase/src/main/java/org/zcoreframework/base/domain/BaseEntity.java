package org.zcoreframework.base.domain;

import org.eclipse.persistence.annotations.Converter;
import org.eclipse.persistence.annotations.Converters;
import org.zcoreframework.base.domain.descriptor.GenericEnumConverter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 * Date: 4/18/17
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Converters({@Converter(name = GenericEnumConverter.NAME, converterClass = GenericEnumConverter.class)})
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String FIELD_VERSION = "VERSION";

    @Version
    @Column(name = FIELD_VERSION, precision = 12)
    private Long version;

    public Long getVersion() {
        return version;
    }
}
