package F;

import java.util.Random;

public final class QuestFunctions {

	public static int getRandomNumber(int low, int high) {
		Random rnd = new Random();
		return rnd.nextInt( high-low + 1 ) + low;
	}

}
