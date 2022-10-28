package blackjack;

public class Player 
{
	private String name;
	private int chips;
	private int total;
	private Card card1;
	private Card card2;
	private Card[] hitCards;
	private boolean bust;
	private boolean hasAce;
	private static final Card fillerCard = new Card("Joker", 0);
	
	//Constructor
	public Player(String chosenName, int startingChips)
	{
		name = chosenName;
		chips = startingChips;
		total = 0;
		card1 = fillerCard;
		card2 = fillerCard;
		hitCards = new Card[10];
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
	
	//Returns total
	public int getTotal()
	{
		return total;
	}
	
	//Adjusts chip amount
	public void adjustChips(int x)
	{
		chips += x;
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
		card1 = fillerCard;
		card2 = fillerCard;
		hitCards = new Card[10];
		bust = false;
		hasAce = false;
	}
	
	//The logic for the Computer's turn
	public void playTurn(Deck deck)
	{
		//Checks initial cards to see if there's an ace
		if(card1.getValue() == 1 || card2.getValue() == 1)
			hasAce = true;
		
		//Initial card total
		total = card1.getValue() + card2.getValue();
		
		//Logic loop
		while(true)
		{
			//Computer is hard-stopped at 17+
			if(total >= 17)
				return;
				
			/*If computer has an ace AND if counting the ace as 11
			 * brings the total to 17-21, kick out. Otherwise,
			 * play will continue.
			 */
			if(hasAce)
				if(total + 10 >= 17 && total + 10 <= 21)
					return;
				
			//Picks up next card 
			for(int i=0; i<hitCards.length; i++)
				if(hitCards[i] == null) //Ensures addition, not replacement
				{
					hitCards[i] = deck.getNextCard();
					if(hitCards[i].getValue() == 1) //Checks to see if an ace is picked up
						hasAce = true;
					total += hitCards[i].getValue(); //Updates the total
					i = hitCards.length; //Kicks out of the loop as soon as a new card is added
				}
				
			//Checking to see if computer busts
			if(total > 21)
			{
				bust = true;
				return;
			}
		}
	}
}