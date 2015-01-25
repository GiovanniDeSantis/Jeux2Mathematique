package model;

import java.util.Stack;

public class PerimaireTable extends Table {
	private Stack<Card> playedCards;
	
	public PerimaireTable() {
		super();
		this.readFileCards("perimairecards.json");
	}
}
