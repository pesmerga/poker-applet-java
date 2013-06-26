// TPanel v0.5

// Created 5/24/04 1:46 PM

// By James M. Piette III

// TPanel.java

// The TPanel is the center JPanel for the PokerPlaya
// applet. This panel lies in the center of the mainPanel 
// and displays the table and other information about
// the table (pot, etc.). TPanel has a paint function 
// that updates the current state of the table after
// any action is taken at the table.

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TPanel extends JPanel
{
	private double pot;

	private int dealer;

	private double[] bets_this_round;

	// Constructor

	public TPanel(double pot, int dealer, double[] bets)
	{
		this.pot = pot;

		this.dealer = dealer;

		bets_this_round = bets;

		repaint();
	}

	// Accessor

	// Get_pot returns the value of the pot
	// according to TPanel.

	public double Get_pot()
	{
		return (pot);
	}

	// Other

	// Paints the graphics onto the panel.

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Draws a poker table.

		g.setColor(Color.green);

		g.fillOval(5, 15, 215, 215);
		g.fillOval(325, 15, 215, 215);
		g.fillRect(120, 15, 320, 215);

		// On top of the table, the amount of money
		// in the pot is painted on.

		g.setColor(Color.black);
		g.setFont(new Font("Dialog", Font.PLAIN, 24));
		g.drawString("$" + pot + "0", 100, 125);

		g.setFont(new Font("Dialog", Font.BOLD, 16));

		// Place the dealer chip in front of the correct
		// player.

		switch (dealer)
		{
			// Player 1.

			case (0):
				g.setColor(Color.white);
				g.fillOval(14, 138, 20, 20);
				
				g.setColor(Color.black);
				g.drawOval(14, 138, 20, 20);

				g.drawString("D", 20, 154);
				break;

			// Player 2.

			case (1):
				g.setColor(Color.white);
				g.fillOval(38, 44, 20, 20);

				g.setColor(Color.black);
				g.drawOval(38, 44, 20, 20);

				g.drawString("D", 44, 60);
				break;

			// Player 3.

			case (2):
				g.setColor(Color.white);
				g.fillOval(144, 18, 20, 20);

				g.setColor(Color.black);
				g.drawOval(144, 18, 20, 20);
	
				g.drawString("D", 148, 34);
				break;

			// Player 4.

			case (3):
				g.setColor(Color.white);
				g.fillOval(294, 18, 20, 20);

				g.setColor(Color.black);
				g.drawOval(294, 18, 20, 20);
	
				g.drawString("D", 298, 34);
				break;

			// Player 5.

			case (4):
				g.setColor(Color.white);
				g.fillOval(434, 18, 20, 20);
				
				g.setColor(Color.black);
				g.drawOval(434, 18, 20, 20);
			
				g.drawString("D", 438, 34);
				break;

			// Player 6.

			case (5):
				g.setColor(Color.white);
				g.fillOval(514, 90, 20, 20);

				g.setColor(Color.black);
				g.drawOval(514, 90, 20, 20);
	
				g.drawString("D", 518, 106);
				break;

 			// Player 7.

			case (6):
				g.setColor(Color.white);
				g.fillOval(474, 188, 20, 20);

				g.setColor(Color.black);
				g.drawOval(474, 188, 20, 20);
	
				g.drawString("D", 478, 204);
				break;
			
			// Player 8.

			case (7):
				g.setColor(Color.white);
				g.fillOval(364, 204, 20, 20);

				g.setColor(Color.black);
				g.drawOval(364, 204, 20, 20);
	
				g.drawString("D", 368, 220);
				break;
			
			// Player 9.

			case (8):
				g.setColor(Color.white);
				g.fillOval(208, 204, 20, 20);

				g.setColor(Color.black);
				g.drawOval(208, 204, 20, 20);
	
				g.drawString("D", 212, 220);
				break;

			// Player 10.

			case (9):
				g.setColor(Color.white);
				g.fillOval(80, 200, 20, 20);

				g.setColor(Color.black);
				g.drawOval(80, 200, 20, 20);
	
				g.drawString("D", 84, 216);
				break;
		}

		// The following switch statements are all the same,
		// except for the coordinates the data in which things
		// are determined to be drawn or not; their purpose
		// is to display the number of bets a player has made
		// in a round.

		// Player 1.

		switch ((int)bets_this_round[0])
		{
			// Since there are no breaks in cases 4, 3,
			// and 2, if the bets_this_round happens to be,
			// say, 3, the first three blue betting chips are
			// displayed.

			case (4):
				g.setColor(Color.blue);
				g.fillOval(40, 162, 10, 10);

				g.setColor(Color.black);
				g.drawOval(40, 162, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(30, 162, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(30, 162, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(40, 172, 10, 10);

				g.setColor(Color.black);
				g.drawOval(40, 172, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(30, 172, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(30, 172, 10, 10);
				break;
		}

		// This is the condition where the player has only
		// entered the small blind, which is half of a bet,
		// so this bet is symbolized using a red chip.

		if (bets_this_round[0] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(30, 172, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(30, 172, 10, 10);
		}
		
		// Player 2.

		switch ((int)bets_this_round[1])
		{
			case (4):
				g.setColor(Color.blue);
				g.fillOval(36, 82, 10, 10);

				g.setColor(Color.black);
				g.drawOval(36, 82, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(26, 82, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(26, 82, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(36, 72, 10, 10);

				g.setColor(Color.black);
				g.drawOval(36, 72, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(26, 72, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(26, 72, 10, 10);
				break;
		}

		if (bets_this_round[1] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(26, 72, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(26, 72, 10, 10);
		}

		// Player 3.

		switch ((int)bets_this_round[2])
		{
			case (4):
				g.setColor(Color.blue);
				g.fillOval(124, 30, 10, 10);

				g.setColor(Color.black);
				g.drawOval(124, 30, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(114, 30, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(114, 30, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(124, 20, 10, 10);

				g.setColor(Color.black);
				g.drawOval(124, 20, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(114, 20, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(114, 20, 10, 10);
				break;
		}

		if (bets_this_round[2] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(114, 20, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(114, 20, 10, 10);
		}

		// Player 4.

		switch ((int)bets_this_round[3])
		{
			case (4):
				g.setColor(Color.blue);
				g.fillOval(274, 30, 10, 10);

				g.setColor(Color.black);
				g.drawOval(274, 30, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(264, 30, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(264, 30, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(274, 20, 10, 10);

				g.setColor(Color.black);
				g.drawOval(274, 20, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(264, 20, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(264, 20, 10, 10);
				break;
		}

		if (bets_this_round[3] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(264, 20, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(264, 20, 10, 10);
		}

		// Player 5.

		switch ((int)bets_this_round[4])
		{
			case (4):
				g.setColor(Color.blue);
				g.fillOval(414, 30, 10, 10);

				g.setColor(Color.black);
				g.drawOval(414, 30, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(404, 30, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(404, 30, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(414, 20, 10, 10);

				g.setColor(Color.black);
				g.drawOval(414, 20, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(404, 20, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(404, 20, 10, 10);
				break;
		}

		if (bets_this_round[4] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(404, 20, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(404, 20, 10, 10);
		}	

		// Player 6.

		switch ((int)bets_this_round[5])
		{
			case (4):
				g.setColor(Color.blue);
				g.fillOval(494, 66, 10, 10);

				g.setColor(Color.black);
				g.drawOval(494, 66, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(504, 66, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(504, 66, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(494, 76, 10, 10);

				g.setColor(Color.black);
				g.drawOval(494, 76, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(504, 76, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(504, 76, 10, 10);
				break;
		}

		if (bets_this_round[5] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(504, 76, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(504, 76, 10, 10);
		}

		// Player 7.

		switch ((int)bets_this_round[6])
		{
			case (4):
				g.setColor(Color.blue);
				g.fillOval(494, 166, 10, 10);

				g.setColor(Color.black);
				g.drawOval(494, 166, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(504, 166, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(504, 166, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(494, 176, 10, 10);

				g.setColor(Color.black);
				g.drawOval(494, 176, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(504, 176, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(504, 176, 10, 10);
				break;
		}

		if (bets_this_round[6] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(504, 176, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(504, 176, 10, 10);
		}

		// Player 8.

		switch ((int)bets_this_round[7])
		{
			case (4):
				g.setColor(Color.blue);
				g.fillOval(400, 202, 10, 10);

				g.setColor(Color.black);
				g.drawOval(400, 202, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(390, 202, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(390, 202, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(400, 212, 10, 10);

				g.setColor(Color.black);
				g.drawOval(400, 212, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(390, 212, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(390, 212, 10, 10);
				break;
		}

		if (bets_this_round[7] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(390, 212, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(390, 212, 10, 10);
		}

		// Player 9.

		switch ((int)bets_this_round[8])
		{
			case (4):
				g.setColor(Color.blue);
				g.fillOval(242, 202, 10, 10);

				g.setColor(Color.black);
				g.drawOval(242, 202, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(232, 202, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(232, 202, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(242, 212, 10, 10);

				g.setColor(Color.black);
				g.drawOval(242, 212, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(232, 212, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(232, 212, 10, 10);
				break;
		}

		if (bets_this_round[8] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(232, 212, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(232, 212, 10, 10);
		}

		// Player 10.

		switch ((int)bets_this_round[9])
		{
			case (4):
				g.setColor(Color.blue);
				g.fillOval(116, 202, 10, 10);

				g.setColor(Color.black);
				g.drawOval(116, 202, 10, 10);

			case (3):
				g.setColor(Color.blue);
				g.fillOval(106, 202, 10, 10);
		
				g.setColor(Color.black);
				g.drawOval(106, 202, 10, 10);
		
			case (2):
				g.setColor(Color.blue);
				g.fillOval(116, 212, 10, 10);

				g.setColor(Color.black);
				g.drawOval(116, 212, 10, 10);
			
			case (1):
				g.setColor(Color.blue);
				g.fillOval(106, 212, 10, 10);
	
				g.setColor(Color.black);
				g.drawOval(106, 212, 10, 10);
				break;
		}

		if (bets_this_round[9] == .5)
		{
			g.setColor(Color.red);
			g.fillOval(106, 212, 10, 10);
		
			g.setColor(Color.black);
			g.drawOval(106, 212, 10, 10);
		}
	}

	// Update updates the center panel, so that the
	// number produced as the amount of money in the
	// pot is correct.

	public void Update(double pot, int dealer, double[] bets)
	{
		this.pot = pot;

		this.dealer = dealer;

		bets_this_round = bets;
	}
}
