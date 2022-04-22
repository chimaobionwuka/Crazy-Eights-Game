/** Deck.java
*   Author: Chimaobi Onwuka
*   UNI: ceo2134
*   
*   Models a typical deck of playing cards
*   To be used with Card class
*
*/
import java.util.*;

class Deck{

    private Card[] deck; // contains the cards to play with
    private int top = 0; // controls the "top" of the deck to deal from
    private char[] Suites = {'c', 'h', 'd', 's'};
    private int[] Ranks = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    private  Card joker = new Card('j', 45);
    


    // constructs a default Deck
    public Deck()
    {
        deck = new Card[52];
        int count = 0;

        for (int l = 0; l < Suites.length; l++)
        {
            for (int n = 0; n < Ranks.length; n++)
            {
                Card Card = new Card(Suites[l], Ranks[n]);
                deck[count] = Card;
                count += 1;
            }
        }
    }

    // Deals the top card off the deck
    

    public Card deal()
    {
        if (canDeal() == true)
        {
        top = top +1;
        return deck[top - 1];
        }
        else
        {
            return joker;
        }
    }

    // returns true provided there is a card left in the deck to deal
    public boolean canDeal(){
        if (top > 51)
        {
            return false;
        }
        else 
        {
            return true;   
        }

    }

    // Shuffles the deck
    public void shuffle()
    {
       top = 0;
       ArrayList<Card> arrayDeck = new ArrayList<Card>(Arrays.asList(deck));
       Collections.shuffle(arrayDeck);
       deck = arrayDeck.toArray(new Card[0]);
    }



    // Returns a string representation of the whole deck
    public String toString(){
        String popple = "";
        for (int i = 0; i < deck.length; i++)
        {
            popple += (i+1) + "\t" + deck[i].toString();
        }
        return popple;
       
    }

    // you may wish to have more helper methods to simplify
    // and shorten the methods above.
}