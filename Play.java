package Package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Play {
    public JPanel nodesPanel;
    private Node[][] nodesGrid;

    public Play() {
        JPanel panel = createPanel();
        createFrame(panel);
        addKeyListener(panel);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    public JPanel createPanel() {
        // Create a new JPanel object
        JPanel panel = new JPanel();
        // Set the background color of the panel to white
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 300));
        nodesPanel = new JPanel(new GridLayout(4, 4));
        panel.add(nodesPanel, BorderLayout.CENTER);
        nodesGrid = Node.createNodesGrid(nodesPanel);
        System.out.println("Printing node information:");
        printNodesGrid();

        // Add the KeyListener to the nodesPanel instead of panel
        addKeyListener(nodesPanel);
        nodesPanel.setFocusable(true);
        nodesPanel.requestFocusInWindow();

        // Find the center node in the nodesGrid
        int centerRow = nodesGrid.length / 2;
        int centerCol = nodesGrid[0].length / 2;
        Node centerNode = nodesGrid[centerRow][centerCol];

        // Add the player object to the center node
        Color playerColor = Color.BLUE;
        int playerWidth = 50;
        int playerHeight = 50;
        Player player = new Player(playerColor, playerWidth, playerHeight, nodesPanel);
        centerNode.addPlayer(player);
        centerNode.setColored(true);
        int centerX = centerNode.getX() + (centerNode.getWidth() - playerWidth) / 2;
        int centerY = centerNode.getY() + (centerNode.getHeight() - playerHeight) / 2;
        player.setBounds(centerX, centerY, playerWidth, playerHeight);
        centerNode.setPlayer(player);

        return panel;
    }

    public void createFrame(JPanel panel) {
        // Create a new JFrame object and add the panel to it
        JFrame frame = new JFrame("Play");
        frame.getContentPane().add(panel);

        // Set the properties of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Make the frame visible
        frame.setVisible(true);
    }

    public void printNodesGrid() {
        for (int i = 0; i < nodesGrid.length; i++) {
            for (int j = 0; j < nodesGrid[0].length; j++) {
                Node node = nodesGrid[i][j];
                System.out.printf("Node (%d, %d) is %s%n", node.getXPos(), node.getYPos(), node.isColored() ? "colored" : "not colored");
            }
        }
    }

    public void addKeyListener(JPanel panel) {
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        moveNodes(-1, 0);
                        break;
                    case KeyEvent.VK_DOWN:
                        moveNodes(1, 0);
                        break;
                    case KeyEvent.VK_LEFT:
                        moveNodes(0, -1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveNodes(0, 1);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public void moveNodes(int dx, int dy) {
        JPanel oldNodesPanel = nodesPanel;

        // Create a new JPanel object
        JPanel newNodesPanel = new JPanel(new GridLayout(4, 4));
        newNodesPanel.setBackground(Color.WHITE);

        // Create a new 4x4 grid of nodes
        Node[][] newNodesGrid = Node.createNodesGrid(newNodesPanel);

        // Move each node in the old grid to its new position in the new grid
        for (int i = 0; i < nodesGrid.length; i++) {
            for (int j = 0; j < nodesGrid[0].length; j++) {
                Node oldNode = nodesGrid[i][j];
                int newX = i + dx;
                int newY = j + dy;
                if (newX < 0) {
                    newX += nodesGrid.length;
                } else if (newX >= nodesGrid.length) {
                    newX -= nodesGrid.length;
                }
                if (newY < 0) {
                    newY += nodesGrid[0].length;
                } else if (newY >= nodesGrid[0].length) {
                    newY -= nodesGrid[0].length;
                }
                Node newNode = newNodesGrid[newX][newY];
                newNode.copyPropertiesFrom(oldNode);
                if (newNode.getPlayer() != null) {
                    // Update the player's position
                    Player player = newNode.getPlayer();
                    int playerWidth = player.getWidth();
                    int playerHeight = player.getHeight();
                    int centerX = newNode.getX() + (newNode.getWidth() - playerWidth) / 2;
                    int centerY = newNode.getY() + (newNode.getHeight() - playerHeight) / 2;
                    player.setBounds(centerX, centerY, playerWidth, playerHeight);
                    // Set the new node to colored
                    newNode.setColored(true);
                    // Add the player to the new node's JPanel
                    newNode.addPlayer(player);
                }
            }
        }

        // Replace the old panel with the new panel
        nodesPanel = newNodesPanel;
        nodesGrid = newNodesGrid;
        JPanel parent = (JPanel) oldNodesPanel.getParent();
        parent.remove(oldNodesPanel);
        parent.add(newNodesPanel, BorderLayout.CENTER);
        parent.revalidate();
        parent.repaint();
        newNodesPanel.repaint();
        System.out.println("Printing node information after repaint:");
        printNodesGrid();
    }
}