package utils;

public class Coordinates {
	private double lat;
	private double lon;
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public Coordinates(double lat,double lon) {
		if(lon>180 || lon<-180) {
			throw new IllegalArgumentException("longitude should be between -180 and 180");
		}
		
		if(lat>85.05 || lon<-85.05) {
			throw new IllegalArgumentException("latitude  should be between -85.05 and 85.05");
		}
		this.lat=lat;
		this.lon=lon;
	}
	
	public static Coordinates[] double2crds(double[] route) {
		Coordinates[] crds=new Coordinates[route.length/2];
		//System.out.println("#"+route.length/2+":"+route.length+"#");
		for(int i=0;i<route.length-1;i=i+2) {
			crds[i/2]=new Coordinates(route[i], route[i+1]);
			//System.out.println(""+i/2+"->"+route[i]+","+route[i+1] +"("+i+":"+(i+1)+")");
		}
		
		return crds;
	}

}
