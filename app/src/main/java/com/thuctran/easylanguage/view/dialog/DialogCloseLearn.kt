package com.thuctran.easylanguage.view.dialog

import android.content.Context
import android.view.animation.AnimationUtils
import com.thuctran.easylanguage.databinding.DialogCloseLearnBinding

class DialogCloseLearn(context:Context) : BaseDialog<DialogCloseLearnBinding>(context) {
    companion object {
        private val TAG: String = DialogCloseLearn::class.java.name
    }

    override fun initViewBinding(): DialogCloseLearnBinding {
        return DialogCloseLearnBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding!!.btNo.setOnClickListener{
            binding!!.btNo.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            dismiss()
        }

        binding!!.btYes.setOnClickListener{
            binding!!.btYes.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            callBack1!!.closeLearn()
        }
    }

}