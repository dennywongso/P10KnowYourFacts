package com.example.a15017096.p10knowyourfacts;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {
    ImageView iv;
    View view;


    public Frag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frag1, container, false);
        iv = (ImageView)view.findViewById(R.id.iv);
        String imageUrl = "http://68.media.tumblr.com/1440327d069c8e21fa52097ae6fe2306/tumblr_otq11n888k1roqv59o1_500.png";
        Picasso.with(getContext()).load(imageUrl).into(iv);


        // Inflate the layout for this fragment
        return view;


    }

}
