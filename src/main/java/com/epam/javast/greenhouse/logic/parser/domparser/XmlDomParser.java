package com.epam.javast.greenhouse.logic.parser.domparser;

import com.epam.javast.greenhouse.logic.parser.api.XmlParser;
import com.epam.javast.greenhouse.model.api.Plant;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.javast.greenhouse.model.enumeration.PlantEnum.FLOWER;
import static com.epam.javast.greenhouse.model.enumeration.PlantEnum.VEGETABLE;

public class XmlDomParser implements XmlParser {

    private static Logger logger = LogManager.getLogger(DomPlantBuilder.class);
    private Plant plant;
    private DocumentBuilder documentBuilder;
    private DomPlantBuilder domPlantBuilder;

    public XmlDomParser() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        domPlantBuilder = new DomPlantBuilder();
        try {
            documentBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Parser Configuration Error", e);
        }
    }


    public List<Plant> plantList(String xmlFile) {
        List<Plant> plants = new ArrayList<>();

        try {
            Document document = documentBuilder.parse(xmlFile);
            Element root = document.getDocumentElement();
            NodeList plantList = root.getElementsByTagName(FLOWER.getValue());
            for (int i = 0; i < plantList.getLength(); i++) {
                Element element = (Element) plantList.item(i);
                plant = domPlantBuilder.buildPlant(element);
                plants.add(plant);
            }

            plantList = root.getElementsByTagName(VEGETABLE.getValue());
            for (int i = 0; i < plantList.getLength(); i++) {
                Element element = (Element) plantList.item(i);
                plant = domPlantBuilder.buildPlant(element);
                plants.add(plant);

            }
        } catch (SAXException e) {
            logger.error("Sax Exception in Dom Parser", e);
        } catch (IOException e) {
            logger.error("Streaming Exception in dom Parser", e);
        }

        return plants;
    }
}
