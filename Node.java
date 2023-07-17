package Package1;

import javax.swing.*;
import java.awt.*;

public class Node extends JPanel {
    private int x, y;
    private boolean colored;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.colored = false;
        setPreferredSize(new Dimension(50, 50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        add(new JLabel(String.format("(%d, %d)", x, y), SwingConstants.CENTER)); // Show x,y position of the node on the node itself
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
        setBackground(colored ? Color.BLACK : Color.WHITE); // Set the background color to black if colored is true, else set it to white
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public static Node[][] createNodesGrid(JPanel panel) {
        Node[][] nodesGrid = new Node[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Node node = new Node(i, j);
                nodesGrid[i][j] = node;
                panel.add(node);
            }
        }
        return nodesGrid;
    }
}