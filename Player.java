package blackjack;

public class Player 
{
	String name;
	int chips;
	
	public Player(String chosenName, int startingChips)
	{
		name = chosenName;
		chips = startingChips;
	}
	public String getName()
	{
		return name;
	}
	public int getChips()
	{
		return chips;
	}
}