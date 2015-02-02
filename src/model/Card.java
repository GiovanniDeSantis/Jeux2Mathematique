package model;

/**
 * Class that models a card of the board game.
 * @author Giovanni De Santis, Rafael Garcia.
 */
public class Card {
	private String id;
	private int perimeter;
	private int area;
	
	/**
	 * Class constructor.
	 * @param id - the identifier associated to the card.
	 * @param perimeter - the perimeter associated to the card.
	 * @param area - the area associated to the card.
	 */
	public Card (String id, int perimeter, int area) {
		this.id = id;
		this.perimeter = perimeter;
		this.area = area;
	}

	/**
	 * Returns the id associated to the card.
	 * @return the id associated to the card.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the perimeter associated to the card.
	 * @return the perimeter associated to the card.
	 */
	public int getPerimeter() {
		return perimeter;
	}
	
	/**
	 * Returns the area associated to the card.
	 * @return the area associated to the card.
	 */
	public int getArea() {
		return area;
	}
}