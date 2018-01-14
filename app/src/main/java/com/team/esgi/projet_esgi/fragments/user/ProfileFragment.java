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
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.UserServices.UserServices;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.User.User;

import static android.content.ContentValues.TAG;

public class ProfileFragment extends Fragment {

    private UserServices mAPIService;
    Context mContext;
    User user;

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
        }


        return view;
    }





}
