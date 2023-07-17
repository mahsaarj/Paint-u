package Package1;

import javax.swing.*;
import java.awt.*;

public class Play {
    public JPanel nodesPanel;
    private Node[][] nodesGrid;

    public Play() {
        JPanel panel = createPanel();
        createFrame(panel);
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

        // Find the center node in the nodesGrid
        int centerRow = nodesGrid.length / 2;
        int centerCol = nodesGrid[0].length / 2;
        Node centerNode = nodesGrid[centerRow][centerCol];

        // Add the player object to the center node
        Color playerColor = Color.BLUE;
        int playerWidth = 50;
        int playerHeight = 50;
        Player player = new Player(playerColor, playerWidth, playerHeight, nodesPanel);
        int centerX = centerNode.getX() + (centerNode.getWidth() - playerWidth) / 2;
        int centerY = centerNode.getY() + (centerNode.getHeight() - playerHeight) / 2;
        player.setBounds(centerX, centerY, playerWidth, playerHeight);
        centerNode.add(player);

        player.setWidth(100); // set the width after adding player to the node
        player.setHeight(100);

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
}
