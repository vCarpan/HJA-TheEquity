package hja.p3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;


public class PokerInterface extends JFrame {

public PokerInterface() {
        setTitle("Poker Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        String imagePath = "src\\main\\java\\hja\\p3\\TableroPoker5.jpg";
        ImageIcon backgroundImage = new ImageIcon(imagePath);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setSize(getSize());
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(backgroundLabel, new Integer(Integer.MIN_VALUE));

        // Posicionamiento horizontal de las cartas en el centro
        int cardCount = 5;
        int cardWidth = 100;
        int cardHeight = 150;
        int horizontalSpacing = 20;
        int xStart = (getWidth() - (cardCount * (cardWidth + horizontalSpacing))) / 2;

        for (int i = 1; i <= cardCount; i++) {
            CardPanel cardPanel = new CardPanel("Card " + i);
            cardPanel.setOpaque(false);
            cardPanel.setBounds(xStart + (i - 1) * (cardWidth + horizontalSpacing), getHeight() / 2 - cardHeight / 2, cardWidth, cardHeight);
            layeredPane.add(cardPanel, new Integer(Integer.MAX_VALUE - 1));
        }

        // Posicionamiento de los jugadores alrededor del hexágono
        Point[] playerPositions = calculateHexagonPositions(getWidth() / 2, getHeight() / 2, 250);

        for (int i = 1; i <= 6; i++) {
            PlayerPanel playerPanel = new PlayerPanel("Player " + i);
            playerPanel.setOpaque(false);
            playerPanel.setBounds(playerPositions[i - 1].x - 75, playerPositions[i - 1].y - 75, 150, 150);
            layeredPane.add(playerPanel, new Integer(Integer.MAX_VALUE));
        }
        this.setVisible(true);
    }

    private Point[] calculateHexagonPositions(int centerX, int centerY, int radius) {
        Point[] positions = new Point[6];
        double angle;
        for (int i = 0; i < 6; i++) {
            angle = 2.0 * Math.PI / 6 * i;
            int x = centerX + (int) (Math.cos(angle) * radius);
            int y = centerY + (int) (Math.sin(angle) * radius);
            positions[i] = new Point(x, y);
        }
        return positions;
    }

}
