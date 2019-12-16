package main.simulation;

import main.config.WorldParams;
import main.internalOrgans.Genes;
import main.map.IMap;
import main.map.WorldMap;
import main.mapElements.Animal;
import main.mechanics.Mechanics;
import main.structures.Vector2d;

import java.util.Random;

public class Simulation implements Runnable {
    private IMap map;
    private Mechanics mechanics;
    private final int duration;
    private final int refreshing;

    public Simulation(int duration, int initialAmountAnimals, int initialAmountPlants, int refreshing){
        this.refreshing = refreshing;
        this.duration = duration;
        int x,y;
        Random random = new Random();
        map = new WorldMap(WorldParams.getInstance().getMapWidth(), WorldParams.getInstance().getMapHeight());
        mechanics = new Mechanics(map);
        for(int i = 0; i< initialAmountPlants; i++){
            mechanics.seedAPlant();
        }
        for(int i= 0; i < initialAmountAnimals; i++){
            x = random.nextInt(WorldParams.getInstance().getMapWidth());
            y = random.nextInt(WorldParams.getInstance().getMapHeight());
            map.placeElement(new Animal(new Genes(), new Vector2d(x,y), 40));
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < duration; i++){
            mechanics.update();
            System.out.println(map);
            try {
                Thread.sleep(refreshing,0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
