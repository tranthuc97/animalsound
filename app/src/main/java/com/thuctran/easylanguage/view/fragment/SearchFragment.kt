package com.thuctran.easylanguage.view.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.R
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.databinding.FragmentReviewBinding
import com.thuctran.easylanguage.databinding.FragmentSearchBinding
import com.thuctran.easylanguage.model.Word
import com.thuctran.easylanguage.view.activity.MainActivity
import com.thuctran.easylanguage.viewmodel.CommonVM
import java.net.URLEncoder

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    companion object{
        var TAG:String = SearchFragment::class.java.name
    }
    override val viewModel by viewModels<CommonVM>()
    private var word:Word? = null
    private var text:String? = null

    override fun initViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding!!.lnResult.isInvisible = true
        callBack1!!.callBack(MainActivity.KEY_ICON_SEARCH,null)
        binding!!.bgSearch.setOnClickListener{
            binding!!.bgSearch.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            text = binding!!.edtInputWord.text.toString()
            Log.i(TAG,"từ vừa nhập: $text")
            Log.i(TAG,"danh sách tất cả từ: ${App.INSTANCE.STORAGE.allWordInApp}")
            Log.i(TAG,"có chưa từ $text không: ${App.INSTANCE.STORAGE.allWordInApp.contains(Word(text!!))}")

            if(App.INSTANCE.STORAGE.allWordInApp.contains(Word(text!!))) {
                binding!!.lnResult.isVisible = true
                word = App.INSTANCE.STORAGE.allWordInApp[App.INSTANCE.STORAGE.allWordInApp.indexOf(
                    Word(text!!)
                )]     //trả về word ở vị trí tìm được
                Log.i(TAG,"từ tìm thấy: ${word.toString()}")
                binding!!.tvWord.text = word!!.WORD
                binding!!.tvHint.text = word!!.HINT
                binding!!.tvTranslate.text = word!!.TRANSLATE
                binding!!.tvSpelling.text = word!!.SPELLING
            }else{
                showNotify("từ nhập sai hoặc chưa cập nhật kịp!!")
            }


            binding!!.ivTranslate.setOnClickListener{
                binding!!.tvTranslate.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
                if (binding!!.tvHint.text == word!!.HINT) {
                    binding!!.tvHint.text = word!!.TRANSHINT
                } else if (binding!!.tvHint.text == word!!.TRANSHINT) {
                    binding!!.tvHint.text = word!!.HINT
                }
            }

            binding!!.ivSound.setOnClickListener {
                var anim: AnimationDrawable = binding!!.ivSound.drawable as AnimationDrawable
                anim.start()
                binding!!.ivSound.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))

                val player = MediaPlayer()
                player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                player.setDataSource(word!!.MP3)
                player.prepare()
                player.start()
            }
        }

        //PT điều khiển nút backPress
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callBack1!!.showDialogCloseApp()
            }
        })
    }

}
//tra google từ điển cambridge
//val word:String = URLEncoder.encode(text,"UTF-8")
//            val uri: Uri = Uri.parse("https://dictionary.cambridge.org/vi/dictionary/english/$word/")
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            ContextCompat.startActivity(context1!!, intent, null)