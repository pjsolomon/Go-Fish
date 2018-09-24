public class GoFish
{
  //FIELDS
  public static Player[] Players;
  public static Collection Deck;

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

  }



}
