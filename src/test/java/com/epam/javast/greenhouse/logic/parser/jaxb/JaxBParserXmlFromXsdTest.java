package com.epam.javast.greenhouse.logic.parser.jaxb;

import com.epam.javast.greenhouse.logic.PlantObjectHolder;
import com.epam.javast.greenhouse.logic.parser.jaxbparser.JaxBParserXmlFromXsd;
import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.entity.Flower;
import com.epam.javast.greenhouse.model.entity.Vegetable;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.XMLConstants;
import java.util.ArrayList;
import java.util.List;

public class JaxBParserXmlFromXsdTest {
    private static final String XML_FILE = "xml/plants.xml";
    private static final String XSD_SCHEMA = "xml/plants.xsd";
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static JaxBParserXmlFromXsd jaxBParser;
    private static List<Plant> plantList;
    private static PlantObjectHolder plantObjectHolder;
    private Flower flower;
    private Vegetable vegetable;
    private static List<Plant> testingPlant;

    @BeforeClass
    public static void init(){
        jaxBParser = new JaxBParserXmlFromXsd(XSD_SCHEMA, LANGUAGE);
        plantList = jaxBParser.plantList(XML_FILE);
        plantObjectHolder = new PlantObjectHolder();
        testingPlant = new ArrayList<>();
    }

    @AfterClass
    public static void close(){
        jaxBParser = null;
        plantList = null;
        plantObjectHolder = null;
    }

    @Test
    public void jaxBParserPlantListPass(){
        flower = plantObjectHolder.getValidFlower();
        vegetable = plantObjectHolder.getValidVegetable();
        testingPlant.add(flower);
        testingPlant.add(vegetable);

        Assert.assertEquals(testingPlant, plantList);

    }


    @Test
    public void jaxBParserPlantListFail(){
        flower = plantObjectHolder.getInValidFlower();
        vegetable = plantObjectHolder.getInValidVegetable();
        testingPlant.add(flower);
        testingPlant.add(vegetable);

        Assert.assertEquals(testingPlant, plantList);
    }
}
