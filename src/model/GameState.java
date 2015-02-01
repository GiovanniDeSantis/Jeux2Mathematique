package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores all the relevant information about a game.
 * Through this class is possible to control the state of the
 * game and, every time that is necessary, update it properly.
 * 
 * @author Giovanni De Santis - Rafael Garcia.
 */
public class GameState {
	private Deck deck;
	private List<Player> players;
	private String lastPlayedCard;
	
	public static final int PERIMAIRE_DECK_DIMENSION = 24;
	
	/**
	 * 
	 * @param playersNames
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
	 * 
	 * @return
	 */
	public String[] getShuffledDeck () {
		String[] shuffledDeck = new String[PERIMAIRE_DECK_DIMENSION];
		
		deck.shuffle();
		for (int i = 0; i < PERIMAIRE_DECK_DIMENSION; i++) {
			shuffledDeck[i] = deck.getTopCard().getId();
		}
		
		return shuffledDeck;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void handlePlayedCard (String id) {
		
	}
}