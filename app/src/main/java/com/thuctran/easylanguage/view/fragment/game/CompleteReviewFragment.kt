package com.thuctran.easylanguage.view.fragment.game

import android.os.Handler
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.R
import com.thuctran.easylanguage.databinding.FragmentCompleteLearnBinding
import com.thuctran.easylanguage.databinding.FragmentCompleteReviewBinding
import com.thuctran.easylanguage.model.Word
import com.thuctran.easylanguage.view.fragment.BaseFragment
import com.thuctran.easylanguage.viewmodel.CommonVM
import com.thuctran.easylanguage.viewmodel.GameVM

@Suppress("UNCHECKED_CAST", "DEPRECATION")
class CompleteReviewFragment : BaseFragment<FragmentCompleteReviewBinding>() {
    companion object{
        var TAG:String = CompleteReviewFragment::class.java.name
    }
    override val viewModel by viewModels<CommonVM>()

    override fun initViewBinding(): FragmentCompleteReviewBinding {
        return FragmentCompleteReviewBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        Handler().postDelayed({
            callBack1!!.backToMainActivity()
        },5000)
    }
}