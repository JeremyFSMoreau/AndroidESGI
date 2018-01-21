package com.team.esgi.projet_esgi.fragments.series;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.MainActivity;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.adapters.SeriesAdapter;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.ShowServices.ShowServices;
import com.team.esgi.projet_esgi.fragments.connection.ConnectionFragment;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.Series.SearchResult;
import com.team.esgi.projet_esgi.models.Series.Serie;
import com.team.esgi.projet_esgi.models.User.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SearchFragment extends Fragment {

    private ShowServices mAPIService;
    private ListView listeSeries;
    private SeriesAdapter mAdapter;
    private ArrayList<Serie> initList;
    Context mContext;
    @BindView(R.id.validateButton)
    public Button validateButton;
    @BindView(R.id.loader)
    public RelativeLayout loader;

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

        ButterKnife.bind(this, view);

        validateButton = view.findViewById(R.id.validateButton);


        final EditText mSearchBar = view.findViewById(R.id.searchBar);
        mAPIService = ApiUtils.getShowService();
        listeSeries = view.findViewById(R.id.list_series);
        Gson gson = new Gson();
        String json = "";
        User user = new User();
        try {
            json = KeyValueDB.getUser(mContext);
            user = gson.fromJson(json, User.class);
        }
        catch (Exception e){
            ((MainActivity)mContext).pushFragment(ConnectionFragment.newInstance());
        }

        final User connectedUser = user;
        initList = new ArrayList<Serie>();
        mAdapter = new SeriesAdapter(initList,mContext);
        listeSeries.setAdapter(mAdapter);

        validateButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String searchValue = mSearchBar.getText().toString();
                  mAdapter.clear();
                  Log.d("TAG",searchValue);
                  sendGet(connectedUser, searchValue);
              }
          }
        );

        listeSeries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Serie serie = (Serie) parent.getItemAtPosition(position);
                KeyValueDB.setSerie(mContext,serie);
                ((MainActivity)getActivity()).pushFragment(SerieFragment.newInstance());
            }
        });

        return view;
    }

    public void sendGet(final User user,String searchValue) {
        showLoader();

        mAPIService.list(searchValue,"Bearer " + user.getToken()).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                hideLoader();
                if(response.isSuccessful()) {
                    for(Serie serie : response.body().getData()){
                        fillListView(serie);
                        for(String alias : serie.getAliases())
                            Log.d("Alias :", "Rep : " + alias);
                        Log.d("test",serie.getSeriesName());
                    }
                }
                else
                {
                    Log.d("arf","c'est raté");
                    ((MainActivity)mContext).pushFragment(ConnectionFragment.newInstance());
                }
            }
            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Log.d(TAG,t.toString());
                ((MainActivity)mContext).pushFragment(ConnectionFragment.newInstance());
            }
        });
    }

    private void showLoader() {
        validateButton.setClickable(true);
        loader.setVisibility(View.VISIBLE);

    }

    private void  hideLoader() {
        validateButton.setClickable(false);
        loader.setVisibility(View.GONE);
    }

    public void fillListView(Serie serie){
        mAdapter.add(serie);
    }


}
