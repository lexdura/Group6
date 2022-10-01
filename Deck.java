package blackjack;

import java.util.Random;

public class Deck 
{
	String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
	Card[] availableCards = new Card[numberOfCards];
	Card[] usedCards = new Card[numberOfCards];
	static final int numberOfCards = 52;
	static final int cardsInSuit = 13;
	
	public Deck()
	{
		int cardCounter=0;
		for(int x=0; x<suits.length; x++)
			for(int i=1; i<=cardsInSuit; i++)
			{
				availableCards[cardCounter] = new Card(suits[x], i);
				cardCounter++;
			}
		shuffle();
	}
	
	public void shuffle()
	{
		int placement;
		Random rand = new Random();
		for(int i=0; i<=numberOfCards; i++)
		{
			placement = rand.nextInt(53);
			Card temp = availableCards[i];
			availableCards[i] = availableCards[placement];
			availableCards[placement] = temp;
		}
	}
}