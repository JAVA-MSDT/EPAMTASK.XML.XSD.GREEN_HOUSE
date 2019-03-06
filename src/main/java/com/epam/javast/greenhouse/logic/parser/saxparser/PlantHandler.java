package com.epam.javast.greenhouse.logic.parser.saxparser;

import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.entity.*;
import com.epam.javast.greenhouse.model.enumeration.*;
import com.epam.javast.greenhouse.util.helper.StringFromEnum;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import static com.epam.javast.greenhouse.model.enumeration.PlantEnum.*; /* Importing PlantEnum to clean the code from
                                                                       Literal Typing each element */

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * that class will handle the work with the xml file to parse it using SAX parser.
 */
public class PlantHandler extends DefaultHandler {

    private static Logger logger = LogManager.getLogger(PlantHandler.class);
    private List<Plant> plantList;
    private PlantEnum plantEnum = null;
    private Plant plant = null;
    private EnumSet<PlantEnum> withText;
    private VisualParameter visualParameter;
    private GrowingTips growingTips;

    public PlantHandler() {
        plantList = new ArrayList<>();
        withText = EnumSet.range(PlantEnum.ID, PlantEnum.IS_SWEET);
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (FLOWER.getValue().equalsIgnoreCase(localName) || VEGETABLE.getValue().equalsIgnoreCase(localName)) {

            if (FLOWER.getValue().equalsIgnoreCase(localName)) {
                plant = new Flower();
                visualParameter = new VisualParameter();
                growingTips = new GrowingTips();
            } else if (VEGETABLE.getValue().equalsIgnoreCase(localName)) {
                plant = new Vegetable();
                visualParameter = new VisualParameter();
                growingTips = new GrowingTips();
            }

            plant.setId(Integer.parseInt(attributes.getValue(0)));
            if (attributes.getLength() == 2) {
                plant.setName(attributes.getValue(1));
            } else {
                plant.setName("Default Plant");
            }
            plant.setVisualParameter(visualParameter);
            plant.setGrowingTips(growingTips);

        } else {
            PlantEnum temp = PlantEnum.findByValue(localName);
            if (withText.contains(temp)) {
                plantEnum = temp;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (FLOWER.getValue().equalsIgnoreCase(localName)) {
            plantList.add(plant);
        }
        if(VEGETABLE.getValue().equalsIgnoreCase(localName)){
            plantList.add(plant);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        if (plantEnum != null) {
            switch (plantEnum) {
                case SOIL:
                    plant.setSoil(Soil.valueOf(StringFromEnum.getString(Soil.values(), s)));
                    break;
                case ORIGIN:
                    plant.setOrigin(Origin.valueOf(StringFromEnum.getString(Origin.values(), s)));
                    break;
                case STALK_COLOR:
                    visualParameter.setStalkColor(Color.valueOf(StringFromEnum.getString(Color.values(), s)));
                    break;
                case LEAF_COLOR:
                    visualParameter.setLeafColor(Color.valueOf(StringFromEnum.getString(Color.values(), s)));
                    break;
                case AVERAGE_SIZE:
                    visualParameter.setAverageSize(Double.parseDouble(s));
                    break;
                case TEMPERATURE:
                    growingTips.setTemperature(Double.parseDouble(s));
                    break;
                case IS_PHOTOPHILOUS:
                    growingTips.setPhotophilous(Boolean.parseBoolean(s));
                    break;
                case WATER_AMOUNT:
                    growingTips.setWaterAmount(Integer.parseInt(s));
                    break;
                case REPRODUCTION:
                    plant.setReproduction(Reproduction.valueOf(StringFromEnum.getString(Reproduction.values(), s)));
                    break;
                case PETALS_QUANTITY:
                    ((Flower) plant).setPetalsQuantity(Integer.parseInt(s));
                    break;
                case SIZE:
                    ((Flower) plant).setSize(Size.valueOf(StringFromEnum.getString(Size.values(), s)));
                    break;
                case IS_POISON:
                    ((Flower) plant).setPoison(Boolean.parseBoolean(s));
                    break;
                case WEIGHT:
                    ((Vegetable) plant).setWeight(Double.parseDouble(s));
                    break;
                case SEASON:
                    ((Vegetable) plant).setSeason(Season.valueOf(StringFromEnum.getString(Season.values(), s)));
                    break;
                case IS_SWEET:
                    ((Vegetable) plant).setSweet(Boolean.parseBoolean(s));

                    break;
                default:
                    logger.error("There is no such element");
            }
        }
        plantEnum = null;
    }

}

