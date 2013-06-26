// You v0.5

// Created 5/26/04 1:15 PM

// By James M. Piette III

// You.java

// You is an implementation of the abstract
// class Playa that describes the specific
// player, or Playa, that is played or
// controlled by the user.

public class You extends Playa
{
	// myHand is the hand of the player, which
	// contains a series of methods and two
	// cards.

	protected PlayaHand myHand;

	// history is a data member that is used
	// only by the users playa. history serves a
	// purpose to keep track of the actions that
	// the different players have preformed,
	// during the current hand. Once the hand is
	// over, You processes the history to help
	// later playing and creates a new instance
	// of PlayaHistory.

	protected PlayaHistory history;

	// Constructors

	// Blank constructor.

	public You()
	{
	}

	// This constructor creates an instance of
	// the You class to begin with a given
	// position and bankroll.

	public You(int pos, double bank)
	{
		super(pos, bank);

		myHand = new PlayaHand();

		history = new PlayaHistory();
	}
	
	// Accessors

	// Get_hand returns the hand of the user.

	public PlayaHand Get_hand()
	{
		return (myHand);
	}

	// Get_history returns the history record
	// kept by You.

	public PlayaHistory Get_history()
	{
		return (history);
	}
}
