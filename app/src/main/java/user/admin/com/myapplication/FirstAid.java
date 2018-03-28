package user.admin.com.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import user.admin.com.firstaid.Burn;
import user.admin.com.firstaid.Choking;
import user.admin.com.firstaid.FirstAidKit;
import user.admin.com.firstaid.HeavyBleeding;
import user.admin.com.firstaid.Hypothermia;
import user.admin.com.firstaid.MuscleFracture;
import user.admin.com.firstaid.Seizure;
import user.admin.com.firstaid.SnakeBite;
import user.admin.com.firstaid.Sprain;

public class FirstAid extends AppCompatActivity implements View.OnClickListener{

    TextView heavy_bleeding,choking,snake_bite,burn,muscle_fracture,siezure,sprain,hypothermia,firstaidkit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        heavy_bleeding = (TextView) findViewById(R.id.txt_heavy_bleeding);
        choking=(TextView)findViewById(R.id.txt_choking);
        snake_bite=(TextView)findViewById(R.id.txt_snake_bite);
        burn = (TextView) findViewById(R.id.txt_burn);
        muscle_fracture=(TextView)findViewById(R.id.txt_muscle_fracture);
        siezure=(TextView)findViewById(R.id.txt_seizure);
        sprain = (TextView) findViewById(R.id.txt_sprain);
        hypothermia=(TextView)findViewById(R.id.txt_hypothermia);
        firstaidkit=(TextView)findViewById(R.id.txt_first_aid);

        heavy_bleeding.setOnClickListener(this);
        choking.setOnClickListener(this);
        snake_bite.setOnClickListener(this);
        burn.setOnClickListener(this);
        muscle_fracture.setOnClickListener(this);
        siezure.setOnClickListener(this);
        sprain.setOnClickListener(this);
        sprain.setOnClickListener(this);
        hypothermia.setOnClickListener(this);
        firstaidkit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.txt_first_aid:
                    startActivity(new Intent(getApplicationContext(), FirstAidKit.class));
                    break;

                case R.id.txt_heavy_bleeding:
                    startActivity(new Intent(getApplicationContext(), HeavyBleeding.class));
                    break;

                case R.id.txt_choking:
                    startActivity(new Intent(getApplicationContext(), Choking.class));
                    break;

                case R.id.txt_snake_bite:
                    startActivity(new Intent(getApplicationContext(), SnakeBite.class));
                    break;

                case R.id.txt_burn:
                    startActivity(new Intent(getApplicationContext(), Burn.class));
                    break;

                case R.id.txt_muscle_fracture:
                    startActivity(new Intent(getApplicationContext(), MuscleFracture.class));
                    break;

                case R.id.txt_seizure:
                    startActivity(new Intent(getApplicationContext(), Seizure.class));
                    break;

                case R.id.txt_sprain:
                    startActivity(new Intent(getApplicationContext(), Sprain.class));
                    break;

                case R.id.txt_hypothermia:
                    startActivity(new Intent(getApplicationContext(), Hypothermia.class));
                    break;

            }
    }
}
