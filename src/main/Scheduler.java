package main;

import main.simulation.Simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Scheduler implements Runnable {

    List<Simulation> simulations;
    int refreshing;
    int duration;

    public Scheduler(int refreshing){
        simulations = new LinkedList<>();
        this.refreshing = refreshing;
    }

    public void addSimulation(Simulation simulation){
        simulations.add(simulation);
    }

    @Override
    public void run() {
        while (simulations.size() > 0){
            for(Simulation simulation : simulations){
                if(simulation.isRunning())simulation.update();
            }
            simulations = simulations.stream().filter(simulation -> !simulation.isFinished()).collect(Collectors.toList());
            try {
                Thread.sleep(refreshing,0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
