package de.htwberlin.prog2.programm;

import de.htwberlin.prog2.controller.Controller;
import de.htwberlin.prog2.model.BinaryTree;
import de.htwberlin.prog2.view.View;

import java.util.LinkedList;

/**
 * Created by laura on 14.01.17.
 */
public class Main {

        public static void main(String[] args) {

            View viewTree = new View();
            viewTree.setLocationRelativeTo(null);

            Controller controller = new Controller(viewTree);
            controller.runTree();


            /*
            BinaryTree smallTree = new BinaryTree();
            smallTree.insert("z");
            smallTree.insert("y");
            smallTree.insert("x");
            smallTree.insert("f");
            smallTree.insert("d");


            LinkedList list = smallTree.treeAsList();
            System.out.println(list);


            //smallTree.printTree();

            /*


*/
/*
            BinaryTree tree = new BinaryTree();
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
            tree.clearTree();
            tree.loadTree();
            tree.printTree();
            */



/*
            BinaryTree<String> smallTree = new BinaryTree<>();
            smallTree.insert("a");
            smallTree.insert("b");
            smallTree.insert("c");
            //smallTree.printTree();
tree.printTreeFromList(tree.treeAsList());

           System.out.println("search a:");
            tree.getNode("a");
            tree.remove(tree.getNode("a"));
*/

/*

            BinaryTreeNode testNode = new BinaryTreeNode("yyy");
            BinaryTreeNode nodeToRemove = tree.getNode(testNode.getData());

            tree.remove(nodeToRemove);


            System.out.println("after remove:");
            tree.printTree();

*/
        }



}
