package com.epam.javast.greenhouse.logic.parser.saxparser;

import com.epam.javast.greenhouse.logic.parser.api.XmlParser;
import com.epam.javast.greenhouse.model.api.Plant;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class XmlSaxParser implements XmlParser {

    private static Logger logger = LogManager.getLogger(XmlSaxParser.class);
    private PlantHandler plantHandler;
    private XMLReader xmlReader;

    public XmlSaxParser(){
        plantHandler = new PlantHandler();
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(plantHandler);
        } catch (SAXException e) {
            logger.error("Exception creating xml reader, e");
        }
    }
    @Override
    public List<Plant> plantList(String xmlFile) {
        try {
            xmlReader.parse(xmlFile);
        } catch (IOException e) {
            logger.error("Exception streaming xml reader, e");
        } catch (SAXException e) {
            logger.error("Exception parsing xml file, e");
        }

        return  plantHandler.getPlantList();
    }
}
