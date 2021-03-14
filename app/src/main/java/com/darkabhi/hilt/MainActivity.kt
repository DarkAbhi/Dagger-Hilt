package com.darkabhi.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(someClass.doAThing())
    }
}

class SomeClass @Inject constructor(private val someInterfaceImpl: SomeInterface, private val gson: Gson) {
    fun doAThing(): String {
        return "I GOT IT ${someInterfaceImpl.getAThing()}"
    }
}

class SomeInterfaceImpl @Inject constructor():SomeInterface {
    override fun getAThing():String{
        return "A THING"
    }
}

interface SomeInterface{
    fun getAThing():String
}
