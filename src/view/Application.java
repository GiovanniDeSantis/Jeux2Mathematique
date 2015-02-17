package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	/* Identifiers of the cards associated to the Card Layout Manager */
	final static String MAINMENU = "Main Menu";
	final static String BOARDGAME = "Board Game";
	final static String TUTORIAL = "Tutorial";
	private JPanel cardsContainer, mainMenu;
	
	/**
	 * Class constructor.
	 */
	public Application () {
		super();
		/* Cards Container Creation */
		cardsContainer = new JPanel(new CardLayout());
		/* Cards Creation */
		mainMenu = new MainMenuGUI(this, cardsContainer);
	}
	
	/**
	 * Initializes the application frame.
	 */
	public void init () {
		/* Cards Container Handling */
		cardsContainer.add(mainMenu, MAINMENU);
		/* Frame Handling */
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(cardsContainer);
		setVisible(true);
	}
	
	/**
	 * Main function.
	 */
	public static void main(String[] args) {
		Application application = new Application();
		application.init();
	}

}
