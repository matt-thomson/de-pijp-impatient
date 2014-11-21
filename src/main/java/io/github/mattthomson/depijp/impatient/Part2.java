package io.github.mattthomson.depijp.impatient;

import com.google.common.collect.ImmutableList;
import io.github.mattthomson.depijp.DePijpFlow;
import io.github.mattthomson.depijp.PijpBuilder;
import io.github.mattthomson.depijp.tap.TsvDePijpTap;

import java.util.Arrays;
import java.util.List;

public class Part2 implements DePijpFlow {
    @Override
    public void flow(PijpBuilder pijpBuilder, String[] args) {
        pijpBuilder
                .read(new TsvDePijpTap(args[0], 2))

                .map(line -> line.get(1))
                .flatMap(text -> Arrays.stream(text.split("[ \\[\\]\\(\\),.]")))

                .groupBy(x -> x)
                .count()

                .map(kv -> (List<String>) ImmutableList.of(kv.getKey(), kv.getValue().toString()))

                .write(new TsvDePijpTap(args[1], 2));
    }
}
