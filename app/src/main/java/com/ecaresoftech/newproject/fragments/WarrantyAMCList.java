package com.ecaresoftech.newproject.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ecaresoftech.newproject.R;
import com.ecaresoftech.newproject.adapters.WarrantyAmcListAdapter;
import com.ecaresoftech.newproject.interfaces.Response_Api;
import com.ecaresoftech.newproject.poja.LiftResponse;
import com.ecaresoftech.newproject.poja.WarrantyAmcPojo;
import com.ecaresoftech.newproject.services.Api_Client2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
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
 * Use the {@link WarrantyAMCList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarrantyAMCList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    SwipeRefreshLayout swipe;
    Context context;
    WarrantyAmcListAdapter adapter;
    Unbinder unbinder;
    LinearLayoutManager linearLayoutManager;
    ArrayList<WarrantyAmcPojo> arrayList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WarrantyAMCList() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WarrantyServiceList.
     */
    // TODO: Rename and change types and number of parameters
    public static WarrantyAMCList newInstance(String param1, String param2) {
        WarrantyAMCList fragment = new WarrantyAMCList();
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
        View view = inflater.inflate(R.layout.fragment_warranty_service_list, container, false);
        context = view.getContext();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void LoadData() {
        final ProgressDialog progress;
        progress = new ProgressDialog(context);
        progress.setTitle("Please Wait!!");
        progress.setMessage("Wait!!");
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login", MODE_PRIVATE);
        String token = sharedPreferences.getString("crftoken", "");
        // String Copkie=sharedPreferences.getString("sesstion_name","")+"="+sharedPreferences.getString("session_id","");
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", getString(R.string.Content_Type));

        SharedPreferences sp = context.getSharedPreferences("Auth", MODE_PRIVATE);
        Response_Api response_api = Api_Client2.createService(Response_Api.class, sp.getString("username", ""), sp.getString("password", ""));
        Call<ResponseBody> call = response_api.WarrantAmc("json", map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

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

                    JSONObject resobj = null;
                    try {
                        resobj = new JSONObject(responseBodyString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Iterator<?> keys = resobj.keys();
                    while (keys.hasNext()) {
                        try {
                            WarrantyAmcPojo warrantyAmcPojo = new WarrantyAmcPojo();
                            String key = (String) keys.next();
                            JSONObject jsonObject = resobj.getJSONObject(key);
                            String value = jsonObject.getString("nid");
                            String title = jsonObject.getString("title");
                            String Type = jsonObject.getString("warranty_amc");
                            String amc_expire = jsonObject.getString("amc_expired");
                            String warranty_expire = jsonObject.getString("warranty_expired");
                            String receiedpayment = jsonObject.getString("receive_payment");
                            String dupayment = jsonObject.getString("due_payment");
                            String paymentdate = jsonObject.getString("payment_date");
                             warrantyAmcPojo.setAmc_expired(amc_expire);
                            warrantyAmcPojo.setTitle(title);
                            warrantyAmcPojo.setReceive_payment(receiedpayment);
                            warrantyAmcPojo.setDue_payment(dupayment);
                            warrantyAmcPojo.setWarranty_expired(warranty_expire);
                            warrantyAmcPojo.setWarranty_amc(Type);
                            warrantyAmcPojo.setPayment_date(paymentdate);
                            warrantyAmcPojo.setNid(value);
                            arrayList.add(warrantyAmcPojo);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progress.cancel();

                    }

                    adapter.setList(arrayList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(context, response.errorBody().source().buffer().clone().readString(Charset.forName("UTF-8")).toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progress.cancel();
                Log.d("TAG", "Response = " + t.toString());
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new WarrantyAmcListAdapter(arrayList, context);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LoadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}