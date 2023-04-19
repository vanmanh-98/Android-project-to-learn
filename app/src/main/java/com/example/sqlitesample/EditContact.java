package com.example.sqlitesample;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditContact extends Fragment implements AdapterView.OnItemSelectedListener{

    EditText mEditName;
    EditText mEditPhone;
    Spinner mType;
    Button mConFirm;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface viewCreateListener{
        void onViewComplete();
    }
    viewCreateListener mViewCreateListener;

    public EditContact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditContact.
     */
    // TODO: Rename and change types and number of parameters
    public static EditContact newInstance(String param1, String param2) {
        EditContact fragment = new EditContact();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewCreateListener = (viewCreateListener) context;
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
        return inflater.inflate(R.layout.activity_add_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditName = view.findViewById(R.id.inputName);
        mEditPhone = view.findViewById(R.id.inputPhone);
        mConFirm = (Button) view.findViewById(R.id.add);
        mConFirm.setText("Confirm");
        mType = (Spinner) view.findViewById(R.id.chooseType);
        String[] items = new String[]{"None", "Lover", "Doctor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spiner_text, items);
        mType.setAdapter(adapter);
        mType.setOnItemSelectedListener(this);
        mViewCreateListener.onViewComplete();
    }
    public void setUnEditValue(int index, String name, String phone, int type){
        mEditName.setText(name);
        mEditPhone.setText(phone);
        mType.setSelection(type);
    }
}