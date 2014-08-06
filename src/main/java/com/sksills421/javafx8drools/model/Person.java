/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sksills421.javafx8drools.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author johndunning
 */
public class Person
{
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    
    private StringProperty name;
    private int age;
    private String sex;
    
    private List<Person> spouses;
    private List<Person> children;
 
    public Person()
    {
 
    }
 
    public Person(String name, int age, String sex)
    {
        this.name = new SimpleStringProperty(name);
        this.age = age;
        this.sex = sex;
    }
 
    public StringProperty nameProperty()
    {
        if (name == null)
        {
            name = new SimpleStringProperty();
        }
        return name;
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
        return age;
    }
 
    public void setAge(int age)
    {
        this.age = age;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public List<Person> getSpouses()
    {
        if(spouses==null)
        {
            spouses = new ArrayList<>();
        }
        
        return spouses;
    }

    public void setSpouses(List<Person> spouses)
    {
        this.spouses = spouses;
    }

    public List<Person> getChildren()
    {
        if(children==null)
        {
            children = new ArrayList<>();
        }
                
        return children;
    }

    public void setChildren(List<Person> children)
    {
        this.children = children;
    }
    
    public void addSpouse(Person spouse)
    {
        this.getSpouses().add(spouse);
    }
    
    public void addChild(Person child)
    {
        this.getChildren().add(child);
    }
 
    public String toString()
    {
        return String.format("Person[name=%s, age=%d, sex=%s]",name,age,sex);
    }
}
