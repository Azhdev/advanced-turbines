package nl.Azhdev.core.api;

import java.util.Random;

public class util {
	public static String getRandomSound(){
		Random random = new Random();
		return "tau:taunt" + random.nextInt(81);
	}
}
