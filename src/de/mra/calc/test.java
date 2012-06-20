package de.mra.calc;

public class test {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		distance d = new distance(48.797917, 9.001082, 48.795783, 9.015652);	
		
		double dist1 = d.getDistKilometer();
		double dist2 = d.getDistMeter();

		System.out.println(dist1);
		System.out.println(dist2);
		
		
		int duration = d.getDurationOf(d.getDistMeter());
		
		System.out.println(duration + " Minuten");
		
	}

}
