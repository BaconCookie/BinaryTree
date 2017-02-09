package de.htwberlin.prog2.datamodel;

import de.htwberlin.prog2.gui.ViewPosition;

import java.io.Serializable;

/**
 * Created by laura on 14.01.17.
 */
//<String extends Comparable<String>> implements Comparable<BalancedTreeNode<String>>
public class BalancedTreeNode implements Serializable {

    private String data;
    private BalancedTreeNode left;
    private BalancedTreeNode right;
    private int depth;
    private int x, y;
    private int id;

    public int level;

    public int treeDepth;
    public ViewPosition viewPosition;

    public int getId() {
        return id;
    }

    public BalancedTreeNode setId(int id) {
        this.id = id;
        return this;
    }

    public int getX() {
        return x;
    }

    public BalancedTreeNode setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public BalancedTreeNode setY(int y) {
        this.y = y;
        return this;
    }


    /**
     * Constructor method of BalancedTreeNode
     * to avoid null pointer exception
     */
    public BalancedTreeNode() {
    }

    /**
     * Overloaded constructor method of BalancedTreeNode
     *
     * @param data data belonging to this BalancedTreeNode
     */
    public BalancedTreeNode(String data) {
        this(data, null, null);
    }

    /**
     * Overloaded constructor method of BalancedTreeNode
     *
     * @param data  data belonging to this BalancedTreeNode
     * @param left  left Child of Node
     * @param right right Child of Node
     */
    public BalancedTreeNode(String data, BalancedTreeNode left, BalancedTreeNode right) {
        super();
        this.data = data;
        this.left = left;
        this.right = right;
        if (left == null && right == null)
            setDepth(1);
        else if (left == null)
            setDepth(right.getDepth() + 1);
        else if (right == null)
            setDepth(left.getDepth() + 1);
        else
            setDepth(Math.max(left.getDepth(), right.getDepth()) + 1);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BalancedTreeNode getLeft() {
        return left;
    }

    public void setLeft(BalancedTreeNode left) {
        this.left = left;
    }

    public BalancedTreeNode getRight() {
        return right;
    }

    public void setRight(BalancedTreeNode right) {
        this.right = right;
    }

    /**
     * @return the depth of current (sub)tree
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    /*
        /**
         * Compare data of two Nodes
         * Override is necessary because this class implements Comparable
         *
         * @param node
         * @return
         */
    /*
    @Override
    public int compareTo(BalancedTreeNode node) {
        return this.data.compareTo(node.data);
    }
*/
/*
    @Override
    public String toString() {
        return "Level " + level + ": " + data;
    }
    */

    @Override
    public java.lang.String toString() {
        if (this.getLeft() != null && this.getRight() != null) {
            return "Level " + level + ": " + data + " || left " + this.getLeft().data + " | right " + this.getRight().data;
        } else if (this.getLeft() != null && this.getRight() == null) {
            return "Level " + level + ": " + data + " || left " + this.getLeft().data;
        } else if (this.getLeft() == null && this.getRight() != null) {
            return "Level " + level + ": " + data + " || right " + this.getRight().data;
        }
        return "Level " + level + ": " + data;
    }

}

