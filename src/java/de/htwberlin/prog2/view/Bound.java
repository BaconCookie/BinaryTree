package de.htwberlin.prog2.view;

public class Bound {

    private int x;
    private int y;
    private int width;
    private int height;

    public Bound() {
    }

    public Bound(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Bound setX(int x) {
        this.x = x;
        return this;
    }

    public Bound setY(int y) {
        this.y = y;
        return this;
    }

    public Bound setWidth(int width) {
        this.width = width;
        return this;
    }

    public Bound setHeight(int height) {
        this.height = height;
        return this;
    }
}
