package de.htwberlin.prog2.datamodel;

//needed for printTree method

import java.util.LinkedList;


/**
 * Created by laura on 01.01.17.
 */
public class BalancedTree<T extends Comparable<T>> {

    BalancedTreeNode<T> root;

    /**
     * Constructor of empty tree
     */
    public BalancedTree() {
        root = null;
    }

    /**
     * Method to find the biggest value in the tree
     *
     * @return Data from node with the biggest value
     */
    public T Maximum() {
        BalancedTreeNode<T> local = root;
        if (local == null)
            return null;
        while (local.getRight() != null)
            local = local.getRight();
        return local.getData();
    }

    /**
     * Method to find the smallest value in the tree
     *
     * @return Data from node with the smallest value
     */
    public T Minimum() {
        BalancedTreeNode<T> local = root;
        if (local == null)
            return null;
        while (local.getLeft() != null) {
            local = local.getLeft();
        }
        return local.getData();
    }

    /**
     * Method to determine depth of node in the tree
     *
     * @param node to measure depth of
     * @return int depth of node in tree
     */
    private int depth(BalancedTreeNode<T> node) {
        if (node == null)
            return 0;
        return node.getDepth();
        // 1 + Math.max(depth(node.getLeft()), depth(node.getRight()));
    }

    /**
     * Method to insert a node
     *
     * @param data from node to insert
     * @return updated root of the tree
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
     * Rebalances tree if necessary
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

    /**
     * Method to rebalance the tree
     * If the tree is to deep on the right, it will rotate towards the left
     * If the tree is to deep on the left, it will rotate towards the right
     *
     * @param node
     * @return
     */
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
        int L = depth(node.getLeft());
        int R = depth(node.getRight());
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


    /**
     * Method to remove a node from the tree
     *
     * @param nodeToRemove node which will be removed by this method
     */
    public void remove(BalancedTreeNode<T> nodeToRemove) {
        try {
            //in case nodeToRemove == root
            if (nodeToRemove.getData().compareTo(root.getData()) == 0) {
                if (root.getLeft() != null) {
                    root = root.getLeft();
                } else if (root.getRight() != null) {
                    root = root.getRight();
                } else {
                    root = null;
                }
            }
            // find parentNode of nodeToRemove and set link to nodeToRemove to null
            else if (hasNoChild(nodeToRemove)) {
                BalancedTreeNode<T> parentNode = getParentNode(nodeToRemove);
                //if nodeToRemove is/was the left child
                if ((parentNode.getLeft() != null) && (parentNode.getLeft().getData().compareTo(nodeToRemove.getData()) > 0)) {
                    parentNode.setLeft(null);
                } else {
                    parentNode.setRight(null);
                }
            } else {
                removeNodeFromTheMiddle(nodeToRemove);
            }
            reBalance(root);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in removeNode method");
        }
    }

    private boolean hasNoChild(BalancedTreeNode<T> nodeToRemove) {
        return nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null;
    }

    /**
     * Method to get the parent node of a node
     *
     * @param childNode node to get the parent from
     * @return parentNode
     */
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

    /**
     * Method to remove a node that is neither the root nor a leaf
     *
     * @param nodeToRemove node that will be removed
     */
    private void removeNodeFromTheMiddle(BalancedTreeNode<T> nodeToRemove) {
        BalancedTreeNode<T> replacementNode = getNodeToReplaceRemoved(nodeToRemove);

        updateParentLink(nodeToRemove, replacementNode);
        updateReplacementNodeToChildren(nodeToRemove, replacementNode);
        updateParentOfReplacementNode(replacementNode);
    }

    /**
     * Method used in removeNodeFromTheMiddle to update the link from the parent node from the removed node to the replacement node
     *
     * @param nodeToRemove    node that will be removed
     * @param replacementNode node that replaces the removed node
     */
    private void updateParentLink(BalancedTreeNode<T> nodeToRemove, BalancedTreeNode<T> replacementNode) {
        BalancedTreeNode<T> parentNode = getParentNode(nodeToRemove);
        //if nodeToRemove is/was the left child
        if ((parentNode.getLeft() != null) && (parentNode.getLeft().getData().compareTo(nodeToRemove.getData()) > 0)) {
            parentNode.setLeft(replacementNode);
        } else { //if nodeToRemove is/was the right child
            parentNode.setRight(replacementNode);
        }
    }

    /**
     * Method used in removeNodeFromTheMiddle to update the link(s) from the replacementNode to its new child(ren)
     *
     * @param nodeToRemove    node that will be removed
     * @param replacementNode node that replaces the removed node
     */
    private void updateReplacementNodeToChildren(BalancedTreeNode<T> nodeToRemove, BalancedTreeNode<T> replacementNode) {
        BalancedTreeNode<T> leftChildOfNodeToRemove = nodeToRemove.getLeft();
        BalancedTreeNode<T> rightChildOfNodeToRemove = nodeToRemove.getRight();

        if (leftChildOfNodeToRemove.getData().compareTo(replacementNode.getData()) != 0) {
            replacementNode.setLeft(leftChildOfNodeToRemove);
        }
        if (rightChildOfNodeToRemove.getData().compareTo(replacementNode.getData()) != 0) {
            replacementNode.setRight(rightChildOfNodeToRemove);
        }
    }

    /**
     * Method used in removeNodeFromTheMiddle to update the link from the parent of the replacementNode to its now gone child
     *
     * @param replacementNode node that replaces the removed node, therefore the old link from it's parent needs to be set to null
     */
    private void updateParentOfReplacementNode(BalancedTreeNode<T> replacementNode) {
        BalancedTreeNode<T> parentOfReplacementNode = getParentNode(replacementNode);
        //update link of the parent from the replacement node to null, since it was the highest or lowest value
        //if replacementNode is/was the left child
        if ((parentOfReplacementNode.getLeft() != null) && (parentOfReplacementNode.getLeft().getData().compareTo(replacementNode.getData()) > 0)) {
            parentOfReplacementNode.setLeft(null);
        } else { //if replacementNode is/was the right child
            parentOfReplacementNode.setRight(null);
        }
    }

    /**
     * Method to find a suitable node to replace one in the middle of the tree
     *
     * @param nodeToRemove this node must have at least one child
     * @return a suitable node for replacement
     */
    private BalancedTreeNode<T> getNodeToReplaceRemoved(BalancedTreeNode<T> nodeToRemove) {
        BalancedTreeNode<T> nodeToReplaceWith;

        //if there is a node (or more) on the left, get the one with the highest value
        if (nodeToRemove.getLeft() != null) {
            nodeToReplaceWith = nodeToRemove.getLeft();
            while (nodeToReplaceWith != null) {
                if (nodeToReplaceWith.getRight() != null) {
                    nodeToReplaceWith = nodeToReplaceWith.getRight();
                } else if (nodeToReplaceWith.getLeft() != null) {
                    nodeToReplaceWith = nodeToReplaceWith.getLeft();
                }
            }
        } else { //get the node with the lowest value on the right (there must be at least one child)
            nodeToReplaceWith = nodeToRemove.getRight();
            while (nodeToReplaceWith != null) {
                if (nodeToReplaceWith.getLeft() != null) {
                    nodeToReplaceWith = nodeToReplaceWith.getLeft();
                } else if (nodeToReplaceWith.getRight() != null) {
                    nodeToReplaceWith = nodeToReplaceWith.getRight();
                }
            }
        }
        return nodeToReplaceWith;
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