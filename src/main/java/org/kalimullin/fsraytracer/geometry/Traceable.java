package org.kalimullin.fsraytracer.geometry;

import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

/**
 * Any object that can be traced (ball, polygon, dot, etc.)
 */
public interface Traceable {
    /**
     * Calculating ray and traceable object intersection point
     * @param ray Ray which should checked for intersection
     * @return HitPoint with intersection data. HitPoint.MISS if there is no intersection
     */
    public HitPoint getHitPoint(Ray ray);
}
