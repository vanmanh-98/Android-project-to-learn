package com.example.sqlitesample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactView.ClickItem, EditContact.viewCreateListener
{

    String TAG = "TESSSSSSSSS";
    EditContact mEditContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startContactView();

    }
    int mTempIndex;
    String mTempName;
    String mTempPhone;
    int mTempType;

    @Override
    protected void onResume() {
        super.onResume();
    }
    private void startContactView(){
        ContactView fragMain = new ContactView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, fragMain);
        ft.commit();
    }
    private void startEditView(){
        mEditContact = new EditContact();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.container, mEditContact);
        fragmentTransaction1.addToBackStack(null);
        fragmentTransaction1.commit();
    }

    @Override
    public void onClickItem(int index, String name, String phone, int type) {
        startEditView();
        mTempIndex = index;
        mTempName = name;
        mTempPhone = phone;
        mTempType = type;
    }

    @Override
    public void onViewComplete() {
        mEditContact.setUnEditValue(mTempIndex, mTempName, mTempPhone, mTempType);
    }
}