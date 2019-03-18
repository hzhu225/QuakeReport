package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter<Earthquake>
{
    private static final String LOCATION_SEPARATOR = " of ";

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

        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        String mag_text = magnitudeFormat.format(currentEQ.getMagnitude());
        TextView magnitudeTV = (TextView)listItemView.findViewById(R.id.magnitude_tv);
        magnitudeTV.setText(mag_text);

        GradientDrawable magnitudeBG = (GradientDrawable) magnitudeTV.getBackground();
        int bgColor = getMagnitudeColor(currentEQ.getMagnitude());
        magnitudeBG.setColor(bgColor);                         //bgColor is ARGB value

        String location_full = currentEQ.getLocation();
        String offset_text;
        String location_text;

        if (location_full.contains(LOCATION_SEPARATOR)) {
            String[] parts =location_full.split(LOCATION_SEPARATOR);
            offset_text = parts[0] + LOCATION_SEPARATOR;
            location_text = parts[1];
        } else {
            offset_text = getContext().getString(R.string.near_the);
            location_text = location_full;
        }

        TextView offsetTV = (TextView)listItemView.findViewById(R.id.offset_tv);
        offsetTV.setText(offset_text);

        TextView locationTV = (TextView)listItemView.findViewById(R.id.location_tv);
        locationTV.setText(location_text);



        long time_long = currentEQ.getTime();
        Date dateObj = new Date(time_long);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String date_text = dateFormatter.format(dateObj);
        TextView dateTV = (TextView)listItemView.findViewById(R.id.date_tv);
        dateTV.setText(date_text);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        String time_text = timeFormatter.format(dateObj);
        TextView timeTV = (TextView)listItemView.findViewById(R.id.time_tv);
        timeTV.setText(time_text);

        return listItemView;
    }


    private int getMagnitudeColor(double mag)
    {
        int colorId;
        int magnitudeFloor = (int) Math.floor(mag);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                colorId = R.color.magnitude1;
                break;
            case 2:
                colorId = R.color.magnitude2;
                break;
            case 3:
                colorId = R.color.magnitude3;
                break;
            case 4:
                colorId = R.color.magnitude4;
                break;
            case 5:
                colorId = R.color.magnitude5;
                break;
            case 6:
                colorId = R.color.magnitude6;
                break;
            case 7:
                colorId = R.color.magnitude7;
                break;
            case 8:
                colorId = R.color.magnitude8;
                break;
            case 9:
                colorId = R.color.magnitude9;
                break;
            default:
                colorId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), colorId);         //return the ARGB value of color, rather than its id
    }


}
