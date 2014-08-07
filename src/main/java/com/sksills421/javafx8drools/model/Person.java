/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sksills421.javafx8drools.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author johndunning
 */
public class Person
{
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    
    private StringProperty name;
    private IntegerProperty age;
    private StringProperty sex;
    
    private ObservableList<Person> spouses = FXCollections.observableArrayList();
    private ObservableList<Person> children= FXCollections.observableArrayList();
 
    public Person()
    {
 
    }
 
    public Person(String name, int age, String sex)
    {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.sex = new SimpleStringProperty(sex);
    }
 
    public StringProperty nameProperty()
    {
        if (name == null)
        {
            name = new SimpleStringProperty();
        }
        return name;
    }
    
    public IntegerProperty ageProperty()
    {
        if (age == null)
        {
            age = new SimpleIntegerProperty();
        }
        return age;
    }
    
    public StringProperty sexProperty()
    {
        if (sex == null)
        {
            sex = new SimpleStringProperty();
        }
        return sex;
    }
    
    public ObservableList<Person> spousesProperty()
    {
        return spouses;
    }
    
    public ObservableList<Person> childrenProperty()
    {
        return children;
    }
 
    public String getName()
    {
        return nameProperty().get();
    }
    
    public void setName(String name)
    {
        nameProperty().set(name);
    }
 
    public int getAge()
    {
        return ageProperty().get();
    }
 
    public void setAge(int age)
    {
        ageProperty().set(age);
    }

    public String getSex()
    {
        return sexProperty().get();
    }

    public void setSex(String sex)
    {
        sexProperty().set(sex);
    }
   
    public void addSpouse(Person spouse)
    {
        spousesProperty().add(spouse);
    }
    
    public void addChild(Person child)
    {
        childrenProperty().add(child);
    }
 
    public String toString()
    {
        return String.format("Person[name=%s, age=%d, sex=%s]",name,age,sex);
    }
}
