package blackjack;

import java.util.Scanner;

public class Game 
{
	//Variables to be used throughout the game
	static Player user;
	static Player[] computers;
	static Deck deck = new Deck();
	static Dealer dealer = new Dealer();
			
	public static void main(String[] args)
	{
		int chips;
		int numOfPlayers;
		String name;
		//Get information from the user
		Scanner get = new Scanner(System.in);
		System.out.print("Welcome to Blakjack. Please enter your Name: ");
		name = get.nextLine();
		System.out.print("Hello " + name + ". Please enter the starting chip amount: ");
		chips = get.nextInt();
		System.out.print("Please enter the number of players that will be joining you today. (From 0-3): ");
		numOfPlayers = get.nextInt(); 
		//Error check to ensure the user entered a value within proper range
		while(numOfPlayers < 0 || numOfPlayers > 3)
		{
			System.out.print("I'm sorry. We will only accept a number between 0-3. Please enter again: ");
			numOfPlayers = get.nextInt();
		}
		
		//Populate the game with the # of players. Assign names & chip value
		user = new Player(name, chips);
		if(numOfPlayers>0)
		{
			computers = new Player[numOfPlayers];
			for(int i=0; i<numOfPlayers; i++)
				computers[i] = new Player("Computer " + (i+1), chips);
		}
		
		//Game loop
		while(user.chips > 0)
		{
			//Initial bets
			//Deal
			//Check for Blackjack
			//Play goes clockwise
			if(computers.length>0)
				for(int i = computers.length-1; i>=0; i--)
					computers[i].playTurn(deck);
			//Player plays their turn
			//If one player still left, have dealer take their turn
			//Post game. Adjust chip amounts, reset deck
		}
	}
	
	//Deals cards in a clockwise position starting with the "last" computer before ending at the dealer.
	public static void deal()
	{
		while(dealer.card2 == null) //Since the dealer is the last to receive a card, once he has both cards, the deal ends
		{
			//Accounts for there being no computers in the game
			if(computers.length>0)
				for(int i = computers.length-1; i>=0; i--)
				{
					if(computers[i].card1 == null)
						computers[i].card1 = deck.getNextCard();
					else
						computers[i].card2 = deck.getNextCard();
				}
			if(user.card1 == null)
				user.card1 = deck.getNextCard();
			else
				user.card2 = deck.getNextCard();
			if(dealer.card1 == null)
				dealer.card1 = deck.getNextCard();
			else
				dealer.card2 = deck.getNextCard();
		}
	}
}