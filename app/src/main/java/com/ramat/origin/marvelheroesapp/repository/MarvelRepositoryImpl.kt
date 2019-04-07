package com.ramat.original.moviescatalog.repository

import android.support.v4.os.BuildCompat
import android.util.Log
import com.ramat.origin.marvelheroesapp.api.MarvelApi
import com.ramat.origin.marvelheroesapp.api.OnGetMarvelCallback
import com.ramat.origin.marvelheroesapp.utils.Constants
import com.ramat.original.moviescatalog.model.ReturnData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

open class MarvelRepositoryImpl: MarvelRepository {
    private var service: MarvelApi

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        const val CHAR_NAME = "wolverine"
        const val PUBLIC_KEY = Constants.Keys.PUBLIC_KEY
        const val PRIVATE_KEY = Constants.Keys.PRIVATE_KEY
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(MarvelApi::class.java)
    }



    override fun getCharacter(callback: OnGetMarvelCallback) {
        val ts = System.currentTimeMillis().toString()
        var hash = getMd5(ts)
        service.getSimpleCharacter(CHAR_NAME, ts, PUBLIC_KEY, hash)
            .enqueue(object : Callback<ReturnData> {

                override fun onResponse(call: Call<ReturnData>, response: Response<ReturnData>) {
                    if (response.isSuccessful) {
                        val character = response.body()
                        if (character!!.data.results != null) {
                            callback.onSuccess(character!!.data.results)
                        } else {
                            callback.onError()
                            Log.e("Response", " response null")
                        }
                    } else {
                        callback.onError()
                        Log.e("Response", response.raw().networkResponse().toString())
                    }

                }

                override fun onFailure(call: Call<ReturnData>, t: Throwable) {
                    callback.onError()
                    t.printStackTrace()
                    Log.e("Response", javaClass.simpleName + " not response 2" + t)
                }
            })
    }

    fun getMd5(ts: String): String {
        try {

            val md = MessageDigest.getInstance("MD5")

            val messageDigest = md.digest(ts.toByteArray()
                    + PRIVATE_KEY.toByteArray()
                    + PUBLIC_KEY.toByteArray())

            val no = BigInteger(1, messageDigest)

            var hashtext = no.toString(16)
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            return hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

}