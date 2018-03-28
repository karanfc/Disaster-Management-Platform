package user.admin.com.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WhatShouldIDo extends AppCompatActivity {
ImageView earthq,volc,hurricane, tsnuami;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_should_ido);
        earthq = (ImageView)findViewById(R.id.what_earthquake);
        volc = (ImageView)findViewById(R.id.what_volcano);
        hurricane=(ImageView)findViewById(R.id.what_hurricane);
        tsnuami=(ImageView)findViewById(R.id.what_tsunami);

        earthq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),WhatEarthquake.class));
            }
        });


        volc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),WhatVolcano.class));
            }
        });


        hurricane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),WhatHurricane.class));
            }
        });


        tsnuami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),WhatTsunami.class));
            }
        });
    }
}
