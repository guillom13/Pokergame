package com.poker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poker.beans.Card;
import com.poker.beans.Player;

/**
 * @author angely
 * Main class to run the program pokergame
 *
 */
public class Main {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    
	public static void main(String[] args) {
		/*
		 * To play enter player's name and his/her hand (5 cards) then enter, 
		 * when no more players type exit and enter.
		 * Taking into account:
		 * flush 5 five cards of the same suit
		 * three of kind
		 * one pair
		 * highest card
		 */
		/* Examples of input:
		 * input 1
Joe,3H,4H,5H,6H,8H
BOB,3C,3D,3S,8C,10D
Sally,AC,10C,5C,2S,2C
exit
		 * OR
Joe 3h 4h 5h 6h 8h
Bob 3c 3d 3s 8c 10d
Sally AC 10C 5C 2S 2C
exit
		* OUTPUT: joe
		* 
		* input 2:
Joe,3H,4H,5C,6H,8H
BOB,3C,2D,9S,8C,10D
Sally,AC,10C,5C,2S,KC
exit
		 * OUTPUT
		 * Sally
		 */
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter player's name and their hand (5 cards) separated by a coma or space on a line:");
		System.out.println("(When done enter exit)");
		// use the delimiters for the input
		scan.useDelimiter("[\\s,\r\n]+");
		
		List<Player> players = new ArrayList<Player>();
		
		while (scan.hasNext()){
			String next = scan.next();
			if (next.equalsIgnoreCase("exit")){
				break;
			}
			Player player = new Player();
			player.setName(next);
			LinkedList<Card> cardsList = new LinkedList<Card>();
			
			while(scan.hasNext()){
				String currentCard = scan.next();
				if (currentCard.length() < 2) {
					System.out.println("Please enter a valid card");
					scan.close();
					System.exit(-1);
				}
				String suit = currentCard.substring(currentCard.length()-1, currentCard.length());
				String rank = currentCard.substring(0, currentCard.length()-1);
				Card card = new Card(suit.toUpperCase(), rank.toUpperCase());
				// Exit if there is an unknown rank or suit input 
				if(card.getRankEnum().equals(Card.Rank.UNKMOWN)
						|| card.getSuitEnum().equals(Card.Suit.UNKMOWN)){
					System.out.println("Unknown Suit or Rank");
					scan.close();
					System.exit(-1);
				}
				cardsList.add(card);
				if(cardsList.size() == 5){
					break;
				}				
			}

			if(cardsList.size()!=5) {
				System.out.println("Please enter 5 cards per player");
				scan.close();
				System.exit(-1);
			}
				
			player.setCardsList(cardsList);
			
			players.add(player);
			
		}
		scan.close();
		
		if (players.size() > 0){
			LOGGER.debug("check the best hand");
			HandUtils utils = new HandUtils();
			System.out.println(utils.findBestHand(players));
		} else {
			LOGGER.warn("no player");
			System.out.println("Enter at least 1 player");
		}
	}
	
	
}
