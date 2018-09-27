import java.util.Scanner;

public class GoFish
{
  //FIELDS
  public static Player[] Players;
  public static CardSet Deck;
  public static int Turn;

  public static void switchTurn()
  {
    if(Turn == 1)
    {
      Turn == 0;
    }
    else
    {
      Turn == 1;
    }
  }

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
    //game is over when all cards are in completed sets of 4
    //thus, game is over when there are 52/4=13 sets

    /***** NEED FUNCTION TO ACCESS PLAYERS # OF SETS *****/

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

    Player Player1 = new Player();
    Player Player2 = new Player();

    Players = new Player[2];
    Player[0] = Player1;
    Player[1] = Player2;

    //MUST INITALIZE DECK AND PLAYERS' HANDS

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
     Boolean goAgain;


     while(!isGameOver())
     {
       goAgain = true;

       while(goAgain)
       {
          //if current player is human, call Player.showHand()
          /***** NEED FUNCTION TO DETERMINE IF PLAYER IS HUMAN *****/
          Players[Turn].showHand();


          //if current player is human, ask for a card, accept input
          /***** NEED FUNCTION TO TURN INPUT INTO CARD OBJECT *****/



          //run checkHand() on non-current player
          /***** NEED FUNCTION TO CHECK COLLECTION FOR CERTAIN CARDS *****/
          if(//checkHand==true)
          {
            //switch card from hands
            /***** NEED FUNCTION TO REMOVE CARD FROM A COLLECTION *****/
            /***** NEED FUNCTION TO ADD CARD TO A COLLECTION *****/
            goAgain = true;
          }
          else
          {
            //current player draws from deck
            Card drawnCard = Deck.draw();

            /***** NEED FUNCTION TO CHECK CARD EQUIVALENCE *****/
            if(//drawn card matches card asked for)
            {
              /***** NEED FUNCTION TO CHECK FOR SETS WITHIN A COLLECTION *****/
              //check for sets of 4
              goAgain = true;
            }
            else
            {
              //check for sets of 4
              /***** NEED FUNCTION TO CHECK FOR SETS WITHIN A COLLECTION *****/
              goAgain = false;
            }
          }
      }

      switchTurns();
    }

    //Display some output at the end of the game
  }
