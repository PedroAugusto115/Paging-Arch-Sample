package com.pedropereira.paginggithubsample.model

import com.squareup.moshi.Json

data class Repository(@Json(name = "id") val id: Long,
                      @Json(name = "name") val name: String)