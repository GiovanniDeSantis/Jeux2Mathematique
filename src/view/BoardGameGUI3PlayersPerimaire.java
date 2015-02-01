package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameState;

/**
 * Class that implements the User Interface of the game board in the
 * case there are three players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class BoardGameGUI3PlayersPerimaire extends BoardGameGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel firstPlayerPanel, secondPlayerPanel, thirdPlayerPanel, centralPanel;
	private JPanel firstPlayerCardPanel, secondPlayerCardPanel, thirdPlayerCardPanel;
	private JLabel firstPlayerLabel, secondPlayerLabel, thirdPlayerLabel;
	private List<CardGUI> deck;
	
	/**
	 * Class constructor.
	 */
	public BoardGameGUI3PlayersPerimaire (String[] playersNames, String[] shuffledDeck) {
		super();
		setLayout(new BorderLayout());
		/* Labels Creation */
		firstPlayerLabel = new JLabel("Jeu de " + playersNames[0]);
		secondPlayerLabel = new JLabel("Jeu de " + playersNames[1]);
		thirdPlayerLabel = new JLabel("Jeu de " + playersNames[2]);
		/* Panels Creation */
		firstPlayerCardPanel = new JPanel(new GridBagLayout());
		secondPlayerCardPanel = new JPanel(new GridBagLayout());
		thirdPlayerCardPanel = new JPanel(new GridBagLayout());
		firstPlayerPanel = new JPanel();
		secondPlayerPanel = new JPanel();
		thirdPlayerPanel = new JPanel();
		centralPanel = new JPanel(new GridBagLayout());
		/* Cards Creation */
		deck = new ArrayList<CardGUI>(GameState.PERIMAIRE_DECK_DIMENSION);
		for (int i = 0; i < GameState.PERIMAIRE_DECK_DIMENSION; i++)
			deck.add(new CardGUI(shuffledDeck[i]));
		/* Initialization */
		init();
	}
	
	@Override
	protected void init () {
		/* First Player Label Handling */
		firstPlayerLabel.setPreferredSize(new Dimension(400,20));
		firstPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Second Player Label Handling */
		secondPlayerLabel.setPreferredSize(new Dimension(400,20));
		secondPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Third Player Label Handling */
		thirdPlayerLabel.setPreferredSize(new Dimension(1000,20));
		thirdPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* First Player Card Panel Handling */
		configurePlayerCardPanel(firstPlayerCardPanel);
		/* Second Player Card Panel Handling */
		configurePlayerCardPanel(secondPlayerCardPanel);
		/* Third Player Card Panel Handling */
		configurePlayerCardPanel(thirdPlayerCardPanel);
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(400, 360));
		firstPlayerPanel.add(firstPlayerLabel);
		firstPlayerPanel.add(firstPlayerCardPanel);
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(1000, 255));
		secondPlayerPanel.add(secondPlayerLabel);
		secondPlayerPanel.add(secondPlayerCardPanel);
		/* Third Player Panel Handling */
		thirdPlayerPanel.setPreferredSize(new Dimension(400, 360));
		thirdPlayerPanel.add(thirdPlayerLabel);
		thirdPlayerPanel.add(thirdPlayerCardPanel);
		/* Center Panel Handling */
		centralPanel.setPreferredSize(new Dimension(200, 360));
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
			playerCardPanel.setPreferredSize(new Dimension(375, 325));
		if (playerCardPanel == secondPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(900, 220));
		if (playerCardPanel == thirdPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(375, 325));
		/* Positioning of the cards of the deck in the playerCardPanel */
		CardGUI card = null;
		GridBagConstraints constraints = new GridBagConstraints();
		/* firstPlayerCardPanel */
		if (playerCardPanel == firstPlayerCardPanel) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (((i + j) + (i * 2)) != 8) {
						card = deck.get((i + j) + (i * 2));
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
					card = deck.get((GameState.PERIMAIRE_DECK_DIMENSION/3) + (i + j) + (i * 3));
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
					if ((((GameState.PERIMAIRE_DECK_DIMENSION/3) * 2) + (i + j) + (i * 2)) != 24) {
						card = deck.get(((GameState.PERIMAIRE_DECK_DIMENSION/3) * 2) + (i + j) + (i * 2));
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

    /* ------------------------------------ First Player CardGUI Action Listener -------------------------------- */
	
	/** Inner class implementing the Action Listener for the CardGUI.
	  */
	public class PlayerCardGUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			Component component = (Component)action.getSource();
			component.setEnabled(false);
			positionPlayedCard(centralPanel, action);
		}

	}
}