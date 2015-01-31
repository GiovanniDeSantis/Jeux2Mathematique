package view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Class that defines and implements the basic methods for the
 * construction of the User Interface of the game board.
 * @author Giovanni De Santis, Rafael Garcia
 */
public class BoardGameGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initializes the BoardGameGUI.
	 */
	protected void init () {
		
	}
	
	/**
	 * Positions the card played at each move in the appropriate panel.
	 * @param referencePanel - the panel in which the played card must be placed.
	 * @param action - the event occurred on the played card.
	 */
	protected void playedCardPositioning (Container referencePanel, ActionEvent action) {
		/* Retrieval of the card on which the event occurred */
		CardGUI sourceComponent = (CardGUI)action.getSource();
		/* The card on which the event has occurred is made transparent */
		sourceComponent.makeTransparent();
		/* Addition of the card to the panel in which are positioned the played cards */
		CardGUI playedCard = new CardGUI(sourceComponent.getSource());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 2;
		int numberComponents = referencePanel.getComponentCount();
		if (numberComponents >= 1) {
			int dx = randInt(-80, 80);
			int dy = randInt(-80, 80);
			constraints.insets.left = dx;
			constraints.insets.top = dy;
		}
		referencePanel.add(playedCard, constraints, 0);
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
}