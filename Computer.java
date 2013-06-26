// James M. Piette III: Created 1/5/03 12:29 PM

// Computer.java

// Computer class is a sub-class of a player. It uses nearly the
// same data members as any generalized player uses, yet it 
// executes the abstract Get_action method differently, calling 
// upon the Computer player's personality traits and other 
// methods specific for finding what action should be taken 
// according to the given round. Computer contains data members
// besides that of the player super-class, which include a
// Personality class, Persona class, ranking, special, kind,
// etc.


public class Computer extends Player
{
	// my_Persona is a data member describing an object known
	// as Persona that is used to predict the personalities
	// of the other players at the table.
/*
	private Persona myPersona;
*/
	// rank is an integer that is used to rank the player's
	// hand with 1 being one of the best hands and 9 being
	// a terrible hand (according to the rankings done by
	// Sklansky and Malmuth in their book).

	private int rank;

	// special is an integer that describes a special case
	// that the player's current hand is distinguished by.
	// If the hand has no special case, then special is
	// set equal to -1.

	private int special;

	// kind is a data member which is represented by an
	// integer giving information about what kind of hand
	// the player has (i.e. pair, suited connector, etc.).

	private int kind;

	// Constructors
	
	// Blank constructor.

	public Computer()
	{
		super();
	}

	// A constructor given the same input as a Player object
	// would, along with a given Personality.

	public Computer(String new_name, double new_bank)
	{
		super(new_name, new_bank);
	}

	// Methods

	// Accessors

	// Get_rank returns the rank of the computer player's
	// hand.

	public int Get_rank()
	{
		return (rank);
	}

	// Get_kind returns the kind of hand the computer
	// player has.

	public int Get_kind()
	{
		return (kind);
	}
		
	// Get_special returns the special case of the computer
	// player's hand. If it does not have a special case,
	// -1 is returned.
	
	public int Get_special()
	{
		return (special);
	}

	// Modifiers

	// Update_player is a method, which is abstract in the
	// superclass, Player. It updates the computer player,
	// during the beginning of a new round (Update_player())
	// or when a new hand begins (Update_player(int position)).
	
	public void Update_player(int position)
	{
		rank = 9;

		kind = 10;

		special = -1;
	
		Ranking();

		super.Update_player(position);
	}

	public void Update_player()
	{
		super.Update_player();
	}

	// Other

	// Ranking is a method that takes the computer player's
	// hand and determines what rank the hand they have is,
	// what kind of hand it is, and whether it fits into
	// some special case or not. It uses other ranking
	// methods to help it, such as Paired, SConnector, etc.

	private void Ranking()
	{
		Paired();
		
		Connector();

		SConnector();

		OConnector();

		SOConnector();

		WSConnector();

		Ace();

		King();

		Queen();
	}

	// Paired is a method that determines whether or not
	// the player's hand has a pair of something setting
	// it's rank accordingly with a kind of 1.

	private void Paired()
	{
		if (Get_hand().Get_low().Get_value() == 
			Get_hand().Get_high().
			Get_value())
		{
			switch (Get_hand().Get_high().
				Get_value())
			{
				case (12) : 
					rank = 1;
					special = 1;
					break;

					// AA

				case (11) :
					rank = 1;
					special = 1;
					break;

					// KK
		
				case (10) :
					rank = 1;
					special = 1;
					break;

					// QQ
	
				case (9) :
					rank = 1;
					break;

					// JJ

				case (8) :
					rank = 2;
					break;

					// TT

				case (7) :
					rank = 3;
					break;

					// 99

				case (6) :
					rank = 4;
					break;
				
					// 88

				case (5) :
					rank = 5;
					break;
		
					// 77

				case (4) :
					rank = 6;
					break;
					
					// 66

				case (3) :
					rank = 6;
					break;
				
					// 55
				
				case (2) :
					rank = 7;
					break;
					
					// 44
		
				case (1) :
					rank = 7;
					break;
		
					// 33
		
				case (0) :
					rank = 7;
					break;
		
					// 22
			}

			kind = 1;
		}
	}

	// SConnector checks a hand to see if it is a
	// suited connector, which is a hand that has
	// cards with values differing by only one
	// (i.e. 87s). Hands that are suited connectors
	// are considered having a kind of 2.

