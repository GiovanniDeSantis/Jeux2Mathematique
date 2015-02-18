package controller;

import java.util.ArrayList;

import model.GameState;

/**
 * Class that handles the interactions between the View and the Model.
 * On the basis of the inputs coming from the User Interface, this class
 * performs specific actions on the underlying Model and, consequently,
 * updates the View.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class GameController {

	private GameState gameState;
	
	public GameController (String[] playersNames) {
		gameState = new GameState(playersNames);
	}
	
	/**
	 * Creates the game.
	 */
	public String[] getShuffledDeck () {
		return gameState.getShuffledDeck();
	}
	
	public boolean handlePlayedCard (String id) {
		return gameState.handlePlayedCard(id);
	}
	
	public void updatePlayerScore (String name, int increment) {
		gameState.updatePlayerScore(name, increment);
	}
	
	public ArrayList<String> findWinners (int numberOfPlayers) {
		return gameState.findWinners(numberOfPlayers);
	}
}
