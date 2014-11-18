package io.github.mattthomson.depijp.impatient;

import io.github.mattthomson.depijp.DePijpFlow;
import io.github.mattthomson.depijp.PijpBuilder;
import io.github.mattthomson.depijp.tap.TsvDePijpTap;

public class Part1Flow implements DePijpFlow {
    @Override
    public void flow(PijpBuilder pijpBuilder, String[] args) {
        pijpBuilder
                .read(new TsvDePijpTap(args[1], 2))
                .write(new TsvDePijpTap(args[2], 2));
    }
}
