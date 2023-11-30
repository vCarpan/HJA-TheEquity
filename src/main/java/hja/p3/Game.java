package hja.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

public class Game {
    
    private final int N_PLAYERS = 6;
    private ArrayList<Player> PlayerList = new ArrayList<>();
    private ArrayList<Double> PlayerEquityList = new ArrayList<>();
    private DeckOfCards deck;
    private int N_SIMULATIONS = 200000;
    private Board board;
    
    public Game(){
        board = new Board();
        deck = new DeckOfCards();
        System.out.println("");
        /*for(int i = 0; i < N_PLAYERS; i++){
            PlayerList.add(new Player("J" + i));
            PlayerList.get(i).addCard(deck.dealingCard());
            PlayerList.get(i).addCard(deck.dealingCard());  
            System.out.println("J" + i);
            System.out.println(PlayerList.get(i).getPlayerCards().get(0).toString() + " " +PlayerList.get(i).getPlayerCards().get(1).toString());
        }*/
        PlayerList.add(new Player("J" + 0));
        PlayerList.get(0).addCard(new Card("8","d"));
        PlayerList.get(0).addCard(new Card("8","h"));
        
        PlayerList.add(new Player("J" + 1));
        PlayerList.get(1).addCard(new Card("A","d"));
        PlayerList.get(1).addCard(new Card("A","c")); 
        
        PlayerList.add(new Player("J" + 2));
        PlayerList.get(2).addCard(new Card("Q","h"));
        PlayerList.get(2).addCard(new Card("Q","d")); 
        
        PlayerList.add(new Player("J" + 3));
        PlayerList.get(3).addCard(new Card("A","s"));
        PlayerList.get(3).addCard(new Card("K","s")); 
        
        PlayerList.add(new Player("J" + 4));
        PlayerList.get(4).addCard(new Card("K","c"));
        PlayerList.get(4).addCard(new Card("Q","s"));
        
        PlayerList.add(new Player("J" + 5));
        PlayerList.get(5).addCard(new Card("6","s"));
        PlayerList.get(5).addCard(new Card("7","c")); 
        
       deck.setDeck("2h3h4h5h6h7h9hThJhKhAh2d3d4d5d6d7d9dTdJdKd2c3c4c5c6c8c9cTcJcQc2s3s4s5s7s8s9sTsJs");
       deck.setOriginalCards();

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
        int compare;
        Hand bestHand;
        Hand currHand;
        Map<Card,Integer> reps = new HashMap<Card, Integer>();
        for(int i = 0; i < N_SIMULATIONS; i++){
            Board board = new Board();
            for(int j = 0; j < 5; j++){
                Card c = deck.dealingCard();
                board.addBoardCard(c);
                if(reps.containsKey(c)){
                reps.replace(c, reps.get(c) +1);
                }
                else{
                    reps.put(c, 1);
                }
            }
            playerTie.push(0);
            PlayerList.get(0).bestHand(board.getBoardCard());
            bestHand = PlayerList.get(0).getBestHand();
            for(int k = 1; k < N_PLAYERS; k++){

                PlayerList.get(k).bestHand(board.getBoardCard());
                currHand = PlayerList.get(k).getBestHand();
                //System.out.println("Jugador"+ k + " tiene: " + currHand + " contra: "+ bestHand );
                compare = currHand.compareTo(bestHand);
                if(compare == 1){
     
                    bestHand = currHand;
                    playerTie.removeAllElements();
                    playerTie.push(k);
                }
                else if(compare == 0){
                    playerTie.push(k);
                }
                //System.out.println("Esta ganando " + playerTie.peek());
            }
            int nWins = playerTie.size();
            int aux;
            //System.out.print("Resultado final: "+playerTie.toString() );
            while(!playerTie.empty()){
                aux = playerTie.pop();
                playerWins.set(aux, playerWins.get(aux)+(1.0/nWins));
            }
            //System.out.println(board.getBoardCard());
            deck.setDeck("2h3h4h5h6h7h9hThJhKhAh2d3d4d5d6d7d9dTdJdKd2c3c4c5c6c8c9cTcJcQc2s3s4s5s7s8s9sTsJs");
        }
        for(int i = 0; i < N_PLAYERS; i++){
            playerWins.set(i, (playerWins.get(i)/N_SIMULATIONS)*100); 
        }
        System.out.println(reps);
        return playerWins;
    }
    
    

}
