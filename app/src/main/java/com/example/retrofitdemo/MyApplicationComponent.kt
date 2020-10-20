package com.example.retrofitdemo

import dagger.Component

@Component(modules = [MyModule::class])
interface ApplicationComponent {

    // where you want to inject
    fun inject(activity: MainActivity)

}