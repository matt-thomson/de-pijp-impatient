package io.github.mattthomson.depijp.impatient;

import com.google.common.io.Files;
import io.github.mattthomson.depijp.DePijp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;
import java.util.List;

import static com.google.common.base.Charsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class Part1Test {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldCopyFile() throws Exception {
        File outputFile = File.createTempFile("output", ".tsv");
        outputFile.deleteOnExit();

        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(() -> {
            List<String> result = Files.readLines(outputFile, UTF_8);
            assertThat(result).containsExactly("one\t1", "two\t2", "three\t3");
        });

        DePijp.main(new String[]{
                Part1.class.getName(),
                "--local",
                "src/test/resources/part1.tsv",
                outputFile.getPath()
        });
    }
}