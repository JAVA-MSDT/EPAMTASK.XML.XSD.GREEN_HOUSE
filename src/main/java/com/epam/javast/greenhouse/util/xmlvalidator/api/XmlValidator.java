package com.epam.javast.greenhouse.util.xmlvalidator.api;

public interface XmlValidator {
    boolean validate(String xmlFile, String schemaFile, String schemaLanguage);
}
