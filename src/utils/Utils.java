package utils;

import java.util.Random;

public class Utils {
	
	/**
	 * Computes a random integer in a generic interval.
	 * @param min - the minimum value of the generic interval.
	 * @param max - the maximum value of the generic interval.
	 * @return a random integer in the interval [min, max].
	 */
	public static int randomInteger (int min, int max) {
		Random random = new Random();
	    int randomNumber = random.nextInt((max - min) + 1) + min;

	    return randomNumber;
	}
}
