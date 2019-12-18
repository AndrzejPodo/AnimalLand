package main.visualiser;

import main.mapElements.MapElement;
import main.structures.FieldImage;
import main.structures.Vector2d;

public interface MapObserver {
    void onFiledChanged(MapElement element, FieldImage newType);
}
