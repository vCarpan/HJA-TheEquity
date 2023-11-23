
package hja.p3;

import java.util.ArrayList;

public class DeckOfCards {
   
    private String orderCards;
    private String originalCards;
    public DeckOfCards(){
        orderCards = "2h3h4h5h6h7h8h9hThJhQhKhAh2d3d4d5d6d7d8d9dTdJdQdKdAd2c3c4c5c6c7c8c9cTcJcQcKcAc2s3s4s5s6s7s8s9sTsJsQsKsAs";
    }
    
    public DeckOfCards(ArrayList<Card> cardList){
        orderCards = "2h3h4h5h6h7h8h9hThJhQhKhAh2d3d4d5d6d7d8d9dTdJdQdKdAd2c3c4c5c6c7c8c9cTcJcQcKcAc2s3s4s5s6s7s8s9sTsJsQsKsAs";
        for(int i= 0; i < cardList.size(); i++){
            orderCards.replace(cardList.get(i).toString(), "");
        }
        originalCards = orderCards;
    }
    public void reset(){
        orderCards = originalCards;
    }
    
    public Card dealingCard(){
        String randomCards = "";
        if (orderCards == null || orderCards.length()<1) {
            return null;
        }else{
            int numero = (int)(Math.random()*orderCards.length());//
            if(numero%2==0){
                randomCards += ""+orderCards.charAt(numero) + orderCards.charAt(numero + 1);
                orderCards = orderCards.replace(""+orderCards.charAt(numero) + orderCards.charAt(numero + 1),"");
            }else{
                randomCards += ""+orderCards.charAt(numero - 1) + orderCards.charAt(numero);
                orderCards = orderCards.replace(""+orderCards.charAt(numero - 1) + orderCards.charAt(numero),"");
            }
        }
        return  new Card(randomCards.charAt(0)+"",randomCards.charAt(1)+"");             
    }
    
    
    
}