package de.htwberlin.prog2.model;

//needed for printTree method

import java.io.Serializable; //TODO Serializable??
import java.util.LinkedList;


/**
 * Created by laura on 01.01.17.
 */
//TODO Serializable??
public class BinaryTree implements Serializable {

    private BinaryTreeNode root;
    private int size;




    /**
     * Constructor of empty tree
     */
    public BinaryTree() {
        root = null;
    }


    /**
     * Method to determine the depth of the tree
     * Finds and uses nodeWithMaxIndex to measure the depth of the tree with
     *
     * @return int depthOfTree
     */
    public int getDepthOfTree() {
        if (root == null) {
            return 0;
        } else {
            LinkedList<BinaryTreeNode> list = treeAsList();
            BinaryTreeNode nodeWithMaxIndex = list.getLast();
            int depthOfTree = measureDepthOfTree(nodeWithMaxIndex); //TODO new calc check
            return depthOfTree;
        }
    }

    private int measureDepthOfTree(BinaryTreeNode node) {
        int depth = 0;
        if (node == root) {
            return depth;
        } else {
            while (findParentNode(node) != null) {
                depth++;
                node = findParentNode(node);
            }
            return depth;
        }
    }

    /**
     * Method to insert a node
     *
     * @param data from node to insert
     * @return updated root of the tree
     */
    public void insert(String data) {
        try {
            size++;
            if (root == null) {
                root = new BinaryTreeNode(data);
            } else {
                insertRecursive(root, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in insert method");
        }
    }

    /**
     * Insert a node recursively
     *
     * @param nodeToCompareWith node to insert
     * @param data              data
     */
    private void insertRecursive(BinaryTreeNode nodeToCompareWith, String data) {
        //if (data < data from nodeToCompareWith), go to the left
        if (nodeToCompareWith.getData().compareTo(data) > 0) {
            if (nodeToCompareWith.getLeft() == null) {
                nodeToCompareWith.setLeft(new BinaryTreeNode(data));
            } else {
                insertRecursive(nodeToCompareWith.getLeft(), data);
            }
        } //else, go to the right
        else if (nodeToCompareWith.getRight() == null) {
            nodeToCompareWith.setRight(new BinaryTreeNode(data));
        } else {
            insertRecursive(nodeToCompareWith.getRight(), data);
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
        BinaryTreeNode searchNode = root;
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
     * @param data contained by node (string)
     * @return node that is being searched or null if node is not found
     */
    public BinaryTreeNode getNode(String data) {
        BinaryTreeNode searchNode = root;
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
    public void remove(BinaryTreeNode nodeToRemove) {
        try {
            size--;
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
            System.out.println("Error in remove method");
        }
    }

    private void removeNodeWithOneChild(BinaryTreeNode nodeToRemove) {
        BinaryTreeNode parentNode = findParentNode(nodeToRemove);
        BinaryTreeNode childNode = findOnlyChild(nodeToRemove);
        if ((parentNode.getLeft() != null) && (parentNode.getLeft().getData().compareTo(nodeToRemove.getData()) == 0)) {
            parentNode.setLeft(childNode);
        } else {
            parentNode.setRight(childNode);
        }
    }

    private void removeNodeWhichIsLeaf(BinaryTreeNode nodeToRemove) {
        BinaryTreeNode parentNode = findParentNode(nodeToRemove);
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

    private boolean hasOneChild(BinaryTreeNode nodeToRemove) {
        return ((nodeToRemove.getLeft() == null | nodeToRemove.getRight() == null) &&
                (nodeToRemove.getLeft() != null | nodeToRemove.getRight() != null));
    }

    private BinaryTreeNode findOnlyChild(BinaryTreeNode nodeToRemove) {
        BinaryTreeNode childNode = new BinaryTreeNode();

        if (nodeToRemove.getLeft() != null) {
            childNode = nodeToRemove.getLeft();
        }
        if (nodeToRemove.getRight() != null) {
            childNode = nodeToRemove.getRight();
        }
        return childNode;
    }

    private boolean hasNoChild(BinaryTreeNode nodeToRemove) {
        return nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null;
    }

    public boolean getIfNodeIsLeaf(BinaryTreeNode nodeToCheck) {
        return hasNoChild(nodeToCheck);
    }

    /**
     * Method to get the parent node of a node
     *
     * @param childNode node to get the parent from
     * @return parentNode
     */
    private BinaryTreeNode findParentNode(BinaryTreeNode childNode) {
        MoveDirection whereDidTheChildGo;

        //childNode != root, double checking for security
        if (childNode.getData().compareTo(root.getData()) == 0) {
            throw new IllegalArgumentException("If childNode == root, then it has no parent!");
        } else {

            BinaryTreeNode parentNode = root;
            BinaryTreeNode searchChildNode = root;

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

    public BinaryTreeNode getParentNode(BinaryTreeNode childNode) {
        return findParentNode(childNode);
    }

    private BinaryTreeNode followChild(BinaryTreeNode parentNode, MoveDirection dirChild) {
        BinaryTreeNode updatedParentNode = new BinaryTreeNode();
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
    private void removeNodeFromTheMiddle(BinaryTreeNode nodeToRemove) {
        BinaryTreeNode replacementNode = findNodeToReplaceRemoved(nodeToRemove);

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
    private BinaryTreeNode findNodeToReplaceRemoved(BinaryTreeNode nodeToRemove) {
        BinaryTreeNode nodeToReplaceWith;

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

    public BinaryTreeNode getNodeToReplaceRemoved(BinaryTreeNode nodeToRemove) {
        return findNodeToReplaceRemoved(nodeToRemove);
    }

    /**
     * Method to find the biggest value in the (sub)tree
     *
     * @return Data from node with the biggest value
     */
    private String Maximum(BinaryTreeNode subTreeNode) {
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
    private String Minimum(BinaryTreeNode subTreeNode) {
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
     * Also sets size to zero
     */
    public BinaryTree clearTree() {
        this.root = null;
        this.size = 0;
        return new BinaryTree();
    }


    public BinaryTreeNode getRoot() {
        return this.root;
    }

/*
    public String toString() {
        return root.toString();
    }
*/
    @Override
    public java.lang.String toString() {
        BinaryTreeNode node = root;
        if (node.getLeft() != null && node.getRight() != null) {
            return "Node: " + node.getData() + " || left " + node.getLeft().getData()
                    + " | right " + node.getRight().getData();
        } else if (node.getLeft() != null && node.getRight() == null) {
            return "Node: " + node.getData() + " || left " + node.getLeft().getData();
        } else if (node.getLeft() == null && node.getRight() != null) {
            return "Node: " + node.getData() + " || right " + node.getRight().getData();
        }
        return "Node: " + node.getData();
    }
    /**
     * Method to put tree in a LinkedList of <BinaryTreeNode>
     * Acts like breadth first search
     *
     * @return list of nodes as linked list
     */
    public LinkedList<BinaryTreeNode> treeAsList() {
        LinkedList<BinaryTreeNode> list = new LinkedList<>();
        LinkedList<BinaryTreeNode> listWithAllElements = new LinkedList<>();
        list.add(root);
        listWithAllElements.add(root);
        while (!list.isEmpty()) {
            BinaryTreeNode binaryTreeNode = list.poll(); //poll: Retrieves and removes the head (first element) of this list.
            BinaryTreeNode left = binaryTreeNode.getLeft();
            BinaryTreeNode right = binaryTreeNode.getRight();
            if (left != null) {
                list.add(left);
                listWithAllElements.add(left);
            }
            if (right != null) {
                list.add(right);
                listWithAllElements.add(right);
            }
        }
        return listWithAllElements;
    }

    public int getSize() {
        return size;
    }

}
