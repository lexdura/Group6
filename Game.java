package blackjack;

import java.util.Scanner;

public class Game 
{
	public static void main(String[] args)
	{
		Scanner get = new Scanner(System.in);
		System.out.println("Welcome to Blakjack. Please enter your Name:");
		String name = get.nextLine();
		System.out.println("Hello " + name + ". Please enter the starting chip amount: ");
		int chips = get.nextInt();
		System.out.print("Please enter the number of players that will be joining you today. (From 0-3): ");
		int numOfPlayers = get.nextInt();
		while(numOfPlayers < 0 || numOfPlayers > 3)
		{
			System.out.print("I'm sorry. We will only accept a number between 0-3. Please enter again: ");
			numOfPlayers = get.nextInt();
		}
		
	}
}