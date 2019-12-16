package main.mechanics;

import main.World;
import main.config.WorldParams;
import main.map.IMap;
import main.map.WorldMap;
import main.mapElements.Animal;
import main.mapElements.MapElement;
import main.mapElements.Plant;
import main.structures.Vector2d;

import java.util.*;
import java.util.stream.Collectors;

public class Mechanics {
    IMap map;

    public Mechanics(IMap map){
        this.map = map;
    }

    public void update(){
        List<MapElement> elements = map.getElements();//.stream().filter(element -> element instanceof Animal).
                                    //collect(Collectors.toCollection(LinkedList::new));
        for(MapElement element : elements){
            element.update();
        }

        List<List<MapElement>> fields = map.getPopulatedFields();

        for(List<MapElement> field : fields){
            processFields(field);
        }

        seedAPlant();
    }

    public void seedAPlant(){
        List<Vector2d> jungle = map.getJungle();
        List<Vector2d> desert = map.getDesert();
        int plantsPerJungle = WorldParams.getInstance().getPlantPerJungle();
        int plantsPerDesert = WorldParams.getInstance().getPlantPerDesert();
        List<Vector2d> freePlaces;
        int rnd = 0;
        Random random = new Random();
        for(int i = 0; i < plantsPerJungle; i++){
            freePlaces = jungle.stream().filter(vector2d -> !map.isOccupied(vector2d)).collect(Collectors.toList());
            if(freePlaces.size() > 0){
                rnd = random.nextInt(freePlaces.size());
                map.placeElement(new Plant(freePlaces.get(rnd), WorldParams.getInstance().getPlantEnergy()));
            }else{
                break;
            }
        }
        for(int i = 0; i < plantsPerDesert; i++){
            freePlaces = desert.stream().filter(vector2d -> !map.isOccupied(vector2d)).collect(Collectors.toList());
            if(freePlaces.size() > 0){
                rnd = random.nextInt(freePlaces.size());
                map.placeElement(new Plant(freePlaces.get(rnd), WorldParams.getInstance().getPlantEnergy()));
            }else{
                break;
            }
        }
    }

    private void processFields(List<MapElement> elements){
        Plant plant = null;
        Animal strongest = null;
        Animal secondStrongest = null;
        Optional<MapElement> strongestOpt = elements.stream().filter(element -> element instanceof Animal).
                max(Comparator.comparingInt(MapElement::getEnergy));
        if (strongestOpt.isPresent()) {
            strongest = (Animal) strongestOpt.get();
            elements.remove(strongest);
        }
        Optional<MapElement>  secondStrongestOpt = elements.stream().filter(element -> element instanceof Animal).
                max(Comparator.comparingInt(MapElement::getEnergy));
        if (secondStrongestOpt.isPresent()) {
            secondStrongest = (Animal) secondStrongestOpt.get();
            elements.remove(secondStrongest);
        }

        for(MapElement element : elements){
            if(element instanceof Plant){
                plant = (Plant) element;
                break;
            }
        }

        if(plant != null && strongest != null){
            strongest.eat(plant);
        }

        if(strongest != null && secondStrongest != null &&
                secondStrongest.getEnergy() >= WorldParams.getInstance().getMinReproduceEnergy() &&
                strongest.getEnergy() >= WorldParams.getInstance().getMinReproduceEnergy())
        {
            Animal child = strongest.reproduce(secondStrongest);
            map.placeElement(child);
        }

    }
}
