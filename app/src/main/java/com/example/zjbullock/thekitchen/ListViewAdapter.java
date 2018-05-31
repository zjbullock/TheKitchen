package com.example.zjbullock.thekitchen;

import android.content.Context;
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

public class ListViewAdapter extends BaseAdapter {

   Context mContext;
    LayoutInflater inflater;
    private ArrayList<recipeNames> arraylist;

    public ListViewAdapter(Context context){
        mContext=context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(FirstFragment.recipeNamesArrayList);
    }

    public class ViewHolder{
        TextView name;
    }



    @Override
    public int getCount() {
        return FirstFragment.recipeNamesArrayList.size();
    }

    @Override
    public recipeNames getItem(int position) {
        return FirstFragment.recipeNamesArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.listview_item, null);

            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(FirstFragment.recipeNamesArrayList.get(position).getRecipename());
        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        FirstFragment.recipeNamesArrayList.clear();
        if (charText.length() == 0) {
            FirstFragment.recipeNamesArrayList.addAll(arraylist);
        } else {
            for (recipeNames wp : arraylist) {
                if (wp.getRecipename().toLowerCase(Locale.getDefault()).contains(charText)) {
                    FirstFragment.recipeNamesArrayList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


}
