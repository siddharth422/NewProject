package com.ecaresoftech.newproject.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ecaresoftech.newproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddWarranty#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddWarranty extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radioGroupamc)
    RadioGroup radioGroupamc;
    @BindView(R.id.start)
    TextInputEditText start;
    @BindView(R.id.end)
    TextInputEditText end;
    @BindView(R.id.cash)
    RadioButton cash;
    @BindView(R.id.cheque)
    RadioButton cheque;
    @BindView(R.id.nift)
    RadioButton nift;
    @BindView(R.id.radioGroupservice)
    RadioGroup radioGroupservice;
    @BindView(R.id.bankname)
    TextInputEditText bankname;
    @BindView(R.id.referncenumber)
    TextInputEditText referncenumber;
    @BindView(R.id.totalpayment)
    TextInputEditText totalpayment;
    @BindView(R.id.receivedpayment)
    TextInputEditText receivedpayment;
    @BindView(R.id.paymentdate)
    TextInputEditText paymentdate;
    @BindView(R.id.save)
    Button save;
    String type, paymentmode;
     Calendar calendar1;
    DatePickerDialog dialog1;
    Context context;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddWarranty() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddWarranty.
     */
    // TODO: Rename and change types and number of parameters
    public static AddWarranty newInstance(String param1, String param2) {
        AddWarranty fragment = new AddWarranty();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.dialog_layout, container, false);
        context=view.getContext();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.radio1, R.id.radio2, R.id.cash, R.id.cheque, R.id.nift, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio1:
                start.setHint("Warranty start");
                end.setHint("Warranty expire");
                type = "warranty";
                break;
            case R.id.radio2:
                start.setHint("AMC start");
                end.setHint("AMC expire");
                type = "AMC";
                break;
            case R.id.cash:
                bankname.setVisibility(View.GONE);
                referncenumber.setVisibility(View.GONE);
                paymentmode = "cash";
                break;
            case R.id.cheque:
                bankname.setVisibility(View.VISIBLE);
                referncenumber.setVisibility(View.GONE);
                paymentmode = "cheque";
                break;
            case R.id.nift:
                bankname.setVisibility(View.VISIBLE);
                referncenumber.setVisibility(View.VISIBLE);
                paymentmode = "nift";
                break;
            case R.id.save:
                verifi();
                break;
        }
    }

    private void verifi() {
        String startstr, endstr, totalPaymentstr, bankNamestr, refrenceNumberstr, recivedpaymentstr, paymendatestr;

        startstr = start.getText().toString();
        endstr = end.getText().toString();
        totalPaymentstr = totalpayment.getText().toString();
        bankNamestr = bankname.getText().toString();
        refrenceNumberstr = referncenumber.getText().toString();
        recivedpaymentstr = receivedpayment.getText().toString();
        paymendatestr = paymentdate.getText().toString();

        if (startstr.equals(""))
        {
            start.setError("Select date");
            start.requestFocus();
        }else if (endstr.equals(""))
        {
            end.setError("Select date");
            end.requestFocus();
        }else if (paymendatestr.equals(""))
        {
            paymentdate.setError("Select date");
            paymentdate.requestFocus();
        }else if (totalPaymentstr.equals(""))
        {
            totalpayment.setError("Enter value");
            totalpayment.requestFocus();
        }else if (recivedpaymentstr.equals(""))
        {

            receivedpayment.setError("Enter value");
            receivedpayment.requestFocus();
        }else if (!type.equals("cash"))
        {
            if (bankNamestr.equals(""))
            {
                bankname.setError("Enter value");
                bankname.requestFocus();
            }
        }else {
            Save(paymendatestr,
                    recivedpaymentstr,
                    refrenceNumberstr,
                    bankNamestr,
                    totalPaymentstr,
                    endstr,
                    startstr);
        }


    }

    private void Save(String paymendatestr, String recivedpaymentstr, String refrenceNumberstr, String bankNamestr, String totalPaymentstr, String endstr, String startstr) {
    }


    @OnClick({R.id.start, R.id.end, R.id.paymentdate})
    public void onView_Clicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                  calendar1 = Calendar.getInstance();
                       dialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day_of_month) {
                        calendar1.set(Calendar.YEAR, year);
                        calendar1.set(Calendar.MONTH, (month));
                        calendar1.set(Calendar.DAY_OF_MONTH, day_of_month);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String myFormat = "YYYY-MM-DD";
                        // SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                        start.setText(sdf.format(calendar1.getTime()));
                    }
                }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
                dialog1.show();
                break;

            case R.id.end:
                calendar1 = Calendar.getInstance();
                dialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day_of_month) {
                        calendar1.set(Calendar.YEAR, year);
                        calendar1.set(Calendar.MONTH, (month));
                        calendar1.set(Calendar.DAY_OF_MONTH, day_of_month);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String myFormat = "YYYY-MM-DD";
                        // SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                        end.setText(sdf.format(calendar1.getTime()));
                    }
                }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
                dialog1.show();
                break;
            case R.id.paymentdate:
                calendar1 = Calendar.getInstance();
                dialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day_of_month) {
                        calendar1.set(Calendar.YEAR, year);
                        calendar1.set(Calendar.MONTH, (month));
                        calendar1.set(Calendar.DAY_OF_MONTH, day_of_month);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String myFormat = "YYYY-MM-DD";
                        // SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                        paymentdate.setText(sdf.format(calendar1.getTime()));
                    }
                }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
                dialog1.show();
                break;
        }
    }
}