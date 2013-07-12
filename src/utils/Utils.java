package utils;

import java.util.Random;

public final class Utils {

	public static int doProbability(double[] probs, int seed) {
		Random rand = (seed==0)? new Random() : new Random(seed);
		double n = rand.nextDouble();
		for(int i=0; i<probs.length; i++) {
			if(probs[i] < n) {
				return i;
			}
			n -= probs[i];
		}
		return -1;
	}
	
	public static double round(double d, double roundto) {
		return Math.round(d/roundto)*roundto;
	}
}
