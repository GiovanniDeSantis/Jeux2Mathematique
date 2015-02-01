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
 * case there are four players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class BoardGameGUI4PlayersPerimaire extends BoardGameGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel firstPlayerPanel, secondPlayerPanel, thirdPlayerPanel, fourthPlayerPanel, centerPanel;
	private JPanel firstPlayerCardPanel, secondPlayerCardPanel, thirdPlayerCardPanel, fourthPlayerCardPanel;
	private JLabel firstPlayerLabel, secondPlayerLabel, thirdPlayerLabel, fourthPlayerLabel;
	private List<CardGUI> deck;
	
	/**
	 * Class constructor.
	 */
	public BoardGameGUI4PlayersPerimaire (String[] playersNames, String[] shuffledDeck) {
		super();
		setLayout(new BorderLayout());
		/* Labels Creation */
		firstPlayerLabel = new JLabel("Jeu de " + playersNames[0]);
		secondPlayerLabel = new JLabel("Jeu de " + playersNames[1]);
		thirdPlayerLabel = new JLabel("Jeu de " + playersNames[2]);
		fourthPlayerLabel = new JLabel("Jeu de " + playersNames[3]);
		/* Panels Creation */
		firstPlayerCardPanel = new JPanel(new GridBagLayout());
		secondPlayerCardPanel = new JPanel(new GridBagLayout());
		thirdPlayerCardPanel = new JPanel(new GridBagLayout());
		fourthPlayerCardPanel = new JPanel(new GridBagLayout());
		firstPlayerPanel = new JPanel();
		secondPlayerPanel = new JPanel();
		thirdPlayerPanel = new JPanel();
		fourthPlayerPanel = new JPanel();
		centerPanel = new JPanel(new GridBagLayout());
		/* Cards Creation */
		deck = new ArrayList<CardGUI>(GameState.PERIMAIRE_DECK_DIMENSION);
		for (int i = 0; i < GameState.PERIMAIRE_DECK_DIMENSION; i++)
			deck.add(new CardGUI("" + shuffledDeck[i] + ".png"));
		/* Initialization */
		init();
	}
	
	@Override
	protected void init () {
		/* First Player Label Handling */
		firstPlayerLabel.setPreferredSize(new Dimension(370,20));
		firstPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Second Player Label Handling */
		secondPlayerLabel.setPreferredSize(new Dimension(1000,20));
		secondPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Third Player Label Handling */
		thirdPlayerLabel.setPreferredSize(new Dimension(370,20));
		thirdPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Fourth Player Label Handling */
		fourthPlayerLabel.setPreferredSize(new Dimension(1000,20));
		fourthPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* First Player Card Panel Handling */
		configurePlayerCardPanel(firstPlayerCardPanel);
		/* Second Player Card Panel Handling */
		configurePlayerCardPanel(secondPlayerCardPanel);
		/* Third Player Card Panel Handling */
		configurePlayerCardPanel(thirdPlayerCardPanel);
		/* Fourth Player Card Panel Handling */
		configurePlayerCardPanel(fourthPlayerCardPanel);
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(370, 265));
		firstPlayerPanel.add(firstPlayerLabel);
		firstPlayerPanel.add(firstPlayerCardPanel);
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(1000, 150));
		secondPlayerPanel.add(secondPlayerLabel);
		secondPlayerPanel.add(secondPlayerCardPanel);
		/* Third Player Panel Handling */
		thirdPlayerPanel.setPreferredSize(new Dimension(370, 265));
		thirdPlayerPanel.add(thirdPlayerLabel);
		thirdPlayerPanel.add(thirdPlayerCardPanel);
		/* Fourth Player Panel Handling */
		fourthPlayerPanel.setPreferredSize(new Dimension(1000, 150));
		fourthPlayerPanel.add(fourthPlayerLabel);
		fourthPlayerPanel.add(fourthPlayerCardPanel);
		/* Center Panel Handling */
		centerPanel.setPreferredSize(new Dimension(200, 265));
		/* Board Panel Handling */
		add(firstPlayerPanel, BorderLayout.WEST);
		add(secondPlayerPanel, BorderLayout.NORTH);
		add(thirdPlayerPanel, BorderLayout.EAST);
		add(fourthPlayerPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Configures the generic playerCardPanel, positioning in it the necessary
	 * cards.
	 * @param playerCardPanel - the panel in which the cards must be positioned.
	 */
	private void configurePlayerCardPanel (JPanel playerCardPanel) {
		
		/* Setting the preferred size of the playerCardPanel */
		if (playerCardPanel == firstPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(345, 260));
		if (playerCardPanel == secondPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(900, 115));
		if (playerCardPanel == thirdPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(345, 260));
		if (playerCardPanel == fourthPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(900, 115));
		/* Positioning of the cards of the deck in the playerCardPanel */
		CardGUI card = null;
		GridBagConstraints constraints = new GridBagConstraints();
		/* firstPlayerCardPanel */
		if (playerCardPanel == firstPlayerCardPanel) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 3; j++) {
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
		/* secondPlayerCardPanel */
		if (playerCardPanel == secondPlayerCardPanel) {
			for (int i = 0; i < 6; i++) {
						card = deck.get((GameState.PERIMAIRE_DECK_DIMENSION/4) + i);
						card.addActionListener(new PlayerCardGUIListener());
						constraints.gridx = i;
						constraints.ipadx = 13;
						constraints.ipady = 10;
						playerCardPanel.add(card, constraints);
			}
		}
		/* thirdPlayerCardPanel */
		if (playerCardPanel == thirdPlayerCardPanel) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 3; j++) {
					card = deck.get(((GameState.PERIMAIRE_DECK_DIMENSION/4) * 2) + (i + j) + (i * 2));
					card.addActionListener(new PlayerCardGUIListener());
					constraints.gridx = j;
					constraints.gridy = i;
					constraints.ipadx = 13;
					constraints.ipady = 10;
					playerCardPanel.add(card, constraints);
				}
			}
		}
		/* fourthPlayerCardPanel */
		if (playerCardPanel == fourthPlayerCardPanel) {
			for (int i = 0; i < 6; i++) {
					card = deck.get(((GameState.PERIMAIRE_DECK_DIMENSION/4) * 3) + i);
					card.addActionListener(new PlayerCardGUIListener());
					constraints.gridx = i;
					constraints.ipadx = 13;
					constraints.ipady = 10;
					playerCardPanel.add(card, constraints);
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
			playedCardPositioning(centerPanel, action);
		}

	}
}