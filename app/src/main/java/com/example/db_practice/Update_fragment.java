package com.example.db_practice;

import android.content.Context;
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

public class Update_fragment extends Fragment
{
    MainActivity mainActivity;
    DBHelper dbHelper;
    EditText name,number;
    TextView updatebtn;

    public Update_fragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.fragment_update_contact,container,false);

        dbHelper = new DBHelper(getContext());
        updatebtn = view.findViewById(R.id.updatebutton);
        name = view.findViewById(R.id.updatename);
        number = view.findViewById(R.id.updatenumber);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updatedata(name.getText().toString(),number.getText().toString());
                replaceFragment(new Show_fragment(mainActivity));
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
