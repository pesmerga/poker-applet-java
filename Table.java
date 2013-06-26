// James M. Piette III: Created 1/29/04 8:47 PM

// Table.java

// The Table class is the main class sitting at the top
// of the heiarchy for this program. It holds all of the
// players at the table, the dealer button (a pointer
// pointing to which player is the dealer), the current
// player being analyzed, last person to raise in the round,
// community cards, and the pot.

public class Table
{
	// The bet_level is a data member describing the
	// low level of the bet (i.e. 15/30 Limit Table:
	// means that the bet_level is 15 for the table).

	private double bet_level;

	// players is an array of size 10 that can hold
	// up to 10 different players. Only sub-classes
	// of the abstract class, Player, can be stored
	// in this array.

	private Player[] players;

	// current_player is an integer describing the index
	// of the current player being observed/prompted for
	// an action.

	private int current_player;

	// last_raiser is an integer pointing to the index
	// of the last person to raise at the table, if
	// it is the pre-flop round, last_raiser points
	// to the big blind, or, if no one has bet, yet,
	// then last_raiser points to the dealer.

	private int last_raiser;

	// The integer dealer points to the index of the
	// player in the players array that is the dealer
	// of the table.

	private int dealer;

	// The community is an array containing the community
	// cards for the table (i.e. the cards that are dealt
	// during the flop, turn, and river). These cards are
	// private data members, but can be accessed freely
	// from the table function.

	private Card[] community;
	
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

	// the_deck is an instance of the Deck object, which
	// is the deck for the table.

	private Deck the_deck;

	// Constructors

	// Blank constructor.

	public Table()
	{
	}

	// Creates an instance of table with a given dealer
	// spot, an array of players, and a bet_level.

	public Table(int dealer, Player[] players, double bet_level)
	{
		this.dealer = dealer;

		this.players = players;

		this.bet_level = bet_level;

		the_deck = new Deck();

		community = new Card[5];

		pot = 0;

		round = 0;
	}

	// Creates a Table object with a given community
	// that had already been found. This instance of
	// the Table class is assumed to be at the last	
	// round of play, or, round = 3.

	public Table(Card[] community)
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

	// Get_round returns the round the table is
	// currently in.

	public int Get_round()
	{
		return (round);
	}

	// Get_current returns the current player at the
	// table.

	public Player Get_current()
	{
		return (players[current_player]);
	}

	// Get_last returns the last raiser at the table.
	
	public Player Get_last()
	{
		return (players[last_raiser]);
	}

	// Get_dealer returns the dealer at the table.

	public Player Get_dealer()
	{
		return (players[dealer]);
	}

	// Get_com returns the community card, taken from
	// the array, community, at the given index.

