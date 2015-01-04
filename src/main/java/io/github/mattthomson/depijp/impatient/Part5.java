package io.github.mattthomson.depijp.impatient;

import com.google.common.collect.ImmutableList;
import io.github.mattthomson.depijp.*;
import io.github.mattthomson.depijp.tap.TextLineDePijpTap;
import io.github.mattthomson.depijp.tap.TsvDePijpTap;

import java.util.Arrays;
import java.util.List;

public class Part5 implements DePijpFlow {
    @Override
    public void flow(PijpBuilder pijpBuilder, String[] args) {
        Pijp<List<String>> docs = pijpBuilder.read(new TsvDePijpTap(args[0], 2, true));
        Pijp<String> stopWords = pijpBuilder.read(new TextLineDePijpTap(args[1]));

        Pijp<Token> tokens = docs
                .flatMap(line -> Arrays.stream(line.get(1).split("[ \\[\\]\\(\\),.]"))
                        .map(token -> new Token(line.get(0), token.trim().toLowerCase())))

                .filter(token -> token.getToken().length() > 0)

                .groupBy(Token::getToken)
                .leftJoinWithTiny(stopWords.group())
                .values()

                .filter(x -> x.getSecond() == null)
                .map(Pair::getFirst);

        Pijp<KeyValue<String, Long>> docFreq = tokens
                .unique()
                .groupBy(Token::getToken)
                .count();

        Pijp<Long> docCount = tokens
                .map(Token::getDocId)
                .unique()
                .groupBy(x -> 0)
                .count()
                .map(KeyValue::getValue);

        Pijp<KeyValue<Token, Long>> tf = tokens.group().count();
        Pijp<Pair<String, Double>> idf = docFreq.groupBy(x -> 0)
                .joinWithTiny(docCount.groupBy(x -> 0))
                .values()
                .map(pair -> new Pair<>(pair.getFirst().getKey(), idf(pair.getSecond(), pair.getFirst().getValue())));

        tf.groupBy(t -> t.getKey().getToken())
                .join(idf.groupBy(Pair::getFirst))
                .values()
                .map(pair -> ImmutableList.of(
                        pair.getFirst().getKey().getDocId(),
                        pair.getFirst().getKey().getToken(),
                        Double.toString(pair.getFirst().getValue() * pair.getSecond().getSecond())))
                .map(l -> (List<String>) l)
                .write(new TsvDePijpTap(args[2], 3));
    }

    private double idf(long numDocs, long termDocs) {
        return Math.log((double) numDocs / (1.0 + termDocs));
    }
}
