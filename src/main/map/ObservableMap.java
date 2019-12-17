package main.map;

import main.structures.FieldType;
import main.structures.Vector2d;
import main.visualiser.MapObserver;

import java.io.FileNotFoundException;

public interface ObservableMap {
    void notifyOnFieldChanged(Vector2d position, FieldType newType) throws FileNotFoundException;
    void addObserver(MapObserver observer);
    void removeObserver(MapObserver observer);
}
