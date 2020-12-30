package com.itvillage.afridigaming.ui.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.itvillage.afridigaming.R;
import com.itvillage.afridigaming.adapter.PalyedGameListAdapter;

public class DashboardFragment extends Fragment {
    String[] maintitle = {
            "All Weapons | Mobile Only | 3234", "Title 2",
            "Title 3", "Title 4",
            "Title 5",
    };

    String[] subtitle = {
            "Time: 29/29/2020 at 20:20 ", "Sub Title 2",
            "Sub Title 3", "Sub Title 4",
            "Sub Title 5",
    };

    Integer[] imgid = {
            R.drawable.free_fire, R.drawable.free_fire,
            R.drawable.free_fire, R.drawable.free_fire,
            R.drawable.free_fire
    };
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final ListView playGameList = root.findViewById(R.id.playGameList);
        PalyedGameListAdapter adapter = new PalyedGameListAdapter(this.getActivity() , maintitle, subtitle, imgid);
        playGameList.setAdapter(adapter);
        return root;
    }
}