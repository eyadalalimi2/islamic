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
import com.eyadalalimi.islamic.pro.nbmessage.activi.activity_editsection;
import com.eyadalalimi.islamic.pro.nbmessage.database.database;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by andrid on 1/3/2017.
 */

public class adap_msg extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<String> Message;
    Context context;
    View view1;
    ArrayList<String> Fovatate;
    int catogray_messaga;


    public adap_msg(Context context1, ArrayList<String> message, ArrayList<String> fovarate, int catogry) {
        Message = message;
        context = context1;
        Fovatate = fovarate;
        catogray_messaga = catogry;


    }

    private int getDrawableId(ImageView iv) {
        return (Integer) iv.getTag();
    }


    @Override
    public int getItemViewType(int position) {

                 return super.getItemViewType(position);

    }

    database db;




    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView message;
        ImageView Copy, Whats, share;
        ImageView FOVARATE;

        public ViewHolder(View v) {
            super(v);
            message = (TextView) v.findViewById(R.id.mess);
            Copy = (ImageView) v.findViewById(R.id.copy);
            Whats = (ImageView) v.findViewById(R.id.whats);
            share = (ImageView) v.findViewById(R.id.share);
            FOVARATE = (ImageView) v.findViewById(R.id.fovarate);
            Typeface type = Typeface.createFromAsset(context.getAssets(), "the_sans.otf");
            message.setTypeface(type);
            //  Copy
            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, activity_editsection.class);
                    i.putExtra("part", catogray_messaga);
                    i.putExtra("postion", getAdapterPosition());
                    context.startActivity(i);

                }
            });
            FOVARATE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        db = new database(context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (Fovatate.get(getAdapterPosition()).matches("0")) {
                        FOVARATE.setImageResource(R.drawable.fov_set);
                        db.SetFovarate(Message.get(getAdapterPosition()));
                        Fovatate.set(getAdapterPosition(), "1");

                        Toast.makeText(context, "تم اضافه الى المفضله", Toast.LENGTH_SHORT).show();
                    } else if (Fovatate.get(getAdapterPosition()).matches("1")) {
                        FOVARATE.setImageResource(R.drawable.fov);
                        db.Fov(Message.get(getAdapterPosition()));
                        Fovatate.set(getAdapterPosition(), "0");

                        Toast.makeText(context, "تم الحذف من المفضله", Toast.LENGTH_SHORT).show();
                    }


                }
            });
            Copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int sdk = android.os.Build.VERSION.SDK_INT;
                    if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboard.setText(Message.get(getAdapterPosition()));
                        Toast.makeText(context, "تم نسخ الرساله", Toast.LENGTH_SHORT).show();
                    } else {
                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("text label", Message.get(getAdapterPosition()));
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
                        context.startActivity(whatsappIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        // context.
                        Toast.makeText(context, "ل يوجد تطبيق واتس اب ", Toast.LENGTH_SHORT).show();
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
                    context.startActivity(Intent.createChooser(sharingIntent, "مشاركه"));
                }
            });


        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


             ViewHolder MessageHolder = (ViewHolder) holder;
            MessageHolder.message.setText(Message.get(position));
            if (Fovatate.get(position).matches("0")) {
                MessageHolder.FOVARATE.setImageResource(R.drawable.fov);
            } else if (Fovatate.get(position).matches("1")) {
                MessageHolder.FOVARATE.setImageResource(R.drawable.fov_set);
            }


    }

    @Override
    public int getItemCount() {
        return Message.size();
    }
}
