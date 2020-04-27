package dk.nikolaj.maplocationandfirebase.model;

public class MyLocation {

    private String lat; //latitude
    private String lon; //longitude

    public MyLocation(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLatitude() {
        if (lat.length() > 0){
            return Double.parseDouble(lat);
        }
        return 0;
    }

    public double getLongitude() {
        if (lon.length() > 0){
            return Double.parseDouble(lon);
        }
        return 0;
    }
}
