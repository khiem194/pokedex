package com.kdnt.pokedex.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract val mViewModel: VM
    lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        mViewBinding.lifecycleOwner = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        return mViewBinding.root
    }


}