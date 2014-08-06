/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.skills421.javafx8drools;

import com.skills421.javafx8drools.manager.RuleManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author johndunning
 */
public class RuleTab extends Tab
{
    private TextArea ruleTA;
    
    public RuleTab()
    {
        this.setText("Rules");
        
        BorderPane rulePane = new BorderPane();
        rulePane.setPadding(new Insets(5));
        setContent(rulePane);

        // top
        FlowPane topPane = new FlowPane(10, 10);
        topPane.setAlignment(Pos.CENTER);
        Label ruleLbl = new Label("Rules");
        topPane.getChildren().add(ruleLbl);

        // middle
        final TextArea ruleTA = new TextArea();
        RuleManager.getInstance().readRules(ruleTA);

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
        saveButton.setOnAction(event -> RuleManager.getInstance().saveRules(ruleTA));
        
        // read the rules
        RuleManager.getInstance().readRules(ruleTA);
    }
}
