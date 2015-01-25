package model;

import controller.Action;

public class PerimexpaireGameState extends GameState {
	private static final int NUMBER_CARDS_PLAYER = 4;
	
	public PerimexpaireGameState() {
		super();
		this.board = new PerimexpaireTable();
	}

	@Override
	public void startGame() {
		this.distributeCards();
		this.setPlayingOrder();
	}
	
	private void distributeCards() {
		for (Team team: this.teams){
			for (String playerName: team.getPlayersNames()){
				for (int i = 0; i < NUMBER_CARDS_PLAYER; i++){
					Card card = this.board.getCard();
					team.giveCardToPlayer(card, playerName);
				}
			}
		}
	}

	@Override
	public void updateGameState(Action movement) {
		// TODO Auto-generated method stub	
	}
}
