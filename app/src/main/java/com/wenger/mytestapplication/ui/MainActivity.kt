package com.wenger.mytestapplication.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.wenger.mytestapplication.R
import com.wenger.mytestapplication.base.BaseActivity
import com.wenger.mytestapplication.databinding.ActivityMainBinding
import com.wenger.mytestapplication.utils.ConnectionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: MainActivityViewModel by viewModels()
    override fun getNavFragment(): Fragment? =
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}