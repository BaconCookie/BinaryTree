package de.htwberlin.prog2.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Created by laura on 08.02.17.
 */

/**
 * Dialog Fenster welches erscheint nachdem ein Knoten gedrückt worden ist.
 * Welches folgenede Optionen liefert.
 * <ul>
 * <li>Knoten hinzufügen</li>
 * <li>Knoten ändern</li>
 * <li>Knoten löschen</li>
 * </ul>
 */
public class DialogWindow extends JFrame {

    private int nodeId;

    private JTextPane jTextPane;
    private JButton addButton = new JButton("ADD");
    private JButton removeButton = new JButton("REMOVE");

    public DialogWindow(int nodeId, String nodeData) {
        this.nodeId = nodeId;

        this.setTitle(Integer.toString(nodeId) + ": " + nodeData);

        this.setSize(400, 100);

        this.setLayout(new FlowLayout());

        this.jTextPane = new JTextPane();
        this.jTextPane.setText(nodeData);
        add(this.jTextPane);
        add(this.addButton);
        add(this.removeButton);

        this.setVisible(true);
    }


    /**
     * Button Knoten hinzufügen
     *
     * @param listenerForAddButton ActionListener
     */
    public void addAddListener(ActionListener listenerForAddButton) {
        this.addButton.addActionListener(listenerForAddButton);
    }

    /**
     * Button Knoten löschen
     *
     * @param listenerForRemoveButton ActionListener
     */
    public void addRemoveListener(ActionListener listenerForRemoveButton) {
        this.removeButton.addActionListener(listenerForRemoveButton);
    }

    /**
     * Gibt das Textfeld des DialogWindows zurück
     *
     * @return Textfeld des Dialogfenster als String
     */
    public String getText() {
        return this.jTextPane.getText();
    }

    /**
     * Gibt die Knoten ID des DialogWindow zurück
     *
     * @return Knoten ID
     */
    public int getNodeId() {
        return this.nodeId;
    }
}
