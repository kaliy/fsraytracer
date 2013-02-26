package org.kalimullin.fsraytracer.data;

import org.junit.Test;
import org.kalimullin.fsraytracer.scene.SceneObject;

import java.util.Set;

public class SceneDataXpathParserTest extends SceneParserTest {

    @Test
    public void testParseXml() throws Exception {
        Set<SceneObject> sceneObjectSet = new SceneDataXpathParser(validXml).getSceneObjects();
        assertEquals(pyramidSceneObjectSet, sceneObjectSet);
    }

}
