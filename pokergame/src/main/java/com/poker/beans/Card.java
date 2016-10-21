package com.poker.beans;

import java.io.Serializable;

/**
 * @author angely
 *
 */
public class Card implements Serializable {

	private static final long serialVersionUID = 6364935416331283744L;
	
	private String suit;
	private String rank;
	private Suit suitEnum;
	private Rank rankEnum;
	
	public Card(String suit, String rank) {
		super();
		this.suit = suit;
		this.rank = rank;
		switch (suit) {
			case "D":
				this.suitEnum = Suit.D;
				break;
			case "C":
				this.suitEnum = Suit.C;
				break;
			case "H":
				this.suitEnum = Suit.H;
				break;
			case "S":
				this.suitEnum = Suit.S;
				break;	
			default:
				this.suitEnum = Suit.UNKMOWN;
				break;
		}
		
		switch (rank) {
			case "2":
				this.rankEnum = Rank.DEUCE;
				break;
			case "3":
				this.rankEnum  = Rank.THREE;
				break;
			case "4":
				this.rankEnum  = Rank.FOUR;
				break;
			case "5":
				this.rankEnum  = Rank.FIVE;
				break;
			case "6":
				this.rankEnum  = Rank.SIX;
				break;	
			case "7":
				this.rankEnum  = Rank.SEVEN;
				break;	
			case "8":
				this.rankEnum  = Rank.EIGHT;
				break;	
			case "9":
				this.rankEnum  = Rank.NINE;
				break;	
			case "10":
				this.rankEnum  = Rank.TEN;
				break;
			case "J":
				this.rankEnum  = Rank.JACK;
				break;
			case "Q":
				this.rankEnum  = Rank.QUEEN;
				break;
			case "K":
				this.rankEnum  = Rank.KING;
				break;	
			case "A":
				this.rankEnum  = Rank.ACE;
				break;	
			default:
				this.rankEnum  = Rank.UNKMOWN;
				break;
		}
		
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public String toString(){
		return rank + suit; 
	}
	
	/**
	 * Enum to be able to sort by Suit
	 */
	public enum Suit {
		UNKMOWN, D, C, H, S;
	}
	
	/**
	 * Enum to be able to sort by rank 
	 */
	public enum Rank {
		UNKMOWN, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
	}

	public Suit getSuitEnum() {
		return suitEnum;
	}

	public void setSuitEnum(Suit suitEnum) {
		this.suitEnum = suitEnum;
	}

	public Rank getRankEnum() {
		return rankEnum;
	}

	public void setRankEnum(Rank rankEnum) {
		this.rankEnum = rankEnum;
	}
}
