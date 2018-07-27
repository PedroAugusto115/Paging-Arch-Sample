package com.pedropereira.paginggithubsample.repository

import android.arch.paging.DataSource
import com.pedropereira.paginggithubsample.api.GitHubApi
import com.pedropereira.paginggithubsample.model.Repository

class PageDataSourceFactory(private val api: GitHubApi):
        DataSource.Factory<Int, Repository>() {

    override fun create(): DataSource<Int, Repository> {
        return PageKeyedGitHubDataSource(api)
    }

}
