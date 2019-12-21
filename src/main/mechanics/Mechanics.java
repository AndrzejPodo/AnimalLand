package main.mechanics;

import main.config.WorldParams;
import main.map.IMap;
import main.mapElements.Animal;
import main.mapElements.MapElement;
import main.mapElements.Plant;
import main.structures.MapDirection;
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
        Collections.sort(elements, Comparator.comparingInt(MapElement::getEnergy));

        for(MapElement element : elements){
            element.update(); //usuniecie martwych, skret, przemieszczenie
        }

        List<List<MapElement>> fields = map.getPopulatedFields();

        for(List<MapElement> field : fields){
            processFields(field); //jedzenie i rozmnazanie zwierzat
        }

        seedAPlant(); //dodanie nowych roslin
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
        List<MapElement> animals = elements.stream().filter(element -> element instanceof Animal).collect(Collectors.toList());
        List<MapElement> plants = elements.stream().filter(element -> element instanceof Plant).collect(Collectors.toList());

        Collections.sort(animals, Comparator.comparingInt(MapElement::getEnergy));

        if(plants.size() > 0 && animals.size() > 0){
            List<MapElement> strongestAnimals = animals.stream().filter(element -> element.getEnergy() == elements.get(elements.size()-1).getEnergy()).collect(Collectors.toList());
            if(strongestAnimals.size() == 1){
                ((Animal)strongestAnimals.get(0)).eat((Plant) plants.get(0));
            }else {
                for (MapElement animal : strongestAnimals) {
                    ((Animal) animal).eat(new Plant(new Vector2d(0, 0), plants.get(0).getEnergy() / strongestAnimals.size()));
                }
                ((Plant)plants.get(0)).die();
            }
        }

        while (animals.size() >= 2){
            Animal a1 = (Animal) animals.remove(animals.size()-1);
            Animal a2 = (Animal) animals.remove(animals.size()-1);
            reproduceAnimals(a1, a2);
        }
    }

    private void reproduceAnimals(Animal a1, Animal a2){
        Random random = new Random();
        if(a1 != null && a2 != null){
            if(a1.getEnergy() >= WorldParams.getInstance().getMinReproduceEnergy()  && a2.getEnergy() >= WorldParams.getInstance().getMinReproduceEnergy()){
                Animal child = a1.reproduce(a2);

                List<MapDirection> freePlaces = new LinkedList<>();
                for(MapDirection direction : MapDirection.values()){
                    Vector2d neighbour = a1.getPosition().add(direction.toUnitVector());
                    if(!map.isOccupied(new Vector2d((neighbour.x+map.getWidth())%map.getWidth(), (neighbour.y+map.getHeight())%map.getHeight()))){
                        freePlaces.add(direction);
                    }
                }
                if(freePlaces.size() != 0){
                    MapDirection direction = freePlaces.get(random.nextInt(freePlaces.size()));
                    Vector2d neighbour = a1.getPosition().add(direction.toUnitVector());
                    child.setPosition(new Vector2d((neighbour.x+map.getWidth())%map.getWidth(), (neighbour.y+map.getHeight())%map.getHeight()));
                    map.placeElement(child);
                }
            }
        }
    }
}
