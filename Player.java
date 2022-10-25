package blackjack;

public class Player 
{
	private String name;
	private int chips;
	private int total;
	private Card card1;
	private Card card2;
	private Card[] hitCards = new Card[20];
	private boolean bust;
	private boolean hasAce;
	
	//Constructor
	public Player(String chosenName, int startingChips)
	{
		name = chosenName;
		chips = startingChips;
		total = 0;
		card1 = null;
		card2 = null;
		hitCards = null;
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
	
	//Sets card1 to incoming card
	public void setCard1(Card c)
	{
		card1 = c;
	}
	
	//Sets card2 to incoming card
	public void setCard2(Card c)
	{
		card2 = c;
	}
	
	//Returns desired hitCard (assumes exclusion of 0-starting-count)
	public Card getHitCard(int x)
	{
		return hitCards[x+1];
	}
	
	//Returns bust value
	public boolean isBusted()
	{
		return bust;
	}
	
	//Checks to see if an ace is held
	public boolean hasAce()
	{
		if(card1.getValue() == 1)
			hasAce = true;
		if(card2.getValue() == 1)
			hasAce = true;
		return hasAce;
	}
	
	//Resets relevant variables between hands
	public void reset()
	{
		total = 0;
		card1 = null;
		card2 = null;
		hitCards = null;
		bust = false;
		hasAce = false;
	}
	
	/* 
	 * NEEDS TO BE UPDATED TO COMPUTER'S LOGIC
	 */
	//The logic for the Computer's turn
	public void playTurn(Deck deck)
	{
		/*
		//Checks initial cards to see if there's an ace
		if(card1.value == 1 || card2.value == 1)
			hasAce = true;
		
		//Initial card total
		total = card1.value + card2.value;
		
		//Logic loop
		while(true)
		{
			//Dealer is hard-stopped at 17+
			if(total >= 17)
				return;
				
			/*If dealer has an ace AND if counting the ace as 11
			 * brings the total to 17-21, kick out. Otherwise,
			 * play will continue.
			 */
			/*if(hasAce)
				if(total + 10 >= 17 && total + 10 <= 21)
					return;
				
			//Picks up next card 
			for(int i=0; i<hitCards.length; i++)
				if(hitCards[i] == null) //Ensures addition, not replacement
				{
					hitCards[i] = deck.getNextCard();
					if(hitCards[i].value == 1) //Checks to see if an ace is picked up
						hasAce = true;
					total += hitCards[i].value; //Updates the total
					i = hitCards.length; //Kicks out of the loop as soon as a new card is added
				}
				
			//Checking to see if dealer busts
			if(total > 21)
			{
				bust = true;
				return;
			}
		}*/
	}
}