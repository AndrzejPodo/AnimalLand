package main.mapElements;


import main.structures.FieldImage;
import main.structures.Vector2d;

public class Ground implements MapElement {
    private Vector2d position;

    public Ground(Vector2d position){
        this.position = position;
    }

    @Override
    public void update() {

    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public FieldImage getImage() {
        return FieldImage.GROUND;
    }

    public String toString(){
        return "Ground "+this.position.toString();
    }
}
