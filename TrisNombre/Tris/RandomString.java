package Tris;

import java.util.Random;

public class RandomString {
	public static String generateString(int length)
	{
		String characters = "azertyuiopqsdfghjklmwxcvbn";
	    char[] text = new char[length];
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        text[i] = characters.charAt(random.nextInt(characters.length()));
	    }
	    return new String(text);
	}
}
