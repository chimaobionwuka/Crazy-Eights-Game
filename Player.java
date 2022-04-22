/** Player.java
*   Author: Chimaobi Onwuka
*   UNI: ceo2134
*   
*   Player class as part of Crazy Eights
*   To be used with Game, Card, Deck classes
*
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

class Player{
    
    private ArrayList<Card> hand = new ArrayList<Card>(); // the player's hand
    private Scanner intput = new Scanner(System.in);
    int playerChoice = 1;

    public Player()
    {
     
    }

    // Adds a card to the player's hand
    public void addCard(Card c)
    {
        hand.add(c);
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    //public Card playsTurn(Deck deck){
        // your code here
    //}

    public void removeCard(int c)
    {
        hand.remove(c);
    }
    
    // Accessor for the players hand
    public ArrayList<Card> getHand()
    {

        return hand;
    }

    //Accessor for the players card
    public Card getCard(int C)
    {

        return hand.get(C);
    }

    //Clears players hand
    public void clear()
    {
        hand.clear();
    }




    // Returns a printable string representing the player's hand
    public String handToString()
    {
        String rockle = "";
        for (int i = 0; i < hand.size(); i++)
        {
            rockle += (i+1) + "\t" + hand.get(i).toString();
        }
        return rockle;
       
    }

    public int playerTurn()
    {
        try
        {
        System.out.println("Type '0' to draw a card, or type the number next to the card in your hand that you wish to play" );
        playerChoice = intput.nextInt();
        System.out.println("\n---------------------------------------------------------------------------------------------");
        return playerChoice;
        }
        catch(Exception e)
    {
        System.out.println("You put in the wrong input.");
    }
        playerChoice = 1000;
        return playerChoice;
    }

// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.

} // end
