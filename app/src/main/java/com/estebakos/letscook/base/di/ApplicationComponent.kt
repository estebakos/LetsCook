package com.estebakos.letscook.base.di

import android.app.Application
import com.estebakos.letscook.LetsCookApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, NetworkModule::class, DataModule::class, ActivityModule::class, FragmentModule::class, CacheModule::class])
interface ApplicationComponent : AndroidInjector<LetsCookApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

}