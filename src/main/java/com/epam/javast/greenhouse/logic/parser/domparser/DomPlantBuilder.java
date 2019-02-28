package com.epam.javast.greenhouse.logic.parser.domparser;

import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.entity.*;
import com.epam.javast.greenhouse.model.enumeration.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import static com.epam.javast.greenhouse.model.enumeration.PlantEnum.*; /* Importing PlantEnum to clean the code from
                                                                       Literal Typing each element */

public class DomPlantBuilder {

    private static Logger logger = LogManager.getLogger(DomPlantBuilder.class);
    private Plant plant;
    private VisualParameter visualParameter;
    private GrowingTips growingTips;

    public DomPlantBuilder() {
    }

    /**
     *
     * @param plantElement elements of the plant inside the xml file
     * @return plant depending on the plant elements
     */
    public Plant buildPlant(Element plantElement) {

        buildSubPlant(plantElement);

        plant.setId(Integer.parseInt(plantElement.getAttribute(ID.getValue())));
        String plantName = plantElement.getAttribute(NAME.getValue());
        if (plantName != null) {
            plant.setName(plantName);
        } else {
            plant.setName("Default Plant");
        }

        String plantSoil = getValueFromElement(plantElement, SOIL.getValue());
        for (Soil soil : Soil.values()) {
            if (soil.name().equalsIgnoreCase(plantSoil)) {
                plant.setSoil(Soil.valueOf(toUpperCase(plantSoil)));
            }
        }
        String plantOrigin = getValueFromElement(plantElement, ORIGIN.getValue());
        for (Origin origin : Origin.values()) {
            if (origin.name().equalsIgnoreCase(plantOrigin)) {
                plant.setOrigin(Origin.valueOf(toUpperCase(plantOrigin)));
            }
        }

        Element vParameter = (Element) plantElement.getElementsByTagName(VISUAL_PARAMETER.getValue()).item(0);
        String stalkColor = getValueFromElement(vParameter, STALK_COLOR.getValue());
        for (Color color : Color.values()) {
            if (color.name().equalsIgnoreCase(stalkColor)) {
                visualParameter.setStalkColor(Color.valueOf(toUpperCase(stalkColor)));
            }
        }
        String leafColor = getValueFromElement(vParameter, LEAF_COLOR.getValue());
        for (Color color : Color.values()) {
            if (color.name().equalsIgnoreCase(stalkColor)) {
                visualParameter.setLeafColor(Color.valueOf(toUpperCase(leafColor)));
            }
        }

        visualParameter.setAverageSize(getDoubleFromElement(vParameter, AVERAGE_SIZE.getValue()));

        Element gTips = (Element) plantElement.getElementsByTagName(GROWING_TIPS.getValue()).item(0);
        growingTips.setTemperature(getDoubleFromElement(gTips, TEMPERATURE.getValue()));
        growingTips.setPhotophilous(getBooleanFromElement(gTips, IS_PHOTOPHILOUS.getValue()));
        growingTips.setWaterAmount(getIntFromElement(gTips, WATER_AMOUNT.getValue()));

        String reproductionPlant = getValueFromElement(plantElement, REPRODUCTION.getValue());
        for (Reproduction reproduction : Reproduction.values()) {
            if (reproduction.name().equalsIgnoreCase(reproductionPlant)) {
                plant.setReproduction(Reproduction.valueOf(toUpperCase(reproductionPlant)));
            }
        }

        return plant;
    }

    /**
     * it is just a helper method to clear and clean the code for readability and dividing the responsibility
     * of the methods
     * @param plantElement elements of the plant inside the xml file
     */
    private void buildSubPlant(Element plantElement){

        if (FLOWER.getValue().equals(plantElement.getTagName())) {
            plant = new Flower();
            visualParameter = new VisualParameter();
            growingTips = new GrowingTips();
            int petalsQuantity = Integer.parseInt(getValueFromElement(plantElement, PETALS_QUANTITY.getValue()));
            ((Flower) plant).setPetalsQuantity(petalsQuantity);

            String flowerSize = getValueFromElement(plantElement, SIZE.getValue());
            if (flowerSize == null) {
                throw new NullPointerException("Size of the flower can not be null");
            }
            for (Size s : Size.values()) {
                if (s.name().equalsIgnoreCase(flowerSize)) {
                    ((Flower) plant).setSize(Size.valueOf(toUpperCase(flowerSize)));
                    break;
                }
            }
            ((Flower) plant).setPoison(getBooleanFromElement(plantElement, IS_POISON.getValue()));

        } else if (VEGETABLE.getValue().equals(plantElement.getTagName())) {
            plant = new Vegetable();
            visualParameter = new VisualParameter();
            growingTips = new GrowingTips();
            ((Vegetable) plant).setWeight(getDoubleFromElement(plantElement, WEIGHT.getValue()));

            String vegetableSeason = getValueFromElement(plantElement, SEASON.getValue());
            if (vegetableSeason == null) {
                throw new NullPointerException("Vegetable Season can not be null");
            }

            for (Season season : Season.values()) {
                if (season.name().equalsIgnoreCase(vegetableSeason)) {

                    ((Vegetable) plant).setSeason(Season.valueOf(toUpperCase(vegetableSeason)));
                }
            }

            ((Vegetable) plant).setSweet(getBooleanFromElement(plantElement, IS_SWEET.getValue()));
        }
        plant.setVisualParameter(visualParameter);
        plant.setGrowingTips(growingTips);
    }

    /**
     *
     * @param element from the nodeList
     * @param elementName name of that element
     * @return text from the nodeList depending on it is name.
     */
    private String getValueFromElement(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }


     // Helper Methods..............

    /**
     *
     * @param element from the nodeList
     * @param elementName string to parse it to boolean value
     * @return boolean from string
     */
    private boolean getBooleanFromElement(Element element, String elementName) {
        return Boolean.parseBoolean(getValueFromElement(element, elementName));
    }

    /**
     *
     * @param element from the nodeList
     * @param elementName string to parse it to Double value
     * @return double value from String
     */
    private double getDoubleFromElement(Element element, String elementName) {
        return Double.parseDouble(getValueFromElement(element, elementName));
    }

    /**
     *
     * @param element from the nodeList
     * @param elementName string to parse it to int value
     * @return int value from String
     */
    private int getIntFromElement(Element element, String elementName) {
        return Integer.parseInt(getValueFromElement(element, elementName));
    }

    // Experimental method to try to check if we can use enum as parameter
    private String getColor(String colour) {
        String col = "" ;
        for (Color color : Color.values()) {
            if (color.name().equalsIgnoreCase(colour)) {
                col = colour;
            }
        }
        return toUpperCase(col);
    }

    /**
     *
     * @param lowerCase string value
     * @return String to upper case
     */
    private String toUpperCase(String lowerCase) {
        return lowerCase.toUpperCase();
    }

}
