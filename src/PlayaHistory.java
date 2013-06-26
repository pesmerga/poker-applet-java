// PlayaHistory v0.5

// Created 5/27/04 11:55 AM

// By James M. Piette III

// PlayaHistory.java

// PlayaHistory is a class that is used only by
// the You class, or the subclass of Playa that
// represents the player controlled by the user.
// This class holds information about what actions
// have been preformed in the current hand of
// play and which players have been making those
// actions. PlayaHistory assists the PokerPlaya
// AI in, not only determining what sort of action
// the user should take, but what sort of players
// the user is facing.

public class PlayaHistory
{
	// total is an array that keeps tracks of
	// the number of bets and calls that have
	// been preformed, during the hand.

	private int[] total;

	// playas_acts is a 3-D array, describing
	// what actions players have been doing
	// and in what rounds of play they have
	// been doing them (NOTE: 'fold' is not
	// an action stored here; only 'call',
	// index of one, and 'bet', index of two,
	// are stored.

	private int[][][] playas_acts;

	// round_folded is an array that
	// represents the round in which a certain
	// player has folded. If that player has
	// yet to have fold, the value in there
	// spot is equal to -1.

	private int[] round_folded;

	// Constructors

	// A blank constructor that initializes
	// an instance of PlayaHistory, setting
	// all number of actions to 0 and, as for
	// round_folded, set to -1.

	public PlayaHistory()
	{
		total = new int[2];

		playas_acts = new int[10][2][4];

		round_folded = new int[10];

		for (int i = 0; i < 10; i++)
		{
			round_folded[i] = -1;

			for (int j = 0; j < 2; j++)
			{
				for (int k = 0; k < 4; k++)
				{
					playas_acts[i][j][k] = 0;
				}
			}
		}

		total[0] = 0;
		total[1] = 0;
	}

	// Accessors

	// Get_total is a method that returns the
	// total number of times a certain action
	// has been preformed.

	public int Get_total(int action)
	{
		return (total[(action - 1)]);
	}

	// Get_playa_acts returns a given player's
	// actions that were preformed in a certain
	// round.

	public int Get_playa_acts(int playa, int action, int round)
	{
		return (playas_acts[playa][(action - 1)][round]);
	}

	// Get_fold_round returns the round in which
	// a player folded.

	public int Get_fold_round(int playa)
	{
		return (round_folded[playa]);
	}

	// Other

	// Update_history is a method that is ran after
	// the table has been updated and a new action
	// has been preformed. To run the method, three
	// parameters must be included: the player who
	// did the action (the index of that player),
	// the action in that the player preformed, and
	// the round that the action was preformed. With
	// this data, Update_history updates and sets
	// the appropiate data members to be representative
	// of the new action preformed.

	public void Update_history(int playa, int action, int round)
	{
		if (action != 0)
		{
			total[(action - 1)]++;
	
			playas_acts[playa][(action - 1)][round]++;
		}

		else
		{
			round_folded[playa] = round;
		}
	}
}
