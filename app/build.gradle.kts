plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.chaitanya.creditapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chaitanya.creditapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //networking retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //okhttp logging interceptor
    implementation(libs.logging.interceptor)

    //navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    //coroutines
    implementation(libs.kotlinx.coroutines.core)

    //recycler view
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.recyclerview.selection)

    //glide
    implementation(libs.glide)
    //seekbar lib
    implementation("me.tankery.lib:circularSeekBar:1.4.2")


}