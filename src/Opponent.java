// Opponent v0.5

// Created 5/26/04 1:16 PM

// By James M. Piette III

// Opponent.java

// Opponent is an implementation of the
// abstract class Playa that describes the
// players that the user is playing against.

public class Opponent extends Playa 
{
	// Constructors

	// Blank constructor.

	public Opponent()
	{
	}

	// This constructor creates an instance of
	// the Opponent class to begin with a given
	// position and bankroll.

	public Opponent(int pos, double bank)
	{
		super(pos, bank);
	}
}
