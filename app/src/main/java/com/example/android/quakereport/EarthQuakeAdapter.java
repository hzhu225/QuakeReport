package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter<Earthquake>
{
    public EarthQuakeAdapter(Context context, List<Earthquake> earthquakes)
    {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEQ = getItem(position);

        TextView magnitudeTV = (TextView)listItemView.findViewById(R.id.magnitude_textview);
        magnitudeTV.setText(currentEQ.getMagnitude());

        TextView locationTV = (TextView)listItemView.findViewById(R.id.location_textview);
        locationTV.setText(currentEQ.getLocation());

        TextView dateTV = (TextView)listItemView.findViewById(R.id.date_textview);
        dateTV.setText(currentEQ.getDate());

        return listItemView;
    }
}
