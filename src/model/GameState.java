package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.Action;
import utils.CircularList;

public abstract class GameState {
	protected List<Team> teams;
	protected Table board;
	protected List<String> playersOrder;
	protected int currentPlayer;
	
	public GameState(){
		this.teams = new ArrayList<>();
	}
	
	public void createTeam(String name, int numberOfPlayers){
		//TODO check if any rule is being broken.
		this.teams.add(new Team(numberOfPlayers, name));
	}
	
	public void insertPlayer(String playerName, String teamName){
		for (Team team: this.teams){
			if (team.getName().equals(teamName)){
				team.insertPlayer(playerName);
			}
		}
	}
	
	protected void setPlayingOrder() {
		this.playersOrder = new CircularList<>();
		for (Team team: this.teams){
			for (String playerName: team.getPlayersNames()){
				this.playersOrder.add(playerName);
			}
		}
		Collections.shuffle(this.playersOrder);
		this.currentPlayer = 0;
	}
	
	public abstract void startGame();
	
	public abstract void updateGameState(Action movement);
}
