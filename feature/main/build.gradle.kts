plugins {
    id("maplemfinder.android.feature")
}

android {
    namespace = "com.hegunhee.maplemfinder.feature.main"
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))

}