/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hja.p3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public PlayerPanel(String playerName) {
        setLayout(new BorderLayout());

        // Nombre del jugador encima de las cartas
        JLabel playerNameLabel = new JLabel(playerName, SwingConstants.CENTER);
        add(playerNameLabel, BorderLayout.NORTH);

        // Panel para las cartas
        JPanel cardPanel = new JPanel(new FlowLayout());
        JButton cardButton1 = new JButton("Card 1");
        JButton cardButton2 = new JButton("Card 2");
        cardPanel.add(cardButton1);
        cardPanel.add(cardButton2);
        add(cardPanel, BorderLayout.CENTER);

        // JLabel adicional debajo de las cartas
        JLabel additionalLabel = new JLabel("Otro JLabel", SwingConstants.CENTER);
        add(additionalLabel, BorderLayout.SOUTH);

        // ActionListeners para las cartas
        cardButton1.addActionListener(e -> JOptionPane.showMessageDialog(null, "Operación con carta 1 realizada"));
        cardButton2.addActionListener(e -> JOptionPane.showMessageDialog(null, "Operación con carta 2 realizada"));
    }
}

class CardPanel extends JPanel {
    public CardPanel(String cardName) {
        setLayout(new FlowLayout());
        JLabel cardLabel = new JLabel(cardName);
        JButton cardButton = new JButton("Operación con carta");

        add(cardLabel);
        add(cardButton);

        cardButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Operación con carta realizada"));
    }
}