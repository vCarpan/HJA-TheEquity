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
    
    public CardChooser(JLabel card) {
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
                cartaLabel.addMouseListener(new CartaMouseListener(j,i, card, cardImage, this));
                add(cartaLabel);
            }
        }
        setVisible(true);
    }

    private String getCardImage(int i, int j) {
        String result = "";
        switch(j){
           case (11):
               result += "jack";
           break;
           case (12):
               result += "queen";
            break;
           case (13):
               result += "king";
            break;
           case (1):
               result += "ace";
            break;
           default:
               result += j;
            break;
       }
        
        result += "_of_";
        switch(i){
           case (0):
               result += "spades.png";
            break;
           case (1):
               result += "clubs.png";
            break;
           case (2):
               result += "diamonds.png";
            break;
           case (3):
               result += "hearts.png";
            break;
           default:
               result += "";
            break;
       }
        
       return result;
    }

    private static class CartaMouseListener extends MouseAdapter {
        private final String nombreCarta;
        private JLabel card;
        private ImageIcon image;
        private CardChooser frame;

        public CartaMouseListener(int number, int suit, JLabel card , ImageIcon cardImage, CardChooser frame) {
            this.nombreCarta = number+" "+suit;
            this.card = card;
            this.image = cardImage;
            this.frame = frame;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Aquí puedes llamar a tu función pasando el nombre de la carta
            System.out.println("Carta clicada: " + nombreCarta);
            // Llama a tu función aquí pasando el nombre de la carta
            // funcionDeCartaClicada(nombreCarta);
            card.setIcon(image);
            System.out.println("La carta es" + nombreCarta + " y su icon es " + card.getIcon());
            frame.setVisible(false);
            
        }
    }
    
}
