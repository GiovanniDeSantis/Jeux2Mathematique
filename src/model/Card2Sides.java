package model;

public class Card2Sides extends Card {
	private Color color;
	
	public Card2Sides(String id, int area, int perimeter, Color color) {
		super(id, area, perimeter);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void changeColor() {
		switch (this.color) {
			case RED:
				this.color = Color.BLUE;
				break;
			case BLUE:
				this.color = Color.RED;
				break;
		}
	}
}
