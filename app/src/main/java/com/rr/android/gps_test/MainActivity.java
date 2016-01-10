package com.rr.android.gps_test;

import android.os.Bundle;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


public class MainActivity extends Activity implements LocationListener {

    TextView t1;
    TextView t2;
    TextView t3;
 //   TextView t4;

    LocationManager lm;
    Criteria kr;
    Location loc;
   String najlepszyDostawca;

    private void odswiez(){
        najlepszyDostawca=lm.getBestProvider(kr, true);
        loc=lm.getLastKnownLocation(najlepszyDostawca);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(TextView) findViewById(R.id.textView1);
        t2=(TextView) findViewById(R.id.textView2);
        t3=(TextView) findViewById(R.id.textView3);
  //      t4=(TextView) findViewById(R.id.textView4);

        kr=new Criteria();
        lm=(LocationManager) getSystemService(LOCATION_SERVICE);
       odswiez();
        lm.requestLocationUpdates(najlepszyDostawca, 1000, 1, this);
       // t1.setText("najlepszy dostawca: "+najlepszyDostawca);
        t2.setText("długość geograficzna: "+loc.getLongitude());
        t3.setText("szerokość geograficzna: "+loc.getLatitude());
   //     t4.setText("-------historia------\n");

        LocationAddress locationAddress = new LocationAddress();
        locationAddress.getAddressFromLocation(loc.getLatitude(), loc.getLongitude(),
                getApplicationContext(), new GeocoderHandler());
    }


    @Override
    public void onLocationChanged(Location location) {
 //   odswiez();
   //     t1.setText("najlepszy dostawca: "+najlepszyDostawca);
   //     t2.setText("długość geograficzna: "+loc.getLongitude());
   //     t3.setText("szerokość geograficzna: "+loc.getLatitude());
  //      t4.setText(t4.getText()+""+loc.getLongitude()+"/"+loc.getLatitude()+"\n");

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            t1.setText(locationAddress);
        }
    }
}
