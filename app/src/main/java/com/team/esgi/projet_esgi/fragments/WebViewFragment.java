package com.team.esgi.projet_esgi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.team.esgi.projet_esgi.R;
import com.team.esgi.projet_esgi.models.KeyValueDB;

public class WebViewFragment extends Fragment {
    Context mContext;
    WebView webView;
    public WebViewFragment() {

    }

    public static WebViewFragment newInstance() {
        return new WebViewFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=this.getActivity();
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        webView = view.findViewById(R.id.webView);
        webView.loadUrl("http://www.imdb.com/find?s=nm&q=" + KeyValueDB.getActor(mContext));
        return view;
    }
}
