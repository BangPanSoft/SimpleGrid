package com.pitipong.gridview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pitipong.gridview.R;
import com.pitipong.gridview.model.PrettyModel;
/**
 * Created by pw on 17/3/2559.
 */

public class ListAdapter extends BaseAdapter {

    Context context;
    PrettyModel prettyModel;

    public ListAdapter(Context context, PrettyModel prettyModel) {
        this.context = context;
        this.prettyModel = prettyModel;
    }

    @Override
    public int getCount() {
        return prettyModel.getResults().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pretty, parent, false);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.txtViewName.setText(prettyModel.getResults().get(position).getWho());
        Glide.with(context)
                .load(prettyModel.getResults().get(position).getUrl())
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .crossFade()
                .into(mHolder.imgViewPhoto);

        return convertView;
    }

    static class ViewHolder {
        TextView txtViewName;
        ImageView imgViewPhoto;
        ViewHolder(View view) {

            txtViewName  = (TextView) view.findViewById(R.id.tv_name);
            imgViewPhoto = (ImageView) view.findViewById(R.id.img_cover);

        }
    }
}
