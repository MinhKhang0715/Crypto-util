package com.example.crypto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CryptoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CryptoApplication.class.getResource("crypto-page.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        stage.setTitle("Algorithms");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}