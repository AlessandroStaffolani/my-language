package interpreter.lexer;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class MyScanner {

    private String stringTokens;
    private String[] specialTokens;
    private int position;

    private static final Pattern DIGIT = Pattern.compile("\\d+");


    public MyScanner(String str, String[] specialTokens) {
        this.stringTokens = str.replaceAll(" ", "");
        if (validateSpecialTokens(specialTokens)) {
            this.specialTokens = specialTokens;
        } else {
            throw new IllegalArgumentException("Special token error, every token should be 1 character length");
        }
        position = 0;
    }

    public Token getNextToken() {
        try {
            return new Token(nextToken().trim());
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    protected String nextToken() {
        String token = "";
        int cursor = 1;

        if (position < stringTokens.length()) {

            String currentString = stringTokens.substring(position, position + cursor);

            if (isSpecialToken(currentString)) {
                token = currentString;
            } else if (DIGIT.matcher(currentString).matches()) {
                if (position < (stringTokens.length()-1)) {
                    String nextChar = stringTokens.substring(position + cursor, position + cursor + 1);
                    while (DIGIT.matcher(nextChar).matches() && (position + cursor) <= (stringTokens.length() - 1)) {
                        currentString = currentString.concat(nextChar);
                        cursor++;
                        if ((position + cursor) <= (stringTokens.length() - 1)) {
                            nextChar = stringTokens.substring(position + cursor, position + cursor + 1);
                        }
                    }
                    if (cursor > 1) {
                        position += cursor - 1;
                    }
                }
                token = currentString;
            } else {
                if (position < (stringTokens.length() - 1)) {
                    String nextChar = stringTokens.substring(position + cursor, position + cursor + 1);
                    while (!isSpecialToken(nextChar) && (position + cursor) <= (stringTokens.length() - 1)) {
                        currentString = currentString.concat(nextChar);
                        cursor++;
                        if ((position + cursor) <= (stringTokens.length() - 1)) {
                            nextChar = stringTokens.substring(position + cursor, position + cursor + 1);
                        }
                    }
                    if (cursor > 1) {
                        position += cursor - 1;
                    }
                }
                token = currentString;
            }

            position++;

        } else {
            throw new NoSuchElementException();
        }

        return token;
    }

    public void setStringTokens(String stringTokens) {
        this.stringTokens = stringTokens.replaceAll(" ", "");
        position = 0;
    }

    protected boolean validateSpecialTokens(String[] specialTokens) {
        boolean isValid = true;
        for (int i = 0; i < specialTokens.length; i++) {
            if (specialTokens[i].length() > 1) {
                isValid = false;
            }
        }
        return isValid;
    }

    protected boolean isSpecialToken(String currentString) {
        boolean isSpecial = false;

        for (int i = 0; i < specialTokens.length; i++) {
            if (currentString.equals(specialTokens[i])) {
                isSpecial = true;
            }
        }

        return isSpecial;
    }
}
