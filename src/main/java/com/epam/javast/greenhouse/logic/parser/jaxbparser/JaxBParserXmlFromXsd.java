package com.epam.javast.greenhouse.logic.parser.jaxbparser;

import com.epam.javast.greenhouse.logic.parser.api.XmlParser;
import com.epam.javast.greenhouse.model.api.Plant;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * JaxB unMarshaller using xsd schema to validate xml File
 */
public class JaxBParserXmlFromXsd implements XmlParser {

    private String schemaLocation;
    private String language;

    public JaxBParserXmlFromXsd(String schemaLocation, String language){
        setSchemaLocation(schemaLocation);
        setLanguage(language);
    }

    public void setLanguage(String language) {
        if(language == null || language.isEmpty()){
            throw new IllegalArgumentException("Language can not be null or empty");
        }
        this.language = language;
    }

    public void setSchemaLocation(String schemaLocation) {
        if(schemaLocation == null || schemaLocation.isEmpty()){
            throw new IllegalArgumentException("Schema Location can not be null or empty");
        }
        this.schemaLocation = schemaLocation;
    }

    @Override
    public List<Plant> plantList(String xmlFile) {
        Plants plants = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Plants.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(language);
            File schemaFile = new File(schemaLocation);
            Schema schema = factory.newSchema(schemaFile);
            unmarshaller.setSchema(schema);
            FileReader reader = new FileReader(xmlFile);
             plants = (Plants) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (SAXException e) {
            e.getLocalizedMessage();
        }
        return plants.getPlants();
    }
}
