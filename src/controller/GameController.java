package controller;

import model.GameState;

/**
 * Class that handles the interactions between the View and the Model.
 * On the basis of the inputs coming from the User Interface, this class
 * performs specific actions on the underlying Model and, consequently,
 * updates the View.
 * @author Giovanni De Santis - Rafael Garcia.
 *
 */
public class GameController {

	private GameState gameState;
	
	/**
	 * Class constructor.
	 */
	public GameController () {
		gameState = new GameState();
	}
	
	/**
	 * Creates the game.
	 */
	public String[] createGame (int numberOfPlayers) {
		return gameState.createGame(numberOfPlayers);
	}
	
	/*
	public void createTeam (String teamName, int numberOfPlayers) {
		gameState.createTeam(teamName, numberOfPlayers);
	}
	
	//TODO Comments.
	public void insertPlayer (String playerName, String teamName) {
		gameState.insertPlayer(playerName, teamName);
	}*/
	
}
