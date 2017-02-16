package de.htwberlin.prog2.model;

import java.io.Serializable;

/**
 * Created by laura on 14.01.17.
 */

public class BinaryTreeNode implements Serializable {

    private String data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    /**
     * Constructor method of BinaryTreeNode
     * to avoid null pointer exception
     */
    public BinaryTreeNode() {
    }

    /**
     * Overloaded constructor method of BinaryTreeNode
     *
     * @param data data belonging to this BinaryTreeNode
     */
    public BinaryTreeNode(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }


    @Override
    public java.lang.String toString() {
        if (this.getLeft() != null && this.getRight() != null) {
            return "Node: " + data + " || left " + this.getLeft().data + " | right " + this.getRight().data;
        } else if (this.getLeft() != null && this.getRight() == null) {
            return "Node: " + data + " || left " + this.getLeft().data;
        } else if (this.getLeft() == null && this.getRight() != null) {
            return "Node: " + data + " || right " + this.getRight().data;
        }
        return "Node: " + data;
    }

}

