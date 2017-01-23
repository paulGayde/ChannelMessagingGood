package paul.gayde.channelmessaging;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

public class ChannelListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, Downloader.OnDownloadCompleteListener {

    private ListView lvChannels;

    public static final String PREFS_NAME = "stockage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String accestoken = settings.getString("accestoken","");

        HashMap<String,String> params = new HashMap<>();
        params.put("accestoken",accestoken);

        Downloader d = new Downloader(this,"http://www.raphaelbischof.fr/messaging/?function=getchannels",params);
        d.addOnDownloadCompleteListener(this);
        d.execute();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onDownloadComplete(String result) {

        Gson gson = new Gson();
        Channels c = gson.fromJson(result, Channels.class);

        for(Channel ch :c.channels){
            Toast.makeText(this, ch.name, Toast.LENGTH_SHORT).show();
        }

        lvChannels = (ListView) findViewById(R.id.lvChannels);
        lvChannels.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, c.channels));
        lvChannels.setOnItemClickListener(this);
    }
}
