/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hja.p3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author penap
 */
class PlayerPanel extends JPanel {

    private String imagePath = "src\\main\\java\\hja\\p3\\cardsSmaller\\backsideCard.png";
    private ImageIcon cardBack = new ImageIcon(imagePath);
    private CardClicker cardClickerListener;

    public PlayerPanel(String playerName) {
        setLayout(new BorderLayout());

        // Nombre del jugador encima de las cartas
        JLabel playerNameLabel = new JLabel(playerName, SwingConstants.CENTER);
        playerNameLabel.setForeground(Color.white);
        add(playerNameLabel, BorderLayout.NORTH);

        JLabel cardLabel1 = new JLabel(cardBack);
        JLabel cardLabel2 = new JLabel(cardBack);
        add(cardLabel1, BorderLayout.WEST);
        add(cardLabel2, BorderLayout.EAST);

        // JLabel adicional debajo de las cartas
        JLabel equityLabel = new JLabel("0%", SwingConstants.CENTER);
        equityLabel.setForeground(Color.white);
        add(equityLabel, BorderLayout.SOUTH);

        // Agregar MouseListener a las cartas
        cardClickerListener = new CardClicker(cardLabel1);
        cardLabel1.addMouseListener(cardClickerListener);

        cardClickerListener = new CardClicker(cardLabel2);
        cardLabel2.addMouseListener(cardClickerListener);
    }
}



