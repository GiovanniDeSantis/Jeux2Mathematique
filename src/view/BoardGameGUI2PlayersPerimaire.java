package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;
import model.GameState;

/**
 * Class that implements the User Interface of the game board in the
 * case there are two players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class BoardGameGUI2PlayersPerimaire extends BoardGameGUI {
	
	//TODO Check the handling of the game controller. Static class or not.
	//	   It is defined in the main menu and in each board game.
	
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	private JPanel firstPlayerPanel, secondPlayerPanel, centralPanel;
	private JPanel firstPlayerCardPanel, secondPlayerCardPanel;
	private JLabel firstPlayerLabel, secondPlayerLabel, firstToPlayLabel;
	private Stack<CardGUI> deck;

	/**
	 * Class constructor.
	 */
	public BoardGameGUI2PlayersPerimaire (GameController gameController, String[] playersNames,
																String[] shuffledDeck, int firstToPlay) {
		super();
		/* Member Data Initialization */
		this.gameController = gameController;
		/* Labels Creation */
		firstPlayerLabel = new JLabel("Jeu de " + playersNames[0]);
		secondPlayerLabel = new JLabel("Jeu de " + playersNames[1]);
		firstToPlayLabel = new JLabel();
		/* Panels Creation */
		firstPlayerCardPanel = new JPanel(new GridBagLayout());
		secondPlayerCardPanel = new JPanel(new GridBagLayout());
		firstPlayerPanel = new JPanel();
		centralPanel = new JPanel(new GridBagLayout());
		secondPlayerPanel = new JPanel();
		/* Cards Creation */
		deck = new Stack<CardGUI>();
		for (int i = 0; i < GameState.PERIMAIRE_DECK_DIMENSION; i++)
			deck.push(new CardGUI(shuffledDeck[i]));
		/* Initialization */
		init(playersNames, firstToPlay);
	}
	
	@Override
	protected void init (String[] playersNames, int firstToPlay) {
		/* First Player Label Handling */
		firstPlayerLabel.setPreferredSize(new Dimension(300,20));
		firstPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Second Player Label Handling */
		secondPlayerLabel.setPreferredSize(new Dimension(300,20));
		secondPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* First to Play Label Handling */
		firstToPlayLabel.setPreferredSize(new Dimension(260, 80));
		firstToPlayLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Player Card Panels Handling */
		switch (firstToPlay) {
			case 1: configurePlayerCardPanel(firstPlayerCardPanel);
					configurePlayerCardPanel(secondPlayerCardPanel);
					enablePlayerCardPanel(secondPlayerCardPanel, false);
					firstToPlayLabel.setText("Le premier joueur sera: " + playersNames[0]);
					break;
			case 2: configurePlayerCardPanel(secondPlayerCardPanel);
					configurePlayerCardPanel(firstPlayerCardPanel);
					enablePlayerCardPanel(firstPlayerCardPanel, false);
					firstToPlayLabel.setText("Le premier joueur sera: " + playersNames[1]);
					break;
			default: break;
		}
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(300, 490));
		firstPlayerPanel.add(firstPlayerLabel);
		firstPlayerPanel.add(firstPlayerCardPanel);
		/* Central Panel Handling */
		centralPanel.setPreferredSize(new Dimension(260, 490));
		centralPanel.add(firstToPlayLabel);
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(300, 490));
		secondPlayerPanel.add(secondPlayerLabel);
		secondPlayerPanel.add(secondPlayerCardPanel);
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
		/* Positioning of the cards of the deck in the playerCardPanel */
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				CardGUI card = deck.pop();
				card.addActionListener(new PlayerCardGUIListener());
				GridBagConstraints constraints = new GridBagConstraints();
				constraints.gridx = j;
				constraints.gridy = i;
				constraints.ipadx = 13;
				constraints.ipady = 10;
				playerCardPanel.add(card, constraints);
			}
		}
	}
	
	/**
	 * Enable/disable all the CardGUI of a given playerCardPanel.
	 * @param playerCardPanel - the panel in which the cards must be enabled/disabled.
	 * @param value - if 'true', the cards are enabled;
	 * 				  if 'false', the cards are disabled.
	 */
	private void enablePlayerCardPanel (JPanel playerCardPanel, boolean value) {
		int numberOfComponents = playerCardPanel.getComponentCount();
		for (int i = 0; i < numberOfComponents; i++) {
			CardGUI card = (CardGUI)playerCardPanel.getComponent(i);
			card.enableCard(value);
		}
	}
	
    /* ------------------------------------ First Player CardGUI Action Listener -------------------------------- */
	
	/** Inner class implementing the Action Listener for the CardGUI.
	  */
	public class PlayerCardGUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			/* Removal of the firstToPlayLabel from the centralPanel */
			if (firstToPlayLabel.isShowing()) {
				firstToPlayLabel.setVisible(false);
				centralPanel.remove(firstToPlayLabel);
			}
			/*  */
			CardGUI playedCard = (CardGUI)action.getSource();
			String playedCardId = playedCard.getId();
			gameController.handlePlayedCard(playedCardId); //TODO To complete.
			/* Positioning of the played card in the centralPanel */
			playedCard.setEnabled(false);
			positionPlayedCard(centralPanel, action);
			/* Enabling and disabling of the card panels 
			 * for the handling of the players' turns
			 */
			if (playedCard.getParent() == firstPlayerCardPanel) {
				enablePlayerCardPanel(firstPlayerCardPanel, false);
				enablePlayerCardPanel(secondPlayerCardPanel, true);
			}
			else {
				enablePlayerCardPanel(secondPlayerCardPanel, false);
				enablePlayerCardPanel(firstPlayerCardPanel, true);				
			}
		}

	}
}
