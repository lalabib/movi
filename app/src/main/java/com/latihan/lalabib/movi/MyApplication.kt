package com.latihan.lalabib.movi

import android.app.Application
import com.latihan.lalabib.movi.core.di.CoreComponent
import com.latihan.lalabib.movi.core.di.DaggerCoreComponent
import com.latihan.lalabib.movi.di.AppComponent
import com.latihan.lalabib.movi.di.DaggerAppComponent

open class MyApplication: Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}