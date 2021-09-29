package com.example.newhealios.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.example.newhealios.R

abstract class BaseFragment : Fragment() {

    private var mProgressDialog: MaterialDialog? = null
    private var mDialog: MaterialDialog? = null
    private lateinit var mBaseViewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    abstract fun setupUI()
    abstract fun setupObserver()

    abstract val layout: Int

    protected fun showLoading() {
        hideLoading()
        try {
            context.let {
                mProgressDialog = MaterialDialog.Builder(it!!)
                    .content(R.string.msg_please_wait)
                    .cancelable(false)
                    .progress(true, 0)
                    .show()
            }

        } catch (e: Exception) {
        }
    }

    protected fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    open fun showDialogAlert(
        context: Context?,
        content: String,
    ) {
        if (context == null) {
            return
        }
        hideDialogAlert()
        if (mDialog == null) {
            mDialog = MaterialDialog.Builder(context)
                .content(content)
                .cancelable(false)
                .positiveText(context.getString(R.string.txt_ok))
                .build()
        }
        mDialog?.show()
    }

    fun hideDialogAlert() {
        mDialog?.dismiss()
    }
}