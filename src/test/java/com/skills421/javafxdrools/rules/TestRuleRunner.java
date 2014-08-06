/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.skills421.javafxdrools.rules;

import com.sksills421.javafx8drools.model.Person;
import com.sksills421.javafx8drools.rules.RuleRunner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


/**
 *
 * @author johndunning
 */
public class TestRuleRunner
{
    
    public TestRuleRunner()
    {
    }
    
    
    
    @Test
    public void testRuleRunner()
    {
        RuleRunner runner = new RuleRunner();
 
        List<Path> rulePaths = Arrays.asList(new Path[]{Paths.get("com/skills421/examples/rules/test1.drl")});
        Person jonDoe =  new Person("Jon Doe", 21, Person.MALE);
        Person janeDoe =  new Person("Jane Doe", 19, Person.FEMALE);
        Person markDoe =  new Person("Mark Doe", 2, Person.MALE);
        Person rubyDoe =  new Person("Ruby Doe", 5, Person.FEMALE);
 
        KieContainer kContainer = runner.buildKieContainer(rulePaths);
        
        KieSession kSession = kContainer.newKieSession();
        
        kSession.insert(jonDoe);
        kSession.insert(janeDoe);
        kSession.insert(markDoe);
        kSession.insert(rubyDoe);
        
        kSession.fireAllRules();
        
        /*
        assertEquals(1,jonDoe.getSpouses().size());
        assertEquals(janeDoe,jonDoe.getSpouses().get(0));
        
        assertEquals(2,jonDoe.getChildren().size());
        assertTrue(jonDoe.getChildren().contains(markDoe));
        assertTrue(jonDoe.getChildren().contains(rubyDoe));
        
        assertEquals(1,janeDoe.getSpouses().size());
        assertEquals(jonDoe,janeDoe.getSpouses().get(0));
        
        assertEquals(1,janeDoe.getChildren().size());
        assertTrue(janeDoe.getChildren().contains(markDoe));
        
        assertEquals(0,markDoe.getSpouses().size());
        assertEquals(0,markDoe.getChildren().size());
        
        assertEquals(0,rubyDoe.getSpouses().size());
        assertEquals(0,rubyDoe.getChildren().size());
                */
    }
}
