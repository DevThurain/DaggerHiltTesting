package com.thurainx.androiddaggerhilttesting.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.thurainx.androiddaggerhilttesting.R
import com.thurainx.androiddaggerhilttesting.databinding.FragmentDoubleTokenBinding
import com.thurainx.androiddaggerhilttesting.mvp.presenters.DoubleTokenPresenterImpl
import com.thurainx.androiddaggerhilttesting.mvp.views.DoubleTokenView


class DoubleTokenFragment : Fragment(), DoubleTokenView {


    private var binding: FragmentDoubleTokenBinding? = null
    private val mPresenter: DoubleTokenPresenterImpl by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoubleTokenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter.initView(this)

        setUpListeners()
        mPresenter.onUiReady(requireContext(), this)
    }

    private fun setUpListeners(){
        binding?.btnApiHealth?.setOnClickListener {
            mPresenter.onTapHealthCheck()
        }
        binding?.btnCall401Api?.setOnClickListener {
            mPresenter.onTapAuthorizeCheck()
        }
    }

    override fun onLoadingHealthCheck() {
        binding?.tvApiStatus?.text = "Calling Health Check Api"
    }

    override fun onLoadingAuthorizeCheck() {
        binding?.tvApiStatus?.text = "Calling Authorize Check Api"
    }

    override fun onSuccessHealthCheck() {
        binding?.tvApiStatus?.text = "Health Check Success"
    }

    override fun onSuccessAuthorizeCheck() {
        binding?.tvApiStatus?.text = "Authorize Check Success"
    }

    override fun showErrorMessage(message: String) {
        binding?.root?.let { Snackbar.make(it,message, Snackbar.LENGTH_SHORT).show() }
    }

}