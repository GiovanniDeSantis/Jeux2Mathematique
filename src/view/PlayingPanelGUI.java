package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Utils;

/**
 * This class implements the User Interface of the game board's section in which the card played in a 
 * round by a generic player is positioned. This User Interface is composed by:
 * 		- a panel containing all the cards played until now;
 * 		- a label aimed at containing an information message that has to be shown to the players.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class PlayingPanelGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel cardsPanel;
	private JLabel messageLabel;
	
	/**
	 * Class constructor.
	 */
	public PlayingPanelGUI () {
		/* User Interface's Components Creation */
		cardsPanel = new JPanel(new GridBagLayout());
		messageLabel = new JLabel();
		/* User Interface Initialization */
		initialize();
	}
	
	/**
	 * Initializes the User Interface.
	 */
	private void initialize () {
		/* Message Label Handling */
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		messageLabel.setVerticalAlignment(JLabel.TOP);
		/* Playing Panel Handling */
		add(cardsPanel);
		add(messageLabel);
	}
	
	
	/**
	 * Sets the size of the panel containing the played cards.
	 * @param width - the width to be assigned to the panel.
	 * @param height - the height to be assigned to the panel.
	 */
	public void setCardsPanelSize (int width, int height) {
		cardsPanel.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Sets the size of the label containing the generic information message.
	 * @param width - the width to be assigned to the label.
	 * @param height - the height to be assigned to the label.
	 */
	public void setMessageLabelSize (int width, int height) {
		messageLabel.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Sets the information message that has to be shown.
	 * @param message - the message to be shown.
	 */
	public void setMessage (String message) {
		messageLabel.setText(message);
	}
	
	/**
	 * Verifies if the label containing the generic information message is empty or not.
	 * @return true if the label is empty, false otherwise.
	 */
	public boolean isMessageEmpty () {
		return messageLabel.getText().isEmpty();
	}
	
	/**
	 * Positions a newly played card in the panel aimed at containing all the played cards.
	 * @param cards - the newly played card.
	 */
	public void positionPlayedCard (CardGUI card) {
		/* The played card is made transparent */
		card.makeTransparent();
		/* Creation of a new card and addition of it to the panel in which all the played cards are positioned */
		CardGUI playedCard = new CardGUI(card.getId());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 2;
		int numberComponents = cardsPanel.getComponentCount();
		if (numberComponents >= 1) {
			int dx = Utils.randomInteger(-80, 80);
			int dy = Utils.randomInteger(-80, 80);
			constraints.insets.left = dx;
			constraints.insets.top = dy;
		}
		cardsPanel.add(playedCard, constraints, 0);
	}
	
	/**
	 * Removes the last played card from the panel aimed at containing all the played cards.
	 */
	public void removeLastCard () {
		CardGUI card = (CardGUI)cardsPanel.getComponent(0);
		card.setVisible(false);
		cardsPanel.remove(0);		
	}
}
