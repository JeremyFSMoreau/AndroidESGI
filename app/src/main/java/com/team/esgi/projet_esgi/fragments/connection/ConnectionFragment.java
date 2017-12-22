package com.team.esgi.projet_esgi.fragments.connection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.team.esgi.projet_esgi.MainActivity;
import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.managers.EpisodesManager;
import com.team.esgi.projet_esgi.models.Episode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

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

    @Override
    public void onResume() {
        super.onResume();

        Realm realm = Realm.getDefaultInstance(); // opens "myrealm.realm"
        try {

// In a DynamicRealm all objects are DynamicRealmObjects
            /*realm.beginTransaction();
            Episode person = realm.createObject(Episode.class);
            person.setId(1);
            person.setName("John");
            realm.commitTransaction();*/

// Queries still work normally
            RealmResults<Episode> persons = realm.where(Episode.class)
                    .equalTo("name", "John")
                    .findAll();

            System.out.println("persons.size() :" +persons.size());
        } finally {
            realm.close();
        }
    }
}
