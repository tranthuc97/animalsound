package com.thuctran.easylanguage.view.fragment.learn

import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.databinding.FragmentLearnP11Binding
import com.thuctran.easylanguage.view.fragment.BaseFragment

class P1LearnDetailFragment1 : BaseFragment<FragmentLearnP11Binding>() {
    companion object{
        var TAG:String = P1LearnDetailFragment1::class.java.name
    }

    override fun initViewBinding(): FragmentLearnP11Binding {
        return FragmentLearnP11Binding.inflate(layoutInflater)
    }

    override fun initViews() {
        Glide.with(this).load(App.INSTANCE.STORAGE.word!!.PICTURE).into(binding!!.ivPictureLearn)
        binding!!.tvHint.text = App.INSTANCE.STORAGE.word!!.HINT


        binding!!.lnLearn11.setOnClickListener{
            binding!!.lnLearn11.startAnimation(AnimationUtils.loadAnimation(context1,com.thuctran.easylanguage.R.anim.anim_rotate_learn))

            Handler().postDelayed({
                NavHostFragment.findNavController(this).navigate(com.thuctran.easylanguage.R.id.toLearn1_2)     //nhớ tạo trong layout thằng FragmentContainerView
            },1000)
        }
    }
}