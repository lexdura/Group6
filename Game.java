package blackjack;

import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game 
{
	//Variables to be used throughout the game
	static Player user = null;
	static ArrayList<Player> computers = new ArrayList<Player>();
	static Deck deck = new Deck();
	static Dealer dealer = new Dealer();
	static int initialPurse;
	static int numOfPlayers;
	static int bet;
	static int doubleDownAmount;
	static boolean dealerPlayed = false;
	static boolean doubledDown;
	static boolean userPlayed;
	static String name;
	static JFrame game = new JFrame("G6 - Blackjack");
	static JPanel gamePanel = new JPanel();
			
	public static void main(String[] args)
	{
		gameGUI();
	}
	
	//Gets information from the user
	public static void getInfo(JFrame f)
	{
		//Creating and framing all necessary items for the GUI
		JDialog userInfo = new JDialog(f, "Who are you?", true);
		userInfo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		userInfo.setLocationRelativeTo(null);
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
					initialPurse = Integer.parseInt(getPurse.getText());
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
		
		//Frame for game
		getInfo(game);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(1200, 800);
        game.setLocationRelativeTo(null);
		game.setLayout(null);
		gamePanel.setSize(game.getSize());
		gamePanel.setLayout(null);
		game.add(gamePanel);
		
		//Populate the game with the # of players. Assign names & chip value
		user = new Player(name, initialPurse);
		if(numOfPlayers>0)
		{
			for(int i=0; i<numOfPlayers; i++)
				computers.add(new Player ("Computer" + (i+1), initialPurse));
		}
		
		//panel for dealer
		JPanel dealerPanel = new JPanel();
		dealerPanel.setBackground(Color.gray);
		dealerPanel.setBounds(450,0,300,240);
		dealerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		JPanel dealerCardPanel = new JPanel();
		dealerCardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		JLabel dealerName = new JLabel("Dealer");
		dealerPanel.add(dealerName);
		dealerPanel.add(dealerCardPanel);
		gamePanel.add(dealerPanel);
		
		//panel for player
        JPanel userPanel = new JPanel();
		userPanel.setBackground(Color.red);
		userPanel.setBounds(0,314,300,240);
		userPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 10));
		JPanel userCardPanel = new JPanel();
		userCardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel userName = new JLabel(name);
        JLabel purse = new JLabel(Integer.toString(user.getChips()));
        userPanel.add(userName);
        userPanel.add(purse);
        userPanel.add(userCardPanel);
        gamePanel.add(userPanel);
		
		//panel for additional player 1
		if(numOfPlayers > 0)
		{
			JPanel comp1Panel = new JPanel();
			comp1Panel.setBackground(Color.LIGHT_GRAY);
			comp1Panel.setBounds(300,314,300,240);
			comp1Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 10));
			JPanel comp1CardPanel = new JPanel();
			comp1CardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
			JLabel comp1Name = new JLabel(computers.get(0).getName());
			JLabel comp1Chips = new JLabel(Integer.toString(computers.get(0).getChips()));
			comp1Panel.add(comp1Name);
			comp1Panel.add(comp1Chips);
			comp1Panel.add(comp1CardPanel);
			gamePanel.add(comp1Panel);
		}
		
		//Panel for additional player 2
		if(numOfPlayers >= 2)
		{
			JPanel comp2Panel = new JPanel();
			comp2Panel.setBackground(Color.cyan);
			comp2Panel.setBounds(600,314,300,240);
			comp2Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 10));
			JPanel comp2CardPanel = new JPanel();
			comp2CardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
			JLabel comp2Name = new JLabel(computers.get(1).getName());
			JLabel comp2Chips = new JLabel(Integer.toString(computers.get(1).getChips()));
			comp2Panel.add(comp2Name);
			comp2Panel.add(comp2Chips);
			comp2Panel.add(comp2CardPanel);
			gamePanel.add(comp2Panel);
		}

		//Panel for additional player 3
		if(numOfPlayers >= 3)
		{
			JPanel comp3Panel = new JPanel();
			comp3Panel.setBackground(Color.GREEN);
			comp3Panel.setBounds(900,314,300,240);
			comp3Panel.setLayout(null);
			JLabel comp3Name = new JLabel(computers.get(2).getName());
			JLabel comp3Chips = new JLabel(Integer.toString(computers.get(2).getChips()));
			comp3Panel.add(comp3Name);
			comp3Panel.add(comp3Chips);
			gamePanel.add(comp3Panel);
		}
		
		//Panel for game-play choices
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(null);
		choicePanel.setBackground(Color.pink);
		choicePanel.setBounds(300, 550, 600, 200);
		JLabel betAmount = new JLabel("Bet Amount (2-500): ");
		betAmount.setBounds(150, 10, 125, 30);
		JTextField enteredAmount = new JTextField();
		enteredAmount.setBounds(275, 10, 50, 30);
		JButton deal = new JButton("Deal");
		deal.setBounds(335, 10, 70, 30);
		JButton hit = new JButton("Hit");
		choicePanel.add(hit);
		JButton stand = new JButton("Stand");
		choicePanel.add(stand);
		JButton doubleDown = new JButton("Double-down");
		choicePanel.add(doubleDown);
		JButton split = new JButton("Split");
		choicePanel.add(split);
		JButton surrender = new JButton("Surrender");
		choicePanel.add(surrender);
		choicePanel.add(betAmount);
		choicePanel.add(enteredAmount);
		choicePanel.add(deal);
		
		gamePanel.add(choicePanel);
		
		
		/*
		 * LOGIC FOR BUTTONS
		 */
		//Deal
		deal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//If the bet amount is valid and is less than or equal to the current purse of user
				if(Integer.parseInt(enteredAmount.getText()) >= 2 &&
				   Integer.parseInt(enteredAmount.getText()) <= 500 &&
				   Integer.parseInt(enteredAmount.getText()) <= user.getChips())
				{
					//Stores the bet to be used across each player
					bet = Integer.parseInt(enteredAmount.getText());
					doubleDownAmount = bet * 2;
					if(computers != null)
						for(int i = computers.size()-1; i>=0; i--)
							computers.get(i).adjustChips(~(bet - 1));	//Subtracts bet from current purse
					user.adjustChips(~(bet - 1));						//Subtracts bet from current purse
					
					deal();
					updateGUI();
					
					//Check for Blackjack. If true, a new hand is started
					if(checkBlackjack())
					{
						//Resets all relevant variables and classes
						dealerPlayed = false;
						userPlayed = false;
						deck.resetDeck();
						user.reset();
						dealer.reset();
						for(Player p : computers)
							p.reset();
					}
					//Otherwise, continue as normal
					else
					{
						updateGUI();
					
						//Play goes clockwise
						if(computers != null)
							for(int i = computers.size()-1; i>=0; i--)
								computers.get(i).playTurn(deck);
					}
				}
			}
		});
		
		//Hit
		hit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				user.addCard(deck.getNextCard());
				updateGUI();
			}
		});
		
		//Stand
		stand.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				/*If one player still left, have dealer take their turn. 
				 * Checks computer busts first. Then user.
				 */
				if(computers != null)
					for(int i = computers.size()-1; i>=0; i--)
						if(computers.get(i).isBusted() == false)
						{
							//If there's a computer left, dealer takes turn & kicks out of loop
							dealer.playTurn(deck);
							dealerPlayed = true;
							i=-1;
						}
				if(user.isBusted() == false && dealerPlayed == false)
					dealer.playTurn(deck);
				
				userPlayed = true;
				postHand();
			}
		});
		
		//Double-down
		doubleDown.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Subtracts bet amount again and deals a singular card
				user.adjustChips(~(bet - 1));
				user.addCard(deck.getNextCard());
				
				/*If one player still left, have dealer take their turn. 
				 * Checks computer busts first. Then user.
				 */
				if(computers != null)
					for(int i = computers.size()-1; i>=0; i--)
						if(computers.get(i).isBusted() == false)
						{
							//If there's a computer left, dealer takes turn & kicks out of loop
							dealer.playTurn(deck);
							dealerPlayed = true;
							i=-1;
						}
				if(user.isBusted() == false && dealerPlayed == false)
					dealer.playTurn(deck);
				
				//Adjusts relevant variables
				doubledDown = true;
				userPlayed = true;
				postHand();
			}
		});
		
		//Split
		split.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		
		//Closes the game
		surrender.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
        game.setVisible(true);
	}
	
	//Updates the GUI
	public static void updateGUI()
	{
		gamePanel.revalidate();
		gamePanel.repaint();
		game.revalidate();
		game.repaint();
		gamePanel.updateUI();
	}

	//Deals cards in a clockwise position starting with the "last" computer before ending at the dealer.
	public static void deal()
	{
		//While the dealer doesn't have 2 card in their hand
		while(dealer.handSize() < 2)
		{
			//Checks to see if there are computers
			if(computers != null)
				for(int i = computers.size()-1; i>=0; i--)
					computers.get(i).addCard(deck.getNextCard());
			user.addCard(deck.getNextCard());
			dealer.addCard(deck.getNextCard());
		}
	}
	
	//Checks starting hand for blackjacks
	public static boolean checkBlackjack()
	{
		boolean blackjack = false;
		//Checks to see if chosen computer has a blackjack. Adjusts accordingly based on dealer
		if(computers != null)
			for(int i = computers.size()-1; i>=0; i--)
			{
				if(computers.get(i).getTotal() == 21 && dealer.getTotal() != 21)
				{
					computers.get(i).adjustChips((int) (bet*1.5));
					blackjack = true;
				}
				else if(computers.get(i).getTotal() == 21 && dealer.getTotal() == 21)
				{
					computers.get(i).adjustChips(bet);
					blackjack = true;
				}
			}
		
		//Checks if user has blackjack. Adjusts accordingly based on dealer
		if(user.getTotal() == 21 && dealer.getTotal() != 21)
		{
			user.adjustChips((int) (bet*1.5));
			blackjack = true;
		}
		else if(user.getTotal() == 21 && dealer.getTotal() == 21)
		{
			user.adjustChips(bet);
			blackjack = true;
		}
		
		//Checks to see if dealer has blackjack
		if(dealer.getTotal() == 21)
			blackjack = true;
		
		//If no one has a blackjack, returns false
		return blackjack;
	}
	
	//Calculates payout if necessary, resets all relevant variables
	public static void postHand()
	{		
		//Checks to see if computer won. Adjusts chip amount if they did. Resets.
		if(computers != null)
			for(int i = computers.size()-1; i>=0; i--)
			{
				if(!computers.get(i).isBusted() && dealer.isBusted())			//If chosen computer is in play and dealer is busted
					computers.get(i).adjustChips(bet*2);						//chosen computer gets initial bet matched
				else if(!computers.get(i).isBusted() && !dealer.isBusted())		//If chosen computer and dealer are in play
				{
					if(computers.get(i).getTotal() > dealer.getTotal())			//If chosen computer total beats dealer total
						computers.get(i).adjustChips(bet*2);					//Chosen computer gets bet matched
					else if (computers.get(i).getTotal() == dealer.getTotal())	//If totals are the same
						computers.get(i).adjustChips(bet);						//Chosen computer gets initial bet back
				}
				computers.get(i).reset();
			}
			
			//Accounts for player doubling down. Adjusts accordingly.
			if(!doubledDown)
			{	
				//Checks to see if user won. Adjusts chip amount if they did. Resets.
				if(!user.isBusted() && dealer.isBusted())			//If user is in play and dealer is busted
					user.adjustChips(bet*2);						//User gets initial bet matched
				else if(!user.isBusted() && !dealer.isBusted())		//If user and dealer are in play
				{
					if(user.getTotal() > dealer.getTotal())			//If user total beats dealer total
						user.adjustChips(bet*2);					//User gets bet matched
					else if (user.getTotal() == dealer.getTotal())	//If totals are the same
						user.adjustChips(bet);						//User gets initial bet back
				}
			}
			else
			{
				if(!user.isBusted() && dealer.isBusted())			//If user is in play and dealer is busted
					user.adjustChips(doubleDownAmount*2);			//User gets double down bet matched
				else if(!user.isBusted() && !dealer.isBusted())		//If user and dealer are in play
				{
					if(user.getTotal() > dealer.getTotal())			//If user total beats dealer total
						user.adjustChips(doubleDownAmount*2);		//User gets double down matched
					else if (user.getTotal() == dealer.getTotal())	//If totals are the same
						user.adjustChips(doubleDownAmount);			//User gets double down bet back
				}
			}
		//Resets all relevant variables and classes
		dealerPlayed = false;
		userPlayed = false;
		deck.resetDeck();
		user.reset();
		dealer.reset();
	}
}