// PlayaTable v0.5

// Created 5/25/04 2:28 PM

// By James M. Piette III

// PlayaTable.java

// The Table class is the main class that sits at
// the top of the heiarchy for nearly all functions
// that are to be preformed in the applet. It holds
// all the necessary data members to allow a game of
// Texas Hold'em Poker to be played, such as bet
// level, player positions, including dealer, small
// blind and big blind positions, community cards on
// the table, round number, etc. The Table is the
// core of the program.

public class PlayaTable
{
	// The bet_level is a data member describing
	// the low level fo the bet (i.e. 15/30 Limit
	// Table means that the bet_level is 15 for
	// the table).

	private double bet_level;

	// playas is an array of size 10 that can hold
	// up to 10 different playas. Only sub-classes
	// of the abstract class, Playa, can be stored
	// in this array.

	private Playa[] playas;

	// current_playa is an integer describing the index
	// of the current playa being observed/prompted for
	// an action.

	private int current_playa;

	// last_raiser is an integer pointing to the index
	// of the last person to raise at the table, if
	// it is the pre-flop round, last_raiser points
	// to the big blind, or, if no one has bet, yet,
	// then last_raiser points to the dealer.

	private int last_raiser;

	// The integer dealer points to the index of the
	// playa in the playas array that is the dealer
	// of the table.

	private int dealer;

	// The community is an array containing the community
	// cards for the table (i.e. the cards that are dealt
	// during the flop, turn, and river). These cards are
	// private data members, but can be accessed freely
	// from the table function.

	private PlayaCard[] community;
	
	// bets_round is a float describing the number of
	// bets that have been made, during a particular
	// round. At the beginning of every round, except for
	// pre-flop, the bets_round is set to equal 0.
	// During pre-flop, the counter is set to 1, as to
	// symbolize the fact that one must at least call one
	// bet to stay in the first round.

	private double bets_round;

	// pot is the pot for the table. It is a double, since
	// a player may be forced to go all-in and a portion
	// of a bet may be placed in.

	private double pot;

	// round is an integer that describes what round of
	// play the table is currently in (i.e. 0 = pre-flop
	// round, 1 = flop, 2 = turn, 3 = river).

	private int round;

	// plays is a data member that refers to the number
	// of hands or plays the game has gone through.

	private int plays;

	// human is an integer pointing to the player being
	// played by the user.

	private int human;

	// Constructors

	// Blank constructor.

	public PlayaTable()
	{
	}

	// The following constructor creates an instance of
	// the Table class that has a given bet_level and
	// a given human playa, you, starting at the
	// given position/seat.

	public PlayaTable(double bet_level, int human)
	{
		Playa[] tempPlayas = new Playa[10];

		for (int i = 0; i < 10; i++)
		{
			if (i != (human - 1))
			{
				tempPlayas[i] = new Opponent(i, 0);
			}
		}

		tempPlayas[(human - 1)] = new You((human - 1), 0);
		
		playas = tempPlayas;

		this.human = (human - 1);

		this.bet_level = bet_level;

		dealer = 9;

		round = 0;

		plays = 0;
	}

	// Creates a Table object with a given community
	// that had already been found. This instance of
	// the Table class is assumed to be at the last	
	// round of play, or, round = 3.

	public PlayaTable(PlayaCard[] community)
	{
		this.community = community;

		round = 3;
	}


	// Methods

	// Accessors

	// Get_level is a method that returns a double
	// that describes the bet_level at the table.

	public double Get_level()
	{
		return (bet_level);
	}

	// Get_pot is a method that returns the pot size at
	// the table.

	public double Get_pot()
	{
		return (pot);
	}

	// Get_bets returns the number of bets that have
	// been placed in the current round of betting.

	public double Get_bets()
	{
		return (bets_round);
	}

	// Get_player_bets returns an array of double
	// values that represent the bets a Playa has
	// made, during the round.

	public double[] Get_playa_bets()
	{
		double[] tempDouble = new double[10];

		for (int i = 0; i < 10; i++)
		{
			if (playas[i] != null)
			{
				tempDouble[i] = playas[i].Get_bets();
			}
		}

		return (tempDouble);
	}
			

	// Get_round returns the round the table is
	// currently in.

	public int Get_round()
	{
		return (round);
	}

	// Get_plays returns the number of times the game
	// has been played.

	public int Get_plays()
	{
		return (plays);
	}

	// Get_current returns the current playa at the
	// table.

	public Playa Get_current()
	{
		return (playas[current_playa]);
	}

	// Get_curr_index returns the index of the current
	// player.

	public int Get_curr_index()
	{
		return (current_playa);
	}

	// Get_last returns the last raiser at the table.
	
	public Playa Get_last()
	{
		return (playas[last_raiser]);
	}

	// Get_last_index returns the index that the last
	// player to raise is located at in the playa
	// array.

