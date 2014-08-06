/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skills421.javafx8drools;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author johndunning
 */
public class MainApp extends Application
{
   @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 650, 500, Color.WHITE);

        TabPane tabPane = new TabPane();
        root.setCenter(tabPane);

        Tab dataTab = new DataTab();
        Tab ruleTab = new RuleTab();

        tabPane.getTabs().addAll(dataTab, ruleTab);

        primaryStage.setTitle("Ancestry");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
