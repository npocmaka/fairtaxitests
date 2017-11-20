package utils;

public class TrackingFare {
	private double distance;
	private double fare;
	
	public double getDistance() {
		return distance;
	}
	
	public double getFare() {
		return fare;
	}
	
	public TrackingFare(double distance,double fare) {
		this.fare=fare;
		this.distance=distance;
	}
	

}
