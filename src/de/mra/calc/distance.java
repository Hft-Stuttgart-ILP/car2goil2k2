package de.mra.calc;

public class distance {
	
	public class distance {
		
		double distKilometer;
		double distMeter;
		int durationMinute;
			
		// geographische Koordinaten (Breite lat, Länge lon) in Grad
		
		static double latSelf;
		static double lonSelf;
		
		static double latTarget;
		static double lonTarget;
			
		public distance(double latSelf, double lonSelf, double latTarget,
				double lonTarget) {
			super();
			
			// Konvertierung in Dezimalgraddarstellung
			distance.latSelf = latSelf / 180 * Math.PI;
			distance.lonSelf = lonSelf / 180 * Math.PI;
			distance.latTarget = latTarget / 180 * Math.PI;
			distance.lonTarget = lonTarget / 180 * Math.PI;
					
		}
		
		public double getDistKilometer() {
					
			double d = 0; double r = 6378.137;
					
			// Entfernungsberechnung mittels Einheitskugel
			d = Math.acos( (Math.sin(latSelf)*Math.sin(latTarget)) + (Math.cos(latSelf)*Math.cos(latTarget)*Math.cos(lonTarget - lonSelf)) );
			   		
			distKilometer = Math.round(d * r * 100) / 100.0;
			
			return distKilometer;
			
		}

		public double getDistMeter() {
								
			distMeter = getDistKilometer() * 1000; 
					
			return distMeter;
			
		}
		
		public int getDurationOf(double Dist) {
			
			final int maxMeterPerMinute = 65;
			
			durationMinute = (int) (Dist/maxMeterPerMinute);
			
			return durationMinute;
			
		}	
		
	}

}
