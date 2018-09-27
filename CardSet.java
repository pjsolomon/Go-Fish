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
  /* An Absolute Mess Right Now, Definitely can be Improved */
  public void checkSets()
  {
    int v;
    Dictionary Dict = new Dictionary();
    ArrayList<int> Keys = new ArrayList();

    for(int i = 0; i < Contents.size(); i++)
    {
      if(Dict.get(Contents[i].getValue) == null)
      {
        Dict.put(Contents[i].getValue(), 1);
        Keys.add(Contents[i].getValue());
      }
      else
      {
        v = Dict.get(Contents[i].getValue);
        Dict.put(Contents[i].getValue(), v+1);
      }
    }

    for(int j = 0; j < Keys.size(); j++)
    {
      if(Dict.get(Keys[j]) == 4)
      {
        for(int k = 0; k < Contents<size(); k++)
        {
          if(Contents[k].getValue == Keys[j])
          {
            Contents.remove(k);
            k = 0;
          }
        }
      }
    }
  }
}
