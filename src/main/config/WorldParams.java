package main.config;

public class WorldParams {

    private static WorldParams worldParams = null;

    public final int mapWidth = 30;
    public final int mapHeight = 10;
    private int energyPerMove = 1;
    private int plantPerJungle = 1;
    private int plantPerDesert = 1;
    private int plantEnergy = 100;


    private WorldParams(){

    }

    public int getEnergyPerMove() {
        return energyPerMove;
    }

    public int getPlantPerDesert() {
        return plantPerDesert;
    }

    public int getPlantPerJungle() {
        return plantPerJungle;
    }

    public int getPlantEnergy(){
        return plantEnergy;
    }

    public static WorldParams getInstance(){
        if (worldParams == null)
            worldParams = new WorldParams();
        return worldParams;
    }
}
