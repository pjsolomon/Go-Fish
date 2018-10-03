// ComputerPlayer class
// 09/22/2018
import java.util.Random;
import java.util.ArrayList;

public class ComputerPlayer extends Player {
	private int computerPlayerIntelligence;
	private int computerPlayerLieFrequency;
	private ArrayList<Integer> askedCardsMemory;
	// Constructors

	/**
	 * Primary Constructor
	 * Creates a "ComputerPlayer" object. Initializes completedSets at 0
	 *
	 * @param intelligence How smart the ComputerPlayer will be.
	 * @param lieFrequency What percentage of the computer's responses will be lies.
	 */
	public ComputerPlayer(int intelligence, int lieFrequency) {
		super.name = "Computer";
		isHuman = false;
		computerPlayerIntelligence = intelligence;
		computerPlayerLieFrequency = lieFrequency;
		hand = new CardCollection();
		askedCardsMemory = new ArrayList<Integer>();
		completedSets = 0;
	}

	/**
	 * showHand
	 * Overwrites the Player's Function to Show Hand. Since this is the computer, we show nothing.
	 * @return string showing nothing.
	 */

	public void showHand()
	{
		System.out.print("");
	}

	public void addToHand(Card C)
	{
		hand.addCard(C);
	}

	/**
	 * checkHand
	 * Accepts a card value, then checks the ComputerPlayer's hand for that value.
	 * Will Lie according to Lie Frequency Setting. Also stores the requested card
	 * into askedCardsMemory for level 2 intelligence computers.
	 *
	 * @param cardValue Value of card to check ComputerPlayer's hand
	 * @return Boleen True if card is in ComputerPlayer's hand, false otherwise
	 */
	public Boolean checkHand(int cardValue) {
		Boolean isInHand = false;
		if(!askedCardsMemory.contains(new Integer(cardValue)))
		{
			askedCardsMemory.add(cardValue);
		}
		if (hand.containsValue(cardValue)) {
			Random randomNumber = new Random();
			// If the random number is higher than the "Lie Frequency", Computer
			// will tell truth and admit it has the card. Otherwise it will lie.
			if (randomNumber.nextInt(100) > computerPlayerLieFrequency) {
				isInHand = true;
				return isInHand;
			} else {
				return isInHand;
			}
		} else {
			return isInHand;
		}
	}


	/**
	 * requestCard
	 * Get's the card value that the computer wants.
	 * @return Value of card the computer wants.
	 */
	public int requestCard (){
		int returnValue = 0;
		Random randomNumber = new Random();
		switch (computerPlayerIntelligence){
			case 1:
				while(true){
					returnValue = randomNumber.nextInt(13);
					if(this.hand.containsValue(returnValue)){
						return returnValue;
					}
				}

			case 2:
				// First, Ask for cards other player asked for previously
				for(int i : askedCardsMemory){
					if(this.hand.containsValue(i)){
						askedCardsMemory.remove(new Integer(i));
						return i;
					}
				}
				// Otherwise, just ask for a random card we have.
				while(true){
					returnValue = randomNumber.nextInt(13);
					if(this.hand.containsValue(returnValue)){
						return returnValue;
					}
				}
		}
		// We should not hit this
		return returnValue;
	}

	/**
	 * toString
	 * Returns a string showing ComputerPlayer status, ideally for writing status
	 *
	 * @return String with all of the ComputerPlayer's relavent data
	 */
	public String toString() {
		String returnValue = "Computer Player Intelligence: " + this.computerPlayerIntelligence + "\nComputer Player Lie Frequency: " + this.computerPlayerLieFrequency + "\nComputer Player Score: " + this.completedSets + "\nComputer Player Hand: " + this.hand + "\n";
		return returnValue;
	}


}
