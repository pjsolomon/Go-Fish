
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;


enum Suit {SPADES, CLUBS, DIAMONDS, HEARTS}



class Card {



    private int value;
    private Suit suit;

    public Card(Suit suit, int value) {
        setSuit(suit);
        setValue(value);
    }

    Suit getSuit() {
        return this.suit;
    }

    int getValue() { return this.value; }

/* isFace take in the value of the card and does a
   conversion from int to string for face cards
 */

    public String isFace(int value){

        String faceCard = Integer.toString(value);

        switch (value){
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

    void setSuit(Suit suit) {
        this.suit = suit;
    }

    void setValue(int value) {
        this.value = value;
    }

    void printCard() {
      System.out.println(isFace(value) + " of " + suit);
      System.out.println("");
        try {
            writeCardToFile("GO FISH! Player drew a: " + isFace(value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* writing to a file logic from
       https://www.baeldung.com/java-write-to-file
    */
    public static void writeCardToFile(String s)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output", true));
        writer.write(s);
        writer.newLine();
        writer.close();
    }

}
