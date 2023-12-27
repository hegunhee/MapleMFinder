plugins {
    id("maplemfinder.android.library")
    id("maplemfinder.android.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.hegunhee.maplemfinder.core.data"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.moshi)
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}