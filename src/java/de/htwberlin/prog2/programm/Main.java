package de.htwberlin.prog2.programm;

import de.htwberlin.prog2.datamodel.BalancedTree;

/**
 * Created by laura on 14.01.17.
 */
public class Main {

        public static void main(String[] args) {
            BalancedTree<String> tree = new BalancedTree<>();
            for (int i = 1; i <= 7; i++)
                tree.insert(new String("i"));

            tree.PrintTree();
        }


}
