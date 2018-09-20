class Card {
    enum Suit {SPADE, CLUB, DIAMOND, HEART}

    private int value;
    private Suit suit;

    public Card(Suit suit, int value) {
        setSuit(suit);
        setValue(value);
    }

    Suit getSuit() {
        return this.suit;
    }

    int getValue() {
        return this.value;
    }

    void setSuit(Suit suit) {
        this.suit = suit;
    }

    void setValue(int value) {
        this.value = value;
    }
}