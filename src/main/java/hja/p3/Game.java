package hja.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Game {
    
    private final int N_PLAYERS = 6;
    private ArrayList<Player> PlayerList = new ArrayList<>();
    private ArrayList<Double> PlayerEquityList = new ArrayList<>();
    private DeckOfCards deck;
    private int N_SIMULATIONS = 20000;
    
    public Game(){
        board = new Board();
        deck = new DeckOfCards();
        System.out.println("");
        for(int i = 0; i < N_PLAYERS; i++){
            PlayerList.add(new Player("J" + i));
            PlayerList.get(i).addCard(deck.dealingCard());
            PlayerList.get(i).addCard(deck.dealingCard());  
            System.out.println("J" + i);
            System.out.println(PlayerList.get(i).getPlayerCards().get(0).toString() + " " +PlayerList.get(i).getPlayerCards().get(1).toString());
        }

    }
    
    public void drawCards(int n){
        for(int i = 0; i < n; i++){
            board.addBoardCard(deck.dealingCard());
        }
    }
    
    public ArrayList<Double> getEquity(){
        ArrayList<Double> playerWins = new ArrayList<>();
        for (int i = 0; i < N_PLAYERS; i++) {
            playerWins.add(0.0);
        }
        Stack<Integer> playerTie = new Stack<Integer>();
        int bestPlayer = -1;
        Hand bestHand;
        Hand currHand;
        for(int i = 0; i < N_SIMULATIONS; i++){
            Board board = new Board();
            for(int j = 0; j < 5; j++){
                board.addBoardCard(deck.dealingCard());
            }
            playerTie.push(0);
            bestHand = PlayerList.get(0).getBestHand();
            for(int k = 1; k < N_PLAYERS; k++){
                currHand = PlayerList.get(k).getBestHand();
                if(currHand.compareTo(bestHand) == 1){
                    bestHand = currHand;
                    playerTie.removeAllElements();
                    playerTie.push(k);
                }
                if(currHand.compareTo(bestHand) == 0){
                    playerTie.push(k);
                }
            }
            int nWins = playerTie.size();
            int aux;
            while(!playerTie.empty()){
                aux = playerTie.pop();
                playerWins.set(aux, playerWins.get(aux)+(1.0/nWins));
            }
            
        }
        for(int i = 0; i < N_PLAYERS; i++){
            playerWins.set(i, playerWins.get(i)/N_SIMULATIONS); 
        }
        return playerWins;
    }
    
    

}
