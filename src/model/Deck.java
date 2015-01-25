package model;

import java.util.Collections;
import java.util.Stack;

public class Deck {
	Stack<Card> cardStack;

	public Deck() {
		this.cardStack = new Stack<>();
	}
	
	public boolean isEmpty() {
		return this.cardStack.isEmpty();
	}
	
	public void insertCard(Card card){
		cardStack.push(card);
	}
	
	public Card getTopCard(){
		return cardStack.pop();
	}
	
	public void shuffle(){
		Collections.shuffle(cardStack);
	}
}
