package io.github.mattthomson.depijp.impatient;

import io.github.mattthomson.depijp.DePijpFlow;
import io.github.mattthomson.depijp.PijpBuilder;
import io.github.mattthomson.depijp.tap.TsvDePijpTap;

public class Part1 implements DePijpFlow {
    @Override
    public void flow(PijpBuilder pijpBuilder, String[] args) {
        pijpBuilder
                .read(new TsvDePijpTap(args[0], 2))
                .write(new TsvDePijpTap(args[1], 2));
    }
}
