import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class CardCollection
{

  ArrayList<Card> Contents;

  //Constructor, Initalizes CardCollection with Empty Contents
  public CardCollection()
  {
    Contents = new ArrayList<Card>();
  }
  //Adds A Card to the CardCollection
  public void addCard(Card C)
  {
    Contents.add(C);
  }

  //Removes All of a Specified Value from the CardCollection
  public ArrayList removeValues(int V)
  {
    ArrayList<Card> removedCards = new ArrayList<Card>();
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

  //Check if a CardCollection Contains a Value
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

  //Print Each Card within a CardCollection
  public void printCards()
  {
    System.out.println("Your Hand:");
    try {
        Card.writeCardToFile("\n Player's hand: ");
    }catch (IOException e) {
        e.printStackTrace();
  }
    for(int i = 0; i < Contents.size(); i++)
    {
      Card C = Contents.get(i);
      //Different Print methods for formatting purposes
      if(C.getValue() < 10) {

        System.out.println(C.isFace(C.getValue()) + "  of " + C.getSuit().toString() + " ");
          try {
              Card.writeCardToFile(C.isFace(C.getValue()) + " of " +  C.getSuit().toString());
          } catch (IOException e) {
              e.printStackTrace();
          }
      } else {
        System.out.println(C.isFace(C.getValue()) + " of " + C.getSuit().toString() + " ");
        try {
              Card.writeCardToFile(C.isFace(C.getValue()) + " of " + C.getSuit().toString());
          } catch (IOException e) {
              e.printStackTrace();
          }

      }
    }

    System.out.print("\n");
  }

  public int cSize()
  {
    return Contents.size();
  }

  //Pops a Card from CardCollection
  public Card draw()
  {
    Card C = Contents.get(0);
    Contents.remove(0);
    return C;
  }


  //Check for Sets of 4 within the CardCollection
  public int removeSets()
  {
    int setValue = 0;
    int[] V = new int[14];

    for(int i = 0; i < V.length; i++)
    {
      V[i] = 0;
    }

    for(int j = 0; j < Contents.size(); j++)
    {
      int val = Contents.get(j).getValue();
      V[val] = V[val] + 1;
    }

    Iterator itr = Contents.iterator();


    while(itr.hasNext())
    {
      Card C = (Card) itr.next();
      if(V[C.getValue()] == 4)
      {
        itr.remove();
        setValue = C.getValue();
      }
    }

    return setValue;
  }

  //Create and Add all 52 Standard Cards to the CardCollection
  public void populateDeck()
  {
    //Plebian Cards
    for (int i = 2; i < 11; i++)
    {
      Card spades = new Card(Suit.SPADES, i);
      Contents.add(spades);
      Card hearts = new Card(Suit.HEARTS, i);
      Contents.add(hearts);
      Card diamonds = new Card(Suit.DIAMONDS, i);
      Contents.add(diamonds);
      Card clubs = new Card(Suit.CLUBS, i);
      Contents.add(clubs);
    }
    //Face Cards
    //Jack = 11
    Card jackSpade = new Card(Suit.SPADES, 11);
    Contents.add(jackSpade);
    Card jackHeart = new Card(Suit.HEARTS, 11);
    Contents.add(jackHeart);
    Card jackDiamond = new Card(Suit.DIAMONDS, 11);
    Contents.add(jackDiamond);
    Card jackClub = new Card(Suit.CLUBS, 11);
    Contents.add(jackClub);

    //Queen = 12
    Card queenSpade = new Card(Suit.SPADES, 12);
    Contents.add(queenSpade);
    Card queenHeart = new Card(Suit.HEARTS, 12);
    Contents.add(queenHeart);
    Card queenDiamond = new Card(Suit.DIAMONDS, 12);
    Contents.add(queenDiamond);
    Card queenClub = new Card(Suit.CLUBS, 12);
    Contents.add(queenClub);

    //King = 13
    Card kingSpade = new Card(Suit.SPADES, 13);
    Contents.add(kingSpade);
    Card kingHeart = new Card(Suit.HEARTS, 13);
    Contents.add(kingHeart);
    Card kingDiamond = new Card(Suit.DIAMONDS, 13);
    Contents.add(kingDiamond);
    Card kingClub = new Card(Suit.CLUBS, 13);
    Contents.add(kingClub);

    //Ace = 1
    Card aceSpade = new Card(Suit.SPADES, 1);
    Contents.add(aceSpade);
    Card aceHeart = new Card(Suit.HEARTS, 1);
    Contents.add(aceHeart);
    Card aceDiamond = new Card(Suit.DIAMONDS, 1);
    Contents.add(aceDiamond);
    Card aceClub = new Card(Suit.CLUBS, 1);
    Contents.add(aceClub);

    Collections.shuffle(Contents);
  }
}
