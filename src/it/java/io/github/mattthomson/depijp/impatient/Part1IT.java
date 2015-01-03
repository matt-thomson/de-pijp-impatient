package io.github.mattthomson.depijp.impatient;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class Part1IT {
    @Test
    public void shouldCopyFile() {
        File input = new File("data/rain.txt");
        File output = new File("target/part1.tsv");

        assertThat(output).hasContentEqualTo(input);
    }
}
