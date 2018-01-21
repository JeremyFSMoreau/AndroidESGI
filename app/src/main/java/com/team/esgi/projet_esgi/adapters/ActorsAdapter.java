package com.team.esgi.projet_esgi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.models.Actors.Actor;
import com.team.esgi.projet_esgi.models.Series.Serie;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActorsAdapter extends ArrayAdapter<Actor> {

    public ActorsAdapter(ArrayList<Actor> actors, Context context) {
        super(context, 0, actors);

    }

    /*@Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        Object object = getItem(position);
        Serie serie = (Serie) object;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Actor actor = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_series_list, parent,false);
        }
        TextView text = (TextView) convertView.findViewById(R.id.text1);
        text.setText(actor.getName() + " \n AS \n " + actor.getRole());
        return convertView;
    }
}