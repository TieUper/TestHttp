
package com.application.testhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonElement
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    val name : String by lazy {
        "zhangsan"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var codeView = CodeView(this)

        val age = 16;

        val age2 = when {
            TextUtils.equals(name, "lisi") -> 18
            TextUtils.equals(name, "zhangsan") -> 19
            else -> 20
        }
        println("我是测试代码 $age2")

        for (i in 0..99) {
//            println("test: $i")
        }

        for (i in 0 until2 100) {
//            println("test2: $i")
        }


        val test2 = Test2()
        test2.age = 10

        println(test2.age)


        var create = create<API>()

        var name : String ?= "  "
        var empty = TextUtils.isEmpty(name)
        var nullOrEmpty = name.isNullOrEmpty()

        println("name result: $empty")

        var name1 = CodeView.name
        CodeView.getInstance()

        var value : String ?= "aaa"
        var let = value?.let {
            1
        } ?: "zhangsan"

        println("value: $let")


        var a = 1
        var b = 2

        var also = b.apply {
          b =  20
        }

        println("also: $also  b: $b")

        val student = Student("zhangsan", 20)

        var also1 = student.also {
            student.name = "lisi"
        }

        println("also1: $also1  student: $student")

        val content = "{\n" +
                "\t\"data\": 123456\n" +
                "}"


        var entity = Gson().fromJson<TestEntity>(content)
        println(entity.name?: "aaa")
    }

    inline fun <reified T : Any> Gson.fromJson(json: String) : T = this.fromJson(json, T::class.java)

    val build = Retrofit.Builder()
        .baseUrl("https://api.hencoder.com/")
        .build()

    inline fun <reified T> create() : T {
       return build.create(T::class.java)
    }

    infix fun Int.until2(to: Int) : IntRange {
        if(to <= Int.MIN_VALUE) return IntRange.EMPTY
        return this..to
    }
}