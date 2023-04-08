package com.mandeep.marvelbook.util.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.util.base
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 07 Apr, 2023
 *
 **/
abstract class BaseFragment<VB : ViewBinding>(private val bindingInflater: (LayoutInflater) -> VB) :
    Fragment() {
    private var _binding: VB? = null
    open val binding: VB
        get() = _binding as VB

    open val TAG: String? = this::class.simpleName


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        _binding?.let {

            return it.root

        } ?: throw IllegalArgumentException("Binding variable is null")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindInitAdapters()
        bindInitViews()
        callAPI()
        bindListeners()
        bindObservers()
        initViewModels()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun callAPI()
    abstract fun bindInitViews()
    abstract fun bindInitAdapters()
    abstract fun bindListeners()
    abstract fun bindObservers()
    abstract fun initViewModels()
}