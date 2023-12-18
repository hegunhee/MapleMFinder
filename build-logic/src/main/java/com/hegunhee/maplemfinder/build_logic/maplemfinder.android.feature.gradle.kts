package com.hegunhee.maplemfinder.build_logic

import com.hegunhee.maplemfinder.build_logic.setting.configureComposeAndroid
import com.hegunhee.maplemfinder.build_logic.setting.configureHiltAndroid
import com.hegunhee.maplemfinder.build_logic.setting.configureKotlinAndroid

plugins {
    id("com.android.library")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureHiltAndroid()
configureComposeAndroid()
configureKotlinAndroid()