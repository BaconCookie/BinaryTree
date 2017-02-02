package de.htwberlin.prog2.datamodel;

//needed for printTree & toString method
import java.util.LinkedList;


/**
 * Created by laura on 01.01.17.
 */
public class BalancedTree<T extends Comparable<T>> {

    BalancedTreeNode<T> root;

    public BalancedTree() {
        root = null;
    }

    public T Maximum() {
        BalancedTreeNode<T> local = root;
        if (local == null)
            return null;
        while (local.getRight() != null)
            local = local.getRight();
        return local.getData();
    }

    public T Minimum() {
        BalancedTreeNode<T> local = root;
        if (local == null)
            return null;
        while (local.getLeft() != null) {
            local = local.getLeft();
        }
        return local.getData();
    }

    private int height(BalancedTreeNode<T> node) {
        if (node == null)
            return 0;
        return node.getHeight();
        // 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    /**
     * Method to insert a node
     * @param data
     * @return
     */
    public BalancedTreeNode<T> insert(T data) {
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
     *
     * @param node already existing node
     * @param data belonging to new node
     * @return
     */
    public BalancedTreeNode<T> insert(BalancedTreeNode<T> node, T data) {
        if (node == null)
            return new BalancedTreeNode<T>(data);
        if (node.getData().compareTo(data) > 0) {
            node = new BalancedTreeNode<T>(node.getData(), insert(node.getLeft(), data),
                    node.getRight());
            // balancedTreeNode.setLeft(insert(balancedTreeNode.getLeft(), data));
        } else if (node.getData().compareTo(data) < 0) {
            // balancedTreeNode.setRight(insert(balancedTreeNode.getRight(), data));
            node = new BalancedTreeNode<T>(node.getData(), node.getLeft(), insert(
                    node.getRight(), data));
        }
        // After insert the new balancedTreeNode, check and rebalance the current tree if necessary.
        return reBalance(node);
    }

    private BalancedTreeNode<T> reBalance(BalancedTreeNode<T> node) {
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

    private int balanceNumber(BalancedTreeNode<T> node) {
        int L = height(node.getLeft());
        int R = height(node.getRight());
        if (L - R >= 2) {
            return -1;
        }
        else if (L - R <= -2) {
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
    private BalancedTreeNode<T> rotateLeft(BalancedTreeNode<T> node) {
        BalancedTreeNode<T> startNode = node;
        BalancedTreeNode<T> rightChild = startNode.getRight();
        BalancedTreeNode<T> leftChild = startNode.getLeft();
        BalancedTreeNode<T> leftOfRightChild = rightChild.getLeft();
        BalancedTreeNode<T> rightOfRightChild = rightChild.getRight();
        startNode = new BalancedTreeNode<T>(startNode.getData(), leftChild, leftOfRightChild);
        rightChild = new BalancedTreeNode<T>(rightChild.getData(), startNode, rightOfRightChild);
        return rightChild;
    }

    /**
     * Method which balances the Tree in case of a heavy left side by rotating to the right
     *
     * @param node node to balance
     * @return leftChild which is the root of this (sub)Tree
     */
    private BalancedTreeNode<T> rotateRight(BalancedTreeNode<T> node) {
        BalancedTreeNode<T> startNode = node;
        BalancedTreeNode<T> leftChild = startNode.getLeft();
        BalancedTreeNode<T> rightChild = startNode.getRight();
        BalancedTreeNode<T> leftOfLeftChild = leftChild.getLeft();
        BalancedTreeNode<T> rightOfLeftChild = leftChild.getRight();
        startNode = new BalancedTreeNode<T>(startNode.getData(), rightOfLeftChild, rightChild);
        leftChild = new BalancedTreeNode<T>(leftChild.getData(), leftOfLeftChild, startNode);
        return leftChild;
    }

    /**
     * Method to search a node
     *
     * @param data belonging to node to search
     * @return  true    if node is in the tree
     *          false   if node is not found
     */
    public boolean search(T data) {
        BalancedTreeNode<T> searchNode = root;
        while (searchNode != null) {
            if (searchNode.getData().compareTo(data) == 0)
                return true;
            else if (searchNode.getData().compareTo(data) > 0)
                searchNode = searchNode.getLeft();
            else
                searchNode = searchNode.getRight();
        }
        return false;
    }

    /**
     * Method to search a node
     * @param data contained by node
     * @return node that is being searched or null if node is not found
     */
    public BalancedTreeNode<T> searchNode(T data) {
        BalancedTreeNode<T> searchNode = root;
        while (searchNode != null) {
            if (searchNode.getData().compareTo(data) == 0)
                return searchNode;
            else if (searchNode.getData().compareTo(data) > 0)
                searchNode = searchNode.getLeft();
            else
                searchNode = searchNode.getRight();
        }
        return null;
    }

    public void remove(BalancedTreeNode<T> nodeToRemove){
        BalancedTreeNode<T> searchNode = root;
        if (search(nodeToRemove.getData()))
        try {
            while (searchNode != null) {
                if (searchNode.compareTo(nodeToRemove) == 0){
                    removeNode(nodeToRemove);
                }
                else if (searchNode.compareTo(nodeToRemove) > 0) {
                    searchNode = searchNode.getLeft();
                } else {
                    searchNode = searchNode.getRight();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println();
            System.out.println("An error occurred!");
        }
    }

    private void removeNode(BalancedTreeNode<T> nodeToRemove) {
        //REMOVE NODE __________________________UNCHECKED CODE___________________________
        // by default move leftChild of node one level up and then rebalance
        BalancedTreeNode leftChild = nodeToRemove.getLeft(); //to update link later
        BalancedTreeNode nodeToReplace = nodeToRemove.getRight(); //to find the node to replace nodeToRemove
        BalancedTreeNode newRightChild = nodeToRemove.getRight().getLeft();                             //NULLPOINTER EXC!
        BalancedTreeNode nodeToUpdateLink = nodeToRemove.getRight();
        while (nodeToReplace != null){
            nodeToReplace.getLeft();
            nodeToUpdateLink.getLeft();
        }
        nodeToReplace.setLeft(leftChild);
        nodeToReplace.setRight(newRightChild);
        nodeToUpdateLink.setLeft(null);
        reBalance(root);
    }


    public String toString() {
        return root.toString();
    }

    public void printTree() {
        root.level = 0;
        LinkedList<BalancedTreeNode<T>> list = new LinkedList<BalancedTreeNode<T>>();

        list.add(root);
        while (!list.isEmpty()) {
            BalancedTreeNode<T> balancedTreeNode = list.poll();
            System.out.println(balancedTreeNode);
            int level = balancedTreeNode.level;
            BalancedTreeNode<T> left = balancedTreeNode.getLeft();
            BalancedTreeNode<T> right = balancedTreeNode.getRight();
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
}