	private void SConnector()
	{
		if ((Get_hand().Get_high().Get_value() ==
			(Get_hand().Get_low().
			Get_value() + 1)) &&
			(Get_hand().Suited()))
		{
			switch (Get_hand().Get_high().
				Get_value())
			{
				case (12) :
					rank = 1;
					special = 2;
					break;
	
					// AKs
				
				case (11) :
					rank = 2;
					special = 2;
					break;
			
					// KQs

				case (10) :
					rank = 3;
					break;

					// QJs

				case (9) :
					rank = 3;
					break;

					// JTs

				case (8) :
					rank = 4;
					break;
	
					// T9s

				case (7) :
					rank = 4;
					break;
		
					// 98s
	
				case (6) :
					rank = 5;
					break;

					// 87s

				case (5) :
					rank = 5;
					break;

					// 76s

				case (4) :
					rank = 5;
					break;

					// 65s

				case (3) :
					rank = 6;
					break;

					// 54s

				case (2) :
					rank = 7;
					break;
	
					// 43s

				case (1) :
					rank = 8;
					break;

					// 32s
			}

			kind = 2;
		}
	}

	// Connector is a method that checks to see if the
	// hand qualifies as a connector, a hand where the
	// greater value card is exactly one unit greater
	// than the small card. A hand with qualification
	// is considered to have a kind of 3, along with
	// a few special conditions.

	private void Connector()
	{
		if ((Get_hand().Get_high().Get_value() == 
			(Get_hand().Get_low().
			Get_value() + 1)) &&
			(!(Get_hand().Suited())))
		{
			switch (Get_hand().Get_high().
				Get_value())
			{
				case (12) :
					rank = 2;
					special = 1;
					break;

					// AK

				case (11) :
					rank = 4;
					special = 4;
					break;

					// KQ

				case (10) :
					rank = 5;
					break;

					// QJ

				case (9) :
					rank = 5;
					break;
					
					// JT					
			
				case (8) :
					rank = 7;
					break;
			
					// T9
			
				case (7) :
					rank = 7;
					break;

					// 98

				case (6) :
					rank = 8;
					break;

					// 87

				case (5) :
					rank = 8;
					break;
		
					// 76

				case (4) :
					rank = 8;
					break;
	
					// 65
				
				case (3) :
					rank = 8;
					break;
		
					// 54
	
				case (2) :
					rank = 9;
					break;
	
					// 43

				case (1) :
					rank = 9;
					break;

					// 32
			}
			
			kind = 3;
		}
	}

	// SOConnector reads a hand and determines whether
	// the hand qualifies as a suited, open connector,
	// which is a hand that is suited and has two units
	// of value between the two cards in the hand (i.e. 86s).

	private void SOConnector()
	{
		if ((Get_hand().Get_high().Get_value() ==
			(Get_hand().Get_low().
			Get_value() + 2)) &&
			(Get_hand().Suited()))
		{
			switch (Get_hand().Get_high().
				Get_value())
			{
				case (12) :
					rank = 2;
					special = 2;
					break;

					// AQs
				
				case (11) :
					rank = 3;
					break;
		
					// KJs
			
				case (10) :
					rank = 4;
					break;
	
					// QTs
	
				case (9) :
					rank = 4;
					break;
		
					// J9s

				case (8) :
					rank = 5;
					break;
	
					// T8s

				case (7) :
					rank = 5;
					break;
				
					// 97s
	
				case (6) :
					rank = 6;
					break;

					// 86s

				case (5) :
					rank = 6;
					break;
	
					// 75s
	
				case (4) :
					rank = 7;
					break;

					// 64s

				case (3) :
					rank = 7;
					break;

					// 53s

				case (2) :
					rank = 8;
					break;
					
					// 42s
			}
			
			kind = 4;
		}
	}

	// WSConnector is a method that checks a hand to
	// see if it is, one, suited, and, two, whether
	// its two cards have values that are 3 units apart,
	// known as a wide, suited connector. A hand of this
	// type has a kind value associated with it that is
	// 5.

	private void WSConnector()
	{
		if ((Get_hand().Get_high().Get_value() ==
			(Get_hand().Get_low().
			Get_value() + 2)) &&
			(Get_hand().Suited()))
		{
			switch (Get_hand().Get_high().
				Get_value())
			{
				case (12) :
					rank = 2;
					break;

					// AJs

				case (11) :
					rank = 4;
					break;

					// KTs

				case (10) :
					rank = 5;
					break;

					// Q9s

				case (9) :
					rank = 6;
					break;

					// J8s

				case (8) :
					rank = 7;
					break;

					// T7s
			
				case (7) :
					rank = 8;
					break;
				
					// 96s

				case (6) :
					rank = 8;
					break;		
	
					// 85s
		
				case (5) :
					rank = 8;
					break;

					// 74s
		
				case (4) :
					rank = 9;
					break;
						
					// 63s

				case (3) :
					rank = 9;
					break;
		
					// 52s
			}
			
			kind = 5;
		}
	}

