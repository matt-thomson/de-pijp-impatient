package io.github.mattthomson.depijp.impatient;

import java.io.File;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Part1FlowIT {
    @Test
    public void shouldCopyFile() {
        File input = new File("data/rain.txt");
        File output = new File("target/part1.tsv");

        assertThat(output).hasContentEqualTo(input);
    }
}
