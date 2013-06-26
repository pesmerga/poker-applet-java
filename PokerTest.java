// James M. Piette III: Created 2/11/04 10:11 PM

// PokerTest.java

// Used for debugging and testing.

public class PokerTest
{
	public static void main(String args[])
	{
		Computer one = new Computer("Dog", 100);

		System.out.println(one.Get_name() + 
				   " " + 
				   one.Get_bankroll());

		Computer two = new Computer("Cat", 125);

		System.out.println(two.Get_name() + 
				   " " + 
				   two.Get_bankroll());

		Computer three = new Computer("Rat", 75);

		System.out.println(three.Get_name() + 
				   " " + 
				   three.Get_bankroll());

		Computer four = new Computer("Bug", 50);

		System.out.println(four.Get_name() + 
				   " " + 
				   four.Get_bankroll());
		
		Computer five = new Computer("Bird", 230);

		System.out.println(five.Get_name() +
				   " " +
				   five.Get_bankroll());
		
		Player[] animals = new Player[10];

		animals[0] = one;
	
		animals[1] = two;

		animals[2] = three;
	
		animals[3] = four;

		animals[4] = five;

		Table poker = new Table(1, animals, 2);

		poker.Update_table();

		System.out.println(poker.Get_pot() + " " +
				   poker.Get_current().Get_name() + " " +
				   poker.Get_last().Get_name() + " " +
				   poker.Get_dealer().Get_name());

		System.out.println(poker.Get_current().Get_hand().Get_card1().Get_value() + " " +
				   poker.Get_current().Get_hand().Get_card1().Get_suit() + " " +
				   poker.Get_current().Get_hand().Get_card2().Get_value() + " " +
				   poker.Get_current().Get_hand().Get_card2().Get_suit());

		System.out.println(poker.Get_current().Get_rank() + " " +
				   poker.Get_current().Get_kind() + " " +
				   poker.Get_current().Get_special());

		double[][] two_peeps;		

		two_peeps = poker.look_hand_prob(2);
	}
}

