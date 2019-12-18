package main.map;

import main.mapElements.MapElement;
import main.structures.FieldImage;
import main.structures.Vector2d;
import main.visualiser.MapObserver;

import java.io.FileNotFoundException;

public interface ObservableMap {
    void notifyOnFieldChanged(MapElement position) throws FileNotFoundException;
    void addObserver(MapObserver observer);
    void removeObserver(MapObserver observer);
}
