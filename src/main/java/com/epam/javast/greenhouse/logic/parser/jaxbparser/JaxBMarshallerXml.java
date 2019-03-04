package com.epam.javast.greenhouse.logic.parser.jaxbparser;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * To write the data objects to a xml file
 */
public class JaxBMarshallerXml {

    /**
     *
     * @param context JaxBContext
     * @param object the object or the collection that you need to write to xml file
     * @param xmlFileLocation location of the xml that you need to create with the data
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public void XmlMarsh(JAXBContext context, Object object, String xmlFileLocation) throws JAXBException,
            FileNotFoundException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, new FileOutputStream(xmlFileLocation));

    }
}
