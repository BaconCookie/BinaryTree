package de.htwberlin.prog2.programm;

import de.htwberlin.prog2.controller.Controller;
import de.htwberlin.prog2.view.View;

/**
 * Created by laura on 14.01.17.
 */
public class Main {

        public static void main(String[] args) {

            View viewTree = new View();
            viewTree.setLocationRelativeTo(null);

            Controller controller = new Controller(viewTree);
            controller.runTree();

        }
}
