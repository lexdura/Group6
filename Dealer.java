package blackjack;

import java.util.ArrayList;

public class Dealer 
{
	private String name;
	private ArrayList<Card> dealerCards;
	private int total;
	private boolean bust;
	private boolean hasAce;
	
	//Constructor
	public Dealer()
	{
		name = "Dealer";
		dealerCards = new ArrayList<Card>();
		total = 0;
		bust = false;
		hasAce = false;
	}
	
	//Returns dealerCard of position x
	public Card getDealerCard(int x)
	{
		return dealerCards.get(x);
	}

	//Adds Card c to dealerCards
	public void addCard(Card c)
	{
		dealerCards.add(c);
	}
	
	//Returns total
	public int getTotal()
	{
		total = 0;
		for(Card i : dealerCards)
			total += i.getValue();
		return total;
	}
	
	//Checks to see if an ace is held
	public boolean hasAce()
	{
		for(int i=0; i < dealerCards.size(); i++)
		{
			if(dealerCards.get(i).getValue() == 1)
				hasAce = true;
		}
		return hasAce;
	}
	
	//Returns "Dealer"
	public String getName()
	{
		return name;
	}
	
	//Returns size of dealerCards
	public int handSize()
	{
		return dealerCards.size();
	}
	
	//Returns bust value
	public boolean isBusted()
	{
		return bust;
	}
	
	//Resets relevant variables between hands
	public void reset()
	{
		dealerCards.clear();
		bust = false;
		hasAce = false;
	}
	
	//The logic for the dealer's turn
	public void playTurn(Deck deck)
	{		
		//Checks initial cards to see if there's an ace
		hasAce();
		
		//Logic loop
		while(true)
		{
			//Dealer is hard-stopped at 17+
			if(getTotal() >= 17)
				return;
				
			/*If dealer has an ace AND if counting the ace as 11
			 * brings the total to 17-21, kick out. Otherwise,
			 * play will continue.
			 */
			if(hasAce)
				if(getTotal() + 10 >= 17 && getTotal() + 10 <= 21)
					return;
				
			//Picks up next card
			dealerCards.add(deck.getNextCard());
			
			//Checks if the picked up card was an ace
			if(dealerCards.get(dealerCards.size()-1).getValue() == 1)
				hasAce = true;
				
			//Checking to see if dealer busts
			if(getTotal() > 21)
			{
				bust = true;
				return;
			}
		}
	}
}