package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class that implements the User Interface of the card of the board game.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class CardGUI extends JButton {

	private static final long serialVersionUID = 1L;
	private String id;
	
    /**
     * Class constructor.
     * @param id - the identifier of the generic card (it is the same identifier
     * 			   used for the cards defined in the model).
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
     * Returns the id associated to the generic card.
     * @return the id associated to the generic card.
     */
    public String getId () {
    	return id;
    }
    
    /**
     * Makes a card transparent (not visible in the User Interface).
     */
    public void makeTransparent () {
    	setIcon(null);
    }

	/**
	 * Enable/disable the card. When disabled, the card cannot be clicked and,
	 * consequently, cannot be played.
	 * @param value - if 'true', the card is enabled;
	 * 				  if 'false', the card is disabled.
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
