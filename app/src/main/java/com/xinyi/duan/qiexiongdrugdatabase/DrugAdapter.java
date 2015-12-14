package com.xinyi.duan.qiexiongdrugdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Duan on 2015/12/8.
 */
public class DrugAdapter extends BaseAdapter {

    private List<DrugInfo> lists;
    private Context context;
    private LinearLayout layout;

    public DrugAdapter(List<DrugInfo> lists, Context context) {
        this.lists = lists;
        this.context = context;

    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.drug, null);
            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.drug_name);
            viewHolder.standardTextView = (TextView) convertView.findViewById(R.id.drug_standard);
            viewHolder.idTextView = (TextView) convertView.findViewById(R.id.drug_id);
            viewHolder.nameTextView.setText(lists.get(position).getDrugName());
            viewHolder.standardTextView.setText(lists.get(position).getDrugStandard());
            viewHolder.idTextView.setText(lists.get(position).getDrugId());
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.nameTextView.setText(lists.get(position).getDrugName());
            viewHolder.standardTextView.setText(lists.get(position).getDrugStandard());
            viewHolder.idTextView.setText(lists.get(position).getDrugId());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView nameTextView;
        TextView standardTextView;
        TextView idTextView;
    }
}
