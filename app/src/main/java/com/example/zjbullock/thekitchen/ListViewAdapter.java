package com.example.zjbullock.thekitchen;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Zjbullock on 11/12/2017.
 */

class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<recipeNames> arraylist;

    ListViewAdapter(ArrayList<recipeNames> recipeNames, Context context){
        mContext=context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(recipeNames);
    }

    private class ViewHolder{
        TextView name;
    }



    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public recipeNames getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(arraylist.get(position).getRecipename());
        return convertView;
    }

     void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        arraylist.clear();


            for (recipeNames wp : arraylist) {
                if (wp.getRecipename().toLowerCase(Locale.getDefault()).contains(charText)) {
                    arraylist.add(wp);
                }
            }

        notifyDataSetChanged();
    }


}
