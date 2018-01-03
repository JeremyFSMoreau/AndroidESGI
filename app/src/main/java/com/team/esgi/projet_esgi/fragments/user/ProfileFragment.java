package com.team.esgi.projet_esgi.fragments.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.data.remote.APIService;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.User.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ProfileFragment extends Fragment {

    private APIService mAPIService;
    Context mContext;

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
        mAPIService = ApiUtils.getAPIService();

        TextView mUsername = view.findViewById(R.id.username);
        TextView mIdentifier = view.findViewById(R.id.identifier);
        TextView mLanguage = view.findViewById(R.id.language);
        TextView mDisplay = view.findViewById(R.id.display);

        Gson gson = new Gson();
        String json = KeyValueDB.getUser(mContext);
        final User user = gson.fromJson(json,User.class);
        sendGet(user);

        Log.d(TAG,user.getUsername());
        Log.d(TAG,user.getUserkey());
        Log.d(TAG,user.getUserData().getLanguage());
        Log.d(TAG,user.getUserData().getFavoritesDisplaymode());

        mUsername.setText(user.getUsername());
        mIdentifier.setText(user.getUserkey());
        mLanguage.setText(user.getUserData().getLanguage());
        mDisplay.setText(user.getUserData().getFavoritesDisplaymode());


        return view;
    }

    public void sendGet(final User user) {
        mAPIService.show("Bearer " + user.getToken()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    user.setUserData(response.body().getUserData());
                    KeyValueDB.setUser(mContext,user);
                    Log.d("test",user.getUserData().getLanguage());
                }
                else
                    Log.d("arf","c'est raté");
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }



}
