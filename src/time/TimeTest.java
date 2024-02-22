package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The total seconds were not calculated properly", seconds == 18305);
	}
	
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			Time.getTotalSeconds("50:00");
			}
		);
	}
	
	@Test
	void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("24:00:00");
		assertTrue("The total seconds at 24 hours were not calculated properly", seconds == 86400);
	}

	@ParameterizedTest
	@ValueSource(strings = {"00:25:10", "05:25:10", "92:25:10", "03:25:10"})
	void testGetSecondsGood(String candidate) {
		int seconds = Time.getSeconds(candidate);
		assertTrue("The seconds were not calculated properly", seconds == 10);
	}
	
	@Test
	void testGetSecondsBad() {
		assertThrows(NumberFormatException.class, () -> {
			Time.getSeconds("00:00:1F");
		});
	}
	
	@Test
	void testGetSecondsBoundary() {
		int seconds = Time.getSeconds("23:59:59");
		assertTrue("The seconds at boundary were not calculated properly.", seconds == 59);
	}

	@ParameterizedTest
	@ValueSource(strings = {"00:25:00", "05:25:32", "99:25:99", "05:25:32"})
	void testGetTotalMinutesGood(String candidate) {
		int hours = Time.getTotalMinutes(candidate);
		assertTrue("The minutes were not calculated properly.", hours==25);
	}
	
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(NumberFormatException.class, () -> {
			Time.getTotalMinutes("10:100:20");
		});
	}
	
	@Test
	void testGetTotalMinutesBoundary() {
		int minutes = Time.getTotalMinutes("23:59:59");
		assertTrue("The total minutes at boundary were not calculated properly.", minutes == 59);
	}

	@Test
	void testGetTotalHoursGood() {
		int hours = Time.getTotalHours("10:05:50");
		assertTrue("The hours were not calculated properly.", hours == 10);
	}
	
	@Test
	void testGetTotalHoursBad() {
		assertThrows(NumberFormatException.class, () -> {
			Time.getTotalHours("5:00:00");
		});
	}
	
	@Test
	void testGetTotalHoursBoundary() {
		int hours = Time.getTotalHours("23:59:59");
		assertTrue("The total hours were not calculated properly.", hours == 23);
	}

}
