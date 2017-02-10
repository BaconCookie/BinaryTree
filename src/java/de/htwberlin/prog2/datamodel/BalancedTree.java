package de.htwberlin.prog2.datamodel;

//needed for printTree method

import de.htwberlin.prog2.gui.DrawLines;
import de.htwberlin.prog2.gui.ViewPosition;
import de.htwberlin.prog2.io.TreeIO;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;


/**
 * Created by laura on 01.01.17.
 */
public class BalancedTree implements Serializable {

    private BalancedTreeNode root;
    private LinkedList<DrawLines> drawLines; // = new LinkedList();
    private int depthOfTree; //max level of tree
    private int size;
    private final int iconSize = 80;


    /**
     * Constructor of empty tree
     */
    public BalancedTree() {
        root = null;
    }


    /**
     * Method to determine depthTree of node in the tree
     *
     * @param node to measure depthTree of
     * @return int depthTree of node in tree
     */
    private int depthTree(BalancedTreeNode node) {
        if (node == null)
            return 0;
        return node.getDepth();
        // 1 + Math.max(depthTree(node.getLeft()), depthTree(node.getRight()));
    }

    /**
     * Method to insert a node
     *
     * @param data from node to insert
     * @return updated root of the tree
     */
    public BalancedTreeNode insert(String data) {
        root = insert(root, data);

        switch (balanceNumber(root)) {
            case 1:
                root = rotateLeft(root);
                break;
            case -1:
                root = rotateRight(root);
                break;
            default:
                break;
        }
        return root;
    }

    /**
     * Overloading insert() method to insert a new node in relation to an already existing node
     * Rebalances tree if necessary
     *
     * @param lastNode already existing node
     * @param data belonging to new node
     * @return inserted node which will be rebalanced
     */
    private BalancedTreeNode insert(BalancedTreeNode lastNode, String data) {
        int depthOfNode;
        if (lastNode == null)
            return new BalancedTreeNode(data);
        if (lastNode.getData().compareTo(data) > 0) {
            lastNode = new BalancedTreeNode(lastNode.getData(), insert(lastNode.getLeft(), data),
                    lastNode.getRight());
        } else {
            if (lastNode.getData().compareTo(data) < 0) {
                lastNode = new BalancedTreeNode(lastNode.getData(), lastNode.getLeft(), insert(lastNode.getRight(), data));
            }
        }
        // After insert the new balancedTreeNode, check and rebalance the current tree if necessary.
      //  reBalance(node);

        // Update depthOfTree if necessary.
       // depthOfNode = depthTree(node);
       // if (node.level  > depthOfTree){
         //   depthOfTree = node.level;
       // }
        return reBalance(lastNode);
    }

    /**
     * Method to rebalance the tree
     * If the tree is too deep on the right, it will rotate towards the left
     * If the tree is too deep on the left, it will rotate towards the right
     *
     * @param node root (whole tree) or other node (subtree) which will be rebalanced
     * @return (new) root node
     */
    private BalancedTreeNode reBalance(BalancedTreeNode node) {
        switch (balanceNumber(node)) {
            case 1:
                node = rotateLeft(node);
                break;
            case -1:
                node = rotateRight(node);
                break;
            default:
                return node;
        }
        return node;
    }

    private int balanceNumber(BalancedTreeNode node) {
        int L = depthTree(node.getLeft());
        int R = depthTree(node.getRight());
        if (L - R >= 2) {
            return -1;
        } else if (L - R <= -2) {
            return 1;
        }
        return 0;
    }

    /**
     * Method which balances the Tree in case of a heavy right side by rotating to the left
     *
     * @param node node to balance
     * @return rightChild which is the root of this (sub)Tree
     */
    private BalancedTreeNode rotateLeft(BalancedTreeNode node) {
        BalancedTreeNode startNode = node;
        BalancedTreeNode rightChild = startNode.getRight();
        BalancedTreeNode leftChild = startNode.getLeft();
        BalancedTreeNode leftOfRightChild = rightChild.getLeft();
        BalancedTreeNode rightOfRightChild = rightChild.getRight();
        startNode = new BalancedTreeNode(startNode.getData(), leftChild, leftOfRightChild);
        rightChild = new BalancedTreeNode(rightChild.getData(), startNode, rightOfRightChild);
        return rightChild;
    }

