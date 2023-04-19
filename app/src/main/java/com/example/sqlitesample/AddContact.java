package com.example.sqlitesample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddContact extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText mEditName;
    private EditText mEditPhone;
    private Spinner mChooseType;
    private Button mAddBut;
    DBhelper mDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        mEditName = (EditText) findViewById(R.id.inputName);
        mEditName.setHint("Name");
        mEditPhone = (EditText) findViewById(R.id.inputPhone);
        mEditPhone.setHint("Phone Number");
        mAddBut = (Button) findViewById(R.id.add);
        mAddBut.setText("Add Contact");
        mChooseType = (Spinner) findViewById(R.id.chooseType);
        String[] items = new String[]{"None", "Lover", "Doctor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spiner_text, items);
        mChooseType.setAdapter(adapter);
        mChooseType.setOnItemSelectedListener(this);
        mDBHelper = new DBhelper(getApplicationContext());
    }
    @Override
    protected void onResume() {
        super.onResume();
        mAddBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBHelper.addContact(mEditName.getText().toString(), mEditPhone.getText().toString(), mChooseType.getSelectedItem().toString());
                mEditPhone.setText("");
                mEditPhone.setHint("Phone Number");
                mEditName.setText("");
                mEditName.setHint("Name");
                mChooseType.setSelection(0);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}