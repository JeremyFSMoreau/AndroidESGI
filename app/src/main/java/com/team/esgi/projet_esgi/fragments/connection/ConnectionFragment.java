package com.team.esgi.projet_esgi.fragments.connection;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.team.esgi.projet_esgi.MainActivity;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.data.remote.APIService;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ConnectionFragment extends Fragment {

    private APIService mAPIService;
    private SharedPreferences sharedPreferences;
    private static String PREF_NAME = "prefs";
    Context mContext;

    public ConnectionFragment() {
        // Required empty public constructor
    }

    public static ConnectionFragment newInstance() {
        return new ConnectionFragment();
    }

    @BindView(R.id.button_connect)
    public Button connectionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = this.getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connection, container, false);

        ButterKnife.bind(this, view);

        final TextView login = view.findViewById(R.id.login);
        final TextView identifier = view.findViewById(R.id.accountIdentifier);

        mAPIService = ApiUtils.getAPIService();
        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = login.getText().toString();
                String userkey = identifier.getText().toString();
                String apikey = "E73267E0132B869C";
                sendPost(apikey, userkey, username);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void sendPost(String apikey, String userkey, String username) {
        final User user = new User(apikey,userkey,username);
        mAPIService.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    user.setToken(response.body().getToken());
                    Log.d("test",user.getToken());
                    KeyValueDB.setUsername(mContext,user.getToken());
                    ((MainActivity)getActivity()).pushFragment(OtherFragment.newInstance());
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