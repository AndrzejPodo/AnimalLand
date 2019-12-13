package main.mapElements;

import main.config.WorldParams;
import main.internalOrgans.Brain;
import main.internalOrgans.Genes;
import main.structures.MapDirection;
import main.structures.Rotation;
import main.structures.Vector2d;

public class Animal extends AbstractCreature implements MapElement{

    private MapDirection orientation = MapDirection.NORTH;

    private Brain brain;

    private Genes genes;

    public Animal(Genes genes, Vector2d initialPosition, int initialEnergy){
        this.genes = genes;
        this.brain = new Brain(genes);
        this.position = initialPosition;
        this.energy = initialEnergy;
    }

    @Override
    public void update() {
        if(this.energy == 0){
            this.die();
        }
        else{
            this.energy -= WorldParams.getInstance().getEnergyPerMove();
            Rotation rotation = brain.decideNextMove();

            for(int i = rotation.ordinal(); i > 0; i--){
                orientation = orientation.next();
            }

            position = position.add(orientation.toUnitVector());
        }
    }

    public Genes getGenes() {
        return genes;
    }

    public Animal reproduce(Animal animal){
        Genes childGenes = this.getGenes().geneticCross(animal.getGenes());
        return new Animal(childGenes, this.position, animal.getEnergy()/4 + this.getEnergy()/4);
    }

    public void eat(Plant plant){
        this.energy += plant.getEnergy();
        plant.die();
    }

    @Override
    public String toString() {
        return this.position.toString();
    }
}
