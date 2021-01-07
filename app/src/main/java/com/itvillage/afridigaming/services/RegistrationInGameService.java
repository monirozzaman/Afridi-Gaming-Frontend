package com.itvillage.afridigaming.services;

import android.content.Context;

import com.itvillage.afridigaming.api.LoginApi;
import com.itvillage.afridigaming.api.RegistrationInGameApi;
import com.itvillage.afridigaming.config.ApiClient;
import com.itvillage.afridigaming.dto.response.LoginResponse;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegistrationInGameService {

    private final Context context;
    private final String LOGIN_REQUEST_BODY_FORMAT = "{ \"partnerType\": \"%s\", \"partnerOneName\": \"%s\", \"partnerTwoName\": \"%s\", \"partnerThreeName\": \"%s\" }";

    public RegistrationInGameService(Context context) {
        this.context = context;
    }

    public Observable<Void> registrationInGame(String gameId,String partnerType, String partnerOneName,String partnerTwoName, String partnerThreeName) {

        String registrationRequestBody = String.format(LOGIN_REQUEST_BODY_FORMAT,partnerType ,partnerOneName, partnerTwoName, partnerThreeName);

        return ApiClient.getClient(context)
                .create(RegistrationInGameApi.class)
                .registrationInGame(gameId,RequestBody.create(MediaType.parse("application/json"), registrationRequestBody));
    }



}
