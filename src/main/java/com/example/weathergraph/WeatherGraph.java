package com.example.weathergraph;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeatherGraph extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("weather-graph.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("WeatherGraph");
        stage.setScene(scene);
        stage.show();
    }

    /*main method begins program execution*/
    public static void main(String[] args) {
        launch(args);
        System.out.println();
    }
}
