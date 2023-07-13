package com.thuctran.easylanguage.viewmodel

import android.util.Log
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.model.Topic
import com.thuctran.easylanguage.model.Word
import com.thuctran.easylanguage.view.fragment.learn.P3LearnFragment
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class AddListVM : BaseViewModel() {
    companion object {
        var TAG: String = AddListVM::class.java.name
    }

    private var translateTopic: String = "translate topic.txt"

     fun addList() {
        addListSchool()
    }

    private fun addListSchool() {
        CommonUtils.INSTANCE.savePrefs(P3LearnFragment.COUNT,"0")       //số đếm ban đầu của progressBar lưu trong prefs với tên là COUNT
        //set luôn thanh progressBar ở đây để ko bị chạy lại dữ liệu

        val listSchool: Array<String> =
            App.INSTANCE.assets.list("school/")!!       //ds các tệp trong assets/school\
        val listExamination: Array<String> =
            App.INSTANCE.assets.list("examination/")!!

        for (path in listSchool) {
            if (path == translateTopic) {     //nếu tệp là translate topic.txt
                val input: InputStream =
                    App.INSTANCE.assets.open("school/$translateTopic")   //mở file

                val isr = InputStreamReader(
                    input,
                    StandardCharsets.UTF_8
                )  //khởi tạo 1 reader để chuyền xuống dưới
                val reader =
                    BufferedReader(isr)   //sử dụng BufferedReader sẽ hiệu quả hơn khi mở và đọc file text

                var topic: String? = null     //khởi tạo name
                var translate: String? = null     //khởi tạo translate
                var picture: String? = null     //khởi tạo picture

                var line =
                    reader.readLine()     //lấy reader ở trên đọc từng dòng (ở đây ko đc khai báo là kiểu String vì có thể dẫn đến reader.readLine() must not be null
                var model: Topic        //khởi tạo ĐT StoryModel, tí nữa sẽ add từng thằng này vào trong listStory

                //vòng lặp while với line luôn khác null, nếu Line == null tức là đã đọc hết các dòng, vòng lặp sẽ dừng lại
                while (line != null) {
                    if (topic == null) {
                        topic = line.replace(
                            "topic:",
                            ""
                        ) //khi name chưa được khởi tạo thì gán luôn name (tiêu đề truyện) bằng dòng đầu tiên
                    } else if (line.contains("translate")) {
                        translate = line.replace("translate:", "")
                    } else if (line.contains("picture")) {
                        picture = line.replace("picture:", "")
                    }
                    line = reader.readLine()    //tiếp tục đọc dòng tiếp theo
                }
                model = Topic(topic, translate, picture)
                App.INSTANCE.STORAGE.listTopic.add(model)

                input.close()
                isr.close()
                reader.close()  //đọc xong rồi nhớ đóng lại
                Log.i(TAG, "topic: ${App.INSTANCE.STORAGE.listTopic}")
            } else {
                val input: InputStream =
                    App.INSTANCE.assets.open("school/$path")   //mở file

                val isr = InputStreamReader(
                    input,
                    StandardCharsets.UTF_8
                )  //khởi tạo 1 reader để chuyền xuống dưới
                val reader =
                    BufferedReader(isr)   //sử dụng BufferedReader sẽ hiệu quả hơn khi mở và đọc file text

                var id:String? = null
                var word: String? = null     //khởi tạo name
                var spelling: String? = null     //khởi tạo translate
                var type: String? = null     //khởi tạo picture
                var translate: String? = null     //khởi tạo picture
                var hint: String? = null     //khởi tạo picture
                var trasHint: String? = null     //khởi tạo picture
                var mp3: String? = null     //khởi tạo picture
                var picture: String? = null     //khởi tạo picture


                var line =
                    reader.readLine()     //lấy reader ở trên đọc từng dòng (ở đây ko đc khai báo là kiểu String vì có thể dẫn đến reader.readLine() must not be null
                var model: Word        //khởi tạo ĐT StoryModel, tí nữa sẽ add từng thằng này vào trong listStory

                //vòng lặp while với line luôn khác null, nếu Line == null tức là đã đọc hết các dòng, vòng lặp sẽ dừng lại
                while (line != null) {
                    if (id == null) {
                        id = line.replace("id:","")//khi name chưa được khởi tạo thì gán luôn name (tiêu đề truyện) bằng dòng đầu tiên
                    } else if (line.contains("word")) {
                        word = line.replace("word:", "")
                    } else if (line.contains("spelling")) {
                        spelling = line.replace("spelling:", "")
                    } else if (line.contains("type")) {
                        type = line.replace("type:", "")
                    } else if (line.contains("translate")) {
                        translate = line.replace("translate:", "")
                    } else if (line.contains("hint")) {
                        hint = line.replace("hint:", "")
                    } else if (line.contains("trasHint")) {
                        trasHint = line.replace("trasHint:", "")
                    } else if (line.contains("mp3")) {
                        mp3 = line.replace("mp3:", "")
                    } else if (line.contains("picture")) {
                        picture = line.replace("picture:", "")
                    }
                    line = reader.readLine()    //tiếp tục đọc dòng tiếp theo
                }
                model = Word(id!!.toInt(),word,spelling,type,translate,hint,trasHint,mp3,picture)
                App.INSTANCE.STORAGE.listWordSchool.add(model)

                input.close()
                isr.close()
                reader.close()  //đọc xong rồi nhớ đóng lại
            }
        }
        for (path in listExamination) {
            if (path == translateTopic) {     //nếu tệp là translate topic.txt
                val input: InputStream =
                    App.INSTANCE.assets.open("examination/$translateTopic")   //mở file

                val isr = InputStreamReader(
                    input,
                    StandardCharsets.UTF_8
                )  //khởi tạo 1 reader để chuyền xuống dưới
                val reader =
                    BufferedReader(isr)   //sử dụng BufferedReader sẽ hiệu quả hơn khi mở và đọc file text

                var topic: String? = null     //khởi tạo name
                var translate: String? = null     //khởi tạo translate
                var picture: String? = null     //khởi tạo picture

                var line =
                    reader.readLine()     //lấy reader ở trên đọc từng dòng (ở đây ko đc khai báo là kiểu String vì có thể dẫn đến reader.readLine() must not be null
                var model: Topic        //khởi tạo ĐT StoryModel, tí nữa sẽ add từng thằng này vào trong listStory

                //vòng lặp while với line luôn khác null, nếu Line == null tức là đã đọc hết các dòng, vòng lặp sẽ dừng lại
                while (line != null) {
                    if (topic == null) {
                        topic = line.replace(
                            "topic:",
                            ""
                        ) //khi name chưa được khởi tạo thì gán luôn name (tiêu đề truyện) bằng dòng đầu tiên
                    } else if (line.contains("translate")) {
                        translate = line.replace("translate:", "")
                    } else if (line.contains("picture")) {
                        picture = line.replace("picture:", "")
                    }
                    line = reader.readLine()    //tiếp tục đọc dòng tiếp theo
                }
                model = Topic(topic, translate, picture)
                App.INSTANCE.STORAGE.listTopic.add(model)

                input.close()
                isr.close()
                reader.close()  //đọc xong rồi nhớ đóng lại
                Log.i(TAG, "topic: ${App.INSTANCE.STORAGE.listTopic}")
            } else {
                val input: InputStream =
                    App.INSTANCE.assets.open("examination/$path")   //mở file

                val isr = InputStreamReader(
                    input,
                    StandardCharsets.UTF_8
                )  //khởi tạo 1 reader để chuyền xuống dưới
                val reader =
                    BufferedReader(isr)   //sử dụng BufferedReader sẽ hiệu quả hơn khi mở và đọc file text

                var id:String? = null
                var word: String? = null     //khởi tạo name
                var spelling: String? = null     //khởi tạo translate
                var type: String? = null     //khởi tạo picture
                var translate: String? = null     //khởi tạo picture
                var hint: String? = null     //khởi tạo picture
                var trasHint: String? = null     //khởi tạo picture
                var mp3: String? = null     //khởi tạo picture
                var picture: String? = null     //khởi tạo picture


                var line =
                    reader.readLine()     //lấy reader ở trên đọc từng dòng (ở đây ko đc khai báo là kiểu String vì có thể dẫn đến reader.readLine() must not be null
                var model: Word        //khởi tạo ĐT StoryModel, tí nữa sẽ add từng thằng này vào trong listStory

                //vòng lặp while với line luôn khác null, nếu Line == null tức là đã đọc hết các dòng, vòng lặp sẽ dừng lại
                while (line != null) {
                    if (id == null) {
                        id = line.replace("id:","")//khi name chưa được khởi tạo thì gán luôn name (tiêu đề truyện) bằng dòng đầu tiên
                    } else if (line.contains("word")) {
                        word = line.replace("word:", "")
                    } else if (line.contains("spelling")) {
                        spelling = line.replace("spelling:", "")
                    } else if (line.contains("type")) {
                        type = line.replace("type:", "")
                    } else if (line.contains("translate")) {
                        translate = line.replace("translate:", "")
                    } else if (line.contains("hint")) {
                        hint = line.replace("hint:", "")
                    } else if (line.contains("trasHint")) {
                        trasHint = line.replace("trasHint:", "")
                    } else if (line.contains("mp3")) {
                        mp3 = line.replace("mp3:", "")
                    } else if (line.contains("picture")) {
                        picture = line.replace("picture:", "")
                    }
                    line = reader.readLine()    //tiếp tục đọc dòng tiếp theo
                }
                model = Word(id!!.toInt(),word,spelling,type,translate,hint,trasHint,mp3,picture)
                App.INSTANCE.STORAGE.listWordExamination.add(model)

                input.close()
                isr.close()
                reader.close()  //đọc xong rồi nhớ đóng lại
            }
        }

        Log.i(TAG, "list word: ${App.INSTANCE.STORAGE.listWordSchool}")
        Log.i(TAG, "list wordExamination: ${App.INSTANCE.STORAGE.listWordExamination}")
        App.INSTANCE.STORAGE.allWordInApp.addAll(App.INSTANCE.STORAGE.listWordSchool)
        App.INSTANCE.STORAGE.allWordInApp.addAll(App.INSTANCE.STORAGE.listWordExamination)
    }
}