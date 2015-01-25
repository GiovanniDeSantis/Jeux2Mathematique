package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGameGUI3Players extends BoardGameGUI {
	
	private static final long serialVersionUID = 1L;
	private JLabel firstPlayerLabel, secondPlayerLabel, thirdPlayerLabel;
	private JPanel firstPlayerPanel, secondPlayerPanel, thirdPlayerPanel, centerPanel;
	
	/**
	 * Class constructor.
	 */
	public BoardGameGUI3Players (String[] playersNames) {
		super();
		setLayout(new BorderLayout());
		/* Labels Creation */
		firstPlayerLabel = new JLabel("Jeu de " + playersNames[0]);
		secondPlayerLabel = new JLabel("Jeu de " + playersNames[1]);
		thirdPlayerLabel = new JLabel("Jeu de " + playersNames[2]);
		/* Panels Creation */
		firstPlayerPanel = new JPanel();
		secondPlayerPanel = new JPanel();
		thirdPlayerPanel = new JPanel();
		centerPanel = new JPanel();
		/* Initialization */
		init();
	}
	
	@Override 
	protected void init () {
		/* First Player Panel Handling */
		firstPlayerPanel.setPreferredSize(new Dimension(350, 425));
		firstPlayerPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		/* Second Player Panel Handling */
		secondPlayerPanel.setPreferredSize(new Dimension(350, 425));
		secondPlayerPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		/* Third Player Panel Handling */
		thirdPlayerPanel.setPreferredSize(new Dimension(900, 215));
		thirdPlayerPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		/* Center Panel Handling */
		centerPanel.setPreferredSize(new Dimension(100, 400));
		centerPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		/* Board Game Handling */
		firstPlayerPanel.add(firstPlayerLabel);
		secondPlayerPanel.add(secondPlayerLabel);
		thirdPlayerPanel.add(thirdPlayerLabel);
		add(firstPlayerPanel, BorderLayout.WEST);
		add(secondPlayerPanel, BorderLayout.EAST);
		add(thirdPlayerPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
	}

}
