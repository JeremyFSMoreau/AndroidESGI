package com.team.esgi.projet_esgi.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.MainActivity;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.adapters.SeriesAdapter;
import com.team.esgi.projet_esgi.adapters.SeriesDataAdapter;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.ShowServices.ShowServices;
import com.team.esgi.projet_esgi.fragments.connection.ConnectionFragment;
import com.team.esgi.projet_esgi.fragments.series.SerieFragment;
import com.team.esgi.projet_esgi.managers.SeriesManager;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.Series.LastUpdated;
import com.team.esgi.projet_esgi.models.Series.Serie;
import com.team.esgi.projet_esgi.models.Series.SerieLastUpdated;
import com.team.esgi.projet_esgi.models.User.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainMenuFragment extends Fragment {
    private ShowServices mAPIService;
    Context mContext;
    User user;
    @BindView(R.id.recentlyUpdated)
    ListView recentlyUpdated;
    ArrayList<Serie> initListUpdated;
    private SeriesDataAdapter mAdapterUpdated;

    public MainMenuFragment() {

    }

    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=this.getActivity();
        View view = inflater.inflate(R.layout.activity_main, container, false);
        mAPIService = ApiUtils.getShowService();
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
        recentlyUpdated = view.findViewById(R.id.recentlyUpdated);
        initListUpdated = new ArrayList<Serie>();
        mAdapterUpdated = new SeriesDataAdapter(initListUpdated,mContext);
        recentlyUpdated.setAdapter(mAdapterUpdated);
        sendGet(user);

        recentlyUpdated.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Serie serie = (Serie)parent.getItemAtPosition(position);
                serie.setId(serie.getDataSerie().getId());
                KeyValueDB.setSerie(mContext,serie);
                ((MainActivity)getActivity()).pushFragment(SerieFragment.newInstance());
            }
        });

        return view;
    }

    public void sendGet(final User user) {
        Long fromTime = (System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)) / 1000;
        String fromTimeString = fromTime.toString();

        Log.d("from",fromTimeString);
        mAPIService.updatedSeries(fromTimeString,"Bearer " + user.getToken()).enqueue(new Callback<LastUpdated>() {
            @Override
            public void onResponse(Call<LastUpdated> call, Response<LastUpdated> response) {
                if(response.isSuccessful()) {
                    Log.d("ah",response.body().getSerieLastUpdated().toString());
                    Integer limitLoop = 0;
                    for(SerieLastUpdated serie : response.body().getSerieLastUpdated()){
                        if(limitLoop <20){
                            getSerieById(serie.getId().toString(),user);
                            limitLoop++;
                        }
                    }
                }
                else
                {
                    Log.d("arf","c'est raté");
                    ((MainActivity)mContext).pushFragment(ConnectionFragment.newInstance());
                }
            }
            @Override
            public void onFailure(Call<LastUpdated> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                Log.d(TAG,t.toString());
                ((MainActivity)mContext).pushFragment(ConnectionFragment.newInstance());
            }
        });
    }

    public void getSerieById(String idSerie, final User user){
        mAPIService.serieDetails(idSerie,"Bearer " + user.getToken()).enqueue(new Callback<Serie>() {
            @Override
            public void onResponse(Call<Serie> call, Response<Serie> response) {
                if(response.isSuccessful()) {
                    if(response.body().getDataSerie().getSeriesName() != null)
                        mAdapterUpdated.add(response.body());
                }
                else
                    Log.d("arf","c'est raté");
            }
            @Override
            public void onFailure(Call<Serie> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
            }
        });
    }
}
