package main;

import main.internalOrgans.Genes;
import main.mapElements.Animal;
import main.structures.Vector2d;

import java.util.LinkedList;

public class World {
    public static void main(String[] args) {
        Animal animal1 = new Animal(new Genes(), new Vector2d(5,5), 100);
        Animal animal2 = new Animal(new Genes(), new Vector2d(5,5),100);

        Animal animal3 = animal1.reproduce(animal2);
        Animal animal4 = animal2.reproduce(animal1);

        LinkedList<Animal> linkedList = new LinkedList<>();
        linkedList.push(animal3);
        linkedList.push(animal4);

        for(int i = 0; i < 10; i++){
            animal3.update();
            System.out.println(animal3);
        }
    }
}
