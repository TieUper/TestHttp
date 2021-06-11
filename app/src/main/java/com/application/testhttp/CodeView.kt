@file:JvmName("Foo")
package com.application.testhttp

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CodeView : AppCompatTextView {

    @Deprecated("test",)
    constructor(context: Context?) : this(context, null) {
        println("test Constructor")
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        println("test Constructor 2")
    }

    init {
        println("test Init2222222")

        test3("name",this::test2)
    }

    companion object{
        @JvmField
        var name = "zhangsan"

        @JvmStatic
        fun getInstance() {

        }
    }

    fun test3(testaaa1: String, testaaa: (test2: String) -> Unit) {
        testaaa.invoke(testaaa1)
    }

    fun test2(test :String) {
        println("测试代码： $test")
    }

    @JvmField
    var age = 12


}