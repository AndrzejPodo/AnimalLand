package main.internalOrgans;

import java.util.Arrays;
import java.util.Random;


public class Genes {
    Random rand = new Random();

    private int[] genes;

    public Genes(int[] genes){
        this.genes = genes;
    }

    public Genes(){
        genes = new int[32];
        genes[0]=0;genes[1]=1;genes[2]=2;genes[3]=3;genes[4]=4;genes[5]=5;genes[6]=6;genes[7]=7;
        for(int i = 8; i < 32; i++){
            genes[i] = rand.nextInt(8);
        }
        Arrays.sort(genes);
    }

    public Genes geneticCross(Genes other){
        int[] newGenes = new int[32];
        int splittingPointI = rand.nextInt(15)+1; //random int from range [1..15]
        int splittingPointII= rand.nextInt(15)+17;//random int from range [17..31]
        int[] firstPartI = Arrays.copyOfRange(genes, 0, splittingPointI);
        int[] thirdPartI = Arrays.copyOfRange(genes, splittingPointII, genes.length);
        int[] secondPartII = Arrays.copyOfRange(other.genes, splittingPointI, splittingPointII);

        System.arraycopy(firstPartI,0, newGenes, 0, firstPartI.length);
        System.arraycopy(secondPartII,0, newGenes, firstPartI.length, secondPartII.length);
        System.arraycopy(thirdPartI,0, newGenes, firstPartI.length+secondPartII.length, thirdPartI.length);

        int[] counters = {0,0,0,0,0,0,0,0};

        for(int i = 0; i < newGenes.length; i++){
            counters[newGenes[i]]++;
        }

        for(int i = 0; i <= 7; i++){
            if(counters[i] == 0){
                int j = 0;

                for(; counters[j] < 2; j++); //losowo wybierac j

                counters[j]--;
                counters[i]++;
            }
        }

        int idx = 0;
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j < counters[i]; j++){
                newGenes[idx++] = i;
            }
        }

        return new Genes(newGenes);
    }

    public int[] getGenes() {
        return genes;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.genes);
    }
}
