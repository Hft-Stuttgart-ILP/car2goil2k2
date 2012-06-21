package de.mra.calc;

import c2G.mobile.api.objekts.Coordinate;

public class Distance {
		
//		static double distKilometer;
//		static double distMeter;
//		static int durationMinute;
			
		static // geographische Koordinaten (Breite lat, Länge lon) in Grad
		double latSelf;
		static double lonSelf;
		
		static double latTarget;
		static double lonTarget;
			
//		public Distance(double latSelf, double lonSelf, double latTarget,
//				double lonTarget) {
//			
//			// Konvertierung in Dezimalgraddarstellung
//			this.latSelf = latSelf / 180 * Math.PI;
//			this.lonSelf = lonSelf / 180 * Math.PI;
//			this.latTarget = latTarget / 180 * Math.PI;
//			this.lonTarget = lonTarget / 180 * Math.PI;
//					
//		}
		
//		public static void distance(Coordinate position, Coordinate target) {
//			// Konvertierung in Dezimalgraddarstellung
//			latSelf = (position.getLatitude() / 180 * Math.PI);
//			lonSelf = (position.getLongitude() / 180 * Math.PI);
//			latTarget = (target.getLatitude() / 180 * Math.PI);
//			lonTarget = (target.getLongitude() / 180 * Math.PI);
//		}
		
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
