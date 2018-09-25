import java.util.Scanner;

public class GoFish
{
  //FIELDS
  public static Player[] players;
  public static Collection deck;

  public static void switchTurn()
  {
    //switch turns somehow
  }

  public static Boolean isGameOver()
  {
    //game is over when all cards are in completed sets of 4
    //thus, game is over when there are 52/4=13 sets
    if(//humanPlayer.sets + computerPlayer.sets == 13)
    {
      return true;
    }
    else
    {
      return false;
    }

  }

  public static void main(String[] args)
  {

    /*
     * Anatomy of a Turn in Go Fish:
     *
     * - turn begins
     * - current player is shown their hand
     * - current player is asked to declare a card
     *    - card must be in player's hand
     * - opposing player is asked if their hand contains that card
     *    - opposing player tells the truth or lies
     * - opposing player either has card or doesn't
     *    - if yes, current player matches and goes again
     *    - if no, current player draws from deck
     *        - if drawn card creates a match, current player goes again
     *        - otherwsise, current player's turn ends
     */

     //initialize scanner object
     Scanner Reader = new Scanner(System.in);

     //initalize boolean to determine if current player get an additional turns
     Boolean playAgain;


     while(!isGameOver())
     {
       playAgain = true;

       while(playAgain)
       {
          //if current player is human, call Player.showHand()
          //if current player is human, ask for a card, accept input
          //run checkHand() on non-current player
          if(//checkHand==true)
          {
            //switch card from hands
            playAgain = true;
          }
          else
          {
            //current player draws from deck
            if(//drawn card matches card asked for)
            {
              //check for sets of 4
              playAgain = true;
            }
            else
            {
              //check for sets of 4
              playAgain = false;
            }
          }
      }

      switchTurns();
    }

    //Display some output at the end of the game
  }
