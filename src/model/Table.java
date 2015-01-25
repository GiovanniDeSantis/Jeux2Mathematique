package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Table {
	Deck deck;

	public Table() {
		this.deck = new Deck();
	}
	
	public void insertCard(Card card){
		this.deck.insertCard(card);
	}
	
	public Card getCard(){
		return this.deck.getTopCard();
	}
	
	protected void readFileCards(String fileName){
		try {
			FileReader reader = new FileReader(fileName);
			JSONParser jsonParser = new JSONParser();
			JSONArray cardsList = (JSONArray) jsonParser.parse(reader);
			Iterator<?> it = cardsList.iterator();
			while (it.hasNext()){
				JSONObject cardData = (JSONObject) it.next();
				String cardID = (String) cardData.get("id");
				int cardArea = (int) cardData.get("area");
				int cardPerimeter = (int) cardData.get("perimeter");
				Card card = new Card(cardID, cardArea, cardPerimeter);
				this.deck.insertCard(card);
			}
			this.deck.shuffle();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
