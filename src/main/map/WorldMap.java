package main.map;

import main.config.WorldParams;
import main.mapElements.*;
import main.mapElements.Observable;
import main.structures.FieldImage;
import main.structures.Vector2d;
import main.visualiser.MapObserver;
import main.visualiser.MapVisualizer;

import java.util.*;

public class WorldMap implements Observer, IMap, ObservableMap {

    private MapVisualizer visualizer = new MapVisualizer(this);
    private int width;
    private int height;
    private List<MapElement>[][] map;
    private HashSet<Vector2d> usedFields = new HashSet<>();
    private List<MapElement> elements = new LinkedList<>();
    private List<Vector2d> jungle = new LinkedList<>();
    private List<Vector2d> desert = new LinkedList<>();
    private List<MapObserver> observers = new LinkedList<>();

    public WorldMap(int width, int height){
        this.width = width;
        this.height = height;
        map = new List[width][height];
        double ratio = WorldParams.getInstance().getJungleToDesertRatio();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                map[i][j] = new LinkedList<>();
                if( i >= (int)((double)(width)/2 - ((double)(width)*ratio)/2)
                        && i <= (int)((double)(width)/2 + ((double)(width)*ratio)/2)
                        &&  j >= (int)((double)(height)/2 - ((double)(height)*ratio)/2)
                        && j <= (int)((double)(height)/2 + ((double)(height)*ratio)/2)){
                    jungle.add(new Vector2d(i,j));
                }else{
                    desert.add(new Vector2d(i,j));
                }
            }
        }
    }

    public void placeElement(MapElement element){
        if(element instanceof Observable) ((Observable) element).addObserver(this);
        map[element.getPosition().x%width][element.getPosition().y%height].add(element);
        usedFields.add(new Vector2d(element.getPosition().x%width, element.getPosition().y%height));
        elements.add(element);

        updateField(element.getPosition());
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return map[position.x%width][position.y%height].size() != 0;
    }

    @Override
    public MapElement objectAt(Vector2d position) {
        return map[position.x%width][position.y%height].stream().max(Comparator.comparingInt(MapElement::getEnergy)).get();
    }

    @Override
    public List<MapElement> getElements() {
        return new LinkedList<>(this.elements);
    }

    public List<Vector2d> getDesert() {
        return desert;
    }

    public List<Vector2d> getJungle() {
        return jungle;
    }

    public List<List<MapElement>> getPopulatedFields(){
        List<List<MapElement>> fields = new LinkedList<>();
        for(Vector2d v : usedFields){
            fields.add(new LinkedList<>(map[v.x][v.y]));
        }
        return fields;
    }

    @Override
    public void onMove(MapElement element, Vector2d oldPosition) {
        map[oldPosition.x%width][oldPosition.y%height].remove(element);
        map[element.getPosition().x%width][element.getPosition().y%height].add(element);
        if(map[oldPosition.x%width][oldPosition.y%height].size() == 0){
            usedFields.remove(new Vector2d(oldPosition.x%width,oldPosition.y%height));
        }
        usedFields.add(new Vector2d(element.getPosition().x%width,element.getPosition().y%height));

        updateField(element.getPosition());

        updateField(oldPosition);
    }

    @Override
    public void onDeath(MapElement element) {
        map[element.getPosition().x%width][element.getPosition().y%height].remove(element);
        elements.remove(element);
        if(map[element.getPosition().x%width][element.getPosition().y%height].size() == 0){
            usedFields.remove(new Vector2d(element.getPosition().x%width,element.getPosition().y%height));
        }

        updateField(element.getPosition());
    }

    @Override
    public String toString() {
        return this.visualizer.draw(new Vector2d(0, 0), new Vector2d(this.width-1, this.height-1));
    }

    private void updateField(Vector2d position){
        if(isOccupied(position)){
            notifyOnFieldChanged(objectAt(position));
        }else{
            notifyOnFieldChanged(new Ground(position));
        }
    }

    @Override
    public void notifyOnFieldChanged(MapElement element){
        for(MapObserver observer : observers){
            observer.onFiledChanged(element, element.getImage());
        }
    }

    @Override
    public void addObserver(MapObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MapObserver observer) {
        observers.remove(observer);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
