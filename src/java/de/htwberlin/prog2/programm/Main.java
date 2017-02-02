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
            tree.insert("iop");
            tree.insert("zzz");
            tree.insert("ab");
            tree.printTree();


         //   System.out.println("search a:");
         //   tree.searchNode("a");
            System.out.println("remove a & ab:");
          //  tree.remove(tree.searchNode("a"));

            BalancedTreeNode<String> a = new BalancedTreeNode<String>("uio");
            tree.remove(a);

            //tree.remove(tree.searchNode("uio"));
            tree.printTree();

        }



}
