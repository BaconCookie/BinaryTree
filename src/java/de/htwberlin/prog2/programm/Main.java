package de.htwberlin.prog2.programm;

import de.htwberlin.prog2.datamodel.BalancedTree;
import de.htwberlin.prog2.datamodel.BalancedTreeNode;

/**
 * Created by laura on 14.01.17.
 */
public class Main {

        public static void main(String[] args) {
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

            BalancedTree<String> smallTree = new BalancedTree<>();
            smallTree.insert("a");
            smallTree.insert("b");
            smallTree.insert("c");
           // smallTree.printTree();

         //   System.out.println("search a:");
         //   tree.searchNode("a");
            System.out.println("remove:");
          //  tree.remove(tree.searchNode("a"));

            BalancedTreeNode<String> nodeToRemove = new BalancedTreeNode<String>("uuu");
            tree.remove(nodeToRemove);

            //tree.remove(tree.searchNode("uio"));
            tree.printTree();

        }



}
