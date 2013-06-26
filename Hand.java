// James M. Piette III: Created 9/22/03 2:02 AM

// Hand.java

// The class Hand represents the hand of a player. It
// contains three data members: card1, card2, and worth.
// Hand includes methods that do functions like
// returning the higher Card in the hand, returning 
// a boolean describing whether two cards are suited,
// and returning the small Card in the hand, along with
// the Hand_worth method that finds the worth of the hand.

public class Hand
{
	// card1 and card2 are the two starting cards
	// of a player, once the cards are dealt to the
	// players at the table.

	private Card card1;

	private Card card2;

	// worth is a double that describes the actual
	// worth of the hand. This number is compared
	// to other hands, where the best hand or the
	// hand with the highest worth value wins.
	// (NOTE: worth is used to, also, help predict
	// what hand could currently, assuming the
	// game has not entered the showdown, win.)

	private double worth;

	// Constructor

	// A blank instance of the class hand.

	public Hand()
	{
	}

	// An instance of the class hand that contains two
	// cards, new_card1 and new_card2, describing the
	// two cards in the hand.

	public Hand(Card new_card1, Card new_card2)
	{
		card1 = new_card1;
		card2 = new_card2;
	}

	// Accessors

	// Get_card1 returns the first card in the hand.

	public Card Get_card1()
	{
		return (card1);
	}
	
	// Get_card2 returns the second card in the hand.

	public Card Get_card2()
	{
		return (card2);
	}
	
	// Get_worth() returns a double describing the
	// worth of the hand.

	public double Get_worth()
	{
		return (worth);
	}

	// Get_high returns the highest valued card
	// in the hand. If there is no Card that is of
	// higher value (i.e. they are equal), then card1
	// is returned.

	public Card Get_high()
	{
		if (card1.Get_value() > card2.Get_value())
		{
			return card1;
		}

		if (card2.Get_value() > card1.Get_value())
		{
			return card2;
		}

		return card1;
	}

	// Get_low returns the lowest valued card in
	// the hand. If there is no card that is of lower
	// value, then card2 is returned.

	public Card Get_low()
	{
		if (card1.Get_value() < card2.Get_value())
		{
			return card1;
		}
	
		if (card2.Get_value() < card1.Get_value())
		{
			return card2;
		}
		
		return card2;
	}

	// Suited returns a boolean describing whether
	// the two cards in the hand are suited, or of the
	// same suit.

	public boolean Suited()
	{
		if (card1.Get_suit() == card2.Get_suit())
		{
			return true;
		}
		
		return false;
	}

	// Modifiers

	// Set_card is a modifier that takes an integer
	// of 1 or 2 and places a given Card object in
	// the correct card spot (either card1 or card2).

	public void Set_card(Card some_card, int index)
	{
		if (index == 1)
		{
			card1 = some_card;
		}
		
		else
		{
			card2 = some_card;
		}
	}

	// Other

	// Hand_worth takes the table at its current state and
	// gives the appropiate worth value to the data member,
	// worth. This method is used to predict who has the 
	// best hand, during the showdown, and it is also used
	// as a supplemental method by the computer player's
	// method or Computer class method, Win_percent, along
	// with other, various functions.

