package com.kdnt.pokedex.ui.splash

import android.os.Bundle
import com.kdnt.pokedex.R
import com.kdnt.pokedex.core.base.BaseActivity
import com.kdnt.pokedex.databinding.ActivitySplashBinding
import com.kdnt.pokedex.ui.home.HomeActivity
import com.kdnt.todoapp.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override fun getLayoutResId(): Int = R.layout.activity_splash
    override val mViewModel: SplashViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gotoHome()

        try {
            val versionName: String = packageManager.getPackageInfo(packageName, 0).versionName
            "v$versionName".also { mViewBinding.appVersionName.text = it }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun gotoHome() {
        startActivity(HomeActivity.openActivity(this))
    }

    override fun onBackPressed() {}


}