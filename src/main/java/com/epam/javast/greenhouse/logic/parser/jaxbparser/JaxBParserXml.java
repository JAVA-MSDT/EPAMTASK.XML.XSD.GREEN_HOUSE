package com.epam.javast.greenhouse.logic.parser.jaxbparser;

import com.epam.javast.greenhouse.logic.parser.api.XmlParser;
import com.epam.javast.greenhouse.model.api.Plant;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * JaxB UnMarshaller xml file without validating it against Xsd file
 */
public class JaxBParserXml implements XmlParser {
    @Override
    public List<Plant> plantList(String xmlFile) {
        Plants plants = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Plants.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            FileReader reader = new FileReader(xmlFile);
            plants = (Plants) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return plants.getPlants();
    }
}
