package hja.p3;

import java.util.ArrayList;

public class Game {
    
    private final int N_PLAYERS = 6;
    private ArrayList<Player> PlayerList;
    private ArrayList<Double> PlayerEquityList;
    private Board board;
    private DeckOfCards deck;
    private int N_SIMULATIONS = 200;
    
    public Game(){
        board = new Board();
        deck = new DeckOfCards();
        for(int i = 0; i < N_PLAYERS; i++){
            PlayerList.add(new Player("J" + i));
            PlayerList.get(i).addCard(deck.dealingCard());
            PlayerList.get(i).addCard(deck.dealingCard());   
        }
    }
    
    public void drawCards(int n){
        for(int i = 0; i < n; i++){
            board.addBoardCard(deck.dealingCard());
        }
    }
    
    public ArrayList<Double> getEquity(){
        ArrayList<Double> playerWins = new ArrayList<>();
        ArrayList<Boolean> playerTie;
        int bestPlayer = -1;
        double bestHand = 0;
        int currHand = 0;
        for(int i = 0; i < N_SIMULATIONS; i++){
            
            for(int j = 0; j < 5; j++){
                board.addBoardCard(deck.dealingCard());
            }
            for(int k = 0; k < N_PLAYERS; k++){
                PlayerList.get(i).getBestHand();
                if(currHand > bestHand){
                    
                }
            }
            
        }
    }
    
    

}
