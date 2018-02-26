package interpreter.lexer;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class MyStupidScanner extends StringTokenizer {

    public MyStupidScanner(String str) {
        super(str);
    }

    public Token getNextToken() {
        try {
            return new Token(nextToken().trim());
        } catch (NoSuchElementException e) {
            return null;
        }
    }

}
