/** Game.java
*   Author: Chimaobi Onwuka
    UNI: ceo2134
*   
*   
*   Game class for playing crazy eights in commandline
*   To be used with Player, Card, Deck classes
*
*/


import java.util.Scanner;
import java.util.ArrayList;

class Game
{
    //Given Instance Variables
    private char currentSuit; // need in case an 8 is played
    private Card faceup; 
    private Scanner input;
    private Player p1 = new Player();
    private ArrayList<Card> compHand = new ArrayList<Card>();
    private String psuedohand = ""; //To get the hand of the player into a string in this class
    private Deck cards;

    //Fabricated Instance Variables
    private String sCurrentSuit = "";
    private boolean cont = true;
    private int borpus;
    private int playAgain = 3;
    private Card joker = new Card('j', 45);
    private boolean go = false;
    private boolean leave = false;
    private Card temp = new Card('c', 86);
    
    // sets up the Game object for play
    public Game()
    {

        // Creation of Scanner Instance
        input = new Scanner(System.in);
         
         //Creation of Player Instance

         //Deck Instanciation and Shuffling
        cards = new Deck();
        cards.shuffle();

        //Computer Hand Deck Creation
         for (int i  = 0; i < 7; i++)
         {
             compHand.add(cards.deal());
             p1.addCard(cards.deal());
         }
         
         //FaceUp Card Initialzition
         faceup = cards.deal();
         currentSuit = faceup.getSuit();
         change();

        //Welcome UI Info
         System.out.println( "\n" + "Welcome to Crazy Eights! You'll start with 7 cards." + 
         " Your job is to match a card in your hand with the up card." + "\nYou can match it by suit or rank." +
         "\nIf you play an 8, you can switch the active suit." +
         " If you run out of cards, you win!" +
         " If you make it through the whole deck then whoever has" +
         " the fewest cards left wins!" +
         " Good luck! " + "\n");
        
    }


    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play()
    {

        if (playAgain != 2)
        {
            while (cont == true)
            {
                //User Display
                userDisplay();
                
                if ((p1.getHand().size() > 0) && (compHand.size() > 0) && (playAgain !=2))
                {
                    //Handling of Player Input and Dealing
                    corpusDeal();

                    //Player Turn Movement
                    playerTurnMovement();

                    //Computer Turn Movement
                    leave = false;
                    computerTurn();

                //Player and Computer Conditions Check
                p1Check();
                compCheck();
                
                }
            }
        }

        return go;
    }

    //Computer Player Logic
    private Card computerTurn()
    {
        //Initilaization of Temp Card pulled from Computer Deck
        int cardSelect = 0;
        boolean leave = false;

    if (playAgain != 2)
    {

        //Controls Card Flipping
        for (int i = 0; i < compHand.size(); i++)
        {
            //Escape Sequence
            if (leave == true)
            {
                //break;
            }

            //Keeps track of the Computer's Current Card and Index
            temp = compHand.get(i);
            cardSelect = i;

            //Main Computer Logic (If it Computer's Card = given Suit, Rank, or is a Crazy 8 it is played)
            if ((compHand.get(i).getSuit() == currentSuit) || (compHand.get(i).getRank() == faceup.getRank()) || (compHand.get(i).getRank() == 8))
            {
             
             if (playAgain != 2)
                {   
                    // Computer using "Crazy 8" and changing Suit
                    compCrazy();

                        //Computer Choice
                        System.out.println("\nThen The Computer has gone with card: " + temp.toString() +"\n");
                        //System.out.println("Computers Cards: \n" + compHand.toString());
                }

                //Setting of Faceup and Removal of Card 
                faceup = compHand.get(i);

                if (temp.getRank() != 8)
                {
                    currentSuit();
                }
                
                compHand.remove(i);

                //Leave initiation
                leave = true;
                return temp;
            }
        }
            //Secondary Computer Logic (If Computer's Card doesnt match any of given parameters: uses method to deal)
            if (((temp.getSuit() != currentSuit) || (temp.getRank() != faceup.getRank())) && (leave == false))
            {
                computerTurnElse();
            }
    }
        //Returns Chosen Card
        return temp;
    }

    //Method that Handles the Computer Deck being dealt a Card then using recursion to restart ComputerTurn
    public void computerTurnElse()
    {
        if (playAgain != 2)
        {
            compHand.add(cards.deal());
            //System.out.println("\nDealt a card: " + compHand.get(compHand.size()-1).toString() + "to Computer\n"); //For testing Purposes
            System.out.println("\nDealt a card to Computer\n");
            compCheck();
            computerTurn();
        }
    }


