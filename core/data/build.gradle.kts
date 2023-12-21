plugins {
    id("maplemfinder.android.library")
    id("maplemfinder.android.hilt")
}

android {
    namespace = "com.hegunhee.maplemfinder.core.data"

}

dependencies {

    implementation(project(":core:domain"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.moshi)
}