package com.thuctran.easylanguage.view.fragment.learn

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.R
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.databinding.FragmentLearnP3Binding
import com.thuctran.easylanguage.view.dialog.BaseDialog
import com.thuctran.easylanguage.view.dialog.DialogCheck
import com.thuctran.easylanguage.view.fragment.BaseFragment
import com.thuctran.easylanguage.viewmodel.CommonVM
import com.thuctran.easylanguage.viewmodel.LearnActVM

class P3LearnFragment : BaseFragment<FragmentLearnP3Binding>(),BaseDialog.CallBack {

    private var dialog: DialogCheck? = null

    companion object{
        var TAG:String = P3LearnFragment::class.java.name
        val COUNT: String = "COUNT"
    }

    override val viewModel by viewModels<CommonVM>()

    override fun initViewBinding(): FragmentLearnP3Binding {
        return FragmentLearnP3Binding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        binding!!.tvContinue.isInvisible = true
        binding!!.tvTranslate.text = App.INSTANCE.STORAGE.word!!.TRANSLATE
        binding!!.tvType.text = "("+App.INSTANCE.STORAGE.word!!.TYPE+")"

        //binding!!.tvContinue.setOnClickListener{
        //            binding!!.tvContinue.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
        //            callBack1!!.showFragmentLearnDetail(P1LearnFragment.TAG,null,false)
        //        }

        binding!!.btCheck.setOnClickListener {
            binding!!.btCheck.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))


            if(binding!!.edtInputWord.text.toString().isEmpty()){
                showNotify("nhập từ vào ô trống!")
            }else {
                binding!!.btCheck.isClickable = false
                CommonUtils.INSTANCE.savePrefs(P2LearnFragment.CHECK_WORD, binding!!.edtInputWord.text.toString())
                if(binding!!.edtInputWord.text.toString() != App.INSTANCE.STORAGE.word!!.WORD){
                    CommonUtils.INSTANCE.savePrefs(P2LearnFragment.WRONG_WORD,binding!!.edtInputWord.text.toString())
                }
                Log.i(TAG,"danh sách từ sai: ${App.INSTANCE.STORAGE.listWordWrong}")
                binding!!.tvContinue.isInvisible = false
                showDialog()
            }
        }

        binding!!.tvContinue.setOnClickListener{
            binding!!.tvContinue.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            if(CommonUtils.INSTANCE.getPrefs(P2LearnFragment.WRONG_WORD)!!.isEmpty()) {
                CommonUtils.INSTANCE.savePrefs(COUNT, (CommonUtils.INSTANCE.getPrefs(COUNT)!!.toInt()+1).toString())
                //nếu ko có từ sai thì Prefs COUNT sẽ được +1 và lưu lại vào chính nó để update progressBar
                    callBack1!!.updateProgress(CommonUtils.INSTANCE.getPrefs(COUNT)!!.toInt())
                    Log.i(TAG, "số i đếm được là: ${CommonUtils.INSTANCE.getPrefs(COUNT)!!.toInt()}")

            }
            callBack1!!.toNextWord()
        }
    }


    override fun showDialog() {
        dialog = DialogCheck(context1!!)
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog!!.CALLBACK = this      //ghi đè PT showDialog() chính là cái PT đang diễn ra này
        dialog!!.show()
    }


}