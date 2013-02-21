package org.kalimullin.fsraytracer.data;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import java.io.File;

public class SceneParserTest extends Assert {

    private SceneParser sceneParser;
    private File invalidXml;
    private File validXml;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this.sceneParser = new SceneParser();
        this.invalidXml = new File("src/test/resources/invalid.xml");
        this.validXml = new File("src/test/resources/simpleScene.xml");
    }

    @Test
    public void testInvalidXml() throws Exception {
        expectedException.expect(SAXException.class);
        sceneParser.getParsedDocument(invalidXml);
    }

    @Test
    public void testValidXml() throws Exception {
        assertNotEquals(new DOMParser().getDocument(), sceneParser.getParsedDocument(validXml));
    }

}
