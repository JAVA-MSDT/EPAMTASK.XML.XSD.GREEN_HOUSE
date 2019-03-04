package com.epam.javast.greenhouse.model.enumeration;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Size")
public enum Size{
    @XmlEnumValue(value = "small")
    SMALL,
    @XmlEnumValue(value = "medium")
    MEDIUM,
    @XmlEnumValue(value = "large")
    LARGE
}
