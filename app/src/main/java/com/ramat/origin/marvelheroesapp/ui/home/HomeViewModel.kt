package com.ramat.origin.marvelheroesapp.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.ramat.origin.marvelheroesapp.api.OnGetMarvelCallback
import com.ramat.original.moviescatalog.model.ReturnData
import com.ramat.original.moviescatalog.repository.MarvelRepository
import com.ramat.original.moviescatalog.repository.MarvelRepositoryImpl

class HomeViewModel(private val repository: MarvelRepository = MarvelRepositoryImpl(),
                    private val fragment: HomeFragment = HomeFragment.newInstance())
    : ViewModel() {

    private val heroesList: MutableLiveData<ReturnData> = MutableLiveData()
    fun getHeroesList() = heroesList

    fun verifyConnectivity(activity: HomeActivity): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun getHeroes(){
        //fragment.showLoading()
        repository.getCharacter(object : OnGetMarvelCallback{

            override fun onSuccess(response: ReturnData) {
                Log.d("Reponse", "It's Ok!!!")
                heroesList.value = response
                //fragment.hideLoading()
            }

            override fun onError() {
                Log.e("ErrorViewModel", "Error in viewmodel call")
                //fragment.hideLoading()
            }
        })
    }
}
