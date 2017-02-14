package de.htwberlin.prog2.view;

import de.htwberlin.prog2.model.BinaryTreeNode;

/**
 * Created by laura on 08.02.17.
 */
public class PositionOfNode {

    private BinaryTreeNode node;
    private int x;
    private int y;
    private int iconSize;

    public PositionOfNode(BinaryTreeNode node, int x, int y, int IconSize) {
        this.node = node;
        this.x = x;
        this.y = y;
        this.iconSize = IconSize;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getMiddleX() {
        return this.x + (this.iconSize / 2);
    }

    public int getY2() {
        return this.y + this.iconSize;
    }

    public int getIconSize() {
        return this.iconSize;
    }

    public BinaryTreeNode getNodeFromList() {
        return this.node;
    }

}
