package de.htwberlin.prog2.datamodel;

//needed for printTree method

import java.util.LinkedList;


/**
 * Created by laura on 01.01.17.
 */
public class BalancedTree<T extends Comparable<T>> {

    private BalancedTreeNode<T> root;

    /**
     * Constructor of empty tree
     */
    public BalancedTree() {
        root = null;
    }

    public int getDepthOfTree() {
        return depth(root);
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
     * @return inserted node which will be rebalanced
     */
    private BalancedTreeNode<T> insert(BalancedTreeNode<T> node, T data) {
        if (node == null)
            return new BalancedTreeNode<>(data);
        if (node.getData().compareTo(data) > 0) {
            node = new BalancedTreeNode<>(node.getData(), insert(node.getLeft(), data),
                    node.getRight());
        } else {
            if (node.getData().compareTo(data) < 0) {
                node = new BalancedTreeNode<>(node.getData(), node.getLeft(), insert(node.getRight(), data));
            }
        }
        // After insert the new balancedTreeNode, check and rebalance the current tree if necessary.
        return reBalance(node);
    }

    /**
     * Method to rebalance the tree
     * If the tree is too deep on the right, it will rotate towards the left
     * If the tree is too deep on the left, it will rotate towards the right
     *
     * @param node root (whole tree) or other node (subtree) which will be rebalanced
     * @return (new) root node
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
        startNode = new BalancedTreeNode<>(startNode.getData(), leftChild, leftOfRightChild);
        rightChild = new BalancedTreeNode<>(rightChild.getData(), startNode, rightOfRightChild);
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
        startNode = new BalancedTreeNode<>(startNode.getData(), rightOfLeftChild, rightChild);
        leftChild = new BalancedTreeNode<>(leftChild.getData(), leftOfLeftChild, startNode);
        return leftChild;
    }

    //TODO this doesn't seem to help unfortunately
    private void rotateTowardsLighterSide(BalancedTreeNode<T> nodeToRemove) {
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
    public boolean search(T data) {
        BalancedTreeNode<T> searchNode = root;
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
     * Method to search a node
     *
     * @param data contained by node
     * @return node that is being searched or null if node is not found
     */
    public BalancedTreeNode<T> getNode(T data) {
        BalancedTreeNode<T> searchNode = root;
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
    public BalancedTreeNode<T> remove(BalancedTreeNode<T> nodeToRemove) {
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

    private void removeNodeWithOneChild(BalancedTreeNode<T> nodeToRemove) {
        BalancedTreeNode<T> parentNode = findParentNode(nodeToRemove);
        BalancedTreeNode<T> childNode = findOnlyChild(nodeToRemove);
        if ((parentNode.getLeft() != null) && (parentNode.getLeft().getData().compareTo(nodeToRemove.getData()) == 0)) {
            parentNode.setLeft(childNode);
        } else {
            parentNode.setRight(childNode);
        }
    }

    private void removeNodeWhichIsLeaf(BalancedTreeNode<T> nodeToRemove) {
        BalancedTreeNode<T> parentNode = findParentNode(nodeToRemove);
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

    private boolean hasOneChild(BalancedTreeNode<T> nodeToRemove) {
        return ((nodeToRemove.getLeft() == null | nodeToRemove.getRight() == null) &&
                (nodeToRemove.getLeft() != null | nodeToRemove.getRight() != null));
    }

    private BalancedTreeNode<T> findOnlyChild(BalancedTreeNode<T> nodeToRemove) {
        BalancedTreeNode<T> childNode = new BalancedTreeNode<T>();

        if (nodeToRemove.getLeft() != null) {
            childNode = nodeToRemove.getLeft();
        }
        if (nodeToRemove.getRight() != null) {
            childNode = nodeToRemove.getRight();
        }
        return childNode;
    }

    private boolean hasNoChild(BalancedTreeNode<T> nodeToRemove) {
        return nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null;
    }

    public boolean getIfNodeIsLeaf(BalancedTreeNode<T> nodeToCheck) {
        return hasNoChild(nodeToCheck);
    }

    /**
     * Method to get the parent node of a node
     *
     * @param childNode node to get the parent from
     * @return parentNode
     */
    private BalancedTreeNode<T> findParentNode(BalancedTreeNode<T> childNode) {
        MoveDirection whereDidTheChildGo;

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

    public BalancedTreeNode<T> getParentNode(BalancedTreeNode<T> childNode) {
        return findParentNode(childNode);
    }

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
        BalancedTreeNode<T> replacementNode = findNodeToReplaceRemoved(nodeToRemove);

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
    private BalancedTreeNode<T> findNodeToReplaceRemoved(BalancedTreeNode<T> nodeToRemove) {
        BalancedTreeNode<T> nodeToReplaceWith;

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

    public BalancedTreeNode<T> getNodeToReplaceRemoved(BalancedTreeNode<T> nodeToRemove) {
        return findNodeToReplaceRemoved(nodeToRemove);
    }

    /**
     * Method to find the biggest value in the (sub)tree
     *
     * @return Data from node with the biggest value
     */
    private T Maximum(BalancedTreeNode<T> subTreeNode) {
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
    private T Minimum(BalancedTreeNode<T> subTreeNode) {
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
    public BalancedTree<String> clear() {
        this.root = null;
        return new BalancedTree<>();
    }

    public BalancedTreeNode<T> getRoot() {
        return this.root;
    }

    public String toString() {
        return root.toString();
    }

    public void printTree() {
        root.level = 0;
        LinkedList<BalancedTreeNode<T>> list = new LinkedList<BalancedTreeNode<T>>();

        list.add(root);
        while (!list.isEmpty()) {
            BalancedTreeNode<T> balancedTreeNode = list.poll(); //poll: Retrieves and removes the head (first element) of this list.
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