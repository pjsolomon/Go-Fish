// Player class
// 09/22/2018

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

@SuppressWarnings("unchecked")

public class Player
{
	protected CardCollection hand;
	protected int completedSets;
	protected String name;
	protected boolean isHuman;


	// Constructors
	/**
	 *Primary Constructor
	 *Creates a "Player" object. Initializes completedSets at 0
	 */
	 public Player (){
	 	this.name = "";
		isHuman = true;
	 	hand = new CardCollection();
	 	completedSets = 0;
	 }

	public Player (String name){
		this.name = name;
		isHuman = true;
		hand = new CardCollection();
		completedSets = 0;
	}

	public String getName()
	{
		return name;
	}

	public boolean isHuman()
	{
		return isHuman;
	}

	/**
	 *showHand
	 *Shows the hand of the Player
	 */
	public void showHand()
	{

		hand.printCards();
	}

	public void sortHand()
	{
		hand.sortCards();
	}

	public boolean isEmpty()
	{
		if(hand.cSize() == 0){
			return true;
		}
		else{
			return false;
		}
	}

	public void addToHand(Card C, boolean Print)
	{
		hand.addCard(C);
		if(Print)
		{
			if(C.getValue() == 1 | C.getValue() == 8)
			{
				System.out.print(name + " Drew An ");
			}
			else
			{
				System.out.print(name + " Drew A ");
			}
			C.printCard();
		}
	}

	public ArrayList<Card> popFromHand(int V)
	{
		return hand.removeValues(V);
	}

	public int getSets()
	{
		return completedSets;
	}

	public Boolean checkSets()
	{
		int g = hand.removeSets();

		if(g > 0)
		{
			System.out.print(name + " Got Four " + Card.isFace(g) + "s! ");
			completedSets = completedSets + 1;
			System.out.print(name + " Now Has " + completedSets + " Book");
			if(completedSets > 1)
			{
				System.out.print("s");
			}
			System.out.println("!\n");
			return(true);
		}
		else{
			return(false);
		}
	}

	/**
	 * checkHand
	 * Asks the player if he has a specific card. Allows player to lie.
	 * @param cardValue Value of card to check ComputerPlayer's hand
	 * @return Boleen True if card is in ComputerPlayer's hand, false otherwise
	 */
	public Boolean checkHand (int cardValue){
		Card c = new Card(Suit.SPADES, 10); //Dummy object
		Boolean isInHand = false;
		Boolean continueToLoop = true;

		Scanner Reader = new Scanner(System.in);

		while(continueToLoop){
			//Need some function to call from the interface to ask the operator if they have 'cardValue'
			hand.printCards();
			System.out.println("Do You Have Any " + Card.isFace(cardValue) + "s? (Y/N): ");
			String Input = Reader.next();
			System.out.println("");

			if (Input.toLowerCase().equals("y") && this.hand.containsValue(cardValue)) {
				// Player says they have it, and actually has the card(s)
				isInHand = true;
				continueToLoop = false;
			} else if (Input.toLowerCase().equals("y") && !(this.hand.containsValue(cardValue))){
				// Player says they have it, but does not actually have the cards!
				isInHand = false;
				System.out.println("Can't Lie About This One!");
				System.out.println("");
			} else if (Input.toLowerCase().equals("n") && !(this.hand.containsValue(cardValue))){
				// Player says they don't have it, and they don't actually have the cards!
				isInHand = false;
				continueToLoop = false;
			} else if (Input.toLowerCase().equals("n") && (this.hand.containsValue(cardValue))){
				// Player lies, actually having the cards!
				System.out.println("Don't Worry, Your Secret is Safe With Me.");
				System.out.println("");
				isInHand = false;
				continueToLoop = false;
			} else {
				System.out.println("I Don't Understand. Try Again!");
				System.out.println("");
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
		int returnValue = 0;
		Scanner Reader = new Scanner(System.in);
		while(returnValue == 0){
			// This function assumes that the interface class contains a function called "prompt"
			// Assuming that it accepts a string as an argument, where that string will be displayed
			// somehow, and the function will return the inputed value.
			System.out.println("What Card Will You Ask For? ");
			String Input = Reader.next();
            try {
                Card.writeCardToFile("Asked For: " + Input);
            }catch (IOException e) {
                e.printStackTrace();
            }
			System.out.println("");
			// Trim the string for the switch
			String caseSelector = Input.trim().toLowerCase();
			// Accepts numbers, number words, and face card names.
			// Sets this function's returnValue
			switch (caseSelector) {
				case "quit":
					returnValue = -1;
					break;
				case "1":
				case "a":
				case "ace":
					returnValue = 1;
					break;
				case "2":
				case "two":
					returnValue = 2;
					break;
				case "3":
				case "three":
					returnValue = 3;
					break;
				case "4":
				case "four":
					returnValue = 4;
					break;
				case "5":
				case "five":
					returnValue = 5;
					break;
				case "6":
				case "six":
					returnValue = 6;
					break;
				case "7":
				case "seven":
					returnValue = 7;
					break;
				case "8":
				case "eight":
					returnValue = 8;
					break;
				case "9":
				case "nine":
					returnValue = 9;
					break;
				case "10":
				case "ten":
					returnValue = 10;
					break;
				case "11":
				case "j":
				case "jack":
					returnValue = 11;
					break;
				case "12":
				case "q":
				case "queen":
					returnValue = 12;
					break;
				case "13":
				case "k":
				case "king":
					returnValue = 13;
					break;
				default:
					returnValue = 0;
					break;
			}

			// Make sure the player can only ask for cards they already have.
			if(!this.hand.containsValue(returnValue) & returnValue != -1)
			{
				returnValue = 0;
				System.out.println("Please Ask for a Rank that You Have in Your Hand.");
			}
		}
		return returnValue;
	}
}
