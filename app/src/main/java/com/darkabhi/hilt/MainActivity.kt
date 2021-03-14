package com.darkabhi.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
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

class SomeClass @Inject constructor(
    private val someInterfaceImpl: SomeInterface,
    private val gson: Gson
) {
    fun doAThing(): String {
        return "I GOT IT ${someInterfaceImpl.getAThing()} \n \n $gson"
    }
}

class SomeInterfaceImpl @Inject constructor(
    private val someDependency: String
) : SomeInterface {
    override fun getAThing(): String {
        return "A THING $someDependency "
    }
}

interface SomeInterface {
    fun getAThing(): String
}

@InstallIn(SingletonComponent::class)
@Module
class MyModule {

    @Singleton
    @Provides
    fun provideSomeString(): String {
        return "Some string"
    }

    @Singleton
    @Provides
    fun provideSomeInterface(someString: String): SomeInterface {
        return SomeInterfaceImpl(someString)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}
