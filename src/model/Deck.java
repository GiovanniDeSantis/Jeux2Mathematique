package model;

import java.util.Collections;
import java.util.ArrayList;

/**
 * Class that models the deck of the board game.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class Deck {
	
	private ArrayList<Card> deck;

	/**
	 * Class constructor.
	 */
	public Deck () {
		/* Deck creation */
		deck = new ArrayList<Card>(GameState.PERIMAIRE_DECK_DIMENSION);
		deck.add(new Card("A", 14, 6));
		deck.add(new Card("B", 12, 5));
		deck.add(new Card("C", 12, 6));
		deck.add(new Card("D", 12, 7));
		deck.add(new Card("E", 12, 8));
		deck.add(new Card("F", 16, 7));
		deck.add(new Card("G", 12, 5));
		deck.add(new Card("H", 12, 5));
		deck.add(new Card("I", 12, 6));
		deck.add(new Card("J", 16, 7));
		deck.add(new Card("K", 12, 6));
		deck.add(new Card("L", 12, 9));
		deck.add(new Card("M", 16, 7));
		deck.add(new Card("N", 12, 5));
		deck.add(new Card("O", 12, 6));
		deck.add(new Card("P", 12, 5));
		deck.add(new Card("Q", 12, 7));
		deck.add(new Card("R", 12, 7));
		deck.add(new Card("S", 14, 6));
		deck.add(new Card("T", 16, 8));
		deck.add(new Card("U", 12, 5));
		deck.add(new Card("V", 14, 8));
		deck.add(new Card("W", 14, 6));
		deck.add(new Card("X", 14, 7));
	}
	
	
	public Card getCard (String id) {
		Card desiredCard = null;
		
		for (Card card : deck) {
			if (id.equals(card.getId()))
				desiredCard = card;
		}
		
		return desiredCard;
	}
	
	public String[] getStringDeck () {
		String[] deck = new String[GameState.PERIMAIRE_DECK_DIMENSION];
		
		for (int i = 0; i < GameState.PERIMAIRE_DECK_DIMENSION; i++) {
			deck[i] = this.deck.get(i).getId();
		}
		
		return deck;		
	}
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffle () {
		Collections.shuffle(deck);
	}
}
