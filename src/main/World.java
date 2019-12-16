package main;

import main.simulation.Simulation;

public class World {
    public static void main(String[] args){
        Simulation simulation1 = new Simulation(1000, 100, 4, 10);
        simulation1.run();
    }
}
