package com.example.fiction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fiction.databinding.ActivityMainBinding
import com.example.fiction.fragments.HomeFragment
import com.example.fiction.fragments.LibraryFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val libraryFragment = LibraryFragment()

    private var currentFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, currentFragment)
            .commit()

        setButtonNavigation()
    }

    private fun switchFragment(fragment: Fragment) {
        if (currentFragment != fragment) {
            currentFragment = fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit()
        }
    }

    private fun setButtonNavigation() = with(mainBinding) {
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_bottom_nav -> {
                    switchFragment(homeFragment)
                }

                R.id.library_bottom_nav -> {
                    switchFragment(libraryFragment)
                }
            }
            true
        }
    }
}