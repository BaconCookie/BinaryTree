package de.htwberlin.prog2.gui;

import java.awt.*;

/**
 * Created by laura on 07.02.17.
 */
public class BinaryTreeCanvas extends java.awt.Canvas {

    @Override
    public void paint(Graphics g) {
        Graphics g2 = this.getGraphics();
        g2.setColor(new Color(255, 0, 0));
        g2.drawString("Hello", 200, 200);
    }
}
