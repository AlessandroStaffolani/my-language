import controller.MainController;
import interpreter.lexer.MyScanner;
import interpreter.lexer.Token;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        MainController controller = new MainController();
        controller.initView();



        /*String[] expressions = {
                "x = 5 + 1",
                "x=44 - 3",
                "ciao = $x + 78",
                "ciao = $x + $ciao"
        };
        for (String expression : expressions) {
            MyScanner scanner = new MyScanner(expression, Token.getSpecialTokens());
            Token token = scanner.getNextToken();
            System.out.print(expression + "\t tokens: ");
            while (token != null) {
                System.out.print(token.toString() + " | ");
                token = scanner.getNextToken();
            }
            System.out.println();
        }*/

    }

}
