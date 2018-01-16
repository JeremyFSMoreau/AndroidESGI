package com.team.esgi.projet_esgi.fragments.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.UserServices.UserServices;
import com.team.esgi.projet_esgi.models.Episodes.Episode;
import com.team.esgi.projet_esgi.models.Episodes.EpisodesList;
import com.team.esgi.projet_esgi.models.KeyValueDB;
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
    ArrayList<String> initListFavorites;
    ArrayAdapter<String> mAdapterFavorites;

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

        TextView mUsername = view.findViewById(R.id.username);
        TextView mIdentifier = view.findViewById(R.id.identifier);
        TextView mLanguage = view.findViewById(R.id.language);
        TextView mDisplay = view.findViewById(R.id.display);

        Gson gson = new Gson();
        String json = KeyValueDB.getUser(mContext);
        user = gson.fromJson(json,User.class);

        favoriteslist = view.findViewById(R.id.favoritesList);
        initListFavorites = new ArrayList<String>();

        mAdapterFavorites = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,initListFavorites);
        favoriteslist.setAdapter(mAdapterFavorites);

        Log.d(TAG,user.getUsername());
        Log.d(TAG,user.getUserkey());
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


        return view;
    }

    public void fillFavorites()
    {
        mAPIService.favoritesList("Bearer " + user.getToken()).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if(response.isSuccessful()) {
                    Favorite favorite = new Favorite();
                    favorite = response.body().getFavorite();
                    Log.d("Tag", response.body().toString());
                    for(Integer i = 0; i<favorite.getFavorites().size();i++)
                    {
                        fillFavoritesListView(favorite.getFavorites().get(i));
                        Log.d("test", "aaaa");
                    }
                }
                else
                    Log.d("arf","c'est raté");
            }
            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void fillFavoritesListView(String favorite)
    {
        mAdapterFavorites.add(favorite);
    }




}
