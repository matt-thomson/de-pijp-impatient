package io.github.mattthomson.depijp.impatient;

import io.github.mattthomson.depijp.DePijpFlow;
import io.github.mattthomson.depijp.PijpBuilder;
import io.github.mattthomson.depijp.tap.TextLineDePijpTap;

public class Part1 implements DePijpFlow {
    @Override
    public void flow(PijpBuilder pijpBuilder, String[] args) {
        pijpBuilder
                .read(new TextLineDePijpTap(args[0]))
                .write(new TextLineDePijpTap(args[1]));
    }
}
