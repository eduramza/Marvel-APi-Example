package com.ramat.original.moviescatalog.repository

import com.ramat.origin.marvelheroesapp.api.OnGetMarvelCallback

interface MarvelRepository{
    //name: String, apiKey: String, ts: String, hash: String,
    fun getCharacter(callback: OnGetMarvelCallback)
}