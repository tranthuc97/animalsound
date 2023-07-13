package com.thuctran.easylanguage.view.fragment

import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.R
import com.thuctran.easylanguage.databinding.FragmentEntrainBinding
import com.thuctran.easylanguage.viewmodel.CommonVM

class EntrainFragment : BaseFragment<FragmentEntrainBinding>(){
    companion object {
        val TAG: String = EntrainFragment::class.java.name
        val AGE: String  = "AGE"
        val USER_NAME: String  = "USER_NAME"
    }

    override val viewModel by viewModels<CommonVM>()

    override fun initViewBinding(): FragmentEntrainBinding {
        return FragmentEntrainBinding.inflate(layoutInflater)
    }

    override fun initViews() {


        binding!!.tvLearnEnglish.setOnClickListener {
            binding!!.tvLearnEnglish.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            toHomeFragment()
        }
    }

    private fun toHomeFragment() {
        if(binding!!.edtName.text.toString().isEmpty() || binding!!.edtAge.text.toString().isEmpty()) {
            showNotify("vui lòng nhập đầy đủ thông tin..")
        }else {
            CommonUtils.INSTANCE.savePrefs(USER_NAME,binding!!.edtName.text.toString())
            CommonUtils.INSTANCE.savePrefs(AGE,binding!!.edtAge.text.toString())
            binding!!.ivDict.startAnimation(AnimationUtils.loadAnimation(context1,R.anim.anim_dict_run))
            Handler().postDelayed({
                callBack1!!.goMainAct()
            },1800)

        }
    }


}

/*var anim: Animation = AnimationUtils.loadAnimation(context1, R.anim.anim_dict_run)
            anim.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(p0: Animation?) {
                    //do nothing
                }

                override fun onAnimationEnd(p0: Animation?) {
                    callBack1!!.showFragment(HomeFragment().TAG,null,false)
                }

                override fun onAnimationRepeat(p0: Animation?) {
                    //do nothing
                }
            })
            binding!!.ivDict.animation = anim*/