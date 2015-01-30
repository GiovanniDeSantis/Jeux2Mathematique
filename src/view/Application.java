package view;

import javax.swing.JFrame;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	private MainMenuGUI mainMenu;
	
	/**
	 * Class constructor.
	 */
	public Application () {
		super();
		mainMenu = new MainMenuGUI(this);
	}
	
	/**
	 * Initializes the application frame.
	 */
	public void init () {
		/* Frame handling */
		setSize(1000, 670);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(mainMenu);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application application = new Application();
		application.init();
	}

}
