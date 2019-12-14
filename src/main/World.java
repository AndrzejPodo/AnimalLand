package main;

import main.config.WorldParams;
import main.internalOrgans.Genes;
import main.map.IMap;
import main.map.WorldMap;
import main.mapElements.Animal;
import main.mechanics.Mechanics;
import main.structures.Vector2d;

import java.util.LinkedList;

public class World {
    public static void main(String[] args) throws InterruptedException {

        IMap map = new WorldMap(WorldParams.getInstance().mapWidth, WorldParams.getInstance().mapHeight);
        Mechanics mechanics = new Mechanics(map);
        Animal animal1 = new Animal(new Genes(), new Vector2d(5,5), 100);
        Animal animal2 = new Animal(new Genes(), new Vector2d(4,4),100);
        Animal animal3 =new Animal(new Genes(), new Vector2d(5,5), 100);
        Animal animal4 = new Animal(new Genes(), new Vector2d(4,4), 100);
        Animal animal5 = new Animal(new Genes(), new Vector2d(5,5), 100);
        Animal animal6 = new Animal(new Genes(), new Vector2d(4,4),100);
        Animal animal7 =new Animal(new Genes(), new Vector2d(5,5), 100);
        Animal animal8 = new Animal(new Genes(), new Vector2d(4,4), 100);
        map.placeElement(animal1);
        map.placeElement(animal2);
        map.placeElement(animal3);
        map.placeElement(animal4);
        map.placeElement(animal5);
        map.placeElement(animal6);
        map.placeElement(animal7);
        map.placeElement(animal8);

        for(int i = 0; i < 1000; i++){
            System.out.println(map.getElements().size());
            System.out.println(map);
            mechanics.update();
            Thread.sleep(500,0);
        }
    }
}
