package main.map;

import main.mapElements.MapElement;
import main.structures.Vector2d;

import java.util.List;

public interface IMap {

    List<List<MapElement>> getPopulatedFields();

    List<MapElement> getElements();

    void placeElement(MapElement element);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);
}
