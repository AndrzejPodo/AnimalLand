package main.config;

import main.parser.JsonParamsParser;

public class WorldParams {

    private static WorldParams worldParams = null;

    Params params;

    private WorldParams(){
        params = JsonParamsParser.parse();
    }

    public int getMapWidth(){
        return params.mapWidth;
    }

    public int getMapHeight(){
        return params.mapHeight;
    }

    public double getJungleToDesertRatio() {
        return params.jungleToDesertRatio;
    }

    public int getEnergyPerMove() {
        return params.energyPerMove;
    }

    public int getPlantPerDesert() {
        return params.plantPerDesert;
    }

    public int getPlantPerJungle() {
        return params.plantPerJungle;
    }

    public int getPlantEnergy(){
        return params.plantEnergy;
    }

    public int getMinReproduceEnergy(){
        return params.minReproduceEnergy;
    }

    public static WorldParams getInstance(){
        if (worldParams == null)
            worldParams = new WorldParams();
        return worldParams;
    }
}
