package com.ramat.origin.marvelheroesapp.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ramat.origin.marvelheroesapp.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }

}