	// OConnector takes a hand and checks to see if it
	// qualifies as an open connector that is not suited.
	// This means that the two cards in the hand have
	// a value seperation of exactly 2 between them.
	// (kind = 6)

	private void OConnector()
	{	
		if ((Get_hand().Get_high().Get_value() == 
			((Get_hand().Get_low().
			Get_value()) + 2)) &&
			(!(Get_hand().Suited())))	
		{		
			switch (Get_hand().Get_high().
				Get_value())		
			{
				case (12) : 
					rank = 3;				   
					special = 1;				   
					break;				   
					
					// AQ		
	
				case (11) :
					rank = 5;				   
					special = 5;				   
					break;				   
					
					// KJ		
			
				case (10) :
					rank = 6;				   
					break;				   
					
					// QT
		
				case (9) :
					rank = 7;				   
					break;				   

					// J9		

				case (8) :
					rank = 8;				   
					break;			  	   
	
					// T8		
	
				case (7) : 
					rank = 9;				   
					break;	
			   
					// 97	
	
				case (6) : 
					rank = 9;				   
					break;				   

					// 86
		
				case (5) :
					rank = 9;				   
					break;				   
					
					// 75		
	
				case (4) :
					rank = 9;				   
					break;				   

					// 64 		
	
				case (3) : 
					rank = 9;				   
					break;				   
					
					// 53		

				case (2) : 
					rank = 9;				   
					break;				   

					// 42		
			}		
		
			kind = 6;	
		}	
	}

	// Ace takes a hand that has an ace in it and
	// gives it the appropiate ranking, depending
	// on whether the hand is suited and what the
	// value of the smaller card is. (NOTE: This
	// function only checks hands with an ace and
	// a smaller card value of a J or less.) The
	// kind value for ace is 7.

	private void Ace()
	{	
		if ((Get_hand().Get_high().Get_value() == 12) 
			&& (Get_hand().Get_low().
			Get_value() < 10))	
		{        	
			if (!(Get_hand().Suited()))			
			{				
				switch (Get_hand().Get_low().
					Get_value())				
				{					
					case (9) : 
						rank = 4;							   
						special = 4;							  
						break;							   

						// AJ					
		
					case (8) : 
						rank = 6;							   
						special = 5;							   
						break;							   
	
						// AT					

					case (7) : 
						rank = 8;							   
						break;							   

						// A9					

					case (6) : 
						rank = 9;							   
						break;							   
						
						// Ax					
	
					case (5) : 
						rank = 9;							   
						break;							   

						// Ax					
	
					case (4) : 
						rank = 9;							   
						break;							   

						// Ax					

					case (3) : 
						rank = 9;							   
						break;							   

						// Ax					
	
					case (2) : 
						rank = 9;							   
						break;							   

						// Ax					

					case (1) : 
						rank = 9;							   
						break;							   
						
						// Ax					

					case (0) : 
						rank = 9;							   
						break;							   
						
						// Ax				
				}			
			}			
		
			if (Get_hand().Suited())			
			{				
				switch (Get_hand().Get_low().
					Get_value())				
				{						
					case (9) : 
						rank = 2;						       
						special = 2;						       
						break;						       
						
						// AJs 					

					case (8) : 
						rank = 3;							   
						break;							   

						// ATs 					
	
					case (7) : 
						rank = 5;							   
						break;							   

						// Axs					
	
					case (6) : 
						rank = 5;							   
						break;							   
						
						// Axs					
	
					case (5) :
						rank = 5;							   
						break;							   
	
						// Axs					
	
					case (4) : 
						rank = 5;							   
						break;							   

						// Axs					
	
					case (3) : 
						rank = 5;							   
						break;							   
					
						// Axs					

					case (2) : 
						rank = 5;							   
						break;							   
						
						// Axs					

					case (1) : 
						rank = 5;							   
						break;							   
						
						// Axs					

					case (0) : 
						rank = 5;							   
						break;							   
						
						// Axs				
				}			
			}		
		}
			
		kind = 7;	
	}

	// King is a method that observes a hand and looks
	// to see if the hand has a king in it and, if it
	// does, if the other card is a ten or less. The
	// method, then, ranks the hand accordingly, placing
	// it in a different rank, if the hand happens to
	// be suited, as well. The kind value is 8. 

