package com.tanda.hackathon.acss.axis.Application;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tanda.hackathon.acss.axis.Models.User;

import java.util.ArrayList;

/**
 * Created by gio on 7/29/17.
 */

public class RoomAdapter extends ArrayAdapter {
    public RoomAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        // Get the data item for this position
//        User user = (User) getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
//        }
//        // Lookup view for data population
//        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
//        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
//        // Populate the data into the template view using the data object
//        tvName.setText(user.name);
//        tvHome.setText(user.hometown);
//        // Return the completed view to render on screen
        return convertView;
    }
}
