// PokerPlaya v0.5

// Created 5/18/04 3:30 PM

// By James M. Piette III

// PokerPlaya.java

// PokerPlaya is an applet-based program that takes
// a given situation(s) in a game of Texas Hold'em
// Poker, given limits, and produces an action(s)
// based on the given information and makes what the
// the program considers as the best or optimal
// action for you, the player, to make.

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PokerPlaya extends JApplet implements ActionListener
{
	// playerPanel is an array of JPanels that contains
	// the specifics for a certain player, whether it
	// be the user's playa or an opponent.

	private JPanel[] playerPanel = new JPanel[10];

	// The following JPanels are the core panels of
	// the applet. Each of the leftPanel, rightPanel,
	// bottomPanel, and topPanel holds all the
	// JPanels for each player. myPanel is the panel
	// that appears in the top-left corner of the
	// applet and displays the users hand, once
	// inputted into the textfields and the
	// suggested move the player should choose.
	// comPanel is the JPanel, where the user
	// inputs after each round, if necessary, the
	// cards appearing in the community. statPanel
	// gives information about the game, such as
	// what round the game is currently in, what
	// player is currently under consideration for
	// making a move, and how many hands have been 
	// played. As for the mainPanel, it holds all of 
	// the other panels within it, being the JPanel
	// encompassing all.

	private JPanel  mainPanel = new JPanel(),
			leftPanel = new JPanel(),
			rightPanel = new JPanel(),
			bottomPanel = new JPanel(),
			topPanel = new JPanel(),
			myPanel = new JPanel(),
			comPanel = new JPanel(),
			statPanel = new JPanel();

	// tablePanel is a special type of panel known
	// as a TPanel. It's sole purpose is to display
	// graphics of a table, bets that have been made
	// in the round, and how much money is currently
	// in the pot.

	private TPanel  tablePanel;
	
	// playerName and playerBank are two arrays of
	// labels that are contained within individual
	// playerPanel's. Each one of these labels will
	// keep updated information about who has
	// folded, player names, and bankrolls.
	//
	// (NOTE: For now, each player begins with a
	// bankroll of $0.00, which could be altered in
	// the future.)

	private JLabel  playerName[] = new JLabel[10],
			playerBank[] = new JLabel[10];

	// bet, call, and fold JButton arrays are items
	// within each playerPanel. These buttons are
	// often disabled to help show what player's
	// have made moves and how is currently up to
	// make a move. After either one of these
	// three buttons have been pressed, the table
	// updates and prepares for what should occur
	// next.

	private JButton[] bet = new JButton[10],
			  call = new JButton[10],
			  fold = new JButton[10];	

	// confirm is a large button that is used for
	// several different tasks throughout the
	// program. Particullary, confirm is used to
	// read TextField's, after information and
	// data has been typed into them.
	//
	// (NOTE: This button sits in the top-right
	// corner of the applet and is rather large,
	// but still proves it's purpose, so, unless
	// horribly aestetically pleasing, the button
	// works perfectly.)

	private JButton	confirm = new JButton("Confirm");

	// card1 and card2 are TextField's use to
	// represent the user's hand that they
	// are dealt. compMove is a non-editable
	// text field that displays the suggested
	// move by the program. com1 through com5
	// are TextFields in the panel, comPanel.
	// During the appropiate round, these text
	// fields become able for input, so that
	// the cards dealt in the community may
	// be added. gameNum represents the number
	// of games have been played, since the
	// program began. roundNum displays what
	// round of play the current game is in.
	// Finally, currPlay displays the current
	// player being prompted to make a move.

	private JTextField
			card1 = new JTextField(4),
			card2 = new JTextField(4),
			compMove = new JTextField(4),
			com1 = new JTextField(4),
			com2 = new JTextField(4),
			com3 = new JTextField(4),
			com4 = new JTextField(4),
			com5 = new JTextField(4),
			gameNum = new JTextField(3),
			roundNum = new JTextField(3),
			currPlay = new JTextField(3);

	// table is an instance of the class PlayaTable.
	// It represents the table that the game is
	// being played on and contains nearly all
	// functions and data members necessary to play
	// the game.

	private PlayaTable table;

	// human is an integer expressing what player #
	// the user is playing at.
	//
	// (NOTE: This integer does NOT represent the
	// index in the array of players contained
	// in the data member, table, that represents
	// the user. That number is one less human.)

	private int human;

	// Intializes the applet for the beginning of
	// play.

	public void init()
	{
		// Displays a welcome message.

		JOptionPane.showMessageDialog(	this,
						"Welcome to Poker Playa v0.5!",
						"Poker Playa v0.5",
						JOptionPane.PLAIN_MESSAGE);
		
		double bet_level = 1;

		human = 0;

		boolean tempErr = true;

		String tempString;

		// Prompts the user for the bet level that the
		// game will be played at. If the user inputs
		// a number that is not appropiate for a bet
		// level, an error message is received.

		while (tempErr)
		{
			try
			{
			tempString = 
			JOptionPane.showInputDialog("Please enter the bet level.");
			
			Double tempDouble = new Double(tempString);

			bet_level = tempDouble.doubleValue();
	
			if (bet_level <= 0)
			{
				throw new ArithmeticException
				("Bet level must be non-zero and (+).");
			}

			tempErr = false;
			} catch (NumberFormatException error1)	{
				JOptionPane.showMessageDialog(this,
				"The bet level must be a number.",
				"Poker Playa v0.5",
				JOptionPane.ERROR_MESSAGE);	}
			  catch (ArithmeticException error2)	{
				JOptionPane.showMessageDialog(this,
				error2.getMessage(),
				"Poker Playa v0.5",
				JOptionPane.ERROR_MESSAGE);	}
		}

		tempErr = true;

		// Prompts the user for where the user is
		// sitting at the table. This number is in
		// respect to the dealer beginning at the
		// 10th seat or Player 9. Once again, if
		// the user incorrectly inputs their starting
		// position, an error message is given.
		//
		// (NOTE: The number to be inputted is
		// from 1 to 10, NOT 0 through 9.)

		while (tempErr)
		{
			try
			{
			tempString = 
			JOptionPane.showInputDialog("What seat are you beginning at?");

			Integer tempInteger = new Integer(tempString);
			
			human = tempInteger.intValue();

			if ((human < 1) || (human > 10))
			{
				throw new ArithmeticException
				("You may only choose a seat between 1 and 10.");
			}
			
			tempErr = false;
			} catch (NumberFormatException error1)	{
				JOptionPane.showMessageDialog(this,
				"Your seat must be an integer.",
				"Poker Playa v0.5",
				JOptionPane.ERROR_MESSAGE);	}
			  catch (ArithmeticException error2)	{
				JOptionPane.showMessageDialog(this,
				error2.getMessage(),
				"Poker Playa v0.5",
				JOptionPane.ERROR_MESSAGE);	}
		}

		// With the information given by the user,
		// an instance of the PlayaTable object is
		// created with the appropiate data and
		// the table is updated to allow for a game
		// to begin. Also, a new instance of TPanel
		// is created and set to be the tablePanel.

		table = new PlayaTable(bet_level, human);

		table.Update_table();

		tablePanel = new TPanel(table.Get_pot(), 
					table.Get_deal_index(), 
					table.Get_playa_bets());

		// The JLabel array, playerName, is ran
		// through. Each label is set to it's default	
		// setting and, if the playerName is the
		// label for the user, that label is set
		// appropiately for the user.

		for (int i = 0; i < 10; i++)
		{
			if (i != (human - 1))
			{
				playerName[i] = new JLabel("Player " + (i + 1));
			}
			
			else
			{
				playerName[i] = new JLabel("You");
			}
		}

		// This for loop prompts the arrays playerPanel,
		// bet, call, and fold to new instance of their
		// class type. Along with creating these new
		// objects, each playerPanel is set to the
		// BoxLayout, so that the appropiate containers
		// can be added in a vertical manner in the
		// panel, starting with the playerName,
		// playerBank (which is intialized), bet, call,
		// and fold. After these are added, each is
		// disabled and given and ActionListener.

		for (int i = 0; i < 10; i++)
		{
			playerPanel[i] = new JPanel();

			bet[i] = new JButton("Bet/Raise");
			call[i] = new JButton("Call/Check");
			fold[i] = new JButton("Fold");

			playerPanel[i].setLayout(new BoxLayout(playerPanel[i],
							       BoxLayout.Y_AXIS));

			playerPanel[i].add(playerName[i]);

			playerBank[i] = new JLabel("$" + table.Get_playa(i).Get_bankroll() +
						   "0");
			playerPanel[i].add(playerBank[i]);

			playerPanel[i].add(bet[i]);
			playerPanel[i].add(call[i]);
			playerPanel[i].add(fold[i]);

			bet[i].addActionListener(this);
			call[i].addActionListener(this);
			fold[i].addActionListener(this);

			bet[i].setEnabled(false);
			call[i].setEnabled(false);
			fold[i].setEnabled(false);
		}

		// mainPanel is given a border and set with the
		// layout known as BorderLayout.

		mainPanel.setBorder(BorderFactory.createTitledBorder("Poker Playa v0.5"));
		mainPanel.setLayout(new BorderLayout());

		// Each of the panel that will contain the
		// playerPanel's is set to a layout, with the
		// panel appearing on the left and right of the
		// screen with a vertical BoxLayout and the
		// bottom and top panels with a GridLayout for
		// the playerPanels and the two of the other
		// for non-playerPanel's used in the applet.

		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		topPanel.setLayout(new GridLayout(1, 5, 1, 1));

		bottomPanel.setLayout(new GridLayout(1, 5, 1, 1));

		// myPanel is intialized with a title to
		// allow the user to know what the panel is for,
		// along with a set layout, GridLayout. Once
		// intialized, card1, card2, and compMove
		// text fields are added into the panel.

		myPanel.setBorder(BorderFactory.createTitledBorder("Your Playa"));
		myPanel.setLayout(new GridLayout(3, 2, 1, 1));
		myPanel.add(new JLabel("Card 1:"));
		myPanel.add(card1);
		myPanel.add(new JLabel("Card 2:"));
		myPanel.add(card2);
		myPanel.add(new JLabel("Playa move:"));
		myPanel.add(compMove);
		
		// compMove is disabled, so that the user can
		// not change any text that is placed in this
		// field.
		//
		// (NOTE: This field is perminantly disabled
		// for the entire duration of the program's
		// execution.)

		compMove.setEditable(false);

		// comPanel is titled and given a GridLayout, so
		// that the five TextField's, com1, com2, com3,
		// com4, and com5 can be added in a grid fashion.
		// Once added, the five text fields are disabled.

		comPanel.setBorder(BorderFactory.createTitledBorder("Community Cards"));
		comPanel.setLayout(new GridLayout(3, 4, 1, 1));
		comPanel.add(new JLabel("1st:"));
		comPanel.add(com1);
		comPanel.add(new JLabel("4th:"));
		comPanel.add(com4);
		comPanel.add(new JLabel("2nd:"));
		comPanel.add(com2);
		comPanel.add(new JLabel("5th:"));
		comPanel.add(com5);
		comPanel.add(new JLabel("3rd:"));
		comPanel.add(com3);

		com1.setEditable(false);
		com2.setEditable(false);
		com3.setEditable(false);
		com4.setEditable(false);
		com5.setEditable(false);	
	
		// statPanel is given it's intial properties,
		// such as border and layout, then the text fields
		// gameNum, roundNum, and currPlay are added to it.

		statPanel.setBorder(BorderFactory.createTitledBorder("Status"));
		statPanel.setLayout(new GridLayout(3, 2, 1, 1));
		statPanel.add(new JLabel("# games:"));
		statPanel.add(gameNum);
		statPanel.add(new JLabel("Round:"));
		statPanel.add(roundNum);
		statPanel.add(new JLabel("Player:"));
		statPanel.add(currPlay);

		// gameNum is set to be 0, since no game has been
		// played as of yet.

		gameNum.setEditable(false);
		gameNum.setText("0");

		// roundNum is set to display "Pre-flop".

		roundNum.setEditable(false);
		roundNum.setText("Pre-flop");
		
		// currPlay is given whatever player is will first
		// be prompted to give an action, whether it be the
		// user or an opponent.

		currPlay.setEditable(false);

		if (table.Get_curr_index() == (human - 1))
		{
			currPlay.setText("You");
		}
	
		else
		{
			tempString = new String("Player " +
						(table.Get_curr_index() + 1));
			currPlay.setText(tempString);
		}

		confirm.addActionListener(this);

		// The playerPanel's are added to left and right
		// panels so that the leftPanel has Player 1 and
		// Player 2, while rightPanel has Player 6 and
		// Player 7.
		
		leftPanel.add(playerPanel[1]);
		leftPanel.add(playerPanel[0]);

		rightPanel.add(playerPanel[5]);
		rightPanel.add(playerPanel[6]);

		// myPanel is added to the farthest left of the
		// topPanel and the button, confirm, to the
		// farthest right, with player's 3, 4, and 5 in
		// the middle.

		topPanel.add(myPanel);
		topPanel.add(playerPanel[2]);
		topPanel.add(playerPanel[3]);
		topPanel.add(playerPanel[4]);
		topPanel.add(confirm);

		// comPanel is placed first into bottomPanel
		// making it appear in the bottom-left corner
		// of the applet, along with statPanel placed
		// in the bottom-right corner, so that the
		// player's 10, 9, and 8 are in between.

		bottomPanel.add(comPanel);
		bottomPanel.add(playerPanel[9]);
		bottomPanel.add(playerPanel[8]);
		bottomPanel.add(playerPanel[7]);
		bottomPanel.add(statPanel);

		// Since mainPanel was set to have a BorderLayout,
		// each of the panels in the applet are added to
		// mainPanel depending on their cardinal direction
		// or center.

		mainPanel.add(leftPanel, "West");
		mainPanel.add(rightPanel, "East");
		mainPanel.add(topPanel, "North");
		mainPanel.add(bottomPanel, "South");
		mainPanel.add(tablePanel, "Center");

		// mainPanel is added to the applet.

		getContentPane().add(mainPanel);

		// The user is prompted to enter their hand in the
		// fields card1 and card2 inside myPanel.

		JOptionPane.showMessageDialog(this,
			"To begin, enter the cards that are in your hand.",
			"Poker Playa v0.5",
			JOptionPane.PLAIN_MESSAGE);
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
		boolean done = false;

		// The event where the confirm button is pressed.

		if (e.getSource() == confirm)
		{
			// Depending on what round of play it is, the
			// program runs specific code for each individual
			// situation.

			if (table.Get_round() == 0)
			{
				// Since this is the code for the event
				// where it is the pre-flop round, the
				// program executes a series of "if"
				// statements to determine whether the
				// user inputted the cards correctly. If
				// the user did everything right, the
				// computer converts the text into the
				// appropiate cards that represent the
				// user's hand.

				String tempString = card1.getText();
	
				// The user gave text that was not in the
				// correct form for a card.

				if (tempString.length() != 2)
				{
					throw new ArithmeticException
					("A card must be in the form value then suit.");
				}

				PlayaCard tempCard = Convert_card(tempString);
					
				// The user inputted the correct form, but
				// used the wrong character or the wrong
				// syntax.
	
				if ((tempCard.Get_value() == -1) ||
				    (tempCard.Get_suit() == -1))
				{
					throw new ArithmeticException
					("The value of a card can be A, K, Q, J, T, or 9-2" +
					 "and the rank of a card can be c, d, h, or s");
				}
	
				// Set card1 to be the user's first card.

				table.Get_human().Get_hand().Set_card(tempCard, 1);

				tempString = card2.getText();
	
				if (tempString.length() != 2)
				{
					throw new ArithmeticException
					("A card must be in the form value then suit.");
				}

				tempCard = Convert_card(tempString);
	
				if ((tempCard.Get_value() == -1) ||
				    (tempCard.Get_suit() == -1))
				{
					throw new ArithmeticException
					("The value of a card can be A, K, Q, J, T, or 9-2" +
					 " and the rank of a card can be c, d, h, or s");
				}

				// Set card2 to be the user's second card.

				table.Get_human().Get_hand().Set_card(tempCard, 2);
		
				// The user gave two of the same cards.

				if (table.Get_human().Get_hand().Get_card1().EqualTo(tempCard))
				{
					throw new ArithmeticException
					("You can not have two of the same cards in your hand!");
				}

				// The first player to act in the round has their
				// button enabled, while the confirm button and
				// card1 and card2 text fields are disabled.
		
				bet[table.Get_curr_index()].setEnabled(true);
				call[table.Get_curr_index()].setEnabled(true);
				fold[table.Get_curr_index()].setEnabled(true);
	
				confirm.setEnabled(false);

				card1.setEditable(false);
				card2.setEditable(false);
			}

			if (table.Get_round() == 1)
			{
				// When the game is in the beginning of the
				// post-flop round, the program reads the
				// first three community card text fields,
				// which represent the flop cards.

				String tempString = com1.getText();

				if (tempString.length() != 2)
				{
					throw new ArithmeticException
					("A card must be in the form value then suit.");
				}

				PlayaCard tempCard = Convert_card(tempString);
	
				if ((tempCard.Get_value() == -1) ||
				    (tempCard.Get_suit() == -1))
				{
					throw new ArithmeticException
					("The value of a card can be A, K, Q, J, T, or 9-2" +
					 "and the rank of a card can be c, d, h, or s");
				}

				PlayaCard[] tempComm = new PlayaCard[3];

				tempComm[0] = tempCard;

				tempString = com2.getText();

				if (tempString.length() != 2)
				{
					throw new ArithmeticException
					("A card must be in the form value then suit.");
				}

				tempCard = Convert_card(tempString);
	
				if ((tempCard.Get_value() == -1) ||
				    (tempCard.Get_suit() == -1))
				{
					throw new ArithmeticException
					("The value of a card can be A, K, Q, J, T, or 9-2" +
					 "and the rank of a card can be c, d, h, or s");
				}

				tempComm[1] = tempCard;

				tempString = com3.getText();

				if (tempString.length() != 2)
				{
					throw new ArithmeticException
					("A card must be in the form value then suit.");
				}

				tempCard = Convert_card(tempString);
	
				if ((tempCard.Get_value() == -1) ||
				    (tempCard.Get_suit() == -1))
				{
					throw new ArithmeticException
					("The value of a card can be A, K, Q, J, T, or 9-2" +
					 "and the rank of a card can be c, d, h, or s");
				}

				tempComm[2] = tempCard;

				if ((tempComm[0].EqualTo(tempComm[1])) ||
				    (tempComm[0].EqualTo(tempComm[2])) ||
				    (tempComm[1].EqualTo(tempComm[2])))
				{
					throw new ArithmeticException
					("No community cards may be the same!");
				}

				if ((tempComm[0].EqualTo(table.Get_human().Get_hand().Get_card1())) ||
				    (tempComm[0].EqualTo(table.Get_human().Get_hand().Get_card2())) ||
				    (tempComm[1].EqualTo(table.Get_human().Get_hand().Get_card1())) ||
				    (tempComm[1].EqualTo(table.Get_human().Get_hand().Get_card2())) ||
				    (tempComm[2].EqualTo(table.Get_human().Get_hand().Get_card1())) ||
				    (tempComm[2].EqualTo(table.Get_human().Get_hand().Get_card2())))
				{
					throw new ArithmeticException
					("There are no two of the same cards in a deck!");
				}

				// If the input given is correct and no error is
				// handled by the program, the table is updated
				// with the array of cards found from the text
				// fields and the first person to make an action
				// in the next round is prompted to begin.

				table.Update_table(tempComm);

				bet[table.Get_curr_index()].setEnabled(true);
				call[table.Get_curr_index()].setEnabled(true);
				fold[table.Get_curr_index()].setEnabled(true);

				if (table.Get_curr_index() != (human - 1))
				{
					currPlay.setText("Player " + 
						(table.Get_curr_index() + 1));
				}

				else
				{
					currPlay.setText("You");
				}

				confirm.setEnabled(false);

				com1.setEditable(false);
				com2.setEditable(false);
				com3.setEditable(false);
			}

			if ((table.Get_round() == 2) ||
			    (table.Get_round() == 3))
			{
				// Since the turn and the river both involve
				// the showing of one card, they can, both,
				// be incorporated using the same code.
				// If it is round 2, then the computer reads
				// the input given from the turn card 
				// TextField. If it is round 3, then the
				// computer reads the river card TextField.

				String tempString;

				if (table.Get_round() == 2)
				{
					tempString = com4.getText();
				}

				else
				{
					tempString = com5.getText();
				}

				if (tempString.length() != 2)
				{
					throw new ArithmeticException
					("A card must be in the form value then suit.");
				}

				PlayaCard tempCard = Convert_card(tempString);
	
				if ((tempCard.Get_value() == -1) ||
				    (tempCard.Get_suit() == -1))
				{
					throw new ArithmeticException
					("The value of a card can be A, K, Q, J, T, or 9-2" +
					 "and the rank of a card can be c, d, h, or s");
				}

				if ((tempCard.EqualTo(table.Get_com(0))) ||
				    (tempCard.EqualTo(table.Get_com(1))) ||
				    (tempCard.EqualTo(table.Get_com(2))) ||
				    ((table.Get_round() == 3) &&
				     (tempCard.EqualTo(table.Get_com(3)))))
				{
					throw new ArithmeticException
					("No two community cards can be the same!");
				}

				if ((tempCard.EqualTo(table.Get_human().Get_hand().Get_card1())) ||
				    (tempCard.EqualTo(table.Get_human().Get_hand().Get_card2())))
				{
					throw new ArithmeticException
					("There are never two of the same cards in a deck!");
				}

				// If no errors occur, the table is updated
				// with either the Turn or the River card,
				// depending on the round, and the first player
				// to act in the round has their buttons
				// enabled to allow them to declare an action.

				table.Update_table(tempCard);

				bet[table.Get_curr_index()].setEnabled(true);
				call[table.Get_curr_index()].setEnabled(true);
				fold[table.Get_curr_index()].setEnabled(true);

				if (table.Get_curr_index() != (human - 1))
				{
					currPlay.setText("Player " + 
						(table.Get_curr_index() + 1));
				}

				else
				{
					currPlay.setText("You");
				}

				if (table.Get_round() == 2)
				{
					com4.setEditable(false);
				}

				else
				{
					com5.setEditable(false);
				}

				confirm.setEnabled(false);
			}

			if (table.Get_round() == 4)
			{
				// Round 4 is the showdown round, where players
				// reveal or muck their cards to decide who
				// is the winner. The only code preformed by
				// the program in this case, if the confirm	
				// button is pressed, is to disable the confirm
				// button and set the boolean variable done
				// equal to true. By doing this, the program
				// runs the PlayaTable method, Check. 
				//
				// (NOTE: In the case that the confirm button
				// is pressed, during round 4, there must have
				// been only one defining winning hand. If 
				// there was a push involved or more than one
				// winning hand, then the program would not 
				// reach this point.)

				confirm.setEnabled(false);

				done = true;
			}

			// Update the center panel and repaint the
			// appropiate graphics to display correct
			// information.

			tablePanel.Update(table.Get_pot(), 
					  table.Get_deal_index(),
					  table.Get_playa_bets());

			tablePanel.repaint();
		}

		// Since bet, call, and fold JButtons work the same
		// for every player, each button is put through the
		// same code, no matter whose the player. To do this,
		// a for loop is used, so that each button is
		// observed to see if any of them had been pressed.
		// If the action was preformed by the player at index
		// j, than, no matter what button was pressed, the
		// Check function in the class PlayaTable is ran to
		// determine if any dramatic changes should be
		// performed.
		// 
		// (NOTE: The condition of done equalling true has to
		// do with round 4 and more information about it
		// is described in earlier comments.)	

		for (int j = 0; j < 10; j++)
		{
			if ((e.getSource() == bet[j]) ||
			    (e.getSource() == call[j]) ||
			    (e.getSource() == fold[j]) ||
			    (done))
			{
				// Depending on which button was pressed, the
				// current player has the appropiate action set
				// and the method Update is ran on the index
				// of the player.

				if (e.getSource() == bet[j])
				{
					table.Get_current().Set_action(2);

					Update(j);
				}
			
				if (e.getSource() == call[j])
				{
					table.Get_current().Set_action(1);

					Update(j);
				}
				
				if (e.getSource() == fold[j])
				{
					table.Get_current().Set_action(0);

					Update(j);
				}
				
				// Check returns an integer and, if that integer
				// is either 1, 2, or 3, that means that an event
				// has occurred, whether it be a new round, 
				// a winner, or a showdown.

				int tempInt = table.Check();

				// If there is only one person left at the table,
				// than there is a winner and a new game needs to
				// begin.
				//
				// (NOTE: there may have been multiple people left
				// in the case of a showdown, but the winning hand
				// was found.)

				if (tempInt == 1)
				{
					double tempDouble = tablePanel.Get_pot();
		
					// Displays a message to the user of who
					// won the hand and how much that player
					// won.

					if (table.Get_curr_index() != (human - 1))
					{
						JOptionPane.showMessageDialog(this,
						"Congratulations Player " +
						(table.Get_curr_index() + 1) + "!" +
						" You just won $" + tempDouble + "0!",
						"Poker Playa v0.5",
						JOptionPane.PLAIN_MESSAGE);
					}
		
					else
					{
						JOptionPane.showMessageDialog(this,
						"Congratulations user!" +
						" You just won $" + tempDouble + "0!",
						"Poker Playa v0.5",
						JOptionPane.PLAIN_MESSAGE);
					}
		
					New_game();

					done = false;
				}

				// The last round has ended and a new round has
				// begun.
		
				if (tempInt == 2)
				{
					// Since at the beginning of any round,
					// some information must be inputted in
					// a text field, so all JButton's, besides
					// confirm need to be disabled.

					for (int i = 0; i < 10; i++)
					{
						bet[i].setEnabled(false);
						call[i].setEnabled(false);
						fold[i].setEnabled(false);
					}
		
					// In the case of the game entering
					// into round 1 or the post-flop,
					// the user must input the flop cards
					// into the appropiate text fields.

					if (table.Get_round() == 1)
					{
						roundNum.setText("Post-flop");
		
						JOptionPane.showMessageDialog(this,
							"Enter the flop cards.",
							"Poker Playa v0.5",
							JOptionPane.PLAIN_MESSAGE);

						com1.setEditable(true);
						com2.setEditable(true);
						com3.setEditable(true);
					}
		
					// The game is entering the Turn or
					// round 2, so the player is prompted
					// to enter the turn card.

					if (table.Get_round() == 2)
					{
						roundNum.setText("The Turn");
				
						JOptionPane.showMessageDialog(this,
							"Enter the turn card.",
							"Poker Playa v0.5",
							JOptionPane.PLAIN_MESSAGE);
		
						com4.setEditable(true);
					}

					// Since it is round 3 or the river,
					// the player is to enter the river
					// card into the river text field.

					if (table.Get_round() == 3)
					{
						roundNum.setText("The River");
				
						JOptionPane.showMessageDialog(this,
							"Enter the river card.",
							"Poker Playa v0.5",
							JOptionPane.PLAIN_MESSAGE);
		
						com5.setEditable(true);
					}
	
					confirm.setEnabled(true);
				}

				// There are no more "official" rounds of play,
				// so players must muck or reveal their cards
				// to determine the winner of the hand.

				if (tempInt == 3)
				{
					roundNum.setText("Showdown");

					for (int h = 0; h < 10; h++)
					{
						bet[h].setEnabled(false);
						call[h].setEnabled(false);
						fold[h].setEnabled(false);
					}

					// The program determines who is the first
					// person that is to reveal their cards.

					int i = 1;

					int dealer = table.Get_deal_index();

					int start;

					// If there were no bets in the final round,
					// than the first person to show their hand
					// is the player closest to the left of the
					// dealer that is still in play.

					if (table.Get_bets() == 0)
					{
						while ((table.Get_playa((dealer + i) % 10).Get_folded()) ||
						       (table.Get_playa((dealer + i) % 10) == null))
						{
							i++;
						}

						start = ((dealer + i) % 10);
					}

					// The player to show their hand first is the
					// last person to bet in the previous round.

					else
					{
						start = table.Get_last_index();
					}

					table.Set_current(start);

					// Displays to the user that a showdown must
					// occur and the number of players in it.

					JOptionPane.showMessageDialog(this,
						"There are " + table.Not_folded() +
						" players, so there must be a showdown.",
						"Poker Playa v0.5",
						JOptionPane.PLAIN_MESSAGE);

					String tempString;

					PlayaCard tempCard;

					int playerCount = 0;

					PlayaHand[] hands = new PlayaHand[table.Not_folded()];

					int stillIn = 0;

					// The following code runs through the remaining
					// players determining, first, to see if the
					// player in question mucked or showed their cards.
					// If the answer is no to mucking, then, if the
					// player in question is not the user's player,
					// the user is asked to input the cards that the
					// player showed.

					while (playerCount < hands.length)
					{
						// Displays an option screen with the option
						// to say "no", the player in question did
						// not muck or "yes", the player did muck.

						String tempMessage;

						if (table.Get_curr_index() == (human - 1))
						{
							tempMessage =
								"Did you muck your cards?";
						}

						else
						{
							tempMessage =
								"Did Player " +
								(table.Get_curr_index() + 1) +
								" muck their cards?";
						}

						int tempOption = 
							JOptionPane.showConfirmDialog(this,
								tempMessage,
								"Poker Playa v0.5",
								JOptionPane.YES_NO_OPTION);

						// If the player did not muck their cards and
						// are not the human player, prompt the user
						// to input the first card in the player's hand
						// followed by the second outputting error
						// messages for incorrect syntax or option(s).

						if (tempOption == JOptionPane.NO_OPTION)
						{
						if (!(table.Get_curr_index() == (human - 1)))
						{
							hands[stillIn] = new PlayaHand();

							for (int k = 0; k < 2; k++)
							{
							boolean tempErr = true;
				
						 	while (tempErr)
							{
							try
							{
							tempString =
							JOptionPane.showInputDialog("Please enter " +
								"card " + (k + 1) + " for Player " +
								(table.Get_curr_index() + 1) + ".");

							if (tempString.length() != 2)
							{
								throw new ArithmeticException
								("A card must be in the form value then suit.");
							}

							tempCard = Convert_card(tempString);
			
							if ((tempCard.Get_value() == -1) ||
							    (tempCard.Get_suit() == -1))
							{
								throw new ArithmeticException
								("The value of a card can be A, K, Q, J, T, or " +
								 "9-2 and the rank of a card can be c, d, h, or s");
							}

							for (int h = 0; h < 5; h++)
							{
								if (tempCard.EqualTo(table.Get_com(h)))
								{
									throw new ArithmeticException
									("You can not have the same card " +
									 "as a card in the community.");
								}
							}

							for (int h = 0; h < stillIn; h++)
							{
								if ((hands[h].Get_card1().EqualTo(tempCard)) ||
								    (hands[h].Get_card2().EqualTo(tempCard)))
								{
									throw new ArithmeticException
									("This player can not have a card " +
									 "that another player has.");
								}
							}
	
							// Set whatever card was given to the hand
							// for the player that the card was meant
							// to be for.

							hands[stillIn].Set_card(tempCard, (k + 1));

							tempErr = false;
							} catch (ArithmeticException error1)	{
								JOptionPane.showMessageDialog(this,
								error1.getMessage(),
								"Poker Playa v0.5",
								JOptionPane.ERROR_MESSAGE);	}
							}
							}
						}

						// If the player was, merely, the user's player,
						// take the hand from the You object and set it
						// at the index represented by the order in which
						// the player(s) showed their hands.

						else
						{
							hands[stillIn] = table.Get_human().Get_hand();
						}

						// Move the current player to the next and add
						// one to the number of players who have showed their
						// hand(s), stillIn.

						table.Move_current();
		
						stillIn++;
						}

						// If the player chose to muck their cards, their
						// action is set to 0, or that the player has
						// folded.

						else
						{
						table.Get_current().Set_action(0);

						table.Update_play();
						}

						// Increment the number of players that have been
						// considered.

						playerCount++;
					}

					// For the number of people that showed their hands,
					// create an array of double representing hand worth
					// and make it with length of the number of people
					// still in. Once created, determine the hand's
					// worth or value.

					double[] handsWorth = new double[table.Not_folded()];

					for (int k = 0; k < table.Not_folded(); k++)
					{
						handsWorth[k] = hands[k].Hand_worth(table);
					}

					double max = handsWorth[0];

					int winner = 0;

					int numWinners = 0;

					// Determine which player(s) had the winning hand.

					for (int k = 1; k < table.Not_folded(); k++)
					{
						if (handsWorth[k] > max)
						{
							max = handsWorth[k];
		
							winner = k;
						}
					}

					// Search to find how many players had that
					// winning hand or hand_worth.

					for (int k = 0; k < table.Not_folded(); k++)
					{
						if (handsWorth[k] == max)
						{
							numWinners++;
						}
					}

					// The following code is applied to the situation where
					// only one winner exists, or, there is one player who
					// has a better hand than all other players, when the
					// showdown round occurs.

					if (numWinners == 1)
					{
						// Find the player that has the winning hand.

						i = 1;		

						while ((table.Get_playa((dealer + i) % 10).Get_folded()) ||
						       (table.Get_playa((dealer + i) % 10) == null))
						{
							i++;
						}
					
						table.Set_current((dealer + i) % 10);
	
						i = 0;
	
						while (!(i == winner))
						{
							table.Move_current();
	
							i++;
						}
	
							
						winner = table.Get_curr_index();
	
						table.Move_current();

						// Set every other player, besides the winner,
						// to be seen as having folded.
	
						while (winner != table.Get_curr_index())
						{
							table.Get_current().Set_action(0);
	
							table.Update_play();
						}
	
						if (table.Get_curr_index() == (human - 1))
						{
							currPlay.setText("You");
						}
					
						else
						{	
							currPlay.setText("Player " + 
									 (table.Get_curr_index() + 1));
						}

						// To return who the winner is and how much
						// they won, the user is asked to hit the
						// confirm button, which will run the method
						// Check, a method of the PlayaTable class.
						// 
						// (NOTE: This is the same code that is used
						// to determine if all players at the table
						// have folded.)

						JOptionPane.showMessageDialog(this,
							"To continue, hit confirm.",
							"Poker Playa v0.5",
							JOptionPane.PLAIN_MESSAGE);
	
						confirm.setEnabled(true);
					}

					// Condition in which there are multiple winners and a push needs
					// to occur.

					else
					{
						// Create an array of Playa's that will
						// represent the winners and find which
						// players are those winners.

						Playa[] winners = new Playa[numWinners];

						i = 1;

						while ((table.Get_playa((dealer + i) % 10).Get_folded()) ||
						       (table.Get_playa((dealer + i) % 10) == null))
						{
							i++;
						}

						table.Set_current((dealer + i) % 10);

						int counter = 0;

						for (int k = 0; k < table.Not_folded(); k++)
						{
							if (handsWorth[k] == max)
							{
								winners[counter] = table.Get_current();

								counter++;
							}
		
							table.Move_current();
						}

						// Display a message telling the user that
						// there was a tie or a push, since two or
						// more players had the same winning hand.

						JOptionPane.showMessageDialog(this,
							"There is a push! Players will " +
							"split the $" + table.Get_pot() +
							"0 evenly amongst themselves.",
							"Poker Playa v0.5",
							JOptionPane.PLAIN_MESSAGE);

						// The pot is split evenly and given to each
						// of the winners.

						double winnings = (table.Get_pot()) / ((double)numWinners);

						for (int k = 0; k < numWinners; k++)
						{
							winners[k].Mod_bankroll(winnings);
						}

						// Prepare the table for a new game by running
						// the New_Play method and run New_game, the
						// applet's method of preparing itself.

						table.New_play();

						New_game();
					}
				}
			}
		}	
		} catch (ArithmeticException error1)	{
		  	JOptionPane.showMessageDialog(this,
			error1.getMessage(),
			"Poker Playa v0.5",
			JOptionPane.ERROR_MESSAGE);	}
	}

	// Convert_card is a method that takes a String that
	// represents a card and converts that String into
	// creating the appropiate, corresponding PlayaCard.

	private PlayaCard Convert_card(String card)
	{
		int value;
	
		int suit;

		// Find the value of the card given.

		switch (card.charAt(0))
		{
			case ('A'):
				value = 12;
				break;			

			case ('K'):
				value = 11;
				break;

			case ('Q'):
				value = 10;
				break;

			case ('J'):
				value = 9;
				break;

			case ('T'):
				value = 8;
				break;
		
			case ('9'):
				value = 7;
				break;

			case ('8'):
				value = 6;
				break;
			
			case ('7'):
				value = 5;
				break;

			case ('6'):
				value = 4;
				break;

			case ('5'):
				value = 3;
				break;
	
			case ('4'):
				value = 2;
				break;
		
			case ('3'):
				value = 1;
				break;
		
			case ('2'):
				value = 0;
				break;

			default:
				value = -1;
				break;
		}

		// Find the suit of the card.

		switch (card.charAt(1))
		{
			case ('c'):
				suit = 0;
				break;
	
			case ('d'):
				suit = 1;
				break;
	
			case ('h'):
				suit = 2;
				break;
	
			case ('s'):
				suit = 3;
				break;

			default:
				suit = -1;
				break;
		}

		return (new PlayaCard(value, suit));
	}

	// New_game is a method that readies the applet for
	// a new game by resetting the necessary variables,
	// updating information from the previous game(s),
	// and repainting the panels.

	public void New_game()
	{
		// Update the table to prepare for the new
		// game.

		table.Update_table();
	
		// Set all of the player's label to their
		// original form and retrieve the new
		// bankrolls of every player. Also, disable
		// all the action buttons to prep for input.

		for (int i = 0; i < 10; i++)
		{
			if (i != (human - 1))
			{
				playerName[i].setText("Player " + (i + 1));
			}
		
			else
			{
				playerName[i].setText("You");
			}
	
			playerBank[i].setText("$" + table.Get_playa(i).Get_bankroll() + "0");
	
			bet[i].setEnabled(false);
			call[i].setEnabled(false);
			fold[i].setEnabled(false);
		}
		
		// Since community and hand cards might have
		// been inputted during the last game, reset
		// of them to be empty and enable card1 and
		// card2 for input.

		confirm.setEnabled(true);
		
		card1.setEditable(true);
		card1.setText("");
		card2.setEditable(true);
		card2.setText("");
		
		com1.setText("");
		com2.setText("");
		com3.setText("");
		com4.setText("");
		com5.setText("");

		// Update the first player to act in the new
		// hand, number of hands the program has been
		// used to play through, and set the round to
		// be round 0 or pre-flop.

		gameNum.setText(table.Get_plays() + "");
		
		roundNum.setText("Pre-flop");
			
		if (table.Get_curr_index() == (human - 1))
		{
			currPlay.setText("You");
		}
		
		else
		{
			currPlay.setText("Player " + (table.Get_curr_index() + 1));
		}
		
		// Repaint the center panel.

		tablePanel.Update(table.Get_pot(),
			 	  table.Get_deal_index(),
				  table.Get_playa_bets());
		
		tablePanel.repaint();
		
		JOptionPane.showMessageDialog(this,
			"Enter the cards that are in your hand.",
			"Poker Playa v0.5",
			JOptionPane.PLAIN_MESSAGE);
	}

	// Update is a method used by the applet to update
	// information after an action is preformed, whether
	// it be bet, call, or fold by any of the players
	// still in the game. The method needs an integer
	// that represents the index of the player preformed
	// the action.

	public void Update(int index)
	{
		// A player has been given a new action, so
		// the PlayaTable function, Update_play, is
		// run to ready the new changes.

		table.Update_play();

		// Updates the bankrolls for the player who
		// just made a new action or updates a player
		// to be seen as folded if that player did so.

		if (table.Get_current().Get_action() != 0)
		{
			playerBank[index].setText("$" + 
				table.Get_playa(index).Get_bankroll() + "0");
		}

		else
		{
			if (index != (human - 1))
			{
				playerName[index].setText("Player " + 
						(index + 1) + " (folded)");
			}
				
			else
			{
				playerName[index].setText("You (folded)");
			}
		}

		// Disable the action buttons of the player
		// being updated and ready or enable the
		// buttons for the next player. However, if
		// four bets have been made during the round,
		// there can be no more betting, so the
		// bet button is disabled.

		bet[index].setEnabled(false);
		call[index].setEnabled(false);
		fold[index].setEnabled(false);

		bet[table.Get_curr_index()].setEnabled(true);
		call[table.Get_curr_index()].setEnabled(true);
		fold[table.Get_curr_index()].setEnabled(true);
		
		if (table.Get_bets() == 4)
		{
			bet[table.Get_curr_index()].setEnabled(false);
		}

		// Repaint the tablePanel.

		tablePanel.Update(table.Get_pot(), 
				  table.Get_deal_index(),
				  table.Get_playa_bets());

		tablePanel.repaint();
	
		// Display what player is, now, the current player.

		if (table.Get_curr_index() != (human - 1))
		{
			currPlay.setText("Player " + (table.Get_curr_index() + 1));
		}

		else
		{
			currPlay.setText("You");
		}
	}	
}
