package main.structures;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class MainNode extends ImageView {

    private final Vector2d position;

    private final Tooltip tooltip;

    public MainNode(Vector2d position, FieldType type, double width, double height) {
        super(type.getImg());

        setFitWidth(width);
        setFitHeight(height);

        this.position = position;
        this.tooltip = new Tooltip(type.getName()+this.position.toString());
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);

        Tooltip.install(this, tooltip);
    }

    public void update(FieldType newType) {
        tooltip.setText(newType.getName()+this.position.toString());

        setImage(newType.getImg());
    }
}
