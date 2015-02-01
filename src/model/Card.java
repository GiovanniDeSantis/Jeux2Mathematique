package model;

public class Card {
	private String id;
	private int perimeter;
	private int area;
	
	public Card (String id, int perimeter, int area) {
		this.id = id;
		this.perimeter = perimeter;
		this.area = area;
	}

	public String getId() {
		return id;
	}

	public int getPerimeter() {
		return perimeter;
	}
	
	public int getArea() {
		return area;
	}
}