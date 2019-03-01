package com.epam.javast.greenhouse.util.helper;

public class StringFromEnum {

    public static String getString(Enum[] values, String s) {
        if(values == null || s == null || s.isEmpty()){
            throw new IllegalArgumentException("check the getStringFromEnum parameter, it is not allow for null or empty string");
        }
        String findingValue = "";
        for (Enum value : values) {
            if (s.toUpperCase().equalsIgnoreCase(value.name())) {
                findingValue = value.name();
            }
        }
        return findingValue;
    }
}
