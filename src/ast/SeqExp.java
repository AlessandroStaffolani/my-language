package ast;

import visitor.ExpSeqVisitor;
import visitor.ExpVisitor;

public class SeqExp extends OpExp {

    public SeqExp(Exp l, Exp r) {
        super(l, r);
    }

    @Override
    public String myOp() {
        return ",";
    }


    @Override
    public void accept(ExpVisitor v) {
        ((ExpSeqVisitor)v).visit(this);
    }
}
