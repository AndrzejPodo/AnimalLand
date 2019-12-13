package main.mechanics;

import main.map.IMap;
import main.map.WorldMap;
import main.mapElements.Animal;
import main.mapElements.MapElement;
import main.mapElements.Plant;

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

        if(strongest != null && secondStrongest != null && secondStrongest.getEnergy() >= 4 && strongest.getEnergy() >= 4){
            Animal child = strongest.reproduce(secondStrongest);
            map.placeElement(child);
        }

    }
}
