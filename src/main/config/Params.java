package main.config;

public class Params {

    public final int mapWidth;
    public final int mapHeight;
    public final int energyPerMove;
    public final int plantPerJungle;
    public final int plantPerDesert;
    public final int plantEnergy;
    public final int minReproduceEnergy;
    public final double jungleToDesertRatio;

    public Params(int mapWidth, int mapHeight, int energyPerMove, int plantPerJungle, int plantPerDesert, int plantEnergy, int minReproduceEnergy, double jungleToDesertRatio){
        this.mapWidth  = mapWidth;
        this.mapHeight = mapHeight;
        this.energyPerMove = energyPerMove;
        this.plantPerJungle = plantPerJungle;
        this.plantPerDesert = plantPerDesert;
        this.plantEnergy = plantEnergy;
        this.minReproduceEnergy = minReproduceEnergy;
        this.jungleToDesertRatio = jungleToDesertRatio;
    }
}
