package com.mandeep.marvelbook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Tue 04 Apr, 2023
 *
 **/
@HiltAndroidApp
class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}