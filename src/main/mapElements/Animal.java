package main.mapElements;

import main.World;
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
        super(initialPosition, initialEnergy);
        this.genes = genes;
        this.brain = new Brain(genes);
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
            Vector2d oldPosition = position;
            int x = (oldPosition.x < 0)?oldPosition.x+WorldParams.getInstance().getMapWidth():oldPosition.x%WorldParams.getInstance().getMapWidth();
            int y = (oldPosition.y < 0)?oldPosition.y+WorldParams.getInstance().getMapHeight():oldPosition.y%WorldParams.getInstance().getMapHeight();
            oldPosition = new Vector2d(x,y);
            position = position.add(orientation.toUnitVector());
            x = (position.x < 0)?position.x+WorldParams.getInstance().getMapWidth():position.x%WorldParams.getInstance().getMapWidth();
            y = (position.y < 0)?position.y+WorldParams.getInstance().getMapHeight():position.y%WorldParams.getInstance().getMapHeight();
            position = new Vector2d(x,y);
            notifyPositionChange(oldPosition);
        }
    }

    public Genes getGenes() {
        return genes;
    }

    public Animal reproduce(Animal animal){
        Genes childGenes = this.getGenes().geneticCross(animal.getGenes());
        Animal child = new Animal(childGenes, this.position, animal.getEnergy()/4 + this.getEnergy()/4);
        this.energy -= this.energy/4;
        animal.setEnergy(animal.getEnergy() - animal.getEnergy()/4);
        return child;
    }

    public void eat(Plant plant){
        int a = this.energy;
        int b = plant.getEnergy();
        this.setEnergy(a+b);
        plant.die();
    }

    @Override
    public String toString() {
        return"A";
    }
}
