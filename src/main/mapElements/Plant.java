package main.mapElements;

import main.map.Observer;
import main.structures.Vector2d;

import java.util.LinkedList;
import java.util.List;

public class Plant implements MapElement, Observable{
    List<Observer> observers = new LinkedList<>();

    @Override
    public void update() {

    }

    @Override
    public Vector2d getPosition() {
        return null;
    }

    public int getEnergy(){
        return 1;
    }

    @Override
    public void notifyPositionChange(Vector2d oldPosition) {

    }

    @Override
    public void die() {
        for(Observer observer : observers){
            observer.onDeath(this);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public String toString() {
        return "P";
    }
}
