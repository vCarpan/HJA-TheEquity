/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hja.p3;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import hja.p3.DeckOfCards;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

class CardPanel extends JPanel {
    
    private String imagePath = "src\\main\\java\\hja\\p3\\cardsSmaller\\backsideCard.png";
    private ImageIcon cardBack = new ImageIcon(imagePath);
    private CardClicker cardClickerListener;
    JLabel cardLabel;
    
    public CardPanel(String cardName, DeckOfCards deck) {
        setLayout(new FlowLayout());

        cardLabel = new JLabel(cardBack);
        add(cardLabel);
        cardClickerListener = new CardClicker(cardLabel, deck);
        cardLabel.addMouseListener(cardClickerListener);
        JButton random = new JButton("Random");
       
        random.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Icon icon = cardLabel.getIcon();
                    Card ant = Card.getCardByIcon(icon);
                    if(ant!=null){
                        deck.addCard(ant);
                    }
                    Card c  = deck.dealingCard();
                    String path = "src\\main\\java\\hja\\p3\\cardsSmaller\\"+c.numberORLetter()+c.getSuit()+".png";
                    ImageIcon ic = new ImageIcon(path);
                    cardLabel.setIcon(ic);
                }
        });
        add(random);
    }
    public Card getCard(){
        Icon icon = cardLabel.getIcon();
        return Card.getCardByIcon(icon);
    }

}