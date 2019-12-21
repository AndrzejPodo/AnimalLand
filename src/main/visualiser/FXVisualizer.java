package main.visualiser;

import main.mapElements.MapElement;
import main.structures.FieldImage;
import main.structures.MyPane;
import main.structures.Vector2d;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class FXVisualizer implements MapObserver{

    private MyPane pane;


    public FXVisualizer(MyPane pane) {
        this.pane = pane;
    }

    @Override
    public void onFiledChanged(MapElement element, FieldImage newImage){
        this.pane.updateNode(element, newImage);
    }

}
