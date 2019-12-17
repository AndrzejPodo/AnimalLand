package main.visualiser;

import main.structures.FieldType;
import main.structures.Vector2d;

import java.io.FileNotFoundException;

public interface MapObserver {
    void onFiledChanged(Vector2d position, FieldType newType);
}
