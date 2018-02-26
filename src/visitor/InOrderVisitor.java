package visitor;

import ast.*;
import interpreter.lexer.Token;

public class InOrderVisitor implements ExpVisitor, ExpAssignVisitor, ExpSeqVisitor {

    String curs = "";

    public String getResult() {
        return curs;
    }

    protected void visitOpExp(OpExp e) {
        e.left().accept(this);
        String sleft = getResult();
        e.right().accept(this);
        String sright = getResult();
        curs = "(" + sleft + " " + e.myOp() + " " + sright + ")";
    }

    @Override
    public void visit(PlusExp e) {
        visitOpExp(e);
    }

    @Override
    public void visit(MinusExp e) {
        visitOpExp(e);
    }

    @Override
    public void visit(MultiplyExp e) {
        visitOpExp(e);
    }

    @Override
    public void visit(DivideExp e) {
        visitOpExp(e);
    }

    @Override
    public void visit(PotExp e) {
        visitOpExp(e);
    }

    @Override
    public void visit(NumExp e) {
        curs = "" + e.getVal();
    }

    @Override
    public void visit(AssignExp e) {
        visitOpExp(e);
    }

    @Override
    public void visit(LIdentExp e) {
        curs = e.getName();
    }

    @Override
    public void visit(RIdentExp e) {
        curs = Token.getIdentifier() + e.getName();
    }

    @Override
    public void visit(SeqExp e) {
        visitOpExp(e);
    }
}
