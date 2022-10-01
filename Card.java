package blackjack;

public class Card 
{
	String suit;
	int value;
	
	//Constructor
	public Card(String incSuit, int incValue)
	{
		suit = incSuit;
		value = incValue;
	}
	
	//Returns the numerical value of the card
	public int getValue()
	{
		return value;
	}
	
	//Returns the name of the Card based on it's value
	public String getName()
	{
		if(value == 1)
			return "Ace of " + suit;
		if(value >1 && value <11)
			return value + " of " + suit;
		if(value == 11)
			return "Jack of " + suit;
		if(value == 12)
			return "Queen of " + suit;
		if(value == 13)
			return "King of " + suit;
		//If value is not between 1-13, then throws an error message
		return "Error. Value is not equivalent to a known card type.";
	}
}