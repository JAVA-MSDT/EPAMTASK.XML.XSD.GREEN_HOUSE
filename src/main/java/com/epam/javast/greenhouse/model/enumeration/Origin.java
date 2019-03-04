package com.epam.javast.greenhouse.model.enumeration;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Origin")
public enum Origin {
    @XmlEnumValue(value = "europe")
    EUROPE,
    @XmlEnumValue(value = "asia")
    ASIA,
    @XmlEnumValue(value = "africa")
    AFRICA,
    @XmlEnumValue(value = "north-america")
    NORTH_AMERICA,
    @XmlEnumValue(value = "south-america")
    SOUTH_AMERICA
}
