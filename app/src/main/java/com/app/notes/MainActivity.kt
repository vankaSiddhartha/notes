package com.app.notes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.app.notes.dataStoreManager.SharedPreferencesManager
import com.app.notes.view.fragments.HomeFragment
import com.app.notes.view.fragments.LoginFragment
import com.app.notes.view.fragments.OnBoardingFragment

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        sharedPreferencesManager = SharedPreferencesManager(this)

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        setContentView(R.layout.activity_main)
        val fragment = HomeFragment()
        window.statusBarColor = Color.parseColor("#232C4B")




    }

    override fun onStart() {
        super.onStart()
        if (sharedPreferencesManager.isLoggedIn) {
            // Navigate to main activity or perform logged in actions
            showFragment(HomeFragment())
        } else {
            // Navigate to login activity or perform logged out actions
            showFragment(OnBoardingFragment())
        }




    }
    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }
}