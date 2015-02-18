package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.GameController;
import model.GameState;

/**
 * Class that implements the User Interface of the game board in the
 * case there are two players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class BoardGameGUI2PlayersPerimaire extends BoardGameGUI {
	
	//TODO Check the handling of the game controller. Static class or not.
	//	   It is defined in the main menu and in every board game.
	
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	private int firstPlayerScore, secondPlayerScore, stuckGameCondition;
	private JButton firstPlayerPassTurnButton, secondPlayerPassTurnButton;
	private JPanel firstPlayerPanel, secondPlayerPanel, centralPanel;
	private JPanel firstPlayerCardPanel, secondPlayerCardPanel, centralCardPanel;
	private JLabel firstPlayerNameLabel, secondPlayerNameLabel, firstPlayerScoreLabel, secondPlayerScoreLabel, informationMessageLabel;
	private Stack<CardGUI> deck;
	private String firstPlayerName, secondPlayerName;

	/**
	 * Class constructor.
	 */
	public BoardGameGUI2PlayersPerimaire (GameController gameController, String[] playersNames,
																String[] shuffledDeck, int firstToPlay) {
		super();
		/* Member Data Initialization */
		this.gameController = gameController;
		firstPlayerScore = 12;
		secondPlayerScore = 12;
		stuckGameCondition = 0;
		firstPlayerName = playersNames[0];
		secondPlayerName = playersNames[1];
		/* Button Initialization */
		firstPlayerPassTurnButton = new JButton("Passe Tour");
		secondPlayerPassTurnButton = new JButton("Passe Tour");
		/* Labels Creation */
		firstPlayerNameLabel = new JLabel("Jeu de " + firstPlayerName);
		secondPlayerNameLabel = new JLabel("Jeu de " + secondPlayerName);
		firstPlayerScoreLabel = new JLabel("Score: " + firstPlayerScore);
		secondPlayerScoreLabel = new JLabel("Score: " + secondPlayerScore);
		informationMessageLabel = new JLabel();
		/* Panels Creation */
		firstPlayerCardPanel = new JPanel(new GridBagLayout());
		centralCardPanel = new JPanel(new GridBagLayout());
		secondPlayerCardPanel = new JPanel(new GridBagLayout());
		firstPlayerPanel = new JPanel();
		centralPanel = new JPanel();
		secondPlayerPanel = new JPanel();
		/* Cards Creation */
		deck = new Stack<CardGUI>();
		for (int i = 0; i < GameState.PERIMAIRE_DECK_DIMENSION; i++)
			deck.push(new CardGUI(shuffledDeck[i]));
		/* Initialization */
		init(firstToPlay);
	}
	
	@Override
	protected void init (int firstToPlay) {
		/* Button Handling */
		firstPlayerPassTurnButton.setPreferredSize(new Dimension(115, 20));
		firstPlayerPassTurnButton.setBorderPainted(false);
		firstPlayerPassTurnButton.setContentAreaFilled(false);
		firstPlayerPassTurnButton.setFocusable(false);
		firstPlayerPassTurnButton.addActionListener(new PassTurnButtonListener());
		secondPlayerPassTurnButton.setPreferredSize(new Dimension(115, 20));
		secondPlayerPassTurnButton.setBorderPainted(false);
		secondPlayerPassTurnButton.setContentAreaFilled(false);
		secondPlayerPassTurnButton.setFocusable(false);
		secondPlayerPassTurnButton.addActionListener(new PassTurnButtonListener());
		/* First Player Name Label Handling */
		firstPlayerNameLabel.setPreferredSize(new Dimension(120,20));
		firstPlayerNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		/* Second Player Name Label Handling */
		secondPlayerNameLabel.setPreferredSize(new Dimension(120,20));
		secondPlayerNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		/* First Player Score Label Handling */
		firstPlayerScoreLabel.setPreferredSize(new Dimension(120, 20));
		firstPlayerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Second Player Score Label Handling */
		secondPlayerScoreLabel.setPreferredSize(new Dimension(120, 20));
		secondPlayerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Information Message Label Handling */
		informationMessageLabel.setPreferredSize(new Dimension(260, 100));
		informationMessageLabel.setHorizontalAlignment(JLabel.CENTER);
		informationMessageLabel.setVerticalAlignment(JLabel.TOP);
		/* Player Card Panels Handling */
		/* This switch is used to handle the initial distribution
		 * of the cards; it is performed on the basis of the order
		 * with which the different players have to make a move.
		 */
		switch (firstToPlay) {
			case 1: configurePlayerCardPanel(firstPlayerCardPanel);
					configurePlayerCardPanel(secondPlayerCardPanel);
					enablePlayerPanel(secondPlayerCardPanel, false);
					informationMessageLabel.setText("Le premier joueur sera: " + firstPlayerName);
					break;
			case 2: configurePlayerCardPanel(secondPlayerCardPanel);
					configurePlayerCardPanel(firstPlayerCardPanel);
					enablePlayerPanel(firstPlayerCardPanel, false);
					informationMessageLabel.setText("Le premier joueur sera: " + secondPlayerName);
					break;
			default: break;
		}
		/* Central Card Panel Handling */
		centralCardPanel.setPreferredSize(new Dimension(260, 410));
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(300, 510));
		firstPlayerPanel.add(firstPlayerNameLabel);
		firstPlayerPanel.add(firstPlayerScoreLabel);
		firstPlayerPanel.add(firstPlayerCardPanel);
		firstPlayerPanel.add(firstPlayerPassTurnButton);
		/* Central Panel Handling */
		centralPanel.setPreferredSize(new Dimension(260, 510));
		centralPanel.add(centralCardPanel);
		centralPanel.add(informationMessageLabel);
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(300, 510));
		secondPlayerPanel.add(secondPlayerNameLabel);
		secondPlayerPanel.add(secondPlayerScoreLabel);
		secondPlayerPanel.add(secondPlayerCardPanel);
		secondPlayerPanel.add(secondPlayerPassTurnButton);
		/* Board Panel Handling */
		add(firstPlayerPanel);
		add(centralPanel);
		add(secondPlayerPanel);
	}
	
	/**
	 * Configures the generic playerCardPanel, positioning in it the necessary
	 * cards.
	 * @param playerCardPanel - the panel in which the cards must be positioned.
	 */
	private void configurePlayerCardPanel (JPanel playerCardPanel) {
		playerCardPanel.setPreferredSize(new Dimension(300, 450));
		CardGUI card;
		GridBagConstraints constraints = new GridBagConstraints();
		/* Positioning of the cards of the deck in the playerCardPanel */
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				card = deck.pop();
				card.addActionListener(new PlayerCardGUIListener());
				constraints.gridx = j;
				constraints.gridy = i;
				constraints.ipadx = 13;
				constraints.ipady = 10;
				playerCardPanel.add(card, constraints);
			}
		}
	}
	
	// TODO Check correctness.
	/**
	 * 
	 * @param playerCardPanel
	 * @param value
	 */
	private void enablePlayerPanel (JPanel playerCardPanel, boolean value) {
		enablePlayerCardPanel(playerCardPanel, value);
		if (playerCardPanel == firstPlayerCardPanel)
			firstPlayerPassTurnButton.setEnabled(value);
		if (playerCardPanel == secondPlayerCardPanel)
			secondPlayerPassTurnButton.setEnabled(value);
	}
	
	/**
	 * 
	 * @param playerCardPanel
	 * @param value
	 */
	private void enablePlayerCardPanel (JPanel playerCardPanel, boolean value) {
		int numberOfComponents = playerCardPanel.getComponentCount();
		for (int i = 0; i < (numberOfComponents); i++) {
			CardGUI card = (CardGUI)playerCardPanel.getComponent(i);
			card.enableCard(value);
		}
	}
	
    /* ------------------------------------ Player CardGUI Action Listener -------------------------------- */
	
	/** Inner class implementing the Action Listener for the CardGUI.
	  */
	public class PlayerCardGUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			/* If present, removal of the text contained in the informationMessageLabel */
			if (! informationMessageLabel.getText().isEmpty())
				informationMessageLabel.setText("");
			/* Handling of the move performed by the generic player */
			CardGUI playedCard = (CardGUI)action.getSource();
			String playedCardId = playedCard.getId(); //TODO Repetition in the positioning
			boolean moveValidity = gameController.handlePlayedCard(playedCardId);
			Container playedCardContainer = playedCard.getParent();
			/* Not valid move: notify the user that is move is not correct */
			if (!moveValidity) {
				informationMessageLabel.setText("Mouvement pas valable!");
			} 
			/* Valid move: positioning of the played card in the centralPanel */
			else {
				stuckGameCondition = 0;
				playedCard.setEnabled(false);
				positionPlayedCard(centralCardPanel, action);
				/* Enabling and disabling of the card panels 
				 * for the handling of the players' turns
				 */
				if (playedCardContainer == firstPlayerCardPanel) {
					firstPlayerScore--;
					gameController.updatePlayerScore(firstPlayerName, -1);
					firstPlayerScoreLabel.setText("Score: " + firstPlayerScore);
					enablePlayerPanel(firstPlayerCardPanel, false);
					enablePlayerPanel(secondPlayerCardPanel, true);
				}
				else {
					secondPlayerScore--;
					gameController.updatePlayerScore(secondPlayerName, -1);
					secondPlayerScoreLabel.setText("Score: " + secondPlayerScore);
					enablePlayerPanel(secondPlayerCardPanel, false);
					enablePlayerPanel(firstPlayerCardPanel, true);				
				}
			}
		}

	}
	
    /* ------------------------------------ Pass Turn Button Action Listener -------------------------------- */

	/** Inner class implementing the Action Listener for the Pass Turn Button.
	  */
	public class PassTurnButtonListener implements ActionListener {

		private Timer timer;
		
		public PassTurnButtonListener () {
			timer = new Timer(2000, new TimerListener());
			timer.setRepeats(false);			
		}
		
		@Override
		public void actionPerformed (ActionEvent action) {
			stuckGameCondition++;
			/* End Game Condition */
			if (stuckGameCondition == 2) {
				informationMessageLabel.setText("Aucun joueur peut faire un mouvement. La partie termine");
				timer.start();
			} else {
				/* Normal Game Condition - Passing Turns */
				JButton clickedButton = (JButton)action.getSource();
				Container buttonContainer = clickedButton.getParent();
				if (buttonContainer == firstPlayerPanel) {
					enablePlayerPanel(firstPlayerCardPanel, false);
					enablePlayerPanel(secondPlayerCardPanel, true);
				} else {
					enablePlayerPanel(secondPlayerCardPanel, false);
					enablePlayerPanel(firstPlayerCardPanel, true);
				}
			}
		}
		
		/* ------------------------------------------------------------------ */
		
		public class TimerListener implements ActionListener {
			
			@Override
			public void actionPerformed (ActionEvent action) {
				informationMessageLabel.setText("A gangé mammt.");
				timer.stop();
			}
			
		}
	
	}
	
}