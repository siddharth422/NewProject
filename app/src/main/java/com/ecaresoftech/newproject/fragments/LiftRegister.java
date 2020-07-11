package com.ecaresoftech.newproject.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.ecaresoftech.newproject.DetailActivity;
import com.ecaresoftech.newproject.R;
import com.ecaresoftech.newproject.interfaces.Response_Api;
import com.ecaresoftech.newproject.poja.Statemodel;
import com.ecaresoftech.newproject.services.Api_Client2;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

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
    String state_string, citystring;
    ArrayList<String> listState = new ArrayList<String>();
    ArrayList<Statemodel> allstatecitylist = new ArrayList<>();
    // for listing all cities
    ArrayList<String> listCity = new ArrayList<String>();
    @BindView(R.id.sitename)
    TextInputEditText sitename;
    @BindView(R.id.address)
    TextInputEditText address;
    @BindView(R.id.india_states)
    Spinner indiaStates;
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
    @BindView(R.id.city)
    TextInputEditText city;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<String> key = new ArrayList<>();
    String keystate, statetemp;
    Context context;

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
        DetailActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        context = view.getContext();
       // callAll();
        return view;
    }

    public void callAll() {
        obj_list();
      //  setSpinner();
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
        validation();
    }

    private void validation() {
        String siteName, addressstr, areastr, contactpersonstr, contactnumberstr, liftNostr, dateoforderstr, dateofcompetionstr,
                liftcapacitystr, doorsizestr, lifttypestr, geartypestr, fieldconsstr, liftversionstr, totalamountstr;
        siteName = sitename.getText().toString();
        addressstr = address.getText().toString();
        areastr = area.getText().toString();
        contactpersonstr = contactperson.getText().toString();
        contactnumberstr = contactnumber.getText().toString();
        liftNostr = liftnumber.getText().toString();
        dateoforderstr = dateoforder.getText().toString();
        dateofcompetionstr = dateofcompletion.getText().toString();
        liftcapacitystr = liftcapacity.getText().toString();
        doorsizestr = doorsize.getText().toString();
        lifttypestr = fieldLiftType.getSelectedItem().toString();
        geartypestr = fieldGearType.getSelectedItem().toString();
        fieldconsstr = fieldCons.getSelectedItem().toString();
        liftversionstr = fieldLift.getSelectedItem().toString();
        totalamountstr = totalpayment.getText().toString();
        citystring=city.getText().toString();

        state_string= indiaStates.getSelectedItem().toString();
          int i=indiaStates.getSelectedItemPosition();
           state_string =String.valueOf(key.get(i));


        if (siteName.equals("")) {
            sitename.setError("Enter Site Name");
            sitename.requestFocus();
        } else if (contactnumberstr.equals("")) {
            contactnumber.setError("Enter Number");
            contactnumber.requestFocus();

        } else {

            Save(totalamountstr,
                    liftversionstr,
                    fieldconsstr,
                    geartypestr,
                    lifttypestr,
                    doorsizestr,
                    liftcapacitystr,
                    dateofcompetionstr,
                    dateoforderstr,
                    liftNostr,
                    contactnumberstr,
                    contactpersonstr,
                    addressstr,
                    siteName, areastr, state_string, citystring);
        }


    }

    public JsonArray makertitle(String value) {
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("target_id", value);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject1);
        return jsonArray;
    }

    public JsonArray maker(String value) {
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("value", value);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject1);
        return jsonArray;
    }

    private void Save(String totalamountstr, String liftversionstr, String fieldconsstr, String geartypestr,
                      String lifttypestr, String doorsizestr, String liftcapacitystr,
                      String dateofcompetionstr, String dateoforderstr, String liftNostr,
                      String contactnumberstr, String contactpersonstr, String addressstr,
                      String siteName, String areastr, String state_string, String citystring) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login", MODE_PRIVATE);
        String token = sharedPreferences.getString("crftoken", "");
        final ProgressDialog progress;
        progress = new ProgressDialog(context);
        progress.setTitle("Please Wait!!");
        progress.setMessage("Wait!!");
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", makertitle("lift_registration"));
        jsonObject.add("title", maker(siteName));
        jsonObject.add("field_address", maker(addressstr));
        jsonObject.add("field_area", maker(areastr));
        jsonObject.add("field_district", maker(citystring.toLowerCase()));
        jsonObject.add("field_state", maker(state_string));
        jsonObject.add("field_contact_person", maker(contactpersonstr));
        jsonObject.add("field_contact_cell_no_", maker(contactnumberstr));
        jsonObject.add("field_lift_no", maker(liftNostr));
        jsonObject.add("field_date_of_order", maker(dateoforderstr));
        jsonObject.add("field_date_of_completion", maker(dateofcompetionstr));
        jsonObject.add("field_lift_capacity", maker(liftcapacitystr));
        jsonObject.add("field_door_size", maker(doorsizestr));
        jsonObject.add("field_lift_type", maker(lifttypestr.toLowerCase()));
        jsonObject.add("field_gear_type", maker(geartypestr.toLowerCase()));
        if (fieldconsstr.equals("Mild Steel")) {
            jsonObject.add("field_cons", maker("mild_steel"));
        } else {
            jsonObject.add("field_cons", maker("stainless_steel"));
        }

        jsonObject.add("field_lift", maker(liftversionstr.toLowerCase()));
        jsonObject.add("field_total_payment", maker(totalamountstr));
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type", getString(R.string.Content_Type));
        map.put("X-CSRF-Token", token);
        SharedPreferences sp = context.getSharedPreferences("Auth", MODE_PRIVATE);
        Response_Api response_api = Api_Client2.createService(Response_Api.class, sp.getString("username", ""), sp.getString("password", ""));
        Call<ResponseBody> call = response_api.CreateUser("json", map, jsonObject);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject jsonObject1 = null;
                try {

                    if (response.isSuccessful()) {
                        ResponseBody responseBody = response.body();
                        BufferedSource source = responseBody.source();
                        try {
                            source.request(Long.MAX_VALUE); // request the entire body.
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Buffer buffer = source.buffer();
                        // clone buffer before reading from it
                        String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));

                        jsonObject1 = new JSONObject(responseBodyString);
                        if (!jsonObject1.getString("nid").equals("")) {
                            progress.dismiss();
                            Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();
                            getActivity().onBackPressed();
                        } else {
                            progress.dismiss();

                            //Toast.makeText(context, response.body().source().buffer().clone().readString(Charset.forName("UTF-8")).toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, jsonObject1.getString("validation_error").toString(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        try {
                            progress.dismiss();
                            Toast.makeText(context, jsonObject1.getString("validation_error").toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, response.body().source().buffer().clone().readString(Charset.forName("UTF-8")).toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    try {
                        Toast.makeText(context, jsonObject1.getString("validation_error").toString(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    progress.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progress.dismiss();
                Log.d("TAG", "Response = " + t.toString());
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

//    private void setSpinner() {
//        HashSet<String> hashSet = new HashSet<String>();
//        hashSet.addAll(listState);
//        listState.clear();
//        listState.addAll(hashSet);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listState);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        indiaStates.setAdapter(adapter);
//
//        indiaStates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                listCity.clear();
//                state_string = parent.getItemAtPosition(position).toString();
//
//
//                for (int i = 0; i < allstatecitylist.size(); i++) {
//                    if (state_string.equals(allstatecitylist.get(i).getState())) {
//                        listCity.add(allstatecitylist.get(i).getName());
//                    }
//
//                    ArrayAdapter<String> adaptercity = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listCity);
//                    adaptercity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    city.setAdapter(adaptercity);
//                    city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            citystring = parent.getItemAtPosition(position).toString();
//
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//    }

    public String getJson() {
        String json = null;
        try {
            // Opening cities.json file
            InputStream is = context.getAssets().open("cities.json");
            // is there any content in the file
            int size = is.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            is.read(buffer);
            // close the stream --- very important
            is.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return json;
        }
        return json;
    }

    void obj_list() {
        // Exceptions are returned by JSONObject when the object cannot be created
        try {
            // Convert the string returned to a JSON object
            JSONObject jsonObject = new JSONObject(getJson());
            // Get Json array
            JSONArray array = jsonObject.getJSONArray("array");

            // Navigate through an array item one by one
            for (int i = 0; i < array.length(); i++) {
                Statemodel statemodel = new Statemodel();
                // select the particular JSON data
                JSONObject object = array.getJSONObject(i);
                String city = object.getString("name");
                String state = object.getString("state");
                // add to the lists in the specified format
                statemodel.setName(city);
                statemodel.setState(state);
                allstatecitylist.add(statemodel);
                listState.add(state);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.dateoforder, R.id.dateofcompletion})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.dateoforder:
                final Calendar calendar1 = Calendar.getInstance();
                DatePickerDialog dialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day_of_month) {
                        calendar1.set(Calendar.YEAR, year);
                        calendar1.set(Calendar.MONTH, (month));
                        calendar1.set(Calendar.DAY_OF_MONTH, day_of_month);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String myFormat = "YYYY-MM-DD";
                        // SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                        dateoforder.setText(sdf.format(calendar1.getTime()));
                    }
                }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
                dialog1.show();
                break;

            case R.id.dateofcompletion:
                final Calendar calendar2 = Calendar.getInstance();
                DatePickerDialog dialog2 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day_of_month) {
                        calendar2.set(Calendar.YEAR, year);
                        calendar2.set(Calendar.MONTH, (month));
                        calendar2.set(Calendar.DAY_OF_MONTH, day_of_month);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String myFormat = "YYYY-MM-DD";
                        // SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                        dateofcompletion.setText(sdf.format(calendar2.getTime()));
                    }
                }, calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH));
                dialog2.show();
                break;
        }
    }
}