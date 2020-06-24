package com.ecaresoftech.newproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.ecaresoftech.newproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LiftRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiftRegister extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.sitename)
    TextInputEditText sitename;
    @BindView(R.id.address)
    TextInputEditText address;
    @BindView(R.id.india_states)
    Spinner indiaStates;
    @BindView(R.id.city)
    Spinner city;
    @BindView(R.id.area)
    TextInputEditText area;
    @BindView(R.id.contactperson)
    TextInputEditText contactperson;
    @BindView(R.id.contactnumber)
    TextInputEditText contactnumber;
    @BindView(R.id.liftnumber)
    TextInputEditText liftnumber;
    @BindView(R.id.dateoforder)
    TextInputEditText dateoforder;
    @BindView(R.id.dateofcompletion)
    TextInputEditText dateofcompletion;
    @BindView(R.id.liftcapacity)
    TextInputEditText liftcapacity;
    @BindView(R.id.doorsize)
    TextInputEditText doorsize;
    @BindView(R.id.totalpayment)
    TextInputEditText totalpayment;
    @BindView(R.id.field_lift_type)
    Spinner fieldLiftType;
    @BindView(R.id.field_gear_type)
    Spinner fieldGearType;
    @BindView(R.id.field_cons)
    Spinner fieldCons;
    @BindView(R.id.field_lift)
    Spinner fieldLift;
    @BindView(R.id.save)
    Button save;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<String> key = new ArrayList<>();
    String keystate, statetemp;

    public LiftRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LiftRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static LiftRegister newInstance(String param1, String param2) {
        LiftRegister fragment = new LiftRegister();
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
        View view = inflater.inflate(R.layout.fragment_lift_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] keyname = {"AP", "AR", "AS", "BR", "CT", "GA", "GJ", "HR", "HP", "JK", "JH", "KA", "KL", "MP", "MH", "MN", "ML", "MZ", "NL", "OR", "PB", "RJ", "SK", "TN", "TG", "TR", "UT", "UP", "WB", "AN", "CH", "DN", "DD", "DL", "LD", "PY"};
        for (int i = 0; i < keyname.length; i++) {
            key.add(keyname[i]);
        }

//        spinner1= spinner.getSelectedItem().toString();
//        int i=spinner.getSelectedItemPosition();
//        keystate =String.valueOf(key.get(i));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
    }
}