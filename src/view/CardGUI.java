package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CardGUI extends JButton {

	private static final long serialVersionUID = 1L;
	private String id;
	
    /**
     * Class constructor.
     * @param source - source path of the background image of the CardGUI.
     */
    public CardGUI (String id) {
    	this.id = id;
    	ImageIcon imageIcon = new ImageIcon(id + ".png");
    	setIcon(imageIcon);
    	int width = imageIcon.getIconWidth();
    	int height = imageIcon.getIconHeight();
    	setBorderPainted(false);
    	setContentAreaFilled(false);
    	setPreferredSize(new Dimension(width, height));
    }
    
    /**
     * 
     * @return
     */
    public String getId () {
    	return id;
    }
    
    /**
     * 
     */
    public void makeTransparent () {
    	setIcon(null);
    }

    /**
     * 
     */
    public void enableCard (boolean value) {
    	if (value) {
    		setEnabled(true);
    	} else {
    		ImageIcon imageIcon = (ImageIcon)this.getIcon();
    		setDisabledIcon(imageIcon);
    		setEnabled(false);
    	}
    }
}
