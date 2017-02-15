package de.htwberlin.prog2.view;


import de.htwberlin.prog2.model.BinaryTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Created by laura on 08.02.17.
 */
public class View extends JFrame {


    private LinkedList<PositionOfLine> PositionOfLine; // = new LinkedList();

    //private LinkedList<BinaryTreeNode> listOfNodes;
    private TreePanel treePanel;
    private DialogWindow dialogWindow;

    JScrollPane jScrollPane;
    private BinaryTree binaryTree;
    private JMenuBar jMenuBar;
    private JButton[] jButtons;

    public View() {

        //Window
        setTitle("BinaryTree by Laura");
        setSize(1000, 600); //width, height
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //If JFrame instead of WinConst IntelliJ complains
        createMenuBar();
        dialogWindow = new DialogWindow();
        this.treePanel = new TreePanel();

        setVisible(true);

    }

    private void createMenuBar() {
        this.jMenuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        // New Tree Button
        JMenuItem eMenuItemNew = new JMenuItem("New Tree");
        eMenuItemNew.setMnemonic(KeyEvent.VK_E);
        eMenuItemNew.setToolTipText("Create a new Tree");

        // Loading Button
        JMenuItem eMenuItemLoad = new JMenuItem("Load File");
        eMenuItemLoad.setMnemonic(KeyEvent.VK_E);
        eMenuItemLoad.setToolTipText("Load a Tree from File");

        // Saving Button
        JMenuItem eMenuItemSave = new JMenuItem("Save File");
        eMenuItemSave.setMnemonic(KeyEvent.VK_E);
        eMenuItemSave.setToolTipText("Save a Tree from File");

        // Exit Button
        JMenuItem eMenuItemExit = new JMenuItem("Exit");
        eMenuItemExit.setMnemonic(KeyEvent.VK_E);
        eMenuItemExit.setToolTipText("Exit");

        // add buttons
        file.add(eMenuItemNew);
        file.add(eMenuItemLoad);
        file.add(eMenuItemSave);
        file.add(eMenuItemExit);

        this.jMenuBar.add(file);

        setJMenuBar(this.jMenuBar);
    }

    private void updateView() {
        getContentPane().removeAll();
        getContentPane().invalidate();

        treePanel.setBinaryTree(binaryTree);

        jScrollPane = new JScrollPane(treePanel);
        jScrollPane.setAutoscrolls(true);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setPreferredSize(new Dimension(1000, 600));
        treePanel.repaint();
        jScrollPane.setViewportView(treePanel);
        jScrollPane.setSize(new Dimension(1000, 600));
        add(jScrollPane);
        jScrollPane.repaint();
        //TODO scrollbars not working yet (they appear but have no function)
        validate();
    }

    public void setBinaryTree(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
        treePanel.setBinaryTree(binaryTree);
        updateView();
    }

    public void openDialogToInsertFirstNode() {
        //dialogWindow = new DialogWindow();
        dialogWindow.setVisible(true);
    }

    public String getStringFromDialogWindow() {
        return dialogWindow.getText();
    }

    public void addTreePanelNodeListener(ActionListener listenerForNodeButton) {
        treePanel.addTreePanelNodeListener(listenerForNodeButton);
    }

    public void addDialogWindowInsertListener(ActionListener listenerForInsertButton) {
        dialogWindow.addInsertListener(listenerForInsertButton);
    }

    public void addDialogWindowRemoveListener(ActionListener listenerForRemoveButton) {
        dialogWindow.addRemoveListener(listenerForRemoveButton);
    }

    public void addMenuNewListener(ActionListener listenerForMenuNew) {
        jMenuBar.getMenu(0).getItem(0).addActionListener(listenerForMenuNew);
    }

    public void addMenuLoadListener(ActionListener listenerForMenuLoad) {
        jMenuBar.getMenu(0).getItem(1).addActionListener(listenerForMenuLoad);
    }

    public void addMenuSaveListener(ActionListener listenerForMenuSave) {
        jMenuBar.getMenu(0).getItem(2).addActionListener(listenerForMenuSave);
    }

    public void addMenuExitListener(ActionListener listenerForMenuExit) {
        jMenuBar.getMenu(0).getItem(3).addActionListener(listenerForMenuExit);
    }

    public DialogWindow getDialogWindow() {
        return dialogWindow;
    }

    public TreePanel getTreePanel() {
        return treePanel;
    }
}