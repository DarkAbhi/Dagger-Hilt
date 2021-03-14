package com.darkabhi.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(someClass.doAThing())
        println(someClass.doSomeOtherThing())
    }
}


class SomeClass @Inject constructor(private val someOtherClass: SomeOtherClass) {
    fun doAThing(): String {
        return "I DID IT!"
    }

    fun doSomeOtherThing(): String {
        return someOtherClass.doSomeOtherThing()
    }
}

class SomeOtherClass @Inject constructor() {
    fun doSomeOtherThing(): String {
        return "I DID THE OTHER THING!"
    }
}