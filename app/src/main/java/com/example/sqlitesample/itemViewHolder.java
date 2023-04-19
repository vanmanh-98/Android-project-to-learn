package com.example.sqlitesample;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.IntConsumer;

public class itemViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView phoneNumber;
    ImageView iconType;
    int typeId;
    icon mIcon;
    View view;
    public itemViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        phoneNumber = (TextView) itemView.findViewById(R.id.number);
        iconType = (ImageView) itemView.findViewById(R.id.icon);
        mIcon = (icon) itemView.findViewById(R.id.frameIcon);

        view = itemView;
    }
}
