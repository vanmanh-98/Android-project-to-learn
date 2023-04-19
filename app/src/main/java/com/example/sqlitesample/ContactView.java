package com.example.sqlitesample;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactView extends Fragment implements NavigationView
        .OnNavigationItemSelectedListener {

    contactAdapter adapter;
    RecyclerView recyclerView;
    ClickListiner listiner;
    FrameLayout mAddContact;
    EditContact mEditContact;
    String TAG = "TESSSSSSSSS";
    DBhelper mDBHelper;
    final  String TABLE_NAME = "CONTACT_TABLE";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    ClickItem mClickItem;
    public interface ClickItem{
        void onClickItem(int index, String name, String phone, int type);
    }
    public ContactView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactView.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactView newInstance(String param1, String param2) {
        ContactView fragment = new ContactView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mClickItem = (ClickItem) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contact_view, container, false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        mAddContact = (FrameLayout) view.findViewById(R.id.addContact);
        listiner = new ClickListiner(){
            @Override
            public void click(int index, String name, String phone, int id){
                mClickItem.onClickItem(index, name, phone, id);
            }
        };

        mDBHelper = new DBhelper(getContext());

    }

    @Override
    public void onResume() {
        super.onResume();
        mAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addActivity = new Intent(getContext(),AddContact.class);
                startActivity(addActivity);
            }
        });
        List<itemData> list = getListDataFromDB();
        adapter = new contactAdapter(list, getContext(),listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private List<itemData> getListDataFromDB(){
        List<itemData> list = new ArrayList<>();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursordb = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if(cursordb.getCount() > 0) {
            cursordb.moveToFirst();
            do {
                list.add(new itemData(cursordb.getString(1), cursordb.getString(2), cursordb.getString(3)));
            }
            while (cursordb.moveToNext());
            return list;
        }
        return null;
    }
}