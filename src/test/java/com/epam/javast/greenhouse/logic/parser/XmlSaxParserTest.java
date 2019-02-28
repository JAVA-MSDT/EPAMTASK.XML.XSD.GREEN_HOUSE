package com.epam.javast.greenhouse.logic.parser;

import com.epam.javast.greenhouse.logic.PlantObjectHolder;
import com.epam.javast.greenhouse.logic.parser.saxparser.XmlSaxParser;
import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.entity.Flower;
import com.epam.javast.greenhouse.model.entity.Vegetable;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class XmlSaxParserTest {

    private static final String XML_FILE = "xml/plants.xml";
    private static XmlSaxParser xmlSaxParser;
    private static List<Plant> plantList;
    private static PlantObjectHolder plantObjectHolder;
    private Flower flower;
    private Vegetable vegetable;
    private static List<Plant> testingPlant;

    @BeforeClass
    public static void init(){
        xmlSaxParser = new XmlSaxParser();
        plantList = xmlSaxParser.plantList(XML_FILE);
        plantObjectHolder = new PlantObjectHolder();
        testingPlant = new ArrayList<>();
    }

    @AfterClass
    public static void close(){
        xmlSaxParser = null;
        plantList = null;
        plantObjectHolder = null;
    }

    @Test
    public void saxParserPlantListPass(){
        flower = plantObjectHolder.getValidFlower();
        vegetable = plantObjectHolder.getValidVegetable();
        testingPlant.add(flower);
        testingPlant.add(vegetable);

        Assert.assertEquals(testingPlant, plantList);

    }


    @Test
    public void saxParserPlantListFail(){
        flower = plantObjectHolder.getInValidFlower();
        vegetable = plantObjectHolder.getInValidVegetable();
        testingPlant.add(flower);
        testingPlant.add(vegetable);

        Assert.assertEquals(testingPlant, plantList);
    }

}
