package com.poker;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poker.beans.Card;
import com.poker.beans.Player;

/**
 * @author angely
 * Methods to check the hand
 */
public class HandUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(HandUtils.class);
	private static int SCORE_HASNOTHING = 1;
	private static int SCORE_FLUSH = 6;
	private static int SCORE_THREEOFKIND = 4;
	private static int SCORE_ONEPAIR = 2;
	
	/**
	 * Check if there is a flush in the cards
	 * @param cards
	 * @return boolean true if it is
	 */
	public boolean isFlush(List<Card> cards){
		if(cards == null || cards.size() == 0) {
			return false;
		}
		String suit= null;
		for (Card c:cards) {
			if(suit == null) {
				suit = c.getSuit();
			}
			if(!suit.equals(c.getSuit())) {
				return false;
			}
		}
		return true;
		
	}
	
	/**
	 * Check if there is a three of a king in the ranks
	 * @param sortedRanks must be sorted 
	 * @return boolean true if it is
	 */
	public boolean isThreeOfkind(LinkedList<Card> sortedRanks){
		
		if(sortedRanks == null || sortedRanks.size() == 0){
			return false;
		}
		
		int score = 1;
		for (int i=0; i < sortedRanks.size()-2; i++){
			if (sortedRanks.get(i).getRank() == null){
				return false;
			}
			if(sortedRanks.get(i).getRank().equals(sortedRanks.get(i+1).getRank())){
				score++;
				LOGGER.debug(sortedRanks.get(i).getRank() + " " + score);
			}
			
		}
		
		if(score == 3) {
			return true;
		}
		
		return false;		
	}
	
	/**
	 * Check if there is a pair in the list
	 * @param sortedRanks
	 * @return boolean true if there is one 
	 */
	public boolean isOnePair(LinkedList<Card> sortedRanks) {
		
		if(sortedRanks == null || sortedRanks.size() == 0) {
			return false;
		}
		
		int score = 1;
		for (int i=0; i < sortedRanks.size()-2; i++) {
			if(sortedRanks.get(i).getRank() == null) {
				return false;
			}
			if(sortedRanks.get(i).getRank().equals(sortedRanks.get(i+1).getRank())) {
				score++;
				LOGGER.debug(sortedRanks.get(i).getRank() + " " + score);
			}			
		}
		
		if(score == 2) {
			return true;
		}
		
		return false;		
	}
	
	/**
	 * Return the name of the player with the best hand
	 * @param list of players
	 * @return name of the best hand
	 */
	public String findBestHand(List<Player> players){
		
		if (players == null || players.size() == 0){
			return "Error - List of players is null or empty";
		}
		
		int hasNothingCount = 0;
				
		for (Player p:players){
			if (p.getCardsList() == null){
				return "Error - List of cards is null for the player " + p.getName();
			}
			// sort with lambda expression on rank
			p.getCardsList().sort((Card o1, Card o2)->o1.getRankEnum().compareTo(o2.getRankEnum()));
			LOGGER.debug("sorted by rank " + p.getCardsList());
			// set the highest card 
			p.setHighCard(p.getCardsList().getLast());
			LOGGER.debug("best card " + p.getCardsList().getLast());
			
			// check if it is a flush
			boolean isFlush = isFlush(p.getCardsList());
			LOGGER.debug("isFlush=" + isFlush);
			if(isFlush){
				p.setScore(SCORE_FLUSH);
				LOGGER.debug("Player=" + p.getName() + " score="+p.getScore());
				continue;
			}
			
			// check if it is a three of a kind
			boolean isThree = isThreeOfkind(p.getCardsList());
			LOGGER.debug("isThree=" + isThree);
			if(isThree){
				p.setScore(SCORE_THREEOFKIND);
				LOGGER.debug("Player=" + p.getName() + " score="+p.getScore());
				continue;
			}
			
			// check if there is one pair
			boolean isOnePair = isOnePair(p.getCardsList());
			LOGGER.debug("isOnePair=" + isOnePair);
			if(isOnePair){
				p.setScore(SCORE_ONEPAIR);
				LOGGER.debug("Player=" + p.getName() + " score="+p.getScore());
				continue;
			}
			
			// if there is nothing 
			p.setScore(SCORE_HASNOTHING);
			hasNothingCount++;
			LOGGER.debug("Player=" + p.getName() + " score="+p.getScore());
		} 
		
		// check if everybody has nothing
		if (hasNothingCount == players.size()) {
			// if everybody has nothing then compare on highest card
			players.sort(
					(Player o1, Player o2) -> o1.getHighCard().getRankEnum().compareTo(o2.getHighCard().getRankEnum()));
			LOGGER.debug("All players got nothing");
		} else {
			// if not compare on the score
			players.sort((Player o1, Player o2) -> o1.getScore().compareTo(o2.getScore()));
		}
		
		// return name of the winner
		String winner = players.get(players.size()-1).getName();		
		return winner;
	}

}
