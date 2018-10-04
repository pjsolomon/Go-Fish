//Import Statements
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

@SuppressWarnings("unchecked")

public class Player
{
	//JOSHUA SACHAR
	//Player's Collection of Cards
	protected CardCollection hand;
	//Player's Number of Books
	protected int completedSets;
	//Player's Name
	protected String name;
	//Whether or Not Player is Human
	protected boolean isHuman;


	// Constructors
	/**
	 *Primary Constructor
	 *Creates a "Player" Object. Initializes completedSets To 0
	 */
	 //JOSHUA SACHAR
	 public Player ()
	 {
	 	this.name = "";
		isHuman = true;
	 	hand = new CardCollection();
	 	completedSets = 0;
	 }

	//Constructor with Name Input from User
	//WILLIAM SLOCUM
	public Player (String name)
	{
		this.name = name;
		isHuman = true;
		hand = new CardCollection();
		completedSets = 0;
	}

	//Returns Player's Name
	//WILLIAM SLOCUM
	public String getName()
	{
		return name;
	}

	//Returns Whether Player is Human
	//WILLIAM SLOCUM
	public boolean isHuman()
	{
		return isHuman;
	}

	//Invokes Method to Print Cards in Hand
	//JOSHUA SACHAR
	public void showHand()
	{
		hand.printCards();
	}

	//Invokes Method to Sort Cards in Hand
	//WILLIAM SLOCUM
	public void sortHand()
	{
		hand.sortCards();
	}

	//Returns True if Hand has 0 Elements
	//WILLIAM SLOCUM
	public boolean isEmpty()
	{
		if(hand.cSize() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//Adds a Specified Card ot Hand, May or May Not Print to Console
	//WILLIAM SLOCUM
	public void addToHand(Card C, boolean Print)
	{
		hand.addCard(C);
		if(Print)
		{
			//Prints Correct Grammar Depending on Card
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

	//Invokes Method to Remove All Cards of Rank V from Hand
	//WILLIAM SLOCUM
	public ArrayList<Card> popFromHand(int V)
	{
		return hand.removeValues(V);
	}

	//Returns Player's Number of Books
	//WILLIAM SLOCUM
	public int getSets()
	{
		return completedSets;
	}

	//If Player Has 4 Cards of Same Rank in Their Hand, Remove Them
	//WILLIAM SLOCUM
	public Boolean checkSets()
	{
		//Invokes Method to Remove Sets from Hand
		int g = hand.removeSets();

		//If g != 0, g is the Rank of the Set
		if(g > 0)
		{
			//Print Results to Console
			System.out.print(name + " Got Four " + Card.isFace(g) + "s! ");
			completedSets = completedSets + 1;
			System.out.print(name + " Now Has " + completedSets + " Book");
			//Ensure Correct Grammar
			if(completedSets > 1)
			{
				System.out.print("s");
			}
			System.out.println("!\n");

			return(true);
		}
		//If g == 0, Then No Sets in Hand
		else
		{
			return(false);
		}
	}

	//Invokes Method to Write Cards of Hand to File
	//WILL SOCCORSI
	public void writeHandToFile()
	{
		hand.recordCards(name);
	}

	/**
	 * checkHand
	 * Asks the player if he has a specific card. Allows player to lie.
	 * @param cardValue Value of card to check ComputerPlayer's hand
	 * @return Boleen True if card is in ComputerPlayer's hand, false otherwise
	 */
	 //JOSHUA SACHAR
	public Boolean checkHand (int cardValue)
	{
		Boolean isInHand = false;
		Boolean continueToLoop = true;

		Scanner Reader = new Scanner(System.in);

		while(continueToLoop)
		{

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
		// Made It Out! Return Results
		return isInHand;
	}

	/**
	 * requestCard
	 * Get's the card value that the player asks for.
	 * Contains Logic to prevent player for asking for a value they do not have.
	 * @return Value of card they want. (Right Now using 1-13. Will interpret face card letters to numbers)
	 */
	 //JOSHUA SACHAR
	public int requestCard ()
	{
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
