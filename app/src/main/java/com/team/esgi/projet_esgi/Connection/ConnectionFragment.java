package com.team.esgi.projet_esgi.Connection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.team.esgi.projet_esgi.MainActivity;
import com.team.esgi.projet_esgi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnectionFragment extends Fragment {

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connection, container, false);

        ButterKnife.bind(this, view);

        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).pushFragment(OtherFragment.newInstance());
            }
        });

        return view;
    }
}
