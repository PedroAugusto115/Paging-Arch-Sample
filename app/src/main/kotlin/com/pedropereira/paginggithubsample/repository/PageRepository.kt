package com.pedropereira.paginggithubsample.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.pedropereira.paginggithubsample.api.GitHubApi
import com.pedropereira.paginggithubsample.model.Repository

class PageRepository {

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }

    fun getPage(): LiveData<PagedList<Repository>> {
        val sourceFactory = PageDataSourceFactory(GitHubApi.create())

        return LivePagedListBuilder(sourceFactory,
                PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPageSize(NETWORK_PAGE_SIZE)
                        .setInitialLoadSizeHint(NETWORK_PAGE_SIZE)
                        .setPrefetchDistance(5)
                        .build())
                .build()
    }

}