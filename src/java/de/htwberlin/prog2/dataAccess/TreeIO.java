package de.htwberlin.prog2.dataAccess;

import de.htwberlin.prog2.model.BinaryTree;

import java.io.*;

/**
 * Created by laura on 01.01.17.
 * @author Laura Hartgers, HTW-Berlin Matrikelnummer 556238
 * @version 1.0
 */
public class TreeIO {


        /**
         * Method to save tree in a file
         * <p>
         * @param treeToSave tree which is being saved
         * @param outputPath path where the polynomial is saved
         * @throws IOException to a higher level because the Exception can't be handled here
         */
        public void save(BinaryTree treeToSave, String outputPath) throws IOException {
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
         * @throws ClassNotFoundException in case he read Value is not of type BinaryTree
         */
        public BinaryTree load(String inputPath) throws IOException, ClassNotFoundException {
            FileInputStream fileInput = new FileInputStream(inputPath);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            BinaryTree treeToLoad = null;
            Object readObject = objectInput.readObject();

            if (readObject instanceof BinaryTree) {
                treeToLoad = (BinaryTree) readObject;
            } else {
                throw new ClassNotFoundException("The read Value is not of type BinaryTree.");
            }

            return treeToLoad;
        }
}
