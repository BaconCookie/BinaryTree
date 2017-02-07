package de.htwberlin.prog2.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by laura on 06.02.17.
 */
public class TreeView extends JFrame {

    private static final int MAX_CHARS = 3;
    private String stringInput;
    private Color backgroundColor;

    private JLabel labelInsert;
    private JLabel labelRemove;
    private JLabel labelSave;
    private JLabel labelLoad;

    private JTextField textfInsert;
    private JTextField textfRemove;
    private JTextField textfSave;
    private JTextField textfLoad;

    private JButton butInsert;
    private JButton butRemove;
    private JButton butSave;
    private JButton butLoad;

    private JMenuItem[] toogleMenu;


    public TreeView() {


        //Window
        setTitle("BalancedTree by Laura");
        setSize(1400, 700); //width, height
        backgroundColor = new Color(130, 225, 100);
        setBackground(backgroundColor);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //If JFrame instead of WinConst IntelliJ complains
        setLayout(new FlowLayout());

        setVisible(true);

        /*
         * MenuBar and menu components
         */
        JMenu treeMenu = new JMenu("File");
        JMenuItem closeTree = new JMenuItem("Exit");
        JMenuItem saveTree = new JMenuItem("Save Tree");
        JMenuItem loadTree = new JMenuItem("Load Tree");
        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutItem = new JMenuItem("About");

        treeMenu.add(saveTree);
        treeMenu.add(closeTree);
        treeMenu.add(loadTree);
        aboutMenu.add(aboutItem);

        toogleMenu = new JMenuItem[3];
        toogleMenu[0] = saveTree;
        toogleMenu[1] = closeTree;
        toogleMenu[2] = aboutItem;

        /*
         * adding the menu bar to the main frame
         */
        this.setJMenuBar(new JMenuBar());
        this.getJMenuBar().add(treeMenu);
        this.getJMenuBar().add(aboutMenu);


/*
        //Add labels
        add(labelInsert = new JLabel("Insert"));
        labelInsert.setBounds(30,20, 70, 20); //x,y, width, height
        add(labelRemove) = new JLabel("Remove");
        labelRemove.setBounds(30,50, 70, 20);
        add(labelSave) = new JLabel("Save");
        labelSave.setBounds(600, 20, 70, 20);
        add(labelLoad) = new JLabel("Load");
        labelLoad.setBounds(600, 50, 70, 20);
*/
        //Add textfields
        add(textfInsert = new JTextField(""));
        textfInsert.setBounds(160, 20, 40, 20); //x,y, width, height
        add(textfRemove = new JTextField(""));
        textfRemove.setBounds(160, 50, 40, 20);
        add(textfSave = new JTextField(""));
        textfSave.setBounds(360, 20, 60, 20);
        add(textfLoad = new JTextField(""));
        textfLoad.setBounds(360, 50, 60, 20);

        //Add buttons
        add(butInsert = new JButton("Insert Node"));
        butInsert.setBounds(20, 20, 130, 20); //x,y, width, height
        add(butRemove = new JButton("Remove Node"));
        butRemove.setBounds(20, 50, 130, 20);
        add(butSave = new JButton("Save Tree"));
        butSave.setBounds(220, 20, 130, 20);
        add(butLoad = new JButton("Load Tree"));
        butLoad.setBounds(220, 50, 130, 20);

        // Add ActionListener to buttons
        butInsert.addActionListener(new Action());
        butRemove.addActionListener(new Action());
        butSave.addActionListener(new Action());
        butLoad.addActionListener(new Action());



//repaint(); //triggers update

    }

    public void paint(Graphics g){
        setBackground(backgroundColor);
        g.setColor(Color.green);
        g.fillOval(750,30,80,60); //x,y, width, height

    }

    /**
     * Methods to add ActionListeners
     */
    public void addMenuListener(ActionListener listenerForMenuClick) {
        for (int i = 0; i < toogleMenu.length; i++) {
            toogleMenu[i].addActionListener(listenerForMenuClick);
        }
    }

    //Inner class to handle clicks on buttons
    private class Action implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            stringInput = textfInsert.getText();
            if (stringInput.length() > MAX_CHARS) {
                stringInput = stringInput.substring(0, 2);
            }


        }
    }




}
