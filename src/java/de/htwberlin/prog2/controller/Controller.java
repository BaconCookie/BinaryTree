package de.htwberlin.prog2.controller;

import de.htwberlin.prog2.dataAccess.TreeIO;
import de.htwberlin.prog2.model.BinaryTree;
import de.htwberlin.prog2.view.BinaryTreeCanvas;
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
        positionCalculator.setPositions(binaryTree);
        view.setBinaryTree(binaryTree);
        addActionListener();
    }

    /**
     * Method which inserts new data as a new node in the tree
     * Stringlength of data MAX_CHARS == 3
     * If the string is any longer, this method will take the first three characters as a substring
     */
    public void insertInTree(String data) {
        try {
            if (data.length() > MAX_CHARS) {
                binaryTree.insert(data.substring(0, 3));
            } else {
                binaryTree.insert(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in insertInTree.");
        }
        sendUpdateToView();
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
     * Adds ActionListener for all menu buttons
     */
    private void addActionListener() {
        this.view.addNodeListener(new NodeListener());
        this.view.addMenuNewListener(new MenuNewListener());
        this.view.addMenuLoadListener(new MenuLoadListener());
        this.view.addMenuSaveListener(new MenuSaveListener());
        this.view.addMenuExitListener(new MenuExitListener());
    }

    /**
     * Führt ein update der View aus
     * <ul>
     * <li>1. Binärbaum der View übergeben</li>
     * <li>2. Actionlistener einfügen</li>
     * </ul>
     */
    void sendUpdateToView() {
        this.view.setBinaryTree(binaryTree);
        addActionListener(); //TODO check if needed here
    }

    /**
     * Führt ein update der View in ein neues Fenster aus und schließt das vorherige
     * <ul>
     * <li>1. Position der alten View sichern</li>
     * <li>2. Alte View unsichbar schalten</li>
     * <li>3. Neue View an gleicher Stelle und gleicher Dimension der alten View erstellen</li>
     * <li>4. Binärbaum der View übergeben</li>
     * <li>5. Actionlistener einfügen</li>
     * </ul>
     */
    void updateViewInNewWindow() {
        int width = view.getSize().width;
        int height = view.getSize().height;
        int locationX = view.getX();
        int locationY = view.getY();

        this.view.setVisible(false);

        this.view = new View();
        this.view.setLocation(locationX, locationY);

        sendUpdateToView();
    }


    /**
     * ActionListener for all nodes
     */
    class NodeListener implements ActionListener {
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
    class MenuNewListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            binaryTree.clearTree();
            sendUpdateToView();
        }
    }

    /**
     * ActionListener for Menu Button "Load Tree"
     */
    class MenuLoadListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            loadTree();
        }
    }

    /**
     * ActionListener for Menu Button "Save Tree"
     */
    class MenuSaveListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            saveTree();
        }
    }

    /**
     * ActionListener for Menu Button "Exit"
     */
    class MenuExitListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }

    /**
     * ActionListener for Dialog Window "Insert Node"
     */
    class DialogInsertListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            insertInTree(dialogWindow.getText());

            dialogWindow.setVisible(false);
        }
    }

    /**
     * ActionListener for Dialog Window "Remove Node"
     */
    class DialogRemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            removeFromTree(dialogWindow.getText());

            dialogWindow.setVisible(false);
        }
    }


}
