package model;

import java.util.List;

/**
 * Class that stores all the relevant information about a game.
 * Through this class is possible to control the state of the
 * game and, every time that is necessary, update it properly.
 * 
 * @author Giovanni De Santis - Rafael Garcia.
 */
public class GameState {
	protected List<Player> players;
	protected List<String> playersOrder;
	protected int currentPlayer;
	
	/**
	 * Performs all the actions necessary to create a new game.
	 */
	public String[] createGame (int numberOfPlayers) {
		return null;
	}
	
	/*
	public void createTeam (String name, int numberOfPlayers) {
		//TODO check if any rule is being broken.
		this.teams.add(new Team(numberOfPlayers, name));
	}
	
	public void insertPlayer (String playerName, String teamName) {
		for (Team team: this.teams){
			if (team.getName().equals(teamName)){
				team.insertPlayer(playerName);
			}
		}
	}
	
	protected void setPlayingOrder () {
		this.playersOrder = new CircularList<>();
		for (Team team: this.teams){
			for (String playerName: team.getPlayersNames()){
				this.playersOrder.add(playerName);
			}
		}
		Collections.shuffle(this.playersOrder);
		this.currentPlayer = 0;
	}*/
	
	//public abstract void startGame();
	
	//public abstract void updateGameState(Action movement);
}
