package com.project.myrecipe.screens.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.project.myrecipe.R
import com.project.myrecipe.screens.mainMenu.MainMenu
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onBackPressed() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        val currentFragment = navHostFragment.childFragmentManager.fragments.firstOrNull()

        if (currentFragment is MainMenu) {
            if (backPressedOnce) {
                super.onBackPressed()
            } else {
                backPressedOnce = true
                Toast.makeText(this, "Πατήστε πάλι πίσω για έξοδο", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    backPressedOnce = false
                }, 2000)
            }
        } else {
            super.onBackPressed()
        }
    }
}
