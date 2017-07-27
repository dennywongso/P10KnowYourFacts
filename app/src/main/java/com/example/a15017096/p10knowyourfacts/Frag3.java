package com.example.a15017096.p10knowyourfacts;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag3 extends Fragment {
    Button btnColor;
    View view;


    public Frag3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frag3, container, false);
        btnColor = (Button)view.findViewById(R.id.btnColor);
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int r1 = r.nextInt(255-1+1)+1;
                int r2 = r.nextInt(255-1+1)+1;
                int r3 = r.nextInt(255-1+1)+1;
                view.setBackgroundColor(Color.rgb(r1,r2,r3));
            }
        });

        // Inflate the layout for this fragment
        return view;


    }

}
