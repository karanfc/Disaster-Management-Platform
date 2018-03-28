package user.admin.com.myapplication;

/**
 * Created by karan on 1/29/2017.
 */

public class RescueLocations {

    public Double lat1 = new Double(0);
    public Double lng1;
    public Double lat2;
    public Double lng2;
    public Double lat3;
    public Double lng3;
    public Double lat4;
    public Double lng4;
    public Double lat5;
    public Double lng5;

   /* public RescueLocations(Double mlat1, Double mlng1,Double mlat2, Double mlng2,Double mlat3, Double mlng3,Double mlat4, Double mlng4,Double mlat5, Double mlng5 )
    {
        this.lat1 = mlat1;
        this.lng1 = mlng1;
        this.lat2 = mlat2;
        this.lng2 = mlng2;
        this.lat3 = mlat3;
        this.lng3 = mlng3;
        this.lat4 = mlat4;
        this.lng4 = mlng4;
        this.lat5 = mlat5;
        this.lng5 = mlng5;

    }*/

    public RescueLocations()
    {}

    public RescueLocations(Double mLat1)
    {
        this.lat1 = mLat1;
    }



    public void setLat (Double lat1)
    {
        this.lat1 = lat1;
    }

    public void setLng (Double lng1)
    {
        this.lng1 = lng1;
    }

    public Double getLat1 ()
    {
        return lat1;
    }

    public Double getLng1()
    {
        return lng1;
    }

    public Double getLat2 ()
    {
        return lat2;
    }

    public Double getLng2()
    {
        return lng2;
    }
    public Double getLat3 ()
    {
        return lat3;
    }

    public Double getLng3()
    {
        return lng3;
    }
    public Double getLat4 ()
    {
        return lat4;
    }

    public Double getLng4()
    {
        return lng4;
    }
    public Double getLat5 ()
    {
        return lat5;
    }

    public Double getLng5()
    {
        return lng5;
    }

}
