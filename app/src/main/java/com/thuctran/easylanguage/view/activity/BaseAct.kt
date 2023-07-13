package com.thuctran.easylanguage.view.activity

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.R
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.thuctran.easylanguage.OnMainCallBack
import com.thuctran.easylanguage.view.fragment.BaseFragment
import java.lang.reflect.Constructor

abstract class BaseAct<V : ViewBinding, M : ViewModel> : AppCompatActivity(), OnClickListener,
    OnMainCallBack {
    protected var binding: V? = null
    protected var viewModel: M? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding()
        setContentView(binding!!.root)
        viewModel = ViewModelProvider(this).get(getClassVM()::class.java)

        initViews()
    }

    abstract fun getClassVM(): M

    abstract fun initViewBinding(): V

    abstract fun initViews()

    final override fun onClick(v: View?) {
        v!!.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_fade_in))
        clickView(v)
    }

    protected fun clickView(v: View) {
        //do sothing
    }

    protected fun showNotify(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showNotify(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showFragment(tag: String, data: Any?, isBacked: Boolean) {
        try {
            var clazz: Class<*> = Class.forName(tag)        //trỏ vào một fragment class thông qua tag truyền vào
            var constructor: Constructor<*> = clazz.getConstructor()        //tạo Constructor
            var baseFragment: BaseFragment<*> = constructor.newInstance() as BaseFragment<*>        //Tạo 1 thực thể từ lớp Fragment

            baseFragment.CALLBACK = this
            if(data!=null){
                baseFragment.DATA = data
            }

            var trans: FragmentTransaction = supportFragmentManager.beginTransaction()
                .replace(com.thuctran.easylanguage.R.id.lnHomeAct, baseFragment, tag)

            if (isBacked) {
                trans.addToBackStack(null)      //trở về màn hình trước đó, true thì có cho phép back
            }
            trans.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out)       //custom hiệu ứng khi chuyển tiếp các fragment
            trans.commit()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun showFragment2(tag: String, data: Any?, isBacked: Boolean) {
        try {
            var clazz: Class<*> = Class.forName(tag)        //trỏ vào một fragment class thông qua tag truyền vào
            var constructor: Constructor<*> = clazz.getConstructor()        //tạo Constructor
            var baseFragment: BaseFragment<*> = constructor.newInstance() as BaseFragment<*>        //Tạo 1 thực thể từ lớp Fragment

            baseFragment.CALLBACK = this
            if(data!=null){
                baseFragment.DATA = data
            }

            var trans: FragmentTransaction = supportFragmentManager.beginTransaction()
                .replace(com.thuctran.easylanguage.R.id.lnMainAct, baseFragment, tag)

            if (isBacked) {
                trans.addToBackStack(null)      //trở về màn hình trước đó, true thì có cho phép back
            }
            trans.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out)       //custom hiệu ứng khi chuyển tiếp các fragment
            trans.commit()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun showFragmentLearnDetail(tag: String, data: Any?, isBacked: Boolean) {
        try {
            var clazz: Class<*> = Class.forName(tag)        //trỏ vào một fragment class thông qua tag truyền vào
            var constructor: Constructor<*> = clazz.getConstructor()        //tạo Constructor
            var baseFragment: BaseFragment<*> = constructor.newInstance() as BaseFragment<*>        //Tạo 1 thực thể từ lớp Fragment

            baseFragment.CALLBACK = this
            if(data!=null){
                baseFragment.DATA = data
            }

            var trans: FragmentTransaction = supportFragmentManager.beginTransaction()
                .replace(com.thuctran.easylanguage.R.id.lnActLearn, baseFragment, tag)

            if (isBacked) {
                trans.addToBackStack(null)      //trở về màn hình trước đó, true thì có cho phép back
            }
            trans.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out)       //custom hiệu ứng khi chuyển tiếp các fragment
            trans.commit()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun showFragmentReviewDetail(tag: String, data: Any?, isBacked: Boolean) {
        try {
            var clazz: Class<*> = Class.forName(tag)        //trỏ vào một fragment class thông qua tag truyền vào
            var constructor: Constructor<*> = clazz.getConstructor()        //tạo Constructor
            var baseFragment: BaseFragment<*> = constructor.newInstance() as BaseFragment<*>        //Tạo 1 thực thể từ lớp Fragment

            baseFragment.CALLBACK = this
            if(data!=null){
                baseFragment.DATA = data
            }

            var trans: FragmentTransaction = supportFragmentManager.beginTransaction()
                .replace(com.thuctran.easylanguage.R.id.lnActReview, baseFragment, tag)

            if (isBacked) {
                trans.addToBackStack(null)      //trở về màn hình trước đó, true thì có cho phép back
            }
            trans.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out)       //custom hiệu ứng khi chuyển tiếp các fragment
            trans.commit()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun execOnMainThread(runnable: Runnable) {
        runOnUiThread(runnable)     //thực thi môi trường runnable
    }

}