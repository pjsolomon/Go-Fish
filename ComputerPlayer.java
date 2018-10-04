// Import Statements
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class ComputerPlayer extends Player
{
	//Computer Players are Normal or Smart (1 or 2)
	private int computerPlayerIntelligence;
	//Computer Players Have Lie Frequency Between 0 and 100
	private int computerPlayerLieFrequency;
	//Computer Players May Remember what Human Asks
	private ArrayList<Integer> askedCardsMemory;
	// Constructors

	/**
	 * Primary Constructor
	 * Creates a "ComputerPlayer" object. Initializes completedSets at 0
	 *
	 * @param intelligence How smart the ComputerPlayer will be.
	 * @param lieFrequency What percentage of the computer's responses will be lies.
	 */
	public ComputerPlayer(int intelligence, int lieFrequency)
	{
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

	//OVERRIDE: Computer Players Don't Show Their Hand
	public void showHand()
	{
		System.out.println("");
	}
	//OVERRIDE: Simply Adds a Card to Hand, No Printing Neccessary
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
	public Boolean checkHand(int cardValue)
	{
		Boolean isInHand = false;
		if(!askedCardsMemory.contains(new Integer(cardValue)))
		{
			askedCardsMemory.add(cardValue);
		}
		if (hand.containsValue(cardValue))
		{
			Random randomNumber = new Random();
			// If the random number is higher than the "Lie Frequency", Computer
			// will tell truth and admit it has the card. Otherwise it will lie.
			if (randomNumber.nextInt(100) > computerPlayerLieFrequency)
			{
				isInHand = true;
				return isInHand;
			}
			else
			{
				return isInHand;
			}
		}
		else
		{
			return isInHand;
		}
	}


	/**
	 * requestCard
	 * Get's the card value that the computer wants.
	 * @return Value of card the computer wants.
	 */
	public int requestCard ()
	{
		int returnValue = 0;
		Random randomNumber = new Random();
		switch (computerPlayerIntelligence)
		{
			case 1:
				while(true)
				{
					returnValue = randomNumber.nextInt(13);
					if(this.hand.containsValue(returnValue))
					{
						try { Card.writeCardToFile("Asked For: " + returnValue); }
						catch (IOException e)
						{
                e.printStackTrace();
            }
						return returnValue;
					}
				}

			case 2:
				// First, Ask for cards other player asked for previously
				for(int i : askedCardsMemory)
				{
					if(this.hand.containsValue(i))
					{
						askedCardsMemory.remove(new Integer(i));
						try { Card.writeCardToFile("Asked For: " + i); }
						catch (IOException e)
						{
                e.printStackTrace();
            }
						return i;
					}
				}
				// Otherwise, just ask for a random card we have.
				while(true)
				{
					returnValue = randomNumber.nextInt(13);
					if(this.hand.containsValue(returnValue))
					{
						try { Card.writeCardToFile("Asked For: " + returnValue); }
						catch (IOException e)
						{
                e.printStackTrace();
            }
						return returnValue;
					}
				}
		}
		// We should not hit this
		return returnValue;
	}
}
