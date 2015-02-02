package view;

import java.awt.BorderLayout;
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
 * case there are three players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class BoardGameGUI3PlayersPerimaire extends BoardGameGUI {
	
	//TODO Check the handling of the game controller. Static class or not.
	//	   It is defined in the main menu and in every board game.
		
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	private JPanel firstPlayerPanel, secondPlayerPanel, thirdPlayerPanel, centralPanel;
	private JPanel firstPlayerCardPanel, secondPlayerCardPanel, thirdPlayerCardPanel;
	private JLabel firstPlayerLabel, secondPlayerLabel, thirdPlayerLabel, firstToPlayLabel, notValidMoveLabel;
	private Stack<CardGUI> deck;
	
	/**
	 * Class constructor.
	 */
	public BoardGameGUI3PlayersPerimaire (GameController gameController, String[] playersNames,
																String[] shuffledDeck, int firstToPlay) {
		super();
		setLayout(new BorderLayout());
		/* Member Data Initialization */
		this.gameController = gameController;
		/* Labels Creation */
		firstPlayerLabel = new JLabel("Jeu de " + playersNames[0]);
		secondPlayerLabel = new JLabel("Jeu de " + playersNames[1]);
		thirdPlayerLabel = new JLabel("Jeu de " + playersNames[2]);
		firstToPlayLabel = new JLabel();
		notValidMoveLabel = new JLabel();
		/* Panels Creation */
		firstPlayerCardPanel = new JPanel(new GridBagLayout());
		secondPlayerCardPanel = new JPanel(new GridBagLayout());
		thirdPlayerCardPanel = new JPanel(new GridBagLayout());
		firstPlayerPanel = new JPanel();
		secondPlayerPanel = new JPanel();
		thirdPlayerPanel = new JPanel();
		centralPanel = new JPanel(new GridBagLayout());
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
		firstPlayerLabel.setPreferredSize(new Dimension(350,20));
		firstPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Second Player Label Handling */
		secondPlayerLabel.setPreferredSize(new Dimension(1000,20));
		secondPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Third Player Label Handling */
		thirdPlayerLabel.setPreferredSize(new Dimension(350,20));
		thirdPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* First to Play Label Handling */
		firstToPlayLabel.setPreferredSize(new Dimension(300, 80));
		firstToPlayLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Error Label Handling */
		notValidMoveLabel.setPreferredSize(new Dimension(300, 80));
		notValidMoveLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Player Card Panels Handling */
		/* This switch is used to handle the initial distribution
		 * of the cards; it is performed on the basis of the order
		 * with which the different players have to make a move.
		 */
		switch (firstToPlay) {
			case 1: configurePlayerCardPanel(firstPlayerCardPanel);
					configurePlayerCardPanel(secondPlayerCardPanel);
					configurePlayerCardPanel(thirdPlayerCardPanel);
					enablePlayerCardPanel(secondPlayerCardPanel, false);
					enablePlayerCardPanel(thirdPlayerCardPanel, false);
					firstToPlayLabel.setText("Le premier joueur sera: " + playersNames[0]);
					break;
			case 2: configurePlayerCardPanel(secondPlayerCardPanel);
					configurePlayerCardPanel(thirdPlayerCardPanel);
					configurePlayerCardPanel(firstPlayerCardPanel);
					enablePlayerCardPanel(thirdPlayerCardPanel, false);
					enablePlayerCardPanel(firstPlayerCardPanel, false);
					firstToPlayLabel.setText("Le premier joueur sera: " + playersNames[1]);
					break;
			case 3: configurePlayerCardPanel(thirdPlayerCardPanel);
					configurePlayerCardPanel(firstPlayerCardPanel);
					configurePlayerCardPanel(secondPlayerCardPanel);
					enablePlayerCardPanel(firstPlayerCardPanel, false);
					enablePlayerCardPanel(secondPlayerCardPanel, false);
					firstToPlayLabel.setText("Le premier joueur sera: " + playersNames[2]);
					break;
			default: break;
		}		
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(350, 360));
		firstPlayerPanel.add(firstPlayerLabel);
		firstPlayerPanel.add(firstPlayerCardPanel);
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(1000, 255));
		secondPlayerPanel.add(secondPlayerLabel);
		secondPlayerPanel.add(secondPlayerCardPanel);
		/* Third Player Panel Handling */
		thirdPlayerPanel.setPreferredSize(new Dimension(350, 360));
		thirdPlayerPanel.add(thirdPlayerLabel);
		thirdPlayerPanel.add(thirdPlayerCardPanel);
		/* Center Panel Handling */
		centralPanel.setPreferredSize(new Dimension(300, 360));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridwidth = 4;
		centralPanel.add(notValidMoveLabel, constraints, 0);
		centralPanel.add(firstToPlayLabel);
		/* Board Panel Handling */
		add(firstPlayerPanel, BorderLayout.WEST);
		add(secondPlayerPanel, BorderLayout.NORTH);
		add(thirdPlayerPanel, BorderLayout.EAST);
		add(centralPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Configures the generic playerCardPanel, positioning in it the necessary
	 * cards.
	 * @param playerCardPanel - the panel in which the cards must be positioned.
	 */
	private void configurePlayerCardPanel (JPanel playerCardPanel) {
		
		/* Setting the preferred size of the playerCardPanel */
		if (playerCardPanel == firstPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(325, 325));
		if (playerCardPanel == secondPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(900, 220));
		if (playerCardPanel == thirdPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(325, 325));
		/* Positioning of the cards of the deck in the playerCardPanel */
		CardGUI card = null;
		GridBagConstraints constraints = new GridBagConstraints();
		/* firstPlayerCardPanel */
		if (playerCardPanel == firstPlayerCardPanel) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (((i + j) + (i * 2)) != 8) {
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
		}
		/* secondPlayerCardPanel */
		if (playerCardPanel == secondPlayerCardPanel) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++) {
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
		/* thirdPlayerCardPanel */
		if (playerCardPanel == thirdPlayerCardPanel) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (((i + j) + (i * 2)) != 8) {
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

    /* ------------------------------------ Player CardGUI Action Listener -------------------------------- */
	
	/** Inner class implementing the Action Listener for the CardGUI.
	  */
	public class PlayerCardGUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			/* If present, removal of the firstToPlayLabel from the centralPanel */
			if (firstToPlayLabel.isShowing()) {
				firstToPlayLabel.setVisible(false);
				centralPanel.remove(firstToPlayLabel);
			}
			/* If present, removal of the notValidMoveLabel from the centralPanel */
			if (! notValidMoveLabel.getText().isEmpty())
				notValidMoveLabel.setText("");
			/* Handling of the move performed by the generic player */
			CardGUI playedCard = (CardGUI)action.getSource();
			String playedCardId = playedCard.getId(); //TODO Repetition in the positioning
			boolean moveValidity = gameController.handlePlayedCard(playedCardId);
			/* Not valid move: notify the user that is move is not correct */
			if (!moveValidity) {
				notValidMoveLabel.setText("Mouvement pas valable!");
			} 
			/* Valid move: positioning of the played card in the centralPanel */
			else {
				playedCard.setEnabled(false);
				positionPlayedCard(centralPanel, action);
				/* Enabling and disabling of the card panels 
				 * for the handling of the players' turns
				 */
				if (playedCard.getParent() == firstPlayerCardPanel) {
					enablePlayerCardPanel(firstPlayerCardPanel, false);
					enablePlayerCardPanel(secondPlayerCardPanel, true);
				}
				if (playedCard.getParent() == secondPlayerCardPanel) {
					enablePlayerCardPanel(secondPlayerCardPanel, false);
					enablePlayerCardPanel(thirdPlayerCardPanel, true);				
				}
				if (playedCard.getParent() == thirdPlayerCardPanel) {
					enablePlayerCardPanel(thirdPlayerCardPanel, false);
					enablePlayerCardPanel(firstPlayerCardPanel, true);
				}
			}
		}
	}

}