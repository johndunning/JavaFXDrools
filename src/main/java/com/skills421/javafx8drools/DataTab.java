/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.skills421.javafx8drools;

import com.skills421.javafx8drools.manager.DataManager;
import com.skills421.javafx8drools.manager.RuleManager;
import com.sksills421.javafx8drools.model.Person;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author johndunning
 */
public class DataTab extends Tab
{
    public DataTab()
    {
        this.setText("Data");
        
        // add BorderPane to the tab
        BorderPane dataTabPane = new BorderPane();
        setContent(dataTabPane);

        // now add the content to the BorderPane
        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setPadding(new Insets(5));

        TableView<Person> allPeopleTableView = new DataTableView(DataManager.getInstance().getAllPeople());
        TableView<Person> spousesTableView = new DataTableView(DataManager.getInstance().getPossiblePartners());
        TableView<Person> childrenTableView = new DataTableView(DataManager.getInstance().getPossibleChildren());

        // selection listening
        allPeopleTableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Person> observable, Person oldValue, Person newValue) ->
        {
            if (observable != null && observable.getValue() != null)
            {
                DataManager.getInstance().getPossiblePartners().clear();
                DataManager.getInstance().getPossiblePartners().addAll(observable.getValue().getSpouses());

                DataManager.getInstance().getPossibleChildren().clear();
                DataManager.getInstance().getPossibleChildren().addAll(observable.getValue().getChildren());

            }
        });

        // column 1
        Label allPeopleLbl = new Label("All People");
        GridPane.setHalignment(allPeopleLbl, HPos.CENTER);
        gridpane.add(allPeopleLbl, 0, 0);
        gridpane.add(allPeopleTableView, 0, 1);

        // column 2
        Label spouseLbl = new Label("Possible Partners");
        GridPane.setHalignment(spouseLbl, HPos.CENTER);
        gridpane.add(spouseLbl, 1, 0);
        gridpane.add(spousesTableView, 1, 1);

        // column 3
        Label childLbl = new Label("Possible Children");
        GridPane.setHalignment(childLbl, HPos.CENTER);
        gridpane.add(childLbl, 2, 0);
        gridpane.add(childrenTableView, 2, 1);

        //
        FlowPane bottomPane = new FlowPane(10, 10);
        bottomPane.setPadding(new Insets(5));
        bottomPane.setAlignment(Pos.CENTER);

        Button fireRulesButton = new Button("Fire Rules");
        bottomPane.getChildren().addAll(fireRulesButton);

        fireRulesButton.setOnAction(event -> RuleManager.getInstance().fireRules(allPeopleTableView));

        //
        dataTabPane.setCenter(gridpane);
        dataTabPane.setBottom(bottomPane);
    }
}
