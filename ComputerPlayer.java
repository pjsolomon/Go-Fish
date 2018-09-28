// ComputerPlayer class
// 09/22/2018
import java.util.Random;

public class ComputerPlayer extends Player {
	private enum computerPlayerIntelligence {
		GOLDFISH(1),
		PERFECT(2),
		OMNISCIENT(3)
	}

	private int computerPlayerLieFrequency;

	// Constructors

	/**
	 * Primary Constructor
	 * Creates a "ComputerPlayer" object. Initializes completedSets at 0
	 *
	 * @param intelligence How smart the ComputerPlayer will be.
	 * @param lieFrequency What percentage of the computer's responses will be lies.
	 */
	public ComputerPlayer(int intelligence, int lieFrequency) {
		computerPlayerIntelligence = intelligence;
		computerPlayerLieFrequency = lieFrequency;
		hand = new CardCollection();
		completedSets = 0;
	}

	/**
	 * showHand
	 * Overwrites the Player's Function to Show Hand. Since this is the computer, we show nothing.
	 * @return string showing nothing.
	 */
	public void showHand()
	{
		System.out.println("");
	}

	/**
	 * checkHand
	 * Accepts a card value, then checks the ComputerPlayer's hand for that value.
	 * Will Lie according to Lie Frequency Setting.
	 *
	 * @param cardValue Value of card to check ComputerPlayer's hand
	 * @return Boleen True if card is in ComputerPlayer's hand, false otherwise
	 */
	public Boolean checkHand(int cardValue) {
		Boolean isInHand = false;
		if (hand.contains(cardValue)) {
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
		switch (computerPlayerIntelligence){
			case 1:
				returnValue = this.hand.getRandomValue();
				break;
			case 2:
				// Trying to figure this one out. Do we already have a variable that keeps track of requested cards?
		}
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
