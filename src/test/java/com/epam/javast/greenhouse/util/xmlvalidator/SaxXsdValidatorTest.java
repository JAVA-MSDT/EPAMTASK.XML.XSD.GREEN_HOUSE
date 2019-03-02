package com.epam.javast.greenhouse.util.xmlvalidator;

import com.epam.javast.greenhouse.util.xmlvalidator.api.XmlValidator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.XMLConstants;

public class SaxXsdValidatorTest {

    private static XmlValidator xmlValidator;
    private static String validXmlFilePath;
    private static String inValidXmlFilePath;
    private static String xsdFilePath;
    private static String language;

    @BeforeClass
    public static void init(){
        xmlValidator = new SaxXsdValidator();
        validXmlFilePath = "xml/plants.xml";
        inValidXmlFilePath = "xml/fail.xml";
        xsdFilePath = "xml/plants.xsd";
        language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    }

    @AfterClass
    public static void close(){
        xmlValidator = null;
        validXmlFilePath = null;
        inValidXmlFilePath = null;
        xsdFilePath = null;
        language = null;
    }

    @Test
    public void isValidReturnTrue(){
        boolean actual = xmlValidator.validate(validXmlFilePath, xsdFilePath, language);

        Assert.assertTrue(actual);
    }

    @Test
    public void isInValidReturnFalse(){
        boolean actual = xmlValidator.validate(inValidXmlFilePath, xsdFilePath, language);

        Assert.assertFalse(actual);
    }

}
