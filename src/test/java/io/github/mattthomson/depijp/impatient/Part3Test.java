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

public class Part3Test {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldCountWordsInFile() throws Exception {
        File outputFile = File.createTempFile("output", ".tsv");
        outputFile.deleteOnExit();

        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(() -> {
            List<String> result = Files.readLines(outputFile, UTF_8);
            assertThat(result).containsOnly("foo\t4", "bar\t2", "baz\t1");
        });

        DePijp.main(new String[]{
                Part3.class.getName(),
                "--local",
                "src/test/resources/part3.tsv",
                outputFile.getPath()
        });
    }
}