    //Sets the current Suit to the Face Up Card's Suit
    public void currentSuit() 
    {
        currentSuit = faceup.getSuit();
        change();
    }

    //Makes Current Suit Variable more visually appealing
    public void change()
    {
        if (currentSuit == 'c')
        {
            sCurrentSuit = "Clubs";
        }
        else if (currentSuit == 'd')
        {
            sCurrentSuit = "Diamonds";
        }
        else if (currentSuit == 's')
        {
            sCurrentSuit = "Spades";
        }
        else if (currentSuit == 'h')
        {
             sCurrentSuit = "Hearts";
        }
    }

    //Makes Suit Variable more visually appealing
    public String suitoString(int i) 
    {
        if (compHand.get(i).getSuit() == 'c')
        {
            return "Clubs";
        }
        else if (compHand.get(i).getSuit() == 'd')
        {
            return "Diamonds";
        }
        else if (compHand.get(i).getSuit() == 's')
        {
            return "Spades";
        }
        else if (compHand.get(i).getSuit() == 'h')
        {
             return "Hearts";
        }
        return "hi";
    }
    
    //Shorthand for p1.handToString (reduces errors)
    public void p1Hand()
    {
        psuedohand = p1.handToString();
    }

    //Check Computer's Deck Size and End Card to determine if gameOver Sequence should be activated
    public void compCheck()
    {
         if (playAgain != 2)
        {
        if ((compHand.size() == 0))
        {
            cont = false;
            System.out.println("**Activated Comp Check**\n");
            compGameOver();
        }
        else if ((compHand.get(compHand.size()-1)).getSuit() == joker.getSuit())
        {
            if (p1.getHand().size() > compHand.size())
            {
                cont = false;
                System.out.println("**Activated Comp Check**\n");
                compGameOver();
            }
            else if (compHand.size() > p1.getHand().size())
            {
                cont = false;
                System.out.println("Activated Comp Check**\n");
                p1GameOver();
            }
        }
        }
    }

    //Check Player 1's Deck Size and End Card to determine if gameOver Sequence should be activated
    public void p1Check()
    {
         if (playAgain != 2)
        {
            if ((p1.getHand().size() == 0))
            {
                cont = false;
                System.out.println("**Activated p1 Check**\n");
                p1GameOver();
            }
            else if ((p1.getCard(p1.getHand().size() -1).getSuit() == joker.getSuit()))
            {
                if (p1.getHand().size() > compHand.size())
                {
                    cont = false;
                    System.out.println("**Activated p1 Check**\n");
                    compGameOver();
                }
                else if (compHand.size() > p1.getHand().size())
                {
                    cont = false;
                    System.out.println("**Activated p1 Check**\n");
                    p1GameOver();
                }
            }
        }
    }

    //Game Over Sequence for Computer
    public void compGameOver()
    {
        if (playAgain != 2)
        {
            System.out.println("**The Computer has won!**\n");
            //clearRefresh();
            System.out.println("Do you want to play again ('1 = Yes'/'2 = No')? ");
            playAgain = input.nextInt();
            playAgain();
            play();
        }
        
    }
    //Game Over Sequence for Computer
    public void p1GameOver()
    {
        if (playAgain != 2)
        {
            System.out.println("**You have won!**\n");
            System.out.println("Do you want to play again ('true = 1'/'false = 2')? ");
            playAgain = input.nextInt();
            playAgain();
        }
    }

    //Handles the play Again sequence and enacts the refreshing of game if "yes"
    public void playAgain()
    {
        if (playAgain == 1)
            {
                go = true;
                cont = true;
                clearRefresh(); ///// a "clean method" that shuffles deck, removes all cards then adss them from deck again and make a thing in deck than when return joker set top to false again
                play();
            }
        else if (playAgain == 2)
            {
                cont = false;
                go = false;
                System.out.println("Thank you for Crazy 8 Abrdiged <3");
                play();
            }
    }

    //Simple Hand to String method
    public String toString()
    {
        String rockle = "";
        for (int i = 0; i < compHand.size(); i++)
        {
            rockle += (i+1) + "\t" + compHand.get(i).toString();
        }
        return rockle;
       
    }

    //Clears the Computer and Player of Decks, Shuffles Cards, Resets faceup, and Redeals Cards
    public void clearRefresh()
    {
        compHand.clear();
        p1.clear();
        cards.shuffle();
        for (int i  = 0; i < 7; i++)
        {
            compHand.add(cards.deal());
            p1.addCard(cards.deal());
            currentSuit();
        }
        faceup = cards.deal();
        System.out.println("Alrighty Great! See New Game BELOW!\n --------------------------------------------------------\n");

    }

