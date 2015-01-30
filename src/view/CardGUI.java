package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CardGUI extends JButton {

	private static final long serialVersionUID = 1L;
	private String source;
	
    /**
     * Class constructor.
     * @param source - source path of the background image of the CardGUI.
     */
    public CardGUI (String source) {
    	this.source = source;
    	ImageIcon imageIcon = new ImageIcon(source);
    	setIcon(imageIcon);
    	int width = imageIcon.getIconWidth();
    	int height = imageIcon.getIconHeight();
    	this.setBorderPainted(false);
    	setContentAreaFilled(false);
    	setPreferredSize(new Dimension(width, height));
    }
    
    /**
     * 
     * @return
     */
    public String getSource () {
    	return source;
    }
    
    /**
     * 
     */
    public void makeTransparent () {
    	setIcon(null);
    }

}
