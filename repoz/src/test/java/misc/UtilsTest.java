package misc;

import java.util.Random;


public class UtilsTest {

		 
	public static String createStringWithLength(int length, boolean withMaj, boolean withMin, boolean withNum) {
		StringBuilder text = new StringBuilder(length);
		String min = "abcdefghijklmnopqrstuvwxyz";
		String maj = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String num = "0123456789";
		String chars = "";
		
		if(withMaj || withMin || withNum ) {
			if(withMaj) chars += maj; 
			if(withMin) chars += min;
			if(withNum) chars += num;
		
			for(int i = 0; i < length; i++) {
				text.append(chars.charAt(new Random().nextInt(chars.length())));
			}
		}
		
		return text.toString();
	}
}
