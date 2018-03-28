package user.admin.com.myapplication;

/**
 * Created by karan on 1/29/2017.
 */

public class CurrentLocation {

    public String username;
    public Double lat;
    public Double lng;

    public CurrentLocation(String user, Double mLat, Double mLng)
    {
        this.username = user;
        this.lng = mLng;
        this.lat = mLat;
    }

}
