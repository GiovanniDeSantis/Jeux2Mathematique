package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that implements the User Interface of the game board in the
 * case there are three players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class BoardGameGUI4PlayersPerimaire extends BoardGameGUI {
	
	// TODO Better handle this thing.
	private final int deckDimension = 24;

	private static final long serialVersionUID = 1L;
	private JPanel firstPlayerPanel, secondPlayerPanel, thirdPlayerPanel, centerPanel;
	private JPanel firstPlayerCardPanel, secondPlayerCardPanel, thirdPlayerCardPanel;
	private JLabel firstPlayerLabel, secondPlayerLabel, thirdPlayerLabel;
	private List<CardGUI> deck;
	
	/**
	 * Class constructor.
	 */
	public BoardGameGUI4PlayersPerimaire (String[] playersNames) {
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
		centerPanel = new JPanel(new GridBagLayout());
		/* Cards Creation */
		//TODO To generalize and modify.
		deck = new ArrayList<>(deckDimension);
		for (int i = 1; i <= deckDimension; i++)
			deck.add(new CardGUI("" + i + ".png"));
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
		/* Center Panel Handling */
		centerPanel.setPreferredSize(new Dimension(200, 360));
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(400, 360));
		secondPlayerPanel.add(secondPlayerLabel);
		secondPlayerPanel.add(secondPlayerCardPanel);
		/* Third Player Panel Handling */
		thirdPlayerPanel.setPreferredSize(new Dimension(1000, 255));
		thirdPlayerPanel.add(thirdPlayerLabel);
		thirdPlayerPanel.add(thirdPlayerCardPanel);
		/* Board Panel Handling */
		add(firstPlayerPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(secondPlayerPanel, BorderLayout.EAST);
		add(thirdPlayerPanel, BorderLayout.NORTH);
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
			playerCardPanel.setPreferredSize(new Dimension(375, 325));
		if (playerCardPanel == thirdPlayerCardPanel)
			playerCardPanel.setPreferredSize(new Dimension(900, 220));
		/* Positioning of the cards of the deck in the playerCardPanel */
		CardGUI card = null;
		GridBagConstraints constraints = new GridBagConstraints();
		/* firstPlayerCardPanel */
		if (playerCardPanel == firstPlayerCardPanel) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (((i + j) + (i * 2)) != 8) {
						card = deck.get((i + j) + (i * 2));
						System.out.println("first: " + ((i + j) + (i * 2)));
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
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (((deckDimension/3) + (i + j) + (i * 2)) != 16) {
						card = deck.get((deckDimension/3) + (i + j) + (i * 2));
						System.out.println("second: " + ((deckDimension/3) + (i + j) + (i * 2)));
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
		/* thirdPlayerCardPanel */
		if (playerCardPanel == thirdPlayerCardPanel) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++) {
					card = deck.get(((deckDimension/3) * 2) + (i + j) + (i * 3));
					System.out.println("third: " + (((deckDimension/3) * 2) + (i + j) + (i * 3)));
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
	
	/**
	 * Positions the card played at each move in the appropriate panel.
	 * @param referencePanel - the panel in which the played card must be placed.
	 * @param action - the event occurred on the played card.
	 */
	private void playedCardPositioning (Container referencePanel, ActionEvent action) {
		/* Retrieval of the card on which the event occurred */
		CardGUI sourceComponent = (CardGUI)action.getSource();
		/* The card on which the event has occurred is made transparent */
		sourceComponent.makeTransparent();
		/* Addition of the card to the panel in which are positioned the played cards */
		CardGUI playedCard = new CardGUI(sourceComponent.getSource());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 2;
		int numberComponents = centerPanel.getComponentCount();
		if (numberComponents >= 1) {
			int dx = randInt(-80, 80);
			int dy = randInt(-80, 80);
			constraints.insets.left = dx;
			constraints.insets.top = dy;
		}
		centerPanel.add(playedCard, constraints, 0);
	}
	
	/**
	 * Computes a random integer in a generic interval.
	 * @param min - the minimum value of the generic interval.
	 * @param max - the maximum value of the generic interval.
	 * @return a random integer in the interval [min, max].
	 */
	private int randInt (int min, int max) {
		Random random = new Random();
	    int randomNumber = random.nextInt((max - min) + 1) + min;

	    return randomNumber;
	}

    /* ------------------------------------ First Player CardGUI Action Listener -------------------------------- */
	
	/** Inner class implementing the Action Listener for the CardGUI.
	  */
	public class PlayerCardGUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			Component component = (Component)action.getSource();
			component.setEnabled(false);
			playedCardPositioning(component.getParent(), action);
		}

	}
}