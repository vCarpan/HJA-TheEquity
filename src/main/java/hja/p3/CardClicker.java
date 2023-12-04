/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hja.p3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;


public class CardClicker extends MouseAdapter{
    
    private JLabel card;
    private DeckOfCards deck;
    
    public CardClicker(JLabel cardLabel, DeckOfCards deck){
        super();
        card = cardLabel;
        this.deck = deck;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {

        CardChooser cardChooser = new CardChooser(card, deck);
        
    }
}
