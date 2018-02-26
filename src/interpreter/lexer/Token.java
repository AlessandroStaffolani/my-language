package interpreter.lexer;

import java.util.regex.Pattern;

public class Token {

    private static final String identifier = "=";
    private static final String rvalueIdentifier = "$";
    private static final Pattern NONDIGIT = Pattern.compile("\\D");
    private static final String[] specialTokens = {
            "(",
            ")",
            "+",
            "-",
            "*",
            "/",
            "^",
            "=",
            "$",
            ",",
    };

    private String tk;

    public Token(String tk) {
        this.tk = tk;
    }

    public boolean isNumber() {
        try {
            Long.parseLong(tk);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public long getAsLong() {
        if (isNumber()) {
            return Long.parseLong(tk);
        } else {
            throw new NumberFormatException("unrecosgnised");
        }
    }

    @Override
    public String toString() {
        return tk;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof String) {
            return this.tk.equals((String) o);
        } else if (o instanceof Token) {
            Token that = (Token) o;
            return this.tk.equals(that.tk);
        } else {
            return false;
        }
    }

    public boolean isIdentifier() {
        if (tk.length() > 0) {
            String firstChar = tk.substring(0, 1);
            if (NONDIGIT.matcher(firstChar).find() && notSpecialToken()) {
                return true;
            }
        }
        return false;
    }

    public static String getIdentifier() {
        return identifier;
    }

    public static String getRvalueIdentifier() {
        return rvalueIdentifier;
    }

    public static String[] getSpecialTokens() {
        return specialTokens;
    }

    protected boolean notSpecialToken() {
        boolean notSpecial = true;
        for (String special : specialTokens) {
            if (special.equals(tk)) {
                notSpecial = false;
            }
        }

        return notSpecial;
    }
}
