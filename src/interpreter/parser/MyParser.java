package interpreter.parser;

import interpreter.lexer.MyStupidScanner;
import interpreter.lexer.Token;

public class MyParser {

    private MyStupidScanner scanner;
    private Token currentToken;

    public MyParser(MyStupidScanner scanner) {

        this.scanner = scanner;
        this.currentToken = scanner.getNextToken();
    }

    public boolean parseExp() {
        boolean t1 = this.parseTerm();
        while (currentToken != null) {
            if (currentToken.equals("+")) {
                currentToken = scanner.getNextToken();
                boolean t2 = this.parseTerm();
                t1 = t1 && t2;
            } else if (currentToken.equals("-")) {
                currentToken = scanner.getNextToken();
                boolean t2 = this.parseTerm();
                t1 = t1 && t2;
            } else {
                return t1;
            }
        }

        return t1;
    }

    public boolean parseTerm() {
        boolean f1 = this.parsePot();

        while (currentToken != null) {
            if (currentToken.equals("*")) {
                currentToken = scanner.getNextToken();
                boolean f2 = this.parsePot();
                f1 = f1 && f2;
            } else if (currentToken.equals("/")) {
                currentToken = scanner.getNextToken();
                boolean f2 = this.parsePot();
                f1 = f1 && f2;
            } else {
                return  f1;
            }
        }

        return f1;
    }

    public boolean parsePot() {
        boolean p1 = this.parseFactor();

        if (currentToken != null && !currentToken.equals("")) {
            if (currentToken.equals("^")) {
                currentToken = scanner.getNextToken();
                boolean p2 = this.parsePot();
                p1 = p1 && p2;
            } else {
                return p1;
            }
        }

        return p1;
    }

    public boolean parseFactor() {
        if (currentToken.equals("(")) {
            currentToken = scanner.getNextToken();
            boolean innerExp = this.parseExp(); // self embedding
            if (currentToken.equals(")")) {
                currentToken = scanner.getNextToken();
                return innerExp;
            } else {
                return  false;
            }
        } else if (currentToken.isNumber()) {
                currentToken = scanner.getNextToken();
                return true;
        } else {
            return false;
        }

    }
}
