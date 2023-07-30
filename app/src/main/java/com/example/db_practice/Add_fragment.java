package com.example.db_practice;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Add_fragment extends Fragment
{
    MainActivity mainActivity;
    TextView addbtn;
    EditText name,number;

    DBHelper dbHelper;
    public Add_fragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.fragment_add_contact,container,false);
        dbHelper = new DBHelper(getContext());
        addbtn=view.findViewById(R.id.addbutton);
        name=view.findViewById(R.id.addname);
        number=view.findViewById(R.id.addnumber);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.adddata(name.getText().toString(),number.getText().toString());
                replaceFragment(new Show_fragment(mainActivity));
                //Bundle bundle=new Bundle();


            }
        });
        return view;


    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction =fm.beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }
}