	public int Get_last_index()
	{
		return (last_raiser);
	}

	// Get_dealer returns the dealer at the table.

	public Playa Get_dealer()
	{
		return (playas[dealer]);
	}

	// Get_deal_index returns the index of the dealer
	// at the table.

	public int Get_deal_index()
	{
		return (dealer);
	}

	// Get_human returns the playa the user is playing.

	public You Get_human()
	{
		return ((You)playas[human]);
	}

	// Get_playa takes an integer that represents an
	// index and returns the playa and that index in
	// the Playa array, playas.

	public Playa Get_playa(int index)
	{
		return (playas[index]);
	}

	// Get_com returns the community card, taken from
	// the array, community, at the given index.

	public PlayaCard Get_com(int index)
	{
		return (community[index]);
	}

	// Not_folded returns the number of players at the
	// table, who have yet to fold their hand.

	public int Not_folded()
	{
		int i;

		int counter = 0;		

		for (i = 0; i < 10; i++)
		{
			if ((playas[i] != null) &&
				(!playas[i].Get_folded()))	
			{
				counter++;
			}
		}

		return counter;
	}

	// Modifiers

	// Move_current is a modifier method that moves the
	// current_player pointer by incrementing it by 1.
	// If the index exceeds 9, then the mod 10 will
	// set the index back to 0. If there is no player
	// there, then the index moves to the next player.

	public void Move_current()
	{
		do
		{
			current_playa = ((current_playa + 1) % 10);

		} while ((playas[(current_playa)] == null) ||
			 (playas[(current_playa)].Get_folded()));
	}

	// Move_dealer moves the dealer button over one seat
	// to the left and, if there is no player seated
	// there, the button moves, until it sits at a seat
	// where a playa is present.

	public void Move_dealer()
	{
		do
		{
			dealer = ((dealer + 1) % 10);

		} while (playas[(dealer)] == null);
	}

	// Set_raiser sets the last_raiser index pointer to
	// the given current_player being observed.

	public void Set_raiser()
	{
		last_raiser = current_playa;
	}

	// Set_current sets the current_playa index pointer
	// to a new, given integer representing the new player
	// who is the current_playa.

	public void Set_current(int current)
	{
		current_playa = current;
	}

	// Other

	// Update_table is a method that is run during the start
	// of each new round. This method is overloaded for
	// particular new rounds. Update_table method that takes
	// no input is used at the start of the pre-flop round.
	// The one take an array of PlayaCard's is for the
	// post-flop round. Finally, the Update_table that uses
	// a single PlayaCard is used to update the table for
	// both the Turn and the River.

	// Update the table for the pre-flop.

	public void Update_table()
	{
		int i;

		int position;
	
		// If this is not the first game played, then
		// move the dealer to the correct position
		// and update the player's positions.

		if (plays != 0)
		{
			Move_dealer();

			position = 0;

			for (int j = 0; j < 10; j++)
			{
				if (playas[(j)] != null)
				{
					playas[(j)].Update_playa(position);

					position++;
				}
			}
		}

		i = 1;

		// Determine who the small blind is at the table
		// and post their blind.

		while (playas[((dealer + i) % 10)] == null)
		{
			i++;
		}
		
		playas[((dealer + i) % 10)].Mod_bankroll((-.5) * bet_level);

		playas[((dealer + i) % 10)].Set_bets(.5);
		
		i++;

		// Determine who the big blind is and post their
		// blind.

		while (playas[((dealer + i) % 10)] == null)
		{
			i++;
		}
		
		playas[((dealer + i) % 10)].Mod_bankroll((-1) * bet_level);

		playas[((dealer + i) % 10)].Set_bets(1);

		// Add the blinds to the pot.

		pot = ((1.5) * bet_level);

		// Set the last raiser to be the big blind and
		// make the first person to the left of the
		// big blind the current player.

		Set_current((dealer + i) % 10);

		Set_raiser();

		Move_current();

		bets_round = 1;

		community = new PlayaCard[5];
	}
	
	// Update the table for the post-flop.

	public void Update_table(PlayaCard[] flop)
	{
		int i = 10;

		// Find the player closest to the dealer, whether
		// it is the dealer or someone to the right of	
		// the dealer.

		while ((playas[((dealer + i) % 10)] == null) ||
		       (playas[((dealer + i) % 10)].Get_folded()))
		{
			i--;
		}

		// Set this player found to be that last person
		// to act in the round if no one bets, or the last
		// raiser.

		Set_current((dealer + i) % 10);
		
		Set_raiser();

		Move_current();
		
		// Update every player sitting at the table.
		
		for (int j = 0; j < 10; j++)
		{
			if (playas[(j)] != null)
			{
				playas[(j)].Update_playa();
			}
		}

		bets_round = 0;

		// With the given array of cards, set them equal
		// to the appropiate cards in the community.

		community[0] = flop[0];
		community[1] = flop[1];
		community[2] = flop[2];
	}

