package com.example.events;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML// this @FXML annotation is important
    //we gave an id in hello-view-fxml file
    private TextField nameField;//id of the TextField fx:id="nameField"
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;
    @FXML
    //to disable buttons
    public void initialize(){
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }
    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        // getSource=>get the text on the button
        if(event.getSource().equals(helloButton)){
            System.out.println("Hello," + nameField.getText());

        } else if (event.getSource().equals(byeButton)) {
            System.out.println("Bye , " + nameField.getText());

        }
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                    System.out.println("I'm going to sleep on the: " + s);
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                            System.out.println("I'm updating the label on the: " + s);
                            ourLabel.setText("We did something!");
                        }
                    });
                } catch(InterruptedException event) {
                    // we don't care about this
                }
            }
        };

        new Thread(task).start();
        if(ourCheckBox.isSelected()){
            nameField.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }
    }
    @FXML
    public void handleKeyReleased(){
        String text = nameField.getText();
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }
    public void handlechange(){
        System.out.println("The checkbox is " + ((ourCheckBox.isSelected())?" checked" : " not checked"));
    }
}