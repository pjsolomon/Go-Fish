import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

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
  public static void populateHand(Player P)
  {
    Card C;
    int limit = 0;
    while(Deck.cSize() > 0 & limit < 7)
    {
      C = Deck.draw();
      P.addToHand(C, false);
      limit++;
    }
  }

  //Go Fish is Played Within the Main Function
  public static void main(String[] args)
  {

    //Clear the User's Screen
    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    /*
    Bob must hand over all cards of that rank if possible.
    If he has none, Bob tells Alice to "go fish" (or just simply "fish"),
    and Alice draws a card from the pool and places it in her own hand.
    Then it is the next player's turn – unless the card Alice drew is the card she asked for,
    in which case she shows it to the other players, and she gets another turn.
    When any player at any time has all four cards of one face value, it forms a book,
    and the cards must be placed face up in front of that player.
    */

    System.out.println("-=-=-=-=-=-=-=-=-=-= Welcome To Go Fish! =-=-=-=-=-=-=-=-=-=-");
    System.out.println("\nHere's How To Play...\n");
    System.out.println("- The Game Consists of 2 Players: You and the Computer");
    System.out.println("- Each Player Begin with 7 Cards in Their Hand");
    System.out.println("- Players Take Turns Asking if Their Opponent Has a Particular Rank in Their Hand");
    System.out.println("\t- Players Can Only Ask for Ranks in Their Own Hand");
    System.out.println("- For Example, You Could Ask the Computer 'Do You Have Any 3s?'");
    System.out.println("- The Computer Must Hand Over All Cards of that Rank, If Possible");
    System.out.println("\t- In Which Case, You Get Another Turn");
    System.out.println("- If the Computer Has No Such Cards, You Must 'Go Fish'");
    System.out.println("\t- You'll Draw a Card From the Deck and Add it to Your Hand");
    System.out.println("\t- If the Card You Draw is of the Rank You Asked For, You'll Go Again");
    System.out.println("- After You Go Fish, It Becomes the Computer's Turn");
    System.out.println("");
    System.out.println("- At Any Time, If a Player has All 4 Cards of a Rank in Their Hand, It Forms a Book");
    System.out.println("\t- That Player Takes those 4 Cards Out of Their Hand and Sets them Aside");
    System.out.println("- Play Proceeds Until All Cards are in Books");
    System.out.println("- At That Point, Whichever Player has the Most Books is the Winner");
    System.out.println("");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

    System.out.println("");
    System.out.println("");

    System.out.println("The Computer has Two Memory Modes: Normal and Smart");
    System.out.println("(1) Normal: The Computer Has No Memory of Your Guesses");
    System.out.println("(2)  Smart: The Computer Has Perfect Memory of Your Guesses");

    //Create a Scanner to Read User's Input
    Scanner Reader = new Scanner(System.in);
    String Input;
    //Ensure that User's Input is Either 1 or 2
    int Intelligence;
    System.out.println("Which Computer Would You Like to Face? (Enter 1 or 2)");
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
    System.out.println("Also, Sometimes the Computer Will Lie to You. You Decide How Often it Lies");
    int lieFreq;
    System.out.println("Enter How Often You'd Like the Computer to Lie");
    System.out.println("For Example, Entering 50 Would Cause the Computer to Lie Half the Time");
    System.out.println("Enter 'R' if You Want a Random Percentage!");
    Random randNum = new Random();
    while(true)
    {
      try
      {
        Input = Reader.next();
        if(Input.equals("R") | Input.equals("r"))
        {
          lieFreq = randNum.nextInt(100);
          break;
        }
        lieFreq = Integer.parseInt(Input);
        if(lieFreq >= 0 & lieFreq <= 100)
        {
          break;
        }
        else { System.out.println("Input Must Be Between 0 and 100 (Or 'R' For Random)."); }
      }
      catch (Exception e)
      {
        System.out.println("Please Enter an Integer (Or 'R' For Random).");
      }
    }

    String name;
    System.out.println("");
    System.out.println("Enter Your Name:");
    name = Reader.next();
    while(name.length() > 15)
    {
      System.out.println("Sorry, Your Name Must Be 15 or Less Characters.");
      System.out.println("Enter Your Name:");
      name = Reader.next();
    }
    System.out.println("");

    //Initialize Each Player
    Player Player1 = new Player(name);
    Player Player2 = new ComputerPlayer(Intelligence,lieFreq);

    //Add Each Player to the Players Array
    Players = new Player[2];
    Players[0] = Player1;
    Players[1] = Player2;

    //Initialize the Deck and Populate it with 52 Cards
    Deck = new CardCollection();
    Deck.populateDeck();

    //Populate Each Player's Hands
    populateHand(Players[0]);
    Players[0].checkSets();
    populateHand(Players[1]);
    Players[1].checkSets();

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
          System.out.println("-=-=-=-=-= " + Players[Turn].getName() + "'s Turn =-=-=-=-=-\n");
          if(Players[0].isEmpty() & Players[1].isEmpty() & Deck.cSize()==0)
          {
            break;
          }

          if(Players[Turn].isEmpty())
          {
            populateHand(Players[Turn]);
            Players[Turn].checkSets();
            System.out.println("- " + Players[Turn].getName() + " Replenished Their Hand From the Deck -");
          }

          if(Players[otherPlayer()].isEmpty())
          {
            populateHand(Players[Turn]);
            Players[otherPlayer()].checkSets();
            System.out.println("- " + Players[otherPlayer()].getName() + " Replenished Their Hand From the Deck -");
          }

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
              Players[Turn].addToHand(removedCards.get(i), false);
            }

            Players[Turn].checkSets();

            System.out.println("Good guess! Go again!\n");
            //Current Player Gets Another Turn
            goAgain = true;
          }
          //Declared Card is Not in Non-Current Player's Hand
          else
          {
            if(Deck.cSize() > 0)
            {
              //Current Player Draws from Decks
              System.out.println("Go Fish!\n");
              drawnCard = Deck.draw();
              Players[Turn].addToHand(drawnCard, Players[Turn].isHuman());

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
      }
      switchTurn();
    }
    if(isGameOver())
    {
      int humSets = Players[0].getSets();
      int comSets = Players[1].getSets();
      if(humSets > comSets)
      {
        System.out.println(Players[0].getName() + " Wins With " + humSets + " Sets!");
      }
      else
      {
        System.out.println("Computer Wins With " + comSets + " Books!");
      }
    }
    else
    {
      System.out.println("Thanks For Playing!\n");
    }
  }
}
