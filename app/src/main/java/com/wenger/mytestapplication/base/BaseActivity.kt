package com.wenger.mytestapplication.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

abstract class BaseActivity : AppCompatActivity() {

    protected val navController: NavController? by lazy {
        val fragment = getNavFragment()
        if (fragment != null) {
            findNavController(fragment)
        } else {
            null
        }
    }

    abstract fun getNavFragment() : Fragment?
}