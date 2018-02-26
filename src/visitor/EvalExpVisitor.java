package visitor;

import ast.*;

public class EvalExpVisitor implements ExpVisitor {

    long value;

    public long getEvaluation() {
        return value;
    }

    @Override
    public void visit(PlusExp e) {
        e.left().accept(this);
        long arg1 = getEvaluation();
        e.right().accept(this);
        long arg2 = getEvaluation();
        value = arg1 + arg2;
    }

    @Override
    public void visit(MinusExp e) {
        e.left().accept(this);
        long arg1 = getEvaluation();
        e.right().accept(this);
        long arg2 = getEvaluation();
        value = arg1 - arg2;
    }

    @Override
    public void visit(TimesExp e) {
        e.left().accept(this);
        long arg1 = getEvaluation();
        e.right().accept(this);
        long arg2 = getEvaluation();
        value = arg1 * arg2;
    }

    @Override
    public void visit(DivExp e) {
        e.left().accept(this);
        long arg1 = getEvaluation();
        e.right().accept(this);
        long arg2 = getEvaluation();
        value = arg1 / arg2;
    }

    @Override
    public void visit(PotExp e) {
        e.left().accept(this);
        Long arg1 = getEvaluation();
        e.right().accept(this);
        Long arg2 = getEvaluation();
        Double powValue = Math.pow(arg1.doubleValue(), arg2.doubleValue());

        value = powValue.longValue();
    }

    @Override
    public void visit(NumExp e) {
        value = e.getVal();
    }
}
