package main.simulation;

import main.World;
import main.config.WorldParams;
import main.internalOrgans.Genes;
import main.map.IMap;
import main.map.WorldMap;
import main.mapElements.Animal;
import main.mechanics.Mechanics;
import main.structures.Vector2d;
import main.visualiser.FXVisualizer;

import java.util.Random;

public class Simulation {
    private WorldMap map;
    private Mechanics mechanics;
    private int duration;
    private boolean finished;
    private boolean isRunning;

    public Simulation(int duration, int initialAmountAnimals, int initialAmountPlants){
        init(duration, initialAmountAnimals, initialAmountPlants);
    }

    public Simulation(int duration, int initialAmountAnimals, int initialAmountPlants, FXVisualizer visualizer){
        map = new WorldMap(WorldParams.getInstance().getMapWidth(), WorldParams.getInstance().getMapHeight());
        map.addObserver(visualizer);
        init(duration, initialAmountAnimals, initialAmountPlants);
    }

    private void init(int duration, int initialAmountAnimals, int initialAmountPlants){
        this.duration = duration;
        this.isRunning = true;
        this.finished = false;
        int x,y;
        Random random = new Random();
        mechanics = new Mechanics(map);
        for(int i = 0; i< initialAmountPlants; i++){
            mechanics.seedAPlant();
        }
        for(int i= 0; i < initialAmountAnimals; i++){
            x = random.nextInt(WorldParams.getInstance().getMapWidth());
            y = random.nextInt(WorldParams.getInstance().getMapHeight());
            map.placeElement(new Animal(new Genes(), new Vector2d(x,y), WorldParams.getInstance().getStartEnergy()));
        }
    }

    public boolean isRunning(){
        return this.isRunning;
    }

    public void stop(){
        this.isRunning = false;
    }

    public void start(){
        if(duration>0)this.isRunning = true;
    }

    public void update(){
        duration--;
        mechanics.update();
        if(duration == 0){
            isRunning = false;
            finished = true;
        }
    }

    public boolean isFinished() {
        return finished;
    }

}
