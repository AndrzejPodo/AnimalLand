package main.mapElements;

import main.map.Observer;
import main.structures.FieldImage;
import main.structures.Vector2d;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCreature implements MapElement, Observable {

    protected Vector2d position;
    protected int energy;

    protected List<Observer> observers = new LinkedList<>();

    public AbstractCreature(Vector2d position, int initialEnergy){
        this.position = position;
        this.energy = initialEnergy;
    }

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

    public void setPosition(Vector2d position){
        this.position = position;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }
}
