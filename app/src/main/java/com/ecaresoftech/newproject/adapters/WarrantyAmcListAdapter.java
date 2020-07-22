package com.ecaresoftech.newproject.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.ecaresoftech.newproject.DetailActivity;
import com.ecaresoftech.newproject.R;
import com.ecaresoftech.newproject.poja.LiftResponse;
import com.ecaresoftech.newproject.poja.WarrantyAmcPojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WarrantyAmcListAdapter extends RecyclerView.Adapter<WarrantyAmcListAdapter.MyHolder>  {
    String url;
    ArrayList<WarrantyAmcPojo> list;
    List<WarrantyAmcPojo> arraylist = new ArrayList<>();
    Context context;
    AlertDialog.Builder alertDialog;
    Dialog dialog;
    private Context mCtx;
    private WarrantyAmcListAdapter.OnItemClickListener mListener;
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(WarrantyAmcListAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    public WarrantyAmcListAdapter(ArrayList<WarrantyAmcPojo> arrayList, Context context) {
        this.list = arrayList;
        this.context = context;
        this.arraylist.addAll(arrayList);
    }
    public void setList(ArrayList<WarrantyAmcPojo> list) {
        this.list = list;
        this.arraylist.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public WarrantyAmcListAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.warranty_amc_card,parent,false);
        context=view.getContext();
        return new WarrantyAmcListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final WarrantyAmcListAdapter.MyHolder holder, final int position) {
        final WarrantyAmcPojo usersItem = list.get(position);

   holder.sitename.setText(usersItem.getTitle());
   if (usersItem.getWarranty_amc().equals("warranty"))
   {
       holder.text.setText("Warranty expire :");
       holder.amcexpire.setText(usersItem.getWarranty_expired());

   }else {
       holder.amcexpire.setText(usersItem.getAmc_expired());
   }
   holder.type.setText(usersItem.getWarranty_amc().toUpperCase());
   holder.receivedpayment.setText(usersItem.getReceive_payment());
   holder.paymentdate.setText(usersItem.getPayment_date());

    }




    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView sitename,type,amcexpire,receivedpayment,paymentdate,text;
        CardView cardView;
        public MyHolder(View itemView) {
            super(itemView);
            sitename=itemView.findViewById(R.id.sitename);
            type=itemView.findViewById(R.id.type);
            amcexpire=itemView.findViewById(R.id.amcexpire);
            receivedpayment=itemView.findViewById(R.id.receivedpayment);
            paymentdate=itemView.findViewById(R.id.paymentdate);
            text=itemView.findViewById(R.id.source);

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
    public void filter(String charText) {
        try {
            charText = charText.toLowerCase(Locale.getDefault());
            list.clear();
            if (charText.length() == 0) {
                list.addAll(arraylist);
            } else {
                for (WarrantyAmcPojo wp : arraylist) {
                    if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                        list.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}

