package blackjack;

public class Dealer 
{
	Card card1;
	Card card2;
	Card[] hitCards = new Card[20];
	
	//Constructor
	public Dealer()
	{
		Card card1 = null;
		Card card2 = null;
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
	
	//The logic for the dealer's turn
	public void playTurn()
	{
		
	}
}
