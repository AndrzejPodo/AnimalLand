package main.mapElements;

import main.structures.Vector2d;

public interface MapElement {
    void update();
    Vector2d getPosition();
}
