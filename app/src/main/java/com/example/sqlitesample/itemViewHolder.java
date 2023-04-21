package com.example.sqlitesample;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.IntConsumer;

public class itemViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView phoneNumber;
    private ImageView iconType;
    String TAG = "viewHolder";
    int typeId;

    View view;
    public itemViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d(TAG, "itemViewHolder: ------------------------------------------------------");
        name = (TextView)itemView.findViewById(R.id.name);
        phoneNumber = (TextView) itemView.findViewById(R.id.number);
        iconType = (ImageView) itemView.findViewById(R.id.icon);

        view = itemView;
    }
    void setTextName(String textName){
        name.setText(textName);
    }
    void setTextPhone(String textPhone){
        phoneNumber.setText(textPhone);
    }
    void setIconType(int iconId){
        iconType.setImageResource(iconId);
    }
}
