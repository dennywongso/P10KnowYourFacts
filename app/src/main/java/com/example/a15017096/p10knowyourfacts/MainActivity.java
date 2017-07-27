package com.example.a15017096.p10knowyourfacts;

import android.app.Activity;
import android.app.AlarmManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Fragment> al;
    FragmentAdapter adapter;
    ViewPager vPager;
    Button btnRead;
    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRead = (Button)findViewById(R.id.btnRead);
        vPager = (ViewPager)findViewById(R.id.viewPager);
        FragmentManager fm = getSupportFragmentManager();
        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());
        al.add(new Frag4());

        adapter = new FragmentAdapter(fm, al);
        vPager.setAdapter(adapter);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);


                //Create a new PendingIntent and add it to the AlarmManager
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                int reqCode = 12345;
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(MainActivity.this,
                                reqCode, intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);

                // Get AlarmManager instance
                am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);

                // Set the alarm
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.previous){
            if (vPager.getCurrentItem() > 0){
                int previousPage = vPager.getCurrentItem() - 1;
                vPager.setCurrentItem(previousPage, true);
            }
        }
        else if(item.getItemId() == R.id.random) {
            Random r = new Random();
            int r1 = r.nextInt(vPager.getChildCount());
            vPager.setCurrentItem(r1, true);
        } else {
            int max = vPager.getChildCount();
            if (vPager.getCurrentItem() < max-1){
                int nextPage = vPager.getCurrentItem() + 1;
                vPager.setCurrentItem(nextPage, true);
            }

        }
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("page", vPager.getCurrentItem());
        editor.commit();
        Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        int page = sharedPref.getInt("page",1);
        vPager.setCurrentItem(page, true);
    }
}
