package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MainMenuGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Application application;
	private Border newGameBorder, buttonsBorder;
	private JButton newGameButton, exitButton, optionsButton;

	/**
	 * Class constructor.
	 * @param application - The reference JFrame in which the MainMenuGUI
	 * 						will be placed.
	 */
	public MainMenuGUI (Application application) {
		super();
		/* Reference Frame initialization */
		this.application = application;
		/* Buttons Creation */
		newGameButton = new JButton("New Game");
		optionsButton = new JButton("Options");
		exitButton = new JButton("Exit");
		/* Initialization */
		init();
	}
	
	/**
	 * Initializes the MainMenuGUI.
	 */
	private void init () {
		/* Panel Handling */
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(newGameButton);
		add(optionsButton);
		add(exitButton);
		/* Border Handling */
		newGameBorder = BorderFactory.createEmptyBorder(100, 0, 0, 0);
		buttonsBorder = BorderFactory.createEmptyBorder(50, 0, 0, 0);
		/* Buttons Handling */
		/* New Game Button Handling */
		newGameButton.setSize(800, 100);
		newGameButton.setBorder(newGameBorder);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newGameButton.addActionListener(new NewGameListener());
		/* Options Button Handling */
		optionsButton.setSize(800, 100);
		optionsButton.setBorder(buttonsBorder);
		optionsButton.setContentAreaFilled(false);
		optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		optionsButton.addActionListener(new OptionsListener());
		/* Exit Button Handling */
		exitButton.setSize(800, 100);
		exitButton.setBorder(buttonsBorder);
		exitButton.setContentAreaFilled(false);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.addActionListener(new ExitListener());
	}
	
    /* ------------------------------------ "New Game" Button Action Listener --------------------------------------- */
	
	/** Inner class implementing the Action Listener for the "New Game"
	  * button.
	  */
	public class NewGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			BoardGameGUI boardGame;
			int numberOfPlayers;
			String[] playersNames;
			
			numberOfPlayers = getNumberOfPlayers();
			playersNames = getPlayersNames(numberOfPlayers);
			boardGame = boardGameGUIInitialization(numberOfPlayers, playersNames);
			application.add(boardGame);
			setVisible(false);
			boardGame.setVisible(true);
		}
		
		/**
		 * Interacts with the user to get the number of players.
		 */
		private int getNumberOfPlayers () {
			int numberOfPlayers;
			String[] possibleValues = { "2", "3", "4" };
			
			numberOfPlayers = Integer.parseInt((String)JOptionPane.showInputDialog(application, 
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
				playersNames[i] = (String)JOptionPane.showInputDialog(application,
																 "Quel est le prenom du joueur numéro " + (i + 1),
																 "Prenom des joueurs",
																 JOptionPane.QUESTION_MESSAGE);
			}
		
			return playersNames;
		}
		
		/**
		 * On the basis of the number of players.
		 * initializes the proper BoardGameGUI.
		 * @param numberOfPlayers - the number of players.
		 * @param playersNames - the names of the players.
		 * @return the initialized BoardGameGUI.
		 */
		private BoardGameGUI boardGameGUIInitialization (int numberOfPlayers, String[] playersNames) {
			BoardGameGUI boardGame;
			
			switch(numberOfPlayers) {
				case 2: boardGame = new BoardGameGUI2Players(playersNames);
						break;
				case 3: boardGame = new BoardGameGUI3Players(playersNames);
						break;
				case 4: boardGame = new BoardGameGUI4Players(playersNames);
						break;
				default: boardGame = new BoardGameGUI();
						 break;
			}
			
			return boardGame;
		}
		
	}
	
	/* ------------------------------------ "Options" Button Action Listener ---------------------------------------- */
	
	/** Inner class implementing the Action Listener for the "Options"
	  * button.
	  */
	public class OptionsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			
		}

	}
	
	/* ------------------------------------ "Exit" Button Action Listener ------------------------------------------ */

	
	/** Inner class implementing the Action Listener for the "Exit"
	  * button.
	  */
	public class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			
		}

	}
}
