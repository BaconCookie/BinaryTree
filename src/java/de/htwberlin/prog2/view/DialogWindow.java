package de.htwberlin.prog2.view;

import de.htwberlin.prog2.model.BinaryTreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Created by laura on 08.02.17.
 */

/**
 * Dialog Window which appears if user clicks on a node
 *
 * Shows user two buttons with the options:
 * "Insert Node"
 * "Remove Node"
 */
public class DialogWindow extends JFrame {

    private JTextPane jTextPane;
    private JButton insertButton = new JButton("Insert Node");
    private JButton removeButton = new JButton("Remove Node");

    public DialogWindow(BinaryTreeNode node) {
        this.setSize(400, 100);

        this.setLayout(new FlowLayout());

        this.jTextPane = new JTextPane();
        this.jTextPane.setText(node.getData());
        add(this.jTextPane);
        add(this.insertButton);
        add(this.removeButton);

        this.setVisible(true);
    }

    /**
     * Button "Insert Node"
     *
     * @param listenerForInsertButton ActionListener
     */
    public void addInsertListener(ActionListener listenerForInsertButton) {
        this.insertButton.addActionListener(listenerForInsertButton);
    }

    /**
     * Button "Remove Node"
     *
     * @param listenerForRemoveButton ActionListener
     */
    public void addRemoveListener(ActionListener listenerForRemoveButton) {
        this.removeButton.addActionListener(listenerForRemoveButton);
    }

    /**
     * Gets text from jTextPane
     *
     * @return text from jTextPane as a String
     */
    public String getText() {
        return this.jTextPane.getText();
    }

}
