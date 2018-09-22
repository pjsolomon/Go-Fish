// Player class
// 09/22/2018


public class Player
{
	private Collection hand;
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
		return hand;
	}

	/**
	 * checkHand
	 * Asks the player if he has a specific card. Allows player to lie.
	 * @param cardValue Value of card to check ComputerPlayer's hand
	 * @return Boleen True if card is in ComputerPlayer's hand, false otherwise
	 */
	public checkHand (int cardValue){
		//Need some function to call from the interface to ask the operator if they have 'cardValue'
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