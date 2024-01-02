plugins {
    id("maplemfinder.android.library")
    id("maplemfinder.android.compose")
}

android {
    namespace = "com.hegunhee.maplemfinder.core.designsystem"
}

dependencies {
    implementation(project(":core:model"))
}