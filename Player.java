package blackjack;

import java.util.ArrayList;

public class Player 
{
	private String name;
	private int chips;
	private int total;
	private ArrayList<Card> playerCards;
	private boolean bust;
	private boolean hasAce;
	
	//Constructor
	public Player(String chosenName, int startingChips)
	{
		name = chosenName;
		chips = startingChips;
		total = 0;
		playerCards = new ArrayList<Card>();
		bust = false;
		hasAce = false;
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
	
	//Returns playerCard of position x
	public Card getPlayerCard(int x)
	{
		return playerCards.get(x);
	}
	
	//Returns total
	public int getTotal()
	{
		total = 0;
		for(Card i : playerCards)
			total += i.getValue();
		return total;
	}
	
	//Adjusts chip amount
	public void adjustChips(int x)
	{
		chips += x;
	}
	
	//Adds card c to PlayerCards
	public void addCard(Card c)
	{
		playerCards.add(c);
	}
	
	//Returns bust value
	public boolean isBusted()
	{
		return bust;
	}
	
	//Checks to see if an ace is held
	public boolean hasAce()
	{
		for(int i=0; i < playerCards.size(); i++)
		{
			if(playerCards.get(i).getValue() == 1)
				hasAce = true;
		}
		return hasAce;
	}
	
	//Resets relevant variables between hands
	public void reset()
	{
		playerCards.clear();
		bust = false;
		hasAce = false;
	}
	
	//The logic for the Computer's turn
	public void playTurn(Deck deck)
	{
		//Checks initial cards to see if there's an ace
		hasAce();
		
		//Logic loop
		while(true)
		{
			//Computer is hard-stopped at 17+
			if(getTotal() >= 17)
				return;
				
			/*If computer has an ace AND if counting the ace as 11
			 * brings the total to 17-21, kick out. Otherwise,
			 * play will continue.
			 */
			if(hasAce)
				if(getTotal() + 10 >= 17 && getTotal() + 10 <= 21)
					return;
				
			//Picks up next card
			playerCards.add(deck.getNextCard());
			
			//Checks if the picked up card was an ace
			if(playerCards.get(playerCards.size()-1).getValue() == 1)
				hasAce = true;
				
			//Checking to see if computer busts
			if(getTotal() > 21)
			{
				bust = true;
				return;
			}
		}
	}
}