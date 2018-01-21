package com.team.esgi.projet_esgi.fragments.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.MainActivity;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.adapters.SeriesDataAdapter;
import com.team.esgi.projet_esgi.data.remote.APIService;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.ShowServices.ShowServices;
import com.team.esgi.projet_esgi.data.remote.UserServices.UserServices;
import com.team.esgi.projet_esgi.fragments.connection.ConnectionFragment;
import com.team.esgi.projet_esgi.fragments.series.SerieFragment;
import com.team.esgi.projet_esgi.managers.SeriesManager;
import com.team.esgi.projet_esgi.managers.UserManager;
import com.team.esgi.projet_esgi.models.Episodes.Episode;
import com.team.esgi.projet_esgi.models.Episodes.EpisodesList;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.Series.DataSerie;
import com.team.esgi.projet_esgi.models.Series.Serie;
import com.team.esgi.projet_esgi.models.User.Favorite;
import com.team.esgi.projet_esgi.models.User.User;
import com.team.esgi.projet_esgi.models.User.UserData;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ProfileFragment extends Fragment {

    private UserServices mAPIService;
    Context mContext;
    User user;
    @BindView(R.id.favoritesList) ListView favoriteslist;
    @BindView(R.id.username) TextView mUsername;
    @BindView(R.id.identifier) TextView mIdentifier;
    @BindView(R.id.language) TextView mLanguage;
    @BindView(R.id.display) TextView mDisplay;
    ArrayList<Serie> initListFavorites;
    SeriesDataAdapter mAdapterFavorites;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=this.getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mAPIService = ApiUtils.getUserService();

        mUsername = view.findViewById(R.id.username);
        mIdentifier = view.findViewById(R.id.identifier);
        mLanguage = view.findViewById(R.id.language);
        mDisplay = view.findViewById(R.id.display);
        initProfile(view);
        /*try {
            initProfile(view);
        }
        catch (Exception e){
            ((MainActivity)mContext).pushFragment(ConnectionFragment.newInstance());
        }*/
        return view;
    }

    public void initProfile(View view)
    {
        Gson gson = new Gson();
        String json = "";
        json = KeyValueDB.getUser(mContext);
        user = gson.fromJson(json, User.class);
        Log.d("test",user.toString());

        favoriteslist = view.findViewById(R.id.favoritesList);
        initListFavorites = new ArrayList<Serie>();
        mAdapterFavorites = new SeriesDataAdapter(initListFavorites,mContext);
        favoriteslist.setAdapter(mAdapterFavorites);
        if(user.getUserData() != null){
            Log.d(TAG,user.getUserData().getLanguage());
            Log.d(TAG,user.getUserData().getFavoritesDisplaymode());
        }

        mUsername.setText(user.getUsername());
        mIdentifier.setText(user.getUserkey());
        if(user.getUserData() != null){
            mLanguage.setText(user.getUserData().getLanguage());
            mDisplay.setText(user.getUserData().getFavoritesDisplaymode());
            fillFavorites();
        }

        favoriteslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Serie serie = (Serie)parent.getItemAtPosition(position);
                serie.setId(serie.getDataSerie().getId());
                KeyValueDB.setSerie(mContext,serie);
                ((MainActivity)getActivity()).pushFragment(SerieFragment.newInstance());
            }
        });
    }

    public void fillFavorites()
    {
        for(String favorite : user.getUserData().getFavorite().getFavorites())
        {
            getSerieById(favorite,user);
        }
    }

    public void getSerieById(String idSerie, final User user){
        ShowServices showServices = ApiUtils.getShowService();
        showServices.serieDetails(idSerie,"Bearer " + user.getToken()).enqueue(new Callback<Serie>() {
            @Override
            public void onResponse(Call<Serie> call, Response<Serie> response) {
                if(response.isSuccessful()) {
                    mAdapterFavorites.add(response.body());
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
