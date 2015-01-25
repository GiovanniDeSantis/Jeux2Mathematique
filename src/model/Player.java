package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Player {
	private static AtomicLong idCounter = new AtomicLong();
	
	private long id;
	private String name;
	private int score;
	private List<Card> cards;
	
	public Player(String name) {
		this.name = name;
		this.id = idCounter.getAndIncrement();
		this.score = 0;
		this.cards = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void updateScore(int additionalScore) {
		this.score += additionalScore;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void assignCard(Card card) {
		this.cards.add(card);
	}
	
	public Card retrieveCard(String id) {
		for (int i = 0; i < this.cards.size(); i++) {
			if (this.cards.get(i).getId().equals(id)) {
				return this.cards.remove(i);
			}
		}
		return null; //EXCEPTION
	}
}
