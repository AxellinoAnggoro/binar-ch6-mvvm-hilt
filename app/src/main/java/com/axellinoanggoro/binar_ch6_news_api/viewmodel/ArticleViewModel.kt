package com.axellinoanggoro.binar_ch6_news_api.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_ch6_news_api.model.Article
import com.axellinoanggoro.binar_ch6_news_api.model.ResponseDataArticle
import com.axellinoanggoro.binar_ch6_news_api.network.ApiClient
import com.axellinoanggoro.binar_ch6_news_api.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(var api : ApiService):ViewModel() {
    var liveDataArticle : MutableLiveData<List<Article>> = MutableLiveData()

    fun getDataArticle() : MutableLiveData<List<Article>>{
        return liveDataArticle
    }

    fun callApiArticle(article : String){
        api.getAllArticles(article)
            .enqueue(object : Callback<ResponseDataArticle>{
                override fun onResponse(
                    call: Call<ResponseDataArticle>,
                    response: Response<ResponseDataArticle>
                ) {
                    if(response.isSuccessful){
                        liveDataArticle.postValue(response.body()!!.articles)
                    }else{
                        liveDataArticle.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataArticle>, t: Throwable) {
                    liveDataArticle.postValue(null)
                }


            })
    }
}