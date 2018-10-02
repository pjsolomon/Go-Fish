import java.util.Scanner;
import java.util.ArrayList;

public class GoFish
{
  //FIELDS
  public static Player[] Players;
  public static CardCollection Deck;
  public static int Turn;

  //A Function that Switches the Current Player at the End of a Turn
  public static void switchTurn()
  {
    if(Turn == 1)
    {
      Turn = 0;
    }
    else
    {
      Turn = 1;
    }
  }

  //A Function that Returns the Index of the Non-Current Player
  public static int otherPlayer()
  {
    if(Turn == 1)
    {
      return 0;
    }
    else
    {
      return 1;
    }
  }

  public static Boolean isGameOver()
  {
    //Go Fish is Over when All Cards are in Completed Sets of 4 (13 Total Sets)
    if(Players[0].getSets() + Players[1].getSets() == 13)
    {
      return true;
    }
    else
    {
      return false;
    }

  }

  //Add 7 Cards from the Deck to Each Player's Hand
  public static void populateHands()
  {
    Card C;

    for(int i = 0; i < 7; i++)
    {
      C = Deck.draw();
      Players[0].addToHand(C);
      C = Deck.draw();
      Players[1].addToHand(C);
    }
  }

  //Go Fish is Played Within the Main Function
  public static void main(String[] args)
  {

    //Clear the User's Screen
    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    //Create a Scanner to Read User's Input
    Scanner Reader = new Scanner(System.in);
    String Input;
    //Ensure that User's Input is Either 1 or 2
    int Intelligence;
    System.out.println("Select Computer Intellegence");
    while(true)
    {
      try
      {
        Input = Reader.next();
        Intelligence = Integer.parseInt(Input);
        if(Intelligence == 1 | Intelligence == 2)
        {
          break;
        }
        else { System.out.println("Please Select Either Option 1 or 2."); }
      }
      catch (Exception e)
      {
        System.out.println("Please Enter an Integer.");
      }
    }
    //Ensure that User's Input is an Integer Between 0 and 100
    int lieFreq;
    System.out.println("Enter Computer Lie Frequency");
    while(true)
    {
      try
      {
        Input = Reader.next();
        lieFreq = Integer.parseInt(Input);
        if(lieFreq >= 0 & lieFreq <= 100)
        {
          break;
        }
        else { System.out.println("Input Must Be Between 0 and 100."); }
      }
      catch (Exception e)
      {
        System.out.println("Please Enter an Integer.");
      }
    }

    //Initialize Each Player
    Player Player1 = new Player();
    Player Player2 = new ComputerPlayer(Intelligence,lieFreq);

    //Add Each Player to the Players Array
    Players = new Player[2];
    Players[0] = Player1;
    Players[1] = Player2;

    //Initialize the Deck and Populate it with 52 Cards
    Deck = new CardCollection();
    Deck.populateDeck();

    //Populate Each Player's Hands
    populateHands();

    //Initialize Some Variables to Use Later
    ArrayList<Card> removedCards = new ArrayList<Card>();
    int requestedValue;
    Card drawnCard;
    Boolean goAgain;

    /*
     * Anatomy of a Turn in Go Fish:
     *
     * - Turn Begins
     * - Current Player is Shown Their Hand
     * - Current Player is Asked to Declare a Card
     *    - Card must be in Player's Hand
     * - Opposing Player is Asked if Their Hand Contains that Card
     *    - Opposing Player Lies or Tells the Truth
     * - Opposing Player's Does or Doesn't Contain Card
     *    - If Yes, Current Player Recieves and Goes Again
     *    - If No, Current Player 'Goes Fish' from Deck
     *        - If Player Draws Declared Card, He Goes Again
     *        - Otherwise, Current Player's Turn Ends
     */

     //Continue Playing as Long as There Are Cards Left in the Deck
     Game:
     while(!isGameOver())
     {
       goAgain = true;

       while(goAgain)
       {
         //Display the Cards in the Current Player's Hand
          Players[Turn].showHand();

          //Current Player must Declare Value
          requestedValue = Players[Turn].requestCard();
          if(requestedValue == -1)
          {
            break Game;
          }
          //Check Whether Non-Current Player has Value in Their Hand
          if(Players[otherPlayer()].checkHand(requestedValue))
          {
            //Remove Appropriate Cards from Non-Current Player's Hand
            removedCards = Players[otherPlayer()].popFromHand(requestedValue);
            //Add Those Cards to Current Player's Hand
            for(int i = 0; i < removedCards.size(); i++)
            {
              Players[Turn].addToHand(removedCards.get(i));
            }

            Players[Turn].checkSets();

            System.out.println("Good guess! Go again!\n");
            //Current Player Gets Another Turn
            goAgain = true;
          }
          //Declared Card is Not in Non-Current Player's Hand
          else
          {
            System.out.println("Go Fish!");

            //Current Player Draws from Decks
            drawnCard = Deck.draw();
            Players[Turn].addToHand(drawnCard);
            System.out.print("You drew a ");
            drawnCard.printCard();

            //If Value of Drawn Card Matches Declared Value, Player Goes Again
            if(drawnCard.getValue() == requestedValue)
            {
              System.out.print("It's a match! ");
              Players[Turn].checkSets();
              goAgain = true;
              System.out.println("Go Again!\n");
            }
            //Otherwise, Their Turn Ends
            else
            {
              Players[Turn].checkSets();
              goAgain = false;
            }
          }
      }
      switchTurn();
    }
    if(isGameOver())
    {
      //Display Some Output At The End of The Game
    }
    else
    {
      System.out.println("Thanks For Playing!\n");
    }
  }
}
