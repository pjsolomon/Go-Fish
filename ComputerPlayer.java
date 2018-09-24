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
		hand = new Collection();
		completedSets = 0;
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
			if (randumNumber.nextInt(100) > computerPlayerLieFrequency) {
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
	 * toString
	 * Returns a string showing ComputerPlayer status, ideally for writing status
	 *
	 * @return String with all of the ComputerPlayer's relavent data
	 */
	public String toString() {
		String returnValue = "Computer Player Intelligence: " + this.computerPlayerIntelligence + "\nComputer Player Lie Frequency: " + this.computerPlayerLieFrequency + "\nComputer Player Score: " + this.completedSets + "\nComputer Player Hand: " + this.hand + "\n";
		return returnValue;
	}

	/**
	 * takeTurn
	 * Computer Player's Take Turn Function
	 * Calls pickCardLogic to decide on what card to pick. Requests card of opponent
	 * If other player has card, performs card swap. Else, draws card.
	 *
	 * @param Player Opposing player.
	 */
	public takeTurn(Player opponent) {
		requestedCard = pickCardLogic
		if (opponent.checkHand(requestedCard)) {
			hand.add(opponent.getCards(requestedCard));
		} else {
			// drawCard = deck.draw()
			this.hand.add(drawCard);
			// If this card matches requested card, get a free turn!
			// interface.say("\nComputer Player drew " + requestedCard + " and gets a free turn.")
			if (drawCard == requestedCard) {
				this.takeTurn(opponent);
			}
		}
		return;
	}

}