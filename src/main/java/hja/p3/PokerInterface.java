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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class PokerInterface extends JFrame {
    private Game game;
    public PokerInterface() {
            game = new Game();
            setTitle("Poker Game");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1200, 700);

            JLayeredPane layeredPane = new JLayeredPane();
            setContentPane(layeredPane);

            String imagePath = "src\\main\\java\\hja\\p3\\TableroPoker5.jpg";
            ImageIcon backgroundImage = new ImageIcon(imagePath);
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setSize(getSize());
            backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
            layeredPane.add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

            // Posicionamiento horizontal de las cartas en el centro
            int cardCount = 5;
            int cardWidth = 100;
            int cardHeight = 150;
            int horizontalSpacing = 20;
            int xStart = (getWidth() - (cardCount * (cardWidth + horizontalSpacing))) / 2;
            
            ArrayList<CardPanel> boardPanel = new ArrayList();
            for (int i = 1; i <= cardCount; i++) {
                CardPanel cardPanel = new CardPanel("Card " + i,game.getDeck());
                cardPanel.setOpaque(false);
                cardPanel.setBounds(xStart + (i - 1) * (cardWidth + horizontalSpacing), getHeight() / 2 - cardHeight / 2, cardWidth, cardHeight);
                layeredPane.add(cardPanel, Integer.valueOf(Integer.MAX_VALUE - 1));
                boardPanel.add(cardPanel);
            }

            // Posicionamiento de los jugadores alrededor del hexágono
            Point[] playerPositions = calculateHexagonPositions(getWidth() / 2, getHeight() / 2, 250);
            ArrayList<PlayerPanel> playersPanel = new ArrayList(); 
            for (int i = 1; i <= 6; i++) {
                PlayerPanel playerPanel = new PlayerPanel("Player " + i,game.getDeck());
                playerPanel.setOpaque(false);
                playerPanel.setBounds(playerPositions[i - 1].x - 75, playerPositions[i - 1].y - 75, 150, 150);
                layeredPane.add(playerPanel, Integer.valueOf(Integer.MAX_VALUE));
                playersPanel.add(playerPanel);
            }
            
            JPanel randomPlayerPanel = new JPanel();
            randomPlayerPanel.setLayout(new BorderLayout());
            randomPlayerPanel.setOpaque(false);
            randomPlayerPanel.setBounds(25, 25, 200, 25);
            JButton randomButton = new JButton("Random Players");

            // Agregar un ActionListener al JButton
            randomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0; i<playersPanel.size(); i++){
                        Pair<Card,Card> pair = playersPanel.get(i).getCards();
                        if(pair.getElement0()==null){
                            playersPanel.get(i).setRandomCard1(game.dealingRandomCard());
                        }
                        if(pair.getElement1()==null){
                            playersPanel.get(i).setRandomCard2(game.dealingRandomCard());
                        }
                    }
                    
                }
            });
            randomPlayerPanel.add(randomButton);
            layeredPane.add(randomPlayerPanel);
            
            JPanel evaluatePanel = new JPanel();
            evaluatePanel.setLayout(new BorderLayout());
            evaluatePanel.setOpaque(false);
            evaluatePanel.setBounds(25, 75, 200, 25);
            JButton evaluateButton = new JButton("Evaluate");

            // Agregar un ActionListener al JButton
            evaluateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Card> predBoard = new ArrayList();
                    ArrayList<Player> playerList = new ArrayList();
                    boolean correcto = true;
                    for(int i=0; i<playersPanel.size(); i++){
                        Pair<Card,Card> pair = playersPanel.get(i).getCards();
                        if(pair.getElement0()!=null && pair.getElement1()!=null){
                            playerList.add(new Player("J" + i));
                            playerList.get(i).addCard(pair.getElement0());
                            playerList.get(i).addCard(pair.getElement1());
                        }else{                      
                            correcto = false;
                            JOptionPane.showMessageDialog(null, "Las cartas de los jugadores deben ser seleccionadas", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }                       
                    }
                    for(int i=0; i<boardPanel.size(); i++){
                        Card c = boardPanel.get(i).getCard();
                        if(c!=null){
                            predBoard.add(c);
                        }                  
                    }
                    if(correcto){
                        java.util.List<Double> playerWins = game.getEquity(predBoard,playerList);
                        for(int i=0; i<playerWins.size();i++){                           
                            playersPanel.get(i).setNumber(playerWins.get(i));                            
                        }    
                    }                                        
                }
            });
            evaluatePanel.add(evaluateButton);
            layeredPane.add(evaluatePanel);

            this.setVisible(true);
            this.setResizable(false);
        }

        private Point[] calculateHexagonPositions(int centerX, int centerY, int radius) {
            Point[] positions = new Point[6];
            double angle;
            for (int i = 0; i < 6; i++) {
                angle = 2.0 * Math.PI / 6 * i;
                int x = centerX + (int) (Math.cos(angle) * radius);
                int y = centerY + (int) (Math.sin(angle) * radius);
                switch(i){
                    case(0):
                        x += 200;
                    break;
                    case(3):
                        x -= 200;
                    break;
                    case(1):
                    case(5):
                        x += 50;
                    break;
                    case(2):
                    case(4):
                        x -= 50;
                    break;
                }
                positions[i] = new Point(x, y);
            }
            return positions;
        }

}
