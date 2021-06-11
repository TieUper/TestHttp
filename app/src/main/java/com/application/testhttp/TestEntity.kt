package com.application.testhttp

import java.io.Serializable

data class TestEntity (val data: Long,
                       val name: String?,
        val content: Content1?
) : Serializable

data class Content1(val name: String,
                    val age : Int)