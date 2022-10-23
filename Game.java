package blackjack;

import java.util.Scanner;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game 
{
	//Variables to be used throughout the game
	static Player user = null;
	static Player[] computers = null;
	static Deck deck = new Deck();
	static Dealer dealer = new Dealer();
	static int chips;
	static int numOfPlayers;
	static String name;
			
	public static void main(String[] args)
	{
		gameGUI();
		
		//Get information from the user
		Scanner get = new Scanner(System.in);
		System.out.print("Welcome to Blackjack. Please enter your Name: ");
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
			//Ensures dealer doesn't play multiple times
			boolean dealerPlayed = false;
			
			//Initial bets
			
			deal();
			
			//Check for Blackjack
			
			//Play goes clockwise
			if(computers != null)
				for(int i = computers.length-1; i>=0; i--)
					computers[i].playTurn(deck);
			
			//Player plays their turn
			
			/*If one player still left, have dealer take their turn
			 * Checks computer busts first. Then Player.
			 */
			if(computers != null)
				for(int i = computers.length-1; i>=0; i--)
					if(computers[i].bust == false)
					{
						//If there's a computer left, dealer takes turn & kicks out of loop
						dealer.playTurn(deck);
						dealerPlayed = true;
						i=-1;
					}
			//Ensures dealer hasn't already played
			if(user.bust == false && dealerPlayed == false)
				dealer.playTurn(deck);
			
			//Post game. Adjust chip amounts, reset deck
			deck.resetDeck();
		}
	}
	
	//Gets information from the user
	public static void getInfo(JFrame f)
	{
		//Creating and framing all necessary items for the GUI
		JDialog userInfo = new JDialog(f, "Who are you?", true);
		userInfo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setSize(450, 350);
		panel.setLayout(null);
		JLabel userName = new JLabel("Player name:");
		userName.setBounds(20, 20, 100, 50);
		JLabel startingPurse = new JLabel("Starting purse amount (500+):");
		startingPurse.setBounds(20, 70, 150, 50);
		JLabel computers = new JLabel("Additional Players (0-3): ");
		computers.setBounds(20, 120, 150, 50);
		JTextField getName = new JTextField();
		getName.setBounds(200, 30, 200, 30);
		JTextField getPurse = new JTextField();
		getPurse.setBounds(200, 80, 200, 30);
		JTextField getComputers = new JTextField();
		getComputers.setBounds(200, 130, 200, 30);
		JButton start = new JButton("START GAME");
		start.setBounds(250, 190, 150, 50);
		JButton cancel = new JButton("CANCEL");
		cancel.setBounds(20, 190, 150, 50);
		
		//What the buttons do when clicked
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Checks to make sure the user entered a valid amount in each field
				if(Integer.parseInt(getPurse.getText()) > 499 && 
				   Integer.parseInt(getComputers.getText()) >= 0 && 
				   Integer.parseInt(getComputers.getText()) <= 3)
				{
					//Sets variables to user input
					name = getName.getText();
					chips = Integer.parseInt(getPurse.getText());
					numOfPlayers = Integer.parseInt(getComputers.getText());
					userInfo.dispose();
				}
			}
		});
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Exits the JDialog box
				userInfo.dispose();
			}
		});
		
		//Piecing the GUI together
		userInfo.add(panel);
		panel.add(userName);
		panel.add(getName);
		panel.add(startingPurse);
		panel.add(getPurse);
		panel.add(computers);
		panel.add(getComputers);
		panel.add(start);
		panel.add(cancel);
		
		//Displays the GUI
		userInfo.setSize(panel.getSize());
		userInfo.setVisible(true);
	}
	
	//Creates the GUI
	public static void gameGUI()
	{
		JFrame game = new JFrame("G6 - Blackjack");
		getInfo(game);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(1200, 800);
        JPanel panel = new JPanel();
        JLabel userName = new JLabel(name);
        JLabel purse = new JLabel(Integer.toString(chips));
        JLabel computers = new JLabel(Integer.toString(numOfPlayers));
        
        panel.add(userName);
        panel.add(purse);
        panel.add(computers);
        game.add(panel);
        
        
        game.setVisible(true);
	}
	
	//Deals cards in a clockwise position starting with the "last" computer before ending at the dealer.
	public static void deal()
	{
		while(dealer.card2 == null) //Since the dealer is the last to receive a card, once he has both cards, the deal ends
		{
			//Accounts for there being no computers in the game
			if(computers != null)
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