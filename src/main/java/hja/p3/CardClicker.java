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
    
    public CardClicker(JLabel cardLabel){
        super();
        card = cardLabel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        CardChooser cardChooser = new CardChooser(card);
    }
}
