package org.kalimullin.fsraytracer;

import org.kalimullin.fsraytracer.data.SceneDataProvider;
import org.kalimullin.fsraytracer.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Kernel {
    private static Logger logger = LoggerFactory.getLogger(Kernel.class);
    public static void main(String[] args) {
        File sceneFile = new File(args[0]);
        Scene scene = new Scene();
        scene.setSceneObjects(new SceneDataProvider(sceneFile).getSceneObjects());
        RayTracer.getInstance().setScene(scene);
        RayTracer.getInstance().startListening(new BufferedReader(new InputStreamReader(System.in)));
    }
}
