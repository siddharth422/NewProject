package com.ecaresoftech.newproject.fragments;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ecaresoftech.newproject.DetailActivity;
import com.ecaresoftech.newproject.R;
import com.ecaresoftech.newproject.actiitys.Login;
import com.ecaresoftech.newproject.adapters.LiftAdapter;
import com.ecaresoftech.newproject.interfaces.Response_Api;
import com.ecaresoftech.newproject.poja.LiftResponse;
import com.ecaresoftech.newproject.services.Api_Client;
import com.ecaresoftech.newproject.services.Api_Client2;
import com.google.gson.JsonObject;

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
 * Use the {@link ListLifts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListLifts extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    LiftAdapter adapter;
    Unbinder unbinder;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    Context context;
    LinearLayoutManager linearLayoutManager;
    ArrayList<LiftResponse> arrayList = new ArrayList<>();
    String userid;
    public static int Count = 0;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListLifts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListLifts.
     */
    // TODO: Rename and change types and number of parameters
    public static ListLifts newInstance(String param1, String param2) {
        ListLifts fragment = new ListLifts();
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
        View view = inflater.inflate(R.layout.fragment_list_lifts, container, false);
        unbinder = ButterKnife.bind(this, view);
        DetailActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        DetailActivity.toolbar.setTitle("Explore All Lift List");
        context = view.getContext();
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new LiftAdapter(arrayList, context);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LoadData(context);
    }

    private void LoadData(final Context context) {

        final ProgressDialog progress;
        progress = new ProgressDialog(context);
        progress.setTitle("Please Wait!!");
        progress.setMessage("Wait!!");
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        SharedPreferences sharedPreferences=context.getSharedPreferences("Login",MODE_PRIVATE);
        String token =sharedPreferences.getString("crftoken","");
        // String Copkie=sharedPreferences.getString("sesstion_name","")+"="+sharedPreferences.getString("session_id","");
        Map<String,String> map=new HashMap<>();
        map.put("Content-Type",getString(R.string.Content_Type));

        SharedPreferences sp = context.getSharedPreferences("Auth", MODE_PRIVATE);
        Response_Api response_api = Api_Client2.createService(Response_Api.class, sp.getString("username", ""), sp.getString("password", ""));
        Call<ResponseBody> call=response_api.Listlift("json",map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful())
                {
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
                    while(keys.hasNext() ) {
                        try {
                            LiftResponse liftResponse=new LiftResponse();
                            String key = (String)keys.next();
                            JSONObject jsonObject=resobj.getJSONObject(key);
                            String value=jsonObject.getString("nid");
                            String title=jsonObject.getString("title");
                            String date=jsonObject.getString("order_date");
                            String area=jsonObject.getString("area");
                            liftResponse.setDate_of_order(date);
                            liftResponse.setTitle(title);
                            liftResponse.setArea(area);
                            liftResponse.setNid(value);
                            arrayList.add(liftResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progress.cancel();
                        adapter.setList(arrayList);
                        recyclerView.setAdapter(adapter);
                    }


                }else {
                    Toast.makeText(context,response.errorBody().source().buffer().clone().readString(Charset.forName("UTF-8")).toString(), Toast.LENGTH_SHORT).show();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.filer_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.filter);
        menuItem.setVisible(false);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    String text = newText;
                    adapter.filter(text);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return false;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
               // rackMonthPicker.show();
                break;
        }
        return true;

    }

}