/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skills421.javafx8drools.manager;

import com.sksills421.javafx8drools.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import javafx.concurrent.Task;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 *
 * @author johndunning
 */
public class RuleManager
{
    private static final String RULEPATH = "src/main/resources/com/skills421/examples/rules/test1.drl";
        
    private static RuleManager instance;
    private KieContainer kContainer = null;
    
    private RuleManager()
    {
        
    }
    
    public static RuleManager getInstance()
    {
        if(instance==null)
        {
            instance = new RuleManager();
        }
        
        return instance;
    }
    
    public void fireRules(final TableView<Person> allPeopleTableView)
    {
        final int idx = allPeopleTableView.getSelectionModel().getFocusedIndex();
        DataManager.getInstance().resetPeople();
        
        Task ruleTask = new Task()
        {
            @Override
            protected Object call() throws Exception
            {
                List<Path> rulePaths = Arrays.asList(new Path[]{Paths.get(RULEPATH)});

                if(kContainer==null) buildKieContainer(rulePaths);

                KieSession kSession = kContainer.newKieSession();

                DataManager.getInstance().getAllPeople().forEach(p -> kSession.insert(p));

                kSession.fireAllRules();

                kSession.dispose();

                return true;
            }

            @Override
            protected void succeeded()
            {
                allPeopleTableView.requestFocus();
                allPeopleTableView.getSelectionModel().select(idx==-1?0:idx);
            }

        };

        new Thread(ruleTask).start();
    }
    
    public void readRules(final TextArea ruleTA)
    {
        StringBuilder ruleContentSB = new StringBuilder();

        Task readTask = new Task()
        {
            @Override
            protected Object call() throws Exception
            {
                Path rulePath = Paths.get(RULEPATH);
                
                try (BufferedReader br = Files.newBufferedReader(rulePath))
                {
                    br.lines()
                            .forEach(s -> {
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
    
    public void saveRules(final TextArea ruleTA)
    {
        kContainer = null;
        
        Task readTask = new Task()
        {
            @Override
            protected Object call() throws Exception
            {
                Path rulePath = Paths.get(RULEPATH);
                
                String content = ruleTA.getText();
                Files.write(rulePath, content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                
                return true;
            }

            @Override
            protected void succeeded()
            {
            	MessageManager.getInstance().displayMessage("Rule Message", "Rule file saved.");
            }

        };

        new Thread(readTask).start();
    }
    
    private KieContainer buildKieContainer(List<Path> rulePaths)
    {
        KieServices ks = KieServices.Factory.get();
        KieResources kr = ks.getResources();
        KieFileSystem kfs = ks.newKieFileSystem();

        rulePaths.forEach(rulePath ->
        {
            try
            {
                Resource resource = kr.newInputStreamResource(Files.newInputStream(rulePath, StandardOpenOption.READ));
                resource.setResourceType(ResourceType.DRL);
                
                kfs.write(rulePath.toString(),resource);
            }
            catch (IOException e)
            {
                MessageManager.getInstance().displayError("Rule Build Error", e.getMessage());
            }
        });
        
        KieBuilder kb = ks.newKieBuilder(kfs);
  
        kb.buildAll();
        
        if (kb.getResults().hasMessages(Level.ERROR))
        {
            MessageManager.getInstance().displayError("Rule Build Error", kb.getResults().toString());
        }

        this.kContainer = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
        
        return this.kContainer;
    }
}
