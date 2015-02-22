package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class implements a panel to be used in the tutorial of the board game.
 * It is composed by:
 * 		- a label containing the information that has to be shown to the user; this 
 * 		  information will be used to explain the different phases of the game.
 * 		- two buttons aimed to allow the user to go back and forth through the different 
 * 		  phases of the tutorial.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class TutorialInformationPanelGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton beforeButton, nextButton;
	private JLabel informationMessageLabel;
	
	/**
	 * Class constructor.
	 */
	public TutorialInformationPanelGUI () {
		/* Buttons Creation */
		beforeButton = new JButton("Avant");
		nextButton = new JButton("Prochain");
		/* Label Creation */
		informationMessageLabel = new JLabel();
		/* User Interface Initialization */
		initialize();
	}
	
	/**
	 * Initializes the User Interface.
	 */
	private void initialize () {
		/* Before Button Handling */
		beforeButton.setBorderPainted(false);
		beforeButton.setContentAreaFilled(false);
		beforeButton.setFocusable(false);
		/* Next Button Handling */
		nextButton.setBorderPainted(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusable(false);
		/* Information Message Label Handling */
		informationMessageLabel.setHorizontalAlignment(JLabel.CENTER);
		/* Tutorial Information Panel Handling */
		add(beforeButton);
		add(informationMessageLabel);
		add(nextButton);
	}

	/**
	 * Sets the size of the button allowing the user to go to the precedent phase 
	 * of the tutorial.
	 * @param width - the width to be assigned to the button.
	 * @param height - the height to be assigned to the button.
	 */
	public void setBeforeButtonSize (int width, int height) {
		beforeButton.setPreferredSize(new Dimension(width, height));
		
	}
	
	/**
	 * Associates an Action Listener to the button used to allow the player to go to the 
	 * precedent phase of the tutorial.
	 * @param actionListener - the Action Listener to be assigned to the button.
	 */
	public void addBeforeButtonActionListener (ActionListener actionListener) {
		beforeButton.addActionListener(actionListener);
	}
	
	/**
	 * Sets the size of the button allowing the user to go to the successive phase 
	 * of the tutorial.
	 * @param width - the width to be assigned to the button.
	 * @param height - the height to be assigned to the button.
	 */
	public void setNextButtonSize (int width, int height) {
		nextButton.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Associates an Action Listener to the button used to allow the player to go to the
	 * successive phase of the tutorial.
	 * @param actionListener - the Action Listener to be assigned to the button.
	 */
	public void addNextButtonActionListener (ActionListener actionListener) {
		nextButton.addActionListener(actionListener);
	}
	
	/**
	 * Sets the size of the label in which will be shown the information aimed at explaining 
	 * to the user the different phases of the game.
	 * @param width - the width to be assigned to the label.
	 * @param height - the height to be assigned to the label.
	 */
	public void setMessageLabelSize (int width, int height) {
		informationMessageLabel.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Sets the message that will be shown in the label containing the information aimed at 
	 * explaining to the user the different phases of the game.
	 * @param message - the message to be set.
	 */
	public void setMessage (String message) {
		informationMessageLabel.setText(message);
	}
}
