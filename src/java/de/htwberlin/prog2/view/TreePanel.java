package de.htwberlin.prog2.view;


import de.htwberlin.prog2.model.BinaryTree;
import de.htwberlin.prog2.model.BinaryTreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;

/**
 * Created by laura on 08.02.17.
 */

public class TreePanel extends JPanel {

    private BinaryTree binaryTree;
    private PositionCalculator positionCalculator;
    private JButton[] jButtons;


    private ImageIcon nodeIcon = new ImageIcon(this.getClass().getResource("/img/CircleGreenTransparent.png"));


    public TreePanel() {
        this.setLayout(null);
        positionCalculator = new PositionCalculator();
    }

    public void setBinaryTree(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
        updateButtons();
    }

    private void updateButtons() {
        this.removeAll();
        addButtons();
    }

    private void addButtons() {
        if (binaryTree.getRoot() != null) {
            LinkedList<BinaryTreeNode> listOfNodes = binaryTree.treeAsList();

            jButtons = new JButton[listOfNodes.size()];
            int i = 0;

            for (BinaryTreeNode node : listOfNodes) {

                this.jButtons[i] = new JButton(node.getData());

                // icon
                this.jButtons[i].setIcon(nodeIcon);

                // border & background
                this.jButtons[i].setBorderPainted(false);
                this.jButtons[i].setContentAreaFilled(false);

                // set text font
                this.jButtons[i].setVerticalTextPosition(SwingConstants.CENTER);
                this.jButtons[i].setHorizontalTextPosition(SwingConstants.CENTER);
                this.jButtons[i].setFont(new Font("Arial", Font.PLAIN, 15));
                this.jButtons[i].setForeground(Color.GRAY);

                // set location
                positionCalculator.setPositions(binaryTree);
                PositionOfNode positionOfNode = positionCalculator.getSingleNodeFromList(node);
                this.jButtons[i].setBounds(positionOfNode.getX(), positionOfNode.getY(), positionOfNode.getIconSize(),
                        positionOfNode.getIconSize()); // ( x, y, width, height)

                // add  button to panel
                this.add(this.jButtons[i]);
                i++;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.setStroke(new BasicStroke(4));

        LinkedList<PositionOfLine> positionOfLineList = positionCalculator.getPositionOfLineList();

        for (PositionOfLine line : positionOfLineList) {

            g2.draw(new Line2D.Float(line.getX1(), line.getY1(), line.getX2(), line.getY2()));

        }
    }

    public void addTreePanelNodeListener(ActionListener listenerForNodeButton) {
        int size = binaryTree.getSize();
        for (int i = 0; i < size; i++) {
            JButton currentButton = this.jButtons[i];
            if (currentButton.getActionListeners().length == 0) {
                currentButton.addActionListener(listenerForNodeButton);
            }
        }
    }

}
