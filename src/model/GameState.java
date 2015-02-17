package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores all the relevant information about a round
 * of the board game.
 * Through this class is possible to control the state of the
 * round and, every time that is necessary, update it properly.
 * 
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class GameState {
	private Deck deck;
	private List<Player> players;
	private Card lastPlayedCard;
	
	public static final int PERIMAIRE_DECK_DIMENSION = 24;
	
	/**
	 * Class constructor.
	 * @param playersNames - the names of the players associated to the game.
	 */
	public GameState (String[] playersNames) {
		/* Creation of the deck */
		deck = new Deck();
		/* Creation of the players taking part to the game */
		players = new ArrayList<Player>();
		for (String name : playersNames) {
			players.add(new Player(name));
		}
		/* Initialization of the last played card */
		lastPlayedCard = null;
	}
	
	/**
	 * Returns the shuffled deck.
	 * @return the shuffled deck.
	 */
	public String[] getShuffledDeck () {
		String[] shuffledDeck = new String[PERIMAIRE_DECK_DIMENSION];
		
		deck.shuffle();
		shuffledDeck = deck.getStringDeck();
		
		return shuffledDeck;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean handlePlayedCard (String id) {
		boolean moveValidity = true;
		Card playedCard = deck.getCard(id);
		
		if (lastPlayedCard != null) {
			if (((playedCard.getArea()) == (lastPlayedCard.getArea()))
					| ((playedCard.getPerimeter()) == (lastPlayedCard.getPerimeter()))) {
				moveValidity = true;
				lastPlayedCard = playedCard;
			} else {
				moveValidity = false;
			}
		} else {
			lastPlayedCard = playedCard;			
		}
		
		return moveValidity;
	}
}