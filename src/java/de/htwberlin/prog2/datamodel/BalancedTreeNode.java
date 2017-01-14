package de.htwberlin.prog2.datamodel;

/**
 * Created by laura on 14.01.17.
 */

public class BalancedTreeNode<T extends Comparable<T>> implements Comparable<BalancedTreeNode<T>> {

    private T data;
    private BalancedTreeNode<T> left;
    private BalancedTreeNode<T> right;
    public int level;
    private int height;

    /**
     * Constructor method of BalancedTreeNode
     *
     * @param data data belonging to this BalancedTreeNode
     */
    public BalancedTreeNode(T data) {
        this(data, null, null);
    }

    /**
     * Overloaded constructor method of BalancedTreeNode
     *
     * @param data  data belonging to this BalancedTreeNode
     * @param left  left Child of Node
     * @param right right Child of Node
     */
    public BalancedTreeNode(T data, BalancedTreeNode<T> left, BalancedTreeNode<T> right) {
        super();
        this.data = data;
        this.left = left;
        this.right = right;
        if (left == null && right == null)
            setHeight(1);
        else if (left == null)
            setHeight(right.getHeight() + 1);
        else if (right == null)
            setHeight(left.getHeight() + 1);
        else
            setHeight(Math.max(left.getHeight(), right.getHeight()) + 1);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BalancedTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BalancedTreeNode<T> left) {
        this.left = left;
    }

    public BalancedTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BalancedTreeNode<T> right) {
        this.right = right;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int compareTo(BalancedTreeNode<T> node) {
        return this.data.compareTo(node.data);
    }


    @Override
    public String toString() {
        return "Level " + level + ": " + data;
    }
    /*
    @Override
    public String toString() {
        if (this.getLeft() != null && this.getRight() != null) {
            return "Level " + level + ": " + data + " || left " + this.getLeft().data + " | right " + this.getRight().data;
        } else if (this.getLeft() != null && this.getRight() == null) {
            return "Level " + level + ": " + data + " || left " + this.getLeft().data;
        } else if (this.getLeft() == null && this.getRight() != null) {
            return "Level " + level + ": " + data + " || right " + this.getRight().data;
        }
        return "Level " + level + ": " + data;
    }
    */
}