	public Card Get_com(int index)
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
			if ((players[i] != null) &&
				(!players[i].Get_folded()))	
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
			current_player = ((current_player + 1) % 10);

		} while (players[(current_player)] == null);
	}

	// Move_dealer moves the dealer button over one seat
	// to the left and, if there is no player seated
	// there, the button moves, until it sits at a seat
	// where a player is present.

	public void Move_dealer()
	{
		do
		{
			dealer = ((dealer + 1) % 10);

		} while (players[(dealer)] == null);
	}

	// Set_raiser sets the last_raiser index pointer to
	// the given current_player being observed.

	public void Set_raiser()
	{
		last_raiser = current_player;
	}

	// Set_current sets the current_player index pointer
	// to a new, given integer representing the new player
	// who is the current_player.

	public void Set_current(int current)
	{
		current_player = current;
	}

	// Other

	public double[][] look_hand_prob(int player_num)
	{
		Deck deck1 = new Deck();
			
		Deck deck2;

		Deck deck3 = new Deck();

		Card temp_card;

		Card[] community = new Card[5];

		int com = 0;

		double wins;

		double hand_prob;	

		boolean check;

		Hand[] hands = new Hand[player_num];

		hands[0] = new Hand();

		double[][] hand_stats = new double[52][52];

		int card1;
		
		int card2;

		while (!deck1.IsEmpty())
		{
			hands[0].Set_card(deck1.Get_card(), 1);

			deck2 = deck1.Copy();

			while (!deck2.IsEmpty())
			{
				hands[0].Set_card(deck2.Get_card(), 2);	

				wins = find_hand_prob(player_num, hands, 0, community, com, 0, deck3);
		
				double num_hands = 1;		
/*
				switch (hands.length)
				{
					case (9) :
						num_hands *= (33 * 32);
					case (8) :
						num_hands *= (35 * 34);
					case (7) :
						num_hands *= (37 * 36);
					case (6) :
						num_hands *= (39 * 38);
					case (5) :
						num_hands *= (41 * 40);
					case (4) :
						num_hands *= (43 * 42);
					case (3) :		
						num_hands *= (45 * 44);
					case (2) :
						num_hands *= (47 * 46);
			
						num_hands *= (50 * 49 * 48);
					
						break;
				}	
*/
				hand_prob = (wins)/(num_hands);

				card1 = hands[0].Get_card1().Get_value() + (13 * hands[0].Get_card1().Get_suit());

				card2 = hands[0].Get_card2().Get_value() + (13 * hands[0].Get_card2().Get_suit());

				hand_stats[card1][card2] = hand_prob;

				System.out.println();

				System.out.println("# of wins for " + card1 + " " + card2 + " : " + hand_stats[card1][card2]);
			}
		}

		return (hand_stats);
	}

	public double find_hand_prob(int player_num, Hand[] hands, double wins, Card[] community, int com, int counter, Deck deck3)
	{
		/*
		System.out.println(counter);
		*/
	
		Card temp_card;

		double value;

		boolean push;

		boolean check;
	
		int i;

		Deck deck1;

		Deck deck2;

		if (player_num == 1)
		{
			if (com == 0)
			{
				deck3 = new Deck();
			}

			if (com != 5)
			{
				while (!(deck3.IsEmpty()))
				{
					temp_card = deck3.Get_card();

					check = true;

					if ((temp_card.EqualTo(hands[0].Get_card1())) ||
						(temp_card.EqualTo(hands[0].Get_card2())))
					{
						check = false;
					}

					i = (hands.length - 1);
/*
					System.out.print(" " + check);
*/
					while ((check) && (i > 0))
					{
						if ((temp_card.EqualTo(hands[i].Get_card1())) ||
							(temp_card.EqualTo(hands[i].Get_card2())))
						{
							check = false;
						}

						i--;
					}
/*
					System.out.print(" " + check);				
*/	
					i = 0;

					while ((check) && (i < com))
					{
						if (temp_card.EqualTo(community[i]))
						{
							check = false;
						}
		
						i++;
					}
/*
					System.out.println(" " + check);
*/
					if (check)
					{
						community[com] = temp_card;

						wins = find_hand_prob(player_num, hands, wins, community, (com + 1), counter, deck3);
					}
				}
			}
			
			if (com == 5)
			{
				Table atable = new Table(community);

				for (i = 0; i < hands.length; i++)
				{
					hands[i].Hand_worth(atable);
				}	
/*
				for (i = 0; i < hands.length; i++)
				{
					System.out.println("Player " + i + ":" + hands[i].Get_card1().Get_value() + " " +
								hands[i].Get_card1().Get_suit() + " " +
								hands[i].Get_card2().Get_value() + " " +
								hands[i].Get_card2().Get_value() + " " +
								hands[i].Get_worth());
				}

				System.out.print("Community :");

				for (i = 0; i < 5; i++)
				{
					System.out.print(community[i].Get_value() + " " +
								community[i].Get_suit());
				}
*/
				i = 1;
			
				push = false;	
		
				while ((i < hands.length) && (hands[0].Get_worth() >= hands[i].Get_worth()))
				{
					if (hands[0].Get_worth() == hands[i].Get_worth())
					{
						push = true;
					}
					
					i++;
				}
			
			
				if (i == hands.length)
				{
					if (push)
					{
						wins += .5;
					}
				
					else
					{
						wins += 1;
					}
				}

				return (wins);
			}			
		}
		
		else
		{
			player_num--;	

			hands[player_num] = new Hand();

			deck1 = new Deck();

			while (!deck1.IsEmpty())
			{
				temp_card = deck1.Get_card();

				check = true;

				if ((temp_card.EqualTo(hands[0].Get_card1())) ||
					(temp_card.EqualTo(hands[0].Get_card2())))
				{
					check = false;
				}

				i = (hands.length - 1);

				while ((check) && (i > player_num))
				{
					if ((temp_card.EqualTo(hands[i].Get_card1())) ||
						(temp_card.EqualTo(hands[i].Get_card2())))
					{
						check = false;
					}
				}
				
				if (check)
				{
					hands[player_num].Set_card(temp_card, 1);

					deck2 = deck1.Copy();
				
					while (!deck2.IsEmpty())
					{
						temp_card = deck2.Get_card();
			
						check = true;

						if ((temp_card.EqualTo(hands[0].Get_card1())) ||
							(temp_card.EqualTo(hands[0].Get_card2())))
						{
							check = false;
						}

						i = (hands.length - 1);

						while ((check) && (i > player_num))
						{
							if ((temp_card.EqualTo(hands[i].Get_card1())) ||
								(temp_card.EqualTo(hands[i].Get_card2())))
							{
								check = false;
							}
						}
						
						if (check)
						{
							hands[player_num].Set_card(temp_card, 2);
					
							wins = find_hand_prob(player_num, hands, wins, community, com, counter, deck3);
						}
					}
				}
			}
		}

		return (wins);
	}

	// Flop_community flops either 1 or 3 cards into the
	// array of Cards, community, depending on what round
	// it is.

	public void Flop_community()
	{
		if (round == 1)
		{
			community[0] = the_deck.Get_card();
			community[1] = the_deck.Get_card();
			community[2] = the_deck.Get_card();
		}

		if (round == 2)
		{
			community[3] = the_deck.Get_card();
		}

		if (round == 3)
		{
			community[4] = the_deck.Get_card();
		}
	}

	// Update_table is a method that is run during the start
	// of each new round of betting (NOTE: When a new hand
	// starts this method is ran differently, which
	// uses the round number as its condition.), performing 
	// such tasks as running the Update_player function for 
	// each individual player, resetting bets_round (set to 1 
	// for pre-flop), etc.

	public void Update_table()
	{
		int i;

		int position;
	
		if (round == 0)
		{
			bet_level *= .5;

			Move_dealer();

			the_deck.Shuffle_deck();

			i = 1;

			while (i < 21)
			{
				if (players[((dealer + i) % 10)] != null)
				{
					if (i < 11)
					{
						players[((dealer + i) % 10)].
							Get_hand().Set_card
							(the_deck.Get_card(), 1);
					}
				
					else
					{
						players[((dealer + i) % 10)].
							Get_hand().Set_card
							(the_deck.Get_card(), 2);
					}
				}
				
				i++;
			}
	
			position = 0;

			for (i = dealer; i < (dealer + 10); i++)
			{
				if (players[(i % 10)] != null)
				{
					players[(i % 10)].Update_player(position);

					position++;
				}
			}


			i = 1;

			while (players[((dealer + i) % 10)] == null)
			{
				i++;
			}
			
			players[((dealer + i) % 10)].
				Mod_bankroll((-.5) * bet_level);

			i++;

			while (players[((dealer + i) % 10)] == null)
			{
				i++;
			}
			
			players[((dealer + i) % 10)].
				Mod_bankroll((-1) * bet_level);

			pot = ((1.5) * bet_level);

			Set_current((dealer + i) % 10);

			Move_current();
		
			Set_raiser();

			bets_round = 1;
		}
		
		else
		{
			if (round == 2)
			{
				bet_level *= 2;
			}

			i = 1;

			while (players[((dealer + i) % 10)] == null)
			{
				i++;
			}

			Set_current((dealer + i) % 10);
			
			Set_raiser();

			for (i = dealer; i < ((dealer + 10) % 10); i++)
			{
				if ((players[(i)] != null) && 
				    (players[(i)].Get_folded()))
				{
					players[(i)].Update_player();
				}
			}

			bets_round = 0;

			Flop_community();
		}
	}
	
	// Round_0 is a method that, when it is the pre-flop round
	// or, notationally, round is equal to 0, runs the table
	// through the necessary procedures, etc., for the table
	// when a computer player or Computer object is being
	// prompted for an action, which is done in the applet.
	// This method is not used when prompting a human player
	// or Human object, since this method is to get the action
	// from the computer player. Along with getting the action
	// from the computer player, the method, also, works alters
	// the history for that player.
/*
	public void Round_0()
	{
		int temp_act;
		
		double amount;

		temp_act = players[current_player].Get_action();

		if (temp_act == 0)
		{
			players[current_player].
				Get_history().
				Round_folded(round);
		}
		
		if (temp_act == 1)
		{
			players[current_player].
				Get_history().
				Add_action(1, 0);

			amount =
			(bets_round - player[current_player]. 
			Get_bets()) * bet_level;

			players[current_player].
				Mod_bankroll(-(amount));

			pot += amount;
		}
	
		if (temp_act == 2)
		{
			Set_raiser();

			players[current_player].
				Get_history().
				Add_action(2, 0);

			bets_round++;

			amount = (bets_round - player[current_player].Get_bets()) * bet_level;

			players[current_player].
				Mod_bankroll(-(amount));

			pot += amount;
		}
	
		Move_current();	
	}
*/
}
