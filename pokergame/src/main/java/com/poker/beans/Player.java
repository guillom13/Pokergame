/**
 * 
 */
package com.poker.beans;

import java.io.Serializable;
import java.util.*;

/**
 * @author angely
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = -110718315984869594L;

	private String name;
	private LinkedList<Card> cardsList;
	private Card highCard;
	private Integer score;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedList<Card> getCardsList() {
		return cardsList;
	}
	public void setCardsList(LinkedList<Card> cardsList) {
		this.cardsList = cardsList;
	}
	public Card getHighCard() {
		return highCard;
	}
	public void setHighCard(Card highCard) {
		this.highCard = highCard;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	@Override
	public String toString(){
		String s = name;
		for (Card c:cardsList ) {
			s += " R="+ c.getRank() + " S=" + c.getSuit();
		}
		return s;	
	}
}
