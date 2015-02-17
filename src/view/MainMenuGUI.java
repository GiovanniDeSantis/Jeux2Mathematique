package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GameController;

public class MainMenuGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Application application;
	private Container fatherContainer;
	private GameController gameController;
	private JPanel newGamePanel, tutorialPanel, optionsPanel, exitPanel;
	private JButton newGameButton, tutorialButton, optionsButton, exitButton;

	/**
	 * Class constructor.
	 * @param application - The container in which the MainMenuGUI is placed.
	 */
	public MainMenuGUI (Application application, Container fatherContainer) {
		super();
		/* Member Data Initialization */
		this.application = application;
		this.fatherContainer = fatherContainer;
		/* Panels Creation */
		newGamePanel = new JPanel(new BorderLayout());
		tutorialPanel = new JPanel(new BorderLayout());
		optionsPanel = new JPanel(new BorderLayout());
		exitPanel = new JPanel(new BorderLayout());
		/* Buttons Creation */
		newGameButton = new JButton("Nouvelle Partie");
		tutorialButton = new JButton("Tutoriel");
		optionsButton = new JButton("Options");
		exitButton = new JButton("Sortie");
		/* Initialization */
		init();
	}
	
	/**
	 * Initializes the MainMenuGUI.
	 */
	private void init () {
		/* New Game Button Handling */
		newGameButton.setPreferredSize(new Dimension(400, 100));
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setFocusable(false);
		newGameButton.addActionListener(new NewGameListener());
		/* Tutorial Button Handling */
		tutorialButton.setPreferredSize(new Dimension(400, 75));
		tutorialButton.setContentAreaFilled(false);
		tutorialButton.setBorderPainted(false);
		tutorialButton.setFocusable(false);
		tutorialButton.addActionListener(new TutorialListener());
		/* Options Button Handling */
		optionsButton.setPreferredSize(new Dimension(400, 75));
		optionsButton.setContentAreaFilled(false);
		optionsButton.setBorderPainted(false);
		optionsButton.setFocusable(false);
		optionsButton.addActionListener(new OptionsListener());
		/* Exit Button Handling */
		exitButton.setPreferredSize(new Dimension(400, 100));
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setFocusable(false);
		exitButton.addActionListener(new ExitListener());
		/* New Game Panel Handling */
		newGamePanel.setPreferredSize(new Dimension(400, 100));
		newGamePanel.add(newGameButton, BorderLayout.SOUTH);
		/* Tutorial Panel Handling */
		tutorialPanel.setPreferredSize(new Dimension(400, 75));
		tutorialPanel.add(tutorialButton, BorderLayout.CENTER);
		/* Options Panel Handling */
		optionsPanel.setPreferredSize(new Dimension(400, 75));
		optionsPanel.add(optionsButton, BorderLayout.CENTER);
		/* Exit Panel Handling */
		exitPanel.setPreferredSize(new Dimension(400, 100));
		exitPanel.add(exitButton, BorderLayout.NORTH);
		/* Main Menu Handling */
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(newGamePanel);
		add(tutorialPanel);
		add(optionsPanel);
		add(exitPanel);
	}
	
    /* ------------------------------------ New Game Button Action Listener --------------------------------------- */
	
	/** Inner class implementing the Action Listener for the "New Game"
	  * button.
	  */
	public class NewGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			BoardGameGUI boardGame;
			CardLayout cardLayout;
			int numberOfPlayers, firstToPlay;
			String[] playersNames;
			String[] shuffledDeck;
			
			/* Request of the desired number of players */
			numberOfPlayers = getNumberOfPlayers();
			/* Request of the names of the players */
			playersNames = getPlayersNames(numberOfPlayers);
			/* Definition of the player that will make the first move */
			firstToPlay = randInt(1, numberOfPlayers); //TODO check if this is the right place
			/* Creation of the game */
			gameController = new GameController(playersNames);
			/* Retrieval of the shuffled deck */
			shuffledDeck = gameController.getShuffledDeck();
			/* Board game initialization */
			boardGame = boardGameGUIInitialization(numberOfPlayers, playersNames, shuffledDeck, firstToPlay);
			/* Adding of the board game to the CardLayout */
			fatherContainer.add(boardGame, Application.BOARDGAME);
			cardLayout = (CardLayout)fatherContainer.getLayout();
			cardLayout.show(fatherContainer, Application.BOARDGAME);
			/* Reshaping of the main frame */
			application.pack();
			application.setLocationRelativeTo(null);			
		}
		
		/**
		 * Interacts with the user to get the number of players.
		 */
		private int getNumberOfPlayers () {
			int numberOfPlayers;
			String[] possibleValues = { "2", "3", "4" };
			
			numberOfPlayers = Integer.parseInt((String)JOptionPane.showInputDialog(fatherContainer, 
																		   "Combien de joueurs vont jouer?", 
																		   "Nombre de joueurs", 
																		   JOptionPane.QUESTION_MESSAGE,
																   		   null,
																		   possibleValues,
																		   possibleValues[0]));
			
			return numberOfPlayers;
		}

		/**
		 * Interacts with the user to get the names of the players.
		 * @param numberOfPlayers - the number of players.
		 * @return the names of the players.
		 */
		private String[] getPlayersNames (int numberOfPlayers) {
			String[] playersNames = new String[numberOfPlayers];
			
			for (int i = 0; i < numberOfPlayers; i++) {
				playersNames[i] = (String)JOptionPane.showInputDialog(fatherContainer,
																 "Quel est le prenom du joueur numéro " + (i + 1),
																 "Prenom des joueurs",
																 JOptionPane.QUESTION_MESSAGE);
			}
		
			return playersNames;
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
		
		/**
		 * On the basis of the number of players.
		 * initializes the proper BoardGameGUI.
		 * @param numberOfPlayers - the number of players.
		 * @param playersNames - the names of the players.
		 * @return the initialized BoardGameGUI.
		 */
		private BoardGameGUI boardGameGUIInitialization (int numberOfPlayers, String[] playersNames, 
																String[] shuffledDeck, int firstToPlay) {
			BoardGameGUI boardGame;
			
			switch(numberOfPlayers) {
				case 2: boardGame = new BoardGameGUI2PlayersPerimaire(gameController, playersNames, shuffledDeck, firstToPlay);
						break;
				case 3: boardGame = new BoardGameGUI3PlayersPerimaire(gameController, playersNames, shuffledDeck, firstToPlay);
						break;
				case 4: boardGame = new BoardGameGUI4PlayersPerimaire(gameController, playersNames, shuffledDeck, firstToPlay);
						break;
				default: boardGame = new BoardGameGUI();
						 break;
			}
			
			return boardGame;
		}
		
	}
	
	/* ------------------------------------ Tutorial Button Action Listener ---------------------------------------- */

	public class TutorialListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent action) {
			TutorialGUIPerimaire tutorial;
			CardLayout cardLayout;
			
			/* Board game initialization */
			tutorial = new TutorialGUIPerimaire();
			/* Adding of the board game to the CardLayout */
			fatherContainer.add(tutorial, Application.TUTORIAL);
			cardLayout = (CardLayout)fatherContainer.getLayout();
			cardLayout.show(fatherContainer, Application.TUTORIAL);
			/* Reshaping of the main frame */
			application.pack();
			application.setLocationRelativeTo(null);			
		}
	}
	
	/* ------------------------------------ Options Button Action Listener ---------------------------------------- */
	
	/** Inner class implementing the Action Listener for the "Options"
	  * button.
	  */
	public class OptionsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			
		}

	}
	
	/* ------------------------------------ Exit Button Action Listener ------------------------------------------ */

	
	/** Inner class implementing the Action Listener for the "Exit"
	  * button.
	  */
	public class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			
		}

	}
}
