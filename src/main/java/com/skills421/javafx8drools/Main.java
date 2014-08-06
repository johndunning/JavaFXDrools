/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.skills421.javafx8drools;

import com.sksills421.javafx8drools.model.Person;
import com.sksills421.javafx8drools.rules.RuleRunner;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author johndunning
 */
public class Main
{
    public static void main(String[] args)
    {
        RuleRunner runner = new RuleRunner();
 
        Path[] rules = { Paths.get("test1.drl") };
        Object[] facts = { new Person("Jon Doe", 21,Person.MALE) };
 
        runner.buildKieContainer(rules);
    }
}
