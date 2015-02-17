package model;

/**
 * Class that models a player of the board game.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class Player {
	private String name;
	private int score;
	
	/**
	 * Class constructor.
	 * @param name - the name associated to the player.
	 */
	public Player (String name) {
		this.name = name;
		score = 0;
	}
	
	/**
	 * Returns the name of the player.
	 * @return the name of the player.
	 */
	public String getName () {
		return name;
	}

	/**
	 * Returns the score of the player.
	 * @return the score of the player.
	 */
	public int getScore () {
		return score;
	}

	/**
	 * Updates the score of the player.
	 * @param additionalScore - the value to sum to the current score.
	 */
	public void updateScore (int additionalScore) {
		score += additionalScore;
	}
}