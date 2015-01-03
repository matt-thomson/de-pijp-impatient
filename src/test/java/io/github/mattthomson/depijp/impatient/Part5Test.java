package io.github.mattthomson.depijp.impatient;

import com.google.common.io.Files;
import io.github.mattthomson.depijp.DePijp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Charsets.UTF_8;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class Part5Test {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldComputeTfIdf() throws Exception {
        File outputFile = File.createTempFile("output", ".tsv");
        outputFile.deleteOnExit();

        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(() -> {
            List<String> result = Files.readLines(outputFile, UTF_8);

            List<String[]> parts = result.stream()
                    .map(line -> Arrays.copyOfRange(line.split("\t"), 0, 2))
                    .collect(toList());
            assertThat(parts).containsExactly(
                    new String[]{"doc1", "baz"},
                    new String[]{"doc1", "foo"},
                    new String[]{"doc2", "foo"},
                    new String[]{"doc3", "foo"}
            );

            List<Double> values = result.stream()
                    .map(line -> Double.parseDouble(line.split("\t")[2]))
                    .collect(toList());
            assertThat(values).containsExactly(
                    1 * Math.log(3.0 / (1 + 1)),
                    2 * Math.log(3.0 / (1 + 3)),
                    1 * Math.log(3.0 / (1 + 3)),
                    1 * Math.log(3.0 / (1 + 3))
            );
        });

        DePijp.main(new String[]{
                Part5.class.getName(),
                "--local",
                "src/test/resources/part5.tsv",
                "src/test/resources/stop.txt",
                outputFile.getPath()
        });
    }
}