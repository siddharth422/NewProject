package com.ecaresoftech.newproject.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecaresoftech.newproject.R;
import com.ecaresoftech.newproject.poja.WarrantyAmcPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.MyHolder>  {
    String url;
    ArrayList<WarrantyAmcPojo> list;
    List<WarrantyAmcPojo> arraylist = new ArrayList<>();
    Context context;
    AlertDialog.Builder alertDialog;
    Dialog dialog;
    private Context mCtx;
    private ServiceListAdapter.OnItemClickListener mListener;
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(ServiceListAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    public ServiceListAdapter(ArrayList<WarrantyAmcPojo> arrayList, Context context) {
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
    public ServiceListAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_card,parent,false);
        context=view.getContext();
        return new ServiceListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final ServiceListAdapter.MyHolder holder, final int position) {
        final WarrantyAmcPojo usersItem = list.get(position);

        holder.sitename.setText(usersItem.getTitle());
        holder.type.setText(String.valueOf(usersItem.getField_service_mode()
        ));
       // holder.servicepayment.setText(usersItem.get());
        holder.paymentdate.setText(usersItem.getPayment_date());
        holder.lastservicedate.setText(String.valueOf(usersItem.getLast_service_date()));

    }




    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView sitename,type,servicemode,servicepayment,paymentdate,lastservicedate;
        CardView cardView;
        public MyHolder(View itemView) {
            super(itemView);
            sitename=itemView.findViewById(R.id.sitename);
            type=itemView.findViewById(R.id.type);
            servicepayment=itemView.findViewById(R.id.servicepayment);
            lastservicedate=itemView.findViewById(R.id.lastservicedate);
            paymentdate=itemView.findViewById(R.id.paymentdate);

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

