package main.structures;

import javafx.scene.layout.GridPane;

public class MyPane extends GridPane {
    private MainNode[][] nodes;

    public MyPane(double width, double height, int rowCount, int columnCount) {
//        super.setHeight(height);
//        super.setWidth(width);
        setHgap(1);
        setVgap(1);
        nodes = new MainNode[rowCount][columnCount];
        for(int i = 0; i < rowCount; i++){
            for(int j = 0; j < columnCount; j++){
                nodes[i][j] = new MainNode(new Vector2d(i, j), FieldType.GROUND, width/rowCount, height/columnCount);
                add(nodes[i][j], i, j,1,1);
            }
        }
    }

    public void updateNode(Vector2d position, FieldType newType) {
        nodes[position.x][position.y].update(newType);
    }
}
