package org.kalimullin.fsraytracer.data;

import org.junit.Test;
import org.kalimullin.fsraytracer.scene.SceneObject;

import java.util.Set;

public class SceneDataSaxParserTest extends SceneDataXpathParserTest {

    @Test
    public void testParseXml() throws Exception {
        Set<SceneObject> sceneObjectSet = new SceneDataSAXParser(validXml).getSceneObjects();
        assertEquals(pyramidSceneObjectSet, sceneObjectSet);
    }

}
