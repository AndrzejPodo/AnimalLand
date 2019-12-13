package main.mapElements;

import main.map.Observer;
import main.structures.Vector2d;

public interface Observable {
    void notifyPositionChange(Vector2d oldPosition);
    void die();
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
