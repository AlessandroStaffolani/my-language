package view;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {

    private JPanel userInterface;
    private JLabel titleLabel;
    private JButton closeButton;
    private JTextArea sourceCode;
    private JButton compileButton;
    private JTextField preOrderValue;
    private JTextField inOrderValue;
    private JTextField postOrderValue;
    private JTextField value;
    private JPanel treePanel;
    private JTextField astValue;
    private JTextArea enviromentValue;

    public UserInterface() {

        setContentPane(userInterface);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

    }

    public String getSourceCodeValue() {
        return sourceCode.getText().trim();
    }

    public void setTree(JScrollPane scrollPane) {
        this.treePanel.add(scrollPane);
    }

    public void setAstValue(String value) {
        this.astValue.setText(value);
    }

    public void setPreOrderValue(String value) {
        this.preOrderValue.setText(value);
    }

    public void setInOrderValue(String value) {
        this.inOrderValue.setText(value);
    }

    public void setPostOrderValue(String value) {
        this.postOrderValue.setText(value);
    }

    public void setValue(String value) {
        this.value.setText(value);
    }

    public void setEnviromentValue(String value) {
        this.enviromentValue.setText(value);
    }

    public JTextArea getSourceCode() { return sourceCode; }

    public JButton getCompileButton() { return compileButton; }

    public JButton getCloseButton() {
        return closeButton;
    }
}