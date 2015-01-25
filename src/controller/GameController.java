package controller;

import java.util.Scanner;

import model.GameState;
import model.PerimaireGameState;
import model.PerimexpaireGameState;

/**
 * Class through which the interactions between the View and the Model
 * are handled.
 * @author Giovanni De Santis - Rafael Garcia.
 */
public abstract class GameController {
	private static final int PERIMAIRE = 1;
	private static final int PERIMEXPAIRE = 2;
	
	private static GameState gameState;
	
	public static void createTeam(String teamName, int numberOfPlayers) {
		gameState.createTeam(teamName, numberOfPlayers);
	}
	
	public static void insertPlayer(String playerName, String teamName) {
		gameState.insertPlayer(playerName, teamName);
	}
	
	public static void startGame(){
		gameState.startGame();
	}

	/*
	public static void main(String[] args) {
		int chosenGame = GameController.chooseGame();
		switch (chosenGame) {
			case PERIMAIRE:
				GameController.initializePerimaire();
				break;
			case PERIMEXPAIRE:
				GameController.initializePerimexpaire();
				break;
			default:
				System.out.println("Invalid Game!");
				break;
		}
	}*/

	/*
	private static int chooseGame() {
		System.out.println("What game do you wanna play?");
		System.out.println("1 - Perimaire");
		System.out.println("2 - Perimexpaire");
		Scanner scanner = new Scanner(System.in);
		int chosenGame = scanner.nextInt();
		scanner.close();
		return chosenGame;
	}*/
	
	/** TODO Now it is useless. */
	/*
	private static void initializePerimaire(){
		gameState = new PerimaireGameState();
		System.out.println("How many players will play the game?");
		Scanner scanner = new Scanner(System.in);
		int numberOfPlayers = scanner.nextInt(); //Check valid value;
		for (int i = 0; i < numberOfPlayers; i++){
			String name = scanner.next();
			GameController.createTeam(name, 1);
			GameController.insertPlayer(name, name);
		}
		scanner.close();
	}
	*/
	
	/*
	private static void initializePerimexpaire() {		
		gameState = new PerimexpaireGameState();
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many teams will play the game?");
		int numberOfTeams = scanner.nextInt(); //Check valid value;
		System.out.println("How many players in each team?");
		int numberOfPlayers = scanner.nextInt(); //Check valid value;
		for (int i = 0; i < numberOfTeams; i++){
			String nameTeam = scanner.next();
			GameController.createTeam(nameTeam, numberOfPlayers);
			for (int j = 0; j < numberOfPlayers; j++){
				String namePlayer = scanner.next();
				GameController.insertPlayer(namePlayer, nameTeam);
			}
		}
		scanner.close();
	}*/
}
