package de.htwberlin.prog2.controller;

import de.htwberlin.prog2.dataAccess.TreeIO;
import de.htwberlin.prog2.model.BinaryTree;
import de.htwberlin.prog2.view.DialogWindow;
import de.htwberlin.prog2.view.PositionCalculator;
import de.htwberlin.prog2.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InvalidObjectException;
/**
 * Created by laura on 01.01.17.
 */

/**
 * Class Controller
 * <p>
 * for:
 * Manage Balanced Tree
 * Manage View
 * Manage DialogWindow to insert or remove a node
 * Action Listener implementation
 */
public class Controller {

    private static final int MAX_CHARS = 3;
    private BinaryTree binaryTree;
    private View view;
    private DialogWindow dialogWindow;
    private PositionCalculator positionCalculator;

    public Controller() {
    }

    //actionlistener implementieren,  actionperformed


    /**
     * Konstruktor des Controllers
     * <ul>
     * <li>1. Erstellt ein Demo Binärbaum</li>
     * <li>2. Übergibt die Binärbaum informationen der View</li>
     * <li>3. Fügt die Actionen Listener für jeden Button hinzu</li>
     * </ul>
     *
     * @param view Die View für das haupt Fenster
     */
    public Controller(View view) {
        this.view = view;
    }

    public void runTree() {
        if (binaryTree != null) {
         //   positionCalculator.setPositions(binaryTree);
            this.view.setBinaryTree(binaryTree);
            addAllActionListener();
        } else {
            // this.dialogWindow = new DialogWindow();
            this.view.openDialogToInsertFirstNode();
            addActionListenerForCaseTreeIsEmpty();
            String data = this.view.getStringFromDialogWindow();
            insertInTree(data);
        }
    }

    /**
     * Method which inserts new data as a new node in the tree
     * Stringlength of data MAX_CHARS == 3
     * If the string is any longer, this method will take the first three characters as a substring
     */
    public void insertInTree(String data) {
        try {
            if (binaryTree == null) {
                binaryTree = new BinaryTree();
            }
            insertInExistingTree(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in insertInTree.");
        }
        sendUpdateToView();
    }

    private void insertInExistingTree(String data) {
        if (data.length() > MAX_CHARS) {
            binaryTree.insert(data.substring(0, 3));
        } else {
            binaryTree.insert(data);
        }
    }

    /**
     * Method which removes a node from the tree
     *
     * @param data data from node which should be removed from the tree
     * @throws InvalidObjectException if "Node not found in Tree."
     */
    public void removeFromTree(String data) {
        try {
            if (binaryTree.search(data)) {
                binaryTree.remove(binaryTree.getNode(data));
            } else {
                throw new InvalidObjectException("Node not found in Tree.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in removeFromTree.");
        }
        sendUpdateToView();
    }


    /**
     * Method which saves tree in a file
     * The file is being placed in the folder of this program.
     */
    public void saveTree() {
        try {
            TreeIO treeIO = new TreeIO();
            String filePath = "./BinaryTree.tree";
            treeIO.save(binaryTree, filePath);
        } catch (IOException e) {
            System.out.println("Error while saving.");
        }
        sendUpdateToView();
    }

    /**
     * Method which loads previously saved tree from a file.
     * The file is being loaded from the folder of this program.
     *
     * @return Loaded tree
     * @throws RuntimeException in case of a caught Exception
     */
    public void loadTree() {
        try {
            TreeIO treeIO = new TreeIO();

            String inputPath = "./BinaryTree.tree";
            BinaryTree loadedTree = treeIO.load(inputPath);
            binaryTree = loadedTree;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading.");
            throw new RuntimeException(e);
        }
        sendUpdateToView();
    }


    /**
     * Führt ein update der View aus
     * <ul>
     * <li>1. Binärbaum der View übergeben</li>
     * <li>2. Actionlistener einfügen</li>
     * </ul>
     */
    void sendUpdateToView() {
        view.setBinaryTree(binaryTree);
        runTree();
    }

    /**
     * Adds ActionListener for all menu buttons, the dialog window and the tree panel
     */
    private void addAllActionListener() {
        view.addTreePanelNodeListener(new NodeListener());
//        dialogWindow.addInsertListener(new DialogInsertListener());
        view.addDialogWindowInsertListener(new DialogInsertListener());
        view.addDialogWindowRemoveListener(new DialogRemoveListener());
        view.addMenuNewListener(new MenuNewListener());
        view.addMenuLoadListener(new MenuLoadListener());
        view.addMenuSaveListener(new MenuSaveListener());
        view.addMenuExitListener(new MenuExitListener());
    }

    private void addActionListenerForCaseTreeIsEmpty() {
        //dialogWindow.addInsertListener(new DialogInsertListener());
        view.addDialogWindowInsertListener(new DialogInsertListener());
        view.addMenuNewListener(new MenuNewListener());
        view.addMenuLoadListener(new MenuLoadListener());
        view.addMenuSaveListener(new MenuSaveListener());
        view.addMenuExitListener(new MenuExitListener());
    }


    /**
     * ActionListener for all nodes
     */
    private class NodeListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            JButton jButton = (JButton) arg0.getSource();
            String s = jButton.getText();
            dialogWindow = new DialogWindow(binaryTree.getNode(s));

            // add action listener for dialog
            dialogWindow.addInsertListener(new DialogInsertListener());
            dialogWindow.addRemoveListener(new DialogRemoveListener());
        }
    }

    /**
     * ActionListener for Menu Button "Clear Tree"
     */
    private class MenuNewListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            binaryTree.clearTree();
            sendUpdateToView();
        }
    }

    /**
     * ActionListener for Menu Button "Load Tree"
     */
    private class MenuLoadListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            loadTree();
        }
    }

    /**
     * ActionListener for Menu Button "Save Tree"
     */
    private class MenuSaveListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            saveTree();
        }
    }

    /**
     * ActionListener for Menu Button "Exit"
     */
    private class MenuExitListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }

    /**
     * ActionListener for Dialog Window "Insert Node"
     */
    private class DialogInsertListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            insertInTree(dialogWindow.getText());

            dialogWindow.setVisible(false);
        }
    }

    /**
     * ActionListener for Dialog Window "Remove Node"
     */
    private class DialogRemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            removeFromTree(dialogWindow.getText());

            dialogWindow.setVisible(false);
        }
    }


}
