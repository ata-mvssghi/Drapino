package com.example.drapino.loginApi

import com.example.drapino.loginiResponses.FirstStepLoginResponse
import com.example.drapino.loginiResponses.LastApiCallResponse
import com.example.drapino.loginiResponses.SecondLogInResponse
import com.example.drapino.loginiResponses.ThirdLogInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface LogInApiService{

        @FormUrlEncoded
        @POST("user/zarinpal-login")
        suspend fun login(@Field("phone_number") phoneNumber: String, @Field("source") source: String): Response<FirstStepLoginResponse>

        @GET("api/v1/Users/CheckUserAfterSendOtp")
        suspend fun checkUserAfterSendOtp(
                @Query("phoneNumber") phoneNumber: String,
                @Query("isNewUser") isNewUser: Boolean,
                @Query("refCode") refCode: String?
        ): Response<SecondLogInResponse>

        @POST("user/zarinpal-login")
        @FormUrlEncoded
        suspend fun zarinpalLogin(
                @Field("phone_number") phoneNumber: String,
                @Field("otp") otp: String,
                @Field("source") source: String
        ): Response<ThirdLogInResponse>

        @Headers("Content-Type: application/json")
        @POST("api/v1/Users/VerifyToken")
        suspend fun verifyToken(@Body requestBody: RequestBody): Response<LastApiCallResponse>
}
data class RequestBody(val phoneNumber: String, val token: String)