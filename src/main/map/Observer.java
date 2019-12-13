package main.map;

import main.mapElements.MapElement;
import main.structures.Vector2d;

public interface Observer {
    void onMove(MapElement element, Vector2d oldPosition);
    void onDeath(MapElement element);
}
