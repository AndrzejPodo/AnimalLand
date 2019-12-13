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
    public static void main(String[] args) {
//        Animal animal1 = new Animal(new Genes(), new Vector2d(5,5), 100);
//        Animal animal2 = new Animal(new Genes(), new Vector2d(5,5),100);
//
//        Animal animal3 = animal1.reproduce(animal2);
//        Animal animal4 = animal2.reproduce(animal1);
//
//        LinkedList<Animal> linkedList = new LinkedList<>();
//        linkedList.push(animal3);
//        linkedList.push(animal4);
//
//        for(int i = 0; i < 10; i++){
//            animal3.update();
//            System.out.println(animal3);
//        }

        IMap map = new WorldMap(WorldParams.getInstance().mapWidth, WorldParams.getInstance().mapHeight);
        Mechanics mechanics = new Mechanics(map);
        Animal animal1 = new Animal(new Genes(), new Vector2d(5,5), 10);
        Animal animal2 = new Animal(new Genes(), new Vector2d(4,4),10);
        map.placeElement(animal1);
        map.placeElement(animal2);

        for(int i = 0; i < 13; i++){
            System.out.println(map);
            mechanics.update();
        }
    }
}
