package com.latihan.lalabib.movi.favorite.di

import android.content.Context
import com.latihan.lalabib.movi.di.FavoriteDependencies
import com.latihan.lalabib.movi.favorite.ui.FavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteDependencies::class])
interface FavoriteComponent {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteDependencies: FavoriteDependencies): Builder
        fun build(): FavoriteComponent
    }
}