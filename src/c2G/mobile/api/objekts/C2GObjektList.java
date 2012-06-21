package c2G.mobile.api.objekts;

import java.util.ArrayList;
import c2G.mobile.api.objekts.Coordinate;

public class C2GObjektList<T> extends ArrayList<T> {

		public static double getDistKilometer(Coordinate position, Coordinate target) {
			
			double latSelf = (position.getLatitude() / 180 * Math.PI);
			double lonSelf = (position.getLongitude() / 180 * Math.PI);
			double latTarget = (target.getLatitude() / 180 * Math.PI);
			double lonTarget = (target.getLongitude() / 180 * Math.PI);
			double d = 0; double r = 6378.137;
			double distKilometer;
			// Entfernungsberechnung mittels Einheitskugel
			d = Math.acos((Math.sin(latSelf)*Math.sin(latTarget)) 
					+ (Math.cos(latSelf)*Math.cos(latTarget)*Math.cos(lonTarget - lonSelf)));
			distKilometer = Math.round(d * r * 100) / 100.0;
			return distKilometer;
		}

		public static double getDistMeter(Coordinate position, Coordinate target) {
			double distMeter;
			distMeter = getDistKilometer(position, target) / 1000; 
			return distMeter;
		}
		
		public static int getDurationOf(double Dist) {
			int durationMinute;
			final int maxMeterPerMinute = 65;
			durationMinute = (int) (Dist/maxMeterPerMinute);
			return durationMinute;
		}	

}
