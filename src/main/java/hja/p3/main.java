package hja.p3;

import java.util.ArrayList;
import java.util.List;

public class main{

    public static void main(String[] args) {
        List<Card> cartas = new ArrayList<>();
        cartas.add(new Card("Q","c"));
        cartas.add(new Card("6","s"));
        cartas.add(new Card("8","c"));
        cartas.add(new Card("K","d"));
        cartas.add(new Card("K","h"));
        
        Game game = new Game(cartas);
        System.out.println(game.getEquity(cartas));
    }
}
