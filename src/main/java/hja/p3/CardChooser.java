/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hja.p3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardChooser extends JFrame{
    public DeckOfCards deck;
    public CardChooser(JLabel card, DeckOfCards deck) {
        this.deck = deck;
        setLayout(new GridLayout(4, 13)); // 4 filas y 13 columnas para representar las cartas
        setSize(1200,700);
        String cardImagePng = "";
        // Agregar JLabels con las imágenes de las cartas
        for (int i = 0; i < 4; i++) {
            for(int j = 1; j < 14; j++){
                cardImagePng = getCardImage(i ,j);
                String imagePath = "src\\main\\java\\hja\\p3\\cardsSmaller\\" + cardImagePng;
                ImageIcon cardImage = new ImageIcon(imagePath);
                JLabel cartaLabel = new JLabel(cardImage);
                cartaLabel.addMouseListener(new CartaMouseListener(card, cardImage, this));
                if(deck.contains(Card.getCardByIcon(cardImage))) add(cartaLabel);            
            }
        }
        String imagePath = "src\\main\\java\\hja\\p3\\cardsSmaller\\backsideCard.png";
        ImageIcon cardImage = new ImageIcon(imagePath);
        JLabel cartaLabel = new JLabel(cardImage);
        cartaLabel.addMouseListener(new CartaMouseListener(card, cardImage, this));
        add(cartaLabel);
        setVisible(true);
    }

    private String getCardImage(int i, int j) {
        String result = "";
        switch(j){
           case (10):
               result += "T";
           break;
           case (11):
               result += "J";
           break;
           case (12):
               result += "Q";
            break;
           case (13):
               result += "K";
            break;
           case (1):
               result += "A";
            break;
           default:
               result += j;
            break;
       }
        
        switch(i){
           case (0):
               result += "s.png";
            break;
           case (1):
               result += "c.png";
            break;
           case (2):
               result += "d.png";
            break;
           case (3):
               result += "h.png";
            break;
           default:
               result += "";
            break;
       }
        
       return result;
    }

    private static class CartaMouseListener extends MouseAdapter {
        private JLabel card;
        private ImageIcon image;
        private CardChooser frame;

        public CartaMouseListener(JLabel card , ImageIcon cardImage, CardChooser frame) {
            this.card = card;
            this.image = cardImage;
            this.frame = frame;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Aquí puedes llamar a tu función pasando el nombre de la carta
            // Llama a tu función aquí pasando el nombre de la carta
            // funcionDeCartaClicada(nombreCarta);
            Card anterior = Card.getCardByIcon(card.getIcon());
            if(anterior!=null){
                frame.deck.addCard(anterior);
            }
            card.setIcon(image);
            Card nueva = Card.getCardByIcon(card.getIcon());
            if(nueva!=null){
                frame.deck.removeCard(nueva);
            }
            System.out.println("su icon es " + card.getIcon());
            frame.setVisible(false);
            
        }
    }
    
}
