// James M. Piette III: Created 10/8/03 12:23 PM

// History.java

// History is a class that holds information about the
// betting history for a given player. This information
// includes a 2D array describing amount of an action
// during a round and the round the player, if they have,
// folded their hand.

public class History
{
	// actions[][] is a 2D array that contains the
	// number of a certain action is performed during
	// a particular round. There are two rows to the
	// array, which represent the two different actions
	// ("call" or "bet"), and four columns, which
	// represent the round in which the action was made
	// (0, 1, etc.).

	private int[][] actions;

	// fold_round is an integer describing what round
	// if any, the player has folded in. If the player
	// has yet to fold, this value is set to be a
	// default, -1.

	private int fold_round;

	// Constructors

	// A blank instance of the History class.

	public History()
	{
		actions = new int[2][4];

		fold_round = -1;
	}

	// Accessors

	// Get_act_hist is a method that takes an integer
	// describing a past round and another integer 
	// portraying a past action and returns a
	// number corresponding with the number of that
	// certain action taken in the specific round.
	// (NOTE: Inputting '1' or "call" returns the
	// first row or index 0 row, while '2' or "bet"
	// returns the second row.)
	
	public int Get_act_hist(int action, int round)
	{
		if (action == 1)
		{
			return (actions[0][round]);
		}
		
		else
		{
			return (actions[1][round]);
		}
	}

	// Get_fold_round is a method that returns the
	// round that the particular player folded on.

	public int Get_fold_round()
	{
		return (fold_round);
	}

	// Modifiers

	// Add_action is a method that alters the current
	// history listed by the player, given the
	// action and the round that the player preformed
	// that action (integers inputted to method).

	public void Add_action(int round, int action)
	{
		(actions[(action - 1)][round])++;
	}

	// Round_folded is a method that sets the data
	// member known as fold_round to the current
	// round, indicating that the player folded
	// during the given round.

	public void Round_folded(int round)
	{
		fold_round = round;
	}
}
