package com.bitamirshafiee.challengeinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bitamirshafiee.challengeinterview.di.networking.DaggerNetworkingComponent
import com.bitamirshafiee.challengeinterview.di.networking.NetworkingComponent
import com.bitamirshafiee.challengeinterview.di.networking.NetworkingModule
import com.bitamirshafiee.challengeinterview.questionlist.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(SearchFragment.newInstance())

    }

    fun showFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        fragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.container, fragment)
            addToBackStack(fragment::class.java.toString())
        }
    }

    fun getNetworkingComponent() : NetworkingComponent{
        return DaggerNetworkingComponent.builder()
            .networkingModule(NetworkingModule())
            .build()
    }
}