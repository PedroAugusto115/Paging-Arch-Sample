package com.pedropereira.paginggithubsample.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.pedropereira.paginggithubsample.R
import com.pedropereira.paginggithubsample.model.Repository
import com.pedropereira.paginggithubsample.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : AppCompatActivity() {

    private lateinit var model: PagingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        model = ViewModelProviders.of(this).get(PagingViewModel::class.java)

        initAdapter()

    }

    private fun initAdapter() {
        val adapter = RepositoriesAdapter()
        list.adapter = adapter
        model.repositories.observe(this, Observer<PagedList<Repository>> {
            adapter.submitList(it)
        })
    }
}
