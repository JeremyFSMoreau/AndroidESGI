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
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.UserServices.UserServices;
import com.team.esgi.projet_esgi.managers.UserManager;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.User.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ConnectionFragment extends Fragment {

    private UserServices userService;
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

        userService = ApiUtils.getUserService();
        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = login.getText().toString();
                String userkey = identifier.getText().toString();
                String apikey = "E73267E0132B869C";

//                Faire un truc dans le genre pour simplifier les appels aux méthodes sur le user (connexion, deco, récupérer son état)

                UserManager.getInstance().connexion(getContext(),apikey, userkey, username);

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }






}
