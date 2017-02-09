package de.htwberlin.prog2.controller;

import de.htwberlin.prog2.datamodel.BalancedTree;
import de.htwberlin.prog2.datamodel.BalancedTreeNode;
import de.htwberlin.prog2.gui.DialogWindow;
import de.htwberlin.prog2.gui.ViewTree;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
/**
 * Created by laura on 01.01.17.
 */

/**
 * Class Controller
 *
 * for:
 * Manage Balanced Tree
 * Manage ViewTree
 * Manage DialogWindow to insert or remove a node
 * Action Listener implementation
 *
 */
public class Controller {

    private BalancedTree balancedTree;

    private LinkedList<BalancedTreeNode> listOfNodes;

    private ViewTree viewTree;

    private DialogWindow dialogWindow;

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
     * @param viewTree Die View für das haupt Fenster
     */
    public Controller(ViewTree viewTree) {
        this.viewTree = viewTree;

    }

    public void runDefaultTree(){

        initDefaultTree();

        viewTree.setBalancedTree(balancedTree);

        addActionListener();
    }

    private void initDefaultTree() {
        BalancedTree tree = new BalancedTree();
        tree.insert("a");
        tree.insert("yyy");
        tree.insert("uio");
        tree.insert("r");
        tree.insert("xx");
        tree.insert("ibb");
        tree.insert("zzz");
        tree.insert("ab");
        tree.insert("uuu");
        tree.insert("vvv");
        balancedTree = tree;
        //listOfNodes = balancedTree.treeAsList();
    }

    /**
     * Fügt der viewTree alle Button Actionlistener hinzu
     */
    private void addActionListener() {
        this.viewTree.addNodeListener(new NodeListener());
        this.viewTree.addMenuNewListener(new MenuNewListener());
        this.viewTree.addMenuLoadListener(new MenuLoadListener());
        this.viewTree.addMenuSaveListener(new MenuSaveListener());
        this.viewTree.addMenuExitListener(new MenuExitListener());
    }

    /**
     * Führt ein update der View aus
     * <ul>
     * <li>1. Binärbaum der View übergeben</li>
     * <li>2. Actionlistener einfügen</li>
     * </ul>
     */
    void updateView() {
        this.viewTree.setBalancedTree(balancedTree);

        addActionListener();
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
        int width = viewTree.getSize().width;
        int height = viewTree.getSize().height;
        int locationX = viewTree.getX();
        int locationY = viewTree.getY();

        this.viewTree.setVisible(false);

        this.viewTree = new ViewTree(width, height);
        this.viewTree.setLocation(locationX, locationY);

        updateView();
    }


    //INNER CLASS - Action Listener

    /**
     * Actionelistener für alle Binärbaum Knoten
     */
    class NodeListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            JButton jButton = (JButton) arg0.getSource();

            int nodeId = Integer.parseInt(jButton.getName());

            BalancedTreeNode node = listOfNodes.get(nodeId);

            dialogWindow = new DialogWindow(nodeId, node.getData());

            // add action listener for dialog
            dialogWindow.addAddListener(new DialogAddListener());
            dialogWindow.addRemoveListener(new DialogRemoveListener());
        }
    }

    /**
     * Actionelistener für Menü Button: neuen Baum anlegen
     */
    class MenuNewListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            balancedTree.clear();
            updateView();
        }
    }

    /**
     * Actionelistener für Menü Button: Binärbaum aus Json Datei laden
     */
    class MenuLoadListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            balancedTree.loadTree();
            /*
            JFileChooser chooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Json", "json", "JSON");
            chooser.setFileFilter(filter);

            chooser.setSelectedFile(new File("./BalancedTree.json"));

            int chooseFile = chooser.showOpenDialog(null);

            if (chooseFile == JFileChooser.APPROVE_OPTION) {
                Load load = new Load(chooser.getSelectedFile());
                listOfNodes.setBinaryTreeFromList(load.getBinaryListArray());
            }
    */
            updateViewInNewWindow();
        }
    }

    /**
     * Actionelistener für Menü Button: Binärbaum in Json Datei speicher
     */
    class MenuSaveListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            balancedTree.saveTree();
            /*

            JFileChooser chooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Json", "json", "JSON");
            chooser.setFileFilter(filter);

            chooser.setSelectedFile(new File("./BinaryTree.json"));

            int chooseFile = chooser.showSaveDialog(null);

            if (chooseFile == JFileChooser.APPROVE_OPTION) {
                Save save = new Save(chooser.getSelectedFile(), listOfNodes);
            }
            */

            updateView();

        }
    }

    /**
     * Actionelistener für Menü Button: Exit
     */
    class MenuExitListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }

    /**
     * Actionelistener für Dialog Window:
     * <ul>
     * <li>ändern eines vorhandenen Knoten</li>
     * </ul>
     */
    class DialogRenameListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            BalancedTreeNode nodeData = new BalancedTreeNode(dialogWindow.getText());

            listOfNodes.set(dialogWindow.getNodeId(), nodeData);

            dialogWindow.setVisible(false);
            updateView();
        }
    }

    /**
     * Actionelistener für Dialog Window:
     * <ul>
     * <li>Hinzufügen von neuen Knoten</li>
     * </ul>
     */
    class DialogAddListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            listOfNodes.add(dialogWindow.getNodeId(), new BalancedTreeNode(dialogWindow.getText()));

            dialogWindow.setVisible(false);

            updateView();
        }
    }

    /**
     * Actionelistener für Dialog Window:
     * <ul>
     * <li>löschen eines Knotens</li>
     * </ul>
     */
    class DialogRemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            listOfNodes.remove(dialogWindow.getNodeId());

            dialogWindow.setVisible(false);

            updateView();
        }
    }


}
