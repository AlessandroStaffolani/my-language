package controller;

import ast.Exp;
import interpreter.lexer.MyScanner;
import interpreter.lexer.Token;
import interpreter.parser.AstParser;
import interpreter.lexer.MyStupidScanner;
import view.*;
import visitor.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainController {

    private UserInterface userInterface;

    private PreOrderVisitor preOrderVisitor;
    private InOrderVisitor inOrderVisitor;
    private PostOrderVisitor postOrderVisitor;
    private EvalAssignExpVisitor evalAssignExpVisitor;

    public MainController() {
        userInterface = new UserInterface();
        preOrderVisitor = new PreOrderVisitor();
        inOrderVisitor = new InOrderVisitor();
        postOrderVisitor = new PostOrderVisitor();
        evalAssignExpVisitor = new EvalAssignExpVisitor();
        addEventsHandler();
    }

    public void initView() {
        userInterface.setVisible(true);
    }

    private void addEventsHandler() {
        userInterface.getCloseButton().addActionListener(e -> System.exit(0));
        //userInterface.getSourceCode().addKeyListener(inputHandler());
        userInterface.getCompileButton().addActionListener(e -> compileHandler());
    }

    private void compileHandler() {
        String expression = userInterface.getSourceCodeValue();
        MyScanner scanner = new MyScanner(expression, Token.getSpecialTokens());
        AstParser parser = new AstParser(scanner);

        try {
            Exp ast = parser.parseSeq();

            if (parser.hasErrors()) {
                handleErro(parser.errorsToString());
            } else {
                userInterface.setAstValue(ast.toString());

                ast.accept(preOrderVisitor);
                userInterface.setPreOrderValue(preOrderVisitor.getResult());

                ast.accept(inOrderVisitor);
                userInterface.setInOrderValue(inOrderVisitor.getResult());

                ast.accept(postOrderVisitor);
                userInterface.setPostOrderValue(postOrderVisitor.getResult());

                ast.accept(evalAssignExpVisitor);
                userInterface.setValue(evalAssignExpVisitor.getEvaluation() + "");
                userInterface.setEnviromentValue(evalAssignExpVisitor.getEnviroment().toString());

                /*TreeVisitor treeVisitor = new TreeVisitor();
                result.accept(treeVisitor);
                DefaultMutableTreeNode tree = treeVisitor.getEvaluation();
                JTree jTree = new JTree(tree);
                JScrollPane scrollPane = new JScrollPane(jTree);
                scrollPane.setPreferredSize(new Dimension(300, 400));
                userInterface.setTree(scrollPane);*/
            }
        } catch (IllegalArgumentException e) {
            handleErro(e.getMessage());
            e.printStackTrace();
        }
    }

    private KeyListener inputHandler() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getExtendedKeyCode() != KeyEvent.VK_BACK_SPACE && e.getExtendedKeyCode() != KeyEvent.VK_DELETE) {
                    JTextArea source = userInterface.getSourceCode();
                    source.setText(source.getText().concat(" "));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private void handleErro(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Errors", JOptionPane.ERROR_MESSAGE);
    }
}
