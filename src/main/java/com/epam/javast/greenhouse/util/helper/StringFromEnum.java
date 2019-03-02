package com.epam.javast.greenhouse.util.helper;

public class StringFromEnum {

    /**
     *
     * @param values from enum class as array
     * @param s String that we need to compare with the values of the enum
     * @return the enum value if it is equal to the string or null if it is not in the list.
     */
    public static String getString(Enum[] values, String s) {
        if(values == null || s == null || s.isEmpty()){
            throw new IllegalArgumentException("check the getStringFromEnum parameter, it is not allow for null or empty string");
        }
        String findingValue = null;
        for (Enum value : values) {
            if (s.toUpperCase().equalsIgnoreCase(value.name())) {
                findingValue = value.name();
            }
        }
        return findingValue;
    }
}
