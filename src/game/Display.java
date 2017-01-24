package game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;﻿
public class Display extends Application{
	public static void main (String[] args){
		launch(args);
	}
	public void start (Stage primaryStage) throws Exception{
		primaryStage.setTitle("Window");
		button = new Button();
		button.setText("Click me");
		
		StackPane layout = newStackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
