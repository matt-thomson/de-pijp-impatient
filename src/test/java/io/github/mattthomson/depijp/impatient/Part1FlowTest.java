package io.github.mattthomson.depijp.impatient;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.github.mattthomson.depijp.DePijp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Part1FlowTest {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldRunFlow() throws Exception {
        File outputFile = File.createTempFile("output", ".tsv");
        outputFile.deleteOnExit();

        exit.expectSystemExitWithStatus(0);
        DePijp.main(new String[]{
                Part1Flow.class.getName(),
                "--local",
                "src/test/resources/part1.tsv",
                outputFile.getPath()
        });

        List<String> result = Files.readLines(outputFile, Charsets.UTF_8);
        assertThat(result).containsExactly("one\t1", "two\t2", "three\t3");
    }
}