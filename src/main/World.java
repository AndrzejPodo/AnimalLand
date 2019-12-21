package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.config.WorldParams;
import main.simulation.Simulation;
import main.structures.MyBox;
import main.structures.MyPane;
import main.visualiser.FXVisualizer;

public class World extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Animal Land");
       Scheduler scheduler = new Scheduler(20);
        //First world
        MyPane pane = new MyPane(WorldParams.getInstance().getMapWidth()*10,WorldParams.getInstance().getMapHeight()*10, WorldParams.getInstance().getMapWidth(), WorldParams.getInstance().getMapHeight());
        FXVisualizer visualizer = new FXVisualizer(pane);
        Simulation simulation = new Simulation(10000, 50, 200, visualizer);
        //Thread firstMap = new Thread(simulation);
        //firstMap.start();
        MyBox box = new MyBox(pane, simulation);

        //second world
        MyPane pane2 = new MyPane(WorldParams.getInstance().getMapWidth()*10,WorldParams.getInstance().getMapHeight()*10, WorldParams.getInstance().getMapWidth(), WorldParams.getInstance().getMapHeight());
        FXVisualizer visualizer2 = new FXVisualizer(pane2);
        Simulation simulation2 = new Simulation(10000, 20, 500, visualizer2);
        //Thread secondMap = new Thread(simulation2);
        //secondMap.start();

        scheduler.addSimulation(simulation);
        scheduler.addSimulation(simulation2);

        Thread main = new Thread(scheduler);
        main.start();
        
        MyBox box2 = new MyBox(pane2, simulation2);


        HBox mainBox = new HBox(box, box2);
        mainBox.setSpacing(10);

        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        stage.show();
    }
}
