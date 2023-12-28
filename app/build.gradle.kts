plugins {
    id("maplemfinder.android.application")
}

android {
    namespace = "com.hegunhee.maplemfinder.app"
    defaultConfig {
        applicationId = "com.hegunhee.maplemfinder.app"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":feature:main"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.ktx)
    implementation(libs.activity.compose)
    implementation(libs.compose.navigation)
}