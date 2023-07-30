package com.example.db_practice;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements PopupMenu.OnMenuItemClickListener {

    Show_fragment show_fragment;
    ArrayList<Model_Class> list;
    DBHelper dbHelper;

    public RecycleAdapter(Show_fragment show_fragment, ArrayList<Model_Class> list) {
        this.show_fragment = show_fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
        holder.id.setText(""+list.get(position).getId()+".");
        holder.name.setText(list.get(position).getName());
        holder.number.setText(list.get(position).getNumber());

        holder.popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.updatecontact){
                            replaceFragment(new Update_fragment(show_fragment.mainActivity));
                        }
                        if(item.getItemId()==R.id.deletecontact){
                            dbHelper.datadelete(holder.id.getText());
                        }
                        return true;
                    }
                });
            }
        });
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fm = show_fragment.getParentFragmentManager();
        FragmentTransaction transaction =fm.beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,number;
        ImageView popup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.displayid);
            name=itemView.findViewById(R.id.displayname);
            number=itemView.findViewById(R.id.displaynumber);
            popup=itemView.findViewById(R.id.popup);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return true;
    }

//    public void popupmenu (View v){
//        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
//        popupMenu.setOnMenuItemClickListener(this);
//        popupMenu.inflate(R.menu.popup_menu);
//        popupMenu.show();
//    }


}
