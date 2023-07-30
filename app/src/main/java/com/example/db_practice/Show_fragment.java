package com.example.db_practice;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Show_fragment extends Fragment
{
    MainActivity mainActivity;
    DBHelper dbHelper;
    RecyclerView recyclerView;
    ArrayList<Model_Class> list = new ArrayList<>();

    public Show_fragment(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.fragment_show_contact,container,false);

        dbHelper = new DBHelper(getContext());
        recyclerView = view.findViewById(R.id.recycleview);

        displayalldata();
        return view;

    }

    private void displayalldata()
    {
        Cursor cursor = dbHelper.displaydata();
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String number=cursor.getString(2);
            Model_Class mc = new Model_Class(id,name,number);
            list.add(mc);
        }

        RecycleAdapter adapter = new RecycleAdapter(Show_fragment.this,list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

}
