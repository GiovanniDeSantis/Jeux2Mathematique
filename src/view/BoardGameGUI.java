package view;

import javax.swing.JPanel;

import controller.GameController;

public class BoardGameGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	protected GameController gameController;
	
	public BoardGameGUI () {
		super();
		gameController = new GameController();
	}

	/**
	 * Initializes the BoardGameGUI.
	 */
	protected void init () {
		
	}
	
}