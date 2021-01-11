package com.itvillage.afridigaming.ui.me;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

import com.itvillage.afridigaming.BuildConfig;
import com.itvillage.afridigaming.LoginActivity;
import com.itvillage.afridigaming.PasswordChange;
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
    private TextView userProfileName,availableBalance,totalkill,totalWins;
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
        ConstraintLayout logoutUser = view.findViewById(R.id.logoutUser);
        ConstraintLayout changePassword = view.findViewById(R.id.changePassword);
        ConstraintLayout help = view.findViewById(R.id.help);
        ConstraintLayout share = view.findViewById(R.id.share);

        userProfileName = view.findViewById(R.id.userProfileName);
        availableBalance = view.findViewById(R.id.availableBalance);
        totalkill = view.findViewById(R.id.totalkill);
        totalWins = view.findViewById(R.id.totalWins);


        getUserProfile();

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Afridi Gaming");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_add_monet_help, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        moneyBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), UserBalanceActivity.class));
            }
        });
        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });

        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), myProfileAdding.class));
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), PasswordChange.class));
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
                    totalkill.setText(Integer.toString(getUserProfile.getTotalKill()));
                    totalWins.setText(Double.toString(getUserProfile.getAcBalance()));

                }, throwable -> {
                    throwable.printStackTrace();
                }, () -> {

                });

    }

}