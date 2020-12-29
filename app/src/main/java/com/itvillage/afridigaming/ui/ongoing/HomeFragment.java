package com.itvillage.afridigaming.ui.ongoing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.itvillage.afridigaming.GamesShowUserViewActivity;
import com.itvillage.afridigaming.R;
import com.itvillage.afridigaming.config.ImageAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        CardView free_fire_but = root.findViewById(R.id.free_fire_but);
        free_fire_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(root.getContext(), GamesShowUserViewActivity.class));
            }
        });
        ViewPager mViewPager = (ViewPager) root.findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(root.getContext());
        mViewPager.setAdapter(adapterView);
        return root;
    }
}