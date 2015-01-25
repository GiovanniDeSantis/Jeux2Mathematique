package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CardGUI extends JButton {

	private static final long serialVersionUID = 1L;
	private Image image;

    /**
     * Class constructor.
     * @param source - source path of the background image of the CardGUI.
     */
    public CardGUI (String source) {
    	super();
    	init(source);
    }
    
    /**
     * Initializes the CardGUI.
     * @param source - source path of the background image of the CardGUI.
     */
    private void init (String source) {
    	int width, height;
    	ImageIcon imageIcon;
    	
    	imageIcon = new ImageIcon(source);
        image = imageIcon.getImage();
        width = image.getWidth(this);
        height =  image.getHeight(this);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(width, height));
    }
    
    @Override
    /**
     * Paints the CardGUI.
     */
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

}
