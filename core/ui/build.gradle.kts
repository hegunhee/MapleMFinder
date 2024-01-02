plugins {
    id("maplemfinder.android.library")
    id("maplemfinder.android.compose")
}

android {
    namespace = "com.hegunhee.maplemfinder.core.ui"
}

dependencies {

    implementation(project(":core:model"))
}