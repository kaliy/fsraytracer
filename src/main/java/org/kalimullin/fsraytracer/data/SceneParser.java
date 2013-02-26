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

}
