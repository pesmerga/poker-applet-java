// PlayaCard v0.5

// Created 5/26/04 1:02 PM

// By James M. Piette III

// PlayaCard.java

// PlayaCard is the class describing a card. It
// contains two data members: value and suit. 
// PlayaCard class consists of several other 
// methods involving construction and accessing 
// of a PlayaCard's values.

public class PlayaCard
{
	// value is the value of the card. In otherwords,
	// value represents the values Deuce to Ace. Ace
	// is represented as a 12 with all other cards going
	// in descending order from there ending with Deuce
	// equal to 0.

	private int value;

	// suit describes the suit of the card expressed in
	// the form of an integer. In this program, the
	// integers 0 through 3 are used where 0 is clubs
	// and the rest are in alphabetical order ending with
	// spades as 3.

	private int suit;

	// Constructors

	// Used when creating an instance of the class Card
	// that is totally blank and empty.

	public PlayaCard()
	{
	}
	
	// This constructor is used when two integers
	// describing the value and suit of the particular
	// instance of the card being created.

	public PlayaCard(int some_value, int some_suit)
	{
		value = some_value;
		suit = some_suit;
	}

	// Accessors

	// Get_value returns an integer between 0 and 12
	// representing the value of the particular instance
	// of Card in question.

	public int Get_value()
	{
		return value;
	}
	 
	// Get_suit returns an integer that ranges from 0
	// to 3, where 0 is clubs, 1 is diamonds, 2 is
	// hearts, and 3 is spades.

	public int Get_suit()
	{
		return suit;
	}

	// EqualTo is a method that takes a card and compares
	// that card to it testing to see if it is equal or	
	// not. For two cards to be equal, they must have the
	// same suit and value as each other.

	public boolean EqualTo(PlayaCard other)
	{
		if ((value == other.Get_value()) &&
			(suit == other.Get_suit()))
		{
			return (true);
		}

		return (false);
	}

}
