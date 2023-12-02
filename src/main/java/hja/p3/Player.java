package hja.p3;

import java.util.ArrayList;
import java.util.List;


public class Player implements Comparable<Player>{
    
    protected ArrayList<Card> playerCards = new ArrayList<>();
    private String id;  //J1, J2 ...
    protected Hand bestHand = new Hand();
    private double equity;

    public Player(String id){
        this.id = id;
    }
    public ArrayList<Card> getPlayerCards(){
        return playerCards;
    }

    public void addCard(Card c){
        playerCards.add(c);
    }
    public void bestHand(ArrayList<Card> boardCards){
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(boardCards);
        allCards.addAll(playerCards);
        
        int n = allCards.size()-1;
        int k = 5;
        
        Combinations comb = new Combinations();
        List<List<Card>> combinations = comb.combine(n, k, allCards); //Combinaciones
        for (List<Card> c : combinations) {
            Hand currentHand = new Hand (c); 

            if (currentHand.compareTo(bestHand) == 1)
                bestHand = currentHand;
        }            
    }

    @Override
    public int compareTo(Player other){
        if(this.bestHand.compareTo(other.getBestHand()) == 1) return -1;
        else if (this.bestHand.compareTo(other.getBestHand()) == -1) return 1;
        else return 0;
    }

    @Override
    public String toString(){
        return this.id + ": " + this.bestHand.toString();
    }

    public Hand getBestHand(){
        return bestHand;
    }
    
    public double getEquity(){
        return equity;
    }
    public void resetHand(){
        bestHand = new Hand();
    }
}