package com.buu.se.s55160077.callmemaybe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LUKHINNN on 11/05/2015.
 */
public class ContactAdapter extends BaseAdapter {
    Context mContext;
    List<ContactItem> mItem;

    public ContactAdapter(Context context, List<ContactItem> item) {
        this.mContext= context;
        this.mItem = item;
    }

    public int getCount() {
        return mItem.size();
    }

    public Object getItem(int position) {
        return mItem.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final ContactItem item = mItem.get(position);

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.contact_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.num = (TextView) convertView.findViewById(R.id.telnum);
            //viewHolder.imgFood = (ImageView) convertView.findViewById(R.id.food_path);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(item.getTxtName());
        viewHolder.num.setText(item.getTxtTelnum());
        //ImageView food_path = (ImageView) convertView.findViewById(R.id.food_path);

        return convertView;
    }
    private static class ViewHolder {
        TextView name;
        TextView num;
    }
}
