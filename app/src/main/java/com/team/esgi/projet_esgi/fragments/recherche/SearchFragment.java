package com.team.esgi.projet_esgi.fragments.recherche;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.data.remote.APIService;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.SearchResult;
import com.team.esgi.projet_esgi.models.Series.Serie;
import com.team.esgi.projet_esgi.models.User.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SearchFragment extends Fragment {

    private APIService mAPIService;
    private ListView listeSeries;
    private ArrayAdapter mAdapter;
    private List<String> initList;
    Context mContext;

    public SearchFragment() {

    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=this.getActivity();



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Button validateButton = view.findViewById(R.id.validateButton);


        final EditText mSearchBar = view.findViewById(R.id.searchBar);
        mAPIService = ApiUtils.getAPIService();
        listeSeries = view.findViewById(R.id.list_series);
        Gson gson = new Gson();
        String json = KeyValueDB.getUser(mContext);
        final User user = gson.fromJson(json,User.class);

        initList = new ArrayList<String>();
        mAdapter = new ArrayAdapter(mContext,android.R.layout.simple_list_item_1,initList);
        listeSeries.setAdapter(mAdapter);

        validateButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String searchValue = mSearchBar.getText().toString();
                  mAdapter.clear();
                  Log.d("TAG",searchValue);
                  sendGet(user, searchValue);
              }
          }
        );


        return view;
    }

    public void sendGet(final User user,String searchValue) {

        mAPIService.list(searchValue,"Bearer " + user.getToken()).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if(response.isSuccessful()) {
                    for(Serie serie : response.body().getData()){
                        fillListView(serie);
                        for(String alias : serie.getAliases())
                            Log.d("Alias :", "Rep : " + alias);
                        Log.d("test",serie.getSeriesName());
                    }
                }
                else
                    Log.d("arf","c'est raté");
            }
            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Log.d(TAG,t.toString());
            }
        });
    }

    public void fillListView(Serie serie){
        mAdapter.add(serie.getSeriesName());
    }


}
