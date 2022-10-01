package blackjack;

public class Card 
{
	String suit;
	int value;
	
	public Card(String incSuit, int incValue)
	{
		suit = incSuit;
		value = incValue;
	}
	public int getValue()
	{
		return value;
	}
	public String getSuit()
	{
		return suit;
	}
}