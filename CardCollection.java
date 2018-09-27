import java.util.ArrayList;
import java.util.Iterator;

public class CardCollection
{

  public ArrayList<Card> Contents;

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
  public CardCollection removeValues(int V)
  {
    CardCollection removedCards = new CardCollection();
    Iterator itr = Contents.Iterator();
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
      if(Contents[i].getValue() == V)
      {
        return true;
      }
    }

    return false
  }

  public void printCards()
  {
    for(int i = 0; i < Contents.size(); i++)
    {
      Card C = Contents[i];
      System.out.print(C.getValue() + C.getSuit() + " ");
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
}
