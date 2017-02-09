package de.htwberlin.prog2.gui;


import de.htwberlin.prog2.datamodel.BalancedTreeNode;
import de.htwberlin.prog2.datamodel.BalancedTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Created by laura on 08.02.17.
 */
public class ViewTree extends JFrame {


    private LinkedList<BalancedTreeNode> listOfNodes = new LinkedList<>();
    private BalancedTree balancedTree;
    private TreePanel treePanel;


    JScrollPane jScrollPane;

    private JMenuBar jMenuBar;

    public ViewTree(int width, int height) {
        this.setTitle("Balanced Tree by Laura");

        this.setSize(width, height);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        createMenuBar();

        this.setVisible(true);
    }

    private void createMenuBar() {
        this.jMenuBar = new JMenuBar();

        ImageIcon iconFile = new ImageIcon(this.getClass().getResource("/icomoon-free_2014-12-23_menu_16_0_000000_none.png"));
        ImageIcon iconNew = new ImageIcon(this.getClass().getResource("/font-awesome_4-7-0_file-o_16_0_000000_none.png"));
        ImageIcon iconSave = new ImageIcon(this.getClass().getResource("/font-awesome_4-7-0_save_16_0_000000_none.png"));
        ImageIcon iconLoad = new ImageIcon(this.getClass().getResource("/font-awesome_4-7-0_upload_16_0_000000_none.png"));
        ImageIcon iconExit = new ImageIcon(this.getClass().getResource("/icomoon-free_2014-12-23_exit_16_0_000000_none.png"));

        JMenu file = new JMenu("File");
        file.setIcon(iconFile);
        file.setMnemonic(KeyEvent.VK_F);


        // New Tree Button
        JMenuItem eMenuItemNew = new JMenuItem("New Tree");
        eMenuItemNew.setIcon(iconNew);
        eMenuItemNew.setMnemonic(KeyEvent.VK_E);
        eMenuItemNew.setToolTipText("Create a new Tree");

        // Loading Button
        JMenuItem eMenuItemLoad = new JMenuItem("Load File");
        eMenuItemLoad.setIcon(iconLoad);
        eMenuItemLoad.setMnemonic(KeyEvent.VK_E);
        eMenuItemLoad.setToolTipText("Load a Tree from File");

        // Saving Button
        JMenuItem eMenuItemSave = new JMenuItem("Save File");
        eMenuItemSave.setIcon(iconSave);
        eMenuItemSave.setMnemonic(KeyEvent.VK_E);
        eMenuItemSave.setToolTipText("Save a Tree from File");

        // Exit Button
        JMenuItem eMenuItemExit = new JMenuItem("Exit");
        eMenuItemExit.setIcon(iconExit);
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

        treePanel = new TreePanel();
        JPanel jPanel = treePanel.getJPanel(listOfNodes);

        jPanel.setPreferredSize(new Dimension(balancedTree.getWidth(), balancedTree.getHeight()));
        jScrollPane = new JScrollPane(jPanel);
        jPanel.setAutoscrolls(true);
        add(jScrollPane);

        validate();
        repaint();
    }

    public void setBalancedTree(LinkedList<BalancedTreeNode> listOfNodes) {
        this.listOfNodes = listOfNodes;
        updateView();
    }

    public void addNodeListener(ActionListener listenerForNodeButton) {
        treePanel.addNodeListener(listenerForNodeButton);
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

    }