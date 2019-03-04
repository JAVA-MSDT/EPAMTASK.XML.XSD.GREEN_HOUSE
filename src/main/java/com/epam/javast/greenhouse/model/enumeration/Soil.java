package com.epam.javast.greenhouse.model.enumeration;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Soil")
public enum Soil {
    @XmlEnumValue(value = "podzol")
    PODZOL,
    @XmlEnumValue(value = "ground")
    GROUND,
    @XmlEnumValue(value = "sod-podzolic")
    SOD_PODZOLIC

}
