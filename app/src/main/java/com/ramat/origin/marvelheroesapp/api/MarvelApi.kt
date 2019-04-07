package com.ramat.origin.marvelheroesapp.api

import com.ramat.original.moviescatalog.model.Result
import com.ramat.original.moviescatalog.model.ReturnData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi{

    @GET("characters")
    fun getSimpleCharacter(@Query("name") name: String,
                           @Query("ts") ts: String,
                           @Query("apikey") apiKey: String,
                           @Query("hash") hash: String): Call<ReturnData>

}

interface OnGetMarvelCallback{
    fun onSuccess(movies: List<Result>)
    fun onError()
}
