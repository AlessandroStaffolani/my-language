package interpreter.lexer;

public class IllegalToken extends Exception {

    private Token tk;

    public IllegalToken(Token token) {
        tk = token;
    }

    public String getMessage() {
        if (tk != null) {
            return tk.toString() + " is an illegal token for this source code! \nShould be: + | - | * | / | ^ | <long_number> ";
        } else {
            return "no token given";
        }
    }
}
