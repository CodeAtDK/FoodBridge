plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
   // id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.foodwastemangmentapplication1"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.foodwastemangmentapplication1"
        minSdk = 34
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        buildConfigField "String", "ATTESTR_API_KEY", "\"${project.hasProperty("ATTESTR_API_KEY") ? project.ATTESTR_API_KEY : ""}\""
//        buildConfigField "String", "ATTESTR_BASE_URL", "\"${project.hasProperty("ATTESTR_BASE_URL") ? project.ATTESTR_BASE_URL : "https://api.attestr.com"}\""

        val atKey = (project.findProperty("ATTESTR_API_KEY") as? String) ?: ""
        val atBase = (project.findProperty("ATTESTR_BASE_URL") as? String) ?: "https://api.attestr.com/"

        buildConfigField("String", "ATTESTR_API_KEY", "\"$atKey\"")
        buildConfigField("String", "ATTESTR_BASE_URL", "\"$atBase\"")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.animation.core.lint)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation Compose Jetpack
    implementation("androidx.navigation:navigation-compose:2.7.3")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:34.5.0"))

    // Firebase analytics
    implementation("com.google.firebase:firebase-analytics")

    // Firebase auth
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.4.0")

    // Material 3 theme importing
    implementation("androidx.compose.material3:material3:1.2.1") // Or the latest version

    //Coil for image displaying
    implementation("io.coil-kt:coil-compose:2.4.0") // Use the latest version

    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-firestore")

    // locations
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // inside dependencies { ... }
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation("com.google.mlkit:text-recognition:16.0.1")


    implementation("com.google.firebase:firebase-storage")
}