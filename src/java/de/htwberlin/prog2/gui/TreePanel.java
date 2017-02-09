package de.htwberlin.prog2.gui;


import de.htwberlin.prog2.datamodel.BalancedTree;
import de.htwberlin.prog2.datamodel.BalancedTreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;

/**
 * Created by laura on 08.02.17.
 */
public class TreePanel extends JPanel {

    private JPanel jPanel;
    private BalancedTree balancedTree;
    //private LinkedList<DrawLines> drawLinesLinkedList;

//    LinkedList<BalancedTreeNode> listOfNodes = balancedTree.treeAsList();
    public JButton[] jButtons;
    private ImageIcon nodeIcon = new ImageIcon(this.getClass().getResource("/img/CircleGreenTransparent.png"));


    public TreePanel() {
        this.setLayout(null);
    }

    public JPanel getJPanel(BalancedTree balancedTree) {
        this.balancedTree = balancedTree;
        //this.listOfNodes = listOfNodes;
        update();
        return this;
    }

    private void addButton(int id) {

        BalancedTreeNode node = balancedTree.searchNodeById(id);

        ViewPosition viewPosition = node.viewPosition;

        this.jButtons[id] = new JButton(node.getData());

        // icon
        this.jButtons[id].setIcon(nodeIcon);

        // boarder & background
        this.jButtons[id].setBorderPainted(false);
        this.jButtons[id].setContentAreaFilled(false);

        // set text font
        this.jButtons[id].setVerticalTextPosition(SwingConstants.CENTER);
        this.jButtons[id].setHorizontalTextPosition(SwingConstants.CENTER);
        this.jButtons[id].setFont(new Font("Arial", Font.PLAIN, 15));
        this.jButtons[id].setForeground(Color.GRAY);

        // set location
        this.jButtons[id].setBounds(viewPosition.getX(), viewPosition.getY(), viewPosition.getIconSize(), viewPosition.getIconSize()); // ( x, y, with, high)

        // set id as button name //TODO maybe not needed
        this.jButtons[id].setName(Integer.toString(id));

        // add  button to panel
        this.add(this.jButtons[id]);
    }

    private void update() {
        this.removeAll();
        balancedTree.getSize();
        int size = balancedTree.getSize();
        this.jButtons = new JButton[size];
        for (int i = 0; i < size; i++) {
            addButton(i);
        }
    }

    protected void paintComponent(Graphics g) {
        LinkedList<DrawLines> drawLinesLinkedList = balancedTree.getDrawLines();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        int size = drawLinesLinkedList.size();

        for (int i = 0; i < size; i++) {
            DrawLines tempLine = drawLinesLinkedList.get(i);
            g2.draw(new Line2D.Float(tempLine.getX1(), tempLine.getY1(), tempLine.getX2(), tempLine.getY2()));

        }
    }


    public void addNodeListener(ActionListener listenerForNodeButton) {
        balancedTree.getSize();
        int size = balancedTree.getSize();
        //LinkedList<DrawLines> drawLinesLinkedList = balancedTree.getDrawLines();
        //int size = drawLinesLinkedList.size();
        for (int i = 0; i < size; i++) {
            this.jButtons[i].addActionListener(listenerForNodeButton);
        }
    }
}
