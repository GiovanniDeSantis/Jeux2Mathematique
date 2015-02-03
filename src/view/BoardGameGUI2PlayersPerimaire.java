package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
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
	//	   It is defined in the main menu and in every board game.
	
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	private JButton firstPlayerPassTurnButton, secondPlayerPassTurnButton;
	private JPanel firstPlayerPanel, secondPlayerPanel, centralPanel;
	private JPanel firstPlayerCardPanel, secondPlayerCardPanel;
	private JLabel firstPlayerLabel, secondPlayerLabel, firstToPlayLabel, informationMessageLabel;
	private Stack<CardGUI> deck;

	/**
	 * Class constructor.
	 */
	public BoardGameGUI2PlayersPerimaire (GameController gameController, String[] playersNames,
																String[] shuffledDeck, int firstToPlay) {
		super();
		/* Member Data Initialization */
		this.gameController = gameController;
		/* Button Initialization */
		firstPlayerPassTurnButton = new JButton("Passe Tour");
		secondPlayerPassTurnButton = new JButton("Passe Tour");
		/* Labels Creation */
		firstPlayerLabel = new JLabel("Jeu de " + playersNames[0]);
		secondPlayerLabel = new JLabel("Jeu de " + playersNames[1]);
		firstToPlayLabel = new JLabel();
		informationMessageLabel = new JLabel();
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
		/* First Player Label Handling */
		firstPlayerLabel.setPreferredSize(new Dimension(300,20));
		firstPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Second Player Label Handling */
		secondPlayerLabel.setPreferredSize(new Dimension(300,20));
		secondPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* First to Play Label Handling */
		firstToPlayLabel.setPreferredSize(new Dimension(260, 80));
		firstToPlayLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Information Message Label Handling */
		informationMessageLabel.setPreferredSize(new Dimension(260, 80));
		informationMessageLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Player Card Panels Handling */
		/* This switch is used to handle the initial distribution
		 * of the cards; it is performed on the basis of the order
		 * with which the different players have to make a move.
		 */
		switch (firstToPlay) {
			case 1: configurePlayerCardPanel(firstPlayerCardPanel);
					configurePlayerCardPanel(secondPlayerCardPanel);
					enablePlayerPanel(secondPlayerCardPanel, false);
					firstToPlayLabel.setText("Le premier joueur sera: " + playersNames[0]);
					break;
			case 2: configurePlayerCardPanel(secondPlayerCardPanel);
					configurePlayerCardPanel(firstPlayerCardPanel);
					enablePlayerPanel(firstPlayerCardPanel, false);
					firstToPlayLabel.setText("Le premier joueur sera: " + playersNames[1]);
					break;
			default: break;
		}
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(300, 510));
		firstPlayerPanel.add(firstPlayerLabel);
		firstPlayerPanel.add(firstPlayerCardPanel);
		firstPlayerPanel.add(firstPlayerPassTurnButton);
		/* Central Panel Handling */
		centralPanel.setPreferredSize(new Dimension(260, 490));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridwidth = 4;
		centralPanel.add(informationMessageLabel, constraints, 0);
		centralPanel.add(firstToPlayLabel);
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(300, 510));
		secondPlayerPanel.add(secondPlayerLabel);
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
			/* If present, removal of the firstToPlayLabel from the centralPanel */
			if (firstToPlayLabel.isShowing()) {
				firstToPlayLabel.setVisible(false);
				centralPanel.remove(firstToPlayLabel);
			}
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
				playedCard.setEnabled(false);
				positionPlayedCard(centralPanel, action);
				/* Enabling and disabling of the card panels 
				 * for the handling of the players' turns
				 */
				if (playedCardContainer == firstPlayerCardPanel) {
					enablePlayerPanel(firstPlayerCardPanel, false);
					enablePlayerPanel(secondPlayerCardPanel, true);
				}
				else {
					enablePlayerPanel(secondPlayerCardPanel, false);
					enablePlayerPanel(firstPlayerCardPanel, true);				
				}
			}
			/* End of Game Handling */
			if (playedCardContainer.getComponentCount() == 0)
				informationMessageLabel.setText("Jeu terminé");
		}

	}
	
    /* ------------------------------------ Pass Turn Button Action Listener -------------------------------- */

	/** Inner class implementing the Action Listener for the Pass Turn Button.
	  */
	public class PassTurnButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			/* If present, removal of the firstToPlayLabel from the centralPanel */
			if (firstToPlayLabel.isShowing()) {
				firstToPlayLabel.setVisible(false);
				centralPanel.remove(firstToPlayLabel);
			}
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
	
}
