package hja.p3;

import java.util.ArrayList;

public class Board {
    private ArrayList<Card> boardCards;
    
    public Board(){
        boardCards = new ArrayList<>();
    }
    
    public void addBoardCard(Card c){
        boardCards.add(c);
    }
    public ArrayList<Card> getBoardCard(){
        return boardCards;
    }
    
}
