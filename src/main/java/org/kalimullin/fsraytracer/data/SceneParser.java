package org.kalimullin.fsraytracer.data;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

/**
 * Scene XML parser.
 * The main idea of this class is to split parser and data provider.
 * @see org.kalimullin.fsraytracer.data.SceneDataProvider
 */
public class SceneParser {

    Logger logger = LoggerFactory.getLogger(SceneParser.class);

    /**
     * Validate XML file using XSD.
     * @param file xml
     * @throws SAXException if file is not valid
     */
    private void validate(File file) throws SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("src/main/resources/scene.xsd"));
        Validator validator = schema.newValidator();
        try {
            // Actually double-creating StreamSource can cause performance problems while parsing really big files.
            // If this is really big deal, this method should be inlined
            validator.validate(new StreamSource(file));
        } catch (IOException e) {
            // TODO somehow get rid of IOException by refactoring all Source-related operations
            logger.error("IOException while parsing XML: ", e);
        }
    }

    /**
     * Parsing Scene XML with validation.
     * @param file input XML
     * @return parsed Document
     * @throws SAXException if XML is invalid
     */
    public Document getParsedDocument(File file) throws SAXException {
        validate(file);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(file);
        } catch (ParserConfigurationException | IOException e) {
            logger.error("Exception while parsing file {}:", file, e);
        }
        return new DOMParser().getDocument();
    }

}
