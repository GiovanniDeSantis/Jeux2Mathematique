package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGameGUI2Players extends BoardGameGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel firstPlayerPanel, secondPlayerPanel, centerPanel;
	private JLabel firstPlayerLabel, secondPlayerLabel;
	//TODO Remove all the code related to the variable card.
	private CardGUI card1, card2, card3, card4, card5, card6;

	/**
	 * Class constructor.
	 */
	public BoardGameGUI2Players (String[] playersNames) {
		super();
		/* Labels Creation */
		firstPlayerLabel = new JLabel("Jeu de " + playersNames[0]);
		secondPlayerLabel = new JLabel("Jeu de " + playersNames[1]);
		/* Panels Creation */
		firstPlayerPanel = new JPanel();
		centerPanel = new JPanel();
		secondPlayerPanel = new JPanel();
		/* Card Creation */
		card1 = new CardGUI("1.png");
		card2 = new CardGUI("2.png");
		card3 = new CardGUI("3.png");
		card4 = new CardGUI("4.png");
		card5 = new CardGUI("5.png");
		card6 = new CardGUI("6.png");
		/* Initialization */
		init();
	}
	
	@Override
	protected void init () {
		/* First Player Label Handling */
		firstPlayerLabel.setPreferredSize(new Dimension(355,20));
		firstPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		firstPlayerLabel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		/* Second Player Label Handling */
		secondPlayerLabel.setPreferredSize(new Dimension(355,20));
		secondPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		secondPlayerLabel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(355, 640));
		firstPlayerPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		firstPlayerPanel.add(firstPlayerLabel);
		firstPlayerPanel.add(card1);
		firstPlayerPanel.add(card2);
		firstPlayerPanel.add(card3);
		/* Center Panel Handling */
		centerPanel.setPreferredSize(new Dimension(260, 640));
		centerPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(355, 640));
		secondPlayerPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		secondPlayerPanel.add(secondPlayerLabel);
		secondPlayerPanel.add(card4);
		secondPlayerPanel.add(card5);
		secondPlayerPanel.add(card6);
		/* CardGUI Handling */
		card1.addActionListener(new CardGUIListener());
		/* Board Panel Handling */
		add(firstPlayerPanel);
		add(centerPanel);
		add(secondPlayerPanel);		
	}

    /* ------------------------------------ CardGUI Action Listener --------------------------------------- */
	
	/** Inner class implementing the Action Listener for the CardGUI.
	  */
	public class CardGUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			firstPlayerPanel.remove(card1);
			centerPanel.add(card1);			
		}

	}
}
