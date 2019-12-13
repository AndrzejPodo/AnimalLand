package main.map;

import main.config.WorldParams;
import main.mapElements.Animal;
import main.mapElements.MapElement;
import main.mapElements.Plant;
import main.structures.Vector2d;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class WorldMap implements Observer {

    private int width;
    private int height;
    private List<MapElement>[][] map;
    private HashSet<Vector2d> usedFields = new HashSet<>();

    public WorldMap(int width, int height){
        this.width = width;
        this.height = height;
        map = new LinkedList[width][height];
    }

    public void placeElement(MapElement element){
        map[element.getPosition().x%width][element.getPosition().y%height].add(element);
        usedFields.add(new Vector2d(element.getPosition().x%width, element.getPosition().y%height));
    }

//    public void placePlant(Plant plant){
//        map[plant.getPosition().x%WorldParams.getInstance().mapWidth][plant.getPosition().y%WorldParams.getInstance().mapHeight]
//                .add(plant);
//        usedFields.add(new Vector2d(plant.getPosition().x%WorldParams.getInstance().mapWidth,
//                plant.getPosition().y%WorldParams.getInstance().mapHeight));
//    }

    @Override
    public void onMove(MapElement element, Vector2d oldPosition) {
        map[oldPosition.x][oldPosition.y].remove(element);
        map[element.getPosition().x%width][element.getPosition().y%height].add(element);
    }

    @Override
    public void onDeath(MapElement element) {
        map[element.getPosition().x%width][element.getPosition().y%height].remove(element);
        if(map[element.getPosition().x%width][element.getPosition().y%height].size() == 0){
            usedFields.remove(new Vector2d(element.getPosition().x%width,element.getPosition().y%height));
        }
    }
}
