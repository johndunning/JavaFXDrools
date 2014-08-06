/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skills421.javafx8drools;

import com.sksills421.javafx8drools.model.Person;
import com.sksills421.javafx8drools.rules.RuleRunner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 *
 * @author johndunning
 */
public class MainApp extends Application
{

    private ObservableList<Person> allPeople;
    private ObservableList<Person> possiblePartners;
    private ObservableList<Person> possibleChildren;

    private TableView<Person> allPeopleTableView;
    private TextArea ruleTA;

    private ObservableList<Person> getAllPeople()
    {
        Person jonDoe = new Person("Jon Doe", 21, Person.MALE);
        Person janeDoe = new Person("Jane Doe", 19, Person.FEMALE);
        Person markDoe = new Person("Mark Doe", 2, Person.MALE);
        Person rubyDoe = new Person("Ruby Doe", 5, Person.FEMALE);

        final ObservableList<Person> allpeople = FXCollections.observableArrayList(
                jonDoe,
                janeDoe,
                markDoe,
                rubyDoe
        );

        return allpeople;
    }

    private TableView<Person> createPeopleTableView(ObservableList<Person> allPeople)
    {
        final TableView<Person> allpeopleTableView = new TableView<>();
        allpeopleTableView.setPrefWidth(200);

        allpeopleTableView.setItems(allPeople);

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");

        nameCol.setPrefWidth(allpeopleTableView.getPrefWidth() * 2 / 3);
        ageCol.setPrefWidth(allpeopleTableView.getPrefWidth() / 3);

        nameCol.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0.166667em;");
        ageCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0.166667em;");

        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory("age"));

        allpeopleTableView.getColumns().setAll(nameCol, ageCol);