	public double Hand_worth(Table theTable)
	{
		int[] value_count = new int[13];

		int[] suit_count = new int[4];

		int value_max = 0;

		int suit_max = 0;

		int i;
	
		int j;
		
		int k;

		int max_com = 0;

		int val_max2;

		boolean check;

		// Two arrays are created. One array, value_count,
		// will be used for the card values having a length
		// of 13. The other is suit_count, an array used for
		// storing the number of suits of a certain type.

		for (i = 0; i < 13; i++)
		{
			value_count[(i)] = 0;
		}

		for (i = 0; i < 4; i++)
		{
			suit_count[(i)] = 0;
		}

		// The following statements look at the player's
		// hand and the community cards and sums them up.
		// In otherwords, it finds how many clubs, spades,
		// aces, tens, etc. that a player has including
		// the table cards.

		value_count[(card1.Get_value())]++;

		suit_count[(card1.Get_suit())]++;

		value_count[(card2.Get_value())]++;

		suit_count[(card2.Get_suit())]++;

		if (theTable.Get_round() > 0)
		{
			value_count[(theTable.Get_com(0).Get_value())]++;

			suit_count[(theTable.Get_com(0).Get_suit())]++;

			value_count[(theTable.Get_com(1).Get_value())]++;

			suit_count[(theTable.Get_com(1).Get_suit())]++;

			value_count[(theTable.Get_com(2).Get_value())]++;

			suit_count[(theTable.Get_com(2).Get_suit())]++;

			max_com = 2;
		}

		if (theTable.Get_round() > 1)
		{
			value_count[(theTable.Get_com(3).Get_value())]++;

			suit_count[(theTable.Get_com(3).Get_suit())]++;

			max_com = 3;
		}

		if (theTable.Get_round() > 2)
		{
			value_count[(theTable.Get_com(4).Get_value())]++;

			suit_count[(theTable.Get_com(4).Get_suit())]++;
		
			max_com = 4;
		}

		// The following loops are used to determine what
		// value or suit of cards has the most instances of
		// it. If two values have the same number of instances,
		// then the higher value of the two is stored.

		for (i = 0; i < 13; i++)	
		{		
			if ((value_count[i] > value_count[value_max]) ||
				((i > value_max) && (value_count[i] ==
				value_count[value_max])))
			{	 
				value_max = i; 
			}	
		}		

		for (i = 0; i < 4; i++)
		{		
			if (suit_count[i] > suit_count[suit_max])
			{ 	
				suit_max = i;
			} 
		}

		// The code to give value or worth for a hand that
		// has a straight, straight flush, or royal flush.

		for (i = 8; i >= 0; i--)
		{
			if ((value_count[i] * value_count[i + 1] *
				value_count[i + 2] * value_count[i + 3]
				* value_count[i + 4]) > 0)
			{
				if (suit_count[suit_max] >= 5)
				{
					check = true;

					if (suit_count[suit_max] != 7)
					{
						j = i;

						while ((check) && (j < (i + 5)))
						{
							for (k = 0; k <= max_com; k++)
							{
								if (theTable.Get_com(k).Get_value() == j)
								{
									if (theTable.Get_com(k).Get_suit() != suit_max)
									{
										check = false;
									}
								}
							}
							
							if (card1.Get_value() == j)
							{
								if (card1.Get_suit() != suit_max)
								{
									check = false;
								}
							}
					
							if (card2.Get_value() == j)
							{
								if (card2.Get_suit() != suit_max)
								{
									check = false;
								}
							}

							j++;
						}
					}
					
					if (check)
					{
						// Royal flush
					
						if (i == 8)
						{
							worth = 9;

							return worth;
						}
						
						// Straight flush
	
						else
						{
							worth = (8 + ((double)(i + 4)/ 13));
		
							return worth;
						}
					}
				}

				// Straight

				else
				{
					worth = (4 + ((double)(i + 4)/ 13));
				}
			}
		}

		// Code for the special case, when a player gets a straight
		// of ace, two, three, four, and five.

		if ((value_count[12] * value_count[0] * value_count[1] *
			value_count[2] * value_count[3]) > 0)
		{
			worth = (4 + (3 / 13));
		}

		// Flush

		if (suit_count[suit_max] >= 5)
		{
			worth = 5;

			j = 1;

			i = 12;

			while (j < 6)
			{
				for (k = 0; k <= max_com; k++)
				{
					if (theTable.Get_com(k).Get_suit() == suit_max)
					{
						if (theTable.Get_com(k).Get_value() == i)
						{
							worth += ((double)i / (double)(13 ^ j));

							j++;
						}
					}
				}

				if (card1.Get_suit() == suit_max)
				{
					if (card1.Get_value() == i)
					{
						worth += ((double)i / (double)(13 ^ j));
					
						j++;
					}
				}
			
				if (card2.Get_suit() == suit_max)
				{
					if (card2.Get_value() == i)
					{
						worth += ((double)i / (double)(13 ^ j));

						j++;
					}
				}

				i--;
			}

			return worth;
		}
		
		// Four of a kind
	
		if (value_count[value_max] == 4)
		{
			worth = (7 + ((double)(value_max) / 13));

			j = 2;

			i = 12;

			check = true;

			while (check)
			{
				for (k = 0; k <= max_com; k++)
				{
					if ((theTable.Get_com(k).Get_value() != value_max) && (check))
					{
						if (theTable.Get_com(k).Get_value() == i)
						{
							worth += ((double)i / (double)(13 ^ j));
							
							check = false;
						}
					}
				}
	
				if ((card1.Get_value() != value_max) && (check))
				{
					if (card1.Get_value() == i)
					{
						worth += ((double)i / (double)(13 ^ j));

						check = false;	
					}
				}
		
				if ((card2.Get_value() != value_max) && (check))
				{
					if (card2.Get_value() == i)
					{
						worth += ((double)i / (double)(13 ^ j));

						check = false;
					}
				}
	
				i--;
			}

			return worth;
		}

		if (value_count[value_max] == 3)
		{
			val_max2 = -1;

			for (i = 0; i < 13; i++)
			{
				if ((value_count[i] >= 2) && (i != value_max) &&
					(i > val_max2))
				{
					val_max2 = i;
				}
			}
		
			// Full house

			if (val_max2 > -1)
			{
				worth = (6 + ((double)(value_max) / 13) +
					((double)(val_max2) / (13 ^ 2)));

				return worth;
			}

			// Three of a kind

			else
			{
				worth = (3 + ((double)(value_max) / 13));

				j = 2;

				i = 12;

				while (j < 4)
				{
					for (k = 0; k <= max_com; k++)
					{
						if (theTable.Get_com(k).Get_value() != value_max)
						{
							if (theTable.Get_com(k).Get_value() == i)
							{
								worth += ((double)i / (double)(13 ^ j));
							
								j++;	
							}
						}
					}
		
					if (card1.Get_value() != value_max)
					{
						if (card1.Get_value() == i)
						{
							worth += ((double)i / (double)(13 ^ j));

							j++;
						}
					}
			
					if (card2.Get_value() != value_max)
					{
						if (card2.Get_value() == i)
						{
							worth += ((double)i / (double)(13 ^ j));

							j++;
						}
					}
		
					i--;
				}
			
				return worth;
			}
		}
		
		if (value_count[value_max] == 2)
		{
			val_max2 = -1;

			for (i = 0; i < 13; i++)
			{
				if ((value_count[i] == 2) && (i != value_max) &&
					(i > val_max2))
				{
					val_max2 = i;
				}
			}
			
			// Two pair
	
			if (val_max2 > -1)
			{
				worth = (2 + ((double)(value_max) / 13) +
					((double)(val_max2) / (13 ^ 2)));

				j = 2;

				i = 12;

				check = true;

				while (check)
				{
					for (k = 0; k <= max_com; k++)
					{
						if ((theTable.Get_com(k).Get_value() != value_max) && 
							(theTable.Get_com(k).Get_value() != val_max2) &&
							(check))
						{
							if (theTable.Get_com(k).Get_value() == i)
							{
								worth += ((double)i / (double)(13 ^ j));
								
								check = false;
							}
						}
					}
		
					if ((card1.Get_value() != value_max) && 
						(card1.Get_value() != val_max2) &&
						(check))
					{
						if (card1.Get_value() == i)
						{
							worth += ((double)i / (double)(13 ^ j));

							check = false;	
						}
					}
			
					if ((card2.Get_value() != value_max) && 
						(card2.Get_value() != val_max2) &&
						(check))
					{
						if (card2.Get_value() == i)
						{
							worth += ((double)i / (double)(13 ^ j));

							check = false;
						}
					}
		
					i--;
				}

				return worth;
			}
	
			// Pair

			else
			{
				worth = (1 + ((double)(value_max) / 13));
		
				j = 2;

				i = 12;

				while (j < 5)
				{
					for (k = 0; k <= max_com; k++)
					{
						if (theTable.Get_com(k).Get_value() != value_max)
						{
							if (theTable.Get_com(k).Get_value() == i)
							{
								worth += ((double)i / (double)(13 ^ j));
							
								j++;	
							}
						}
					}
		
					if (card1.Get_value() != value_max)
					{
						if (card1.Get_value() == i)
						{
							worth += ((double)i / (double)(13 ^ j));

							j++;
						}
					}
			
					if (card2.Get_value() != value_max)
					{
						if (card2.Get_value() == i)
						{
							worth += ((double)i / (double)(13 ^ j));

							j++;
						}
					}
		
					i--;
				}

				return worth;
			}
		}

		// High card

		else
		{
			worth = ((double)(value_max) / 13);

			j = 2;

			i = 12;

			while (j < 6)
			{
				for (k = 0; k <= max_com; k++)
				{
					if (theTable.Get_com(k).Get_suit() == suit_max)
					{
						if (theTable.Get_com(k).Get_value() == i)
						{
							worth += ((double)i / (double)(13 ^ j));

							j++;
						}
					}
				}

				if (card1.Get_suit() == suit_max)
				{
					if (card1.Get_value() == i)
					{
						worth += ((double)i / (double)(13 ^ j));
					
						j++;
					}
				}
			
				if (card2.Get_suit() == suit_max)
				{
					if (card2.Get_value() == i)
					{
						worth += ((double)i / (double)(13 ^ j));

						j++;
					}
				}

				i--;
			}

			return worth;
		}
	}

}
