package com.epam.javast.greenhouse.logic.saxanalyzer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimplePlantHandler extends DefaultHandler {

    public SimplePlantHandler() {
        super();
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Parsing");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Parsing");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String s = localName;
        for (int i = 0; i < attributes.getLength(); i++) {
            s += " " + attributes.getLocalName(i) + " " + attributes.getValue(i);
        }
        System.out.println(s.trim());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(localName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(new String(ch, start, length));
    }
}
