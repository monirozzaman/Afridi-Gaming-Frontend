package com.itvillage.afridigaming.api;

import com.itvillage.afridigaming.dto.response.UpdatePasswordResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UpdateRoomIdAndPasswordApi {
    @PUT("api/auth/admin/update/games/profile/{id}")
    Observable<Void> updateRoomIdAndPasswordApi(@Path("id") String id,@Body RequestBody body);
}