	private void King()
	{	
		if ((Get_hand().Get_high().Get_value() == 11) 
			&& (Get_hand().Get_low().
			Get_value() < 9))	
		{		
			if (!(Get_hand().Suited()))		
			{				
				switch (Get_hand().Get_low().
					Get_value())			
				{				
					case (8) : 
						rank = 6;						   
						special = 5;						   
						break;						   

						// KT				

					case (7) : 
						rank = 8;					       
						break;						   
		
						// K9				
		
					case (6) : 
						rank = 9;						   
						break;						   
						
						// Kx				

					case (5) : 
						rank = 9;						   
						break;						   
						
						// Kx				
		
					case (4) : 
						rank = 9;						   
						break;						   
						
						// Kx				

					case (3) : 
						rank = 9;						   
						break;						   
						
						// Kx				

					case (2) : 
						rank = 9;						   
						break;						   

						// Kx				

					case (1) : 
						rank = 9;						   
						break;						   
						
						// Kx				

					case (0) : 
						rank = 9;						   
						break;						   

						// Kx			
				}
			}		
			if (Get_hand().Suited())		
			{			
				switch (Get_hand().Get_low().
					Get_value())			
				{				
					case (8) : 
						rank = 4;						   
						break;						   
					
						// KTs 				
				
					case (7) : 
						rank = 6;					       
						break;						   
						
						// K9s				
	
					case (6) : 
						rank = 7;						   
						break;						   
						
						// Kxs				
	
					case (5) : 
						rank = 7;						   
						break;						   
						
						// Kxs				

					case (4) : 
						rank = 7;						   
						break;						   
						
						// Kxs				
			
					case (3) : 
						rank = 7;						   
						break;						   
						
						// Kxs				
	
					case (2) : 
						rank = 7;						   
						break;						   
	
						// Kxs				

					case (1) : 
						rank = 7;						   
						break;						   
						
						// Kxs				
					
					case (0) : 
						rank = 7;						   
						break;						   
						
						// Kxs			
				}
			}
		}
	}

	// Queen is a function that looks at a hand and
	// determines whether or not it qualifies for
	// any hands that have a queen and another card
	// that is of value less than 8 (a ten).

	public void Queen()
	{
		if ((Get_hand().Get_high().Get_value() == 10) &&
			(Get_hand().Get_low().Get_value() < 8))
		{
			if (!Get_hand().Suited())
			{
				switch (Get_hand().Get_low().Get_value())			
				{				
					case (7) : 
						rank = 8;						   
						break;						   

						// Q9				
	
					case (6) : 
						rank = 9;					   	   
						break;						   
						
						// Qx				
	
					case (5) : 
						rank = 9;					   	   
						break;						   
						
						// Qx			 
	
					case (4) : 
						rank = 9;					   	   
						break;						   
						
						// Qx				
	
					case (3) : 
						rank = 9;					   	   
						break;						  
						
						// Qx

					case (2) : 
						rank = 9;					   	   
						break;						   

						// Qx
	
					case (1) : 
						rank = 9;					   	  
						break;						   
						
						// Qx				
	
					case (0) : 
						rank = 9;					   	   
						break;						   

						// Qx			
				}		
			}			
			if (Get_hand().Suited())		
			{			
				switch (Get_hand().Get_low().
					Get_value())			
				{				
					case (7) : 
						rank = 5;						   
						break;						   
						
						// Q9s				

					case (6) : 
						rank = 7;						  
						break;					       
						
						// Q8s				
	
					case (5) : 
						rank = 9;						   
						break;						   
						
						// Qxs				
	
					case (4) : 
						rank = 9;						   
						break;						   

						// Qxs				
	
					case (3) : 
						rank = 9;						   
						break;						   

						// Qxs				
	
					case (2) : 
						rank = 9;						   
						break;						  

						// Qxs				
	
					case (1) : 
						rank = 9;						   
						break;						   
						
						// Qxs				

					case (0) : 
						rank = 9;
						break;						   
						
						// Qxs			
				}
			}		
		}		
		
		kind = 9;	
	}	

	public int Get_action()
	{
		return 0;
	}

	// Bet_1st is a, mainly, algorithmic method that runs
	// through the current situation at the table and
	// determines what and how it will act regarding the 
	// current conditions, what kind of hand they hold,
	// and what kind of personality the computer player 
	// has. All of these things lumped together are run, 
	// determining the action (raise/reraise, call/check,
	// or fold) that the computer will choose, during the 
	// pre-flop round or, denoted notationally as round
	// = 0.	
/*
	public int Bet_1st(Table myTable)
	{
*/		
}
