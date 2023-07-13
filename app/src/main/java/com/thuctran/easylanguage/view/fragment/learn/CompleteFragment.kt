package com.thuctran.easylanguage.view.fragment.learn

import android.os.Handler
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.R
import com.thuctran.easylanguage.databinding.FragmentCompleteLearnBinding
import com.thuctran.easylanguage.model.Word
import com.thuctran.easylanguage.view.fragment.BaseFragment
import com.thuctran.easylanguage.viewmodel.GameVM

@Suppress("UNCHECKED_CAST", "DEPRECATION")
class CompleteFragment : BaseFragment<FragmentCompleteLearnBinding>() {
    companion object{
        var TAG:String = CompleteFragment::class.java.name
    }
    override val viewModel by viewModels<GameVM>()

    override fun initViewBinding(): FragmentCompleteLearnBinding {
        return FragmentCompleteLearnBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        Glide.with(context1!!).load(R.raw.ic_complete).into(binding!!.ivComplete)       //load ảnh gif

        val listComplete:MutableList<Word> = data1 as MutableList<Word>
        if (App.INSTANCE.STORAGE.listWordReview.containsAll(listComplete)){
            //do no thing
        }else{
            App.INSTANCE.STORAGE.listWordReview.addAll(listComplete)
        }

        Handler().postDelayed({
            callBack1!!.backToMainActivity()
        },5000)
    }
}