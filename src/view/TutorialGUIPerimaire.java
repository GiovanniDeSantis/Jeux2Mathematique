package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JPanel;

import model.GameState;

/**
 * Class that implements the User Interface of the game board in the
 * case a tutorial must be shown.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class TutorialGUIPerimaire extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int tutorialPhase;
	private JPanel upperPanel, lowerPanel;
	private PlayerPanelGUI firstPlayer, secondPlayer;
	private PlayingPanelGUI playingPanel;
	private Stack<CardGUI> deck;	
	private TutorialInformationPanelGUI informationPanel;

	/**
	 * Class constructor.
	 */
	public TutorialGUIPerimaire () {
		/* Member Data Initialization */
		tutorialPhase = 0;
		/* Panels Creation */
		firstPlayer = new PlayerPanelGUI("Carolina", 12);
		playingPanel = new PlayingPanelGUI();
		secondPlayer = new PlayerPanelGUI("Mehdi", 12);
		informationPanel = new TutorialInformationPanelGUI();
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		/* Deck Creation */
		String[] tutorialDeck = {"O", "N", "W", "B", "H", "D", "M", "I", "J", "A", "S", "F",
		"C", "T", "P", "V", "R", "K", "U", "X", "G", "E", "L", "Q"};
		deck = new Stack<CardGUI>();
		for (int i = 0; i < GameState.PERIMAIRE_DECK_DIMENSION; i++)
			deck.push(new CardGUI(tutorialDeck[i]));
		/* Board Game Initialization */
		initialize();
	}
	
	/**
	 * Initialized the User Interface.
	 */
	protected void initialize () {
		/* First Player Panel Handling */
		firstPlayer.setPreferredSize(new Dimension(300, 510));
		firstPlayer.setButtonsPanelSize(300, 30);
		firstPlayer.setHeaderLabelSize(240, 20);
		firstPlayer.configureCardsPanel(4, 3, deck, false, false);
		firstPlayer.enable(false);
		/* Playing Panel Handling */
		playingPanel.setPreferredSize(new Dimension(260, 490));
		playingPanel.setCardsPanelSize(260, 490);
		playingPanel.setMessageLabelSize(0, 0);
		/* Second Player Panel Handling */
		secondPlayer.setPreferredSize(new Dimension(300, 510));
		secondPlayer.setButtonsPanelSize(300, 30);
		secondPlayer.setHeaderLabelSize(240, 20);
		secondPlayer.configureCardsPanel(4, 3, deck, false, false);
		secondPlayer.enable(false);
		/* Upper Panel Handling */
		upperPanel.add(firstPlayer);
		upperPanel.add(playingPanel);
		upperPanel.add(secondPlayer);
		/* Information Panel Handling */
		informationPanel.setPreferredSize(new Dimension(860, 55));
		informationPanel.setBeforeButtonSize(105, 35);
		informationPanel.addBeforeButtonActionListener(new BeforeButtonListener());
		informationPanel.setNextButtonSize(105, 35);
		informationPanel.addNextButtonActionListener(new NextButtonListener());
		informationPanel.setMessageLabelSize(550, 50);
		informationPanel.setMessage("<html><div style=\"text-align: center;\">"
				+ "Ce jeu consiste à empiler des cartes par égalités d'aires et de périmètres."
				+ "<br>Le but est être le premier à se défaire de ses cartes</div></html>");
		/* Lower Panel Handling */
		lowerPanel.setPreferredSize(new Dimension(860, 65));
		lowerPanel.add(informationPanel);
		/* Board Game Handling */
		setPreferredSize(new Dimension(860, 600));
		add(upperPanel);
		add(lowerPanel);
	}
	
    /* ------------------------------------ Before Button Action Listener -------------------------------- */
	
	/**
	 * Inner class implementing the action listener to be associated to the "Before Button" of the TutorialInformationPanelGUI.
	 * @author Giovanni De Santis, Rafael Garcia.
	 */
	private class BeforeButtonListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent action) {
			if (tutorialPhase > 0)
				tutorialPhase--;
			switch (tutorialPhase) {
				case 0: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Ce jeu consiste à empiler des cartes par égalités d'aires et de périmètres."
						+ "<br>Le but est être le premier à se défaire de ses cartes</div></html>");
						break;
				case 1: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Toute carte empilée doit représenter une figure de même aire ou de même "
						+ "périmètre que la figure de la carte du dessous.</div></html>");
						break;
				case 2: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Le joueur désigné pour débuter la partie, retire de son jeu la carte de "
						+ "son choix et la pose sur la table.<br>Le premier joueur est Carolina."
						+ "</div></html>");
						goToPrecedentPhase(firstPlayer, 9);
						firstPlayer.updateScore(1);
						break;
				case 3: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina joue la carte P de aire 5 et périmètre 12.<br> Le joueur "
						+ "suivant doit poser sur la carte précédente une carte de son jeu.</div></html>");
						secondPlayer.updateScore(1);
						goToPrecedentPhase(secondPlayer, 7);
						break;
				case 4: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi joue la carte H de <font color=\"red\">aire 5</font> et "
						+ "<font color=\"red\">périmètre 12</font>.<br>Poser une carte représentant"
						+ " une figure de même aire et de même périmètre permet de rejouer.</div></html>");
						secondPlayer.updateScore(1);
						goToPrecedentPhase(secondPlayer, 10);
						break;
				case 5: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi rejoue en posant la carte N de <font color=\"red\">aire 5</font> et "
						+ "<font color=\"red\">périmètre 12</font>.</div></html>");
						secondPlayer.updateScore(1);
						goToPrecedentPhase(secondPlayer, 8);
						break;
				case 6:	informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi rejoue en posant la carte B de <font color=\"red\">aire 5</font> et "
						+ "<font color=\"red\">périmètre 12</font>.</div></html>");
						secondPlayer.updateScore(1);
						goToPrecedentPhase(secondPlayer, 6);
						break;
				case 7:	informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi rejoue en posant la carte D de aire 7 et <font color=\"red\">périmètre 12"
						+ "</font>.</div></html>");
						firstPlayer.updateScore(1);
						goToPrecedentPhase(firstPlayer, 0);
						break;
				case 8:	informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina joue la carte Q de <font color=\"red\">aire 7</font> et "
						+ "<font color=\"red\">périmètre 12</font>.</div></html>");
						firstPlayer.updateScore(1);
						goToPrecedentPhase(firstPlayer, 7);
						break;
				case 9:	informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina rejoue en posant la carte R de <font color=\"red\">aire 7</font> et"
						+ " <font color=\"red\">périmètre 12</font>.</div></html>");
						firstPlayer.updateScore(1);
						goToPrecedentPhase(firstPlayer, 4);
						break;
				case 10: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina rejoue en posant la carte X de <font color=\"red\">aire 7</font> et"
						+ " périmètre 14.</div></html>");
						secondPlayer.updateScore(1);
						goToPrecedentPhase(secondPlayer, 0);
						break;
				case 11: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi pose la carte F de <font color=\"red\">aire 7</font> et périmètre 16."
						+ "</div></html>");
						firstPlayer.updateScore(1);
						goToPrecedentPhase(firstPlayer, 10);
						break;
				case 12: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina pose la carte T de aire 8 et <font color=\"red\">périmètre 16"
						+ "</font>.</div></html>");
						secondPlayer.updateScore(1);
						goToPrecedentPhase(secondPlayer, 5);
						break;
				case 13: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi pose la carte M de aire 7 et <font color=\"red\">périmètre 16</font>."
						+ "</div></html>");
						firstPlayer.setPassTurnButtonTextColor(Color.RED);
						break;
				case 14: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Un joueur qui ne peut pas jouer passe son tour en appuyant le bouton "
						+ "approprié.<br>Carolina ne peut pas jouer et passe son tour.</div></html>");
						secondPlayer.updateScore(1);
						goToPrecedentPhase(secondPlayer, 3);
						break;
				case 15: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi pose la carte J de <font color=\"red\">aire 7</font> et "
						+ "<font color=\"red\">périmètre 16</font>, mais ne peut pas rejouer.</div></html>");
						break;
				case 16: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina ne peut pas jouer, la partie s'achève.<div></html>");
						break;
				case 17: informationPanel.setMessage("<html><div style=\"text-align: center;\">Chaque joueur compte ses cartes "
						+ "en fin de partie et marque autant de points qu’il a de cartes.<br> "
						+ "Dans ce cas, Carolina marque 7 points et Mehdi marque 5 points.</div></html>");
						break;
				case 18: informationPanel.setMessage("<html><div style=\"text-align: center;\">Au bout de plusieurs parties, le "
						+ "vainqueur est celui qui totalise le plus petit nombre de points.<br>"
						+ "</div></html>");
						break;
				case 19: informationPanel.setMessage("<html><div style=\"text-align: center;\">Fin du tutoriel</div></html>");
						break;
				default: break;
			}
		}
		
		private void goToPrecedentPhase (PlayerPanelGUI playerPanel, int cardToRestore) {
			playingPanel.removeLastCard();
			playerPanel.restoreCard(cardToRestore);
		}
	}
	
	/* ------------------------------------ Next Button Action Listener -------------------------------- */
	
	/**
	 * Inner class implementing the action listener to be associated to the "Next Button" of the TutorialInformationPanelGUI.
	 * @author Giovanni De Santis, Rafael Garcia.
	 */
	private class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent action) {
			if (tutorialPhase < 19)
				tutorialPhase++;
			switch (tutorialPhase) {
				case 0: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Ce jeu consiste à empiler des cartes par égalités d'aires et de périmètres."
						+ "<br>Le but est être le premier à se défaire de ses cartes</div></html>");
						break;
				case 1: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Toute carte empilée doit représenter une figure de même aire ou de même "
						+ "périmètre que la figure de la carte du dessous.</div></html>");
						break;
				
				case 2: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Le joueur désigné pour débuter la partie, retire de son jeu la carte de "
						+ "son choix et la pose sur la table.<br>Le premier joueur est Carolina."
						+ "</div></html>");
						break;
				case 3: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina joue la carte P de aire 5 et périmètre 12.<br> Le joueur "
						+ "suivant doit poser sur la carte précédente une carte de son jeu.</div></html>");
						firstPlayer.updateScore(-1);
						playCard(firstPlayer, 9);
						break;
				case 4: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi joue la carte H de <font color=\"red\">aire 5</font> et "
						+ "<font color=\"red\">périmètre 12</font>.<br>Poser une carte représentant"
						+ " une figure de même aire et de même périmètre permet de rejouer.</div></html>");
						secondPlayer.updateScore(-1);
						playCard(secondPlayer, 7);
						break;
				case 5: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi rejoue en posant la carte N de <font color=\"red\">aire 5</font> et "
						+ "<font color=\"red\">périmètre 12</font>.</div></html>");
						secondPlayer.updateScore(-1);
						playCard(secondPlayer, 10);
						break;
				case 6:	informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi rejoue en posant la carte B de <font color=\"red\">aire 5</font> et "
						+ "<font color=\"red\">périmètre 12</font>.</div></html>");
						secondPlayer.updateScore(-1);
						playCard(secondPlayer, 8);
						break;
				case 7:	informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi rejoue en posant la carte D de aire 7 et <font color=\"red\">périmètre 12"
						+ "</font>.</div></html>");
						secondPlayer.updateScore(-1);
						playCard(secondPlayer, 6);
						break;
				case 8:	informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina joue la carte Q de <font color=\"red\">aire 7</font> et "
						+ "<font color=\"red\">périmètre 12</font>.</div></html>");
						firstPlayer.updateScore(-1);
						playCard(firstPlayer, 0);
						break;
				case 9:	informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina rejoue en posant la carte R de <font color=\"red\">aire 7</font> et"
						+ " <font color=\"red\">périmètre 12</font>.</div></html>");
						firstPlayer.updateScore(-1);
						playCard(firstPlayer, 7);
						break;
				case 10: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina rejoue en posant la carte X de <font color=\"red\">aire 7</font> et"
						+ " périmètre 14.</div></html>");
						firstPlayer.updateScore(-1);
						playCard(firstPlayer, 4);
						break;
				case 11: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi pose la carte F de <font color=\"red\">aire 7</font> et périmètre 16."
						+ "</div></html>");
						secondPlayer.updateScore(-1);
						playCard(secondPlayer, 0);
						break;
				case 12: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina pose la carte T de aire 8 et <font color=\"red\">périmètre 16"
						+ "</font>.</div></html>");
						firstPlayer.updateScore(-1);
						playCard(firstPlayer, 10);
						break;
				case 13: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi pose la carte M de aire 7 et <font color=\"red\">périmètre 16</font>."
						+ "</div></html>");
						secondPlayer.updateScore(-1);
						playCard(secondPlayer, 5);
						break;
				case 14: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Un joueur qui ne peut pas jouer passe son tour en appuyant le bouton "
						+ "approprié.<br>Carolina ne peut pas jouer et passe son tour.</div></html>");
						firstPlayer.setPassTurnButtonTextColor(Color.RED);
						break;
				case 15: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Mehdi pose la carte J de <font color=\"red\">aire 7</font> et "
						+ "<font color=\"red\">périmètre 16</font>, mais ne peut pas rejouer.</div></html>");
						secondPlayer.updateScore(-1);
						playCard(secondPlayer, 3);
						break;
				case 16: informationPanel.setMessage("<html><div style=\"text-align: center;\">"
						+ "Carolina ne peut pas jouer, la partie s'achève.<div></html>");
						break;
				case 17: informationPanel.setMessage("<html><div style=\"text-align: center;\">Chaque joueur compte ses cartes "
						+ "en fin de partie et marque autant de points qu’il a de cartes.<br> "
						+ "Dans ce cas, Carolina marque 7 points et Mehdi marque 5 points.</div></html>");
						break;
				case 18: informationPanel.setMessage("<html><div style=\"text-align: center;\">Au bout de plusieurs parties, le "
						+ "vainqueur est celui qui totalise le plus petit nombre de points.<br>"
						+ "</div></html>");
						break;
				case 19: informationPanel.setMessage("<html><div style=\"text-align: center;\">Fin du tutoriel</div></html>");
						break;
				default: break;
			}
		}
		
		private void playCard (PlayerPanelGUI playerPanel, int cardToPlay) {
			CardGUI card = playerPanel.getCardToPlay(cardToPlay);
			playingPanel.positionPlayedCard(card);
			
		}
	}
}
