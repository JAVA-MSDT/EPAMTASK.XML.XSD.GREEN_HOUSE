package com.epam.javast.greenhouse.logic.parser;

import com.epam.javast.greenhouse.logic.PlantObjectHolder;
import com.epam.javast.greenhouse.logic.parser.staxparser.XmlStaxParser;
import com.epam.javast.greenhouse.model.api.Plant;
import com.epam.javast.greenhouse.model.entity.Flower;
import com.epam.javast.greenhouse.model.entity.Vegetable;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class XmlStaxParserTest {
    private static final String XML_FILE = "xml/plants.xml";
    private static XmlStaxParser xmlStaxParser;
    private static List<Plant> plantList;
    private static PlantObjectHolder plantObjectHolder;
    private Flower flower;
    private Vegetable vegetable;
    private static List<Plant> testingPlant;

    @BeforeClass
    public static void init(){
        xmlStaxParser = new XmlStaxParser();
        plantList = xmlStaxParser.plantList(XML_FILE);
        plantObjectHolder = new PlantObjectHolder();
        testingPlant = new ArrayList<>();
    }

    @AfterClass
    public static void close(){
        xmlStaxParser = null;
        plantList = null;
        plantObjectHolder = null;
    }

    @Test
    public void domParserPlantListPass(){
        flower = plantObjectHolder.getValidFlower();
        vegetable = plantObjectHolder.getValidVegetable();
        testingPlant.add(flower);
        testingPlant.add(vegetable);

        Assert.assertEquals(testingPlant, plantList);

    }


    @Test
    public void domParserPlantListFail(){
        flower = plantObjectHolder.getInValidFlower();
        vegetable = plantObjectHolder.getInValidVegetable();
        testingPlant.add(flower);
        testingPlant.add(vegetable);

        Assert.assertEquals(testingPlant, plantList);
    }
}
