import java.util.ArrayList;
import java.util.Iterator;

public class CardCollection
{

  ArrayList<Card> Contents = new ArrayList<Card>();

  //Constructor, Initalizes a List of Card
  public CardCollection()
  {
    Contents = new ArrayList();
  }
  //Adds Card to the List
  public void addCard(Card C)
  {
    Contents.add(C);
  }

  //Removes a Card from the List
  public ArrayList removeValues(int V)
  {
    ArrayList<Card> removedCards = new ArrayList();
    Iterator itr = Contents.iterator();
    while(itr.hasNext())
    {
      Card C = (Card)itr.next();

      if(C.getValue() == V)
      {
        removedCards.add(C);
        itr.remove();
      }
    }
    return removedCards;
  }

  //Check if List Contains a Card
  public Boolean containsValue(int V)
  {
    for(int i = 0; i < Contents.size(); i++)
    {
      if(Contents.get(i).getValue() == V)
      {
        return true;
      }
    }

    return false;
  }

  public void printCards()
  {
    for(int i = 0; i < Contents.size(); i++)
    {
      Card C = Contents.get(i);
      System.out.print(C.getValue() + C.getSuit().toString() + " ");
    }

    System.out.println("");
  }

  public int cSize()
  {
    return Contents.size();
  }

  //Pops a Card from the List
  public Card draw()
  {
    Card C = Contents.get(0);
    Contents.remove(0);
    return C;
  }

  //Check for Sets of 4 within the List
  /* An Absolute Mess Right Now, Definitely can be Improved */
  public void checkSets()
  {

  }

  //This method creates a clean deck of 52 cards
  public ArrayList<Card> initializeDeck() {

    //Plebian Cards
    for (int i = 2; i < 11; i++) {
      Card spades = new Card(Suit.SPADE, i);
      Contents.add(spades);
      Card hearts = new Card(Suit.HEART, i);
      Contents.add(hearts);
      Card diamonds = new Card(Suit.DIAMOND, i);
      Contents.add(diamonds);
      Card clubs = new Card(Suit.CLUB, i);
      Contents.add(clubs);
    }
    //Face Cards
    //Jack = 11
    Card jackSpade = new Card(Suit.SPADE, 11);
    Contents.add(jackSpade);
    Card jackHeart = new Card(Suit.HEART, 11);
    Contents.add(jackHeart);
    Card jackDiamond = new Card(Suit.DIAMOND, 11);
    Contents.add(jackDiamond);
    Card jackClub = new Card(Suit.CLUB, 11);
    Contents.add(jackClub);

    //Queen = 12
    Card queenSpade = new Card(Suit.SPADE, 12);
    Contents.add(queenSpade);
    Card queenHeart = new Card(Suit.HEART, 12);
    Contents.add(queenHeart);
    Card queenDiamond = new Card(Suit.DIAMOND, 12);
    Contents.add(queenDiamond);
    Card queenClub = new Card(Suit.CLUB, 12);
    Contents.add(queenClub);

    //King = 13
    Card kingSpade = new Card(Suit.SPADE, 13);
    Contents.add(kingSpade);
    Card kingHeart = new Card(Suit.HEART, 13);
    Contents.add(kingHeart);
    Card kingDiamond = new Card(Suit.DIAMOND, 13);
    Contents.add(kingDiamond);
    Card kingClub = new Card(Suit.CLUB, 13);
    Contents.add(kingClub);

    //Ace = 14
    Card aceSpade = new Card(Suit.SPADE, 14);
    Contents.add(aceSpade);
    Card aceHeart = new Card(Suit.HEART, 14);
    Contents.add(aceHeart);
    Card aceDiamond = new Card(Suit.DIAMOND, 14);
    Contents.add(aceDiamond);
    Card aceClub = new Card(Suit.CLUB, 14);
    Contents.add(aceClub);


    return Contents;
  }
}
