package Package1;

import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    private Color color;
    private int width;
    private int height;

    public Player(Color color, int width, int height, JPanel panel) {
        this.color = color;
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setBackground(color);
        int x = (panel.getWidth() - width) / 2;
        int y = (panel.getHeight() - height) / 2;
        setBounds(x, y, width, height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        setPreferredSize(new Dimension(width, height));
        int x = (getParent().getWidth() - width) / 2;
        int y = (getParent().getHeight() - height) / 2;
        setBounds(x, y, width, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        int x = (getParent().getWidth() - width) / 2;
        int y = (getParent().getHeight() - height) / 2;
        setBounds(x, y, width, height);
    }

}