    /**
     * Method which balances the Tree in case of a heavy left side by rotating to the right
     *
     * @param node node to balance
     * @return leftChild which is the root of this (sub)Tree
     */
    private BalancedTreeNode rotateRight(BalancedTreeNode node) {
        BalancedTreeNode startNode = node;
        BalancedTreeNode leftChild = startNode.getLeft();
        BalancedTreeNode rightChild = startNode.getRight();
        BalancedTreeNode leftOfLeftChild = leftChild.getLeft();
        BalancedTreeNode rightOfLeftChild = leftChild.getRight();
        startNode = new BalancedTreeNode(startNode.getData(), rightOfLeftChild, rightChild);
        leftChild = new BalancedTreeNode(leftChild.getData(), leftOfLeftChild, startNode);
        return leftChild;
    }

    //TODO this doesn't seem to help unfortunately
    private void rotateTowardsLighterSide(BalancedTreeNode nodeToRemove) {
        int depthLeft = root.getLeft().getDepth();
        int depthRight = root.getRight().getDepth();
        //BalancedTreeNode<T> leftTree = root.getLeft();
        //BalancedTreeNode<T> rightTree = root.getRight();
        if ((depthLeft > depthRight) && (nodeToRemove.getData().compareTo(root.getData()) < 0)) {
            rotateRight(root);
        } else if ((depthLeft < depthRight) && (nodeToRemove.getData().compareTo(root.getData()) > 0)) {
            rotateLeft(root);
        }
    }

    /**
     * Method to search a node, to see if it is in the Tree
     *
     * @param data belonging to node to search
     * @return boolean
     * true    if node is in the tree
     * false   if node is not found
     */
    public boolean search(String data) {
        BalancedTreeNode searchNode = root;
        while (searchNode != null) {
            if (searchNode.getData().compareTo(data) == 0) {
                return true;
            } else if (searchNode.getData().compareTo(data) > 0)
                searchNode = searchNode.getLeft();
            else
                searchNode = searchNode.getRight();
        }
        return false;
    }

    /**
     * Method to search a node by index
     *
     * @param id id of node that is being sought
     * @return node with corresponding id
     */
    public BalancedTreeNode searchNodeById(int id) {

        LinkedList<BalancedTreeNode> list = treeAsList();
        boolean nodeIsNotFound = true;
        BalancedTreeNode searchNode = list.poll();
        while (nodeIsNotFound) {
            if (searchNode.getId() == id) {
                nodeIsNotFound = false;
            } else {
                searchNode = list.poll();
            }
        }
        return searchNode;
    }


    /**
     * Method to search a node
     *
     * @param data contained by node (string)
     * @return node that is being searched or null if node is not found
     */
    public BalancedTreeNode getNode(String data) {
        BalancedTreeNode searchNode = root;
        while (searchNode != null) {
            if (searchNode.getData().compareTo(data) == 0)
                return searchNode;
            else if (searchNode.getData().compareTo(data) > 0)
                searchNode = searchNode.getLeft();
            else
                searchNode = searchNode.getRight();
        }
        //if node is not found return null
        return null;
    }


