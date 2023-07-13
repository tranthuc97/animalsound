package com.thuctran.easylanguage.view.fragment

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.R
import com.thuctran.easylanguage.databinding.FragmentSplashBinding
import com.thuctran.easylanguage.view.fragment.learn.P2LearnFragment
import com.thuctran.easylanguage.viewmodel.CommonVM

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    companion object {
        var TAG: String = SplashFragment::class.java.name
    }
    override val viewModel by viewModels<CommonVM>()    //phải khai báo như thế này thì mới sd được viewmodel trong Flagment


    override fun initViewBinding(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun initViews() {

        CommonUtils.INSTANCE.savePrefs(P2LearnFragment.WRONG_WORD,"")
        binding!!.ivLet.startAnimation(AnimationUtils.loadAnimation(context1,R.anim.anim_let))
        binding!!.ivGet.startAnimation(AnimationUtils.loadAnimation(context1,R.anim.anim_get))
        binding!!.ivStarted.startAnimation(AnimationUtils.loadAnimation(context1,R.anim.anim_started))
        var anim:Animation = AnimationUtils.loadAnimation(context1,R.anim.anim_bus)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                //do nothing
            }

            override fun onAnimationEnd(p0: Animation?) {
                    if(CommonUtils.INSTANCE.getPrefs(EntrainFragment.USER_NAME) == null){
                        callBack1!!.showFragment(EntrainFragment.TAG, null, false)
                    }else {
                        callBack1!!.goMainAct()
                    }
            }

            override fun onAnimationRepeat(p0: Animation?) {
                //do nothing
            }
        })
        binding!!.ivBus.animation = anim

    }
}