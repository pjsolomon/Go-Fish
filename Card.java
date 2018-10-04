//Import Statements
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

//Create an Enum to Represent a Card's Suit
enum Suit {SPADES, CLUBS, DIAMONDS, HEARTS}

class Card
{
    //When Initalized To False, FileWrite Overwrites Existing File
    public static boolean Append = false;

    //Card Objects Have a Value and a Suit
    private int value;
    private Suit suit;

    //Inialize a Card Object with Specified Values
    public Card(Suit suit, int value)
    {
        setSuit(suit);
        setValue(value);
    }

    //Returns a Card's Suit
    Suit getSuit() { return this.suit; }

    //Returns a Card's Value
    int getValue() { return this.value; }

/*
 * isFace Takes the Value of a Card and
 * Converts it From Int to String for Face Cards
 */

    public static String isFace(int value)
    {
        //Convert Integer Value into a String
        String faceCard = Integer.toString(value);

        //If Value is a Face Card, Return Its Name Instead
        switch (value)
        {
            case 1 :
                faceCard = "Ace";
                return faceCard;

            case 11 :
                faceCard = "Jack";
                return faceCard;

            case 12 :
                faceCard = "Queen";
                return faceCard;

            case 13 :
                faceCard = "King";
                return faceCard;
        }
        return faceCard;
    }

    //Set Suit to Specified Suit
    void setSuit(Suit suit) { this.suit = suit; }

    //Set Value to Specified Value
    void setValue(int value) { this.value = value; }

    //Print the Full Details of a Card
    void printCard()
    {
      System.out.println(isFace(value) + " of " + suit);
      System.out.println("");
    }


    /* Writing to a File Logic From https://www.baeldung.com/java-write-to-file */
    //Function That Writes a Card Information to the Output File
    public static void writeCardToFile(String s) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", Append));
        writer.write(s);
        writer.newLine();
        writer.close();
        Append = true;
    }
}
