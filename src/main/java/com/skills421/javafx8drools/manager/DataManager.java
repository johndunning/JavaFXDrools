/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skills421.javafx8drools.manager;

import com.sksills421.javafx8drools.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author johndunning
 */
public class DataManager
{

    private static DataManager instance;

    private ObservableList<Person> allPeople;
    private ObservableList<Person> possiblePartners;
    private ObservableList<Person> possibleChildren;

    private DataManager()
    {

    }

    public static DataManager getInstance()
    {
        if (instance == null)
        {
            instance = new DataManager();
        }

        return instance;
    }

    public ObservableList<Person> getAllPeople()
    {
        if (allPeople == null)
        {
            Person jonDoe = new Person("Jon Doe", 21, Person.MALE);
            Person janeDoe = new Person("Jane Doe", 19, Person.FEMALE);
            Person markDoe = new Person("Mark Doe", 2, Person.MALE);
            Person rubyDoe = new Person("Ruby Doe", 5, Person.FEMALE);

            allPeople = FXCollections.observableArrayList(
                    jonDoe,
                    janeDoe,
                    markDoe,
                    rubyDoe
            );
        }

        return allPeople;
    }

    public void resetPeople()
    {
        this.getAllPeople().clear();

        Person jonDoe = new Person("Jon Doe", 21, Person.MALE);
        Person janeDoe = new Person("Jane Doe", 19, Person.FEMALE);
        Person markDoe = new Person("Mark Doe", 2, Person.MALE);
        Person rubyDoe = new Person("Ruby Doe", 5, Person.FEMALE);

        allPeople.addAll(jonDoe, janeDoe, markDoe, rubyDoe);
        getPossiblePartners().clear();
        getPossibleChildren().clear();
    }

    public ObservableList<Person> getPossiblePartners()
    {
        if (possiblePartners == null)
        {
            possiblePartners = FXCollections.observableArrayList();
        }

        return possiblePartners;
    }

    public ObservableList<Person> getPossibleChildren()
    {
        if (possibleChildren == null)
        {
            possibleChildren = FXCollections.observableArrayList();
        }

        return possibleChildren;
    }
}
