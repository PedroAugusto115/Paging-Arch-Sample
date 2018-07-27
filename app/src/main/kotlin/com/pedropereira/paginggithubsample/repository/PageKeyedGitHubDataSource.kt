package com.pedropereira.paginggithubsample.repository

import android.arch.paging.PageKeyedDataSource
import com.pedropereira.paginggithubsample.api.GitHubApi
import com.pedropereira.paginggithubsample.model.Page
import com.pedropereira.paginggithubsample.model.Repository
import com.pedropereira.paginggithubsample.nextPage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageKeyedGitHubDataSource(private val gitHubApi: GitHubApi) :
        PageKeyedDataSource<Int, Repository>(){

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repository>) {
        val call = gitHubApi.getRepositoryPage(
                itemsCount = params.requestedLoadSize)

        call.enqueue(object : Callback<Page> {
            override fun onFailure(call: Call<Page>?, t: Throwable?) {}

            override fun onResponse(call: Call<Page>?, response: Response<Page>) {
                if(response.isSuccessful) {
                    val data = response.body()?.items!!
                    callback.onResult(data, null, response.nextPage)
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {

        val call = gitHubApi.getRepositoryPage(
                page = params.key,
                itemsCount = params.requestedLoadSize)

        call.enqueue(object : Callback<Page> {
            override fun onFailure(call: Call<Page>?, t: Throwable?) {}

            override fun onResponse(call: Call<Page>?, response: Response<Page>) {
                if(response.isSuccessful) {
                    val data = response.body()?.items!!
                    callback.onResult(data, response.nextPage)
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        // No need to implement in this case
    }

}
