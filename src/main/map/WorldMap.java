package main.map;

import main.config.WorldParams;
import main.mapElements.Animal;
import main.mapElements.MapElement;
import main.mapElements.Observable;
import main.mapElements.Plant;
import main.structures.Vector2d;
import main.visualiser.MapVisualizer;

import java.util.*;

public class WorldMap implements Observer, IMap {

    private MapVisualizer visualizer = new MapVisualizer(this);
    private int width;
    private int height;
    private List<MapElement>[][] map;
    private HashSet<Vector2d> usedFields = new HashSet<>();
    private List<MapElement> elements = new LinkedList<>();
    private List<Vector2d> jungle = new LinkedList<>();
    private List<Vector2d> desert = new LinkedList<>();

    public WorldMap(int width, int height){
        this.width = width;
        this.height = height;
        map = new List[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                map[i][j] = new LinkedList<>();
                if( i >= 2*width/8 && i <= 6*(width/8) &&  j >= 2*height/8 && j <= 6*(height/8)){
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
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return map[position.x][position.y].size() != 0;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return map[position.x][position.y].stream().max(Comparator.comparingInt(MapElement::getEnergy)).get();
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
        if(map[oldPosition.x%width][oldPosition.y%height].size() == 0) usedFields.remove(new Vector2d(oldPosition.x%width,oldPosition.y%height));
        usedFields.add(new Vector2d(element.getPosition().x%width,element.getPosition().y%height));
    }

    @Override
    public void onDeath(MapElement element) {
        map[element.getPosition().x%width][element.getPosition().y%height].remove(element);
        elements.remove(element);
        if(map[element.getPosition().x%width][element.getPosition().y%height].size() == 0){
            usedFields.remove(new Vector2d(element.getPosition().x%width,element.getPosition().y%height));
        }
    }

    @Override
    public String toString() {
        return this.visualizer.draw(new Vector2d(0, 0), new Vector2d(this.width-1, this.height-1));
    }
}
