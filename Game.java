package blackjack;

import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game 
{
	//Variables to be used throughout the game
	static Player user = null;
	static Player[] computers = null;
	static Deck deck = new Deck();
	static Dealer dealer = new Dealer();
	static int initialPurse;
	static int numOfPlayers;
	static String name;
			
	public static void main(String[] args)
	{
		boolean dealerPlayed;
		gameGUI();
		
		//Game loop
		while(user.getChips() > 0)
		{
			dealerPlayed = false;
			
			//Initial bets
			
			deal();
			
			//Check for Blackjack
			checkBlackjack();
			
			//Play goes clockwise
			if(computers != null)
				for(int i = computers.length-1; i>=0; i--)
					computers[i].playTurn(deck);
			
			//Player plays their turn
			
			/*If one player still left, have dealer take their turn. 
			 * Checks computer busts first. Then user.
			 */
			if(computers != null)
				for(int i = computers.length-1; i>=0; i--)
					if(computers[i].isBusted() == false)
					{
						//If there's a computer left, dealer takes turn & kicks out of loop
						dealer.playTurn(deck);
						dealerPlayed = true;
						i=-1;
					}
			if(user.isBusted() == false && dealerPlayed == false)
				dealer.playTurn(deck);
			
			//Post hand. Adjust chip amounts, reset deck
			deck.resetDeck();
			user.reset();
			dealer.reset();
			if(computers != null)
				for(int i = computers.length-1; i>=0; i--)
					computers[i].reset();
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
		JFrame game = new JFrame("G6 - Blackjack");
		getInfo(game);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(1200, 800);
		game.setLayout(null);
		game.setVisible(true);
		
		//Populate the game with the # of players. Assign names & chip value
		user = new Player(name, initialPurse);
		if(numOfPlayers>0)
		{
			computers = new Player[numOfPlayers];
			for(int i=0; i<numOfPlayers; i++)
				computers[i] = new Player("Computer " + (i+1), initialPurse);
		}
		
		//panel for dealer
		JPanel dealerPanel = new JPanel();
		dealerPanel.setBackground(Color.gray);
		dealerPanel.setBounds(450,0,300,240);
		dealerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		JPanel dealerCardPanel = new JPanel();
		dealerCardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		JLabel dealerCard1 = new JLabel("2 of Clubs"); //TO BE UPDATED TO HAVE REAL CARD VALUE
		JLabel dealerCard2 = new JLabel("2 of Clubs"); //TO BE UPDATED TO HAVE REAL CARD VALUE
		dealerCardPanel.add(dealerCard1);
		dealerCardPanel.add(dealerCard2);
		JLabel dealerName = new JLabel("Dealer");
		dealerPanel.add(dealerName);
		dealerPanel.add(dealerCardPanel);
		game.add(dealerPanel);
		
		//panel for player
        JPanel userPanel = new JPanel();
		userPanel.setBackground(Color.red);
		userPanel.setBounds(0,314,300,240);
		userPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 10));
		JPanel userCardPanel = new JPanel();
		userCardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		JLabel userCard1 = new JLabel("2 of Clubs"); //TO BE UPDATED TO HAVE REAL CARD VALUE
		JLabel userCard2 = new JLabel("2 of Clubs"); //TO BE UPDATED TO HAVE REAL CARD VALUE
		userCardPanel.add(userCard1);
		userCardPanel.add(userCard2);
        JLabel userName = new JLabel(name);
        JLabel purse = new JLabel(Integer.toString(user.getChips()));
        userPanel.add(userName);
        userPanel.add(purse);
        userPanel.add(userCardPanel);
        game.add(userPanel);
		
		//panel for additional player 1
		if(numOfPlayers > 0)
		{
			JPanel comp1Panel = new JPanel();
			comp1Panel.setBackground(Color.LIGHT_GRAY);
			comp1Panel.setBounds(300,314,300,240);
			comp1Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 10));
			JPanel comp1CardPanel = new JPanel();
			comp1CardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
			JLabel comp1Card1 = new JLabel("2 of Clubs"); //TO BE UPDATED TO HAVE REAL CARD VALUE
			JLabel comp1Card2 = new JLabel("2 of Clubs"); //TO BE UPDATED TO HAVE REAL CARD VALUE
			comp1CardPanel.add(comp1Card1);
			comp1CardPanel.add(comp1Card2);
			JLabel comp1Name = new JLabel(computers[0].getName());
			JLabel comp1Chips = new JLabel(Integer.toString(computers[0].getChips()));
			comp1Panel.add(comp1Name);
			comp1Panel.add(comp1Chips);
			comp1Panel.add(comp1CardPanel);
			game.add(comp1Panel);
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
			JLabel comp2Card1 = new JLabel("QUEEN OF DIAMONDS"); //TO BE UPDATED TO HAVE REAL CARD VALUE
			JLabel comp2Card2 = new JLabel("QUEEN OF DIAMONDS"); //TO BE UPDATED TO HAVE REAL CARD VALUE
			comp2CardPanel.add(comp2Card1);
			comp2CardPanel.add(comp2Card2);
			JLabel comp2Name = new JLabel(computers[1].getName());
			JLabel comp2Chips = new JLabel(Integer.toString(computers[1].getChips()));
			comp2Panel.add(comp2Name);
			comp2Panel.add(comp2Chips);
			comp2Panel.add(comp2CardPanel);
			game.add(comp2Panel);
		}

		//Panel for additional player 3
		if(numOfPlayers >= 3)
		{
			JPanel comp3Panel = new JPanel();
			comp3Panel.setBackground(Color.GREEN);
			comp3Panel.setBounds(900,314,300,240);
			comp3Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 10));
			JPanel comp3CardPanel = new JPanel();
			comp3CardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
			JLabel comp3Card1 = new JLabel("QUEEN OF DIAMONDS"); //TO BE UPDATED TO HAVE REAL CARD VALUE
			JLabel comp3Card2 = new JLabel("QUEEN OF DIAMONDS"); //TO BE UPDATED TO HAVE REAL CARD VALUE
			comp3CardPanel.add(comp3Card1);
			comp3CardPanel.add(comp3Card2);
			JLabel comp3Name = new JLabel(computers[2].getName());
			JLabel comp3Chips = new JLabel(Integer.toString(computers[2].getChips()));
			comp3Panel.add(comp3Name);
			comp3Panel.add(comp3Chips);
			comp3Panel.add(comp3CardPanel);
			game.add(comp3Panel);
		}

        game.setVisible(true);
	}

	//Deals cards in a clockwise position starting with the "last" computer before ending at the dealer.
	public static void deal()
	{
		while(dealer.getCard2() == null) //Since the dealer is the last to receive a card, once he has both cards, the deal ends
		{
			//Accounts for there being no computers in the game
			if(computers != null)
				for(int i = computers.length-1; i>=0; i--)
				{
					if(computers[i].getCard1() == null)
						computers[i].setCard1(deck.getNextCard());
					else
						computers[i].setCard2(deck.getNextCard());
				}
			if(user.getCard1() == null)
				user.setCard1(deck.getNextCard());
			else
				user.setCard2(deck.getNextCard());
			if(dealer.getCard1() == null)
				dealer.setCard1(deck.getNextCard());
			else
				dealer.setCard2(deck.getNextCard());
		}
	}
	
	//Checks starting hand for blackjacks
	public static void checkBlackjack()
	{
		if(computers != null)
			for(int i = computers.length-1; i>=0; i--)
			{
				if(computers[i].hasAce());
					
			}
	}
}