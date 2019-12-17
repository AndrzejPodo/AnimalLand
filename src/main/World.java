package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.config.WorldParams;
import main.simulation.Simulation;
import main.structures.MyPane;
import main.visualiser.FXVisualizer;

public class World extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Animal Land");
        MyPane pane = new MyPane(WorldParams.getInstance().getMapWidth()*20,WorldParams.getInstance().getMapHeight()*20, WorldParams.getInstance().getMapWidth(), WorldParams.getInstance().getMapHeight());

        FXVisualizer visualizer1 = new FXVisualizer(pane);
        Simulation simulation1 = new Simulation(1000, 20, 200, 250, visualizer1);

        Thread thread = new Thread(simulation1);

        thread.start();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }
}