	// Update the table for the turn or the river.

	public void Update_table(PlayaCard card)
	{
		int i = 10;
		
		// Find the player that will be the last person
		// to act in the round if no one bets by setting
		// the person closest to the right of the dealer, 
		// or the dealer, and make them the last raiser.

		while ((playas[((dealer + i) % 10)] == null) ||
		       (playas[((dealer + i) % 10)].Get_folded()))
		{
			i--;
		}

		Set_current((dealer + i) % 10);
			
		Set_raiser();

		Move_current();

		// Update the player's sitting at the table.
			
		for (int j = 0; j < 10; j++)
		{
			if (playas[(j)] != null)
			{
				playas[(j)].Update_playa();
			}
		}
	
		bets_round = 0;

		// If it is round 2 or the turn, double the betting
		// level and make card, the inputted card, equal to
		// the card in the community representing the turn
		// card

		if (round == 2)
		{
			bet_level *= 2;

			community[3] = card;
		}

		// If it is round 3 or the river, set the river card
		// to the last spot in the community array.

		else
		{
			community[4] = card;
		}
	}

	// Update_play is a method that is ran after each action
	// is preformed. It's job is to update the correct data
	// members, such as pot, action history, etc.

	public void Update_play()
	{
		int act = Get_current().Get_action();

		// The player chose to fold.

		if (act == 0)
		{
			Get_current().Set_folded(true);

			Move_current();
		}

		// The player chose to call/check.

		if (act == 1)
		{
			double tempAmount = (bets_round - Get_current().Get_bets()) *
					    (bet_level);

			Get_current().Mod_bankroll(-tempAmount);

			pot += tempAmount;

			Get_current().Set_bets(bets_round);

			Get_current().Set_folded(false);

			Move_current();
		}

		// The player chose to bet/raise.

		if (act == 2)
		{
			bets_round++;

			double tempAmount = (bets_round - Get_current().Get_bets()) *
					    (bet_level);

			Get_current().Mod_bankroll(-tempAmount);
		
			pot += tempAmount;

			Get_current().Set_bets(bets_round);

			Get_current().Set_folded(false);
		
			Set_raiser();			

			Move_current();
		}

		// Update the history being kept track of
		// by the object PlayaHistory by running
		// the method Update_history.

		Get_human().Get_history().Update_history(Get_curr_index(),
							 act,
							 round);
	}

	// Check is a function that checks and determines 
	// whether the hand is done, the round is done,
	// or nothing needs to be changed.

	public int Check()
	{
		// If everyone has folded except one player, then
		// that player is the winner, which is the current
		// player.

		if (Not_folded() == 1)
		{
			Get_current().Mod_bankroll(pot);
		
			pot = 0;

			plays++;

			if (round >= 2)
			{
				bet_level *= .5;
			}

			round = 0;

			return (1);
		}
		
		// During the pre-flop round.

		if (round == 0)
		{
			// If the current player is the last person
			// to raise AND the number of bets made in the
			// round is greater than one (i.e. someone has
			// raised in the round), then begin the next
			// round.

			if ((current_playa == last_raiser) &&
			    (bets_round > 1))
			{
				round++;
	
				return (2);
			}

			// If no one has bet this round and the last
			// raiser or the big blind has made an
			// action, then start the next round.
		
			if ((Get_last().Get_action() != -1) &&
			    (bets_round == 1))
			{
				int tempCurr = current_playa;

				current_playa = last_raiser;

				Move_current();

				if (tempCurr == current_playa)
				{
					round++;

					return (2);
				}

				current_playa = tempCurr;

				return (0);
			}

			else
			{
				return (0);
			}
		}

		// During any round, besides the pre-flop or
		// showdown.

		if ((round > 0) && (round < 4))
		{
			// The current player to act is, also,
			// the last raiser and the number of
			// bets made in the round is greater
			// than zero, so the next round begins.

			if ((current_playa == last_raiser) &&
			    (bets_round > 0)) 
			{
				round++;

				if (round == 4)
				{
					return (3);
				}
	
				return (2);
			}

			// The number of bets made in the round
			// is zero and the last player to act
			// has made an action.

			if ((Get_last().Get_action() != -1) &&
			    (bets_round == 0))
			{
				int tempCurr = current_playa;

				current_playa = last_raiser;

				Move_current();

				if (current_playa == tempCurr)
				{
					round++;
		
					if (round == 4)
					{
						return (3);
					}

					return (2);
				}

				current_playa = tempCurr;

				return (0);
			}

			else
			{
				return (0);
			}
		}

		else
		{
			return (0);
		}
	}

	// New_play is a method used only when a push occurs
	// at the table; New_play resets the necessary variables
	// and preps the table for a new hand.

	public void New_play()
	{
		pot = 0;

		bet_level *= .5;
		
		round = 0;

		plays++;
	}
}
