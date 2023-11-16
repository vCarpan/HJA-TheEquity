package hja.p3;

import java.util.ArrayList;

public class Board {
    private ArrayList<Card> boardCards = new ArrayList<>();
    
    public Board(){
        
    }
    
    public void addBoardCard(Card c){
        boardCards.add(c);
    }
    public ArrayList<Card> getBoardCard(){
        return boardCards;
    }
    
}
