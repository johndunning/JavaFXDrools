/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.skills421.javafx8drools.component;

import com.sksills421.javafx8drools.model.Person;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author johndunning
 */
public class DataTableView extends TableView<Person>
{
    public DataTableView(ObservableList<Person> data)
    {
        setPrefWidth(200);

        setItems(data);

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");

        nameCol.setPrefWidth(getPrefWidth() * 2 / 3);
        ageCol.setPrefWidth(getPrefWidth() / 3);

        nameCol.setStyle("-fx-alignment: CENTER-LEFT;");
        ageCol.setStyle("-fx-alignment: CENTER-RIGHT;");

        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory("age"));

        getColumns().setAll(nameCol, ageCol);
    }
}
