package com.ramat.original.moviescatalog.repository

import android.arch.lifecycle.LiveData
import com.ramat.origin.marvelheroesapp.api.OnGetMarvelCallback
import com.ramat.original.moviescatalog.model.ReturnData

interface MarvelRepository{
    //name: String, apiKey: String, ts: String, hash: String,
    fun getCharacter(callback: OnGetMarvelCallback)
    fun getHeroes(): LiveData<ReturnData>
}