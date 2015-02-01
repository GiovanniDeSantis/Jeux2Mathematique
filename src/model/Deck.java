package model;

import java.util.Collections;
import java.util.Stack;

public class Deck {
	
	private Stack<Card> deck;

	public Deck () {
		/* Deck creation */
		deck = new Stack<Card>();
		deck.push(new Card("A", 14, 6));
		deck.push(new Card("B", 12, 5));
		deck.push(new Card("C", 12, 6));
		deck.push(new Card("D", 12, 7));
		deck.push(new Card("E", 12, 8));
		deck.push(new Card("F", 16, 7));
		deck.push(new Card("G", 12, 5));
		deck.push(new Card("H", 12, 5));
		deck.push(new Card("I", 12, 6));
		deck.push(new Card("J", 16, 7));
		deck.push(new Card("K", 12, 6));
		deck.push(new Card("L", 12, 9));
		deck.push(new Card("M", 16, 7));
		deck.push(new Card("N", 12, 5));
		deck.push(new Card("O", 12, 6));
		deck.push(new Card("P", 12, 5));
		deck.push(new Card("Q", 12, 7));
		deck.push(new Card("R", 12, 7));
		deck.push(new Card("S", 14, 6));
		deck.push(new Card("T", 16, 8));
		deck.push(new Card("U", 12, 5));
		deck.push(new Card("V", 14, 8));
		deck.push(new Card("W", 14, 6));
		deck.push(new Card("X", 14, 7));
	}
	
	public Card getTopCard () {
		return deck.pop();
	}
	
	public void shuffle () {
		Collections.shuffle(deck);
	}
}