        return allpeopleTableView;
    }

    private TableView<Person> createSpousesTableView(ObservableList<Person> spouses)
    {
        final TableView<Person> spousesTableView = new TableView<>();
        spousesTableView.setPrefWidth(200);

        spousesTableView.setItems(spouses);

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");

        nameCol.setPrefWidth(spousesTableView.getPrefWidth() * 2 / 3);
        ageCol.setPrefWidth(spousesTableView.getPrefWidth() / 3);

        nameCol.setStyle("-fx-alignment: CENTER-LEFT;");
        ageCol.setStyle("-fx-alignment: CENTER-RIGHT;");

        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory("age"));

        spousesTableView.getColumns().setAll(nameCol, ageCol);

        return spousesTableView;
    }

    private TableView<Person> createChildrenTableView(ObservableList<Person> children)
    {
        final TableView<Person> childrenTableView = new TableView<>();
        childrenTableView.setPrefWidth(200);

        childrenTableView.setItems(children);

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");

        nameCol.setPrefWidth(childrenTableView.getPrefWidth() * 2 / 3);
        ageCol.setPrefWidth(childrenTableView.getPrefWidth() / 3);

        nameCol.setStyle("-fx-alignment: CENTER-LEFT;");
        ageCol.setStyle("-fx-alignment: CENTER;");

        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory("age"));

        childrenTableView.getColumns().setAll(nameCol, ageCol);

        return childrenTableView;
    }

    private void resetData(final ObservableList<Person> allPeople)
    {
        allPeople.clear();

        Person jonDoe = new Person("Jon Doe", 21, Person.MALE);
        Person janeDoe = new Person("Jane Doe", 19, Person.FEMALE);
        Person markDoe = new Person("Mark Doe", 2, Person.MALE);
        Person rubyDoe = new Person("Ruby Doe", 5, Person.FEMALE);

        allPeople.addAll(jonDoe, janeDoe, markDoe, rubyDoe);
    }

    private void fireRules(final ObservableList<Person> allPeople)
    {
        /*
         Person jonDoe = allPeople.filtered(p -> p.nameProperty().get().equals("Jon Doe")).get(0);
         Person janeDoe = allPeople.filtered(p -> p.nameProperty().get().equals("Jane Doe")).get(0);
         Person markDoe = allPeople.filtered(p -> p.nameProperty().get().equals("Mark Doe")).get(0);
         Person rubyDoe = allPeople.filtered(p -> p.nameProperty().get().equals("Ruby Doe")).get(0);

         jonDoe.addSpouse(janeDoe);
         jonDoe.addChild(markDoe);
         jonDoe.addChild(rubyDoe);
         */

        /*
         RuleRunner runner = new RuleRunner();
 
         Path[] rules = { Paths.get("com/skills421/examples/rules/test1.drl") };
 
         KieContainer kContainer = runner.buildKieContainer(rules);
        
         KieSession kSession = kContainer.newKieSession();
        
         allPeople.forEach(p -> kSession.insert(p));
        
         kSession.fireAllRules();
         */
        Task ruleTask = new Task()
        {
            @Override
            protected Object call() throws Exception
            {
                RuleRunner runner = new RuleRunner();

                Path[] rules =
                {
                    Paths.get("com/skills421/examples/rules/test1.drl")
                };

                KieContainer kContainer = runner.buildKieContainer(rules);

                KieSession kSession = kContainer.newKieSession();

                allPeople.forEach(p -> kSession.insert(p));

                kSession.fireAllRules();

                return true;
            }

            @Override
            protected void succeeded()
            {
                allPeopleTableView.requestFocus();
                allPeopleTableView.getSelectionModel().select(allPeopleTableView.getSelectionModel().getFocusedIndex());
            }

        };

        new Thread(ruleTask).start();
    }

    private Tab createDataTab()
    {
        Tab dataTab = new Tab("Data");

        // add BorderPane to the tab
        BorderPane dataTabPane = new BorderPane();
        dataTab.setContent(dataTabPane);

        // now add the content to the BorderPane
        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setPadding(new Insets(5));

        allPeopleTableView = this.createPeopleTableView(allPeople);
        TableView<Person> spousesTableView = this.createSpousesTableView(possiblePartners);
        TableView<Person> childrenTableView = this.createChildrenTableView(possibleChildren);

        // selection listening
        allPeopleTableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Person> observable, Person oldValue, Person newValue) ->
        {
            if (observable != null && observable.getValue() != null)
            {
                possiblePartners.clear();
                possiblePartners.addAll(observable.getValue().getSpouses());

                possibleChildren.clear();
                possibleChildren.addAll(observable.getValue().getChildren());

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

        Button resetDataButton = new Button("Reset Data");
        Button fireRulesButton = new Button("Fire Rules");
        bottomPane.getChildren().addAll(resetDataButton, fireRulesButton);

        //
        resetDataButton.setOnAction(event ->
        {
            int idx = allPeopleTableView.getSelectionModel().getSelectedIndex();

            this.resetData(allPeople);

            allPeopleTableView.getSelectionModel().select(idx);
            allPeopleTableView.requestFocus();

        });

        fireRulesButton.setOnAction(event -> this.fireRules(allPeople));

        //
        dataTabPane.setCenter(gridpane);
        dataTabPane.setBottom(bottomPane);

        return dataTab;
    }

    private void readRules(final TextArea ruleTA)
    {
        StringBuilder ruleContentSB = new StringBuilder();

        Task readTask = new Task()
        {
            @Override
            protected Object call() throws Exception
            {
                Path rulePath = Paths.get("src/main/resources/com/skills421/examples/rules/test1.drl");
                
                System.out.println("Reading Rule File: "+rulePath.toString());

                try (BufferedReader br = Files.newBufferedReader(rulePath))
                {
                    br.lines()
                            .forEach(s -> {
                                System.out.println("Line: "+s);
                                ruleContentSB.append(s);
                                ruleContentSB.append("\n");
                            });
                }
                catch (IOException e)
                {
                    System.out.println(e.getMessage());
                }
                
                return true;
            }

            @Override
            protected void succeeded()
            {
                ruleTA.setText(ruleContentSB.toString());
            }

        };

        new Thread(readTask).start();
    }
    
    private void saveRules()
    {
        Task readTask = new Task()
        {
            @Override
            protected Object call() throws Exception
            {
                Path rulePath = Paths.get("src/main/resources/com/skills421/examples/rules/test1.drl");
                
                System.out.println("Writing Rule File: "+rulePath.toString());

                String content = ruleTA.getText();
                Files.write(rulePath, content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                
                return true;
            }

            @Override
            protected void succeeded()
            {
                System.out.println("Saved");
            }

        };

        new Thread(readTask).start();
    }

    private Tab createRuleTab()
    {
        Tab ruleTab = new Tab("Rules");

        BorderPane rulePane = new BorderPane();
        rulePane.setPadding(new Insets(5));
        ruleTab.setContent(rulePane);

        // top
        FlowPane topPane = new FlowPane(10, 10);
        topPane.setAlignment(Pos.CENTER);
        Label ruleLbl = new Label("Rules");
        topPane.getChildren().add(ruleLbl);

        // middle
        ruleTA = new TextArea();

        // bottom
        FlowPane bottomPane = new FlowPane(10, 10);
        bottomPane.setPadding(new Insets(5));
        bottomPane.setAlignment(Pos.CENTER);

        Button saveButton = new Button("Save");
        bottomPane.getChildren().addAll(saveButton);

        rulePane.setTop(topPane);
        rulePane.setCenter(ruleTA);
        rulePane.setBottom(bottomPane);

        //
        saveButton.setOnAction(event -> saveRules());
        
        // read the rules
        readRules(ruleTA);

        return ruleTab;
    }

    @Override
    public void start(Stage primaryStage)
    {
        allPeople = this.getAllPeople();
        possiblePartners = FXCollections.observableArrayList();
        possibleChildren = FXCollections.observableArrayList();

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 650, 250, Color.WHITE);

        TabPane tabPane = new TabPane();
        root.setCenter(tabPane);

        Tab dataTab = createDataTab();
        Tab ruleTab = createRuleTab();

        tabPane.getTabs().addAll(dataTab, ruleTab);

        primaryStage.setTitle("Ancestry!");
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
