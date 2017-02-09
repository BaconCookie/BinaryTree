package de.htwberlin.prog2.datamodel;

import de.htwberlin.prog2.gui.DialogWindow;
import de.htwberlin.prog2.gui.ViewTree;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/**
 * Created by laura on 01.01.17.
 */

/**
 * Class Controller
 *
 * contains elements to:
 * Manage Balanced Tree
 * Manage ViewTree
 * Manage DialogWindow to insert or remove a node
 *  Action Listener
 * @param <T>
 */
public class Controller {

    private BalancedTree<String> balancedTree = new BalancedTree<>();



    private Listlabel<NodeData> nodeList;
    private ViewTree<T> viewTree;

    private DialogWindow dialogWindow;

    public Controller(){}



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
        public Controller(ViewTree<T> viewTree) {
            this.viewTree = viewTree;
            this.nodeList = new BinaryLinkedList<NodeData>();

            initDefaultTree();
            viewTree.setBinaryTree(nodeList);

            addActionListener();
        }

        /**
         * Fügt der viewTree alle Button Actionlistener hinzu
         */
        private void addActionListener() {
            this.viewTree.addNodeListener(new NodeListener());
            this.viewTree.addMenuNewListener(new MenuNewListener());
            this.viewTree.addMenuLoadListener(new MenuLoadListener());
            this.viewTree.addMenuSaveListener(new MenuSaveListener());
            this.viewTree.addSortAcsListener(new MenuSortAcsListener());
            this.viewTree.addSortDecsListener(new MenuSortDecsListener());
            this.viewTree.addMenuExitListener(new MenuExitListener());
        }

        /**
         * Erstellt ein Demo Binärbaum, der anschließend nach DESC sortiert wird
         */
        private void initDefaultTree() {
            this.nodeList.add(new NodeData("A"));
            this.nodeList.add(new NodeData("K"));
            this.nodeList.add(new NodeData("G22"));
            this.nodeList.add(new NodeData("C"));
            this.nodeList.add(new NodeData("D"));
            this.nodeList.add(new NodeData("G"));
            this.nodeList.add(new NodeData("F"));
            this.nodeList.add(new NodeData("G"));
            this.nodeList.add(new NodeData("K"));
            this.nodeList.add(new NodeData("I33"));
            this.nodeList.add(new NodeData("J"));
            this.nodeList.add(new NodeData("K"));
            this.nodeList.add(new NodeData("L"));
            this.nodeList.add(new NodeData("AA"));
            this.nodeList.sort(OrderBy.DESC);
        }

        /**
         * Führt ein update der View aus
         * <ul>
         * <li>1. Binärbaum der View übergeben</li>
         * <li>2. Actionlistener einfügen</li>
         * </ul>
         */
        void updateView() {
            this.viewTree.setBinaryTree(nodeList);

            addActionListener();
        }

        /**
         * Führt ein update der View in ein neues Fenster aus und schließt das vorherige
         * <ul>
         *     <li>1. Position der alten View sichern</li>
         *     <li>2. Alte View unsichbar schalten</li>
         *     <li>3. Neue View an gleicher Stelle und gleicher Dimension der alten View erstellen</li>
         *     <li>4. Binärbaum der View übergeben</li>
         *     <li>5. Actionlistener einfügen</li>
         * </ul>
         */
        void updateViewInNewWindow() {
            int sizeWith = viewTree.getSize().width;
            int sizeHeight = viewTree.getSize().height;
            int locationX = viewTree.getX();
            int locationY = viewTree.getY();

            this.viewTree.setVisible(false);

            this.viewTree = new View(sizeWith, sizeHeight);
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

                NodeData nodeData = nodeList.get(nodeId);

                dialogWindow = new DialogWindow(nodeId, nodeData.getContent());

                // add action listener for dialog
                dialogWindow.addRenameListener(new DialogRenameListener());
                dialogWindow.addAddListener(new DialogAddListener());
                dialogWindow.addRemoveListener(new DialogRemoveListener());
            }
        }

        /**
         * Actionelistener für Menü Button: neuen Baum anlegen
         */
        class MenuNewListener implements ActionListener {
            public void actionPerformed(ActionEvent arg0) {
                nodeList.clearAll();
                nodeList.add(new NodeData("AAA"));

                updateView();
            }
        }

        /**
         * Actionelistener für Menü Button: Binärbaum aus Json Datei laden
         */
        class MenuLoadListener implements ActionListener {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Json", "json", "JSON");
                chooser.setFileFilter(filter);

                chooser.setSelectedFile(new File("~/BinaryTree.json"));

                int chooseFile = chooser.showOpenDialog(null);

                if (chooseFile == JFileChooser.APPROVE_OPTION) {
                    Load load = new Load(chooser.getSelectedFile());
                    nodeList.setBinaryTreeFromList(load.getBinaryListArray());
                }

                updateViewInNewWindow();
            }
        }

        /**
         * Actionelistener für Menü Button: Binärbaum in Json Datei speicher
         */
        class MenuSaveListener implements ActionListener {
            public void actionPerformed(ActionEvent arg0) {

                JFileChooser chooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Json", "json", "JSON");
                chooser.setFileFilter(filter);

                chooser.setSelectedFile(new File("~/BinaryTree.json"));

                int chooseFile = chooser.showSaveDialog(null);

                if (chooseFile == JFileChooser.APPROVE_OPTION) {
                    Save save = new Save(chooser.getSelectedFile(), nodeList);
                }

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
                NodeData nodeData = new NodeData(dialogWindow.getText());

                nodeList.set(dialogWindow.getNodeId(), nodeData);

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
                nodeList.add(dialogWindow.getNodeId(), new NodeData(dialogWindow.getText()));

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
                nodeList.remove(dialogWindow.getNodeId());

                dialogWindow.setVisible(false);

                updateView();
            }
        }


}
