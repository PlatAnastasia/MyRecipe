import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.project.myrecipe"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.project.myrecipe"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures{
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    //Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    // WorkManager dependency
    implementation ("androidx.work:work-runtime:2.7.1")
    // Optional: For Kotlin Coroutines support in WorkManager
    implementation ("androidx.work:work-runtime-ktx:2.7.1")

    // Hilt for dependency injection
    implementation ("com.google.dagger:hilt-android:2.42") // or latest version
    kapt ("com.google.dagger:hilt-android-compiler:2.42") // or latest version

    // ViewModel and Hilt integration
    kapt ("androidx.hilt:hilt-compiler:1.0.0") // or latest version

    // Hilt WorkManager integration (if you're using WorkManager with Hilt)
    implementation ("androidx.hilt:hilt-work:1.0.0") // or latest version

    // Hilt for testing (optional, for testing)
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.42") // or latest version
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.42")
    // Hilt for WorkManager Integration
    implementation ("androidx.hilt:hilt-work:1.0.0")
    // WorkManager
    implementation ("androidx.work:work-runtime-ktx:2.7.1") // or latest version
    // WorkManager Hilt integration
    implementation ("androidx.hilt:hilt-work:1.0.0") // or latest version

}