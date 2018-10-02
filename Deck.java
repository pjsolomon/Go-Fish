import java.lang.reflect.Array;
import java.util.ArrayList;

public class Deck {


    //initialize the deck
    ArrayList<Card> CardList = new ArrayList<Card>();

    public Deck() {
        setCardList(CardList);
    }

    public void setCardList(ArrayList<Card> importedCardList){
        CardList = importedCardList;
    }

    public ArrayList<Card> getCardList() {
        return this.CardList;
    }

    //Iterate through and find the card
    public void removeCard(Card c) {
        for (int i = 0; i < CardList.size(); i++){
            if (c.getValue() == CardList.get(i).getValue() && c.getSuit() == CardList.get(i).getSuit()){
                CardList.remove(CardList.get(i));
            }
        }
    }

    //This method creates a clean deck of 52 cards
    public ArrayList<Card> initializeDeck() {

        //Plebian Cards
        for (int i = 2; i < 11; i++) {
            Card spades = new Card(Suit.SPADE, i);
            CardList.add(spades);
            Card hearts = new Card(Suit.HEART, i);
            CardList.add(hearts);
            Card diamonds = new Card(Suit.DIAMOND, i);
            CardList.add(diamonds);
            Card clubs = new Card(Suit.CLUB, i);
            CardList.add(clubs);
        }
        //Face Cards
        //Jack = 11
        Card jackSpade = new Card(Suit.SPADE, 11);
        CardList.add(jackSpade);
        Card jackHeart = new Card(Suit.HEART, 11);
        CardList.add(jackHeart);
        Card jackDiamond = new Card(Suit.DIAMOND, 11);
        CardList.add(jackDiamond);
        Card jackClub = new Card(Suit.CLUB, 11);
        CardList.add(jackClub);

        //Queen = 12
        Card queenSpade = new Card(Suit.SPADE, 12);
        CardList.add(queenSpade);
        Card queenHeart = new Card(Suit.HEART, 12);
        CardList.add(queenHeart);
        Card queenDiamond = new Card(Suit.DIAMOND, 12);
        CardList.add(queenDiamond);
        Card queenClub = new Card(Suit.CLUB, 12);
        CardList.add(queenClub);

        //King = 13
        Card kingSpade = new Card(Suit.SPADE, 13);
        CardList.add(kingSpade);
        Card kingHeart = new Card(Suit.HEART, 13);
        CardList.add(kingHeart);
        Card kingDiamond = new Card(Suit.DIAMOND, 13);
        CardList.add(kingDiamond);
        Card kingClub = new Card(Suit.CLUB, 13);
        CardList.add(kingClub);

        //Ace = 14
        Card aceSpade = new Card(Suit.SPADE, 14);
        CardList.add(aceSpade);
        Card aceHeart = new Card(Suit.HEART, 14);
        CardList.add(aceHeart);
        Card aceDiamond = new Card(Suit.DIAMOND, 14);
        CardList.add(aceDiamond);
        Card aceClub = new Card(Suit.CLUB, 14);
        CardList.add(aceClub);


        return CardList;
    }




}


