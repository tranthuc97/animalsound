package com.thuctran.easylanguage.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.thuctran.easylanguage.R

abstract class BaseDialog<V : ViewBinding>(context: Context) :
    Dialog(context, R.style.Theme_dialog) {
    protected var callBack1: CallBack? = null
    protected var binding: V? = null

    var CALLBACK: CallBack
        get() = callBack1!!
        set(value) {
            callBack1 =value
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding()
        setContentView(binding!!.root)
        initViews()
    }

    abstract fun initViewBinding(): V

    protected open fun initViews() {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    private fun showDialog() {
        //gọi callBack để nhờ thằng Activity đướng app dùm
        callBack1!!.showDialog()
    }

    interface CallBack {
        fun showDialog() {}
        fun showDialogCloseLearn() {}
        fun closeLearn() {}
        fun closeApp() {}
        fun showNotify() {}
        fun closeAppTrue() {}

    }

    /*var dialog = DialogGameOver(context1)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.CALLBACK = this
        dialog.show()
    * */
}