    /**
     * Method to remove a node from the tree
     *
     * @param nodeToRemove node which will be removed by this method
     */
    public BalancedTreeNode remove(BalancedTreeNode nodeToRemove) {
        try {
            //in order to rebalance properly later:
            //TODO why does this not seem to work????--------------------------------------------------------
            rotateTowardsLighterSide(nodeToRemove);
            //in case nodeToRemove == root
            if (nodeToRemove.getData().compareTo(root.getData()) == 0) {
                removeNodeWhichIsCurrentRoot();
            } else if (hasNoChild(nodeToRemove)) {
                // find parentNode of nodeToRemove and set link to nodeToRemove to null
                removeNodeWhichIsLeaf(nodeToRemove);
            } else if (hasOneChild(nodeToRemove)) {
                removeNodeWithOneChild(nodeToRemove);
            } else {
                removeNodeFromTheMiddle(nodeToRemove);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in removeNode method");
        }
        reBalance(root); //TODO why does this not seem to work????--------------------------------------------------------
        return root;
    }

    private void removeNodeWithOneChild(BalancedTreeNode nodeToRemove) {
        BalancedTreeNode parentNode = findParentNode(nodeToRemove);
        BalancedTreeNode childNode = findOnlyChild(nodeToRemove);
        if ((parentNode.getLeft() != null) && (parentNode.getLeft().getData().compareTo(nodeToRemove.getData()) == 0)) {
            parentNode.setLeft(childNode);
        } else {
            parentNode.setRight(childNode);
        }
    }

    private void removeNodeWhichIsLeaf(BalancedTreeNode nodeToRemove) {
        BalancedTreeNode parentNode = findParentNode(nodeToRemove);
        //if nodeToRemove is/was the left child
        if ((parentNode.getLeft() != null) && (parentNode.getLeft().getData().compareTo(nodeToRemove.getData()) == 0)) {
            parentNode.setLeft(null);
        } else {
            parentNode.setRight(null);
        }
    }

    private void removeNodeWhichIsCurrentRoot() {
        if (root.getLeft() != null) {
            root = root.getLeft();
        } else if (root.getRight() != null) {
            root = root.getRight();
        } else {
            root = null;
        }
    }

    private boolean hasOneChild(BalancedTreeNode nodeToRemove) {
        return ((nodeToRemove.getLeft() == null | nodeToRemove.getRight() == null) &&
                (nodeToRemove.getLeft() != null | nodeToRemove.getRight() != null));
    }

    private BalancedTreeNode findOnlyChild(BalancedTreeNode nodeToRemove) {
        BalancedTreeNode childNode = new BalancedTreeNode();

        if (nodeToRemove.getLeft() != null) {
            childNode = nodeToRemove.getLeft();
        }
        if (nodeToRemove.getRight() != null) {
            childNode = nodeToRemove.getRight();
        }
        return childNode;
    }

    private boolean hasNoChild(BalancedTreeNode nodeToRemove) {
        return nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null;
    }

    public boolean getIfNodeIsLeaf(BalancedTreeNode nodeToCheck) {
        return hasNoChild(nodeToCheck);
    }

    /**
     * Method to get the parent node of a node
     *
     * @param childNode node to get the parent from
     * @return parentNode
     */
    private BalancedTreeNode findParentNode(BalancedTreeNode childNode) {
        MoveDirection whereDidTheChildGo;

        //childNode != root, double checking for security
        if (childNode.getData().compareTo(root.getData()) == 0) {
            throw new IllegalArgumentException("If childNode == root, then it has no parent!");
        } else {

            BalancedTreeNode parentNode = root;
            BalancedTreeNode searchChildNode = root;

            //now the child needs to go one level ahead of the parent
            if (searchChildNode.getData().compareTo(childNode.getData()) > 0) { //
                searchChildNode = searchChildNode.getLeft();
                whereDidTheChildGo = MoveDirection.LEFT;
            } else {
                searchChildNode = searchChildNode.getRight();
                whereDidTheChildGo = MoveDirection.RIGHT;
            }

            //then the they both go ahead, parent follows child and stays one step behind
            while (searchChildNode.getData().compareTo(childNode.getData()) != 0) {
                //if child is found, return parent node
                if (searchChildNode.getData().compareTo(childNode.getData()) == 0) {
                    return parentNode;
                } else if (searchChildNode.getData().compareTo(childNode.getData()) > 0) { // if getNode is < child(our goal here), go left
                    parentNode = followChild(parentNode, whereDidTheChildGo);
                    searchChildNode = searchChildNode.getLeft();
                    whereDidTheChildGo = MoveDirection.LEFT;
                } else {
                    parentNode = followChild(parentNode, whereDidTheChildGo);
                    searchChildNode = searchChildNode.getRight();
                    whereDidTheChildGo = MoveDirection.RIGHT;
                }
            }
            return parentNode;
        }
    }

    public BalancedTreeNode getParentNode(BalancedTreeNode childNode) {
        return findParentNode(childNode);
    }

    private BalancedTreeNode followChild(BalancedTreeNode parentNode, MoveDirection dirChild) {
        BalancedTreeNode updatedParentNode = new BalancedTreeNode();
        if (dirChild == MoveDirection.LEFT) {
            updatedParentNode = parentNode.getLeft();
        } else if (dirChild == MoveDirection.RIGHT) {
            updatedParentNode = parentNode.getRight();
        } else {
            System.out.println("Error in followChild");
        }
        return updatedParentNode;
    }

    /**
     * Method to remove a node that is neither the root nor a leaf
     *
     * @param nodeToRemove node that will be removed
     */
    private void removeNodeFromTheMiddle(BalancedTreeNode nodeToRemove) {
        BalancedTreeNode replacementNode = findNodeToReplaceRemoved(nodeToRemove);

        //remove old link to node which is going to replace the nodeToRemove
        remove(replacementNode);

        //set data to be the same as the replacement node:
        //the links of the nodeToRemove and its parent can stay the same this way
        nodeToRemove.setData(replacementNode.getData());
    }


    /**
     * Method to find a suitable node to replace one in the middle of the tree
     *
     * @param nodeToRemove this node must have at least one child
     * @return a suitable node for replacement
     */
    private BalancedTreeNode findNodeToReplaceRemoved(BalancedTreeNode nodeToRemove) {
        BalancedTreeNode nodeToReplaceWith;

        //if there is a node (or more) on the left, get the one with the highest value
        if (nodeToRemove.getLeft() != null) {
            nodeToReplaceWith = nodeToRemove.getLeft();
            nodeToReplaceWith = getNode(Maximum(nodeToReplaceWith));

        } else { //get the node with the lowest value on the right
            nodeToReplaceWith = nodeToRemove.getRight();
            nodeToReplaceWith = getNode(Minimum(nodeToReplaceWith));
        }
        return nodeToReplaceWith;
    }

    public BalancedTreeNode getNodeToReplaceRemoved(BalancedTreeNode nodeToRemove) {
        return findNodeToReplaceRemoved(nodeToRemove);
    }

    /**
     * Method to find the biggest value in the (sub)tree
     *
     * @return Data from node with the biggest value
     */
    private String Maximum(BalancedTreeNode subTreeNode) {
        if (subTreeNode == null)
            return null;
        while (subTreeNode.getRight() != null)
            subTreeNode = subTreeNode.getRight();
        return subTreeNode.getData();
    }

    /**
     * Method to find the smallest value in the (sub)tree
     *
     * @return Data from node with the smallest value
     */
    private String Minimum(BalancedTreeNode subTreeNode) {
        if (subTreeNode == null)
            return null;
        while (subTreeNode.getLeft() != null) {
            subTreeNode = subTreeNode.getLeft();
        }
        return subTreeNode.getData();
    }

    /**
     * Method which seems to clear the tree by pointing the root to null
     * and thus making all elements of the tree unreachable
     */
    public BalancedTree clear() {
        this.root = null;
        this.drawLines = new LinkedList<>();
        this.size = 0;
        //this.depthOfTree = 0;
        return new BalancedTree();
    }

    public BalancedTreeNode getRoot() {
        return this.root;
    }


    public String toString() {
        return root.toString();
    }

    /**
     * Method to simply print the tree on the console
     */
    public void printTree() {
        root.level = 0;
        LinkedList<BalancedTreeNode> list = new LinkedList<>();

        list.add(root);
        while (!list.isEmpty()) {
            BalancedTreeNode balancedTreeNode = list.poll(); //poll: Retrieves and removes the head (first element) of this list.
            System.out.println(balancedTreeNode);
            int level = balancedTreeNode.level;
            BalancedTreeNode left = balancedTreeNode.getLeft();
            BalancedTreeNode right = balancedTreeNode.getRight();
            if (left != null) {
                left.level = level + 1;
                list.add(left);
            }
            if (right != null) {
                right.level = level + 1;
                list.add(right);
            }
        }
    }

    /**
     * Method to put tree in a LinkedList of <BalancedTreeNode>
     * Acts like breadth first search
     *
     * @return list of nodes
     */
    public LinkedList<BalancedTreeNode> treeAsList() {
        root.level = 0;
        LinkedList<BalancedTreeNode> list = new LinkedList<>(); //
        LinkedList<BalancedTreeNode> listWithAllElements = new LinkedList<>(); //
        list.add(root);
        listWithAllElements.add(root);
        while (!list.isEmpty()) {
            BalancedTreeNode balancedTreeNode = list.poll(); //poll: Retrieves and removes the head (first element) of this list.
            int level = balancedTreeNode.level;
            BalancedTreeNode left = balancedTreeNode.getLeft();
            BalancedTreeNode right = balancedTreeNode.getRight();
            if (left != null) {
                left.level = + 1;
                list.add(left);
                listWithAllElements.add(left);
            }
            if (right != null) {
                right.level = level + 1;
                list.add(right);
                listWithAllElements.add(right);
            }
        }
        return listWithAllElements;
    }

    /**
     * Method to print the tree from a list of nodes
     *
     * @param list in which tree was being saved
     */
    public void printTreeFromList(LinkedList<BalancedTreeNode> list) {
        while (!list.isEmpty()) {
            BalancedTreeNode balancedTreeNode = list.poll(); //poll: Retrieves and removes the head (first element) of this list.
            System.out.println(balancedTreeNode);
            int level = balancedTreeNode.level;
            BalancedTreeNode left = balancedTreeNode.getLeft();
            BalancedTreeNode right = balancedTreeNode.getRight();
            if (left != null) {
                left.level = level + 1;
            }
            if (right != null) {
                right.level = level + 1;
            }
        }
    }


    public int getDepthOfTree(){
        LinkedList<BalancedTreeNode> list = treeAsList();
        BalancedTreeNode nodeWithMaxIndex = list.getLast();
        int depthOfTree = nodeWithMaxIndex.getDepth();
        return depthOfTree;
    }

    public ViewPosition getPositionOfNodeByIndex(int id) {
        BalancedTreeNode node = searchNodeById(id);
        return node.viewPosition;
    }


    public LinkedList<DrawLines> getDrawLines() {
        return drawLines;
    }

    public int getSize() {
        return size;
    }


    public int getWidth() {
        return (int) Math.pow(2, depthOfTree) * iconSize;
    }

    public int getHeight() {
        return depthOfTree * iconSize * 2;
    }

    /**
     * Method which saves tree in a file
     * The file is being placed in the folder of this program.
     */
    public void saveTree() {
        try {
            TreeIO treeIO = new TreeIO();
            String filePath = "./BalancedTree.json";
            treeIO.save(this, filePath);
        } catch (IOException e) {
            System.out.println("Error while saving.");
        }
    }

    /**
     * Method which loads previously saved tree from a file (./saved.tree).
     * The file is being loaded from the folder of this program.
     *
     * @return Loaded tree
     * @throws RuntimeException in case of a caught Exception
     */
    public BalancedTree loadTree() {
        try {
            TreeIO treeIO = new TreeIO();

            String inputPath = "./BalancedTree.json";
            BalancedTree loadedTree = treeIO.load(inputPath);
            this.root = loadedTree.getRoot();
            return loadedTree;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading.");
            throw new RuntimeException(e);
        }
    }


}
