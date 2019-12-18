package main.structures;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public enum FieldImage {

    ANIMAL("animal", "animal.png"),
    GROUND("ground",  "ground.png"),
    GRASS("grass", "grass.png");

    private final String resources = "src/resources/".replace("/", File.separator);

    private String name;
    private Image img;

    FieldImage(String name, String imageName){
        this.name = name;
        try {
            final FileInputStream input = new FileInputStream(resources + imageName);
            this.img = new Image(input);
        } catch (FileNotFoundException ignore) {
            ignore.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public Image getImg() {
        return img;
    }



}
