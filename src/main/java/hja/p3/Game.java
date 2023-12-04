package hja.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Game {

    
    
    private final int N_PLAYERS = 6;
    private DeckOfCards deck;
    private int N_SIMULATIONS = 2000000;
    
    public Game(){
        deck = new DeckOfCards();
        //System.out.println("");
        /*for(int i = 0; i < N_PLAYERS; i++){
            PlayerList.add(new Player("J" + i));
            PlayerList.get(i).addCard(deck.dealingCard());
            PlayerList.get(i).addCard(deck.dealingCard());  
            System.out.println("J" + i);
            System.out.println(PlayerList.get(i).getPlayerCards().get(0).toString() + " " +PlayerList.get(i).getPlayerCards().get(1).toString());
        }*/
        /*PlayerList.add(new Player("J" + 0));
        PlayerList.get(0).addCard(new Card("8","d"));
        PlayerList.get(0).addCard(new Card("8","h"));
        deck.removeCard(new Card("8","d"));
        deck.removeCard(new Card("8","h"));
        
        PlayerList.add(new Player("J" + 1));
        PlayerList.get(1).addCard(new Card("A","c"));
        PlayerList.get(1).addCard(new Card("A","d")); 
        deck.removeCard(new Card("A","c"));
        deck.removeCard(new Card("A","d"));
        
        PlayerList.add(new Player("J" + 2));
        PlayerList.get(2).addCard(new Card("Q","d"));
        PlayerList.get(2).addCard(new Card("Q","h")); 
        deck.removeCard(new Card("Q","d"));
        deck.removeCard(new Card("Q","h"));
        
        PlayerList.add(new Player("J" + 3));
        PlayerList.get(3).addCard(new Card("A","s"));
        PlayerList.get(3).addCard(new Card("K","s"));
        deck.removeCard(new Card("A","s"));
        deck.removeCard(new Card("K","s"));
        
        PlayerList.add(new Player("J" + 4));
        PlayerList.get(4).addCard(new Card("K","c"));
        PlayerList.get(4).addCard(new Card("Q","s"));
        deck.removeCard(new Card("K","c"));
        deck.removeCard(new Card("Q","s"));
        
        PlayerList.add(new Player("J" + 5));
        PlayerList.get(5).addCard(new Card("7","c"));
        PlayerList.get(5).addCard(new Card("6","d"));
        deck.removeCard(new Card("7","c"));
        deck.removeCard(new Card("6","d"));
        
        for(int i=0; i < predBoard.size(); i++){
            deck.removeCard(predBoard.get(i));
        }*/
        
        

    }
 
    public String dealingRandomCard() {
        Card c = deck.dealingCard();
        return c.numberORLetter()+c.getSuit()+"";
    }
    public List<Double> getEquity(List<Card> predBoard, ArrayList<Player> PlayerList) {
        deck.setOriginalCards();
        System.out.println(deck.getOriginalCards()+":"+deck.getOriginalCards().length());
        System.out.println(deck.getOrderCards()+":"+deck.getOrderCards().length());
        List<Double> playerWins = new ArrayList<>();

        for (int i = 0; i < N_PLAYERS; i++) {
            playerWins.add(0.0);
        }
        Hand bestHand;
        Hand currHand;
        int compare;

        for (int i = 0; i < N_SIMULATIONS; i++) {
            List<Integer> winningPlayers = new ArrayList<>();
            Board board = new Board();

            for (int j = 0; j < (5-predBoard.size()); j++) {
                Card c = deck.dealingCard();
                board.addBoardCard(c);
            }
            for(int c=0; c < predBoard.size(); c++){
                board.addBoardCard(predBoard.get(c));
            }
            
            /*System.out.println("-------------------------------Jugada "+i+"----------------------------------");
            for(int k = 0; k < N_PLAYERS; k++){ 
                System.out.println("J" + k);
                System.out.println(PlayerList.get(k).getPlayerCards().get(0).toString() + " " +PlayerList.get(k).getPlayerCards().get(1).toString());
            }
            System.out.println("Board: "+board.getBoardCard());*/
            winningPlayers.add(0);
            PlayerList.get(0).resetHand();
            PlayerList.get(0).bestHand(board.getBoardCard());
            bestHand = PlayerList.get(0).getBestHand();

            for (int k = 1; k < N_PLAYERS; k++) {
                PlayerList.get(k).resetHand();
                PlayerList.get(k).bestHand(board.getBoardCard());
                currHand = PlayerList.get(k).getBestHand();
                compare = currHand.compareTo(bestHand);
                if (compare == 1) {
                    bestHand = currHand;
                    winningPlayers.clear();
                    winningPlayers.add(k);
                } else if (compare == 0) {
                    winningPlayers.add(k);
                }
            }

            int nWins = winningPlayers.size();
            //System.out.println("Ganando: "+winningPlayers.toString());
            for (int player : winningPlayers) {
                double previous = playerWins.get(player);
                playerWins.set(player, previous + (1.0 / nWins));
            }

            deck.reset();
        }
        double suma = 0.0;    
        for (int i = 0; i < N_PLAYERS; i++) {
            playerWins.set(i, (playerWins.get(i) / N_SIMULATIONS) * 100);
            suma += playerWins.get(i);
        }
        System.out.println(suma);

        return playerWins;
    }
    public DeckOfCards getDeck(){
        return deck;
    } 

    
}
