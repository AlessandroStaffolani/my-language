package visitor;

import ast.*;

import java.util.HashMap;
import java.util.Map;

public class EvalAssignExpVisitor implements ExpAssignVisitor, ExpSeqVisitor {

    private Map<String, Long> enviroment = new HashMap<>();

    private Object value;

    public Object getEvaluation() {
        return value;
    }

    public Map<String, Long> getEnviroment() {
        return enviroment;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void visit(AssignExp e) {
        e.left().accept(this);
        String id = ((LIdentExp) e.left()).getName();

        e.right().accept(this);
        value = getEvaluation();

        enviroment.put(id, (Long) value);
    }

    @Override
    public void visit(LIdentExp e) {
        value = e.getName();
    }

    @Override
    public void visit(RIdentExp e) {
        String id = e.getName();
        Long val = enviroment.get(id);

        if (val != null) {
            value = val;
        } else {
            throw new RuntimeException("Invalid identifier");
        }
    }

    @Override
    public void visit(PlusExp e) {
        e.left().accept(this);
        long arg1 = (Long) getEvaluation();
        e.right().accept(this);
        long arg2 = (Long) getEvaluation();
        value = new Long(arg1 + arg2);
    }

    @Override
    public void visit(MinusExp e) {
        e.left().accept(this);
        long arg1 = (Long) getEvaluation();
        e.right().accept(this);
        long arg2 = (Long) getEvaluation();
        value = new Long(arg1 - arg2);
    }

    @Override
    public void visit(MultiplyExp e) {
        e.left().accept(this);
        long arg1 = (Long) getEvaluation();
        e.right().accept(this);
        long arg2 = (Long) getEvaluation();
        value = new Long(arg1 * arg2);
    }

    @Override
    public void visit(DivideExp e) {
        e.left().accept(this);
        long arg1 = (Long) getEvaluation();
        e.right().accept(this);
        long arg2 = (Long) getEvaluation();
        value = new Long(arg1 / arg2);
    }

    @Override
    public void visit(PotExp e) {
        e.left().accept(this);
        Long arg1 = (Long) getEvaluation();
        e.right().accept(this);
        Long arg2 = (Long) getEvaluation();
        Double powValue = Math.pow(arg1.doubleValue(), arg2.doubleValue());

        value = new Long(powValue.longValue());
    }

    @Override
    public void visit(NumExp e) {
        value = (Long) e.getVal();
    }

    @Override
    public void visit(SeqExp e) {
        e.left().accept(this);

        e.right().accept(this);
        value = getEvaluation();
    }
}
