package org.dreamkite;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Goal which touches a timestamp file.
 *
 * @phase process-sources
 */
@Mojo(name = "gen-doc", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class GenDocMojo extends AbstractMojo {

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
    }
}
