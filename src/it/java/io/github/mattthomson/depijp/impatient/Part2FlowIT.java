package io.github.mattthomson.depijp.impatient;

import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Charsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class Part2FlowIT {
    @Test
    public void shouldCountWordsInFile() throws IOException {
        File output = new File("target/part2.tsv");

        List<String> result = Files.readLines(output, UTF_8);
        assertThat(result).contains("mountainous\t1", "is\t4");
    }
}
