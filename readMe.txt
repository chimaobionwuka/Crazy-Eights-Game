readMe.txt for Programming Project 4
Author: Chimaobi Onwuka

Welcome to my Read-Me, and inside look on what makes my Programming Project Mine.

For Card.java

Card.java which is the backbone of the Deck, and subsequently the Game class allows you create a card out of two instance variables, suit and rank.
In the constructor those two variables are built as formal parameters of the Card object. Then there are two main accesor methods of the class which allow one to return the suit and 
rank of the particular Card. Then Finally there is a to String Mwthod which turns takes the rank or suit which are just letters and number and turns 
them into more readable and accurate cards depending on the specifc card. For example, (s,11) becomes Jack of Spades and so on. This helps particularly when
displaying cards to the player during gameplay.

For Deck.java

Deck.java is like the next step of evolution in making a fully functional deck out of the Card object. Intiallity intitialized are array called "deck", an integer that represents the card of the deck that is to be delt.
Then there are 2 arrays that hold all the possible letters representing the suits and the possible numbers representing the ranks. And fiannly a joker card, which represents the card
passed when the end of the deck is rached. So in the constructor for deck, the deck array is initilized with a length of 52 representing the 52 final cards in a deck.
Then a nested for loop is used to go through every possiblity of suit and rank to create a new card in the deck deck. Count is kept to determine in which place each card would be placed.
Then a deal method is created which first checks if the canDeal() method is true and if so it then returns "deals" the next neccesary card and iterates the "top" of the deck to the next card.
And if the canDeal() method determiens no more cards from the deck can be delt then this method returns the joker card which would initiate the gameOver sequence if the all 52 cards end up being used.
Then there is shuffle method which restarts the top variable at the top of the deck and creates a copy of deck as an ArrayList, shuffles it with the shuffle method and then makes it back into an Array which is assigned back to deck.
Finally there is a toString Method which takes every index in deck and makes into a readable string and when all of them are concatenated it is "returned".

For Player.java
Player.java for the most part handles the deck of player, in all regards, and the player input in term of choosing a card to place down or draw.
There is a method to add a particular card to to the players deck which is useful for dealing, a complimentary removal card which does quite the opposite but insteead removes the card based of its index. 
Then there is  getHand() mehtod which returns the full contents of the deck ArrayList of the player. Then there is the author-made getCard method which allows for
easy access to a parrtucar card from the players hand mostly to prevent errors when typing in logic. Then there is a clear() method which helps prepare the deck for anything
games played after the initial bout. Then there is a handToString() for readablity. Finally rhere is a playerTurn() method which allows the player to input a 0 to draw, and 
any other integer number to select the index of the card they would like to "play". Then this integer value is returned.

For Deck.java

//Extra Instance Variables
A behemoth of a class Deck.java  is, a load of Instance Variables are initialized in the first few lines sich as psuedohand which is just p1.handToString() as variable to decrease erros in typing.
Then there are vairablrs like sCurrentSuit which is just like toString but for suites. The cont variable determines if the play() method is allowed to continue or noow, borpus is just corpus (player input) adjusted to account for indexes being length-1.
playAgain variable determines if the game is stopped after asking the player or continued. Joker needed to be reinitilized in this class so, the check methods could compare the joker card passed in through cards.deal() againts the real thing.
And finally go is what is returned to the CrazyEights.java to determine if it will keepPlaying or not.

//The Game constructor 
The Game Constructor handles the creation of the scanner to take player's input to play again. the initiliazation of the cards deck, shuffling the cards.
and the dealing of 7 cards (using a for loop) to both the player and Computer deck. It also gives the faceup variable a card from th edeck and sets the currentSuit to the suit of the faceup.
Finally, it prints out the intro, how to play, and the rules of the game.

//Play
So the play() method hands/holds alot of the methods that make the game run, as well as returns a boolean value at the end of the first round to see if the game should keep on going.
It starts of by first ensuring that the playAgain() is not equal to 2 which basically means that the player had not already said no continuing the game. It shows up throughout the game, so I will just omit its explanation\
from here  on out. The first method invoked is userDisplay which basicacally informs the user of what the Up Card is, the Current Suit, Player Cards, and Computer Cards (for development and bugfixing).
Then an if loop ensures that the game should still be going through checking the size of both the the p1 hand and comp hand. Then corpusDeal() method is invoked which recieves the players input from the player class and determines if the game should deal them a card
or convert the int value to corpus which is the correct index in the player's ArrayList. Then playerTurnMvement() is invoked which controls what the player actually does if it chose a card.
In the case that the card that was played was a CRAZY 8, then an if statement is used to declare an 8 was played, get the players choice of suit, remove the card from the deck and then invokes the check methods (more on them later).
In this method it also alternatively checks if either the suit or rank of the card chosen matches the faceup card so it can place it. In this case it would remove make that the new faceup, remove that card from the deck, then invoke the check methods. Finally, if the card choise does not match
it will tell the player to try again until it does or they draw.

//ComputerTurn
While still a method in play, computerTurn handles all the logic and decision making of the computer plaer. A card is created initially to hold the chosen return card since this method requries a return, and its index is saved in cardSelect. 
The leave boolean is just a pawn in the escape sequence of this rather elaborate method. A for loop is used to check every single card in the deck of the computer to see 
if any of them match the suit, rank of the card, or are CRAZY 8 card. In the case that it is a crazy 8, the computer does the compCrazy() method which checks if the temp card rank is an 8 then changes the new suit to a random suit based on the current suit and tells displays this. 
Then it alters the faceup card to be the card it chose, and removes it from the deck. 
Essentially"playing" the card. Finally the escape sequence is initiated by setting leave to true and hitting return temp statement. Thenstatement outside the for loop then asks, (in the case that there was no card match), if the nothing matched and if so then invokes the computerTurnElse method.
This method facilizted the adding a card to the computers deck and then uses recursion and calls on the computerTurn method to check once again if any of the cards in this newly minted deck have a matching spirit.

//p1Check and compCheck
So the checks which are virtually identical, and are sprinkled around all over the code basically check the current conditions, computer and player deck sizes for basically size 0, and if any of the decks hold the END CARD which is basically just a joker passe into a deck
once the 52 card has been delt, and in this case the checks see which deck (the computer or the player) had the least card and then it activates the associated gameOver sequence. 
theGameOver sequence prints the winner depending on who won and then asks the player if they want to play again. It then sets this input to the variable playAgain and runs the associated method playAgain()

//playAgain()
The playAagain() method is simply is just a simple if else statement that checks if the player chose to play again (1) or not (2). In the case that it does the go variable
which is returned by play() is set to true which allows keepPlaying() in the CrazyEights.java to play again and sets cont = true which allows the sequence in play() to keep on cycling between 
player and computer turns.
Howvever, if its false, it will set cont to false and go to false and print out the end statement, which allows for an end to the play() sequence and associated loops entirely,
and subsequently an end to the game.

//clearRefresh
Finally there is the clearRefresh() method which is invoked in the case that playAgain = 1, and it clears the computer and player decks entirely, shuffles the cards again witht the shuffles method, resets the faceup to a new 
card within this shuffled method, and redeals cards to both decks.

I did a few bug fixes and implemented a correct working computer crazy 8 function. I was unable to fix the random index error that occured sometimes after ending the game, even after adding 
certain safeguards namely (if playAgain() =! 2) everywhere which was supposed to stop all print statements and actions if the player chose to end the game. As of now this is the only bug that I am aware of.
