package com.eyadalalimi.islamic.pro.nbmessage.apapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.nbmessage.database.database;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by andrid on 1/3/2017.
 */

public class adap_msg_fav extends RecyclerView.Adapter<adap_msg_fav.ViewHolder> {
    ArrayList<String> Message;
    Context context;
    View view1;
    ViewHolder viewHolder1;


    public adap_msg_fav(Context context1, ArrayList<String> message) {
        Message = message;
        context = context1;
    }

    database db;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView message;
        ImageView Copy, Whats, share;
        ImageView FOVARATE;

        public ViewHolder(View v) {
            super(v);
            message = (TextView) v.findViewById(R.id.mess);
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "the_sans.otf");
            message.setTypeface(type2);
            Copy = (ImageView) v.findViewById(R.id.copy);
            Whats = (ImageView) v.findViewById(R.id.whats);
            share = (ImageView) v.findViewById(R.id.share);
            share = (ImageView) v.findViewById(R.id.share);
            FOVARATE = (ImageView) v.findViewById(R.id.fovarate);

            FOVARATE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        db = new database(context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    db.Fov(Message.get(getAdapterPosition()));
                    Message.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    Toast.makeText(context, "تم الحذف من المفضله", Toast.LENGTH_SHORT).show();
                }
            });
            Copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboard.setText(Message.get(getAdapterPosition()));
                        Toast.makeText(context, "تم نسخ الرساله", Toast.LENGTH_SHORT).show();
                    } else {
                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager)  context.getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("text label",Message.get(getAdapterPosition()));
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(context, "تم نسخ الرساله", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            Whats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                    whatsappIntent.setType("text/plain");
                    whatsappIntent.setPackage("com.whatsapp");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT, Message.get(getAdapterPosition()));
                    try {
                        context.   startActivity(whatsappIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        // context.
                        Toast.makeText(context, "ل يوجد تطبيق ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String shareBody = Message.get(getAdapterPosition());
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    context. startActivity(Intent.createChooser(sharingIntent, "مشاركه"));
                }
            });



        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.message.setText(Message.get(position));
        holder.FOVARATE.setImageResource(R.drawable.fov_set);
    }

    @Override
    public int getItemCount() {
        return Message.size();
    }
}
