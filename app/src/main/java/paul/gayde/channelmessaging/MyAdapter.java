package paul.gayde.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gaydep on 27/01/2017.
 */
public class MyAdapter extends ArrayAdapter<Channel> {
    public MyAdapter(Context context, List<Channel> channels) {
        super(context, 0, channels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Channel channel = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_channel_list, parent, false);
        }
        TextView channelsName = (TextView) convertView.findViewById(R.id.textViewChannelName);
        TextView channelsNbPers = (TextView) convertView.findViewById(R.id.textViewChannelNbPers);
        channelsName.setText(channel.getName());
        channelsNbPers.setText("Nombre de personnes connectées : " + channel.getConnectedusers().toString());
        return convertView;
    }
}