package com.pedropereira.paginggithubsample.viewmodel

import android.arch.lifecycle.ViewModel
import com.pedropereira.paginggithubsample.repository.PageRepository

class PagingViewModel: ViewModel() {

    val repositories = PageRepository().getPage()

}