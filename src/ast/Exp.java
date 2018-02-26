package ast;

import visitor.ExpVisitor;

abstract public class Exp {

    @Override
    public String toString() {
        return super.toString();
    }

    abstract public void accept(ExpVisitor v);
}
