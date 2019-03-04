package com.epam.javast.greenhouse.logic.parser.jaxb;

import com.epam.javast.greenhouse.logic.PlantObjectHolder;
import com.epam.javast.greenhouse.logic.parser.jaxbparser.JaxBMarshallerXml;
import com.epam.javast.greenhouse.logic.parser.jaxbparser.Plants;
import com.epam.javast.greenhouse.model.entity.Flower;
import com.epam.javast.greenhouse.model.entity.Vegetable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class JaxBMarshallerXmlTest {
    /**
     * To test this method copy it to the main method or add main method here then check the
     * xml file which will be created in the specified location
     */
    public void XmlMarshTest(){
        try {
            JaxBMarshallerXml marshallerXml = new JaxBMarshallerXml();
            JAXBContext context = JAXBContext.newInstance(Plants.class);
            Plants plants = new Plants();
            PlantObjectHolder plantObjectHolder = new PlantObjectHolder();
            Flower flower = plantObjectHolder.getValidFlower();
            Vegetable vegetable = plantObjectHolder.getValidVegetable();
            plants.addPlant(flower);
            plants.addPlant(vegetable);
            marshallerXml.XmlMarsh(context, plants, "xml/newPlants.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}
