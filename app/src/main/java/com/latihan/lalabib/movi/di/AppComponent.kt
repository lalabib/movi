package com.latihan.lalabib.movi.di

import com.latihan.lalabib.movi.core.di.CoreComponent
import com.latihan.lalabib.movi.detail.DetailActivity
import com.latihan.lalabib.movi.favorite.FavoriteActivity
import com.latihan.lalabib.movi.home.HomeActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: HomeActivity)
    fun inject(activity: DetailActivity)
    fun inject(activity: FavoriteActivity)
}