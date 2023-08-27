package org.dreamkite;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.Data;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.springframework.util.ResourceUtils;

import javax.management.Attribute;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Goal which touches a timestamp file.
 *
 * @phase process-sources
 */
@Mojo(name = "gen-code", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
@Data
public class GenCodeMojo extends AbstractMojo {

    /**
     * Location of the file
     */
    @Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
    private File outputDirectory;

    /**
     * Location of the file
     */
    @Parameter(defaultValue = "${project.build.sourceDirectory}", property = "sourceDir", required = true)
    private File sourceDirectory;


    public void execute() throws MojoExecutionException {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

            cfg.setDirectoryForTemplateLoading(ResourceUtils.getFile("classpath:ftl"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template temp = cfg.getTemplate("person.ftl");

            Map<String, Object> root = new HashMap<>();

            root.put("packageName", "org.dreamkite");
            root.put("className", "Person");
            root.put("author", "dreamkite");

            List<Attribute> attr_list = new ArrayList<Attribute>();
            attr_list.add(new Attribute("id", "Long"));
            attr_list.add(new Attribute("name", "String"));
            attr_list.add(new Attribute("age", "Integer"));
            attr_list.add(new Attribute("hobby", "List<String>"));
            root.put("attrs", attr_list);

            File file = new File(outputDirectory, "Person.java");
            getLog().info("Start to gen file" + file.getAbsolutePath());
            OutputStream fos = new FileOutputStream(file); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);

            temp.process(root, out);
            fos.flush();
            fos.close();
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }


        getLog().info("gen code success!");
    }
}
