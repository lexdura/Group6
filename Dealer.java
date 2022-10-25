package blackjack;

public class Dealer 
{
	private String name;
	private Card card1;
	private Card card2;
	private Card[] hitCards = new Card[52];
	private int total;
	private boolean bust;
	private boolean hasAce;
	
	//Constructor
	public Dealer()
	{
		name = "Dealer";
		card1 = null;
		card2 = null;
		hitCards = null;
		total = 0;
		bust = false;
		hasAce = false;
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
	
	//Checks to see if an ace is held
	public boolean hasAce()
	{
		if(card1.getValue() == 1)
			hasAce = true;
		if(card2.getValue() == 1)
			hasAce = true;
		return hasAce;
	}
	
	//Returns "Dealer"
	public String getName()
	{
		return name;
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
	
	//The logic for the dealer's turn
	public void playTurn(Deck deck)
	{
		//For traversing dealer's hit cards
		int numOfHits = 0;
		
		//Checks initial cards to see if there's an ace
		if(card1.getValue() == 1 || card2.getValue() == 1)
			hasAce = true;
		
		//Initial card total
		total = card1.getValue() + card2.getValue();
		
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
			if(hasAce)
				if(total + 10 >= 17 && total + 10 <= 21)
					return;
			
			//Picks up next card 
			hitCards[numOfHits] = deck.getNextCard();
			if(hitCards[numOfHits].getValue() == 1) //Checks to see if an ace is picked up
				hasAce = true;
			total += hitCards[numOfHits].getValue(); //Updates the total
			numOfHits++;
			
			//Checking to see if dealer busts
			if(total > 21)
			{
				bust = true;
				return;
			}
		}
	}
}