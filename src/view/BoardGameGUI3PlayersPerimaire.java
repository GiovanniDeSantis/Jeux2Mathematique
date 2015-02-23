package view;

import java.awt.BorderLayout;
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
 * case there are three players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class BoardGameGUI3PlayersPerimaire extends BoardGameGUI {
	
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	private int stuckGameCondition = 0;
	private PlayerPanelGUI firstPlayer, secondPlayer, thirdPlayer;
	private PlayingPanelGUI playingPanel;
	private Stack<CardGUI> deck;
	private String[] playersNames;
	
	/**
	 * Class constructor.
	 */
	public BoardGameGUI3PlayersPerimaire (GameController gameController, String[] playersNames,
																String[] shuffledDeck, int firstToPlay) {
		setLayout(new BorderLayout());
		/* Member Data Initialization */
		this.gameController = gameController;
		this.playersNames = playersNames;
		/* Panels Creation */
		firstPlayer = new PlayerPanelGUI(playersNames[0], 8);
		secondPlayer = new PlayerPanelGUI(playersNames[1], 8);
		thirdPlayer = new PlayerPanelGUI(playersNames[2], 8);
		playingPanel = new PlayingPanelGUI();
		/* Cards Creation */
		deck = new Stack<CardGUI>();
		for (int i = 0; i < GameState.PERIMAIRE_DECK_DIMENSION; i++) {
			CardGUI card = new CardGUI(shuffledDeck[i]);
			card.addActionListener(new PlayerCardGUIListener());
			deck.push(card);
		}
		/* Initialization */
		initialize(firstToPlay);
	}
	
	@Override
	protected void initialize (int firstToPlay) {
		/* First Player Panel Handling */
		firstPlayer.setPreferredSize(new Dimension(350, 400));
		firstPlayer.setHeaderLabelSize(320, 20);
		firstPlayer.setCardsPanelSize(350, 320);
		firstPlayer.setButtonsPanelSize(350, 30);
		firstPlayer.addPassTurnButtonActionListener(new PassTurnButtonListener());
		/* Second Player Panel Handling */
		secondPlayer.setPreferredSize(new Dimension(1000, 290));
		secondPlayer.setHeaderLabelSize(1000, 20);
		secondPlayer.setButtonsPanelSize(1000, 30);
		secondPlayer.addPassTurnButtonActionListener(new PassTurnButtonListener());
		/* Third Player Panel Handling */
		thirdPlayer.setPreferredSize(new Dimension(350, 400));
		thirdPlayer.setHeaderLabelSize(320, 20);
		thirdPlayer.setCardsPanelSize(350, 320);
		thirdPlayer.setButtonsPanelSize(350, 30);
		thirdPlayer.addPassTurnButtonActionListener(new PassTurnButtonListener());
		/* Playing Panel Handling */
		playingPanel.setPreferredSize(new Dimension(300, 400));
		playingPanel.setMessageLabelSize(300, 80);
		playingPanel.setCardsPanelSize(300, 300);
		/* Deck's Cards Distribution Handling */		
		switch (firstToPlay) {
			case 1: firstPlayer.configureCardsPanel(3, 3, deck, true, false);
					secondPlayer.configureCardsPanel(2, 4, deck, false, false);
					secondPlayer.enable(false);
					thirdPlayer.configureCardsPanel(3, 3, deck, true, false);
					thirdPlayer.enable(false);
					playingPanel.setMessage("Le premier joueur sera: " + playersNames[0]);
					break;
			case 2: secondPlayer.configureCardsPanel(2, 4, deck, false, false);
					thirdPlayer.configureCardsPanel(3, 3, deck, true, false);
					thirdPlayer.enable(false);
					firstPlayer.configureCardsPanel(3, 3, deck, true, false);
					firstPlayer.enable(false);
					playingPanel.setMessage("Le premier joueur sera: " + playersNames[1]);
					break;
			case 3: thirdPlayer.configureCardsPanel(3, 3, deck, true, false);
					firstPlayer.configureCardsPanel(3, 3, deck, true, false);
					firstPlayer.enable(false);
					secondPlayer.configureCardsPanel(2, 4, deck, false, false);
					secondPlayer.enable(false);
					playingPanel.setMessage("Le premier joueur sera: " + playersNames[2]);
					break;
			default: break;
		}
		/* Board Panel Handling */
		add(firstPlayer, BorderLayout.WEST);
		add(secondPlayer, BorderLayout.NORTH);
		add(thirdPlayer, BorderLayout.EAST);
		add(playingPanel, BorderLayout.CENTER);
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
				else if (playerPanel == secondPlayer) {
					secondPlayer.updateScore(-1);
					gameController.updatePlayerScore(playersNames[1], -1);
					secondPlayer.enable(false);
					thirdPlayer.enable(true);		
				} else {
					thirdPlayer.updateScore(-1);
					gameController.updatePlayerScore(playersNames[2], -1);
					thirdPlayer.enable(false);
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
			if (stuckGameCondition == 3) {
				playingPanel.setMessage("<html><div style=\"text-align: center;\">Aucun joueur peut "
						+ "faire un mouvement. La partie termine.</div></html>");
				firstPlayer.enable(false);
				secondPlayer.enable(false);
				thirdPlayer.enable(false);
				timer.start();
			} else {
				/* Normal Game Condition - Passing Turns */
				JButton clickedButton = (JButton)action.getSource();
				Container playerPanel = clickedButton.getParent().getParent();
				if (playerPanel == firstPlayer) {
					firstPlayer.enable(false);
					secondPlayer.enable(true);
				} else if (playerPanel == secondPlayer) {
					secondPlayer.enable(false);
					thirdPlayer.enable(true);
				} else {
					thirdPlayer.enable(false);
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