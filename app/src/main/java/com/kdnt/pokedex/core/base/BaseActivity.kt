package com.kdnt.pokedex.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VM : BaseViewModel, VB: ViewDataBinding>:AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract val mViewModel: VM
    lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = DataBindingUtil.setContentView(this, getLayoutResId())
        mViewBinding.lifecycleOwner = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


    }
}