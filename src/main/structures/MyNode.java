package main.structures;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.mapElements.MapElement;


public class MyNode extends ImageView {

    private final Vector2d position;

    private final Tooltip tooltip;

    public MyNode(MapElement element, double width, double height) {
        super(element.getImage().getImg());

        setFitWidth(width);
        setFitHeight(height);

        this.position = element.getPosition();
        this.tooltip = new Tooltip(element.toString());
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);

        Tooltip.install(this, tooltip);
    }

    public void update(MapElement element, FieldImage image) {
        System.out.println(element);
        tooltip.setText(element.toString());
        setImage(image.getImg());

    }
}
