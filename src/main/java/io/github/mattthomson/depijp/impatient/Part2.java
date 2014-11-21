package io.github.mattthomson.depijp.impatient;

import io.github.mattthomson.depijp.DePijpFlow;
import io.github.mattthomson.depijp.KeyValue;
import io.github.mattthomson.depijp.PijpBuilder;
import io.github.mattthomson.depijp.tap.TsvDePijpTap;

import java.util.Arrays;

public class Part2 implements DePijpFlow {
    @Override
    public void flow(PijpBuilder pijpBuilder, String[] args) {
        pijpBuilder
                .read(new TsvDePijpTap(args[0], 2))

                .flatMap(line -> Arrays.stream(line.get(1).split("[ \\[\\]\\(\\),.]")))
                .group()
                .count()
                .map(KeyValue::toStringList)

                .write(new TsvDePijpTap(args[1], 2));
    }
}
