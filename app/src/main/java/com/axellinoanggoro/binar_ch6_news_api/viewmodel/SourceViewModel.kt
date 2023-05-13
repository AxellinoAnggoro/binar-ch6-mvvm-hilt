package com.axellinoanggoro.binar_ch6_news_api.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_ch6_news_api.model.ResponseDataSource
import com.axellinoanggoro.binar_ch6_news_api.model.Source
import com.axellinoanggoro.binar_ch6_news_api.network.ApiClient
import com.axellinoanggoro.binar_ch6_news_api.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(var api : ApiService) :ViewModel() {
    var liveDataSource : MutableLiveData<List<Source>?> = MutableLiveData()

    fun getDataSource():MutableLiveData<List<Source>?>{
        return liveDataSource
    }

    fun callApiSource(category : String){
        api.getAllSource(category)
            .enqueue(object : Callback<ResponseDataSource>{
                override fun onResponse(
                    call: Call<ResponseDataSource>,
                    response: Response<ResponseDataSource>
                ) {
                    if (response.isSuccessful){
                        liveDataSource.postValue(response.body()!!.sources)
                    }else{
                        liveDataSource.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataSource>, t: Throwable) {
                    liveDataSource.postValue(null)
                }

            })
    }
}