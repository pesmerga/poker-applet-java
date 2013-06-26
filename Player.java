// James M. Piette III: Created 10/7/03 8:55 PM

// Player.java

// The Player class is a superclass for a computer player
// and a human player. A Player is one of up to ten
// individuals that can sit at the table, being either
// a computer-controlled or human-controlled player.
// The Player class contains a hand, an integer describing
// bets made this round, a double showing how much money
// the player has (bankroll), a string of the player's
// name, a history of the player's betting, the position
// in the table the player is in, and a boolean that tells
// whether the player has folded or not.

public abstract class Player
{
	// name is the name of the player represented in
	// the form of a string.

	private String name;

	// bankroll is the amount of money a player has.

	private double bankroll;

	// action is an integer describing the action
	// the Player will be taking. The integer can
	// be either 0, fold, 1, check or call, 2,
	// bet, raise, or reraise, or -1, describing
	// a player who has yet to make an action.

	private int action;

	// bets_this_round is an integer between 0 and
	// 4 that shows how many bets the player has
	// made in the current round of play.

	private int bets_this_round;

	// position is an integer describing in what
	// seat or position the player is currently
	// seated at.

	private int position;

	// folded is a boolean that, if true, means
	// the player has folded. If folded is false,
	// then the player has yet to fold.

	private boolean folded;

	// myHand is the hand of the player, which
	// contains a series of methods and two
	// cards.

	private Hand myHand;

	// myHistory is an instance of the class
	// history describing the player's history.

	private History myHistory;

	// Constructors

	// A blank constructor.

	public Player()
	{
	}

	// Creates an instance of Player with a given name and
	// bankroll.

	public Player(String new_name, double new_bank)
	{
		name = new_name;
		
		bankroll = new_bank;

		myHand = new Hand();
	
		myHistory = new History();
	}

	// Accessors

	// Get_name returns the name of the player.

	public String Get_name()
	{
		return (name);
	}

	// Get_bankroll returns the amount of money the player
	// controls.

	public double Get_bankroll()
	{
		return (bankroll);
	}
	
	// Get_bets returns the number of bets a
	// player has made this round.

	public int Get_bets()
	{
		return (bets_this_round);
	}

	// Get_position returns the position of the player accroding
	// to where the sit in the table.

	public int Get_position()
	{
		return (position);
	}
	
	// Get_folded returns a boolean describing whether the
	// player has folded or not.

	public boolean Get_folded()
	{
		return (folded);
	}

	// Get_hand returns the hand of the player, myHand.

	public Hand Get_hand()
	{
		return (myHand);
	}
	
	// Get_history returns the betting history of the player.

	public History Get_history()
	{
		return (myHistory);
	}

	// Modifiers
		
	// Mod_bankroll takes a double and adds the given double
	// to the player's bankroll. This number may be either
	// positive or negative number.

	public void Mod_bankroll(double value)
	{
		bankroll += value;
	}

	// Other

	// Update_player is a method that updates the player's
	// data members to make them consistent and correct
	// for every new round of play. (NOTE: If the method
	// is given an integer, then Update_player runs as
	// with the understanding that a new hand has started
	// and the round is 0, using the integer given to
	// describe position of the player.) Also, this method
	// is overloaded, since a human player has different
	// things that are needed to be prompted after each
	// hand/round.

	public void Update_player(int position)
	{
		this.position = position;

		action = -1;

		bets_this_round = 0;

		myHistory = new History();
	
		folded = false;
	}
		

	public void Update_player()
	{
		action = -1;
	
		bets_this_round = 0;
	}

	public abstract int Get_rank();

	public abstract int Get_kind();
	
	public abstract int Get_special();	
}
