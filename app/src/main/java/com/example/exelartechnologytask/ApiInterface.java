package com.example.exelartechnologytask;


import com.example.exelartechnologytask.Model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("registerusertest.php?")
    @FormUrlEncoded
    Call<User> userRegister(
            @Field("fname") String name,
            @Field("lname") String lname,
            @Field("email_id") String email_id,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("madd") String madd,
            @Field("madd_from") String madd_form,
            @Field("newsletter") String newsletter,
            @Field("address") String address,
            @Field("timezone") String timezone
    );
    @FormUrlEncoded
    @POST("loginusertest.php?")
    Call<User> userLogin(@Field("email_id") String email_id,
                         @Field("password") String password,
                         @Field("madd") String madd,
                         @Field("madd_form") String madd_form);

}
