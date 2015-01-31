package view;

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

/**
 * Class that implements the User Interface of the game board in the
 * case there are two players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class BoardGameGUI2PlayersPerimaire extends BoardGameGUI {

	// TODO Better handle this thing.
	private final int deckDimension = 24;
	
	private static final long serialVersionUID = 1L;
	private JPanel firstPlayerPanel, secondPlayerPanel, centerPanel;
	private JPanel firstPlayerCardPanel, secondPlayerCardPanel;
	private JLabel firstPlayerLabel, secondPlayerLabel;
	private List<CardGUI> deck;

	/**
	 * Class constructor.
	 */
	public BoardGameGUI2PlayersPerimaire (String[] playersNames) {
		super();
		/* Labels Creation */
		firstPlayerLabel = new JLabel("Jeu de " + playersNames[0]);
		secondPlayerLabel = new JLabel("Jeu de " + playersNames[1]);
		/* Panels Creation */
		firstPlayerCardPanel = new JPanel(new GridBagLayout());
		secondPlayerCardPanel = new JPanel(new GridBagLayout());
		firstPlayerPanel = new JPanel();
		centerPanel = new JPanel(new GridBagLayout());
		secondPlayerPanel = new JPanel();
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
		firstPlayerLabel.setPreferredSize(new Dimension(300,20));
		firstPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Second Player Label Handling */
		secondPlayerLabel.setPreferredSize(new Dimension(300,20));
		secondPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* First Player Card Panel Handling */
		configurePlayerCardPanel(firstPlayerCardPanel);
		/* Second Player Card Panel Handling */
		configurePlayerCardPanel(secondPlayerCardPanel);
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(300, 490));
		firstPlayerPanel.add(firstPlayerLabel);
		firstPlayerPanel.add(firstPlayerCardPanel);
		/* Center Panel Handling */
		centerPanel.setPreferredSize(new Dimension(260, 490));
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(300, 490));
		secondPlayerPanel.add(secondPlayerLabel);
		secondPlayerPanel.add(secondPlayerCardPanel);
		/* Board Panel Handling */
		add(firstPlayerPanel);
		add(centerPanel);
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
				CardGUI card;
				/* firstPlayerCardPanel */
				if (playerCardPanel == firstPlayerCardPanel)
					card = deck.get((i + j) + (i * 2));
				/* secondPlayerCardPanel */
				else
					card = deck.get((deckDimension/2) + (i + j) + (i * 2));
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
