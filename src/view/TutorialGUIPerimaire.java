package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameState;

/**
 * Class that implements the User Interface of the game board in the
 * case a tutorial must be shown.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class TutorialGUIPerimaire extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int tutorialPhase = 0;
	private JButton firstPlayerPassTurnButton, secondPlayerPassTurnButton, beforeButton, nextButton;
	private JPanel upperPanel, lowerPanel;
	private JPanel firstPlayerPanel, secondPlayerPanel, centralPanel;
	private JPanel firstPlayerCardPanel, secondPlayerCardPanel, centralCardPanel;
	private JLabel firstPlayerLabel, secondPlayerLabel, informationMessageLabel;
	private Stack<CardGUI> deck;

	/**
	 * Class constructor.
	 */
	public TutorialGUIPerimaire () {
		/* Button Creation */
		firstPlayerPassTurnButton = new JButton("Passe Tour");
		secondPlayerPassTurnButton = new JButton("Passe Tour");
		beforeButton = new JButton("Avant");
		nextButton = new JButton("Prochain");
		/* Labels Creation */
		firstPlayerLabel = new JLabel("Jeu de Carolina");
		secondPlayerLabel = new JLabel("Jeu de Mehdi");
		informationMessageLabel = new JLabel();
		/* Panels Creation */
		firstPlayerCardPanel = new JPanel(new GridBagLayout());
		secondPlayerCardPanel = new JPanel(new GridBagLayout());
		centralCardPanel = new JPanel(new GridBagLayout());
		firstPlayerPanel = new JPanel();
		centralPanel = new JPanel();
		secondPlayerPanel = new JPanel();
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		/* Cards Creation */
		String[] tutorialDeck = {"O", "N", "W", "B", "H", "D", "M", "I", "J", "A", "S", "F",
		"C", "T", "P", "V", "R", "K", "U", "X", "G", "E", "L", "Q"};
		deck = new Stack<CardGUI>();
		for (int i = 0; i < GameState.PERIMAIRE_DECK_DIMENSION; i++)
			deck.push(new CardGUI(tutorialDeck[i]));
		/* Initialization */
		init();
	}
	
	protected void init () {
		/* Button Handling */
		firstPlayerPassTurnButton.setPreferredSize(new Dimension(115, 20));
		firstPlayerPassTurnButton.setBorderPainted(false);
		firstPlayerPassTurnButton.setContentAreaFilled(false);
		firstPlayerPassTurnButton.setFocusable(false);
		secondPlayerPassTurnButton.setPreferredSize(new Dimension(115, 20));
		secondPlayerPassTurnButton.setBorderPainted(false);
		secondPlayerPassTurnButton.setContentAreaFilled(false);
		secondPlayerPassTurnButton.setFocusable(false);
		beforeButton.setPreferredSize(new Dimension(105, 35));
		beforeButton.setBorderPainted(false);
		beforeButton.setContentAreaFilled(false);
		beforeButton.setFocusable(false);
		beforeButton.addActionListener(new TutorialButtonListener());
		nextButton.setPreferredSize(new Dimension(105, 35));
		nextButton.setBorderPainted(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusable(false);
		nextButton.addActionListener(new TutorialButtonListener());
		/* First Player Label Handling */
		firstPlayerLabel.setPreferredSize(new Dimension(300,20));
		firstPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Second Player Label Handling */
		secondPlayerLabel.setPreferredSize(new Dimension(300,20));
		secondPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Information Message Label Handling */
		Dimension informationMessageLabelDimension = new Dimension(550, 50);
		informationMessageLabel.setMinimumSize(informationMessageLabelDimension);
		informationMessageLabel.setPreferredSize(informationMessageLabelDimension);
		informationMessageLabel.setMaximumSize(informationMessageLabelDimension);
		informationMessageLabel.setHorizontalAlignment(JLabel.CENTER);
		informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
				+ "Ce jeu consiste à empiler des cartes par égalités d'aires et de périmètres."
				+ "<br>Le but est être le premier à se défaire de ses cartes</div></html>");
		/* Player Card Panels Handling */
		configurePlayerCardPanel(firstPlayerCardPanel);
		configurePlayerCardPanel(secondPlayerCardPanel);
		/* Central Card Panel Handling */
		centralCardPanel.setPreferredSize(new Dimension(260, 490));
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(300, 510));
		firstPlayerPanel.add(firstPlayerLabel);
		firstPlayerPanel.add(firstPlayerCardPanel);
		firstPlayerPanel.add(firstPlayerPassTurnButton);
		enablePlayerPanel(firstPlayerCardPanel, false);
		/* Central Panel Handling */
		centralPanel.setPreferredSize(new Dimension(260, 490));
		centralPanel.add(centralCardPanel);
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(300, 510));
		secondPlayerPanel.add(secondPlayerLabel);
		secondPlayerPanel.add(secondPlayerCardPanel);
		secondPlayerPanel.add(secondPlayerPassTurnButton);
		enablePlayerPanel(secondPlayerCardPanel, false);
		/* Upper Panel Handling */
		upperPanel.add(firstPlayerPanel);
		upperPanel.add(centralPanel);
		upperPanel.add(secondPlayerPanel);
		/* Lower Panel Handling */
		lowerPanel.setPreferredSize(new Dimension(860, 55));
		lowerPanel.add(beforeButton);
		lowerPanel.add(informationMessageLabel);
		lowerPanel.add(nextButton);
		/* Board Panel Handling */
		setPreferredSize(new Dimension(860, 600));
		add(upperPanel);
		add(lowerPanel);
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
				constraints.gridx = j;
				constraints.gridy = i;
				constraints.ipadx = 13;
				constraints.ipady = 10;
				playerCardPanel.add(card, constraints);
			}
		}
	}
	
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
	
	/*----------------------------------------------------- */
	
	private class TutorialButtonListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent action) {
			JButton sourceButton = (JButton)action.getSource();
			if ((sourceButton == beforeButton) && (tutorialPhase > 0))
				tutorialPhase--;
			if ((sourceButton == nextButton) && (tutorialPhase < 19))
				tutorialPhase++;
			switch (tutorialPhase) {
				case 0: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Ce jeu consiste à empiler des cartes par égalités d'aires et de périmètres."
						+ "<br>Le but est être le premier à se défaire de ses cartes</div></html>");
						break;
				case 1: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Toute carte empilée doit représenter une figure de même aire ou de même "
						+ "périmètre que la figure de la carte du dessous.</div></html>");
						break;
				
				case 2: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Le joueur désigné pour débuter la partie, retire de son jeu la carte de "
						+ "son choix et la pose sur la table.<br>Le premier joueur est Carolina."
						+ "</div></html>");
						if (sourceButton == beforeButton)
							removeLastCard(firstPlayerCardPanel, 9);
						break;
				case 3: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Carolina joue la carte P de aire 5 et périmètre 12.<br> Le joueur "
						+ "suivant doit poser sur la carte précédente une carte de son jeu.</div></html>");
						if (sourceButton == beforeButton)
							removeLastCard(secondPlayerCardPanel, 7);
						if (sourceButton == nextButton)
							positionPlayedCard(firstPlayerCardPanel, 9);
						break;
				case 4: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Mehdi joue la carte H de <font color=\"red\">aire 5</font> et "
						+ "<font color=\"red\">périmètre 12</font>.<br>Poser une carte représentant"
						+ " une figure de même aire et de même périmètre permet de rejouer.</div></html>");
						if (sourceButton == beforeButton)
							removeLastCard(secondPlayerCardPanel, 10);
						if (sourceButton == nextButton)
							positionPlayedCard(secondPlayerCardPanel, 7);
						break;
				case 5: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Mehdi rejoue en posant la carte N de <font color=\"red\">aire 5</font> et "
						+ "<font color=\"red\">périmètre 12</font>.</div></html>");
						if (sourceButton == beforeButton)
							removeLastCard(secondPlayerCardPanel, 8);
						if (sourceButton == nextButton)
							positionPlayedCard(secondPlayerCardPanel, 10);
						break;
				case 6:	informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Mehdi rejoue en posant la carte B de <font color=\"red\">aire 5</font> et "
						+ "<font color=\"red\">périmètre 12</font>.</div></html>");
						if (sourceButton == beforeButton)
							removeLastCard(secondPlayerCardPanel, 6);
						if (sourceButton == nextButton)
							positionPlayedCard(secondPlayerCardPanel, 8);
						break;
				case 7:	informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Mehdi rejoue en posant la carte D de aire 7 et <font color=\"red\">périmètre 12"
						+ "</font>.</div></html>");
						if (sourceButton == beforeButton)
							removeLastCard(firstPlayerCardPanel, 0);
						if (sourceButton == nextButton)
							positionPlayedCard(secondPlayerCardPanel, 6);
						break;
				case 8:	informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Carolina joue la carte Q de <font color=\"red\">aire 7</font> et "
						+ "<font color=\"red\">périmètre 12</font>.</div></html>");
						if (sourceButton == beforeButton)
							removeLastCard(firstPlayerCardPanel, 7);
						if (sourceButton == nextButton)
							positionPlayedCard(firstPlayerCardPanel, 0);
						break;
				case 9:	informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Carolina rejoue en posant la carte R de <font color=\"red\">aire 7</font> et"
						+ " <font color=\"red\">périmètre 12</font>.</div></html>");
						if (sourceButton == beforeButton)
							removeLastCard(firstPlayerCardPanel, 4);
						if (sourceButton == nextButton)
							positionPlayedCard(firstPlayerCardPanel, 7);
						break;
				case 10: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						+ "Carolina rejoue en posant la carte X de <font color=\"red\">aire 7</font> et"
						+ " périmètre 14.</div></html>");
						if (sourceButton == beforeButton)
							removeLastCard(secondPlayerCardPanel, 0);
						if (sourceButton == nextButton)
							positionPlayedCard(firstPlayerCardPanel, 4);
						 break;
				case 11: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						 + "Mehdi pose la carte F de <font color=\"red\">aire 7</font> et périmètre 16."
						 + "</div></html>");
						 if (sourceButton == beforeButton)
							 removeLastCard(firstPlayerCardPanel, 10);
						 if (sourceButton == nextButton)
							 positionPlayedCard(secondPlayerCardPanel, 0);
						 break;
				case 12: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						 + "Carolina pose la carte T de aire 8 et <font color=\"red\">périmètre 16"
						 + "</font>.</div></html>");
						 if (sourceButton == beforeButton)
							 removeLastCard(secondPlayerCardPanel, 5);
						 if (sourceButton == nextButton)
							 positionPlayedCard(firstPlayerCardPanel, 10);
						 break;
				case 13: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						 + "Mehdi pose la carte M de aire 7 et <font color=\"red\">périmètre 16</font>."
						 + "</div></html>");
						 if (sourceButton == beforeButton) {
							 firstPlayerPassTurnButton.setForeground(Color.RED);
							 firstPlayerPassTurnButton.setEnabled(false);
						 }
						 if (sourceButton == nextButton)
							 positionPlayedCard(secondPlayerCardPanel, 5);
						 break;
				case 14: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						 + "Un joueur qui ne peut pas jouer passe son tour en appuyant le bouton "
						 + "approprié.<br>Carolina ne peut pas jouer et passe son tour.</div></html>");
						 firstPlayerPassTurnButton.setEnabled(true);
						 firstPlayerPassTurnButton.setForeground(Color.RED);
						 if (sourceButton == beforeButton)
							 removeLastCard(secondPlayerCardPanel, 3);
						 break;
				case 15: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						 + "Mehdi pose la carte J de <font color=\"red\">aire 7</font> et "
						 + "<font color=\"red\">périmètre 16</font>, mais ne peut pas rejouer.</div></html>");
						 if (sourceButton == nextButton) {
							 firstPlayerPassTurnButton.setForeground(Color.RED);
							 firstPlayerPassTurnButton.setEnabled(false);
							 positionPlayedCard(secondPlayerCardPanel, 3);
						 }
						 break;
				case 16: informationMessageLabel.setText("<html><div style=\"text-align: center;\">"
						 + "Lucie ne peut pas jouer, la partie s'achève.<div></html>");
						 break;
				case 17: informationMessageLabel.setText("<html><div style=\"text-align: center;\">Chaque joueur compte ses cartes "
						 + "en fin de partie et marque autant de points qu’il a de cartes.<br> "
						 + "Dans ce cas, Carolina marque 7 points et Mehdi marque 5 points.</div></html>");
						 break;
				case 18: informationMessageLabel.setText("<html><div style=\"text-align: center;\">Au bout de plusieurs parties, le "
						 + "vainqueur est celui qui totalise le plus petit nombre de points.<br>"
						 + "</div></html>");
						 break;
				case 19: informationMessageLabel.setText("<html><div style=\"text-align: center;\">Fin du tutoriel</div></html>");
				default: break;
			}
		}
		
		private void positionPlayedCard (JPanel playerCardPanel, int cardToMove) {
			/* Retrieval of the desired card */
			CardGUI card = (CardGUI)playerCardPanel.getComponent(cardToMove);
			/* The card on which the event has occurred is made transparent */
			card.makeTransparent();
			/* Addition of the card to the panel in which are positioned the played cards */
			CardGUI playedCard = new CardGUI(card.getId());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 2;
			int numberComponents = centralCardPanel.getComponentCount();
			if (numberComponents >= 1) {
				int dx = randomInteger(-50, 50);
				int dy = randomInteger(-50, 50);
				constraints.insets.left = dx;
				constraints.insets.top = dy;
			}
			centralCardPanel.add(playedCard, constraints, 0);
		}
		
		private void removeLastCard (JPanel playerCardPanel, int cardToRestore) {
			CardGUI card = (CardGUI)centralCardPanel.getComponent(0);
			card.setVisible(false);
			centralCardPanel.remove(0);
			CardGUI temp = (CardGUI)playerCardPanel.getComponent(cardToRestore);
			temp.makeVisible();
		}
		
		private int randomInteger (int min, int max) {
			Random random = new Random();
		    int randomNumber = random.nextInt((max - min) + 1) + min;

		    return randomNumber;
		}
	}
}
