package com.epam.javast.greenhouse.util.xmlvalidator;

import com.epam.javast.greenhouse.util.xmlvalidator.api.XmlValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class SaxXsdValidator implements XmlValidator {
    private static Logger logger = LogManager.getLogger(SaxXsdValidator.class);

    @Override
    public boolean validate(String xmlFile, String schemaFile, String schemaLanguage) {


        try {
            SchemaFactory factory = SchemaFactory.newInstance(schemaLanguage);
            Schema schema = factory.newSchema(new File(schemaFile));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(xmlFile));
            validator.validate(source);
        } catch (SAXException e) {
           e.printStackTrace();
           return false;

        } catch (IOException e) {
            logger.error("IOException");
            e.printStackTrace();
            return false;
        }

            return true;
        }
}
