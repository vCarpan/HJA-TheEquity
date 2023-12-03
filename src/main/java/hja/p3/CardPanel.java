/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hja.p3;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class CardPanel extends JPanel {
    
    private String imagePath = "src\\main\\java\\hja\\p3\\cardsSmaller\\backsideCard.png";
    private ImageIcon cardBack = new ImageIcon(imagePath);
    private CardClicker cardClickerListener;
    
    public CardPanel(String cardName) {
        setLayout(new FlowLayout());

        JLabel cardLabel = new JLabel(cardBack);
        add(cardLabel);
        cardClickerListener = new CardClicker(cardLabel);
        cardLabel.addMouseListener(cardClickerListener);
    }
}