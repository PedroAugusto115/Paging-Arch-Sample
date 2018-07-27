package com.pedropereira.paginggithubsample

import com.pedropereira.paginggithubsample.model.Page
import retrofit2.Response

internal val Response<Page>.nextPage : Int?
get(){
    val nextUrl = headers().get("Link")?.split(',')?.firstOrNull { it.contains("next") }
    return nextUrl?.substring(nextUrl.indexOf("page=") + 5)?.split("&")?.firstOrNull()?.toInt()
}