package model;

public class Card {
	private String id;
	private int area;
	private int perimeter;
	
	public Card(String id, int area, int perimeter) {
		this.id = id;
		this.area = area;
		this.perimeter = perimeter;
	}

	public String getId() {
		return id;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(int perimeter) {
		this.perimeter = perimeter;
	}
}
