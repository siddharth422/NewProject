package com.ecaresoftech.newproject.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.ecaresoftech.newproject.MainActivity;
import com.ecaresoftech.newproject.R;
import com.ecaresoftech.newproject.poja.LiftResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiftAdapter extends RecyclerView.Adapter<LiftAdapter.MyHolder>  {
    String url;
    ArrayList<LiftResponse> arrayList;
    Context context;
    AlertDialog.Builder alertDialog;
    Dialog dialog;
    private Context mCtx;
    private LiftAdapter.OnItemClickListener mListener;
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(LiftAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    public LiftAdapter(ArrayList<LiftResponse> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    public void setList(ArrayList<LiftResponse> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }
    @Override
    public LiftAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card,parent,false);
        context=view.getContext();
        return new LiftAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final LiftAdapter.MyHolder holder, final int position) {
            final LiftResponse usersItem = arrayList.get(position);

        if (position%2==0)
        {
            holder.cardView.setCardBackgroundColor(Color.WHITE);
            holder.name.setTextColor(Color.BLACK);
            holder.date.setTextColor(Color.BLACK);
            holder.address.setTextColor(Color.BLACK);
            holder.addresstext.setTextColor(Color.BLACK);
            holder.datetext.setTextColor(Color.BLACK);
            holder.nametext.setTextColor(Color.BLACK);
        }
            holder.name.setText(usersItem.getTitle());
            holder.date.setText(usersItem.getDate_of_order());
            holder.address.setText(usersItem.getArea());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //creating a popup menu
                    PopupMenu popup = new PopupMenu(context, holder.itemView);
                    //inflating menu from xml resource
                    popup.inflate(R.menu.options_menu);
                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.amcadd:



                                    break;
                                case R.id.serviceadd:


                                    //handle menu2 click
                                    break;
                            }
                            return false;
                        }
                    });
                    //displaying the popup
                    popup.show();

                    return false;
                }
            });
    }


    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name,date,address,nametext,datetext,addresstext;
        CardView cardView;
        public MyHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.datetime);
            address=itemView.findViewById(R.id.addresstext);
            cardView=itemView.findViewById(R.id.card);
            nametext=itemView.findViewById(R.id.sitename);
            datetext=itemView.findViewById(R.id.date);
            addresstext=itemView.findViewById(R.id.address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


}

