package org.kalimullin.fsraytracer;

import org.kalimullin.fsraytracer.ray.HitData;
import org.kalimullin.fsraytracer.ray.Ray;
import org.kalimullin.fsraytracer.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class RayTracer {
    private static RayTracer ourInstance = new RayTracer();
    private static Logger logger = LoggerFactory.getLogger(RayTracer.class);
    private Scene scene = new Scene();

    public static RayTracer getInstance() {
        return ourInstance;
    }

    private RayTracer() {
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void startListening(BufferedReader inputStreamReader) {
        try {
            String line;
            while ((line = inputStreamReader.readLine()) != null) {
                try {
                    Ray ray = Ray.getRayFromString(line);
                    HitData hitData = getScene().traceRay(Ray.getRayFromString(line));
                    System.out.println(ray.getId() + ":" + (!HitData.MISS.equals(hitData) ? hitData.getSceneObject().getName() : ""));
                } catch (Exception e) {
                    logger.error("Exception while execution:", e);
                }
            }
        } catch (IOException e) {
            //TODO better error handling
            logger.error("IOException while listening stdin");
        }
    }

}
