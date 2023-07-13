package com.thuctran.easylanguage.view.fragment

import androidx.fragment.app.viewModels
import com.thuctran.easylanguage.databinding.FragmentEntrainBinding
import com.thuctran.easylanguage.viewmodel.CommonVM

class HomeFragment : BaseFragment<FragmentEntrainBinding>(){
    val TAG: String = HomeFragment::class.java.name
    override val viewModel by viewModels<CommonVM>()

    override fun initViewBinding(): FragmentEntrainBinding {
        return FragmentEntrainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        //do nothing
    }
}