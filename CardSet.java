import java.util;

public class CardSet
{

  public ArrayList<Card> Contents;

  //Constructor, Initalizes a List of Card
  public CardSet(ArrayList<Card> Cs)
  {
    this.Contenst = Cs;
  }

  //Adds Card to the List
  public void addCard(Card C)
  {
    Contents.add(C);
  }

  //Removes a Card from the List
  public void removeCard(Card C)
  {
    Contents.remove(C);
  }

  //Check if List Contains a Card
  public Boolean containsCard(Card C)
  {
    return Contents.contains(C);
  }

  public void printSet()
  {
    for(int i = 0; i < Contents.size(); i++)
    {
      Card C = Contents[i];
      System.out.print(C.getValue() + C.getSuit() + " ");
    }

    System.out.println("");
  }

  //Pops a Card from the List
  public Card draw()
  {
    Card C = Contents.get(0);
    Contents.remove(0);
    return C;
  }

  //Check for Sets of 4 within the List
  public void checkSets()
  {
    //TBD
  }
}
