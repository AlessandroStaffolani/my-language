package visitor;

import ast.*;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeVisitor implements ExpVisitor {

    DefaultMutableTreeNode root;

    public DefaultMutableTreeNode getResult() {
        return root;
    }

    protected void visitOpExp(OpExp e) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(e.myOp());
        e.left().accept(this);
        node.add(getResult());
        e.right().accept(this);
        node.add(getResult());
        root = node;
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
        root.add(new DefaultMutableTreeNode(e.getVal() + ""));
    }
}
