package interpreter.parser;

import interpreter.lexer.MyStupidScanner;
import interpreter.lexer.Token;

public class MyLongIntInterpreter {

    private MyStupidScanner scanner;
    private Token currentToken;

    public MyLongIntInterpreter(MyStupidScanner scanner) {
        this.scanner = scanner;
        this.currentToken = scanner.getNextToken();
    }

    public long parseExp() {
        long t1 = this.parseTerm();
        while (currentToken != null) {
            if (currentToken.equals("+")) {
                currentToken = scanner.getNextToken();
                long t2 = this.parseTerm();
                t1 = t1 + t2;
            } else if (currentToken.equals("-")) {
                currentToken = scanner.getNextToken();
                long t2 = this.parseTerm();
                t1 = t1 - t2;
            } else {
                return t1;
            }
        }

        return t1;
    }

    public long parseTerm() {
        long f1 = this.parsePot();

        while (currentToken != null) {
            if (currentToken.equals("*")) {
                currentToken = scanner.getNextToken();
                long f2 = this.parsePot();
                f1 = f1 * f2;
            } else if (currentToken.equals("/")) {
                currentToken = scanner.getNextToken();
                long f2 = this.parsePot();
                f1 = f1 / f2;
            } else {
                return  f1;
            }
        }

        return f1;
    }

    public long parsePot() {
        long f1 = this.parseFactor();

        if (currentToken != null && !currentToken.equals("")) {
            if (currentToken.equals("^")) {
                currentToken = scanner.getNextToken();
                long p2 = this.parsePot();
                long res;
                for (res = 1; p2 > 0; p2--) {
                    res = res * f1;
                }
                f1 = res;
            } else {
                return f1;
            }
        }

        return f1;
    }

    public long parseFactor() {
        if (currentToken.equals("(")) {
            currentToken = scanner.getNextToken();
            long innerExp = this.parseExp(); // self embedding
            if (currentToken.equals(")")) {
                currentToken = scanner.getNextToken();
                return innerExp;
            } else {
                throw new IllegalArgumentException("missing");
            }
        } else if (currentToken.isNumber()) {
                long value = currentToken.getAsLong();
                currentToken = scanner.getNextToken();
                return value;
        } else {
            throw new IllegalArgumentException("unrecosgnised");
        }

    }
}