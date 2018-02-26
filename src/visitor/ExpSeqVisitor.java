package visitor;

import ast.SeqExp;

public interface ExpSeqVisitor extends ExpAssignVisitor {

    public abstract void visit(SeqExp e);
}
