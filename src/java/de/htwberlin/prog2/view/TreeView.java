package de.htwberlin.prog2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by laura on 06.02.17. // == OLD!
 */
public class TreeView extends JFrame {

    private static final int MAX_CHARS = 3;
    private String stringInput;

    private JTextField textfInsert;
    private JTextField textfRemove;
    private JTextField textfSave;
    private JTextField textfLoad;

    private JButton butInsert;
    private JButton butRemove;
    private JButton butSave;
    private JButton butLoad;

    private JMenuItem[] toogleMenu;
    private final GridBagConstraints gridBagConstraints;
    private Canvas cnvs;


    public TreeView() {
        //Window
        setTitle("BinaryTree by Laura");
        setSize(1400, 700); //width, height
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //If JFrame instead of WinConst IntelliJ complains

        gridBagConstraints = new GridBagConstraints();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.setConstraints(this, gridBagConstraints);
        setLayout(gridBagLayout);

        setupTextFields();
        setUpButtons();
        setupCanvas();

        composeLayout();


        // Add ActionListener to buttons
        butRemove.addActionListener(new Action());
        butSave.addActionListener(new Action());
        butLoad.addActionListener(new Action());
        setVisible(true);
    }

    private void setupCanvas() {
        cnvs = new BinaryTreeCanvas();
        cnvs.setSize(1000, 600);
        cnvs.setBackground(Color.black);
    }

    private void setupTextFields() {
        textfInsert = new JTextField("");
        textfRemove = new JTextField("");
        textfSave = new JTextField("");
        textfLoad = new JTextField("");
        textfInsert.setColumns(10);
        textfRemove.setColumns(10);
        textfSave.setColumns(10);
        textfLoad.setColumns(10);
    }

    private void composeLayout() {
        addComponent(butInsert, 0, 0);
        addComponent(textfInsert, 1, 0);

        addComponent(butSave, 3, 0);
        addComponent(textfSave, 4, 0);

        addComponent(butLoad, 3, 1);
        addComponent(textfLoad, 4, 1);

        addComponent(butRemove, 0, 1);
        addComponent(textfRemove, 1, 1);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 4;
        add(cnvs, gridBagConstraints);
    }

    private void addComponent(Component component, int column, int row) {
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridx = column;
        gridBagConstraints.gridy = row;
        add(component, gridBagConstraints);
    }

    private void setUpButtons() {
        Bound bound = new Bound(20, 20, 130, 20);
        butInsert = createButton("Insert Node", bound);

        Bound removeBtnBound = new Bound().setX(20).setY(50).setWidth(130).setHeight(20);
        butRemove = createButton("Remove Node", removeBtnBound);

        Bound saveBtnBound = new Bound().setX(220).setY(20).setWidth(130).setHeight(20);
        butSave = createButton("Save Tree", saveBtnBound);

        Bound loadBtnBound = new Bound().setX(220).setY(50).setWidth(130).setHeight(20);
        butLoad = createButton("Load Tree", loadBtnBound);
    }

    private JButton createButton(String text, Bound bound) {
        JButton button = new JButton(text);
        button.setBounds(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight()); //x,y, width, height
        button.addActionListener(new Action());
        return button;
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
