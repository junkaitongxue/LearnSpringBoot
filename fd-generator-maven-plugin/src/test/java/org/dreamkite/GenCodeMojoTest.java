package org.dreamkite;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

class GenCodeMojoTest {

    @TempDir
    Path tempDir;

    @Test
    void genCode() throws MojoExecutionException {
        GenCodeMojo genCodeMojo = new GenCodeMojo();
        genCodeMojo.setOutputDirectory(tempDir.toFile());
        genCodeMojo.execute();
    }


}