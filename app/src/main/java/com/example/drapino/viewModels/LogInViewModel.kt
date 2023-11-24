package com.example.drapino.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.drapino.loginApi.LogInApiService
import com.example.drapino.loginApi.RequestBody
import com.example.drapino.loginApi.RetrofitInstance
import com.example.drapino.loginiResponses.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogInViewModel:ViewModel() {
    var isNewUser = false
    val retrofit  = RetrofitInstance.getRetrofitInstance()
    val apiService = retrofit.create(LogInApiService::class.java)
    val secondApiRetrofit = RetrofitInstance.getSecondRetrofitInstance()
    val secondApiService = secondApiRetrofit.create(LogInApiService::class.java)
    private lateinit var user_token :Token
    private lateinit var user_token_string :String
    private val _isChecked = MutableLiveData<Boolean>()
    // Convert MutableLiveData to LiveData to expose it publicly
    val isChecked: LiveData<Boolean> get() = _isChecked
    fun getFirstApiRequest(phone_number:String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.login(phone_number,"dropino")
                if(response.isSuccessful) {
                    Log.i("dropino", "${response.body()?.message}")
                    isNewUser = response.body()?.data?.new_user == true
                    Log.i("dropino", "is new user  = $isNewUser")
                    val endPoint = "api/v1/Users/CheckUserAfterSendOtp"
                    Log.i("dropino", "phone nu,ber = $phone_number")
                }
                else{
                    Log.i("dropino","first api call failed")
                    return@launch
                }
                val reformedNumber = reformatPhoneNumber(phone_number)
                Log.i("dropino", "reformed is = $reformedNumber")
                //*********************
                val checkResponse = secondApiService.checkUserAfterSendOtp(reformedNumber, isNewUser, null)
                Log.i("dropino", "ssssssssssss")
                if (checkResponse.isSuccessful) {
                    Log.i("dropino", "mmmmmmmmmmm")
                    val responseBody = checkResponse.body()
                    if (responseBody?.isSuccess == true) {
                        Log.i("dropino", responseBody.message ?: "No message")
                        withContext(Dispatchers.Main) {
                            _isChecked.value = true
                        }
                        Log.i("dropino","mmd is a d good gurt")
                    }
                    else {
                        // Server returned success status, but the response body indicates failure
                        Log.i("dropino", "Request failed. Server message: ${responseBody?.message}")
                    }
                }
                else {
                    // Request failed (e.g., network issue, server error)
                    val errorBody = checkResponse.errorBody()?.string()
                    Log.i("dropino", "Request failed. Error body: $errorBody")
                }
            }
            catch (e:Exception){
                println(e.stackTrace.toString())
            }
        }
    }
    fun submitButtonClicked(phone_number:String , otpCode:String){
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val thirdApiCallResponse = apiService.zarinpalLogin(phone_number,otpCode,"dropino")
                if(thirdApiCallResponse.isSuccessful){
                    val response = thirdApiCallResponse.body()
                    Log.i("dropino","${response?.data}" )
                    if(response?.status == true){
                        Log.i("dropino","$response.message")
                    }
                    else{
                        Log.i("dropino","${response?.message}")
                    }
                    Log.i("dropino","befor eenteeing last sakt")
                    val correctedNum  = reformatPhoneNumber(phone_number)
                    user_token_string = response?.data.toString()
                    lastTask(correctedNum,user_token_string)
                }
                else{
                    Log.i("dropino","wtf")
                    Log.e("dropino", thirdApiCallResponse.errorBody().toString())
                }

            }
            catch (e:java.lang.Exception){
                Log.i("dropino","wtfff")
                Log.e("dropino", "${e.message}")
            }
        }

    }
    fun lastTask(phone_number:String,token:String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.i("dropino","entered last task fun")
                val requestBody = RequestBody(phone_number, token)
                val verifyResponse = secondApiService.verifyToken(requestBody)
                Log.i("dropino","last api call is called ")
                if(verifyResponse.isSuccessful){
                    Log.i("dropino","hagahaha")
                    Log.i("dropino","${verifyResponse.body()?.message}")
                    val body = verifyResponse.body()
                    user_token = body?.data?.token!!
                }
                else
                {
                    Log.i("dropino","faild to get the verify token api call")
                }
            }
            catch (e:java.lang.Exception){
                Log.e("dropino","${e.message}")
            }

        }

    }
    fun reformatPhoneNumber(input: String): String {
        // Remove the first two characters ("98") and replace with "0"
        return "0${input.substring(2)}"
    }
}