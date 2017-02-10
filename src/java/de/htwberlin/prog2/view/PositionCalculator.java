package de.htwberlin.prog2.view;

import de.htwberlin.prog2.model.BinaryTree;
import de.htwberlin.prog2.model.BinaryTreeNode;

import java.util.LinkedList;

/**
 * Created by laura on 10.02.17.
 */
public class PositionCalculator {

    private final int iconSize = 80;

    private LinkedList<PositionOfLine> positionOfLineList;
    private LinkedList<PositionOfNode> positionOfNodeList;

    public PositionCalculator() {

    }

    public void setPositions(BinaryTree binaryTree) {
        this.positionOfLineList.clear();
        this.positionOfNodeList.clear();

        LinkedList<BinaryTreeNode> list = binaryTree.treeAsList();

        int depthOfTree = binaryTree.getDepthOfTree();
        int maxWidth = (int) Math.pow(2, depthOfTree) * iconSize;
        int i = 0;

        while (!list.isEmpty()) {
            BinaryTreeNode node = list.poll();

            int blockSize = maxWidth / (int) Math.pow(2, depthOfTree);
            int blockStartY = (this.iconSize * 3 / 2) * depthOfTree;
            int blockStartX;

            if (node == binaryTree.getRoot()) {
                blockStartX = maxWidth / 2 - (iconSize / 2);
                this.positionOfNodeList.add(i, new PositionOfNode(blockStartX, blockStartY, this.iconSize));
                i++;
            } else {

                if (node == binaryTree.getParentNode(node).getLeft()) {
                    // location for Button
                    PositionOfNode positionOfParentNode = getSingleNodeFromList(binaryTree.getParentNode(node));
                    blockStartX = positionOfParentNode.getMiddleX() - (blockSize / 2) - (iconSize / 2);
                    this.positionOfNodeList.add(i, new PositionOfNode(blockStartX, blockStartY, this.iconSize));
                    i++;
                } else {
                    // location for Button
                    PositionOfNode positionOfParentNode = getSingleNodeFromList(binaryTree.getParentNode(node));
                    blockStartX = positionOfParentNode.getMiddleX() + (blockSize / 2) - (iconSize / 2);
                    this.positionOfNodeList.add(i, new PositionOfNode(blockStartX, blockStartY, this.iconSize));
                    i++;
                }

                if (binaryTree.getParentNode(node) != null) {
                    // locations for lines
                    PositionOfNode positionOfParentNode = getSingleNodeFromList(binaryTree.getParentNode(node));
                    PositionOfNode positionOfNode = getSingleNodeFromList(node);

                    int x1 = positionOfParentNode.getMiddleX();
                    int y1 = positionOfParentNode.getY2();
                    int x2 = positionOfNode.getMiddleX();
                    int y2 = positionOfNode.getY();

                    this.positionOfLineList.add(new PositionOfLine(x1, x2, y1, y2));
                }
            }
        }
    }

    public PositionOfNode getSingleNodeFromList(BinaryTreeNode node) {
        int indexNode = positionOfNodeList.indexOf(node);
        return positionOfNodeList.get(indexNode);
    }

    public LinkedList<PositionOfLine> getPositionOfLineList() {
        return positionOfLineList;
    }

    public LinkedList<PositionOfNode> getPositionOfNodeList() {
        return positionOfNodeList;
    }

}
