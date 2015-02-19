package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class implements the User Interface of the game board's section reserved to a particular 
 * player. This User Interface is composed by:
 * 		- a label containing the name of the player;
 * 		- a label containing the current score of the player during a given match;
 * 		- a panel containing the cards currently held by the player;
 * 		- a button aimed at allow to the player to pass his turn.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class PlayerPanelGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private int playerScore;
	private JLabel nameLabel, scoreLabel;
	private JPanel cardsPanel;
	private JButton passTurnButton;
	
	/**
	 * Class Construction.
	 * @param playerName - the name of the player.
	 * @param playerScore - the initial score to assign to the player.
	 */
	public PlayerPanelGUI (String playerName, int playerScore) {
		/* Member Data Initialization */
		this.playerScore = playerScore;
		/* User Interface's Components Creation */
		nameLabel = new JLabel("Jeu de " + playerName);
		scoreLabel = new JLabel("Score: " + playerScore);
		cardsPanel = new JPanel(new GridBagLayout());
		passTurnButton = new JButton("Passe Tour");
		/* User Interface Initialization */
		initialize();
	}
	
	/**
	 * Initializes the User Interface.
	 */
	private void initialize () {
		/* Name Label Handling */
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		/* Score Label Handling */
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Pass Turn Button Handling */
		passTurnButton.setBorderPainted(false);
		passTurnButton.setContentAreaFilled(false);
		passTurnButton.setFocusable(false);
		/* Player Panel Handling */
		add(nameLabel);
		add(scoreLabel);
		add(cardsPanel);
		add(passTurnButton);
	}
	
	/**
	 * Enables/Disables the User Interface.
	 * @param value - a boolean value useful for specifying if the User Interface must be enabled or 
	 * 				  not; if value is equal to 'true' the User Interface is enabled, otherwise 
	 * 				  it is disabled.
	 */
	public void enable (boolean value) {
		/* Cards Enabling/Disabling */
		int numberOfComponents = cardsPanel.getComponentCount();
		for (int i = 0; i < numberOfComponents; i++) {
			CardGUI card = (CardGUI)cardsPanel.getComponent(i);
			card.enableCard(value);
		}
		/* Pass Turn Button Enabling/Disabling */
		passTurnButton.setEnabled(value);
	}
	
	/**
	 * Sets the size of the label containing the name of the player.
	 * @param width - the width to be assigned to the label.
	 * @param height - the height to be assigned to the label.
	 */
	public void setNameLabelSize (int width, int height) {
		nameLabel.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Sets the size of the label containing the current score of the player.
	 * @param width - the width to be assigned to the label.
	 * @param height - the height to be assigned to the label.
	 */
	public void setScoreLabelSize (int width, int height) {
		scoreLabel.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Updates the current score of the player.
	 * @param increment - the increment/decrement to be summed to the current score.
	 */
	public void updateScore (int increment) {
		playerScore = playerScore + increment;
		scoreLabel.setText("Score: " + playerScore);
	}
	
	/**
	 * Sets the size of the panel containing the cards currently held by the player. 
	 * @param width - the width to be assigned to the panel.
	 * @param height - the height to be assigned to the panel.
	 */
	public void setCardsPanelSize (int width, int height) {
		cardsPanel.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Distributes in the appropriate panel the cards which have to be assigned to the player.
	 * The cards are put in the cells of a matrix composed by a number of rows and columns which 
	 * have to be specified.
	 * @param rows - the number of rows forming the matrix.
	 * @param columns - the number of columns forming the matrix.
	 * @param deck - the deck containing the cards which have to be assigned to the player; the 
	 * 				 assignment is realized in a way according to which the first rows*columns 
	 * 				 cards of the deck are distributed to the player.
	 */
	public void configureCardsPanel (int rows, int columns, Stack<CardGUI> deck) {
		GridBagConstraints constraints = new GridBagConstraints();
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				CardGUI card = deck.pop();
				constraints.gridx = j;
				constraints.gridy = i;
				constraints.ipadx = 13;
				constraints.ipady = 10;
				cardsPanel.add(card, constraints);
			}
	}
	
	/**
	 * Sets the size of the button allowing to the player to pass his turn.
	 * @param width - the width to be assigned to the button.
	 * @param height - the height to be assigned to the button.
	 */
	public void setButtonSize (int width, int height) {
		passTurnButton.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Associates an Action Listener to the button used to allow to the player to pass his turn.
	 * @param actionListener - the Action Listener to be assigned to the button.
	 */
	public void addButtonActionListener (ActionListener actionListener) {
		passTurnButton.addActionListener(actionListener);
	}

}
