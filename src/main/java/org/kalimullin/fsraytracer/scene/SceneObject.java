package org.kalimullin.fsraytracer.scene;

import org.kalimullin.fsraytracer.geometry.Traceable;

/**
 * Marker interface for object that can be placed to scene.
 */
public interface SceneObject extends Traceable {
    public String getName();
}
