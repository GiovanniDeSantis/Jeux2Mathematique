package model;

public class Player {
	private String name;
	private int score;
	
	public Player (String name) {
		this.name = name;
		score = 0;
	}
	
	public String getName () {
		return name;
	}

	public void updateScore (int additionalScore) {
		score += additionalScore;
	}
	
	public int getScore () {
		return score;
	}
}