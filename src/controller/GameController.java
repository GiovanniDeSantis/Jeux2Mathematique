package controller;

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
	public String[] createGame () {
		return gameState.createGame();
	}	
}
