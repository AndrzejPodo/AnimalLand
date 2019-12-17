package main.visualiser;

import main.structures.FieldType;
import main.structures.MyPane;
import main.structures.Vector2d;


public class FXVisualizer implements MapObserver{

    private MyPane pane;

    public FXVisualizer(MyPane pane) {
        this.pane = pane;
    }

    @Override
    public void onFiledChanged(Vector2d position, FieldType newType){
        this.pane.updateNode(position, newType);
    }
}
