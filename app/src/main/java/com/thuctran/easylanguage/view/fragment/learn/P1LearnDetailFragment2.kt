package com.thuctran.easylanguage.view.fragment.learn

import android.annotation.SuppressLint
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.NavHostFragment
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.databinding.FragmentLearnP12Binding
import com.thuctran.easylanguage.view.fragment.BaseFragment

class P1LearnDetailFragment2 : BaseFragment<FragmentLearnP12Binding>() {
    companion object{
        var TAG:String = P1LearnDetailFragment2::class.java.name
    }

    override fun initViewBinding(): FragmentLearnP12Binding {
        return FragmentLearnP12Binding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        binding!!.tvWord.text = App.INSTANCE.STORAGE.word!!.WORD
        binding!!.tvSpelling.text = App.INSTANCE.STORAGE.word!!.SPELLING
        binding!!.tvTranslate.text = App.INSTANCE.STORAGE.word!!.TRANSLATE
        binding!!.tvType.text = "("+App.INSTANCE.STORAGE.word!!.TYPE+")"


        binding!!.lnLearn12.setOnClickListener{
            binding!!.lnLearn12.startAnimation(AnimationUtils.loadAnimation(context1,com.thuctran.easylanguage.R.anim.anim_rotate_learn))
            Handler().postDelayed({
                NavHostFragment.findNavController(this).navigate(com.thuctran.easylanguage.R.id.toLearn1_1)     //nhớ tạo trong layout thằng FragmentContainerView
            },1000)
        }
    }
}