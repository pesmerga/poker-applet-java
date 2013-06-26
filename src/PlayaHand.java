// PlayaHand v0.5

// Created 5/26/04 12:56 PM

// By James M. Piette III

// PlayaHand.java

// The class PlayaHand represents the hand of a player.
// It contains three data members: card1, card2, and
// worth. PlayaHand includes methods that returns the
// card containing the higher value out of the two cards
// in the hand, or the smaller card of the two, and
// a function called Hand_worth, which finds the worth
// of the particular PlayaHand.

public class PlayaHand
{
	// card1 and card2 are the two starting cards
	// of a player, once the cards are dealt to the
	// players at the table.

	private PlayaCard card1;

	private PlayaCard card2;

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

	public PlayaHand()
	{
	}

	// An instance of the class hand that contains two
	// cards, new_card1 and new_card2, describing the
	// two cards in the hand.

	public PlayaHand(PlayaCard new_card1, PlayaCard new_card2)
	{
		card1 = new_card1;
		card2 = new_card2;
	}

	// Accessors

	// Get_card1 returns the first card in the hand.

	public PlayaCard Get_card1()
	{
		return (card1);
	}
	
	// Get_card2 returns the second card in the hand.

	public PlayaCard Get_card2()
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
	// in the hand. If there is no PlayaCard that is of
	// higher value (i.e. they are equal), then card1
	// is returned.

	public PlayaCard Get_high()
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

	public PlayaCard Get_low()
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

	public void Set_card(PlayaCard acard, int index)
	{
		if (index == 1)
		{
			card1 = acard;
		}
		else
		{
			card2 = acard;
		}
	}

	// Other

	// Hand_worth takes the table at its current state and
	// gives the appropiate worth value to the data member,
	// worth. This method is used to predict who has the 
	// best hand, during the showdown, and it is also used
	// as a supplemental method by the computer player's
	// method or You class method, Win_percent, along
	// with other, various functions.
	//
	// (NOTE: To do specific rankings of hands to include
	// kickers, etc., the program uses the value of the
	// kicker or specific hand type divided by 13^n, where
	// n could be one through five. This is done because
	// the most that a value of a hand can become is
	// 12, an ace, so the kicker will not make a pair better
	// than a bad three-of-a-kind or be equated with it,
	// since incorrect calculations may do so.

	public double Hand_worth(PlayaTable theTable)
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

		i = 12;
	
		j = 0;

		PlayaCard[] best_cards = new PlayaCard[(max_com + 3)];

		while ((j <= (max_com + 2)))
		{
			if (card1.Get_value() == i)
			{
				best_cards[j] = card1;
			
				j++;
			}
		
			if (card2.Get_value() == i)
			{
				best_cards[j] = card2;

				j++;
			}
	
			for (k = 0; k <= max_com; k++)
			{
				if (theTable.Get_com(k).Get_value() == i)
				{
					best_cards[j] = theTable.Get_com(k);

					j++;
				}
			}
		
			i--;
		}

		// The following loops are used to determine what
		// value or suit of cards has the most instances of
		// it. If two values have the same number of instances,
		// then the higher value of the two is stored.

		for (i = 1; i < 13; i++)	
		{		
			if (value_count[i] >= value_count[value_max])
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

					j = i + 4;

					while ((j >= i) && (check))
					{
						check = false;

						for (k = 0; k < 7; k++)
						{
							if (best_cards[k].Get_value() == j)
							{
								if (best_cards[k].Get_suit() == suit_max)
								{
									check = true;
								}
							}
						}

						j--;
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

				else if (suit_count[suit_max] < 5)
				{
					worth = (4 + ((double)(i + 4)/ 13));

					return worth;
				}
			}
		}

		// Code for the special case, when a player gets a straight
		// of ace, two, three, four, and five.

		if ((value_count[12] * value_count[0] * value_count[1] *
			value_count[2] * value_count[3]) > 0)
		{
			if (suit_count[suit_max] >= 5)
			{
				check = true;

				j = 12;

				k = 0;

				while ((j != 4) && (check))
				{
					check = false;

					for (k = 0; k < 7; k++)
					{
						if (best_cards[k].Get_value() == j)
						{
							if (best_cards[k].Get_suit() == suit_max)
							{
								check = true;
							}
						}
					}

					j = ((j + 1) % 13);
				}
	
				if (check)
				{
					// Straight Flush of a wheel (A, 2, 3, 4, 5)

					worth = (8 + (3 / 13));

					return worth;
				}
			}
	
			else if (suit_count[suit_max] < 5)
			{
				worth = (4 + (3 / 13));

				return (worth);
			}
		}

		// Flush

		if (suit_count[suit_max] >= 5)
		{
			worth = 5;

			j = 1;

			i = 12;

			check = true;
	
			k = 0;

			while (check)
			{
				if (best_cards[k].Get_suit() == suit_max)
				{
					worth += ((double)(best_cards[k].Get_value()) / (double)(j * 13));

					j *= 13;
				}

				if (j == 371293)
				{
					check = false;
				}

				k++;
			}

			return worth;
		}
		
		// Four of a kind
	
		if (value_count[value_max] == 4)
		{
			worth = (7 + ((double)(value_max) / 13));

			j = 13;

			i = 12;

			check = true;

			k = 0;
			
			while (check)
			{
				if (best_cards[k].Get_value() != value_max)
				{
					worth += ((double)(best_cards[k].Get_value()) / (double)(j * 13));

					j *= 13;
				}

				if (j == 169)
				{
					check = false;
				}

				k++;
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
					((double)(val_max2) / (13 * 13)));

				return worth;
			}

			// Three of a kind

			else
			{
				worth = (3 + ((double)(value_max) / 13));

				j = 13;

				i = 12;

				check = true;

				k = 0;
			
				while (check)
				{
					if (best_cards[k].Get_value() != value_max)
					{
						worth += ((double)(best_cards[k].Get_value()) / (double)(j * 13));

						j *= 13;
					}

					if (j == 2197)
					{
						check = false;
					}

					k++;
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
					((double)(val_max2) / (13 * 13)));

				j = 169;

				i = 12;

				check = true;

				k = 0;
			
				while (check)
				{
					if ((best_cards[k].Get_value() != value_max) && 
					    (best_cards[k].Get_value() != val_max2))
					{
						worth += 
						((double)(best_cards[k].Get_value()) / (double)(j * 13));

						j *= 13;
					}

					if (j == 2197)
					{
						check = false;
					}
	
					k++;
				}
			
				return worth;
			}
			
			// Pair

			else
			{
				worth = (1 + ((double)(value_max) / 13));
		
				j = 13;

				i = 12;

				check = true;

				k = 0;
			
				while (check)
				{
					if (best_cards[k].Get_value() != value_max)
					{
						worth += 
						((double)(best_cards[k].Get_value()) / (double)(j * 13));

						j *= 13;
					}

					if (j == 28561)
					{
						check = false;
					}

					k++;
				}

				return worth;
			}
		}

		// High card

		else
		{
			worth = ((double)(value_max) / 13);

			j = 13;

			i = 12;

			check = true;

			k = 0;
	
			while (check)
			{
				if (i != value_max)
				{
					worth += ((double)(best_cards[k].Get_value()) / (double)(j * 13));

					j *= 13;

					k++;
				}

				if (j == 28561)
				{
					check = false;
				}
			
				i--;
			}

			return worth;
		}
	}
}
