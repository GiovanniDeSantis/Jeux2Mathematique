package model;

import controller.Action;

public class PerimaireGameState extends GameState {
	private static final int NUMBER_OF_CARDS = 24;
	
	public PerimaireGameState() {
		super();
		this.board = new PerimaireTable();
	}
	
	@Override
	public void startGame() {
		this.distributeCards();
		this.setPlayingOrder();
	}

	private void distributeCards(){
		for (Team team: this.teams){
			for (String playerName: team.getPlayersNames()){
				int amountOfCards = NUMBER_OF_CARDS/this.totalNumberOfPlayers();
				for (int i = 0; i < amountOfCards; i++){
					Card card = this.board.getCard();
					team.giveCardToPlayer(card, playerName);
				}
			}
		}
	}

	private int totalNumberOfPlayers() {
		int numberOfPlayers = 0;
		for (Team team: this.teams){
			numberOfPlayers += team.getNumberOfPlayers();
		}
		return numberOfPlayers;
	}

	@Override
	public void updateGameState(Action movement) {
		// TODO Auto-generated method stub
	}
}
