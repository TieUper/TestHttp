
@file:JvmName("Foo")
package com.application.testhttp

import java.util.*


fun test() {
    println("testValue")
}

class Test2 {
    var name: String = "zhangsan"
        get() = field.toUpperCase(Locale.ROOT)
    var age: Int = 0
        set(value) {
            if (value < 10) {
                field = value
            }else {
                field = -1
            }
        }
}
