package com.thuctran.easylanguage.view.fragment

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.databinding.FragmentLearnBinding
import com.thuctran.easylanguage.model.Word
import com.thuctran.easylanguage.view.activity.MainActivity
import com.thuctran.easylanguage.view.adapter.ReAdapterListTopic
import com.thuctran.easylanguage.viewmodel.CommonVM

class LearnFragment : BaseFragment<FragmentLearnBinding>() {
    companion object {
        var TAG: String = LearnFragment::class.java.name
        private val SCHOOL: String = "school"
        private val EXAMINATION: String = "examination"
    }


    override val viewModel by viewModels<CommonVM>()
    override fun initViewBinding(): FragmentLearnBinding {
        return FragmentLearnBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        callBack1!!.callBack(MainActivity.KEY_ICON_LEARN,null)
        var adapter: ReAdapterListTopic? = null
        adapter = ReAdapterListTopic(context1!!) {
            adapter!!.ENTOPIC.observe(this) {
                showLearnDetailAct(it)
            }
        }
        binding!!.rcListTopic.adapter = adapter

        //PT điều khiển nút backPress
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callBack1!!.showDialogCloseApp()
            }
        })
    }

    @SuppressLint("SuspiciousIndentation")
    private fun showLearnDetailAct(enTopic: String) {
        if (enTopic == SCHOOL) {
            callBack1!!.goLearnDetailAct(App.INSTANCE.STORAGE.listWordSchool)
        }
        if (enTopic == EXAMINATION) {
            callBack1!!.goLearnDetailAct(App.INSTANCE.STORAGE.listWordExamination)
        }

        Log.i(TAG,"bạn đang ấn vào topic: $enTopic")
    }
}