package com.eyadalalimi.islamic.pro.nbholyquran;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anwar_se on 6/24/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class StringArrayAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> stringList;
//    private OnItemClickListener listener;

    public StringArrayAdapter(@NonNull Context context, List<String> list) {
        super(context, android.R.layout.simple_spinner_dropdown_item, list);
        mContext = context;
        stringList = list;
    }

    @NonNull
    @Override
    public View getView(/*final*/ int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

        String currentString = stringList.get(position);
//        listItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null)
//                    listener.onItemClick(v, position);
//            }
//        });
        TextView release = (TextView) listItem.findViewById(android.R.id.text1);
        release.setText(currentString);

        return listItem;
    }

//    public void setListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }

    public void notifyItems(List<String> stringList) {
        this.stringList.clear();
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }
}
