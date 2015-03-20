package com.sd.numberguess;


/**
 * The Java program to play the number-guessing game
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NumberGuessingGame {
	// For simplicity, let us set the range to integers between 0 and 1000 (inclusive)
	public static final int MIN = 0;
	public static final int MAX = 1000;
	
	// keeps track of the number of guesses the game is making to reach the number in the user's mind
	private int numAttempts = 0; 
	
	// The input reader needed to read user's input
	private BufferedReader bufferedReader = null;	

	// Constructor -- initialize the BufferedReader
	public NumberGuessingGame() {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception ex) {
			System.err.println ("Exception creating a BufferedReader: " + ex.getMessage());
		}
	}
	
	// closes the Buffered reader. Call this only when the class is no longer needed
	public void closeReader() {
	 	try {
	 		bufferedReader.close();
	 	} catch (Exception ex) {
	 		System.err.println ("Exception closing the buffered reader");
	 	}		
	}
		
	/** 
	 * the core business method that tries to 'guess' the number
	 * relies on the standard 'binary search' algorithm, uses recursion
	 * @param lower
	 * @param higher
	 * @throws Exception
	 */
	public void play(int lower, int higher) throws Exception {			
			boolean validInput = false;	
			int guess = (lower + higher)/2;
			numAttempts++;
			System.out.print ("My Guess #" + numAttempts + ": Is the number "+ guess + "? (Respond with higher, lower, or yes):");		
			while (!validInput) {				
				String userResp = bufferedReader.readLine();
				switch (userResp.trim().toUpperCase()) {
		 			case "HIGHER":
		 				validInput = true;
		 				play(guess+1, higher);
		 				break;		
		 			case "LOWER":
		 				validInput = true;
		 				play(lower, guess-1);
		 				break;
		 			case "YES":
		 				validInput = true;
		 				System.out.println ("Thanks! I could guess it in " + numAttempts + " attempts!");
		 				break;
		 			default:
		 				System.out.print ("Invalid response... Respond with higher, lower, or yes: ");
		 		}				
			}	 							
	}

	
	
	public static void main (String[] args) throws Exception {		
		NumberGuessingGame game = new NumberGuessingGame();
		System.out.println ("Choose an integer number in your mind between " + game.MIN + " and " + game.MAX  + " (inclusive)...");		
	 	boolean gameStarted = false;
	 	while (!gameStarted) {
	 		System.out.print ("enter READY to start the game: ");
		    String userInput = game.bufferedReader.readLine();
		    if (userInput.trim().equalsIgnoreCase("READY")) {
		    		gameStarted = true;
		    } else {
		    		System.out.print ("Invalid input!");
		    }
	 	}
	 	System.out.println ("This program will now start guessing the number...");
	 	game.play(game.MIN, game.MAX);
	 	game.closeReader();
	}

}

