package main.structures;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MyBox  extends VBox {
    private Thread thread;
    private ButtonState buttonState = ButtonState.STOP;

    public MyBox(MyPane pane, Thread thread){
        this.thread = thread;
        super.setAlignment(Pos.CENTER);
        super.setHeight(pane.getHeight()+20);
        Button stopStartButton = new Button(ButtonState.STOP.toString());
        stopStartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switch (buttonState){
                    case STOP:
                        thread.suspend();
                        stopStartButton.setText(ButtonState.START.toString());
                        buttonState = ButtonState.START;
                        break;
                    case START:
                        thread.resume();
                        stopStartButton.setText(ButtonState.STOP.toString());
                        buttonState = ButtonState.STOP;
                        break;
                }
            }
        });
        super.getChildren().add(pane);
        super.getChildren().add(stopStartButton);
    }

    enum ButtonState{
        STOP("STOP"), START("START");
        private String text;
        ButtonState(String label){
            text = label;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
