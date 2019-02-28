package com.epam.javast.greenhouse.model.enumeration;

/**
 * To use the class variable or the xml elements name by calling them from here
 * to the specific service class instead of typing the same word literally more than once in a different places
 * in the project
 */
public enum PlantEnum {
    PLANTS("plants"),
    FLOWER("flower"),
    VEGETABLE("vegetable"),
    VISUAL_PARAMETER("visual-parameter"),
    GROWING_TIPS("growing-tips"),
    ID("id"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    STALK_COLOR("stalk-color"),
    LEAF_COLOR("leaf-color"),
    AVERAGE_SIZE("average-size"),
    TEMPERATURE("temperature"),
    IS_PHOTOPHILOUS("is-photophilous"),
    WATER_AMOUNT("water-amount"),
    REPRODUCTION("reproduction"),
    PETALS_QUANTITY("petals-quantity"),
    SIZE("size"),
    IS_POISON("is-poison"),
    WEIGHT("weight"),
    SEASON("season"),
    IS_SWEET("is-sweet");

    private String value;

    PlantEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PlantEnum findByValue(String value){
        for(PlantEnum plantEnum : values()){
            if(plantEnum.getValue().equalsIgnoreCase(value)){
                return plantEnum;
            }
        }
        return null;
    }
}



























