package com.itvillage.afridigaming.ui.me;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.itvillage.afridigaming.R;
import com.itvillage.afridigaming.UserBalanceActivity;
import com.itvillage.afridigaming.dto.response.UserCreateProfileResponse;
import com.itvillage.afridigaming.myProfileAdding;
import com.itvillage.afridigaming.services.GetUserService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MeFragment extends Fragment {

    private MeViewModel mViewModel;
    private TextView userProfileName,availableBalance;
    private View view;

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_fragment, container, false);
        ConstraintLayout myProfile = view.findViewById(R.id.myProfile);
        ConstraintLayout moneyBag = view.findViewById(R.id.moneyBag);

        userProfileName = view.findViewById(R.id.userProfileName);
        availableBalance = view.findViewById(R.id.availableBalance);


        getUserProfile();

        moneyBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), UserBalanceActivity.class));
            }
        });

        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), myProfileAdding.class));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MeViewModel.class);
        // TODO: Use the ViewModel
    }


    @SuppressLint("CheckResult")
    private void getUserProfile() {



        GetUserService getUserService = new GetUserService(view.getContext().getApplicationContext());
        Observable<UserCreateProfileResponse> userCreateProfileResponseObservable =
                getUserService.getUserProfile();

        userCreateProfileResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getUserProfile -> {

                    userProfileName.setText(getUserProfile.getFirstName());
                    availableBalance.setText(Double.toString(getUserProfile.getAcBalance()));

                }, throwable -> {
                    throwable.printStackTrace();
                }, () -> {

                });

    }

}