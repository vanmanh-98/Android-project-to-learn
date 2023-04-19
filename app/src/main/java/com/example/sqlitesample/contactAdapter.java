package com.example.sqlitesample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class contactAdapter extends RecyclerView.Adapter<itemViewHolder>{
    List<itemData> list = Collections.emptyList();
    final String Doctor = "Doctor";
    final String lover = "love";
    String TAG = "van thien mon";
    Context context;
    ClickListiner listiner;
    public contactAdapter(List<itemData> list, Context context, ClickListiner listiner){
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }
    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context1 = parent.getContext();
        Log.d(TAG, "onCreateViewHolder: -----------------------------------------------------------------------------------------------");
        LayoutInflater inflater = LayoutInflater.from(context1);
        View contactView = inflater.inflate(R.layout.item_layout, parent, false);
        itemViewHolder viewHolder = new itemViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        final String mName = list.get(position).name;
        final String mPhone = list.get(position).phoneNum;
        final int mId = list.get(position).typeId;
        holder.name.setText(list.get(position).name);
        holder.phoneNumber.setText(list.get(position).phoneNum);
        switch (list.get(position).typeText){
            case Doctor:
                holder.iconType.setImageResource(R.drawable.user_md_24);
                Log.d(TAG, "onBindViewHolder: height = "+ holder.iconType.getMeasuredHeight()+ " width = "+holder.iconType.getMeasuredWidth());
                break;
            case lover:
                holder.iconType.setImageResource(R.drawable.following_24);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listiner.click(index, mName, mPhone, mId);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }
}
