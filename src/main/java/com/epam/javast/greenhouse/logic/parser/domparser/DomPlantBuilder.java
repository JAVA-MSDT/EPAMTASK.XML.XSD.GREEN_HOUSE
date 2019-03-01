package com.epam.javast.greenhouse.logic.parser.domparser;

import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.entity.*;
import com.epam.javast.greenhouse.model.enumeration.*;
import com.epam.javast.greenhouse.util.helper.StringFromEnum;
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
        plant.setSoil(Soil.valueOf(StringFromEnum.getString(Soil.values(), plantSoil)));

        String plantOrigin = getValueFromElement(plantElement, ORIGIN.getValue());
        plant.setOrigin(Origin.valueOf(StringFromEnum.getString(Origin.values(), plantOrigin)));

        Element vParameter = (Element) plantElement.getElementsByTagName(VISUAL_PARAMETER.getValue()).item(0);
        String stalkColor = getValueFromElement(vParameter, STALK_COLOR.getValue());
        visualParameter.setStalkColor(Color.valueOf(StringFromEnum.getString(Color.values(), stalkColor)));

        String leafColor = getValueFromElement(vParameter, LEAF_COLOR.getValue());
        visualParameter.setLeafColor(Color.valueOf(StringFromEnum.getString(Color.values(), leafColor)));

        visualParameter.setAverageSize(Double.parseDouble(getValueFromElement(vParameter, AVERAGE_SIZE.getValue())));

        Element gTips = (Element) plantElement.getElementsByTagName(GROWING_TIPS.getValue()).item(0);
        growingTips.setTemperature(Double.parseDouble(getValueFromElement(gTips, TEMPERATURE.getValue())));
        growingTips.setPhotophilous(Boolean.parseBoolean(getValueFromElement(gTips, IS_PHOTOPHILOUS.getValue())));
        growingTips.setWaterAmount(Integer.parseInt(getValueFromElement(gTips, WATER_AMOUNT.getValue())));

        String reproductionPlant = getValueFromElement(plantElement, REPRODUCTION.getValue());
        plant.setReproduction(Reproduction.valueOf(StringFromEnum.getString(Reproduction.values(), reproductionPlant)));

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

            ((Flower) plant).setPetalsQuantity(Integer.parseInt(getValueFromElement(plantElement, PETALS_QUANTITY.getValue())));

            String flowerSize = getValueFromElement(plantElement, SIZE.getValue());
            ((Flower) plant).setSize(Size.valueOf(StringFromEnum.getString(Size.values(), flowerSize)));

            ((Flower) plant).setPoison(Boolean.parseBoolean(getValueFromElement(plantElement, IS_POISON.getValue())));

        } else if (VEGETABLE.getValue().equals(plantElement.getTagName())) {
            plant = new Vegetable();
            visualParameter = new VisualParameter();
            growingTips = new GrowingTips();

            ((Vegetable) plant).setWeight(Double.parseDouble(getValueFromElement(plantElement, WEIGHT.getValue())));

            String vegetableSeason = getValueFromElement(plantElement, SEASON.getValue());
            ((Vegetable) plant).setSeason(Season.valueOf(StringFromEnum.getString(Season.values(), vegetableSeason)));

            ((Vegetable) plant).setSweet(Boolean.parseBoolean(getValueFromElement(plantElement, IS_SWEET.getValue())));
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
}