    //Handles the Logic of Player Input from Player Class and checks to see if input is "draw" or selection of card
    public void corpusDeal()
    {
        //Input from Player Class
                int corpus = p1.playerTurn();

        //Handling of Player Input and Dealing
                if (corpus == 0 && playAgain !=2)
                {
                    p1.addCard(cards.deal());
                    System.out.println("You drew a: " + p1.getCard(p1.getHand().size()-1) );
                    p1Check();
                    compCheck();
                    play();
                }
                else 
                {
                    borpus = corpus-1;
                }
    }

    //Informs the user of Up Card, Current Suit, Player Cards, and Computer Card's (if enabled)
    public void userDisplay()
    {
            p1Hand();
            System.out.println("------------------------------------\nThe up card is the " + faceup.toString());
            System.out.println("The current suit is " + sCurrentSuit);
            System.out.println("------------------------------------\nYour cards are: \n" + psuedohand);
            //System.out.println("Computers Cards: \n" + toString() + "\n"); /////For Testing Purposes (Uncomment to view change of Computer Cards);
    }

    //Handles the Game sequence if Crazy 8 is played by Player or Player Draws
    public void playerTurnMovement()
    {
    //Player Turn Movement
                if (borpus > p1.getHand().size()-1)
                    {
                        System.out.println("Your selected card is out of range.");
                        play(); 
                    }
                else if ( p1.getCard(borpus).getRank() == 8)
                    {
                        System.out.println("**You played the " + p1.getCard(borpus).toString() + " **");
                        System.out.println("Choose a suit to set!\nYour options are: 'c,d,h,s'");
                        currentSuit = input.next().charAt(0);
                        change();
                        System.out.println("You set the current suit to " + sCurrentSuit);
                        faceup = p1.getCard(borpus);
                        p1.removeCard(borpus);
                        System.out.println("\nYou went!");
                        p1Check();
                        compCheck();
                       
                    }
                else if (((p1.getCard(borpus).getSuit() == currentSuit) || (p1.getCard(borpus).getRank() == faceup.getRank())))
                    {
                        faceup = p1.getCard(borpus);
                        currentSuit();
                        p1.removeCard(borpus);
                        System.out.println("\nYou went!");
                        p1Check();
                        compCheck();

                    }
                else if ((borpus != 0) && (((p1.getCard(borpus).getSuit() != currentSuit) && (p1.getCard(borpus).getRank() != faceup.getRank()))))
                    {  
                      if (playAgain != 2) 
                        {
                           System.out.println("Your chosen card doesnt match the current suit or deck. Try Again");
                            play(); 
                        }
                    }    
    }

    public void compCrazy()
    {
        // Computer using "Crazy 8" and changing Suit
                    if (temp.getRank() == 8)
                    {
                        System.out.println("The Computer used card: " + temp);
                        
                        if (currentSuit == 'd')
                        {
                            currentSuit = 's';
                        }
                        else if (currentSuit == 's')
                        {
                            currentSuit = 'h';
                        }
                        else if (currentSuit == 'h')
                        {
                            currentSuit = 'c';
                        }
                        else if (currentSuit == 'c')
                        {
                            currentSuit = 'd';
                        }

                        change();
                        
                        System.out.println("The Computer sets suit to: " + sCurrentSuit);
                    }
    }

}

/*
    //Method used to test Cards,Decks,Indexes, and toString Methods
    public void test()
    {
        System.out.println("--------------------------------------------------------------- \n TEST");
        //System.out.println("Hand to String");
        //System.out.println(p1.handToString());
        //System.out.println("First card of Hand");
        //System.out.println(p1.getCard(0));
        //System.out.println("Comphand First Caed");
        //System.out.println(compHand.get(0));
        //System.out.println("Comphand first card suit");
        //System.out.println(compHand.get(0).getSuit());
        //System.out.println("Players chosen hand suit");
        //System.out.println(p1.getCard(borpus).getSuit());
        //System.out.println("Last Card in P1");
        //System.out.println(p1.getCard(p1.getHand().size() -1).getSuit());
        //System.out.println("Joker Card End Game Card");
        //System.out.println(joker.getSuit());
        //System.out.println("Last card in CompHand");
        //System.out.println((compHand.get(compHand.size()-1)).getSuit());
        //System.out.println("Computer's Hand");
        //System.out.println(compHand.toString());
        //System.out.println("TEST\n---------------------------------------------------------------\n");
        
        //Printing/Testing of Main Logic Boolean Parameters
                //System.out.println(compHand.get(i).getSuit());
                //System.out.println(currentSuit);
                //System.out.println(compHand.get(i).getRank());
                //System.out.println(faceup.getRank());

    }
*/
