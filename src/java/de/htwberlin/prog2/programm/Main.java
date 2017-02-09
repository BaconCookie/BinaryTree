package de.htwberlin.prog2.programm;

import de.htwberlin.prog2.datamodel.BalancedTree;
import de.htwberlin.prog2.datamodel.BalancedTreeNode;
import de.htwberlin.prog2.gui.TreeView;
import de.htwberlin.prog2.gui.ViewTree;

/**
 * Created by laura on 14.01.17.
 */
public class Main <T extends Comparable<T>> {

        public static void main(String[] args) {


            final int defaultWidth = 660;
            final int defaultHeight = 550;
            ViewTree viewTree = new ViewTree<>(defaultWidth, defaultHeight);

            viewTree.setLocationRelativeTo(null);



            BalancedTree<String> tree = new BalancedTree<>();
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
            tree.printTree();

         /*   tree.saveTree();
            tree.clear();
            tree.loadTree();
            tree.printTree();
            */



/*
            BalancedTree<String> smallTree = new BalancedTree<>();
            smallTree.insert("a");
            smallTree.insert("b");
            smallTree.insert("c");
            //smallTree.printTree();
tree.printTreeFromList(tree.treeAsList());

           System.out.println("search a:");
            tree.getNode("a");
            tree.remove(tree.getNode("a"));
*/



            BalancedTreeNode<String> testNode = new BalancedTreeNode<String>("yyy");
            BalancedTreeNode<String> nodeToRemove = tree.getNode(testNode.getData());

            tree.remove(nodeToRemove);


            System.out.println("after remove:");
            tree.printTree();


        }



}
