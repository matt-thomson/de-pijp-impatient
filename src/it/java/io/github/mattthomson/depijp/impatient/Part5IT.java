package io.github.mattthomson.depijp.impatient;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Charsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class Part5IT {
    private static final Joiner JOINER = Joiner.on("\t");

    @Test
    public void shouldComputeTfIdf() throws IOException {
        File output = new File("target/part5.tsv");

        List<String> result = Files.readLines(output, UTF_8);
        Map<String, Double> values = Maps.transformValues(
                Maps.uniqueIndex(result, line -> JOINER.join(Arrays.copyOfRange(line.split("\t"), 0, 2))),
                line -> Double.parseDouble(line.split("\t")[2]));

        assertThat(values.get("doc02\train")).isEqualTo(2 * Math.log(6.0 / (4 + 1)));
    }
}
