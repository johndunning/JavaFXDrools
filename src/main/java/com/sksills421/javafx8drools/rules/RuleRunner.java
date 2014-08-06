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
import java.nio.file.StandardOpenOption;
import java.util.List;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
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
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public KieContainer buildKieContainer(List<Path> rulePaths)
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
                System.out.println(e.getMessage());
            }
        });

        ks.newKieBuilder(kfs).buildAll();

        KieContainer kc = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());

        return kc;
    }
}
