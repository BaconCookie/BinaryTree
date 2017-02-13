package de.htwberlin.prog2.view;

import de.htwberlin.prog2.model.BinaryTree;
import de.htwberlin.prog2.model.BinaryTreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by laura on 07.02.17.
 */
public class BinaryTreeCanvas extends java.awt.Canvas {

    private BinaryTree binaryTree;
    public JButton[] jButtons;

    public void addNodeListener(ActionListener listenerForNodeButton) {
        int size = binaryTree.getSize();
        for (int i = 0; i < size; i++) {
            this.jButtons[i].addActionListener(listenerForNodeButton);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics g2 = this.getGraphics();
        g2.setColor(new Color(255, 0, 0));
        g2.drawString("Hello", 200, 200);
    }
}
