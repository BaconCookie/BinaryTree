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
     *
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
     * Method to search a node, to see if it is in the Tree
     *
     * @param data belonging to node to search
     * @return boolean
     * true    if node is in the tree
     * false   if node is not found
     */
    public boolean search(T data) {
        BalancedTreeNode<T> searchNode = root;
        while (searchNode != null) {
            if (searchNode.getData().compareTo(data) == 0) {
                System.out.println("search = true"); //<------------------------remove when project finish
                return true;
            } else if (searchNode.getData().compareTo(data) > 0)
                searchNode = searchNode.getLeft();
            else
                searchNode = searchNode.getRight();
        }
        System.out.println("search = false"); //<-------------------------------remove when project finish
        return false;
    }

    /**
     * Method to search a node
     *
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


    public void removeNode(BalancedTreeNode<T> nodeToRemove) {
        try {
            //in case nodeToRemove == root
            if (nodeToRemove.getData().compareTo(root.getData()) == 0) {
                root = null;
            }
            // find parentNode of nodeToRemove and set link to nodeToRemove to null
            else if (hasNoChild(nodeToRemove)) {
                BalancedTreeNode<T> parentNode = getParentNode(nodeToRemove);
                if (parentNode.getLeft() != null) {
                    if (parentNode.getLeft().getData().compareTo(nodeToRemove.getData()) > 0) {
                        parentNode.setLeft(null);
                    }
                } else {
                    parentNode.setRight(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in removeNode method");

            //reBalance(root);
        }
    }


    private boolean hasNoChild(BalancedTreeNode<T> nodeToRemove) {
        return nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null;
    }

    private MoveDirection whereDidTheChildGo;

    private BalancedTreeNode<T> followChild(BalancedTreeNode<T> parentNode, MoveDirection dirChild) {
        BalancedTreeNode<T> updatedParentNode = new BalancedTreeNode<T>();
        if (dirChild == MoveDirection.LEFT) {
            updatedParentNode = parentNode.getLeft();
        } else if (dirChild == MoveDirection.RIGHT) {
            updatedParentNode = parentNode.getRight();
        } else {
            System.out.println("Error in followChild");
        }
        return updatedParentNode;
    }

    private BalancedTreeNode<T> getParentNode(BalancedTreeNode<T> childNode) {

        //childNode != root, double checking for security
        if (childNode.getData().compareTo(root.getData()) == 0) {
            throw new IllegalArgumentException("If childNode == root, then it has no parent!");
        } else {

            BalancedTreeNode<T> parentNode = root;
            BalancedTreeNode<T> searchChildNode = root;


            //now the child needs to go one level ahead of the parent
            if (searchChildNode.getData().compareTo(childNode.getData()) > 0) { //
                searchChildNode = searchChildNode.getLeft();
                whereDidTheChildGo = MoveDirection.LEFT;
            } else {
                searchChildNode = searchChildNode.getRight();
                whereDidTheChildGo = MoveDirection.RIGHT;
            }

            //then the they both go ahead, parent follows child and stays one step behind
            while (searchChildNode != null) {
                //if child is found, return parent node
                if (searchChildNode.getData().compareTo(childNode.getData()) == 0) {
                    return parentNode;
                } else if (searchChildNode.getData().compareTo(childNode.getData()) > 0) { // if searchNode is < child(our goal here), go left
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