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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
    private JLabel cardLabel1;
    private JLabel cardLabel2;
    JLabel equityLabel;
    JCheckBox foldCheckBox;
    public PlayerPanel(String playerName, DeckOfCards deck) {
        setLayout(new BorderLayout());

        // Nombre del jugador encima de las cartas
        JLabel playerNameLabel = new JLabel(playerName, SwingConstants.CENTER);
        playerNameLabel.setForeground(Color.white);
        add(playerNameLabel, BorderLayout.NORTH);

        cardLabel1 = new JLabel(cardBack);
        cardLabel2 = new JLabel(cardBack);
        add(cardLabel1, BorderLayout.WEST);
        add(cardLabel2, BorderLayout.EAST);
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        JButton random1 = new JButton("Random");
        JButton random2 = new JButton("Random");
       
        random1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Icon icon = cardLabel1.getIcon();
                    Card ant = Card.getCardByIcon(icon);
                    if(ant!=null){
                        deck.addCard(ant);
                    }
                    Card c  = deck.dealingCard();
                    setRandomCard1(c.numberORLetter()+c.getSuit()+"");
                }
        });
        random2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Icon icon = cardLabel2.getIcon();
                    Card ant = Card.getCardByIcon(icon);
                    if(ant!=null){
                        deck.addCard(ant);
                    }
                    Card c  = deck.dealingCard();
                    setRandomCard2(c.numberORLetter()+c.getSuit()+"");
                }
        });
        southPanel.add(random1,BorderLayout.WEST);
        southPanel.add(random2,BorderLayout.EAST);
        southPanel.setOpaque(false);
        // JLabel adicional debajo de las cartas
        
        JPanel foldPanel = new JPanel(); 
        foldPanel.setOpaque(false);
        equityLabel = new JLabel("0%", SwingConstants.CENTER);
        equityLabel.setForeground(Color.white);
        foldPanel.add(equityLabel);
        foldCheckBox = new JCheckBox("Fold");
        foldCheckBox.setOpaque(false);
        foldCheckBox.setForeground(Color.RED);
        foldPanel.add(foldCheckBox);
        southPanel.add(foldPanel,  BorderLayout.SOUTH);
        add(southPanel,BorderLayout.SOUTH);
        // Agregar MouseListener a las cartas
        cardClickerListener = new CardClicker(cardLabel1, deck);
        cardLabel1.addMouseListener(cardClickerListener);

        cardClickerListener = new CardClicker(cardLabel2, deck);
        cardLabel2.addMouseListener(cardClickerListener);
    }
    public Pair<Card,Card> getCards(){
        Card c1;
        Icon icon = cardLabel1.getIcon();
        c1 = Card.getCardByIcon(icon);
        Card c2;
        Icon icon2 = cardLabel2.getIcon();
        c2 = Card.getCardByIcon(icon2);
        return new Pair<Card,Card>(c1,c2); 
    }

    void setRandomCard1(String card) {
        String path = "src\\main\\java\\hja\\p3\\cardsSmaller\\"+card+".png";
        ImageIcon ic = new ImageIcon(path);
        cardLabel1.setIcon(ic);
    }

    void setRandomCard2(String card) {
        String path = "src\\main\\java\\hja\\p3\\cardsSmaller\\"+card+".png";
        ImageIcon ic = new ImageIcon(path);
        cardLabel2.setIcon(ic);
    }
    public void setNumber(Double number){
        equityLabel.setText(number.toString()+"%");
    }
    public boolean isFold(){
        return foldCheckBox.isSelected();
    }
    public void setFold(){
        if(!foldCheckBox.isSelected())
            foldCheckBox.doClick();
    }
}



