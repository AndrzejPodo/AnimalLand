package main.internalOrgans;

import main.structures.Rotation;

import java.util.Random;

public class Brain {

    private Genes genes;

    public Brain(Genes genes){
        this.genes = genes;
    }

    public Rotation decideNextMove(){
        Random rand = new Random();
        int genTable[] = genes.getGenes();
        int rotationIdx = genTable[rand.nextInt(genTable.length)];

        return Rotation.values()[rotationIdx];
    }
}
