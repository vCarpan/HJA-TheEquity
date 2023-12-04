package hja.p3;

import java.util.ArrayList;
import java.util.List;


public class Game implements Cloneable{

    
    
    private final int N_PLAYERS = 6;
    private DeckOfCards deck;
    private int TOTAL_SIMULATIONS = 2000000;
    
    public Game(){
        deck = new DeckOfCards();
    }
 
    public String dealingRandomCard() {
        Card c = deck.dealingCard();
        return c.numberORLetter()+c.getSuit()+"";
    }
    public List<Double> getEquity(List<Card> predBoard, ArrayList<Pair<Player,Boolean>> PlayerList) {
        deck.setOriginalCards();
        System.out.println(deck.getOriginalCards()+":"+deck.getOriginalCards().length());
        System.out.println(deck.getOrderCards()+":"+deck.getOrderCards().length());
        List<Double> playerWins = new ArrayList<>();

        for (int i = 0; i < N_PLAYERS; i++) {
            playerWins.add(0.0);
        }
        Hand bestHand = new Hand();
        Hand currHand;
        int compare;
        calculateSimulations(predBoard.size());
        for (int i = 0; i < TOTAL_SIMULATIONS; i++) {
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
            
            int x=0;
            for (x = 0; x < N_PLAYERS; x++) {
                if(PlayerList.get(x).getElement1()){
                    winningPlayers.add(x);
                    PlayerList.get(x).getElement0().resetHand();
                    PlayerList.get(x).getElement0().bestHand(board.getBoardCard());
                    bestHand = PlayerList.get(x).getElement0().getBestHand();
                    break;
                }
            }

            for (int k = x+1; k < N_PLAYERS; k++) {
                if(PlayerList.get(k).getElement1()){
                    PlayerList.get(k).getElement0().resetHand();
                    PlayerList.get(k).getElement0().bestHand(board.getBoardCard());
                    currHand = PlayerList.get(k).getElement0().getBestHand();
                    compare = currHand.compareTo(bestHand);
                    if (compare == 1) {
                        bestHand = currHand;
                        winningPlayers.clear();
                        winningPlayers.add(k);
                    } else if (compare == 0) {
                        winningPlayers.add(k);
                    }
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
            playerWins.set(i, (playerWins.get(i) / TOTAL_SIMULATIONS) * 100);
            suma += playerWins.get(i);
        }
        System.out.println(suma);

        return playerWins;
    }
    public DeckOfCards getDeck(){
        return deck;
    }
    
    private void calculateSimulations(int size) {
        switch(size){
            case 5:
                TOTAL_SIMULATIONS = 1;
                break;
            case 4: 
                TOTAL_SIMULATIONS = 100000;
                break;
            case 3: 
                TOTAL_SIMULATIONS = 300000;
                break;
            case 2: 
                TOTAL_SIMULATIONS = 500000;
                break;
            case 1: 
                TOTAL_SIMULATIONS = 1500000;
                break;
            case 0: 
                TOTAL_SIMULATIONS = 2000000;
                break;
        }
    }

    
}
