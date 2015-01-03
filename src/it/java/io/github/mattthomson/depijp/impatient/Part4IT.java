package io.github.mattthomson.depijp.impatient;

import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Charsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class Part4IT {
    @Test
    public void shouldCountWordsInFileRemovingStopWords() throws IOException {
        File output = new File("target/part4.tsv");

        List<String> result = Files.readLines(output, UTF_8);
        assertThat(result).contains("mountainous\t1").doesNotContain("is\t4", "a\t8");
    }
}
