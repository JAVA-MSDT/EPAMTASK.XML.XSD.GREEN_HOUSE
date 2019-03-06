package com.epam.javast.greenhouse.logic.parser.staxparser;

import com.epam.javast.greenhouse.logic.parser.api.XmlParser;
import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.entity.Flower;
import com.epam.javast.greenhouse.model.entity.GrowingTips;
import com.epam.javast.greenhouse.model.entity.Vegetable;
import com.epam.javast.greenhouse.model.entity.VisualParameter;
import com.epam.javast.greenhouse.model.enumeration.*;

import static com.epam.javast.greenhouse.model.enumeration.PlantEnum.*;

import com.epam.javast.greenhouse.util.helper.StringFromEnum;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class XmlStaxParser implements XmlParser {

    private static Logger logger = LogManager.getLogger(XmlStaxParser.class);
    private XMLInputFactory inputFactory;
    private XMLStreamReader streamReader;
    private Plant plant;
    private VisualParameter visualParameter;
    private GrowingTips growingTips;
    private List<Plant> plantList;
    private FileInputStream inputStream;

    public XmlStaxParser() {
        inputFactory = XMLInputFactory.newInstance();
        plantList = new ArrayList<>();
    }

    @Override
    public List<Plant> plantList(String xmlFile) {

        String name;

        try {
            inputStream = new FileInputStream(new File(xmlFile));
            streamReader = inputFactory.createXMLStreamReader(inputStream);
            while (streamReader.hasNext()) {
                int type = streamReader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = streamReader.getLocalName();
                    if (findByValue(name) == FLOWER || findByValue(name) == VEGETABLE) {
                        plant = buildPlant(streamReader, name);
                        plantList.add(plant);
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("Xml Steam Exception in Stax parser", e);
        } catch (FileNotFoundException e) {
            logger.error("File not found in Stax parser", e);
        }
        return plantList;
    }

    public Plant buildPlant(XMLStreamReader reader, String plantType) throws XMLStreamException {

        if (FLOWER.getValue().equalsIgnoreCase(plantType)) {
            plant = new Flower();
            visualParameter = new VisualParameter();
            growingTips = new GrowingTips();
        } else if (VEGETABLE.getValue().equalsIgnoreCase(plantType)) {
            plant = new Vegetable();
            visualParameter = new VisualParameter();
            growingTips = new GrowingTips();
        }

        plant.setId(Integer.parseInt(reader.getAttributeValue(null, ID.getValue())));
        plant.setName(reader.getAttributeValue(null, NAME.getValue()));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (Objects.requireNonNull(findByValue(name))) {
                        case SOIL:
                            String soil = getValueFromElement(reader);
                            plant.setSoil(Soil.valueOf(StringFromEnum.getString(Soil.values(), soil)));
                            break;
                        case ORIGIN:
                            String origin = getValueFromElement(reader);
                            plant.setOrigin(Origin.valueOf(StringFromEnum.getString(Origin.values(), origin)));
                            break;
                        case VISUAL_PARAMETER:
                            plant.setVisualParameter(getVisualParameter(reader));
                            break;
                        case GROWING_TIPS:
                            plant.setGrowingTips(getGrowingTips(reader));
                            break;
                        case REPRODUCTION:
                            String reproduction = getValueFromElement(reader);
                            plant.setReproduction(Reproduction.valueOf(StringFromEnum.getString(Reproduction.values(), reproduction)));
                            break;
                        case PETALS_QUANTITY:
                            String petalsQuantity = getValueFromElement(reader);
                            ((Flower) plant).setPetalsQuantity(Integer.parseInt(petalsQuantity));
                            break;
                        case SIZE:
                            String size = getValueFromElement(reader);
                            ((Flower) plant).setSize(Size.valueOf(StringFromEnum.getString(Size.values(), size)));
                            break;
                        case IS_POISON:
                            String isPoison = getValueFromElement(reader);
                            ((Flower) plant).setPoison(Boolean.parseBoolean(isPoison));
                            break;
                        case WEIGHT:
                            String weight = getValueFromElement(reader);
                            ((Vegetable) plant).setWeight(Double.parseDouble(weight));
                            break;
                        case SEASON:
                            String season = getValueFromElement(reader);
                            ((Vegetable) plant).setSeason(Season.valueOf(StringFromEnum.getString(Season.values(), season)));
                            break;
                        case IS_SWEET:
                            String isSweet = getValueFromElement(reader);
                            ((Vegetable) plant).setSweet(Boolean.parseBoolean(isSweet));

                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (findByValue(name) == FLOWER || findByValue(name) == VEGETABLE) {
                        return plant;
                    }
                    break;
            }
        }
        throw new XMLStreamException("There is no such elements");
    }


    private GrowingTips getGrowingTips(XMLStreamReader reader) throws XMLStreamException {
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (Objects.requireNonNull(findByValue(name))) {
                        case TEMPERATURE:
                            growingTips.setTemperature(Double.parseDouble(getValueFromElement(reader)));
                            break;
                        case IS_PHOTOPHILOUS:
                            growingTips.setPhotophilous(Boolean.parseBoolean(getValueFromElement(reader)));
                            break;
                        case WATER_AMOUNT:
                            growingTips.setWaterAmount(Integer.parseInt(getValueFromElement(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (findByValue(name) == GROWING_TIPS) {
                        return growingTips;
                    }
                    break;
            }
        }
        throw new XMLStreamException("there is no such element");
    }

    private VisualParameter getVisualParameter(XMLStreamReader reader) throws XMLStreamException {
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (Objects.requireNonNull(findByValue(name))) {
                        case STALK_COLOR:
                            String stalkColor = getValueFromElement(reader);
                            visualParameter.setStalkColor(Color.valueOf(StringFromEnum.getString(Color.values(), stalkColor)));
                            break;
                        case LEAF_COLOR:
                            String leafColor = getValueFromElement(reader);
                            visualParameter.setLeafColor(Color.valueOf(StringFromEnum.getString(Color.values(), leafColor)));
                            break;
                        case AVERAGE_SIZE:
                            visualParameter.setAverageSize(Double.parseDouble(getValueFromElement(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (findByValue(name) == VISUAL_PARAMETER) {
                        return visualParameter;
                    }
                    break;
            }
        }
        throw new XMLStreamException("there is nop such element");

    }


    private String getValueFromElement(XMLStreamReader reader) throws XMLStreamException {
        String text = "";
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
