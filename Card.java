/** Card.java
*   Author: Chimaobi Onwuka
*   
*   
*   Models a typical playing card
*
*/

class Card{
    
    private char suit;
    private int rank;

    // Initializes a card instance
    public Card(char suit, int rank){
        this.suit = suit;
        this.rank = rank;
    }

    // Accessor for suit
    public char getSuit(){
        return suit;
    }
    
    // Accessor for rank
    public int getRank(){
        return rank;
    }

    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString()
    {
        String rankFull = "";
        String suitFull = "";

        if (rank == 1)
        {
             rankFull = "Ace";
        }
        else if (rank == 11)
        {
             rankFull = "Jack";
        }
        else if (rank == 12)
        {
             rankFull = "Queen";
        }
        else if (rank == 13)
        {
             rankFull = "King";
        }
        else if (rank == 45)
        {
            rankFull = "Joker";
        }
        else
        {
             rankFull = Integer.toString(rank);
        }

        if (suit == 'c')
        {
            suitFull = "Clubs";
        }
        else if (suit == 'd')
        {
            suitFull = "Diamonds";
        }
        else if (suit == 'h')
        {
            suitFull = "Hearts";
        }
        else if (suit == 's')
        {
            suitFull = "Spades";
        }
        else if (suit == 'j')
        {
            suitFull = "Jokers";
        }

        return(rankFull + " of " + suitFull + "\n") ;
    }
}