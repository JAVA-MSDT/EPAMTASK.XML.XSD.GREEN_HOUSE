package com.epam.javast.greenhouse.model.enumeration;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Color")
public enum Color {

    @XmlEnumValue(value = "white")
    WHITE,
    @XmlEnumValue(value = "black")
    BLACK,
    @XmlEnumValue(value = "red")
    RED,
    @XmlEnumValue(value = "green")
    GREEN,
    @XmlEnumValue(value = "yellow")
    YELLOW,
    @XmlEnumValue(value = "orange")
    ORANGE,
    @XmlEnumValue(value = "blue")
    BLUE,
    @XmlEnumValue(value = "rose")
    ROSE;

    Color() {
    }


}
