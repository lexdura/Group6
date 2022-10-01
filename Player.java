package blackjack;

public class Player 
{
	String name;
	int chips;
	Card card1;
	Card card2;
	Card[] hitCards = new Card[20];
	boolean bust;
	
	//Constructor
	public Player(String chosenName, int startingChips)
	{
		name = chosenName;
		chips = startingChips;
		card1 = null;
		card2 = null;
		bust = false;
	}
	
	//Returns the name of the player
	public String getName()
	{
		return name;
	}
	
	//Returns the current value of their chips
	public int getChips()
	{
		return chips;
	}
	
	//Returns card1
	public Card getCard1()
	{
		return card1;
	}
		
	//Returns card2
	public Card getCard2()
	{
		return card2;
	}
	
	//The logic for playing a computer playing a turn
	public void playTurn(Deck deck)
	{
		boolean hasAce = false;
		if(card1.value == 1 || card2.value == 1)
			hasAce = true;
		while(!bust)
		{
			
		}
	}
}