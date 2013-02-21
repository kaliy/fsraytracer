package org.kalimullin.fsraytracer;

import org.kalimullin.fsraytracer.ray.HitData;
import org.kalimullin.fsraytracer.ray.Ray;
import org.kalimullin.fsraytracer.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RayTracer {
    private static RayTracer ourInstance = new RayTracer();
    private static Logger logger = LoggerFactory.getLogger(RayTracer.class);
    private Scene scene = new Scene();

    public static RayTracer getInstance() {
        return ourInstance;
    }

    ExecutorService executorService = Executors.newFixedThreadPool(100);

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
                    traceRay(Ray.getRayFromString(line));
                } catch (Exception e) {
                    logger.error("Exception while execution:", e);
                }
            }
        } catch (IOException e) {
            //TODO better error handling
            logger.error("IOException while listening stdin");
        }
    }

    private void traceRay(final Ray ray) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                HitData hitData = getScene().traceRay(ray);
                System.out.println(ray.getId() + ":" + (!HitData.MISS.equals(hitData) ? hitData.getSceneObject().getName() : ""));
            }
        });
    }

}
