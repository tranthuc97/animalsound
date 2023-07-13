package com.thuctran.easylanguage.view.fragment.learn

import android.graphics.drawable.AnimationDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.R
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.databinding.FragmentLearnP2Binding
import com.thuctran.easylanguage.view.dialog.BaseDialog
import com.thuctran.easylanguage.view.dialog.DialogCheck
import com.thuctran.easylanguage.view.fragment.BaseFragment

@Suppress("DEPRECATION")
class P2LearnFragment : BaseFragment<FragmentLearnP2Binding>(), BaseDialog.CallBack {
    companion object {
        var TAG: String = P2LearnFragment::class.java.name
        val CHECK_WORD: String = "CHECK_WORD"
        val WRONG_WORD: String = "WRONG_WORD"
    }



    private var dialog: DialogCheck? = null

    override fun initViewBinding(): FragmentLearnP2Binding {
        return FragmentLearnP2Binding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding!!.tvContinue.isInvisible = true
        binding!!.ivSound.setOnClickListener {
            var anim: AnimationDrawable = binding!!.ivSound.drawable as AnimationDrawable
            anim.start()
            binding!!.ivSound.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            val player = MediaPlayer()
            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
            player.setDataSource(App.INSTANCE.STORAGE.word!!.MP3)
            player.prepare()
            player.start()
        }

        //show dialog
        binding!!.btCheck.setOnClickListener {
            binding!!.btCheck.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            if(binding!!.edtInputWord.text.toString().isEmpty()){
                showNotify("nhập từ vào ô trống!")

            }else {
                binding!!.btCheck.isClickable = false
                CommonUtils.INSTANCE.savePrefs(CHECK_WORD, binding!!.edtInputWord.text.toString())
                if(binding!!.edtInputWord.text.toString() != App.INSTANCE.STORAGE.word!!.WORD){
                    CommonUtils.INSTANCE.savePrefs(WRONG_WORD,binding!!.edtInputWord.text.toString())
                }
                binding!!.tvContinue.isInvisible = false
                Log.i(TAG, "ô đã nhập chữ: ${binding!!.edtInputWord.text}")
                showDialog()
            }
        }

        binding!!.tvContinue.setOnClickListener {
            binding!!.tvContinue.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            if (binding!!.edtInputWord.text.toString().isEmpty()) {
                showNotify("nhập từ vào ô trống!")
            } else {
                callBack1!!.showFragmentLearnDetail(P3LearnFragment.TAG,null,false)
            }
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