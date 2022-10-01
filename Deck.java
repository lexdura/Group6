package blackjack;

import java.util.Random;

public class Deck 
{
	//Variables to be used throughout
	String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
	Card[] availableCards = new Card[numberOfCards];
	Card[] usedCards = new Card[numberOfCards];
	static final int numberOfCards = 52;
	static final int cardsInSuit = 13;
	
	//Constructor
	public Deck()
	{
		//Populates the deck with each card of the suit
		int cardCounter=0;
		for(int x=0; x<suits.length; x++)
			for(int i=1; i<=cardsInSuit; i++)
			{
				availableCards[cardCounter] = new Card(suits[x], i);
				cardCounter++;
			}
		//Shuffles the deck immediately after instantiation for play
		shuffle();
	}
	
	//Shuffles the deck
	public void shuffle()
	{
		int placement;
		Random rand = new Random();
		//Will traverse the deck from beginning to end using index i
		for(int i=0; i<=numberOfCards; i++)
		{
			//selects a random # between 0-52
			placement = rand.nextInt(53);
			
			//swaps card i with whatever random card was chosen
			Card temp = availableCards[i];
			availableCards[i] = availableCards[placement];
			availableCards[placement] = temp;
		}
	}
	
	//Obtains the next available card in the deck
	public Card getNextCard()
	{
		//Searches the deck for the next available card
		int i=0;
		while(availableCards[i] == null)
			i++;
		
		//When selected, removes from the available cards and puts it into the used cards
		usedCards[i] = availableCards[i];
		availableCards[i] = null;
		
		//Returns selected card
		return usedCards[i];
	}
	
	//Resets and reshuffles the deck
	public void resetDeck()
	{
		//Traverses the used cards, removing them from the used and adding to the available
		int i=0;
		while(usedCards[i] != null)
		{
			availableCards[i] = usedCards[i];
			usedCards[i] = null;
			i++;
		}
		shuffle();
	}
}