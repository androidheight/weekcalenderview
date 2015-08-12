package com.example.calenderdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class ScheduleListAdapter extends BaseAdapter{
    private ArrayList<Schedulemodel>testItems;
    private Context context;

    public ScheduleListAdapter(ArrayList<Schedulemodel> testItems, Context context) {
        super();
        this.testItems = testItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return testItems.size();
    }

    @Override
    public Object getItem(int position) {
        return testItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.schedule_list_item, null);
            holder = new ViewHolder();
            holder.tvTestName = (TextView)convertView.findViewById(R.id.schedule_title);
            holder.tvExam= (TextView)convertView.findViewById(R.id.schedule_sub_title);
            holder.tvDuration=(TextView)convertView.findViewById(R.id.schedule_sub_title2);

            convertView.setTag(holder);

        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTestName.setText(testItems.get(position).getTitle());
        holder.tvExam.setText(testItems.get(position).getSubTitle());
        holder.tvDuration.setText(testItems.get(position).getSubjectName());
              return convertView;
    }
    static class ViewHolder{
        TextView tvTestName,tvExam,tvDuration;
    }
}