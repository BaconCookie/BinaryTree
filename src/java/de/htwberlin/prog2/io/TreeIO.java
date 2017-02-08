package de.htwberlin.prog2.io;

import de.htwberlin.prog2.datamodel.BalancedTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laura on 01.01.17.
 * @author Laura Hartgers, HTW-Berlin Matrikelnummer 556238
 * @version 1.0
 */
public class TreeIO <T extends Comparable<T>>{


        /**
         * Method to save tree in a file
         * <p>
         * @param treeToSave tree which is being saved
         * @param outputPath path where the polynomial is saved
         * @throws IOException to a higher level because the Exception can't be handled here
         */
        public void save(BalancedTree<T> treeToSave, String outputPath) throws IOException {
            FileOutputStream fileOutput = new FileOutputStream(outputPath);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(treeToSave);
        }

        /**
         * Method to load a tree from a file
         * <p>
         * @param inputPath location where the file can be found
         * @return treeToLoad if successful
         * @throws IOException            to a higher level because the Exception can't be handled here
         * @throws ClassNotFoundException in case he read Value is not of type BalancedTree
         */
        public BalancedTree<T> load(String inputPath) throws IOException, ClassNotFoundException {
            FileInputStream fileInput = new FileInputStream(inputPath);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            BalancedTree<T> treeToLoad = null;
            Object readObject = objectInput.readObject();

            if (readObject instanceof BalancedTree) {
                treeToLoad = (BalancedTree<T>) readObject;
            } else {
                throw new ClassNotFoundException("The read Value is not of type BalancedTree.");
            }

            return treeToLoad;
        }



}
