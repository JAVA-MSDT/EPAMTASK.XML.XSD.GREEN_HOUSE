package com.epam.javast.greenhouse.model.enumeration;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Season")
public enum Season {
    @XmlEnumValue(value = "summer")
    SUMMER,
    @XmlEnumValue(value = "winter")
    WINTER,
    @XmlEnumValue(value = "autumn")
    AUTUMN,
    @XmlEnumValue(value = "spring")
    SPRING
}
