/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sksills421.javafx8drools.rules;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message.Level;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;

/**
 *
 * @author johndunning
 */
public class RuleRunner
{

    public RuleRunner()
    {
    }

    private void printFileContent(Path path)
    {
        try (BufferedReader br = Files.newBufferedReader(path))
        {
            br.lines().forEach(s -> System.out.println(s));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    public KieContainer buildKieContainer(Path[] rules)
    {

        KieServices kieServices = KieServices.Factory.get();
        KieResources kieResources = kieServices.getResources();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        KieRepository kieRepository = kieServices.getRepository();

        for (Path ruleFilePath : rules)
        {
            //Resource resource = kieResources.newFileSystemResource(ruleFilePath.toAbsolutePath().toFile());
            Resource resource = kieResources.newClassPathResource(ruleFilePath.toString());

            // path has to start with src/main/resources
            // append it with the package from the rule
            String kiePath = "src/main/resources/" + ruleFilePath.toString();
            System.out.println("Processing Rule File: " + kiePath);
            printFileContent(Paths.get(kiePath));
            kieFileSystem.write(kiePath, resource);
        }

        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);

        kb.buildAll();

        if (kb.getResults().hasMessages(Level.ERROR))
        {
            System.out.println(kb.getResults().toString());
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }

        KieContainer kContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());

        return kContainer;
    }
}
