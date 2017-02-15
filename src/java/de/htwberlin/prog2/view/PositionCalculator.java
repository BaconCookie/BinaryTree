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
        positionOfLineList = new LinkedList<>();
        positionOfNodeList = new LinkedList<>();
    }

    public void setPositions(BinaryTree binaryTree) {
        positionOfLineList.clear();

        LinkedList<BinaryTreeNode> listOfNodes = binaryTree.treeAsList();
        int depthOfTree = binaryTree.getDepthOfTree(listOfNodes);
        int maxWidth = (int) Math.pow(2, depthOfTree) * iconSize;

        while (!listOfNodes.isEmpty()) {
            BinaryTreeNode node = listOfNodes.poll();

            int blockSize = maxWidth / (int) Math.pow(2, binaryTree.getDepthOfSubTree(node));
            int blockStartY = (this.iconSize * 3 / 2) * binaryTree.getDepthOfSubTree(node);
            int blockStartX;

            if (node == binaryTree.getRoot()) {
                blockStartX = maxWidth / 2 - (iconSize / 2);
                this.positionOfNodeList.add(new PositionOfNode(node, blockStartX, blockStartY, this.iconSize));
            } else {

                if (node == binaryTree.getParentNode(node).getLeft()) {
                    // location for Button
                    PositionOfNode positionOfParentNode = getSingleNodeFromList(binaryTree.getParentNode(node));
                    blockStartX = positionOfParentNode.getMiddleX() - (blockSize / 2) - (iconSize / 2);
                    this.positionOfNodeList.add(new PositionOfNode(node, blockStartX, blockStartY, this.iconSize));
                } else {
                    // location for Button
                    PositionOfNode positionOfParentNode = getSingleNodeFromList(binaryTree.getParentNode(node));
                    blockStartX = positionOfParentNode.getMiddleX() + (blockSize / 2) - (iconSize / 2);
                    this.positionOfNodeList.add(new PositionOfNode(node, blockStartX, blockStartY, this.iconSize));
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
        PositionOfNode foundPositionOfNode = null;
        for (PositionOfNode positionOfNode : positionOfNodeList){
            if (positionOfNode.getNodeFromList().equals(node))
                 foundPositionOfNode = positionOfNode;
        }return foundPositionOfNode;
    }

    public LinkedList<PositionOfLine> getPositionOfLineList() {
        return positionOfLineList;
    }

}
