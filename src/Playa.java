// Playa v0.5

// Created 5/26/04 12:27 PM

// By James M. Piette III

// Playa.java

// The Playa class is an abstract class for players
// that play Poker in the Table class. A Playa
// can either be a You, which is the Playa the
// user is controlling, and an Opponent, which
// is all the other players sitting at the table
// with the user. The Playa class, also, holds
// most of the basic data that both of these
// classes of Playas have, such as bankroll,
// action, bets the player has made in the round,
// etc.

public abstract class Playa
{
	// bankroll is the amount of money a player has.

	protected double bankroll;

	// action is an integer describing the action
	// the Player will be taking. The integer can
	// be either 0, fold, 1, check or call, 2,
	// bet, raise, or reraise, or -1, describing
	// a player who has yet to make an action.

	protected int action;

	// bets_this_round is an integer between 0 and
	// 4 that shows how many bets the player has
	// made in the current round of play.

	protected double bets_this_round;

	// position is an integer describing in what
	// seat or position the player is currently
	// seated at.

	protected int position;

	// folded is a boolean that, if true, means
	// the player has folded. If folded is false,
	// then the player has yet to fold.

	protected boolean folded;

	// Constructors

	// A blank constructor.

	public Playa()
	{
		action = -1;

		bets_this_round = 0;

		folded = false;
	}

	// Creates an instance of Playa with a given bankroll
	// and position.

	public Playa(int pos, double new_bank)
	{
		position = pos;

		bankroll = new_bank;

		action = -1;

		bets_this_round = 0;

		folded = false;
	}

	// Accessors

	// Get_bankroll returns the amount of money the player
	// controls.

	public double Get_bankroll()
	{
		return (bankroll);
	}
	
	// Get_bets returns the number of bets a
	// player has made this round.

	public double Get_bets()
	{
		return (bets_this_round);
	}

	// Get_position returns the position of the player accroding
	// to where the sit in the table.

	public int Get_position()
	{
		return (position);
	}

	// Get_action returns the action last preformed by the Playa.

	public int Get_action()
	{
		return (action);
	}
	
	// Get_folded returns a boolean describing whether the
	// player has folded or not.

	public boolean Get_folded()
	{
		return (folded);
	}

	// Modifiers
		
	// Mod_bankroll takes a double and adds the given double
	// to the player's bankroll. This number may be either
	// positive or negative number.

	public void Mod_bankroll(double value)
	{
		bankroll += value;
	}

	// Set_action is a method that takes an integer and sets
	// the data member describing a player's action, action,
	// to the new action.

	public void Set_action(int action)
	{
		this.action = action;
	}

	// Set_folded sets the player to have a true or false value 
	// for the folded data member and the player is, now,
	// recognized as folded.

	public void Set_folded(boolean fold)
	{
		folded = fold;
	}

	// Set_bets is a function that takes a double describing
	// a new amount of bets or the bet representation of how
	// much the player has contributed to the pot.

	public void Set_bets(double bets)
	{
		bets_this_round = bets;
	}

	// Other

	// Update_player is a method that updates the player's
	// data members to make them consistent and correct
	// for every new round of play. 
	//
	// (NOTE: If the method is given an integer, then 
	// Update_player runs as with the understanding that 
	// a new hand has started and the round is 0, using 
	// the integer given to describe position of the player,
	// since this method is overloaded.) 

	// Round 0 or the pre-flop.

	public void Update_playa(int position)
	{
		this.position = position;

		action = -1;

		bets_this_round = 0;

		folded = false;
	}
		
	// Any other round besides round 0.

	public void Update_playa()
	{
		action = -1;
	
		bets_this_round = 0;
	}
}
