package util;

import sensor.SensorEvent;
import java.util.List;

public abstract class Print {
	/**
	 * print all the sensor / activity events
	 * @param events
	 */
	public static void printEvents(final List<SensorEvent> events) {
		for(SensorEvent se: events) {
			System.out.println(se.print());
		}
	}

}
