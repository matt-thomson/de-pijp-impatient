package io.github.mattthomson.depijp.impatient;

import io.github.mattthomson.depijp.*;
import io.github.mattthomson.depijp.tap.TextLineDePijpTap;
import io.github.mattthomson.depijp.tap.TsvDePijpTap;

import java.util.Arrays;

public class Part4 implements DePijpFlow {
    @Override
    public void flow(PijpBuilder pijpBuilder, String[] args) {
        Pijp<String> stopWords = pijpBuilder.read(new TextLineDePijpTap(args[1]));

        pijpBuilder
                .read(new TsvDePijpTap(args[0], 2))

                .flatMap(line -> Arrays.stream(line.get(1).split("[ \\[\\]\\(\\),.]")))

                .map(token -> token.trim().toLowerCase())
                .filter(token -> token.length() > 0)

                .group()
                .leftJoinWithTiny(stopWords.group())
                .values()

                .filter(x -> x.getSecond() == null)
                .map(Pair::getFirst)

                .group()
                .count()
                .map(KeyValue::toStringList)

                .write(new TsvDePijpTap(args[2], 2));
    }
}
