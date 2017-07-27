package com.example.a15017096.p10knowyourfacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.crazyhitty.chdev.ks.rssmanager.Channel;
import com.crazyhitty.chdev.ks.rssmanager.RSS;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;
import java.util.ArrayList;
import java.util.List;

public class Frag4 extends Fragment implements RssReader.RssCallback {
    View view;
    ArrayAdapter<String> aa;
    ArrayList<String> al;
    ListView lv;
    private RssReader rssReader = new RssReader(this);

    public Frag4() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frag4, container, false);
        String[] url = new String[]{"https://www.gov.sg/rss/factuallyrss"};
        rssReader.loadFeeds(url);
        lv = (ListView)view.findViewById(R.id.lv);
        return view;

    }
    //load feeds
    private void loadFeeds(String[] urls) {
        rssReader.loadFeeds(urls);
    }
    @Override
    public void rssFeedsLoaded(final List<RSS> rssList) {
        al = new ArrayList<>();
        aa = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        for(Channel.Item i : rssList.get(0).getChannel().getItems()){
                al.add(i.getTitle());
        }
        aa.notifyDataSetChanged();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent rpIntent = new Intent(Intent.ACTION_VIEW);
                String urls = rssList.get(0).getChannel().getItems().get(position).getLink();
                rpIntent.setData(Uri.parse(urls));
                startActivity(rpIntent);
            }
        });
    }
    @Override
    public void unableToReadRssFeeds(String errorMessage) {

    }
}
