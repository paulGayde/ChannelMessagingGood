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
    private final Context context;
    private final List<Channel> values;


    public MyAdapter(Context context, List<Channel> values) {
        super(context, R.layout.channels_list_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.channels_list_row, parent, false);
        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
        TextView tvUser = (TextView) rowView.findViewById(R.id.tvNbPers);
        Channel chan = values.get(position);
        tvName.setText(chan.getName());
        tvUser.setText("nombre d'utilisateurs connectés : " + chan.getConnectedusers());
        return rowView;
    }
}