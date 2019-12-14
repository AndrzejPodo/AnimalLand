package main;

import main.config.WorldParams;
import main.internalOrgans.Genes;
import main.map.IMap;
import main.map.WorldMap;
import main.mapElements.Animal;
import main.mechanics.Mechanics;
import main.structures.Vector2d;

import java.util.Random;

public class World {
    public static void main(String[] args) throws InterruptedException {

        IMap map = new WorldMap(WorldParams.getInstance().mapWidth, WorldParams.getInstance().mapHeight);
        Mechanics mechanics = new Mechanics(map);

        Animal myAnimal = new Animal(new Genes(), new Vector2d(35,15), 100);
        map.placeElement(myAnimal);
        Random rnd = new Random();
        int x;
        int y;
        for(int i = 0; i< 4; i++){
            mechanics.seedAPlant();
        }
        for(int i= 0; i < 100; i++){
            x = rnd.nextInt(WorldParams.getInstance().mapWidth);
            y = rnd.nextInt(WorldParams.getInstance().mapHeight);
            map.placeElement(new Animal(new Genes(), new Vector2d(x,y), 40));
        }

        for(int i = 0; i < 10000; i++){
            System.out.println(myAnimal.getEnergy());
            System.out.println(map);
            mechanics.update();
            Thread.sleep(10,0);
            System.out.flush();
        }
    }
}
