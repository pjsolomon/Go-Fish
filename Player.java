// Player class
// 09/22/2018


public class Player
{
	private CardCollection hand;
	private int completedSets;


	// Constructors
	/**
	 *Primary Constructor
	 *Creates a "Player" object. Initializes completedSets at 0
	 */
	public Player (){
		hand = new Collection();
		completedSets = 0;
	}

	/**
	 *showHand
	 *Shows the hand of the Player
	 *@return string showing the Player's hand
	 */
	public String showHand(){
		hand.printCards();
	}

	/**
	 * checkHand
	 * Asks the player if he has a specific card. Allows player to lie.
	 * @param cardValue Value of card to check ComputerPlayer's hand
	 * @return Boleen True if card is in ComputerPlayer's hand, false otherwise
	 */
	public Boolean checkHand (int cardValue){
		Boolean isInHand = false;
		Boolean continueToLoop = true;

		Scanner Reader = new Scanner(System.in);

		while(continueToLoop){
			//Need some function to call from the interface to ask the operator if they have 'cardValue'
			System.out.prinln("Do you have any " + cardValue + "s? (Y/N): ");
			String Input = Reader.next()

			if (playerResponse.toLowerCase() == "y" && this.hand.contains(cardValue)) {
				// Player says they have it, and actually has the card(s)
				isInHand = true;
				continueToLoop = false;
			} else if (playerResponse.toLowerCase() == "y" && !(this.hand.contains(cardValue))){
				// Player says they have it, but does not actually have the cards!
				isInHand = false;
				interface.message("Can't Lie about this one!");
			} else if (playerResponse.toLowerCase() == "n" && !(this.hand.contains(cardValue))){
				// Player says they don't have it, and they don't actually have the cards!
				isInHand = false;
				continueToLoop = false;
			} else if (playerResponse.toLowerCase() == "n" && (this.hand.contains(cardValue))){
				// Player lies, actually having the cards!
				interface.message("Don't Worry, your secret is safe with me.");
				isInHand = false;
				continueToLoop = false;
			} else {
				interface.message("I don't understand. Try again!");
			}
		}
		// Made it out! It's time to return.
		return isInHand;
	}

	/**
	 * requestCard
	 * Get's the card value that the player asks for.
	 * Contains Logic to prevent player for asking for a value they do not have.
	 * @return Value of card they want. (Right Now using 1-13. Will interpret face card letters to numbers)
	 */
	public int requestCard (){
		Boolean continueToLoop = true;
		int returnValue = 0;
		Scanner Reader = new Scanner(System.in);
		while(continueToLoop){
			// This function assumes that the interface class contains a function called "prompt"
			// Assuming that it accepts a string as an argument, where that string will be displayed
			// somehow, and the function will return the inputed value.
			System.out.prinln("What card will you ask for? ");
			String Input = Reader.next();
			// Trim the string for the switch
			String caseSelector = Input.trim().toLowerCase().substring(0,3);
			// This switch interprets the first 3 characters in the returned string.
			// Accepts numbers, number words, and face card names.
			// Sets this function's returnValue
			switch (caseSelector) {
				case "1":
				case "one":
				case "ace":
					returnValue = 1;
					break;
				case "2":
				case "two":
					returnValue = 2;
					break;
				case "3":
				case "thr":
					returnValue = 3;
					break;
				case "4":
				case "fou":
					returnValue = 4;
					break;
				case "5":
				case "fiv":
					returnValue = 5;
					break;
				case "6":
				case "six":
					returnValue = 6;
					break;
				case "7":
				case "sev":
					returnValue = 7;
					break;
				case "8":
				case "eig":
					returnValue = 8;
					break;
				case "9":
				case "nin":
					returnValue = 9;
					break;
				case "10":
				case "ten":
					returnValue = 10;
					break;
				case "11":
				case "ele":
				case "jac":
					returnValue = 11;
					break;
				case "12":
				case "twe":
				case "que":
					returnValue = 12;
					break;
				case "13":
				case "thi":
				case "kin":
					returnValue = 13;
					break;
				default:
					returnValue = 0;
					break;
			}

			// Make sure the player can only ask for cards they already have.
			if(this.hand.containsValue(returnValue)){
				return returnValue;
			} else {
				// Remind Player that they have to ask for a value they alraedy have.
				System.out.println("You do not have this card in your hand. Please ask for a card value that you already have in your hand.");
				returnValue = 0;
			}
			// If we've gone this far, we should repeat the loop.
		}
	}

	/**
	 *toString
	 *Returns a string showing player status, ideally for writing status
	 *@return String with all of the ComputerPlayer's relavent data
	 */
	public String toString(){
		String returnValue = "Human Player Score: " + this.completedSets + "\nHuman Player Hand: " + this.hand + "\n";
		return returnValue
	}
}
