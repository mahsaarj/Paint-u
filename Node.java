package Package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Node extends JPanel {
    private int x, y;
    private boolean colored;
    private Player player;
    private boolean isColored;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.colored = false;
        setPreferredSize(new Dimension(50, 50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        add(new JLabel(String.format("(%d, %d)", x, y), SwingConstants.CENTER)); // Show x,y position of the node on the node itself

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int dx = e.getXOnScreen() - getLocationOnScreen().x;
                int dy = e.getYOnScreen() - getLocationOnScreen().y;
                setLocation(getLocation().x + dx, getLocation().y + dy);
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int dx = 0, dy = 0;
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        dy = -10;
                        break;
                    case KeyEvent.VK_DOWN:
                        dy = 10;
                        break;
                    case KeyEvent.VK_LEFT:
                        dx = -10;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dx = 10;
                        break;
                }
                setLocation(getLocation().x + dx, getLocation().y + dy);
            }
        });
        setFocusable(true);
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
        if (colored) {
            setBackground(Color.RED);
        } else {
            setBackground(Color.WHITE);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public void copyPropertiesFrom(Node other) {
        this.colored = other.colored;
        this.setBackground(other.getBackground());
    }

    // addPlayer method implementation
    public void addPlayer(Player player) {
        if (this.player != null) {
            this.remove(player);
        }
        this.player = player;
        this.add(player);
        this.revalidate();
        this.repaint();
    }

    public Player getPlayer() {
        return player;
    }

}