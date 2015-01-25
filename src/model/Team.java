package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Team {
	private static AtomicLong idCounter = new AtomicLong();
	
	private long id;
	private String name;
	private List<Player> players;
	
	public Team(int playersNumber, String name) {
		this.id = idCounter.getAndIncrement();
		this.name = name;
		this.players = new ArrayList<>(playersNumber);
	}
	
	public void insertPlayer(String name){
		//TODO Check if team is full
		this.players.add(new Player(name));
	}
	
	public void giveCardToPlayer(Card card, String playerName){
		for (Player player: this.players){
			if (player.getName().equals(playerName)){
				player.assignCard(card);
			}
		}
	}
		
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumberOfPlayers(){
		return this.players.size();
	}
	
	public List<String> getPlayersNames(){
		List<String> playersNames = new ArrayList<>(this.players.size());
		for (Player player: this.players){
			playersNames.add(player.getName());
		}
		return playersNames;
	}

	public int getTotalScore(){
		int score = 0;
		for (Player player: this.players){
			score += player.getScore();
		}
		return score;
	}
}
