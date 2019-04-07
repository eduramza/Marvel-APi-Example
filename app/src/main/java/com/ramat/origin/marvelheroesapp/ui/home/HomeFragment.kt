package com.ramat.origin.marvelheroesapp.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ramat.origin.marvelheroesapp.R
import com.ramat.original.moviescatalog.model.Result
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var viewModel: HomeViewModel = HomeViewModel(fragment = this)
    private val adapter = MarvelCharacterAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        progressbar_home.visibility = View.GONE

        recycler_view_characters.layoutManager = LinearLayoutManager(context)
        recycler_view_characters.adapter = adapter

        viewModel.getHeroes()
        configureObservers()
    }

    fun configureObservers(){
        viewModel.getHeroesList().observe(this, Observer { heroes ->
            heroes?.let {
                adapter.updateList(heroes.data.results as MutableList<Result>)
            }
        })
    }

    fun showLoading(){
        progressbar_home.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progressbar_home.visibility = View.GONE
    }

}
