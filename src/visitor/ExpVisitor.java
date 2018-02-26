package visitor;

import ast.*;

public interface ExpVisitor {

    void visit(PlusExp e);

    void visit(MinusExp e);

    void visit(MultiplyExp e);

    void visit(DivideExp e);

    void visit(PotExp e);

    void visit(NumExp e);
}
