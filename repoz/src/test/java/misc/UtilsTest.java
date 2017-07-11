package misc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;


public class UtilsTest {

	public static String createRandomString(int length, boolean withMaj, boolean withMin, boolean withNum) {
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
	
	public static LocalDate createRandomLocalDate() {
		int day = new Random().nextInt(28) + 1;
		int month = new Random().nextInt(11) + 1;
		int year = new Random().nextInt(1001) + 1000;
		return LocalDate.of(year, month, day);
	}
	
	public static LocalDateTime createRandomLocalDateTime() {
		int seconde = new Random().nextInt(59) + 1;
		int minute = new Random().nextInt(59) + 1;
		int hour = new Random().nextInt(23) + 1;
		int day = new Random().nextInt(28) + 1;
		int month = new Random().nextInt(11) + 1;
		int year = new Random().nextInt(1001) + 1000;
		return LocalDateTime.of(year, month, day, hour, minute, seconde);
	}
}
