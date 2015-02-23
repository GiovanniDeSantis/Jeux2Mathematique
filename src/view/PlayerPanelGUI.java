package view;

import java.awt.Color;
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
 * 		- a button aimed at allow the player to pass his turn.
 * 		- a button aimed at allow the player to signal an error committed by the opponent player.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class PlayerPanelGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private int playerScore;
	private JLabel headerLabel;
	private JPanel cardsPanel, buttonsPanel;
	private JButton passTurnButton, signalErrorButton;
	private String playerName;
	
	/**
	 * Class Construction.
	 * @param playerName - the name of the player.
	 * @param playerScore - the initial score to assign to the player.
	 */
	public PlayerPanelGUI (String playerName, int playerScore) {
		/* Member Data Initialization */
		this.playerName = playerName;
		this.playerScore = playerScore;
		/* Buttons Creation */
		passTurnButton = new JButton("Passe Tour");
		signalErrorButton = new JButton("Signale Erreur");
		/* User Interface's Components Creation */
		headerLabel = new JLabel("Jeu de " + playerName + "      Score: " + playerScore);
		cardsPanel = new JPanel(new GridBagLayout());
		buttonsPanel = new JPanel();
		/* User Interface Initialization */
		initialize();
	}
	
	/**
	 * Initializes the User Interface.
	 */
	private void initialize () {
		/* Header Label Handling */
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Pass Turn Button Handling */
		passTurnButton.setBorderPainted(false);
		passTurnButton.setContentAreaFilled(false);
		passTurnButton.setFocusable(false);
		passTurnButton.setMultiClickThreshhold(300);
		/* Signal Error Button Handling */
		signalErrorButton.setBorderPainted(false);
		signalErrorButton.setContentAreaFilled(false);
		signalErrorButton.setFocusable(false);
		signalErrorButton.setMultiClickThreshhold(300);
		/* Buttons Panel Handling */
		buttonsPanel.add(passTurnButton);
		buttonsPanel.add(signalErrorButton);
		/* Player Panel Handling */
		add(headerLabel);
		add(cardsPanel);
		add(buttonsPanel);
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
		signalErrorButton.setEnabled(value);
	}
	
	/**
	 * Sets the size of the label containing the name and the score of the player.
	 * @param width - the width to be assigned to the label.
	 * @param height - the height to be assigned to the label.
	 */
	public void setHeaderLabelSize (int width, int height) {
		headerLabel.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Updates the current score of the player.
	 * @param increment - the increment/decrement to be summed to the current score.
	 */
	public void updateScore (int increment) {
		playerScore = playerScore + increment;
		headerLabel.setText("Jeu de " + playerName + "      Score: " + playerScore);
	}
	
	/**
	 * Sets the size of the panel containing the cards currently held by the player. 
	 * @param width - the width to be assigned to the panel.
	 * @param height - the height to be assigned to the panel.
	 */
	public void setCardsPanelSize (int width, int height) {
		cardsPanel.setPreferredSize(new Dimension(width, height));
	}
	
	/* ------------ To Correct ------------- */
	/**
	 * 
	 * @param cardToRestore
	 */
	public void restoreCard (int cardToRestore) {
		CardGUI card = (CardGUI)cardsPanel.getComponent(cardToRestore); //TODO To correct.
		card.makeVisible();
	}
	
	/**
	 * 
	 * @param cardToPlay
	 */
	public CardGUI getCardToPlay (int cardToPlay) {
		CardGUI card = (CardGUI)cardsPanel.getComponent(cardToPlay);
		card.setEnabled(false);
		
		return card;
	}
	
	/* --------------------------------------------- */
	
	/**
	 * Distributes in the appropriate panel the cards which have to be assigned to the player.
	 * The cards are put in the cells of a matrix composed by a number of rows and columns which 
	 * have to be externally specified.
	 * @param rows - the number of rows forming the matrix.
	 * @param columns - the number of columns forming the matrix.
	 * @param deck - the deck containing the cards which have to be assigned to the player; the 
	 * 				 assignment is realized in a way according to which the first rows*columns 
	 * 				 cards of the deck are distributed to the player.
	 * @param oneLess - defines if in the last row of the matrix it has to lack the last card;
	 * 					in this way the first ((rows*columns) - 1) cards of the deck are distributed 
	 * 					to the player.
	 * @param twoLess - defines if in the last row of the matrix it has to lack the last two cards;
	 * 					in this way the first ((rows*columns) - 2) cards of the deck are distributed 
	 * 					to the player.
	 */
	public void configureCardsPanel (int rows, int columns, Stack<CardGUI> deck, boolean oneLess, boolean twoLess) {		
		GridBagConstraints constraints = new GridBagConstraints();
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				int matrixDimension = rows * columns;
				if (oneLess) {
					if (((i + j) + (i * 2)) != (matrixDimension - 1)) {
						CardGUI card = deck.pop();
						constraints.gridx = j;
						constraints.gridy = i;
						constraints.ipadx = 13;
						constraints.ipady = 10;
						cardsPanel.add(card, constraints);
					}
				}
				else if (twoLess) {
					if (((i + j) + (i * 2)) != (matrixDimension - 2)) {
						CardGUI card = deck.pop();
						constraints.gridx = j;
						constraints.gridy = i;
						constraints.ipadx = 13;
						constraints.ipady = 10;
						cardsPanel.add(card, constraints);
					}
				} else {
					CardGUI card = deck.pop();
					constraints.gridx = j;
					constraints.gridy = i;
					constraints.ipadx = 13;
					constraints.ipady = 10;
					cardsPanel.add(card, constraints);
				}
			}
	}
	
	/**
	 * Sets the size of the panel containing the buttons allowing the player either to pass 
	 * his turn or to signal an error committed by the opponent player.
	 * @param width - the width to be assigned to the button.
	 * @param height - the height to be assigned to the button.
	 */
	public void setButtonsPanelSize (int width, int height) {
		buttonsPanel.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Sets the color of the text characterizing the pass turn button.
	 * @param color - the color to be set.
	 */
	public void setPassTurnButtonTextColor (Color color) {
		passTurnButton.setForeground(color);
	}
	
	/**
	 * Sets the color of the text characterizing the signal error button.
	 * @param color - the color to be set.
	 */
	public void setSignalErrorButtonTextColor (Color color) {
		signalErrorButton.setForeground(color);
	}
	
	/**
	 * Associates an Action Listener to the button used to allow the player to pass his turn.
	 * @param actionListener - the Action Listener to be assigned to the button.
	 */
	public void addPassTurnButtonActionListener (ActionListener actionListener) {
		passTurnButton.addActionListener(actionListener);
	}
	
	/**
	 * Associates an Action Listener to the button used to allow the player to signal an error 
	 * of the opponent player.
	 * @param actionListener - the Action Listener to be assigned to the button.
	 */
	public void addSignalErrorButtonActionListener (ActionListener actionListener) {
		signalErrorButton.addActionListener(actionListener);
	}

}
