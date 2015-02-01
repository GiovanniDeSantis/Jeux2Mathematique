package controller;

public class Action {
	private String playerName;
	private String playedCard;
	
	public Action(String playerName, String playedCard){
		this.playerName = playerName;
		this.playedCard = playedCard;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPlayedCard() {
		return playedCard;
	}
}