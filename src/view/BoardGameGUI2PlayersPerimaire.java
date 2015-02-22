package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.Timer;

import controller.GameController;
import model.GameState;
import model.Player;

/**
 * Class that implements the User Interface of the game board in the
 * case there are two players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class BoardGameGUI2PlayersPerimaire extends BoardGameGUI {
	
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	private int stuckGameCondition;	
	private PlayerPanelGUI firstPlayer, secondPlayer;
	private PlayingPanelGUI playingPanel;
	private Stack<CardGUI> deck;
	private String[] playersNames;

	/**
	 * Class constructor.
	 */
	public BoardGameGUI2PlayersPerimaire (GameController gameController, String[] playersNames,
																String[] shuffledDeck, int firstToPlay) {
		/* Member Data Initialization */
		this.gameController = gameController;
		this.playersNames = playersNames;
		stuckGameCondition = 0;
		/* Panels Creation */
		firstPlayer = new PlayerPanelGUI(playersNames[0], 12);
		playingPanel = new PlayingPanelGUI();
		secondPlayer = new PlayerPanelGUI(playersNames[1], 12);
		/* Deck Creation */
		deck = new Stack<CardGUI>();
		for (int i = 0; i < GameState.PERIMAIRE_DECK_DIMENSION; i++) {
			CardGUI card = new CardGUI(shuffledDeck[i]);
			card.addActionListener(new PlayerCardGUIListener());
			deck.push(card);
		}
		/* Board Game Initialization */
		initialize(firstToPlay);
	}
	
	@Override
	protected void initialize (int firstToPlay) {
		/* First Player Panel Handling */
		firstPlayer.setPreferredSize(new Dimension(300, 510));
		firstPlayer.setHeaderLabelSize(240, 20);
		firstPlayer.setCardsPanelSize(300, 450);
		firstPlayer.setButtonSize(115, 20);
		firstPlayer.addButtonActionListener(new PassTurnButtonListener());
		/* Playing Panel Handling */
		playingPanel.setPreferredSize(new Dimension(260, 510));
		playingPanel.setMessageLabelSize(260, 80);
		playingPanel.setCardsPanelSize(260, 410);
		/* Second Player Panel Handling */
		secondPlayer.setPreferredSize(new Dimension(300, 510));
		secondPlayer.setHeaderLabelSize(240, 20);
		secondPlayer.setCardsPanelSize(300, 450);
		secondPlayer.setButtonSize(115, 20);
		secondPlayer.addButtonActionListener(new PassTurnButtonListener());
		/* Deck's Cards Distribution Handling */		
		switch (firstToPlay) {
			case 1: firstPlayer.configureCardsPanel(4, 3, deck, false, false);
					secondPlayer.configureCardsPanel(4, 3, deck, false, false);
					secondPlayer.enable(false);
					playingPanel.setMessage("Le premier joueur sera: " + playersNames[0]);
					break;
			case 2: secondPlayer.configureCardsPanel(4, 3, deck, false, false);
					firstPlayer.configureCardsPanel(4, 3, deck, false, false);
					firstPlayer.enable(false);
					playingPanel.setMessage("Le premier joueur sera: " + playersNames[1]);
					break;
			default: break;
		}
		/* Board Game Handling */
		add(firstPlayer);
		add(playingPanel);
		add(secondPlayer);
	}
	
    /* ------------------------------------ Player CardGUI Action Listener -------------------------------- */
	
	/** Inner class implementing the Action Listener for the CardGUI.
	  * @author Giovanni De Santis, Rafael Garcia.
	  */
	public class PlayerCardGUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			/* If present, removal of the text contained in the information message label of the playing panel */
			if (! playingPanel.isMessageEmpty())
				playingPanel.setMessage("");
			/* Handling of the move performed by the generic player */
			CardGUI playedCard = (CardGUI)action.getSource();
			boolean moveValidity = gameController.handlePlayedCard(playedCard.getId());
			Container playerPanel = playedCard.getParent().getParent();
			/* Not valid move: notify the user that the move is not correct */
			if (! moveValidity) {
				playingPanel.setMessage("Mouvement pas valable!");
			} 
			/* Valid move: positioning of the played card in the playing panel */
			else {
				stuckGameCondition = 0;
				playedCard.setEnabled(false);
				playingPanel.positionPlayedCard(playedCard);
				/* Enabling and disabling of the card panels 
				 * for the handling of the players' turns
				 */
				if (playerPanel == firstPlayer) {
					firstPlayer.updateScore(-1);
					gameController.updatePlayerScore(playersNames[0], -1);
					firstPlayer.enable(false);
					secondPlayer.enable(true);
				}
				else {
					secondPlayer.updateScore(-1);
					gameController.updatePlayerScore(playersNames[1], -1);
					secondPlayer.enable(false);
					firstPlayer.enable(true);		
				}
			}
		}

	}
	
    /* ------------------------------------ Pass Turn Button Action Listener -------------------------------- */

	/** Inner class implementing the Action Listener for the Pass Turn Button.
	  * @author Giovanni De Santis, Rafael Garcia.
	  */
	public class PassTurnButtonListener implements ActionListener {

		private Timer timer;
		
		/**
		 * Class constructor.
		 */
		public PassTurnButtonListener () {
			timer = new Timer(3000, new TimerListener());
			timer.setRepeats(false);			
		}
		
		@Override
		public void actionPerformed (ActionEvent action) {
			stuckGameCondition++;
			/* End Game Condition */
			if (stuckGameCondition == 2) {
				playingPanel.setMessage("<html><div style=\"text-align: center;\">Aucun joueur peut "
						+ "faire un mouvement. La partie termine.</div></html>");
				firstPlayer.enable(false);
				secondPlayer.enable(false);
				timer.start();
			} else {
				/* Normal Game Condition - Passing Turns */
				JButton clickedButton = (JButton)action.getSource();
				Container buttonContainer = clickedButton.getParent();
				if (buttonContainer == firstPlayer) {
					firstPlayer.enable(false);
					secondPlayer.enable(true);
				} else {
					secondPlayer.enable(false);
					firstPlayer.enable(true);
				}
			}
		}
		
	    /* ------------------------------------ Timer Action Listener -------------------------------- */
		
		/**
		 * Inner class implementing the Action Listener to be associated to the timer used in the PassTurnButtonListener.
		 * @author Giovanni De Santis, Rafael Garcia.
		 *
		 */
		public class TimerListener implements ActionListener {
			
			@Override
			public void actionPerformed (ActionEvent action) {
				/* Retrieval of the current players' scores. */
				ArrayList<Player> results = gameController.getResults();
				/* Print of the match results in the information message label of the playing panel */
				String rank = "";
				for (int i = 0; i < results.size(); i++) {
					Player player = results.get(i);
					rank = rank.concat("" + (i + 1) + ") " + player.getName() + " - " + player.getScore() + "<br>");
				}
				playingPanel.setMessage("<html><div style=\"text-align: center;\">Les résultats de la partie sont:<br>"
						+ "" + rank + "</div></html>");
				timer.stop();
			}
			
		}
	
	}
	
}