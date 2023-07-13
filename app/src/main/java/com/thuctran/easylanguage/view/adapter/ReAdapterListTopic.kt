package com.thuctran.easylanguage.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.R
import com.thuctran.easylanguage.model.ClassModel
import com.thuctran.easylanguage.model.Topic

class ReAdapterListTopic(private var context: Context,private var event:View.OnClickListener) : RecyclerView.Adapter<ReAdapterListTopic.ClassHolder>() {
    companion object{
        var TAG:String = "ReAdapterListTopic"
    }
    private var enTopic:MutableLiveData<String> = MutableLiveData("")
    val ENTOPIC:MutableLiveData<String>
        get() = enTopic

    val EVENT:View.OnClickListener      //khai báo event này giống một dạng callback, nhờ thằng MainFragment Click hộ
        get() = event

    //từ item_view trong res/layout ta ánh xạ thành ClassHolder(inflate)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassHolder {
        val view:View =LayoutInflater.from(context).inflate(R.layout.item_topic, parent,false)
        return ClassHolder(view)
    }

    //PT định nghĩa số lượng item để đúc
    override fun getItemCount(): Int {
        return App.INSTANCE.STORAGE.listTopic!!.size
    }

    //ClassHolder lấy ở thằng class bên dưới, đây là PT đưa vào số lượng itemdata, để nhét dữ liệu tương ứng của từng data vào từng ClassHolder
    override fun onBindViewHolder(holder: ClassHolder, position: Int) {
        var data: Topic = App.INSTANCE.STORAGE.listTopic[position]       //thay Any bằng tên Model tương ứng
        //ta có thể lấy dữ liệu của từng Model ở vị trí position

        Log.i(TAG,"Link ảnh: ${data.PICTURE}")
        Glide.with(context).load(data.PICTURE).into(holder.ivTopic)
        holder.tvEnTopic.text = data.TOPIC
        holder.tvVnTopic.text = data.TRANSLATE

    }

    //Holder ở đây có nghĩa là đồ đựng
    inner class ClassHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //tại đây có thể khai báo từng ĐT View tương ứng của thằng itemView. VD: var tvStoryName:TextView = itemView.findViewById(R.id.tv_story_name)
        //muốn setOnclick cho từng ĐT View khai báo thì phải đặt trong khố init

        var ivTopic:ImageView = itemView.findViewById(R.id.ivTopic)
        var ivFunny:ImageView = itemView.findViewById(R.id.ivFunny)
        var tvEnTopic:TextView = itemView.findViewById(R.id.tvEnTopic)
        var tvVnTopic:TextView = itemView.findViewById(R.id.tvVnTopic)

        init {
            itemView.setOnClickListener{
                itemView.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
                it.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_fade_in))
                event.onClick(it)     //từ event gọi click view
                enTopic.value = tvEnTopic.text as String
            }
        }
    }

}