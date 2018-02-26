package interpreter.parser;

import ast.*;
import interpreter.lexer.IllegalToken;
import interpreter.lexer.MyScanner;
import interpreter.lexer.MyStupidScanner;
import interpreter.lexer.Token;

import java.util.ArrayList;

public class AstParser {

    private MyScanner scanner;
    private Token currentToken;

    private ArrayList<String> errors;

    public AstParser(MyScanner scanner) {
        this.scanner = scanner;
        currentToken = scanner.getNextToken();
        errors = new ArrayList<String>();
    }

    public Exp parseSeq() {

        try {
            Exp expSequence = parseExp();
            while (currentToken != null) {

                if (currentToken.equals(",")) {
                    currentToken = scanner.getNextToken();
                    Exp nextExp = parseExp();
                    if (nextExp != null) {
                        expSequence = new SeqExp(expSequence, nextExp);
                    } else {
                        throw new IllegalArgumentException("No expression after symbol ','");
                    }
                } else {
                    return expSequence;
                }

            }

            return expSequence;
        } catch (IllegalArgumentException exception) {
            errors.add(exception.getMessage());
            return null;
        }
    }

    public Exp parseExp() {

        try {

            Exp termSeq = this.parseTerm();
            while (currentToken != null) {
                if (currentToken.equals("+")) {
                    currentToken = scanner.getNextToken();
                    Exp nextTerm = this.parseTerm();
                    if (nextTerm != null) {
                        termSeq = new PlusExp(termSeq, nextTerm);
                    } else {
                        throw new IllegalToken(currentToken);
                    }
                } else if (currentToken.equals("-")) {
                    currentToken = scanner.getNextToken();
                    Exp nextTerm = this.parseTerm();
                    if (nextTerm != null) {
                        termSeq = new MinusExp(termSeq, nextTerm);
                    } else {
                        throw new IllegalToken(currentToken);
                    }
                } else if (currentToken.isIdentifier()) {
                    String id = currentToken.toString();
                    currentToken = scanner.getNextToken();
                    if (currentToken != null) {
                        if (!(currentToken.equals(Token.getIdentifier()))) {
                            throw new IllegalArgumentException("After identifier must be a =");
                        }
                        currentToken = scanner.getNextToken();
                        Exp rigthExp = parseExp();
                        return new AssignExp(new LIdentExp(id), rigthExp);
                    }
                } else {
                    return termSeq;
                }
            }

            return termSeq;

        } catch (IllegalToken | IllegalArgumentException exception) {
            errors.add(exception.getMessage());
            return null;
        }
    }

    public Exp parseTerm() {

        try {

            Exp factorSeq = this.parsePot();

            while (currentToken != null) {
                if (currentToken.equals("*")) {
                    currentToken = scanner.getNextToken();
                    Exp nextFactor = this.parsePot();
                    if (nextFactor != null) {
                        factorSeq = new TimesExp(factorSeq, nextFactor);
                    } else {
                        throw new IllegalToken(currentToken);
                    }
                } else if (currentToken.equals("/")) {
                    currentToken = scanner.getNextToken();
                    Exp nextFactor = this.parsePot();
                    if (nextFactor != null) {
                        factorSeq = new DivExp(factorSeq, nextFactor);
                    } else {
                        throw new IllegalToken(currentToken);
                    }
                } else {
                    return factorSeq;
                }
            }

            return factorSeq;

        } catch (IllegalToken illegalToken) {
            errors.add(illegalToken.getMessage());
            return null;
        }
    }

    public Exp parsePot() {

        try {

            Exp potSeq = this.parseFactor();

            if (currentToken != null && !currentToken.equals("")) {
                if (currentToken.equals("^")) {
                    currentToken = scanner.getNextToken();
                    Exp nextPot = this.parsePot();
                    if (nextPot != null) {
                        potSeq = new PotExp(potSeq, nextPot);
                    } else {
                        throw new IllegalToken(currentToken);
                    }
                } else {
                    return potSeq;
                }
            }

            return potSeq;

        } catch (IllegalToken illegalToken) {
            errors.add(illegalToken.getMessage());
            return null;
        }
    }

    public Exp parseFactor() {
        try {
            if (currentToken.equals("(")) {
                currentToken = scanner.getNextToken();
                Exp innerExp = this.parseSeq(); // self embedding
                if (currentToken.equals(")")) {
                    currentToken = scanner.getNextToken();
                    return innerExp;
                } else {
                    return null;
                }
            } else if (currentToken.equals(Token.getRvalueIdentifier())) {

                currentToken = scanner.getNextToken();
                String id = currentToken.toString();
                currentToken = scanner.getNextToken();
                return new RIdentExp(id);

            } else if (currentToken.isNumber()) {
                long value = currentToken.getAsLong();
                currentToken = scanner.getNextToken();
                return new NumExp(value);
            } else {
                return null;
            }

        } catch (IllegalArgumentException exception) {
            errors.add(exception.getMessage());
            return null;
        }
    }

    public boolean hasErrors() {
        return errors.size() != 0;
    }

    public String errorsToString() {
        String message = "";

        for (String err : errors) {
            message = message.concat(err).concat("\n");
        }

        return message;
    }
}
