package main.mapElements;

import main.map.Observer;
import main.structures.Vector2d;

import java.util.LinkedList;

public abstract class AbstractCreature implements MapElement, Observable {

    protected Vector2d position;
    protected int energy;


    protected LinkedList<Observer> observers;

    public void notifyPositionChange(Vector2d oldPosition){
        for(Observer observer : observers){
            observer.onMove(this, oldPosition);
        }
    }

    public void die(){
        for(Observer observer : observers){
            observer.onDeath(this);
        }
    }

    public void addObserver(Observer observer){
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer){
        this.observers.remove(observer);
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }
}
