package io.github.mattthomson.depijp.impatient;

import java.util.Arrays;

import io.github.mattthomson.depijp.DePijpFlow;
import io.github.mattthomson.depijp.KeyValue;
import io.github.mattthomson.depijp.PijpBuilder;
import io.github.mattthomson.depijp.tap.TsvDePijpTap;

public class Part3 implements DePijpFlow {
    @Override
    public void flow(PijpBuilder pijpBuilder, String[] args) {
        pijpBuilder
                .read(new TsvDePijpTap(args[0], 2))

                .flatMap(line -> Arrays.stream(line.get(1).split("[ \\[\\]\\(\\),.]")))

                .map(token -> token.trim().toLowerCase())
                .filter(token -> token.length() > 0)

                .group()
                .count()
                .map(KeyValue::toStringList)

                .write(new TsvDePijpTap(args[1], 2));
    }
}
