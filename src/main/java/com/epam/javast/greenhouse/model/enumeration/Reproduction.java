package com.epam.javast.greenhouse.model.enumeration;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Reproduction")
public enum Reproduction {
    @XmlEnumValue(value = "leaves")
    LEAVES,
    @XmlEnumValue(value = "cutting")
    CUTTING,
    @XmlEnumValue(value = "seed")
    SEED

}
