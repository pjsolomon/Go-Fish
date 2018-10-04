//Import Statements
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;

public class GoFish
{
  //FIELDS
  //WILLIAM SLOCUM
  public static Player[] Players;
  public static CardCollection Deck;
  public static int Turn;

  //A Function that Switches the Current Player at the End of a Turn
  //WILLIAM SLOCUM
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
  //WILLIAM SLOCUM
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

  //WILLIAM SLOCUM
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
  //WILLIAM SLOCUM
  public static void populateHand(Player P)
  {
    Card C;
    int limit = 0;
    //Add Up to 7 Cards to a Hand as Long as Deck Still Has Cards
    while(Deck.cSize() > 0 & limit < 7)
    {
      C = Deck.draw();
      P.addToHand(C, false);
      limit++;
    }
  }

  //Go Fish is Played Within the Main Function
  //WILLIAM SLOCUM
  public static void main(String[] args)
  {

    //Clear the User's Screen
    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

    //Print the Instructions of the Game
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

    //Print Some Options for the User to Select
    System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-= Options =-=-=-=-=-=-=-=-=-=-=-=-=-\n");

    //Create a Scanner to Read User's Input
    Scanner Reader = new Scanner(System.in);
    String Input;

    System.out.println("-------------------------");
    System.out.println("Computer Intelligence");
    System.out.println("-------------------------");
    System.out.println("(1) Normal: Computer Has No Memory of Your Guesses");
    System.out.println("(2)  Smart: Computer Has Perfect Memory of Your Guesses");


    //Ensure that User's Input is Either 1 or 2
    int Intelligence;
    System.out.print("\nSelect Computer Intelligence: ");
    while(true)
    {
      //If User Enters a String, Catch it and Ask Again
      try
      {
        Input = Reader.next();
        Intelligence = Integer.parseInt(Input);
        if(Intelligence == 1 | Intelligence == 2)
        {
          break;
        }
        else { System.out.print("Enter Either '1' or '2': "); }
      }
      catch (Exception e)
      {
        System.out.print("Enter an Integer: ");
      }
    }

    System.out.println("");
    System.out.println("-------------------------");
    System.out.println("Computer Lie Frequency");
    System.out.println("-------------------------");
    System.out.print("Enter A Percentage (Or R For Random): ");
    int lieFreq;
    Random randNum = new Random();
    //Continue Loop Until Acceptable Input Breaks Out
    while(true)
    {
      try
      {
        Input = Reader.next();
        //Only String Allowed is R or r
        if(Input.equals("R") | Input.equals("r"))
        {
          lieFreq = randNum.nextInt(100);
          break;
        }
        //Ensure that User's Input is an Integer Between 0 and 100
        lieFreq = Integer.parseInt(Input);
        if(lieFreq >= 0 & lieFreq <= 100)
        {
          break;
        }
        else { System.out.print("Input Must Be Between 0 and 100: "); }
      }
      catch (Exception e)
      {
        System.out.print("Enter an Integer (Or 'R' For Random): ");
      }
    }

    System.out.println("\n-------------------------");
    System.out.println("Player Name");
    System.out.println("-------------------------");
    System.out.print("Enter Your Name: ");
    String name;
    name = Reader.next();
    //While Name is Longer Than 15 Characters, Ask Again
    while(name.length() > 15)
    {
      System.out.print("Input Must Be 15 Characters or Less: ");
      name = Reader.next();
    }

    System.out.println("");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-= Let's Play! =-=-=-=-=-=-=-=-=-=-=-=-=-");
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

    //Populate Each Player's Hands (Check for Sets Right Away!)
    populateHand(Players[0]);
    Players[0].checkSets();
    populateHand(Players[1]);
    Players[1].checkSets();

    //Initialize Some Variables to Use Later
    ArrayList<Card> removedCards = new ArrayList<Card>();
    int requestedValue;
    Card drawnCard;
    Boolean goAgain;
    Boolean madeSet;

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
       //goAgain = true Allows Code to Reach Inner While Loop
       goAgain = true;

       while(goAgain)
       {
          //If No Cards Are in Hand or Deck, Break Out of Loop, Game Should End
          if(Players[0].isEmpty() & Players[1].isEmpty() & Deck.cSize()==0)
          {
            break;
          }

          //Repopulate Current Player's Hand If It Is Empty
          if(Players[Turn].isEmpty())
          {
            madeSet = true;
            //Make Sure New Hand Doesn't Contain A Set
            while(madeSet)
            {
              populateHand(Players[Turn]);
              madeSet = Players[Turn].checkSets();
              System.out.println("- " + Players[Turn].getName() + " Replenished Their Hand From the Deck -\n");
            }
          }
          //Repopulate Non-Current Player's Hand If It Is Empty As Well
          if(Players[otherPlayer()].isEmpty())
          {
            madeSet = true;
            //Make Sure New Hand Doesn't Contain A Set
            while(madeSet)
            {
              populateHand(Players[otherPlayer()]);
              madeSet = Players[otherPlayer()].checkSets();
              System.out.println("- " + Players[otherPlayer()].getName() + " Replenished Their Hand From the Deck -");
            }
          }

          System.out.println("-=-=-=-=-= " + Players[Turn].getName() + "'s Turn =-=-=-=-=-\n");

          //Sort, Write, and Display the Cards in the Current Player's Hand
          Players[0].sortHand();
          Players[Turn].writeHandToFile();
          Players[Turn].showHand();

          //Current Player must Declare Value
          requestedValue = Players[Turn].requestCard();
          //If Player Types 'quit', requestedValue will be -1, Should End Game
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
            //Check For Sets in PLayer's Hand with New Cards
            Players[Turn].checkSets();

            System.out.println("Good Guess! Go Again!\n");
            //Current Player Gets Another Turn
            goAgain = true;
          }
          //Declared Card is Not in Non-Current Player's Hand
          else
          {
            //If Deck Isn't Empty, Player Must Draw a Card
            if(Deck.cSize() > 0)
            {
              //Current Player Draws from Deck
              System.out.println("Go Fish!\n");
              drawnCard = Deck.draw();
              //Write Draw to File
              try
              {
                Card.writeCardToFile("Go Fish! Drew A " + Card.isFace(drawnCard.getValue()));
              }
              catch (IOException e)
              {
                  e.printStackTrace();
              }
              //Add Drawn Card to Hand
              Players[Turn].addToHand(drawnCard, Players[Turn].isHuman());

              //If Value of Drawn Card Matches Declared Value, Player Goes Again
              if(drawnCard.getValue() == requestedValue)
              {
                System.out.print("It's a Match! ");
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
      //At then End of Someones Turn, Make Sure the Other Player Goes Next
      switchTurn();
    }
    //If isGameOver = true, There Must be a Winner
    if(isGameOver())
    {
      //Find Each Player's Number of Books, Decide Winner
      int humSets = Players[0].getSets();
      int comSets = Players[1].getSets();
      if(humSets > comSets)
      {
        System.out.println("-=-=-=-=-=-=-=-=-=-= Game Over! =-=-=-=-=-=-=-=-=-=-");
        System.out.println(Players[0].getName() + " Wins With " + humSets + " Sets!\n");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-");
        System.out.println("");
      }
      else
      {
        System.out.println("-=-=-=-=-=-=-=-=-=-= Game Over! =-=-=-=-=-=-=-=-=-=-");
        System.out.println("\nComputer Wins With " + comSets + " Books!\n");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-");
        System.out.println("");
      }
    }
    //If isGameOver = false, Then Player Must've Asked to Quit, There Is No Winner
    else
    {
      System.out.println("-=-=-=-=-=-=-=-=-=-= Game Over! =-=-=-=-=-=-=-=-=-=-");
      System.out.println("\nThanks For Playing!\n");
      System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-");
      System.out.println("");
    }
  }
}
