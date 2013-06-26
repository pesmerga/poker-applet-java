// James M. Piette III: Created 9/20/03 6:15 PM

// Deck.java

// Represents the Deck class in the program. The deck will contain an
// array of 52 cards and functions that go along with that data member,
// such as a shuffling method based on a random number. It will also
// contain a private data member describing the top card of the stack,
// so, instead of having to delete members or move members of the array
// back and forth, for each card taken from the deck, the top card moves
// down one. deck will also contain a method for outputting a specific
// card in the deck.

import java.io.*;
import java.util.*;

public class Deck
{
	// Because deck size will never vary between games, the
	// deck_cards[] array is prompted always to contain 52
	// card elements.

	private Card deck_cards[] = new Card[52];

	// An integer describing the index, not the actual number the
	// element is in the array, of the top card of the deck. Each
	// time a game is played, before any cards are dealt, the top_card
	// is set back to 0 (first card in deck_cards[]).

	private int top_card;

	// Constructor
	
	// This is one constructor for an instance of the class
	// Deck. Since there will always be 52 cards (Deuce to Ace and
	// Clubs to Spades) in the deck_cards[], the array size is set
	// to be of a length of 52. The constructor places the
	// 52 cards in an unshuffled fashion into deck_cards[].

	public Deck()
	{
		int i;

		for (i = 0; i < 52; i++)
		{
			deck_cards[(i)] = new Card((i % 13), (i / 13));
		}
	}

	// The other constructor for an instance of Deck is 
	// exclusively used by methods that use multiple 
	// decks. This constructor takes an array of cards
	// along with an integer pointing to the top_card
	// of the deck.

	public Deck(Card[] deck_cards, int top_card)
	{
		this.deck_cards = deck_cards;

		this.top_card = top_card;
	}

	// Accessor
	
	// Get_card() returns a card from the top of the deck,
	// represented by the private member top_card. Everytime this
	// function is called, one is added to top_card. NOTE: Because
	// there is no way for a whole deck of cards in a game of Texas
	// Hold'em to be depleted, there is no point in making a break
	// in case the top_card is invalid or too high.

	public Card Get_card()
	{
		Card temp_card = deck_cards[(top_card)];

		top_card++;
		
		return temp_card;
	}

	// Copy is a method that returns a copy of the deck.

	public Deck Copy()
	{
		Deck copy = new Deck(deck_cards, top_card);

		return copy;
	}

	// IsEmpty is a method that returns a boolean describing
	// whether the deck is empty (true) or is not (false).
	// (NOTE: The deck is seen as empty when the top_card
	// is equal to 52.)

	public boolean IsEmpty()
	{
		if (top_card == 52)
		{
			return (true);
		}
		
		return (false);
	}

	// Modifiers

	// Add_card takes a card and inserts the card at the top_card
	// position. top_card is then incremented downwards (-). 
	// NOTE: Add_card will not add a card if top_card = 0.

	public void Add_card(Card card1)
	{
		if (top_card != 0)
		{
			deck_cards[(top_card)] = card1;

			top_card--;
		}
	}

	// Other

	// Shuffle_deck() takes the deck_cards[] and shuffles them
	// using a random number generator and a comparison sort. In 
	// otherwords, an alternate array containing 52 doubles is created 
	// and random doubles are placed into the elements. This array is 
	// sorted and the deck_of_cards[] mimics this sort. Once it has 
	// finished shuffling the array of cards, top_card is set to equal 
	// 0, or the top (first) card of the deck.

	public void Shuffle_deck()
	{
		Random generator = new Random();

		double random_list[] = new double[52];
	
		int i;

		for (i = 0; i < 52; i++)
		{
			random_list[i] = generator.nextDouble();
		}
	
		int counter;
		
		double temp_double;

		Card temp_card;

		do
		{
			counter = 0;

			for (i = 0; i < 51; i++)
			{
				if (random_list[(i)] < random_list[(i + 1)])
				{
					temp_double = random_list[(i)];
					random_list[(i)] = random_list[(i + 1)];
					random_list[(i + 1)] = temp_double;

					temp_card = deck_cards[(i)];
					deck_cards[(i)] = deck_cards[(i + 1)];
					deck_cards[(i + 1)] = temp_card;

					counter = 1;
				}
			}
		} while (counter == 1);
		
		top_card = 0;
	}
}	
