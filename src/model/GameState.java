package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that stores all the relevant information about a game.
 * Through this class is possible to control the state of the
 * game and, every time that is necessary, update it properly.
 * 
 * @author Giovanni De Santis - Rafael Garcia.
 */
public class GameState {
	private List<Player> players;
	private Deck deck;
	
	public static final int PERIMAIRE_DECK_DIMENSION = 24;
	
	public GameState (String[] playersNames) {
		/* Creation of the players taking part to the game */
		players = new ArrayList<Player>();
		for (String name : playersNames) {
			players.add(new Player(name));
		}
		/* Creation of the deck */
		deck = new Deck();
	}
	
	public String[] createGame () {
		String[] shuffledDeck = new String[PERIMAIRE_DECK_DIMENSION];
		/* Shuffling the deck */
		deck.shuffle();
		for (int i = 0; i < PERIMAIRE_DECK_DIMENSION; i++) {
			shuffledDeck[i] = deck.getTopCard().getId();
		}
		
		return shuffledDeck;
	}
	
	public int determineFirstPlayer (int min, int max) {
		Random random = new Random();
	    int randomNumber = random.nextInt((max - min) + 1) + min;

	    return randomNumber;		
	}
}